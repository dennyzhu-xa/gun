package com.gun.server.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.gun.common.entity.pojo.RoleAuthorityDTO;
import com.gun.common.exception.ILotteryException;
import com.gun.common.exception.LotteryException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.LotteryMessage;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.ViewPage;
import com.gun.common.pojo.form.AbstractForm;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.server.controller.utils.SessionManager;
import com.gun.service.LotteryService;
import com.gun.service.RoleAuthorityService;


@SuppressWarnings("rawtypes")
public abstract class LotteryMultiActionController<T, S extends LotteryService> extends MultiActionController {
		private static final Log log = LogFactory.getLog(LotteryMultiActionController.class);
		protected S service;
		private Class<T> commandClass;
		private ViewPage defaultViewPage;
		private Map<String, ViewPage> viewPageMap;
	  @Autowired
	  private RoleAuthorityService roleAuthorityService;

		/**
		 * Constructor for AbstractMultiActionController.
		 */
		public LotteryMultiActionController() {

		}
		
		/**
		 * Constructor for AbstractMultiActionController.
		 * @param delegate handler object. This does not need to implement any particular interface, as everything is done using reflection.
		 */
		public LotteryMultiActionController(Object delegate) {
			super(delegate);
		}
		
		/**
		 * Purpose:初始化頁面
		 * @param request
		 * @param command
		 * @throws LotteryException
		 * @return ModelAndView
		 */
		public abstract ModelAndView init(HttpServletRequest request, T command) throws LotteryException;
		
		public abstract T parse(HttpServletRequest request, T command) throws LotteryException;
		
		public abstract boolean validate(T command) throws LotteryException;
		/** (non-Javadoc)
		 * @see org.springframework.web.servlet.mvc.multiaction.MultiActionController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 */
		@SuppressWarnings("unchecked")
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
				ModelAndView mav = null;
				Model model = new Model();
					
