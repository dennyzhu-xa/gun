package com.gun.service;

import java.util.List;

import com.gun.common.entity.pojo.LotteryReportDTO;
import com.gun.common.exception.ServiceException;
import com.gun.common.pojo.Model;
import com.gun.common.pojo.form.LotteryReportForm;

/**
 * 
 * @author felixli
 *
 */
@SuppressWarnings("rawtypes")
public interface LotteryReportService extends LotteryService{

  public Model queryStockBalance(LotteryReportForm lotteryReportForm) throws ServiceException;
  
  public List<LotteryReportDTO> exportStockBalance(String periods) throws ServiceException;
  
  public Model querySaleTicketList(LotteryReportForm lotteryReportForm) throws ServiceException;
  
  public Model queryMerchantSettlement(LotteryReportForm lotteryReportForm) throws ServiceException;
}
