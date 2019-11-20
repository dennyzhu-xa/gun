package com.gun.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gun.common.exception.ConvertException;
import com.gun.common.pojo.BeanUtils;
import com.gun.common.pojo.CompositeIdentifier;
import com.gun.common.pojo.ValueObject;

//import com.tsb.ipms.appl.log.monitor.MonitorLog;

/**
 * The concrete transformer class is a simple transformer for DTO and DMO.
 * The attributes number of DTO should be greater than and equal DMOs.
 * The attributes of DTO or DMO you would like to transform, whose names should be the same.
 * 
 * @author felixli
 * @since JDK 1.7
 */
public class SimpleDtoDmoTransformer implements Transformer{
	private static Log log = LogFactory.getLog(SimpleDtoDmoTransformer.class);
	/**
	 * The constant value of default suffix name of a DTO pattern.
	 */
	public static final String DEFAULT_DTO_SUFFIX = "DTO";
	private String dtoSuffixPattern = DEFAULT_DTO_SUFFIX;
	/**
	 * Constructor for SimpleDtoDmoTransformer.
	 */
	public SimpleDtoDmoTransformer() {
	}
	
	public Object transform(Object fromObject, Object toObject)	throws ConvertException {
		String fromObjName = fromObject.getClass().getSimpleName();
		boolean isDtoToDmo = (fromObjName.lastIndexOf(getDtoSuffixPattern()) >= 0)? true : false;
		return this.transform(fromObject, toObject, isDtoToDmo);
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object transform(Object fromObject, Object toObject, boolean isDtoToDmo)	throws ConvertException {
			if(fromObject == null || toObject == null) {
				log.debug("both values of fromObject and toObject can't be null!");
				return null;
			}
			//String fromObjName = fromObject.getClass().getSimpleName();
			//boolean isDtoToDmo = (fromObjName.lastIndexOf(getDtoSuffixPattern()) >= 0)? true : false;
			String toPackageName = toObject.getClass().getPackage().getName();
			Class dmoClass = (isDtoToDmo) ? toObject.getClass() : fromObject.getClass();
			Field[] fields = BeanUtils.getAllFields(dmoClass);
			
			if (fields == null || fields.length == 0) return null;
			
			String fieldName =  null;
			Object fromAttrObj = null;
			Object toAttrObj = null;
			try {
				for (int i = 0 ; i < fields.length ; i++) {
					
					if(Modifier.isStatic(fields[i].getModifiers())) continue;
					
						fieldName = fields[i].getName();
						fromAttrObj = null;
						try {
							fromAttrObj = BeanUtils.invokeGetter(fromObject, fieldName);
						}catch(Throwable ge) {
							log.debug("Can't get "+fieldName+"'s value !");
						}
						
						toAttrObj = null;
						
						if( fromAttrObj != null){
							if (BeanUtils.isPrimitive(fromAttrObj.getClass()) || fromAttrObj instanceof Enum) {
								toAttrObj = fromAttrObj;
							} else if(fromAttrObj instanceof ValueObject || fromAttrObj instanceof CompositeIdentifier) {
								Class toAttrObjClass = fields[i].getType();
								if (fromAttrObj instanceof CompositeIdentifier) {
									String fromAttrObjName = fromAttrObj.getClass().getSimpleName();
									int endIndex = fromAttrObjName.indexOf(getDtoSuffixPattern());
									if(endIndex <= 0) endIndex = fromAttrObjName.length(); 
									String toAttrObjName = (isDtoToDmo)? fromAttrObjName.substring(0, endIndex) : fromAttrObjName.concat(getDtoSuffixPattern());
									toAttrObjClass = Class.forName(toPackageName+"."+toAttrObjName);
								}
								toAttrObj = BeanUtils.newInstance(toAttrObjClass);
								transform(fromAttrObj, toAttrObj);
							} else if(fromAttrObj instanceof Collection) {
									if (fromAttrObj instanceof List) {
										toAttrObj = new ArrayList<Object>();
									} else if (fromAttrObj instanceof Set) {
										toAttrObj = new HashSet<Object>();
									} else {
										throw new ConvertException("Not supported collection type - " + fromAttrObj.getClass());
									}
									
									Iterator iterator = ((Collection)fromAttrObj).iterator();
									Object obj = null;
									Object toObj = null;
									String objName = null;
									String toObjName = null;
									while (iterator.hasNext()){
											obj = iterator.next();
											objName = obj.getClass().getSimpleName();
											int endIndex = objName.indexOf(getDtoSuffixPattern());
											if(endIndex <= 0) endIndex = objName.length(); 
											toObjName = (isDtoToDmo)? objName.substring(0, endIndex) : objName.concat(getDtoSuffixPattern());
											toObj = BeanUtils.newInstance(toPackageName+"."+toObjName);
											transform(obj, toObj);
											((Collection)toAttrObj).add(toObj);
									}//end of while
							}//end of if
						}// end of if fromAttrObj != null
						try {
							BeanUtils.set(toAttrObj, fieldName, toObject);
						}catch(Exception se){
							log.debug("Can't set "+toObject.getClass().getSimpleName()+"."+fieldName+"'s value");
						} 	
				}//end of for	
			} catch (Throwable e) {
					log.error(e, e);
					throw new ConvertException(e);
			}
//			finally {
//					MonitorLog.infoService("", "SimpleDtoDmoTransformer.transform","("+fromObject.getClass()+","+toObject.getClass()+")"," ("+(System.currentTimeMillis()-startTime)+" ms) ");
//			}			
			return toObject;
	}
	
	
	/**
	 * @param dtoSuffixPattern the dtoSuffixPattern to set
	 */
	public void setDtoSuffixPattern(String dtoSuffixPattern) {
			if (dtoSuffixPattern == null) this.dtoSuffixPattern = ""; 
			else this.dtoSuffixPattern = dtoSuffixPattern;
	}
	/**
	 * @return the dtoSuffixPattern
	 */
	public String getDtoSuffixPattern() {
			return dtoSuffixPattern;
	}

}
