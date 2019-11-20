package com.gun.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cyber.lottery.dao.FunctionDefDAO;
import com.cyber.lottery.dao.RoleAuthorityDAO;
import com.cyber.lottery.dao.RoleDefDAO;
import com.cyber.lottery.dao.SysUserDAO;
import com.gun.common.entity.RoleAuthority;
import com.gun.common.entity.RoleDef;
import com.gun.common.entity.pojo.FunctionTypeDTO;
import com.gun.common.entity.pojo.RoleAuthorityDTO;
import com.gun.common.entity.pojo.RoleDefDTO;
import com.gun.common.entity.pojo.SysUserInfoDTO;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.LotteryMessage;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;
import com.gun.common.pojo.form.SystemForm;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.common.utils.SimpleDtoDmoTransformer;
import com.gun.common.utils.StringUtils;
import com.gun.service.RoleAuthorityService;

/**
 * 
 * @author felixli
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService{
	private static final Log log = LogFactory.getLog(RoleAuthorityServiceImpl.class);
	
  private SimpleDtoDmoTransformer transformer = new SimpleDtoDmoTransformer();
	
	@Autowired
	private RoleAuthorityDAO roleAuthorityDAO;
	
	@Autowired
	private RoleDefDAO roleDefDAO;
	
	@Autowired
  private SysUserDAO sysUserDAO;
	
  /**
   * 功能清單檔DAO
   */
  @Autowired(required=true)
  private FunctionDefDAO functionDefDAO;

	/**
	 * (non-Javadoc)
	 * @see com.gun.service.LotteryService#init(java.lang.Object)
	 */
  public Model init(Object request) throws ServiceException {
		Model model = new Model();
		return model;
	}
	
	/** (non-Javadoc)
   * @see com.gun.service.RoleAuthorityService#list()
   */
  public Model list(SystemForm systemForm) throws ServiceException {
    Model model = new Model();
    LotteryMessage message = null;
    List<ParamItem> roleList = new ArrayList<ParamItem>();
    try {
      //分頁元件
      PageInfo pageInfo = new PageInfo();
      if(StringUtils.hasText(systemForm.getPageSet())) {
        pageInfo.setMaxIndex(Integer.parseInt(systemForm.getPageSet()));
        pageInfo.setSize(Integer.parseInt(systemForm.getPageSet()));
      }
      if(systemForm.getPageNo()!=null) {
        pageInfo.setPage(systemForm.getPageNo());
      }
      roleList = this.roleDefDAO.list(pageInfo);
      if (CollectionUtils.isEmpty(roleList)&& systemForm.getPageNo()!=null &&systemForm.getPageNo()>=1) {
        systemForm.setPageNo(systemForm.getPageNo()-1);
        pageInfo.setPage(systemForm.getPageNo());
        roleList = this.roleDefDAO.list(pageInfo);
      }
      if(CollectionUtils.isEmpty(roleList)){
        systemForm.setNotDataCode(LotteryMessageCode.DATA_NOT_FOUND);
      }
      systemForm.setRoleResults(roleList);
      this.getAllFunction(systemForm);
      message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.INIT_SUCCESS);
      if(StringUtils.hasText(systemForm.getMessageCode())){
        message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,systemForm.getMessageCode());
      }
      model.setPageInfo(pageInfo);
    } catch (Exception e) {
      log.error(this.getClass().getName()+"list failed!! ", e);
      message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.INIT_PAGE_FAILED);
      throw new ServiceException(message);
    }
    model.setMessage(message);
    return model;
  }
  
  private SystemForm getAllFunction(SystemForm systemForm) throws Exception {
    try {
      //功能權限清單查詢
      List<FunctionTypeDTO> qryFuncList = this.functionDefDAO.queryFunctionsByParentId(null);
      if(null != qryFuncList && !CollectionUtils.isEmpty(qryFuncList)){
        //整理功能權限清單
        List<FunctionTypeDTO> functionList = new ArrayList<FunctionTypeDTO>();
        Map<String, List<FunctionTypeDTO>> subFunctionList = new HashMap<String, List<FunctionTypeDTO>>();
        
        for(FunctionTypeDTO functionDef : qryFuncList){
          String funcId = functionDef.getFunctionId();
          String parentFuncid = functionDef.getParentFunctionId();
          
          //若為一級選單
          if(funcId.equals(parentFuncid) || !StringUtils.hasText(parentFuncid)){
            functionList.add(functionDef);
          }
          //若為二級選單
          else{
            if(subFunctionList.get(parentFuncid) != null){
              subFunctionList.get(parentFuncid).add(functionDef);
            }else{
              subFunctionList.put(parentFuncid, new ArrayList<FunctionTypeDTO>());
              subFunctionList.get(parentFuncid).add(functionDef);
            }
          }
            
        }
        
        systemForm.setFunctionList(functionList);
        systemForm.setSubFunctionList(subFunctionList);
      }
    } catch (Exception e) {
      log.error(this.getClass().getName()+".queryByProerties failed:"+e);
      throw new ServiceException(e);
    }
    return systemForm;
    }

	/**
	 * (non-Javadoc)
	 * @see com.gun.service.RoleAuthorityService#queryByProerties(java.lang.Integer)
	 */
	public List<RoleAuthorityDTO> queryByProerties(Integer roleId) throws ServiceException{
		List<RoleAuthorityDTO> results = null;
		try {
			log.debug("---> AuthorityServiceImpl queryByProerties start!!");
			results = this.roleAuthorityDAO.queryRoleAuthorityByRole(roleId);
			log.debug("---> AuthorityServiceImpl queryByProerties end!!");
		} catch (Exception e) {
			log.error(this.getClass().getName()+".queryByProerties failed:"+e);
			throw new ServiceException(e);
		}
		return results;
	}

	/**
	 * (non-Javadoc)
	 * @see com.gun.service.RoleAuthorityService#deleteByRoleId(java.lang.Integer)
	 */
	public Model deleteByRoleId(Integer roleId) throws ServiceException {
	  Model model = new Model();
    LotteryMessage message = null;
		try {
			log.debug("---> AuthorityServiceImpl deleteByRoleId start!!");
			this.roleAuthorityDAO.deleteByRoleId(roleId);
			message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS, LotteryMessageCode.SAVE_SUCCESS);
			model.setMessage(message);
			log.debug("---> AuthorityServiceImpl deleteByRoleId end!!");
		} catch (Exception e) {
			log.error(this.getClass().getName()+".deleteByRoleId failed:"+e);
			message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.SAVE_FAILED);
			model.setMessage(message);
			throw new ServiceException(message);
		}
		return model;
	}

	/**
	 * (non-Javadoc)
	 * @see com.gun.service.RoleAuthorityService#saveOrUpdate(java.util.List)
	 */
	public Model saveOrUpdate(List<RoleAuthorityDTO> roleAuthorityList) throws ServiceException {
	  Model model = new Model();
    LotteryMessage message = null;
		try {
			log.debug("---> AuthorityServiceImpl saveOrUpdate start!!");
			List<RoleAuthority> saveList = new ArrayList<RoleAuthority>();
			RoleAuthority roleAuthority = null;
			for (RoleAuthorityDTO roleAuthorityDTO : roleAuthorityList) {
			  roleAuthority = (RoleAuthority) transformer.transform(roleAuthorityDTO, new RoleAuthority());
			  saveList.add(roleAuthority);
      }
			if(!CollectionUtils.isEmpty(saveList)){
			  this.roleAuthorityDAO.saveOrUpdateAll(saveList);
			  message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS, LotteryMessageCode.SAVE_SUCCESS);
			  model.setMessage(message);
			}
			log.debug("---> AuthorityServiceImpl saveOrUpdate end!!");
		} catch (Exception e) {
			log.error(this.getClass().getName()+".saveOrUpdate failed:"+e);
			message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.SAVE_FAILED);
			throw new ServiceException(message);
		}
		return model;
	}

  /** (non-Javadoc)
   * @see com.gun.service.RoleAuthorityService#getRoleAll()
   */
  public List<ParamItem> getRoleAll() throws ServiceException {
    log.debug("---> AuthorityServiceImpl getRoleAll start!!");
    List<ParamItem> results = new ArrayList<ParamItem>();
    try {
      results = this.roleDefDAO.getRoleList();
      log.debug("---> AuthorityServiceImpl getRoleAll end!!");
    } catch (Exception e) {
      log.error(this.getClass().getName()+".getRoleAll failed:"+e);
      throw new ServiceException(new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.INIT_FAIlED));
    }
    return results;
  }

  /** (non-Javadoc)
   * @see com.gun.service.RoleAuthorityService#saveRole(com.gun.common.entity.pojo.RoleAuthorityDTO)
   */
  public Model saveRole(RoleDefDTO roleDefDTO) throws ServiceException {
    log.debug("---> AuthorityServiceImpl saveRole start!!");
    RoleDef entity =null;
    Model model = new Model();
    LotteryMessage message = null;
    try {
      if(roleDefDTO.getRoleId()!=null){
        entity = this.roleDefDAO.findById(RoleDef.class,roleDefDTO.getRoleId());
        if(entity!=null){
          entity.setRoleName(roleDefDTO.getRoleName());
          this.roleDefDAO.saveOrUpdate(entity);
          message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS, LotteryMessageCode.UPDATE_SUCCESS);
        }else{
          message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.UODATE_ROLE_FAILED);
        }
      }else{
        boolean checkRole = this.roleDefDAO.getRoleDefByRoleName(roleDefDTO.getRoleName());
        if(!checkRole){
          entity = new RoleDef();
          entity.setRoleName(roleDefDTO.getRoleName());
          this.roleDefDAO.saveOrUpdate(entity);
          message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS, LotteryMessageCode.INSERT_SUCCESS);
        }else{
          message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.ROLE_EXISTS_FAILED);
        }
      }
      log.debug("---> AuthorityServiceImpl saveRole end!!");
    } catch (Exception e) {
      log.error(this.getClass().getName()+".saveRole failed:"+e);
      message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.SAVE_FAILED);
      throw new ServiceException(message);
    }
    model.setMessage(message);
    return model;
  }
  
  public Model deleteRoleDefById(Integer roleId) throws ServiceException {
    log.debug("---> AuthorityServiceImpl deleteRoleDefById start!!");
    Model model = new Model();
    LotteryMessage message = null;
    try {
      List<RoleAuthorityDTO> list = this.queryByProerties(roleId);
      List<SysUserInfoDTO> userList = this.sysUserDAO.getSysUserByUserRoleId(roleId);
      if(CollectionUtils.isEmpty(list) && CollectionUtils.isEmpty(userList)){
        this.roleDefDAO.deleteByRoleId(roleId);
        message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS, LotteryMessageCode.DELETE_SUCCESS);
      }else{
        message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.DELETE_ROLE_FAILED);
      }
      log.debug("---> AuthorityServiceImpl deleteRoleDefById end!!");
    } catch (Exception e) {
      log.error(this.getClass().getName()+".deleteRoleDefById failed:"+e);
      message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.DELETE_FAILED);
      throw new ServiceException(message);
    }
    model.setMessage(message);
    return model;
  }

  /** (non-Javadoc)
   * @see com.gun.service.RoleAuthorityService#queryUserRole(java.lang.Integer)
   */
  @SuppressWarnings("unchecked")
  @Override
  public Model queryUserRole(Integer roleId) throws ServiceException {
    List<RoleAuthorityDTO> results = null;
    Model model = new Model();
    LotteryMessage message = null;
    try {
      log.debug("---> AuthorityServiceImpl queryByProerties start!!");
      results = this.roleAuthorityDAO.queryRoleAuthorityByRole(roleId);
      message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS);
      model.setMessage(message);
      model.setResponse(results);
      log.debug("---> AuthorityServiceImpl queryUserRole end!!");
    } catch (Exception e) {
      log.error(this.getClass().getName()+".queryUserRole failed:"+e);
      message = new LotteryMessage(LotteryMessage.STATUS_FAILURE,LotteryMessageCode.DATA_ACCESS_FAILED);
      throw new ServiceException(e);
    }
    return model;
  }

}
