package com.gun.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gun.common.entity.pojo.LotteryReportDTO;
import com.gun.common.entity.pojo.NearbyStoresDTO;
import com.gun.common.exception.LotteryException;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.JasperReportCriteriaDTO;
import com.gun.common.pojo.LotteryMessage;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.ReportExporter;
import com.gun.common.pojo.UserSessionContext;
import com.gun.common.pojo.form.LotteryReportForm;
import com.gun.common.utils.LotteryConstants;
import com.gun.server.controller.utils.SessionManager;
import com.gun.service.LotteryReportService;

@SuppressWarnings("rawtypes")
@Controller
public class LotteryReportController extends LotteryMultiActionController<LotteryReportForm, LotteryReportService>{
 
  private static final Log log = LogFactory.getLog(LotteryReportController.class);
 
  @Autowired
  private LotteryReportService lotteryReportService;
  
  /** (non-Javadoc)
   * @see com.gun.server.controller.LotteryMultiActionController#init(javax.servlet.http.HttpServletRequest, java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value=LotteryConstants.INIT_STOCK_BALANCE)
  public ModelAndView init(HttpServletRequest request,@ModelAttribute(LotteryReportForm.FORM_NAME) LotteryReportForm lotteryReportForm) throws LotteryException {
    ModelAndView mav = new ModelAndView();
    Model model = new Model();
    LotteryMessage message = null;
    try{
      UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
      if (userSession == null){
        logger.debug("The user is not logged on !!");
        userSession = new UserSessionContext();
      }
      model = lotteryReportService.init(lotteryReportForm);
      request.getSession().setAttribute(LotteryReportForm.NUMBER_OF_PERIODS_LIST, lotteryReportForm.getNumberOfPeriodsList());
      super.requestRole(request, userSession.getRoleId(), LotteryConstants.STOCK_BALANCE_ROLE);
    } catch (Exception e) {
      log.error(this.getClass().getName()+".init failed!! " + e);
      if (e instanceof ServiceException){
        message = ((ServiceException) e).getLotteryMessage();
      }
      model.setMessage(message);
    }
    mav.addObject(model);
    mav.setViewName(LotteryConstants.JSP_LRS + LotteryConstants.STOCK_BALANCE_REPORT);
    return mav;
  }
  
  @RequestMapping(value=LotteryConstants.QUERY_STOCK_BALANCE)
  public ModelAndView queryStockBalance(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(LotteryReportForm.FORM_NAME) LotteryReportForm lotteryReportForm) throws LotteryException {
    ModelAndView mav = null;
    Model model = null;
    try {
      UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
      if (userSession == null){
        logger.debug("The user is not logged on !!");
        userSession = new UserSessionContext();
      }
      mav = new ModelAndView();
      model = this.lotteryReportService.queryStockBalance(lotteryReportForm);
      super.requestRole(request, userSession.getRoleId(), LotteryConstants.STOCK_BALANCE_ROLE);
      mav.addObject(model);
      mav.setViewName(LotteryConstants.JSP_LRS + LotteryConstants.STOCK_BALANCE_REPORT);
    } catch (Exception e) {
      log.error(this.getClass().getSimpleName()+" queryStockBalance failed!! ");
      model = new Model();
      if (e instanceof ServiceException){
        model.setMessage(((ServiceException) e).getLotteryMessage());
      }
      mav.addObject(model);
      mav.setViewName(LotteryConstants.JSP_LRS + LotteryConstants.STOCK_BALANCE_REPORT);
    }
    return mav;
  }
  
  @RequestMapping(value=LotteryConstants.EXPORT_STOCK_BALANCE)
  public void exportStockBalance(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(LotteryReportForm.FORM_NAME) LotteryReportForm lotteryReportForm) throws LotteryException {
    try {
      JasperReportCriteriaDTO criteria = new JasperReportCriteriaDTO();
      List<LotteryReportDTO> results = new ArrayList<LotteryReportDTO>();
      results = this.lotteryReportService.exportStockBalance(lotteryReportForm.getQueryNumberOfPeriods());
      if(!CollectionUtils.isEmpty(results)){
        //子報表集合
        Map<String,String> subMap = new HashMap<String, String>();
        subMap.put(LotteryReportForm.REPORT_SUB_SOLD_JRXML_NAME, LotteryReportForm.REPORT_SUB_SOLD_NAME);
        subMap.put(LotteryReportForm.REPORT_SUB_UNSOLD_JRXML_NAME,LotteryReportForm.REPORT_SUB_UNSOLD_NAME);
        //參數設定
        Map<String,Object> mapRriteria = new HashMap<String, Object>();
        mapRriteria.put(LotteryReportDTO.ATTRIBUTE.PERIODS.getValue(), lotteryReportForm.getQueryNumberOfPeriods());
        mapRriteria.put(LotteryReportDTO.ATTRIBUTE.SOLD_NUMBER.getValue(), lotteryReportForm.getCurrentSoldNumber());
        mapRriteria.put(LotteryReportDTO.ATTRIBUTE.NOT_FORSALE_NUMBER.getValue(), lotteryReportForm.getCurrentUnsoldNumber());
        criteria.setResult(results);
        criteria.setParameters(mapRriteria);
        //設置所需報表的Name
        criteria.setJrxmlName(LotteryReportForm.REPORT_JRXML_NAME);
        //設置報表路徑
        criteria.setJrxmlPath(LotteryConstants.JRXML_PATH);
        //設置匯出格式
        criteria.setType(JasperReportCriteriaDTO.REPORT_TYPE_MSEXCEL);
        //設置報表Name
        criteria.setReportFileName(LotteryReportForm.REPORT_JRXML_NAME);
        ReportExporter.exportMainAndSubReport(criteria, subMap, response);
      }
    } catch (Exception e) {
      log.error(this.getClass().getSimpleName()+" export failed!! ");
    }
  }
  
  @RequestMapping(value=LotteryConstants.INIT_SALE_TICKET_LIST)
  public ModelAndView initSaleTicketList(HttpServletRequest request,@ModelAttribute(LotteryReportForm.FORM_NAME) LotteryReportForm lotteryReportForm) throws LotteryException {
    ModelAndView mav = new ModelAndView();
    Model model = new Model();
    LotteryMessage message = null;
    try{
      UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
      if (userSession == null){
        logger.debug("The user is not logged on !!");
        userSession = new UserSessionContext();
      }
      model = lotteryReportService.init(lotteryReportForm);
      request.getSession().setAttribute(LotteryReportForm.NUMBER_OF_PERIODS_LIST, lotteryReportForm.getNumberOfPeriodsList());
      super.requestRole(request, userSession.getRoleId(), LotteryConstants.SALE_TICKET_LIST_ROLE);
    } catch (Exception e) {
      log.error(this.getClass().getName()+".init failed!! " + e);
      if (e instanceof ServiceException){
        message = ((ServiceException) e).getLotteryMessage();
      }
      model.setMessage(message);
    }
    mav.addObject(model);
    mav.setViewName(LotteryConstants.JSP_LRS + LotteryConstants.SALE_TICKET_LIST_REPORT);
    return mav;
  }
  
  @RequestMapping(value=LotteryConstants.QUERY_SALE_TICKETLIST)
  public ModelAndView querySaleTicketList(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(LotteryReportForm.FORM_NAME) LotteryReportForm lotteryReportForm) throws LotteryException {
    ModelAndView mav = null;
    Model model = null;
    try {
      UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
      if (userSession == null){
        logger.debug("The user is not logged on !!");
        userSession = new UserSessionContext();
      }
      mav = new ModelAndView();
      lotteryReportForm.setActionId(LotteryConstants.QUERY_SALE_TICKETLIST);
      model = this.lotteryReportService.querySaleTicketList(lotteryReportForm);
      super.requestRole(request, userSession.getRoleId(), LotteryConstants.SALE_TICKET_LIST_ROLE);
      mav.addObject(model);
      mav.setViewName(LotteryConstants.JSP_LRS + LotteryConstants.SALE_TICKET_LIST_REPORT);
    } catch (Exception e) {
      log.error(this.getClass().getSimpleName()+" querySaleTicketList failed!! ");
      model = new Model();
      if (e instanceof ServiceException){
        model.setMessage(((ServiceException) e).getLotteryMessage());
      }
      mav.addObject(model);
      mav.setViewName(LotteryConstants.JSP_LRS + LotteryConstants.SALE_TICKET_LIST_REPORT);
    }
    return mav;
  }
  
  @RequestMapping(value=LotteryConstants.EXPORT_SALE_TICKET_LIST)
  public void exportSaleTicketList(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(LotteryReportForm.FORM_NAME) LotteryReportForm lotteryReportForm) throws LotteryException {
    try {
      JasperReportCriteriaDTO criteria = new JasperReportCriteriaDTO();
      List<LotteryReportDTO> results = new ArrayList<LotteryReportDTO>();
      lotteryReportForm.setActionId(LotteryConstants.EXPORT_SALE_TICKET_LIST);
      this.lotteryReportService.querySaleTicketList(lotteryReportForm);
      results = lotteryReportForm.getSaleTicketList();
      if(!CollectionUtils.isEmpty(results)){
        //參數設定
        Map<String,Object> mapRriteria = new HashMap<String, Object>();
        criteria.setResult(results);
        criteria.setParameters(mapRriteria);
        //設置所需報表的Name
        criteria.setJrxmlName(LotteryReportForm.REPORT_SALE_TICKET_JRXML_NAME);
        //設置報表路徑
        criteria.setJrxmlPath(LotteryConstants.JRXML_PATH);
        //設置匯出格式
        criteria.setType(JasperReportCriteriaDTO.REPORT_TYPE_MSEXCEL);
        //設置報表Name
        criteria.setReportFileName(LotteryReportForm.REPORT_SALE_TICKET_JRXML_NAME);
        ReportExporter.exportReport(criteria, response);
      }
    } catch (Exception e) {
      log.error(this.getClass().getSimpleName()+" export failed!! ");
    }
  }

  /**
   * 
   * Purpose:MerchantSettlementReport的初始化方法
   * @author davidsheng
   * @param request
   * @param lotteryReportForm
   * @return
   * @throws LotteryException
   * @return ModelAndView
   */
  @RequestMapping(value=LotteryConstants.INIT_MERCHANT_SETTLEMENT)
  public ModelAndView initMerchantSettlement(HttpServletRequest request,@ModelAttribute(LotteryReportForm.FORM_NAME) LotteryReportForm lotteryReportForm) throws LotteryException {
    ModelAndView mav = new ModelAndView();
    Model model = new Model();
    LotteryMessage message = null;
    try{
      UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
      if (userSession == null){
        logger.debug("The user is not logged on !!");
        userSession = new UserSessionContext();
      }
      model = lotteryReportService.init(lotteryReportForm);
      super.requestRole(request, userSession.getRoleId(), LotteryConstants.MERCHANT_SETTLEMENT_ROLE);
    } catch (Exception e) {
      log.error(this.getClass().getName()+".init failed!! " + e);
      if (e instanceof ServiceException){
        message = ((ServiceException) e).getLotteryMessage();
      }
      model.setMessage(message);
    }
    mav.addObject(model);
    mav.setViewName(LotteryConstants.JSP_LRS + LotteryConstants.MERCHANT_SETTLEMENT_REPORT);
    return mav;
  }
  
