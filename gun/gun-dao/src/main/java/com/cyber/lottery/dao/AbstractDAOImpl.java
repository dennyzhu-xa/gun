package com.cyber.lottery.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.gun.common.exception.DataAccessException;
import com.gun.common.pojo.PageInfo;
import com.gun.common.pojo.ValueObject;
import com.gun.common.utils.LotteryMessageCode;
import com.gun.dao.impl.GenericDAOImpl;


/**
 * A basic concrete DAO class.
 * @author Edward Yen
 * @since JDK 1.5
 *
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractDAOImpl<T extends ValueObject> implements AbstractDAO<T> {
	private static final Log log = LogFactory.getLog(AbstractDAOImpl.class);
		@Autowired
		private SessionFactory sessionFactory;
		
		@Autowired
		private GenericDAOImpl genericDAO;
		private String strStatement;
		
		/**
		 * Constructor for GenericBaseDAO.
		 */
		public AbstractDAOImpl(){
			
		}
		/**
		 * Constructor for GenericBaseDAO.
		 * @param daoSupport
		 */
		public AbstractDAOImpl(SessionFactory sessionFactory) {
				this.sessionFactory = sessionFactory;
		}
		
		public Session getSession() {  	       
	        return this.sessionFactory.getCurrentSession();  
	    }  				
		/** (non-Javadoc)
		 * @see com.cyber.lottery.dao.AbstractDAO#findById(java.lang.String, java.io.Serializable)
		 */
		@SuppressWarnings("unchecked")
    public T findById(String entityName, Serializable id) throws DataAccessException{
				Object entity = null;
				try {
					entity = getSession().get(entityName, id);
				}catch(Throwable e) {
					throw new DataAccessException(LotteryMessageCode.QUERY_FAIlED, e);
				}
			 	return (T)entity;	
		}
		/** (non-Javadoc)
		 * @see com.cyber.lottery.dao.AbstractDAO#findById(java.lang.Class, java.io.Serializable)
		 */
		@SuppressWarnings("unchecked")
    public T findById(Class entityClass, Serializable id) throws DataAccessException {
				T entity = null;
				try {
					entity = (T)getSession().get(entityClass, id);
				}catch(Throwable e) {
				    //e.printStackTrace();
					throw new DataAccessException(LotteryMessageCode.QUERY_FAIlED, e);
				}
			 	return entity;
		}
		public T findById(T entity) throws DataAccessException {
				if (entity == null) throw new DataAccessException(LotteryMessageCode.ILLEGAL_ARGUMENT);
				return this.findById(entity.getClass(), entity.getId());
		}
		/** (non-Javadoc)
		 * @see com.cyber.lottery.dao.AbstractDAO#delete(java.lang.Object)
		 */
		public void delete(T entity) throws DataAccessException {
				try {
					getSession().delete(entity);
					this.flush();
				}catch (Throwable e) {
					throw new DataAccessException(LotteryMessageCode.DELETE_FAILED, e);
				}
		}
		/** (non-Javadoc)
		 * @see com.cyber.lottery.dao.AbstractDAO#save(java.lang.Object)
		 */
		@SuppressWarnings("unchecked")
    public T save(T entity) throws DataAccessException {
				try {
					if (entity == null) throw new DataAccessException(LotteryMessageCode.ILLEGAL_ARGUMENT);
					Serializable id = this.getSession().save(entity);
					this.flush();
					entity.setId(id);
					return entity;
				}catch (Throwable e) {
					throw new DataAccessException(LotteryMessageCode.INSERT_FAILED,e);
				}	
		}
	
		/** (non-Javadoc)
		 * @see com.cyber.lottery.dao.AbstractDAO#update(java.lang.Object)
		 */
		public T update(T entity) throws DataAccessException {
				try {
					if (entity == null) throw new DataAccessException(LotteryMessageCode.ILLEGAL_ARGUMENT);
					this.getSession().update(entity);
					this.flush();
					return entity;
				}catch (Throwable e) {
					throw new DataAccessException(LotteryMessageCode.UPDATE_FAILED,e);
				}
		}
		/** (non-Javadoc)
		 * @see com.cyber.lottery.dao.AbstractDAO#saveOrUpdate(java.lang.Object)
		 */
		public T saveOrUpdate(T entity) throws DataAccessException {
				try {
					if (entity == null) throw new DataAccessException(LotteryMessageCode.ILLEGAL_ARGUMENT);
					this.getSession().saveOrUpdate(entity);
					this.flush();
					return entity;
				}catch (Throwable e) {
					throw new DataAccessException(LotteryMessageCode.SAVE_FAILED,e);
				}
		}
		/**
		 * (non-Javadoc)
		 * @see com.cyber.lottery.dao.AbstractDAO#saveOrUpdateAll(java.util.Collection)
		 */
		public void saveOrUpdateAll(Collection entities) throws DataAccessException {
			try{
				if(!CollectionUtils.isEmpty(entities)){
					for (Object object : entities) {
						getSession().saveOrUpdate(object);
					}
					this.flush();
				}
			}catch (Throwable e) {
				throw new DataAccessException(LotteryMessageCode.SAVE_FAILED,e);
			}
		}
		public void evict(T entity) {
				if (entity != null) {
					this.getSession().evict(entity);
				}
		}
		public void flush() {
			this.getSession().flush();
		}
		public void clear() {
				this.getSession().clear();	
		}
		
		public String getAccount() {
			return "";
//			Connection conn = null;
//			try {
//				conn = ((SessionFactoryImpl) sessionFactory).getConnectionProvider().getConnection();
//				return conn.getMetaData().getUserName();				
//			}catch(Exception e) {
//				//e.printStackTrace();
//				return null;
//			}finally{
//				if(conn!=null)
//					try {
//						conn.close();
//					} catch (SQLException e) {						
//						//e.printStackTrace();
//					}
//			}
		}
		
		public String getURL() {
			return "";
//			Connection conn = null;
//			try {
//				conn = ((SessionFactoryImpl) sessionFactory).getConnectionProvider().getConnection();
//				return conn.getMetaData().getURL();			
//			}catch(Exception e) {
//				//e.printStackTrace();
//				return null;
//			}finally {
//				try {
//					if(conn != null)conn.close();
//				}catch(Exception e) {
//					//e.printStackTrace();					
//				}
//			}
		}
		
		public String getStrStatement() {
			return strStatement;
		}
		public void setStrStatement(String strStatement) {
			this.strStatement = strStatement;
		}
		
		/**
		 * (non-Javadoc)
		 * @see com.cyber.lottery.dao.AbstractDAO#getTotal(java.lang.String, java.util.Map)
		 */
    public int getTotal(String sql,Map<String,Object> params) throws DataAccessException{
      SQLQuery cntQry = this.getSession().createSQLQuery(sql);
      if(!CollectionUtils.isEmpty(params)){
        Set<String> keys =  params.keySet();
        for (String key : keys) {
          cntQry.setParameter(key, params.get(key));
        }
      }
      int totalCnt = 0;
      List results = cntQry.list();
      if(!CollectionUtils.isEmpty(results)){
        totalCnt = Integer.parseInt(results.get(0).toString());
      }
      return totalCnt;
    }
		/**
		 * Purpose:查詢數據
		 * @param sql:需要執行的SQL語句
		 * @param params:SQL中需要傳入的參數
		 * @param transformer:AliasBean
		 * @param pageInfo:分頁組件
		 * @throws DataAccessException:出錯時DataAccessException
		 * @return List:返回結果集
		 */
		public List getQueryList(String sql,Map<String,Object> params,ResultTransformer transformer,PageInfo pageInfo) throws DataAccessException{
			log.debug(this.getClass().getName()+".getQueryList start!!!");
			try{
				SQLQuery sqlQuery  = this.getSession().createSQLQuery(sql);
				log.debug(this.getClass().getName()+".getQueryList SQL======>"+sql);
				if(!CollectionUtils.isEmpty(params)){
					Set<String> keys =  params.keySet();
					for (String key : keys) {
						sqlQuery.setParameter(key, params.get(key));
						log.debug(this.getClass().getName()+".getQueryList "+key+":======>"+params.get(key));
					}
				}
				if (pageInfo != null) {
					StringBuffer countSql = new StringBuffer(sql);
					int start = countSql.lastIndexOf("ORDER BY");
					if (start == -1){
						start = countSql.lastIndexOf("order by");
					}
					if (start != -1){
						countSql.delete(start, countSql.length());
					}
					start = countSql.indexOf("FROM");
					if (start == -1){
						start = countSql.indexOf("from");
					}
					if (start != -1){
						countSql.delete(0, start);
						countSql.insert(0, "SELECT COUNT(*) ");
					}
					SQLQuery countSqlQuery  = this.getSession().createSQLQuery(countSql.toString());
					if(!CollectionUtils.isEmpty(params)){
						Set<String> keys =  params.keySet();
						for (String key : keys) {
							countSqlQuery.setParameter(key, params.get(key));
							log.debug(this.getClass().getName()+".getQueryList "+key+":======>"+params.get(key));
						}
					}
					
					int totalCnt = (int) countSqlQuery.uniqueResult();
//					List results = sqlQuery.list();
//					int totalCnt = 0;
//					if(!CollectionUtils.isEmpty(results)){
//						totalCnt = results.size();
//					}
					pageInfo.setTotal(totalCnt);
					// 分頁資料撈取
					sqlQuery.setMaxResults(pageInfo.getSize());
					sqlQuery.setFirstResult(pageInfo.getPage() * pageInfo.getSize());
				}
				sqlQuery.setResultTransformer(transformer);
				return sqlQuery.list();
			}catch(DataAccessException e){
				log.error(this.getClass().getName()+".getQueryList failed!!!!!",e);
				throw new DataAccessException(e);
			}
		}
		
		/**
		 * Purpose:查詢數據
		 * @param sql:需要執行的SQL語句
		 * @param params:SQL中需要傳入的參數
		 * @param transformer:AliasBean
		 * @param pageInfo:分頁組件
		 * @throws DataAccessException:出錯時DataAccessException
		 * @return List:返回結果集
		 */
		public List getQueryList(String countSql,String sql,Map<String,Object> params,ResultTransformer transformer,PageInfo pageInfo) throws DataAccessException{
			log.debug(this.getClass().getName()+".getQueryList start!!!");
			try{
				SQLQuery sqlQuery  = this.getSession().createSQLQuery(sql);
				SQLQuery countSqlQuery  = this.getSession().createSQLQuery(countSql);
				log.debug(this.getClass().getName()+".getQueryList SQL======>"+sql);
				log.debug(this.getClass().getName()+".getQueryList countSqlQuery======>"+countSqlQuery);
				if(!CollectionUtils.isEmpty(params)){
					Set<String> keys =  params.keySet();
					for (String key : keys) {
						sqlQuery.setParameter(key, params.get(key));
						countSqlQuery.setParameter(key, params.get(key));
						log.debug(this.getClass().getName()+".getQueryList "+key+":======>"+params.get(key));
					}
				}
				if (pageInfo != null) {
					int totalCnt = (int)countSqlQuery.uniqueResult();
					pageInfo.setTotal(totalCnt);
					// 分頁資料撈取
					sqlQuery.setMaxResults(pageInfo.getSize());
					sqlQuery.setFirstResult(pageInfo.getPage() * pageInfo.getSize());
				}
				sqlQuery.setResultTransformer(transformer);
				return sqlQuery.list();
			}catch(DataAccessException e){
				log.error(this.getClass().getName()+".getQueryList failed!!!!!",e);
				throw new DataAccessException(e);
			}
		}
		
		/**
		 * Purpose:查詢數據
		 * @param sql:需要執行的SQL語句
		 * @param params:SQL中需要傳入的參數
		 * @param transformer:AliasBean
		 * @param pageInfo:分頁組件
		 * @throws DataAccessException:出錯時DataAccessException
		 * @return List:返回結果集
		 */
		public List getQueryList(String countSql,String sql,Map<String,Object> countParams,Map<String,Object> params,ResultTransformer transformer,PageInfo pageInfo) throws DataAccessException{
			log.debug(this.getClass().getName()+".getQueryList start!!!");
			try{
				SQLQuery sqlQuery  = this.getSession().createSQLQuery(sql);
				SQLQuery countSqlQuery  = this.getSession().createSQLQuery(countSql);
				log.debug(this.getClass().getName()+".getQueryList SQL======>"+sql);
				log.debug(this.getClass().getName()+".getQueryList countSqlQuery======>"+countSqlQuery);
				if(!CollectionUtils.isEmpty(params)){
					Set<String> keys =  params.keySet();
					for (String key : keys) {
						sqlQuery.setParameter(key, params.get(key));
						log.debug(this.getClass().getName()+".getQueryList "+key+":======>"+params.get(key));
					}
				}
				if(!CollectionUtils.isEmpty(countParams)){
					Set<String> keys =  countParams.keySet();
					for (String key : keys) {
						countSqlQuery.setParameter(key, countParams.get(key));
						log.debug(this.getClass().getName()+".getQueryList "+key+":======>"+countParams.get(key));
					}
				}
				if (pageInfo != null) {
					int totalCnt = (int)countSqlQuery.uniqueResult();
					pageInfo.setTotal(totalCnt);
					// 分頁資料撈取
					sqlQuery.setMaxResults(pageInfo.getSize());
					sqlQuery.setFirstResult(pageInfo.getPage() * pageInfo.getSize());
				}
				sqlQuery.setResultTransformer(transformer);
				return sqlQuery.list();
			}catch(DataAccessException e){
				log.error(this.getClass().getName()+".getQueryList failed!!!!!",e);
				throw new DataAccessException(e);
			}
		}
		
		/**
		 * Purpose:根据期数删除不是当前期的数据
		 * @author davidsheng
		 * @param sql:需要執行的SQL語句
		 * @param params:SQL中需要傳入的參數
		 * @throws DataAccessException:出錯時DataAccessException
		 * @return void
		 */
		public void deleteByLotteryPeriods(String sql,Map<String,Object> params) throws DataAccessException {
			log.debug(this.getClass().getName()+".deleteByLotteryPeriods start!!!");
			try {
				SQLQuery sqlQuery  = this.getSession().createSQLQuery(sql);
				log.debug(this.getClass().getName()+".deleteByLotteryPeriods SQL======>"+sql);
				if(!CollectionUtils.isEmpty(params)){
					Set<String> keys =  params.keySet();
					for (String key : keys) {
						sqlQuery.setParameter(key, params.get(key));
						log.debug(this.getClass().getName()+".deleteByLotteryPeriods "+key+":======>"+params.get(key));
					}
				}
				sqlQuery.executeUpdate();
			} catch (DataAccessException e) {
				log.error(this.getClass().getName()+".deleteByLotteryPeriods failed!!!!!",e);
				throw new DataAccessException(e);
			}
		}
		/**
		 * @return the sessionFactory
		 */
		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}
		/**
		 * @param sessionFactory the sessionFactory to set
		 */
		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}
		/**
		 * @return the genericDAO
		 */
		public GenericDAOImpl getGenericDAO() {
			return genericDAO;
		}
		/**
		 * @param genericDAO the genericDAO to set
		 */
		public void setGenericDAO(GenericDAOImpl genericDAO) {
			this.genericDAO = genericDAO;
		}

}
