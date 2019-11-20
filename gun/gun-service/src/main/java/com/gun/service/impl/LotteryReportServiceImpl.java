package com.gun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cyber.lottery.dao.LotteryReportDAO;
import com.gun.common.entity.pojo.LotteryReportDTO;
import com.gun.common.entity.pojo.NearbyStoresDTO;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.LotteryMessage;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;
import com.gun.common.pojo.form.LotteryReportForm;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.common.utils.StringUtils;
import com.gun.service.LotteryReportService;

/**
 * @author felixli
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class LotteryReportServiceImpl implements LotteryReportService{
  private static final Log log = LogFactory.getLog(LotteryReportServiceImpl.class);
 
  @Autowired
  private LotteryReportDAO lotteryReportDAO;
  /** (non-Javadoc)
   * @see com.gun.service.LotteryService#init(java.lang.Object)
   */
  @Override
  public Model init(Object request) throws ServiceException {
    Model model = null;
    LotteryMessage message = null;
    try {
      model = new Model();
      LotteryReportForm lotteryReportForm = (LotteryReportForm) request;
      List<ParamItem> list = lotteryReportDAO.getNumberOfPeriods();
      lotteryReportForm.setNumberOfPeriodsList(list);
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
   * @see com.gun.service.LotteryReportService#queryStockBalance(com.gun.common.pojo.form.LotteryReportForm)
   */
  @Override
  public Model queryStockBalance(LotteryReportForm form) throws ServiceException {
    Model model = null;
    LotteryMessage message = null;
    List<LotteryReportDTO> lotterySaleList = new ArrayList<LotteryReportDTO>();
    try {
      model = new Model();
      lotterySaleList = this.lotteryReportDAO.queryStockBalance(form.getQueryNumberOfPeriods());
      form.setLotterySaleList(lotterySaleList);
      if (CollectionUtils.isEmpty(lotterySaleList)) {
        message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.DATA_NOT_FOUND);
      }else{
        form.setCurrentSoldNumber(lotterySaleList.get(0).getSoldNumber());
        form.setCurrentUnsoldNumber(lotterySaleList.get(0).getNotForsaleNumber());
        message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.QUERY_SUCCESS);
      }
    } catch (Exception e) {
      log.error(this.getClass().getName()+"query failed!! ", e);
      message = new LotteryMessage(LotteryMessage.STATUS_FAILURE,LotteryMessageCode.QUERY_FAIlED);
      throw new ServiceException(message);
    }
    model.setMessage(message);
    return model;
  }
  /** (non-Javadoc)
   * @see com.gun.service.LotteryReportService#exportStockBalance(java.lang.String)
   */
  @Override
  public List<LotteryReportDTO> exportStockBalance(String periods) throws ServiceException {
    List<LotteryReportDTO> result = null;
    try {
      result = this.lotteryReportDAO.exportStockBalance(periods);
    } catch (Exception e) {
      log.error(this.getClass().getName()+"exportStockBalance failed!! ", e);
      throw new ServiceException(e);
    }
    return result;
  }
  /** (non-Javadoc)
   * @see com.gun.service.LotteryReportService#querySaleTicketList(com.gun.common.pojo.form.LotteryReportForm)
   */
  @Override
  public Model querySaleTicketList(LotteryReportForm lotteryReportForm) throws ServiceException {
    Model model = null;
    LotteryMessage message = null;
    List<LotteryReportDTO> results = new ArrayList<LotteryReportDTO>();
    try {
      log.debug(this.getClass().getName()+ " querySaleTicketList start!!");
      model = new Model();
      //分頁元件
      PageInfo pageInfo = new PageInfo();
      if(LotteryConstants.QUERY_SALE_TICKETLIST.equals(lotteryReportForm.getActionId())){
        if(StringUtils.hasText(lotteryReportForm.getPageSet())) {
          pageInfo.setMaxIndex(Integer.parseInt(lotteryReportForm.getPageSet()));
          pageInfo.setSize(Integer.parseInt(lotteryReportForm.getPageSet()));
        }
        if(lotteryReportForm.getPageNo()!=null) {
          pageInfo.setPage(lotteryReportForm.getPageNo());
        }
        results = this.lotteryReportDAO.querySaleTicketList(pageInfo,lotteryReportForm.getQueryNumberOfPeriods());
        if (CollectionUtils.isEmpty(results)) {
          message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.DATA_NOT_FOUND);
        }else{
          message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.QUERY_SUCCESS);
        }
        model.setPageInfo(pageInfo);
        model.setMessage(message);
      }else{
    	pageInfo.setPage(-1);
		pageInfo.setSize(-1);
        results = this.lotteryReportDAO.querySaleTicketList(pageInfo,lotteryReportForm.getQueryNumberOfPeriods());
      }
      lotteryReportForm.setSaleTicketList(results);
      log.debug(this.getClass().getName()+ " querySaleTicketList  end!!");
    } catch (Exception e) {
      log.error(this.getClass().getName()+".querySaleTicketList failed:"+e);
      message = new LotteryMessage(LotteryMessage.STATUS_FAILURE,LotteryMessageCode.QUERY_FAIlED);
      throw new ServiceException(message);
    }
    return model;
  }
  
  /**
   * (non-Javadoc)
   * @see com.gun.service.LotteryReportService#queryMerchantSettlement(com.gun.common.pojo.form.LotteryReportForm)
   */
  @Override
  public Model queryMerchantSettlement(LotteryReportForm lotteryReportForm) throws ServiceException {
	  Model model = null;
	    LotteryMessage message = null;
	    List<NearbyStoresDTO> results = new ArrayList<NearbyStoresDTO>();
	    try {
	      log.debug(this.getClass().getName()+ " queryMerchantSettlement start!!");
	      model = new Model();
	      //分頁元件
	      PageInfo pageInfo = new PageInfo();
	      if(LotteryConstants.QUERY_MERCHANT_SETTLEMENT.equals(lotteryReportForm.getActionId())){
	        if(StringUtils.hasText(lotteryReportForm.getPageSet())) {
	          pageInfo.setMaxIndex(Integer.parseInt(lotteryReportForm.getPageSet()));
	          pageInfo.setSize(Integer.parseInt(lotteryReportForm.getPageSet()));
	        }
	        if(lotteryReportForm.getPageNo()!=null) {
	          pageInfo.setPage(lotteryReportForm.getPageNo());
	        }
	        results = this.lotteryReportDAO.queryMerchantSettlement(pageInfo,lotteryReportForm);
	        if (CollectionUtils.isEmpty(results)) {
	          message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.DATA_NOT_FOUND);
	        }else{
	          message = new LotteryMessage(LotteryMessage.STATUS_SUCCESS,LotteryMessageCode.QUERY_SUCCESS);
	        }
	        model.setPageInfo(pageInfo);
	        model.setMessage(message);
	      }else{
	    	  pageInfo.setPage(-1);
			  pageInfo.setSize(-1);
	    	  results = this.lotteryReportDAO.queryMerchantSettlement(pageInfo,lotteryReportForm);
	      }
	      lotteryReportForm.setMerchantSettlementList(results);
	      log.debug(this.getClass().getName()+ " queryMerchantSettlement  end!!");
	    } catch (Exception e) {
	      log.error(this.getClass().getName()+".queryMerchantSettlement failed:"+e);
	      message = new LotteryMessage(LotteryMessage.STATUS_FAILURE,LotteryMessageCode.QUERY_FAIlED);
	      throw new ServiceException(message);
	    }
	    return model;
  }

}
