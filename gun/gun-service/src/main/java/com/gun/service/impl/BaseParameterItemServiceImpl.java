package com.gun.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cyber.lottery.dao.BaseParameterItemDAO;
import com.gun.common.entity.BaseParameterItemDef;
import com.gun.common.entity.BaseParameterItemDefId;
import com.gun.common.entity.pojo.BaseParameterItemDefDTO;
import com.gun.common.entity.pojo.BaseParameterItemDefIdDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.LotteryMessage;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;
import com.gun.common.pojo.UserSessionContext;
import com.gun.common.pojo.form.BaseParameterItemForm;
import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.common.utils.SimpleDtoDmoTransformer;
import com.gun.common.utils.StringUtils;
import com.gun.service.BaseParameterItemService;


/**
 * @author felixli
 *
 */
@Service
public class BaseParameterItemServiceImpl implements BaseParameterItemService {
  /**
   * 日志记录物件
   */
  private static final Log log = LogFactory.getLog(BaseParameterItemServiceImpl.class);
  
  private SimpleDtoDmoTransformer transformer = new SimpleDtoDmoTransformer();
  
  @Autowired
  private BaseParameterItemDAO baseParameterItemDAO;

  /** (non-Javadoc)
   * @see com.gun.service.LotteryService#init(java.lang.Object)
   */
  public Model init(Object request) throws ServiceException {
    Model model = null;
    LotteryMessage message = null;
    try {
      model = new Model();
      List<ParamItem> effectiveDateList = new ArrayList<ParamItem>();
      List<ParamItem> baseParameterTypeList = new ArrayList<ParamItem>();
      BaseParameterItemForm command = (BaseParameterItemForm) request;
      effectiveDateList = this.baseParameterItemDAO.getEffectiveDate();
      command.setEffectiveDateList(effectiveDateList);
      baseParameterTypeList = baseParameterItemDAO.getBaseParameterType();
      command.setBaseParameterTypeList(baseParameterTypeList);
      message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.INIT_SUCCESS);
      model.setMessage(message);
    } catch (Exception e) {
      log.error(this.getClass().getName()+".init failed:"+e);
      message = new LotteryMessage(LotteryMessage.STATUS_FAILURE,LotteryMessageCode.INIT_FAIlED);
      throw new ServiceException(message);
    }
    return model;
  }
  
  /** (non-Javadoc)
   * @see com.gun.service.BaseParameterItemService#query(com.gun.common.pojo.form.BaseParameterItemForm)
   */
  public Model query(BaseParameterItemForm form) throws ServiceException {
    Model model = null;
    LotteryMessage message = null;
    List<BaseParameterItemDefDTO> parameterList = new ArrayList<BaseParameterItemDefDTO>();
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
      parameterList = this.baseParameterItemDAO.queryParameterByEffectiveDate(pageInfo,form.getQueryBptdCode(),form.getQueryEffectiveDate());
      if (CollectionUtils.isEmpty(parameterList)&& form.getPageNo()!=null && form.getPageNo()>=1) {
        form.setPageNo(form.getPageNo()-1);
        pageInfo.setPage(form.getPageNo());
        parameterList = this.baseParameterItemDAO.queryParameterByEffectiveDate(pageInfo,form.getQueryBptdCode(),form.getQueryEffectiveDate());
      }
      form.setResults(parameterList);
      if (CollectionUtils.isEmpty(parameterList)) {
        message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.DATA_NOT_FOUND);
      }else{
        message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.QUERY_SUCCESS);
      }
      if(StringUtils.hasText(form.getMessageCode())){
        message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,form.getMessageCode());
      }
      model.setPageInfo(pageInfo);
      form.setQueryFlag(LotteryConstants.YES);
    } catch (Exception e) {
      log.error(this.getClass().getName()+"query failed!! ", e);
      message = new LotteryMessage(LotteryMessage.STATUS_FAILURE,LotteryMessageCode.QUERY_FAIlED);
      throw new ServiceException(message);
    }
    model.setMessage(message);
    return model;
  }

  /** (non-Javadoc)
   * @see com.gun.service.BaseParameterItemService#getBaseParameterType()
   */
  public Model initEdit(BaseParameterItemForm command) throws ServiceException {
    Model model = null;
    LotteryMessage message = null;
//    List<ParamItem> baseParameterTypeList = new ArrayList<ParamItem>();
    try {
      model = new Model();
//      baseParameterTypeList = baseParameterItemDAO.getBaseParameterType();
//      command.setBaseParameterTypeList(baseParameterTypeList);
      message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.INIT_PAGE_SUCCESS);
    } catch (Exception e) {
      log.error(this.getClass().getName()+"initEdit failed!! ", e);
      message = new LotteryMessage(LotteryMessage.STATUS_FAILURE,LotteryMessageCode.INIT_PAGE_FAILED);
      throw new ServiceException(message);
    }
    model.setMessage(message);
    return model;
  }

  /** (non-Javadoc)
   * @see com.gun.service.BaseParameterItemService#getBaseParameterItemDefDTOs(java.lang.String, java.util.Date, java.lang.String[])
   */
  public List<BaseParameterItemDefDTO> getBaseParameterItemDefDTOs(BaseParameterItemForm command) throws ServiceException {
    List<BaseParameterItemDefDTO> list = null;
    try {
      if(LotteryConstants.BUTTON_ADD.equals(command.getEditFlag())){
        list = this.baseParameterItemDAO.getBaseParameterItemDefDTOs(command.getBptdCode(), null,true);
      }else if("V".equals(command.getEditFlag()) && LotteryConstants.YES.equals(command.getApprovedFlag())){
        list = this.baseParameterItemDAO.getBaseParameterItemDefDTOs(command.getBptdCode(), DateTimeUtils.toDate(command.getEffectiveDate()),false,LotteryConstants.YES);
      }else{
        list = this.baseParameterItemDAO.getBaseParameterItemDefDTOs(command.getBptdCode(), DateTimeUtils.toDate(command.getEffectiveDate()),false);
      }
    } catch (Exception e) {
      log.error(this.getClass().getName()+"getBaseParameterItemDefDTOs failed!! ", e);
    }
    return list;
  }

  /** (non-Javadoc)
   * @see com.gun.service.BaseParameterItemService#save(com.gun.common.pojo.form.BaseParameterItemForm)
   */
  public Model save(BaseParameterItemForm command) throws ServiceException {
    Model model = null;
    LotteryMessage message = null;
    List<BaseParameterItemDef> saveList = new ArrayList<BaseParameterItemDef>();
    BaseParameterItemDefId baseParameterItemDefId = null;
    BaseParameterItemDef baseParameterItemDef= null;
    try {
      model = new Model();
      UserSessionContext user= (UserSessionContext) command.getUserSessionContext();
      String bptdCode = command.getBptdCode();
      String effectiveDate = command.getEffectiveDate();
      Date editDate = DateTimeUtils.getCurrentTimestamp();
      if(!"A".equals(command.getEditFlag())){
        List<BaseParameterItemDefDTO>  data = command.getSaveData();
        for (int i = 0; i < data.size(); i++) {
          baseParameterItemDefId = new BaseParameterItemDefId();
          baseParameterItemDef = new BaseParameterItemDef();
          baseParameterItemDefId.setBpidId(bptdCode+LotteryConstants.MARK_UNDERLINE+(i+1));
          baseParameterItemDefId.setBptdCode(bptdCode);
          baseParameterItemDefId.setEffectiveDate(effectiveDate);
          baseParameterItemDef.setId(baseParameterItemDefId);
          baseParameterItemDef.setItemName(data.get(i).getItemName());
          baseParameterItemDef.setItemValue(data.get(i).getItemValue());
          baseParameterItemDef.setItemDesc(data.get(i).getItemDesc());
          baseParameterItemDef.setItemOrder(data.get(i).getItemOrder());
          baseParameterItemDef.setApprovedFlag(LotteryConstants.NO);
          if(data.get(i).getWinningAmount()!=null){
            baseParameterItemDef.setNumberField1(new BigDecimal(data.get(i).getWinningAmount()));
          }
          if(data.get(i).getNumberOfWinners()!=null){
            baseParameterItemDef.setNumberField2(new BigDecimal(data.get(i).getNumberOfWinners()));
          }
          if(data.get(i).getCategoryImage().getBytes().length>0){
            baseParameterItemDef.setImgFlag(LotteryConstants.YES);
            baseParameterItemDef.setImgField1(data.get(i).getCategoryImage().getBytes());
          }else{
            if(command.getMap()!=null){
              byte[] imgByte = command.getMap().get(data.get(i).getItemValue());
              if(imgByte!=null && imgByte.length>0){
                baseParameterItemDef.setImgFlag(LotteryConstants.YES);
                baseParameterItemDef.setImgField1(imgByte);
              }
            }
          }
          baseParameterItemDef.setUpdatedById(user.getUserId());
          baseParameterItemDef.setUpdatedByName(user.getAliasName());
          baseParameterItemDef.setUpdatedDate(editDate);
          saveList.add(baseParameterItemDef);
        }
        if("E".equals(command.getEditFlag())){
          this.baseParameterItemDAO.deleteBaseParameterItemDef(bptdCode, effectiveDate);
          message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.UPDATE_SUCCESS);
          command.setMessageCode(LotteryMessageCode.UPDATE_SUCCESS);
        }else{
          message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.INSERT_SUCCESS);
          command.setMessageCode(LotteryMessageCode.INSERT_SUCCESS);
        }
        this.baseParameterItemDAO.saveOrUpdateAll(saveList);
      }else{
        this.baseParameterItemDAO.approvalBaseParameterItemDef(bptdCode, effectiveDate);
        message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.APPROVAL_SUCCESS);
        command.setMessageCode(LotteryMessageCode.APPROVAL_SUCCESS);
      }
      if(LotteryConstants.YES.equals(command.getQueryFlag())){
       model = this.query(command);
      }
    } catch (Exception e) {
      log.error(this.getClass().getName()+"save failed!! ", e);
      message = new LotteryMessage(LotteryMessage.STATUS_FAILURE,LotteryMessageCode.SAVE_FAILED);
      throw new ServiceException(message);
    }
    model.setMessage(message);
    return model;
  }

  /** (non-Javadoc)
   * @see com.gun.service.BaseParameterItemService#checkBaseParameterItemDef(java.lang.String, java.lang.String)
   */
  public boolean checkBaseParameterItemDef(String bptdCode, String effectiveDate) throws ServiceException {
    boolean result = false;
    try {
      List<BaseParameterItemDefDTO> listData = this.baseParameterItemDAO.getBaseParameterItemDefDTOs(bptdCode, DateTimeUtils.toDate(effectiveDate), false);
      if(!CollectionUtils.isEmpty(listData)){
        result = true;
      }
    } catch (Exception e) {
      log.error(this.getClass().getName()+"checkBaseParameterItemDef failed!! ", e);
    }
    return result;
  }

  /** (non-Javadoc)
   * @see com.gun.service.BaseParameterItemService#loadCategoryImg(com.gun.common.entity.pojo.BaseParameterItemDefIdDTO)
   */
  public byte[] loadCategoryImg(BaseParameterItemDefIdDTO baseParameterItemDefIdDTO) throws DataAccessException {
    byte[] result = null;
    try {
      BaseParameterItemDefId id = (BaseParameterItemDefId) transformer.transform(baseParameterItemDefIdDTO, new BaseParameterItemDefId());
      BaseParameterItemDef entity = this.baseParameterItemDAO.findById(BaseParameterItemDef.class, id);
      if(entity!=null){
        result = entity.getImgField1();
      }
    } catch (Exception e) {
      log.error(this.getClass().getName()+"loadCategoryImg failed!! ", e);
    }
    return result;
  }

  /** (non-Javadoc)
   * @see com.gun.service.BaseParameterItemService#delete(com.gun.common.pojo.form.BaseParameterItemForm)
   */
  @Override
  public Model delete(BaseParameterItemForm command) throws ServiceException {
    Model model = null;
    LotteryMessage message = null;
    try {
      model = new Model();
      this.baseParameterItemDAO.deleteBaseParameterItemDef(command.getBptdCode(), command.getEffectiveDate());
      this.baseParameterItemDAO.flush();
      message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.DELETE_SUCCESS);
      command.setMessageCode(LotteryMessageCode.DELETE_SUCCESS);
      model = this.query(command);
      if(CollectionUtils.isEmpty(command.getResults())){
        command.setQueryEffectiveDate(LotteryConstants.STRING_EMPTY);
      }
      model.setMessage(message);
    } catch (Exception e) {
      log.error(this.getClass().getName()+".delete failed:"+e);
      message = new LotteryMessage(LotteryMessage.STATUS_FAILURE,LotteryMessageCode.DELETE_FAILED);
      throw new ServiceException(message);
    }
    return model;
  }

}
