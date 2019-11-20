package com.gun.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cyber.lottery.dao.FunctionDefDAO;
import com.gun.common.entity.FunctionType;
import com.gun.common.entity.pojo.FunctionTypeDTO;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.LotteryMessage;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;
import com.gun.common.pojo.form.FunctionTypeForm;
import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.common.utils.SimpleDtoDmoTransformer;
import com.gun.common.utils.StringUtils;
import com.gun.service.FunctionTypeService;


/**
 * @author felixli
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class FunctionTypeServiceImpl implements FunctionTypeService {
  
  private static final Log log = LogFactory.getLog(FunctionTypeServiceImpl.class);
  
  private SimpleDtoDmoTransformer transformer = new SimpleDtoDmoTransformer();
  
  @Autowired
  private FunctionDefDAO FunctionDefDAO;

  /** (non-Javadoc)
   * @see com.gun.service.LotteryService#init(java.lang.Object)
   */
  public Model init(Object request) throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  /** (non-Javadoc)
   * @see com.gun.service.FunctionTypeService#list()
   */
  public Model list(FunctionTypeForm functionTypeForm) throws ServiceException{
    Model model = null;
    LotteryMessage message = null;
    List<FunctionTypeDTO> functionList = new ArrayList<FunctionTypeDTO>();
    try {
      model = new Model();
      PageInfo pageInfo = new PageInfo();
    //分頁元件
      if(StringUtils.hasText(functionTypeForm.getPageSet())) {
        pageInfo.setMaxIndex(Integer.parseInt(functionTypeForm.getPageSet()));
        pageInfo.setSize(Integer.parseInt(functionTypeForm.getPageSet()));
      }
      if(functionTypeForm.getPageNo()!=null) {
        pageInfo.setPage(functionTypeForm.getPageNo());
      }
      functionList = this.FunctionDefDAO.list(pageInfo);
      if (CollectionUtils.isEmpty(functionList)&& functionTypeForm.getPageNo()!=null && functionTypeForm.getPageNo()>=1) {
        functionTypeForm.setPageNo(functionTypeForm.getPageNo()-1);
        pageInfo.setPage(functionTypeForm.getPageNo());
        functionList = this.FunctionDefDAO.list(pageInfo);
      }
      if(CollectionUtils.isEmpty(functionList)){
        functionTypeForm.setNotDataCode(LotteryMessageCode.DATA_NOT_FOUND);
      }
      functionTypeForm.setResults(functionList);
      message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.INIT_SUCCESS);
      if(StringUtils.hasText(functionTypeForm.getMessageCode())){
        message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,functionTypeForm.getMessageCode());
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

  /** (non-Javadoc)
   * @see com.gun.service.FunctionTypeService#queryParentFunctionList()
   */
  @Override
  public List<ParamItem> queryParentFunctionList() throws ServiceException {
    List<ParamItem> results =null;
   try {
     results = this.FunctionDefDAO.queryParentFunctionList();
    } catch (Exception e) {
      log.error(this.getClass().getName()+"queryParentFunctionList failed!! ", e);
      throw new ServiceException(e);
    }
   return results;
  }

  /** (non-Javadoc)
   * @see com.gun.service.FunctionTypeService#saveOrUpdate(com.gun.common.entity.pojo.FunctionTypeDTO)
   */
  @Override
  public Model saveOrUpdate(FunctionTypeDTO functionTypeDTO) throws ServiceException {
    Model model = new Model();
    FunctionType entity = null;
    LotteryMessage message = null;
    try {
      //新增
      if(!StringUtils.hasText(functionTypeDTO.getFunctionId())){
        entity = (FunctionType) transformer.transform(functionTypeDTO, new FunctionType());
        entity.setFunctionId(functionTypeDTO.getFunctionCode());
        if(!StringUtils.hasText(functionTypeDTO.getParentFunctionId())){
          entity.setParentFunctionId(functionTypeDTO.getFunctionCode());
        }
        entity.setCreatedDate(DateTimeUtils.toString(DateTimeUtils.getCurrentDate(), DateTimeUtils.DT_FMT_YYYYMMDD_DASH));
        this.FunctionDefDAO.saveOrUpdate(entity);
        message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS, LotteryMessageCode.INSERT_SUCCESS);
        //修改  
      }else{
        entity = this.FunctionDefDAO.findById(FunctionType.class, functionTypeDTO.getFunctionId());
        if(entity!=null){
          entity.setFunctionName(functionTypeDTO.getFunctionName());
          entity.setFunctionOrder(functionTypeDTO.getFunctionOrder());
          entity.setFunctionUrl(functionTypeDTO.getFunctionUrl());
          entity.setButtons(functionTypeDTO.getButtons());
          if(StringUtils.hasText(functionTypeDTO.getParentFunctionId())){
            entity.setParentFunctionId(functionTypeDTO.getParentFunctionId());
          }
          entity.setFunctionDescription(functionTypeDTO.getFunctionDescription());
          entity.setUpdatedById(functionTypeDTO.getUpdatedById());
          entity.setUpdatedByName(functionTypeDTO.getUpdatedByName());
          entity.setUpdatedDate(DateTimeUtils.toString(DateTimeUtils.getCurrentDate(), DateTimeUtils.DT_FMT_YYYYMMDD_DASH));
          this.FunctionDefDAO.saveOrUpdate(entity);
          message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS, LotteryMessageCode.UPDATE_SUCCESS);
        }else{
          message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.FUNCTION_NOT_EXIST);
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

  /** (non-Javadoc)
   * @see com.gun.service.FunctionTypeService#checkFunction(java.lang.String)
   */
  public boolean checkFunction(String functionCode) throws ServiceException {
    boolean result = false;
    try {
      FunctionType fun = this.FunctionDefDAO.findById(FunctionType.class, functionCode);
      if(fun==null){
        result = true;
      }
    } catch (Exception e) {
      log.error(this.getClass().getName()+"checkFunction failed!! ", e);
      throw new ServiceException(e);
    }
    return result;
  }
  
  /** (non-Javadoc)
   * @see com.gun.service.FunctionTypeService#queryFunctionByParentId(java.lang.String)
   */
  public List<FunctionTypeDTO> queryFunctionByParentId(String parentFunctionId) throws ServiceException {
    List<FunctionTypeDTO> result = null;
    try {
      result = this.FunctionDefDAO.queryFunctionsByParentId(parentFunctionId);
    } catch (Exception e) {
      log.error(this.getClass().getName()+"checkFunction failed!! ", e);
    }
    return result;
  }

  /** (non-Javadoc)
   * @see com.gun.service.FunctionTypeService#deleteByPK(java.io.Serializable[])
   */
  public Model deleteByPK(Serializable... id) throws ServiceException {
    Model model = new Model();
    LotteryMessage message = null;
    try {
      for (Integer i = 0; i < id.length; i++) {
        FunctionType entity = this.FunctionDefDAO.findById(FunctionType.class, id[i]);
        if(entity!=null){
          this.FunctionDefDAO.delete(entity);
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

}
