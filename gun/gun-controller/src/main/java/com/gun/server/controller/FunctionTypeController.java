package com.gun.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gun.common.entity.pojo.FunctionTypeDTO;
import com.gun.common.exception.LotteryException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.LotteryMessage;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.ParamItem;
import com.gun.common.pojo.UserSessionContext;
import com.gun.common.pojo.form.FunctionTypeForm;
import com.gun.common.pojo.form.SystemForm;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.common.utils.StringUtils;
import com.gun.common.utils.i18NUtil;
import com.gun.server.controller.utils.SessionManager;
import com.gun.service.FunctionTypeService;

@Controller
public class FunctionTypeController extends LotteryMultiActionController<FunctionTypeForm, FunctionTypeService>{
    private static final Log log = LogFactory.getLog(FunctionTypeController.class);
    
    @Autowired
    private FunctionTypeService functionTypeService;
  /** (non-Javadoc)
   * @see com.gun.server.controller.LotteryMultiActionController#init(javax.servlet.http.HttpServletRequest, java.lang.Object)
   */
  @RequestMapping(value=LotteryConstants.INIT_RESOURCE)
  public ModelAndView init(HttpServletRequest request, @ModelAttribute(FunctionTypeForm.FORM_NAME)FunctionTypeForm functionTypeForm) throws LotteryException {
    ModelAndView mav = new ModelAndView();
    Model model = null;
    try{
      UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
      if (userSession == null){
        logger.debug("The user is not logged on !!");
        userSession = new UserSessionContext();
      }
      model = functionTypeService.list(functionTypeForm);
      super.requestRole(request, userSession.getRoleId(), LotteryConstants.RESOURCE_MANAGEMENT_ROLE);
      mav.addObject(model);
      mav.setViewName(LotteryConstants.JSP_SYS + LotteryConstants.LOTTERY_RESOURCE_LIST);
    } catch (Exception e) {
      log.error(this.getClass().getName()+".init failed!! " + e);
      model = new Model();
      if (e instanceof ServiceException){
          model.setMessage(((ServiceException) e).getLotteryMessage());
      }
      mav.addObject(model);
      mav.setViewName(LotteryConstants.JSP_SYS + LotteryConstants.LOTTERY_RESOURCE_LIST);
    }
    return mav;
  }
  
  @RequestMapping(value = LotteryConstants.QUERY_PARENT_FUNCTION_LIST)
  @ResponseBody
  public List<ParamItem> queryParentFunctionList() throws LotteryException {
    List<ParamItem> ParentFunctionList = new ArrayList<ParamItem>();
    try {
      ParentFunctionList = this.functionTypeService.queryParentFunctionList();
    } catch (Exception e) {
      log.error(this.getClass().getName()+".init failed!! " + e);
    }
     return ParentFunctionList;
  }
  
  @RequestMapping(value = LotteryConstants.SAVE_FUNCTION)
  @ResponseBody
  public Map<String, Object> saveFunctionType(FunctionTypeDTO functionTypeDTO, HttpServletRequest request) throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    Model model = null;
    UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
    if (userSession == null){
      logger.debug("The user is not logged on !!");
      userSession = new UserSessionContext();
    }
    try {
      boolean checkFun = this.functionTypeService.checkFunction(functionTypeDTO.getFunctionCode());
      if(!checkFun && !StringUtils.hasText(functionTypeDTO.getFunctionId())){
        map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
        map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(LotteryMessageCode.FUNCTION_CODE_EXISTS, userSession.getLocale()));
      }else{
        if(StringUtils.hasText(functionTypeDTO.getFunctionId())){
          functionTypeDTO.setUpdatedById(userSession.getUserId());
          functionTypeDTO.setUpdatedByName(userSession.getAliasName());
        }else{
          functionTypeDTO.setCreatedById(userSession.getUserId());
          functionTypeDTO.setCreatedByName(userSession.getAliasName());
        }
        model =  this.functionTypeService.saveOrUpdate(functionTypeDTO);
        if(model.getMessage().getStatus()==1){
          map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), true);
          map.put(SystemForm.ATTRIBUTE.MESSAGE_CODE.getValue(), model.getMessage().getCode());
        }else{
          map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
          map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(model.getMessage().getCode(), userSession.getLocale()));
        }
      }
    } catch (Exception e) {
      map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
      if (e instanceof ServiceException){
        LotteryMessage message= ((ServiceException) e).getLotteryMessage();
        map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(message.getCode(), userSession.getLocale()) );
      }
      log.error(this.getClass().getSimpleName()+" doSave failed!! ");
    }
     return map;
  }
  
  @RequestMapping(value=LotteryConstants.DELETE_FUNCTION)
  @ResponseBody
  public Map<String, Object> deleteFunctionType(@RequestParam("ids") String[] ids, HttpServletRequest request) throws Exception{
    boolean parentFlag = false;
    Model model = null;
    Map<String, Object> map = new HashMap<String, Object>();
    UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
    if (userSession == null){
      logger.debug("The user is not logged on !!");
      userSession = new UserSessionContext();
    }
    try {
      for (int i = 0; i < ids.length; i++) {
        List<FunctionTypeDTO> funList = this.functionTypeService.queryFunctionByParentId(ids[i]);
            if(!CollectionUtils.isEmpty(funList)){
              parentFlag = true;
              break;
        }
      }
      if (parentFlag) {
        map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
        map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(LotteryMessageCode.PRESENCE_REF_FAILED,userSession.getLocale()));
      } else {
        model = functionTypeService.deleteByPK(ids);
        map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), true);
        map.put(SystemForm.ATTRIBUTE.MESSAGE_CODE.getValue(), model.getMessage().getCode());
      }
    } catch (Exception e) {
      map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
      if (e instanceof ServiceException){
        LotteryMessage message= ((ServiceException) e).getLotteryMessage();
        map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(message.getCode(), userSession.getLocale()) );
      }
      log.error(this.getClass().getSimpleName()+" deleteFunctionType failed!! ");
    }
    return map;
  }

  /** (non-Javadoc)
   * @see com.gun.server.controller.LotteryMultiActionController#parse(javax.servlet.http.HttpServletRequest, java.lang.Object)
   */
  @Override
  public FunctionTypeForm parse(HttpServletRequest request, FunctionTypeForm command) throws LotteryException {
    // TODO Auto-generated method stub
    return null;
  }

  /** (non-Javadoc)
   * @see com.gun.server.controller.LotteryMultiActionController#validate(java.lang.Object)
   */
  @Override
  public boolean validate(FunctionTypeForm command) throws LotteryException {
    // TODO Auto-generated method stub
    return false;
  }

}
