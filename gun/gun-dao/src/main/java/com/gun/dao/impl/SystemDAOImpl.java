package com.gun.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.DataException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cyber.lottery.dao.AbstractDAOImpl;
import com.cyber.lottery.dao.SystemDAO;
import com.gun.common.entity.SysUserInfo;
import com.gun.common.entity.pojo.SysUserInfoDTO;
import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.form.SysUserInfoForm;
import com.gun.common.utils.LotteryConstants;
import com.gun.common.utils.SqlSyntaxFactory;
import com.gun.common.utils.StringUtils;


@Repository
public class SystemDAOImpl extends AbstractDAOImpl<SysUserInfo> implements SystemDAO {
	
	private static final Log log = LogFactory.getLog(SystemDAOImpl.class);

	/**
	 * (non-Javadoc)
	 * @see com.cyber.lottery.dao.SystemDAO#list(com.gun.common.pojo.PageInfo)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
  public List<SysUserInfoDTO> list(PageInfo pageInfo,SysUserInfoForm sysUserInfoForm) throws DataAccessException {
		List<SysUserInfoDTO> results = null;
		String sqlQuery = null;
		try {
			sqlQuery =  SqlSyntaxFactory.GET_USER_INFO;
			Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.hasText(sysUserInfoForm.getQueryUserAccount())){
			  sqlQuery+="AND su.USER_ID like :queryUserAccount ";
			  params.put("queryUserAccount", LotteryConstants.MARK_PERCENT+sysUserInfoForm.getQueryUserAccount()+LotteryConstants.MARK_PERCENT);
			}
			if(StringUtils.hasText(sysUserInfoForm.getQueryUserName())){
			  sqlQuery+="AND su.USER_NAME like :queryUserName ";
			  params.put("queryUserName", LotteryConstants.MARK_PERCENT+sysUserInfoForm.getQueryUserName()+LotteryConstants.MARK_PERCENT);
			}
			log.debug(this.getClass().getName()+"[SQL] ---> list  SQL：" + SqlSyntaxFactory.GET_USER_BY_NAME);
			List list = super.getQueryList(sqlQuery, params, Transformers.aliasToBean(SysUserInfoDTO.class), pageInfo);
			if(!CollectionUtils.isEmpty(list)){
				results = (List<SysUserInfoDTO>)list;
			}
		}catch (DataException e) {
			log.error(this.getClass().getName()+"list failed!!" + e );
			throw new DataAccessException(e);
		}
		return results;
	}


}