  /**
   * 
   * Purpose:MerchantSettlementReport的查询方法
   * @author davidsheng
   * @param request
   * @param response
   * @param lotteryReportForm
   * @return
   * @throws LotteryException
   * @return ModelAndView
   */
  @RequestMapping(value=LotteryConstants.QUERY_MERCHANT_SETTLEMENT)
  public ModelAndView queryMerchantSettlement(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(LotteryReportForm.FORM_NAME) LotteryReportForm lotteryReportForm) throws LotteryException {
    ModelAndView mav = null;
    Model model = null;
    try {
      UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
      if (userSession == null){
        logger.debug("The user is not logged on !!");
        userSession = new UserSessionContext();
      }
      mav = new ModelAndView();
      lotteryReportForm.setActionId(LotteryConstants.QUERY_MERCHANT_SETTLEMENT);
      model = this.lotteryReportService.queryMerchantSettlement(lotteryReportForm);
      super.requestRole(request, userSession.getRoleId(), LotteryConstants.MERCHANT_SETTLEMENT_ROLE);
      mav.addObject(model);
      mav.setViewName(LotteryConstants.JSP_LRS + LotteryConstants.MERCHANT_SETTLEMENT_REPORT);
    } catch (Exception e) {
      log.error(this.getClass().getSimpleName()+" queryMerchantSettlement failed!! ");
      model = new Model();
      if (e instanceof ServiceException){
        model.setMessage(((ServiceException) e).getLotteryMessage());
      }
      mav.addObject(model);
      mav.setViewName(LotteryConstants.JSP_LRS + LotteryConstants.MERCHANT_SETTLEMENT_REPORT);
    }
    return mav;
  }
  
