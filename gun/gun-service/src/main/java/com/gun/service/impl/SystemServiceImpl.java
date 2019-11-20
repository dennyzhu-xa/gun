package com.gun.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cyber.lottery.dao.SysUserDAO;
import com.cyber.lottery.dao.SystemDAO;
import com.gun.common.entity.SysUserInfo;
import com.gun.common.entity.pojo.SysUserInfoDTO;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.LotteryMessage;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.form.SysUserInfoForm;
import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.common.utils.SimpleDtoDmoTransformer;
import com.gun.common.utils.SqlSyntaxFactory;
import com.gun.common.utils.StringUtils;
import com.gun.service.SystemService;

/**
 * 
 * @author felixli
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class SystemServiceImpl implements SystemService {

	private static final Log log = LogFactory.getLog(SystemServiceImpl.class);
	
	private SimpleDtoDmoTransformer transformer = new SimpleDtoDmoTransformer();
	
	@Autowired
	private SystemDAO systemDAO;
	
	@Autowired
	private SysUserDAO sysUserDAO;
	
	/**
	 * (non-Javadoc)
	 * @see com.gun.service.LotteryService#init(java.lang.Object)
	 */
	public Model init(Object request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @see com.gun.service.SystemService#query(com.gun.common.pojo.form.SystemForm)
	 */
  public Model query(SysUserInfoForm form) throws ServiceException {
		Model model = null;
		LotteryMessage message = null;
		List<SysUserInfoDTO> userList = new ArrayList<SysUserInfoDTO>();
		try {
			model = new Model();
			PageInfo pageInfo = new PageInfo();
		//分頁元件
      if(StringUtils.hasText(form.getPageSet())) {
        pageInfo.setMaxIndex(Integer.parseInt(form.getPageSet()));
        pageInfo.setSize(Integer.parseInt(form.getPageSet()));
      }
      if(form.getPageNo()!=null) {
        pageInfo.setPage(form.getPageNo());
      }
			userList = this.systemDAO.list(pageInfo,form);
			if (CollectionUtils.isEmpty(userList)&& form.getPageNo()!=null && form.getPageNo()>=1) {
			  form.setPageNo(form.getPageNo()-1);
			  pageInfo.setPage(form.getPageNo());
			  userList = this.systemDAO.list(pageInfo,form);
			}
			form.setResults(userList);
			if (CollectionUtils.isEmpty(userList)) {
				message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.DATA_NOT_FOUND);
			}else{
			  message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.QUERY_SUCCESS);
			}
			if(StringUtils.hasText(form.getMessageCode())){
			  message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,form.getMessageCode());
			}
			model.setPageInfo(pageInfo);
		} catch (Exception e) {
			log.error(this.getClass().getName()+"query failed!! ", e);
			message = new LotteryMessage(LotteryMessage.STATUS_FAILURE,LotteryMessageCode.QUERY_FAIlED);
			throw new ServiceException(message);
		}
		model.setMessage(message);
		return model;
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.gun.service.SystemService#checkUserByName(java.lang.String)
	 */
	public boolean checkUserByName(String userId) throws ServiceException {
	  SysUserInfoDTO sysUserDTO =  this.sysUserDAO.getSysUserByUserName(userId);
		if(sysUserDTO!=null){
			return false;
		}
		return true;
	}
	
	@Override
	public Model saveOrUpdate(SysUserInfoDTO sysUserDTO) throws ServiceException {
	  Model model = new Model();
		SysUserInfo entity = null;
		LotteryMessage message = null;
		try {
			//新增
			if(sysUserDTO.getId()==null){
				entity = new SysUserInfo();
				entity = (SysUserInfo) transformer.transform(sysUserDTO, new SysUserInfo());
				entity.setCreatedDate(DateTimeUtils.getCurrentTimestamp());
				this.sysUserDAO.saveOrUpdate(entity);
        message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS, LotteryMessageCode.INSERT_SUCCESS);
			//修改
			}else{
				entity = this.sysUserDAO.findById(SysUserInfo.class, sysUserDTO.getId().intValue());
				if(entity!=null){
	        entity.setUserName(sysUserDTO.getUserName());
	        entity.setEmail(sysUserDTO.getEmail());
	        entity.setUserId(sysUserDTO.getUserId());
	        entity.setRoleId(sysUserDTO.getRoleId());
	        entity.setTelephone(sysUserDTO.getTelephone());
	        entity.setUpdatedById(sysUserDTO.getUpdatedById());
	        entity.setUpdatedByName(sysUserDTO.getUpdatedByName());
	        entity.setUpdatedDate(DateTimeUtils.getCurrentTimestamp());
	        this.sysUserDAO.saveOrUpdate(entity);
	        message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS, LotteryMessageCode.UPDATE_SUCCESS);
	      }else{
	        message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.USER_NOT_EXIST);
	      }
			}
		} catch (Exception e) {
			log.error(this.getClass().getName()+"saveOrUpdate failed!! ", e);
			message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.SAVE_FAILED);
			throw new ServiceException(message);
		}
		model.setMessage(message);
    return model;
	}
	
	@Override
	public Model deleteByPK(Serializable... id) throws ServiceException {
	  Model model = new Model();
    LotteryMessage message = null;
		try {
			for (Integer i = 0; i < id.length; i++) {
			  SysUserInfo entity = this.sysUserDAO.findById(SysUserInfo.class, id[i]);
				if(entity!=null){
					this.sysUserDAO.delete(entity);
				}
			}
			message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS, LotteryMessageCode.DELETE_SUCCESS);
		} catch (Exception e) {
			log.error(this.getClass().getName()+"deleteByPK failed!! ", e);
			message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.DELETE_FAILED);
			throw new ServiceException(message);
		}
		model.setMessage(message);
		return model;
	}

	@Override
	public Long getcount() throws ServiceException {
		Long result=0L;
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userName", null);
			result = (long) this.sysUserDAO.getTotal(SqlSyntaxFactory.GET_USER_BY_NAME, params);
		} catch (Exception e) {
			log.error(this.getClass().getName()+"getcount failed!! ", e);
			throw new ServiceException();
		}
		
		return result;
	}

}
