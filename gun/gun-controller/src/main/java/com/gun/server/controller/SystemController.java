package com.gun.server.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gun.common.entity.pojo.RoleAuthorityDTO;
import com.gun.common.entity.pojo.RoleDefDTO;
import com.gun.common.entity.pojo.SysUserInfoDTO;
import com.gun.common.exception.LotteryException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.LotteryMessage;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.ParamItem;
import com.gun.common.pojo.UserSessionContext;
import com.gun.common.pojo.form.SysUserInfoForm;
import com.gun.common.pojo.form.SystemForm;
import com.gun.common.utils.EncodeDecodeUtils;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.common.utils.StringUtils;
import com.gun.common.utils.i18NUtil;
import com.gun.server.controller.utils.SessionManager;
import com.gun.service.SystemService;

@SuppressWarnings({"unchecked","rawtypes"})
@Controller
public class SystemController extends LotteryMultiActionController<SystemForm, SystemService>{
	private static final Log log = LogFactory.getLog(SystemController.class);
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(value=LotteryConstants.QUERY_USER_INIT)
	  public ModelAndView init(HttpServletRequest request, @ModelAttribute(SystemForm.FORM_NAME) SystemForm systemForm)
	      throws LotteryException {
	  ModelAndView mav = new ModelAndView();
	  Model model = new Model();
	  LotteryMessage message = null;
    try{
      UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
      if (userSession == null){
        logger.debug("The user is not logged on !!");
        userSession = new UserSessionContext();
      }
      List<ParamItem> roleList = super.getRoleAuthorityService().getRoleAll();
      request.getSession().setAttribute(LotteryConstants.ROLE_LIST, roleList);
      super.requestRole(request, userSession.getRoleId(), LotteryConstants.USER_MANAGEMENT_ROLE);
      message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.INIT_PAGE_SUCCESS);
    } catch (Exception e) {
      log.error(this.getClass().getName()+".init failed!! " + e);
      if (e instanceof ServiceException){
        message = ((ServiceException) e).getLotteryMessage();
      }
    }
    model.setMessage(message);
    mav.addObject(model);
    mav.setViewName(LotteryConstants.JSP_SYS + LotteryConstants.LOTTERY_USER_INFO);
    return mav;
	  }

	@RequestMapping(value=LotteryConstants.QUERY_USER_INFO)
	public ModelAndView queryUserInfo(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(SysUserInfoForm.FORM_NAME) SysUserInfoForm sysUserInfoForm) throws LotteryException {
	  ModelAndView mav = null;
	  Model model = null;
		try {
		  UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
      if (userSession == null){
        logger.debug("The user is not logged on !!");
        userSession = new UserSessionContext();
      }
		  mav = new ModelAndView();
			model = this.systemService.query(sysUserInfoForm);
			super.requestRole(request, userSession.getRoleId(), LotteryConstants.USER_MANAGEMENT_ROLE);
			mav.addObject(model);
      mav.setViewName(LotteryConstants.JSP_SYS+LotteryConstants.LOTTERY_USER_INFO);
		} catch (Exception e) {
		  log.error(this.getClass().getSimpleName()+" queryUserInfo failed!! ");
		  model = new Model();
		  if (e instanceof ServiceException){
		    model.setMessage(((ServiceException) e).getLotteryMessage());
      }
		  mav.addObject(model);
		  mav.setViewName(LotteryConstants.JSP_SYS+LotteryConstants.LOTTERY_USER_INFO);
		}
		return mav;
	}
	
	
	@RequestMapping(value=LotteryConstants.SAVE_SYS_USER)
	@ResponseBody
	public Map<String, Object> doSave(SysUserInfoDTO entity, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Model model = null;
		UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
    if (userSession == null){
      logger.debug("The user is not logged on !!");
      userSession = new UserSessionContext();
    }
		try {
		  boolean checkUser = this.systemService.checkUserByName(entity.getUserId());
			if(!checkUser && entity.getId()==null){
				map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
				map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(LotteryMessageCode.REG_EXISTS_FAILED, userSession.getLocale()));
			}else{
			  if(entity.getId()==null){
			    entity.setPassword(EncodeDecodeUtils.encryptDes(entity.getPassword(),LotteryConstants.LOTTERY));
			    entity.setCreateById(userSession.getUserId());
			    entity.setCreatedByName(userSession.getAliasName());
			  }else{
			    entity.setUpdatedById(userSession.getUserId());
          entity.setUpdatedByName(userSession.getAliasName());
			  }
				model = this.systemService.saveOrUpdate(entity);
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
	
	@RequestMapping(value=LotteryConstants.DELETE_SYS_USER)
	@ResponseBody
	public Map<String, Object> deleteSysUser(HttpServletRequest request, @RequestParam("ids") Integer[] ids) throws Exception {
	  Model model = null;
	  Map<String, Object> map = new HashMap<String, Object>();
	  UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
    if (userSession == null){
      logger.debug("The user is not logged on !!");
      userSession = new UserSessionContext();
    }
	  try {
		  if (Arrays.asList(ids).contains(1)) {
		    map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
		    map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(LotteryMessageCode.Delete_SYSTEM_USER, userSession.getLocale()));
	    } else {
	      model = systemService.deleteByPK(ids);
	      map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), true);
        map.put(SystemForm.ATTRIBUTE.MESSAGE_CODE.getValue(), model.getMessage().getCode());
	    }
    } catch (Exception e) {
      map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
      if (e instanceof ServiceException){
        LotteryMessage message= ((ServiceException) e).getLotteryMessage();
        map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(message.getCode(), userSession.getLocale()) );
      }
      log.error(this.getClass().getSimpleName()+" deleteSysUser failed!! ");
    }
	  return map;
	}
	
  @RequestMapping(value=LotteryConstants.INIT_ROLE)
	public ModelAndView initRole(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(SystemForm.FORM_NAME)SystemForm systemForm) throws LotteryException {
    ModelAndView mav = new ModelAndView();
    Model model = new Model();
		try {
		  UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
	    if (userSession == null){
	      logger.debug("The user is not logged on !!");
	      userSession = new UserSessionContext();
	    }
		  model = super.getRoleAuthorityService().list(systemForm);
		  super.requestRole(request, userSession.getRoleId(), LotteryConstants.ROLE_MANAGEMENT_ROLE);
		  mav.addObject(model);
      mav.setViewName(LotteryConstants.JSP_SYS+LotteryConstants.LOTTERY_ROLE_INFO);
		} catch (Exception e) {
		  log.error(this.getClass().getName()+".initRole failed!! " + e);
      if (e instanceof ServiceException){
          model.setMessage(((ServiceException) e).getLotteryMessage());
      }
      mav.addObject(model);
      mav.setViewName(LotteryConstants.JSP_SYS + LotteryConstants.LOTTERY_ROLE_INFO);
		}
		return mav;
	}
	
	@RequestMapping(value=LotteryConstants.SAVE_ROLE_AUTHORITY)
	@ResponseBody
	public Map<String, Object>  saveRoleAuthority(HttpServletRequest request, HttpServletResponse response,RoleAuthorityDTO roleAuthorityDTO) throws Exception {
	  Map<String, Object> map = new HashMap<String, Object>();
    Model model = null;
    UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
    if (userSession == null){
      logger.debug("The user is not logged on !!");
      userSession = new UserSessionContext();
    }
	  try {
		  Integer roleId = roleAuthorityDTO.getRoleId();
		  model = super.getRoleAuthorityService().deleteByRoleId(roleId);
		  if(model.getMessage().getStatus()==1){
		    String functinIds = roleAuthorityDTO.getFunctionId();
			    if(StringUtils.hasText(functinIds)){
		        String[] idsValue = functinIds.split(LotteryConstants.MARK_COMMA);
		        List<RoleAuthorityDTO> list = new ArrayList<RoleAuthorityDTO>();
		        RoleAuthorityDTO roleAuthority = null;
		        for (int i = 0; i < idsValue.length; i++) {
		          roleAuthority= new RoleAuthorityDTO();
		          roleAuthority.setRoleId(roleId);
		          roleAuthority.setFunctionId(idsValue[i]);
		          list.add(roleAuthority);
		        }
		        model =  super.getRoleAuthorityService().saveOrUpdate(list);
		        map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), true);
		        map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(model.getMessage().getCode(), userSession.getLocale()) );
		      }else{
		    	  map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), true);
			      map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(model.getMessage().getCode(), userSession.getLocale()) );
		      }
		  }
		} catch (Exception e) {
			log.error(this.getClass().getSimpleName()+" saveRoleAuthority failed!! ");
			 map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
	      if (e instanceof ServiceException){
	        LotteryMessage message= ((ServiceException) e).getLotteryMessage();
	        map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(message.getCode(), userSession.getLocale()) );
	      }
		}
	  return map;
	}
	
	@RequestMapping(value=LotteryConstants.SAVE_ROLE_DEF)
	@ResponseBody
  public Map<String, Object> saveRoleDef(HttpServletRequest request, HttpServletResponse response,RoleDefDTO roleDefDTO) throws Exception {
	  Map<String, Object> map = new HashMap<String, Object>();
    Model model = null;
    UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
    if (userSession == null){
      logger.debug("The user is not logged on !!");
      userSession = new UserSessionContext();
    }
	  try {
	    model =  super.getRoleAuthorityService().saveRole(roleDefDTO);
	    if(model.getMessage().getStatus()==1){
	      map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), true);
	      map.put(SystemForm.ATTRIBUTE.MESSAGE_CODE.getValue(), model.getMessage().getCode());
	    }else{
	      map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
	      map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(model.getMessage().getCode(), userSession.getLocale()));
	    }
    } catch (Exception e) {
      map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
      if (e instanceof ServiceException){
        LotteryMessage message= ((ServiceException) e).getLotteryMessage();
        map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(message.getCode(), userSession.getLocale()) );
      }
      log.error(this.getClass().getSimpleName()+" saveRoleDef failed!! ");
    }
	  return map;
  }
	
	 @RequestMapping(value=LotteryConstants.DELETE_ROLE_DEF)
	 @ResponseBody
	  public Map<String, Object> deleteRoleDef(HttpServletRequest request, HttpServletResponse response,Integer roleId) throws Exception {
	   Map<String, Object> map = new HashMap<String, Object>();
	   Model model = null;
	   UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
	    if (userSession == null){
	      logger.debug("The user is not logged on !!");
	      userSession = new UserSessionContext();
	    }
	   try {
	      model = super.getRoleAuthorityService().deleteRoleDefById(roleId);
	      if(model.getMessage().getStatus()==0 && LotteryMessageCode.DELETE_ROLE_FAILED.equals(model.getMessage().getCode())){
	        map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
	        map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(LotteryMessageCode.PRESENCE_REF_FAILED,userSession.getLocale()));
	      }else{
	        map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), true);
	        map.put(SystemForm.ATTRIBUTE.MESSAGE_CODE.getValue(), model.getMessage().getCode());
	      }
	    } catch (Exception e) {
	      map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
	      if (e instanceof ServiceException){
	        LotteryMessage message= ((ServiceException) e).getLotteryMessage();
	        map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(message.getCode(), userSession.getLocale()) );
	      }
	      log.error(this.getClass().getSimpleName()+" deleteRoleDef failed!! ");
	    }
	   return map;
	  }
	 
	  @RequestMapping(value=LotteryConstants.QUERY_USER_ROLE)
	  @ResponseBody
	  public Map<String, Object> queryUserRole(HttpServletRequest request,Integer roleId) throws Exception {
	    Map<String, Object> map = new HashMap<String, Object>();
	    Model model = null;
	    UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
	    if (userSession == null){
	      logger.debug("The user is not logged on !!");
	      userSession = new UserSessionContext();
	    }
	    try {
	      model =  super.getRoleAuthorityService().queryUserRole(roleId);
	      List<RoleAuthorityDTO> results = (List<RoleAuthorityDTO>) model.getResponse();
	      map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), true);
	      map.put(SystemForm.ATTRIBUTE.RESULT.getValue(), results);
	    } catch (Exception e) {
	      map.put(SystemForm.ATTRIBUTE.SUCCESS.getValue(), false);
	      if (e instanceof ServiceException){
	        LotteryMessage message= ((ServiceException) e).getLotteryMessage();
	        map.put(SystemForm.ATTRIBUTE.MESSAGE.getValue(), i18NUtil.getName(message.getCode(), userSession.getLocale()) );
	      }
	      log.error(this.getClass().getSimpleName()+" getUserRole failed!! ");
	    }
	    return map;
	  }

	@Override
	public SystemForm parse(HttpServletRequest request, SystemForm command)
			throws LotteryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validate(SystemForm command) throws LotteryException {
		// TODO Auto-generated method stub
		return false;
	}
}
