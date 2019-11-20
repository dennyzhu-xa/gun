package com.cyber.lottery.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;

import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ValueObject;



/**
 * A basic DAO interface.
 * @author Edward Yen
 * @since JDK 1.5
 *
 */
@SuppressWarnings("rawtypes")
public interface AbstractDAO<T extends ValueObject> {
		public Session getSession();

		public T findById(String entityName, Serializable id) throws DataAccessException;

		public T findById(Class entityClass, Serializable id) throws DataAccessException;

		public T findById(T entity) throws DataAccessException;

		public T save(T entity)throws DataAccessException;

		public T update(T entity)throws DataAccessException;

		public void delete(T entity)throws DataAccessException;

		public T saveOrUpdate(T entity) throws DataAccessException;
		/**
		 * Purpose:批量處理多筆資料
		 * @param entities:entity集合
		 * @throws DataAccessException:出錯時返回DataAccessException
		 * @return void
		 */
		public void saveOrUpdateAll(Collection entities) throws DataAccessException;
		public void evict(T entity);
		public void flush();
		/**
		 * Purpose:取得資料總數
		 * @param sql:SQL
		 * @param params:參數名稱及參數value
		 * @throws DataAccessException::出錯時返回DataAccessException
		 * @return int:資料總筆數
		 */
		public int getTotal(String sql,Map<String,Object> params) throws DataAccessException;
		
		public List getQueryList(String sql,Map<String,Object> params,ResultTransformer transformer,PageInfo pageInfo) throws DataAccessException;


}