				String actionId = this.getActionId(request);					
				String viewPage = this.getDefaultViewPage(actionId);
				try {
					mav = super.handleRequestInternal(request, response);
				}catch (Throwable e) {
					logMessage(e.getMessage() , (new Date(System.currentTimeMillis())).toString());
					log.error("ErfaMultiActionController is failed, actionId:"+validate(actionId)+", e:"+e, e);
					viewPage = this.getFailedViewPage(actionId);
						
					LotteryMessage message = null;
					if (e instanceof ILotteryException) message = ((ILotteryException)e).getLotteryMessage();
					else message = new LotteryMessage(LotteryMessage.STATUS_FAILURE, LotteryMessageCode.UNKNOWN_SYSTEM_ERROR, new String[]{e.getMessage()});
					model.setMessage(message);
						
					mav = new ModelAndView(viewPage, LotteryConstants.PAGE_PARAM_MODEL, model);
				}
				finally {
					if (mav == null)  mav = new ModelAndView(viewPage, LotteryConstants.PAGE_PARAM_MODEL, model);

					if (mav.getModel() != null) {
						model = (Model)mav.getModel().get(LotteryConstants.PAGE_PARAM_MODEL);
						if (model.getRequest() == null) model.setRequest(request.getAttribute(DEFAULT_COMMAND_NAME));
					}
				}
				return mav;
		}
		
		@SuppressWarnings("unchecked")
    protected void bind(HttpServletRequest request, Object command) throws Exception {
				try {
					super.bind(request, command);
				} catch (Exception e) {
					logMessage(e.getMessage() , (new Date(System.currentTimeMillis())).toString());
					log.error("binding http request parameters is failed:"+e, e);
				}
				if (command == null) command = this.newCommandObject(this.commandClass);
				T result = this.parse(request, (T)command);
				if (result != null) {
					BeanUtils.copyProperties(result, command);
					request.setAttribute(DEFAULT_COMMAND_NAME, command);
				}
				
				String actionId = this.getActionId(request);
				if (command != null) {
					if (command instanceof AbstractForm) {
						((AbstractForm)command).setActionId(actionId);
						((AbstractForm)command).setUserSessionContext(SessionManager.getUserSessionContext(request));
					}
				}
				if ( null != actionId && StringUtils.hasText(actionId) && 
						actionId.equalsIgnoreCase(LotteryConstants.ACTION.INIT.getValue())) return;
				log.debug("start to validate http request parameters...");
				boolean isValid = validate((T)command);
				if (!isValid)  throw new LotteryException(LotteryMessageCode.PAGE_VALIDATE_FAILED);
		}

		public String getActionId(HttpServletRequest request) {
				String actionId = request.getParameter(LotteryConstants.PAGE_PARAM_ACTION_ID);
				actionId = StringUtils.trimWhitespace(validate(actionId));
				if (StringUtils.hasText(actionId)) return null;
				else return actionId;
		}
		/**
		 * @return
		 */
		public Class<T> getCommandClass() {
			return commandClass;
		}

		/**
		 * @param commandClass
		 */
		public void setCommandClass(Class<T> commandClass) {
			this.commandClass = commandClass;
		}

		/**
		 * @return the service
		 */
		public S getService() {
			return service;
		}

		/**
		 * @param service the service to set
		 */
		public void setService(S service) {
			this.service = service;
		}

		/**
		 * @param viewPageMap the viewPageMap to set
		 */
		public void setViewPageMap(Map<String, ViewPage> viewPageMap) {
			this.viewPageMap = viewPageMap;
		}
		
		/**
		 * @param defaultViewPage the defaultViewPage to set
		 */
		public void setDefaultViewPage(ViewPage defaultViewPage) {
			this.defaultViewPage = defaultViewPage;
		}
		
		public String getDefaultViewPage(String actionId) {
			String viewPage = (this.defaultViewPage == null) ? null : this.defaultViewPage.getDefaultPage();
			if (!CollectionUtils.isEmpty(this.viewPageMap) && this.viewPageMap.containsKey(actionId)) {
				ViewPage page = this.viewPageMap.get(actionId);
				if (page != null && StringUtils.hasText(page.getDefaultPage())) viewPage = page.getDefaultPage();
			}
			return viewPage;
		}
		public String getSuccessfulViewPage(String actionId) {
			String viewPage = (this.defaultViewPage == null) ? null : this.defaultViewPage.getSuccessfulPage();
			if (!StringUtils.hasText(viewPage)) viewPage = this.getDefaultViewPage(actionId);
			
			if (!CollectionUtils.isEmpty(this.viewPageMap) && this.viewPageMap.containsKey(actionId)) {
				ViewPage page = this.viewPageMap.get(actionId);
				if (page != null && StringUtils.hasText(page.getSuccessfulPage())) viewPage = page.getSuccessfulPage();
			}
			return viewPage;
		}
		public String getFailedViewPage(String actionId) {
			String viewPage = (this.defaultViewPage == null) ? null : this.defaultViewPage.getFailedPage();
			if (!StringUtils.hasText(viewPage)) viewPage = this.getDefaultViewPage(actionId);
			
			if (!CollectionUtils.isEmpty(this.viewPageMap) && this.viewPageMap.containsKey(actionId)) {
				ViewPage page = this.viewPageMap.get(actionId);
				if (page != null && StringUtils.hasText(page.getFailedPage())) viewPage = page.getFailedPage();
			}
			return viewPage;
		}
		
		/**
		 * Purpose:塞入簽核連結之baseURL
		 * @param command
		 * @param request
		 * @return AbstractForm
		 */
		public AbstractForm setBaseUrl(AbstractForm command, HttpServletRequest request){
			//String baseUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
			String baseUrl = validate(request.getScheme())+"://"+validate(request.getServerName())+":"+validate(request.getServerPort())+validate(request.getContextPath())+"/";
			if(null != command)
				command.setBaseUrl(baseUrl);
			log.debug("baseUrl is >>> " + validate(baseUrl) );
			return command;
		}

	    public String validate(String strParam) {
	    	//log.debug(" validate >>> " + temp );
			return strParam;
	    }
	    public int validate(int strParam) {
	    	return strParam;
	    }
		
	    public String[] validateArray(String[] arrayParam ) {
	    	log.debug(" validateArray >>> " +  Arrays.toString(arrayParam));
			return arrayParam;    	
	    }
	    
	    public void logMessage(String exceptionMessage, String date){	    	
	    }
	    
		/**
		 * @return the defaultViewPage
		 */
		public ViewPage getDefaultViewPage() {
			return defaultViewPage;
		}

		/**
		 * @return the viewPageMap
		 */
		public Map<String, ViewPage> getViewPageMap() {
			return viewPageMap;
		}

    
    /**
     * @return the roleAuthorityService
     */
    public RoleAuthorityService getRoleAuthorityService() {
      return roleAuthorityService;
    }

    
    /**
     * @param roleAuthorityService the roleAuthorityService to set
     */
    public void setRoleAuthorityService(RoleAuthorityService roleAuthorityService) {
      this.roleAuthorityService = roleAuthorityService;
    }
		
    /**
     * 
     * @param request
     * @param roleId
     * @param requestStr
     * @throws LotteryException
     */
    public void requestRole(HttpServletRequest request,Integer roleId,String requestStr) throws LotteryException{
      try {
        List<RoleAuthorityDTO> userRoleButtons =  this.roleAuthorityService.queryByProerties(roleId);
        StringBuilder stringBuilder = new StringBuilder();
        for (RoleAuthorityDTO roleAuthorityDTO : userRoleButtons) {
          stringBuilder.append(roleAuthorityDTO.getFunctionId()+LotteryConstants.MARK_COMMA);
        }
        request.setAttribute(requestStr, stringBuilder.toString());
      } catch (Exception e) {
        log.error(this.getClass().getName()+".requestRole failed:"+e);
        throw new ServiceException(new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.INIT_FAIlED));
      }
    }
}