  @RequestMapping(value=LotteryConstants.EXPORT_MERCHANT_SETTLEMEN)
  public void exportMerchantSettlemen(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(LotteryReportForm.FORM_NAME) LotteryReportForm lotteryReportForm) throws LotteryException {
    try {
      JasperReportCriteriaDTO criteria = new JasperReportCriteriaDTO();
      List<NearbyStoresDTO> results = new ArrayList<NearbyStoresDTO>();
      lotteryReportForm.setActionId(LotteryConstants.EXPORT_MERCHANT_SETTLEMEN);
      this.lotteryReportService.queryMerchantSettlement(lotteryReportForm);
      results = lotteryReportForm.getMerchantSettlementList();
      if(!CollectionUtils.isEmpty(results)){
        //參數設定
        Map<String,Object> mapRriteria = new HashMap<String, Object>();
        criteria.setResult(results);
        criteria.setParameters(mapRriteria);
        //設置所需報表的Name
        criteria.setJrxmlName(LotteryReportForm.REPORT_MERCHANT_SETTLEMEN_JRXML_NAME);
        //設置報表路徑
        criteria.setJrxmlPath(LotteryConstants.JRXML_PATH);
        //設置匯出格式
        criteria.setType(JasperReportCriteriaDTO.REPORT_TYPE_MSEXCEL);
        //設置報表Name
        criteria.setReportFileName(LotteryReportForm.REPORT_MERCHANT_SETTLEMEN_JRXML_NAME);
        ReportExporter.exportReport(criteria, response);
      }
    } catch (Exception e) {
      log.error(this.getClass().getSimpleName()+" export failed!! ");
    }
  }
  
  /** (non-Javadoc)
   * @see com.gun.server.controller.LotteryMultiActionController#parse(javax.servlet.http.HttpServletRequest, java.lang.Object)
   */
  @Override
  public LotteryReportForm parse(HttpServletRequest request, LotteryReportForm command) throws LotteryException {
    // TODO Auto-generated method stub
    return null;
  }

  /** (non-Javadoc)
   * @see com.gun.server.controller.LotteryMultiActionController#validate(java.lang.Object)
   */
  @Override
  public boolean validate(LotteryReportForm command) throws LotteryException {
    // TODO Auto-generated method stub
    return false;
  }

}
