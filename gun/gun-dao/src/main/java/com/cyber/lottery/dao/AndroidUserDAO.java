package com.cyber.lottery.dao;

import java.math.BigDecimal;
import java.util.List;

import com.gun.common.entity.AndroidUser;
import com.gun.common.entity.pojo.AndroidUserDTO;
import com.gun.common.entity.pojo.CustomerWinningDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ParamItem;
/**
 * 
 * @author kevinshen
 *
 */
public interface AndroidUserDAO extends AbstractDAO<AndroidUser>{

	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	AndroidUser selectByUsername(String username);
	/**
	 * 根据token查询用户
	 * @param token
	 * @return
	 */
	AndroidUser selectByToken(String token);
	/**
	 * 根据id查询用户信息
	 * @param token
	 * @return
	 */
	AndroidUserDTO selectById(BigDecimal userId);
	/**
	 * 
	 * Purpose:查詢
	 * @author samDuan
	 * @return
	 * @return List<AndroidUserDTO>
	 */
	List<AndroidUserDTO> exportUser(PageInfo pageInfo,String phoneNumber,String moblieNo)throws DataAccessException;
	/**
	 * 根据FaceBook Id查询用户信息
	 * @param facebookUserId
	 * @return
	 */
	AndroidUser selectByFaceBookUserId(String facebookUserId);
	/**
	 * 
	 * Purpose:查詢用户中奖信息
	 * @author neiljing
	 * @return
	 * @return List<CustomerWinningDTO>
	 */
	List<CustomerWinningDTO> queryCustomerWinning(PageInfo pageInfo,String period,String username)throws DataAccessException;
  
  public List<ParamItem> getNumberOfPeriods() throws DataAccessException;
}
