package com.cyber.lottery.dao;

import java.util.List;

import com.gun.common.entity.pojo.LotteryReportDTO;
import com.gun.common.entity.pojo.NearbyStoresDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;
import com.gun.common.pojo.form.LotteryReportForm;

/**
 * 
 * @author felixli
 *
 */
public interface LotteryReportDAO extends AbstractDAO{	
	
	public List<ParamItem> getNumberOfPeriods() throws DataAccessException;
	
	public List<LotteryReportDTO> queryStockBalance(String periods) throws DataAccessException;
	
	public List<LotteryReportDTO> exportStockBalance(String periods) throws DataAccessException;
	
	public List<LotteryReportDTO> querySaleTicketList(PageInfo pageInfo,String periods) throws DataAccessException;
	
	public List<NearbyStoresDTO> queryMerchantSettlement(PageInfo pageInfo,LotteryReportForm lotteryReportForm) throws DataAccessException;
}
