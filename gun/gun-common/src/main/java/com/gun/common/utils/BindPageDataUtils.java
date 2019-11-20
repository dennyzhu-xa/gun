package com.gun.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gun.common.exception.LotteryException;
import com.gun.common.pojo.BeanUtils;


/**
 * 
 * @author felixli
 *
 */

public final class BindPageDataUtils {
	public static final String STRING = "java.lang.String";

	public static final String INTEGER = "java.lang.Integer";

	public static final String SHORT = "java.lang.Short";

	public static final String LONG = "java.lang.Long";

	public static final String BIGDECIMAL = "java.math.BigDecimal";

	public static final String BIGINTEGER = "java.math.BigInteger";

	public static final String NUMBER = "java.lang.Number";

	public static final String DOUBLE = "java.lang.Double";

	public static final String FLOAT = "java.lang.Float";

	public static final String BOOLEAN = "java.lang.Boolean";

	public static final String CHARACTER = "java.lang.Character";

	public static final String BYTE = "java.lang.Byte";

	public static final String BYTE_ARRAY = "byte[]";

	public static final String UTIL_DATE = "java.util.Date";

	public static final String SQL_DATE = "java.sql.Date";

	public static final String TIMESTAMP = "java.sql.Timestamp";

	public static final String TIME = "java.sql.Time";

	public BindPageDataUtils() {
	}

	/**
	 * Purpose:綁定畫面上的數據到對應的DTO對象
	 * @param request 畫面傳遞的請求
	 * @param clazz 綁值對象的Class
	 * @return Object 綁值成功的對象
	 * @throws LotteryException - 錯誤發生時丟出 IPMSServiceException
	 */
	public static Object bindValueObject(HttpServletRequest request, Class<?> clazz) throws LotteryException {
		return bindValueObject(request, clazz, null, false);
	}

	/**
	 * Purpose:綁定畫面上的數據到對應的DTO對象
	 * note1 如果畫面上有多個同類的對象 ,可以使用suffix區別開來.
	 * note2 如果使用了suffix進行區別,畫面欄位名字必須是DTO屬性名字添加 '"_"+suffix' or 'suffix+"_"'
	 * @param request 畫面傳遞的請求
	 * @param clazz 綁值對象的Class
	 * @param suffix 區分多個對象的關鍵字,可以為index等
	 * @param beforeOrAfter 前綴 or 後綴
	 * @return Object 綁值成功的對象
	 * @throws LotteryException - 錯誤發生時丟出 LotteryException
	 */
	public static Object bindValueObject(HttpServletRequest request, Class<?> clazz, String suffix, Boolean beforeOrAfter) throws LotteryException {
		try {
			if (request == null || clazz == null)
				return null;
			Object valueInstance = BeanUtils.newInstance(clazz);
			Field[] fields = BeanUtils.getAllFields(clazz);
			if (fields == null || fields.length == 0)
				return null;
			String fieldName = null;
			Object attrValue = null;
			Class<?> fieldType = null;
			for (Field field : fields) {
				if (Modifier.isStatic(field.getModifiers()))
					continue;
				fieldName = field.getName();
				fieldType = field.getType();
				if (BeanUtils.isPrimitive(fieldType)) {
					attrValue =
							request.getParameter(beforeOrAfter ? (((suffix == null || suffix.equals("")) ? "" : (suffix + "_")) + fieldName) : (fieldName + ((suffix == null || suffix.equals("")) ? ""
									: ("_" + suffix))));
					if (attrValue != null) {
						attrValue = BeanUtils.toObject(fieldType, attrValue.toString());
					}
				} else {
//					if(ValueObject.class.isAssignableFrom(fieldType) || CompositeIdentifier.class.isAssignableFrom(fieldType)) {
//						attrValue = bindValueObject(request, fieldType);
//					} 
					// Collection暫時未實現
//					else if(Collection.class.isAssignableFrom(fieldType)) {
//						if (List.class.isAssignableFrom(fieldType)) {
//							attrValue = new ArrayList<Object>(0);
//						} else if (Set.class.isAssignableFrom(fieldType)) {
//							attrValue = new HashSet<Object>(0);
//						} else {
//							throw new IpmsLotteryException("Not supported collection type - " + fieldType);
//						}
//					}
				}
				if (attrValue != null) {
					BeanUtils.set(attrValue, fieldName, valueInstance);
				}
				attrValue = null;

			}
			return valueInstance;
		} catch (Exception e) {
			throw new LotteryException("", e);
		}
	}

	/**
	 * Purpose: 綁定畫面上清單中的數據到對應的DTO集合中(添加只帶公共屬性的對象)
	 * note1 畫面上的同ID的控件個數必須一樣 否則會出現賦值亂掉的問題
	 * note2 默認無'前綴'or'後綴'
	 * note3 默認添加只帶公共屬性的對象
	 * @param request 畫面傳遞的請求
	 * @param clazz 綁值對象的Class
	 * @return List<?> 綁值成功的對象集合
	 * @throws LotteryException - 錯誤發生時丟出 LotteryException
	 */
	public static List<?> bindValueObjectList(HttpServletRequest request, Class<?> clazz) throws LotteryException {
		return bindValueObjectList(request, clazz, null, false, null, false);
	}

	/**
	 * Purpose: 綁定畫面上清單中的數據到對應的DTO集合中
	 * note1 畫面上的同ID的控件個數必須一樣 否則會出現賦值亂掉的問題
	 * note2 默認無'前綴'or'後綴'
	 * @param request 畫面傳遞的請求
	 * @param clazz 綁值對象的Class
	 * @param isAddPublicObject 是否添加只帶公共屬性的對象
	 * @return List<?> 綁值成功的對象集合
	 * @throws LotteryException - 錯誤發生時丟出 LotteryException
	 */
	public static List<?> bindValueObjectList(HttpServletRequest request, Class<?> clazz, List<String> publicAttributes) throws LotteryException {
		return bindValueObjectList(request, clazz, null, false, publicAttributes, false);
	}

	/**
	 * Purpose:綁定畫面上清單中的數據到對應的DTO集合中(添加只帶公共屬性的對象)
	 * note1 畫面上的同ID的控件個數必須一樣 否則會出現賦值亂掉的問題
	 * note2 如果畫面上有多個同類的對象 ,可以使用suffix區別開來.
	 * note3 如果使用了suffix進行區別,畫面欄位名字必須是DTO屬性名字添加 '"_"+suffix' or 'suffix+"_"'
	 * @param request 畫面傳遞的請求
	 * @param clazz 綁值對象的Class
	 * @param suffix 區分多個對象的關鍵字,可以為index等
	 * @param beforeOrAfter 前綴 or 後綴
	 * @return List<?> 綁值成功的對象集合
	 * @throws LotteryException - 錯誤發生時丟出 LotteryException
	 */
	public static List<?> bindValueObjectList(HttpServletRequest request, Class<?> clazz, String suffix, Boolean beforeOrAfter) throws LotteryException {
		return bindValueObjectList(request, clazz, suffix, beforeOrAfter, null, false);
	}

	/**
	 * Purpose:綁定畫面上清單中的數據到對應的DTO集合中
	 * note1 畫面上的同ID的控件個數必須一樣 否則會出現賦值亂掉的問題
	 * note2 如果畫面上有多個同類的對象 ,可以使用suffix區別開來.
	 * note3 如果使用了suffix進行區別,畫面欄位名字必須是DTO屬性名字添加 '"_"+suffix' or 'suffix+"_"'
	 * @param request 畫面傳遞的請求
	 * @param clazz 綁值對象的Class
	 * @param suffix 區分多個對象的關鍵字,可以為index等
	 * @param beforeOrAfter 前綴 or 後綴
	 * @param isAddPublicObject 是否添加只帶公共屬性的對象
	 * @return List<?> 綁值成功的對象集合
	 * @throws LotteryException - 錯誤發生時丟出 LotteryException
	 */
	public static List<?> bindValueObjectList(HttpServletRequest request, Class<?> clazz, String suffix, Boolean beforeOrAfter, List<String> publicAttributes, Boolean isAddPublicObject)
			throws LotteryException {
		try {
			if (request == null || clazz == null)
				return null;
			Field[] fields = BeanUtils.getAllFields(clazz);
			if (fields == null || fields.length == 0)
				return null;
			List<Object> valueInstances = new ArrayList<Object>();
			// 公共屬性
			Map<Field, String> commonAttr = new HashMap<Field, String>();
			Object valueInstance;
			String fieldName = null;
			Object attrValue = null;
			Class<?> fieldType = null;
			String[] values = null;
			List<MultipartFile> fileValues=null;
			MultipartFile fileValue;
			String value;
			for (Field field : fields) {
				if (Modifier.isStatic(field.getModifiers()))
					continue;
				values = null;
				fieldName = field.getName();
				fieldType = field.getType();
				if (BeanUtils.isPrimitive(fieldType)) {
				  if(fieldType==org.springframework.web.multipart.commons.CommonsMultipartFile.class){
				    MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request; 
				    fileValues =
				            mRequest.getFiles(beforeOrAfter ? (((suffix == null || suffix.equals("")) ? "" : (suffix + "_")) + fieldName)
			                  : (fieldName + ((suffix == null || suffix.equals("")) ? "" : ("_" + suffix))));
				  }else{
				    values =
                    request.getParameterValues(beforeOrAfter ? (((suffix == null || suffix.equals("")) ? "" : (suffix + "_")) + fieldName)
                        : (fieldName + ((suffix == null || suffix.equals("")) ? "" : ("_" + suffix))));
				  }
				  if(fieldType==org.springframework.web.multipart.commons.CommonsMultipartFile.class){
				    if (fileValues == null || fileValues.size() <= 0)
	            continue;
				  }else{
				    if (values == null || values.length <= 0)
	            continue;
				  }
					// 判斷是否為公共屬性
					if (!CollectionUtils.isEmpty(publicAttributes) && publicAttributes.contains(fieldName)) {
						// 公共屬性
						commonAttr.put(field, values[0]);
						continue;
					}
					if(fieldType==org.springframework.web.multipart.commons.CommonsMultipartFile.class){
					  if(!CollectionUtils.isEmpty(fileValues)){
	            for(int index = 0; index < fileValues.size(); index++){
	              fileValue = fileValues.get(index);
	              attrValue = fileValue;
	              if (valueInstances == null || valueInstances.size() <= index) {
	                valueInstance = BeanUtils.newInstance(clazz);
	                BeanUtils.set(attrValue, fieldName, valueInstance);
	                valueInstances.add(valueInstance);
	              } else {
	                valueInstance = valueInstances.get(index);
	                BeanUtils.set(attrValue, fieldName, valueInstance);
	              }
	            }
	          }
					}else{
					  if(values!=null){
	            for (int index = 0; index < values.length; index++) {
	              value = values[index];
	              attrValue = BeanUtils.toObject(fieldType, value);
	              if (valueInstances == null || valueInstances.size() <= index) {
	                valueInstance = BeanUtils.newInstance(clazz);
	                BeanUtils.set(attrValue, fieldName, valueInstance);
	                valueInstances.add(valueInstance);
	              } else {
	                valueInstance = valueInstances.get(index);
	                BeanUtils.set(attrValue, fieldName, valueInstance);
	              }
	            }
	          }
					}
					
				} else {
//					if(ValueObject.class.isAssignableFrom(fieldType) || CompositeIdentifier.class.isAssignableFrom(fieldType)) {
//						attrValue = bindValueObjectList(request, fieldType,suffix,beforeOrAfter,isAddPublicObject);
//					} 
				}
			}
			// 是否添加只帶公共屬性的對象(無單獨屬性的情況)
			if (isAddPublicObject) {
				// 如果有公共屬性 並且無單獨屬性 則新建立一個對象保存公共屬性
				if (!CollectionUtils.isEmpty(commonAttr) && CollectionUtils.isEmpty(valueInstances)) {
					valueInstances.add(BeanUtils.newInstance(clazz));
				}
			}
			// 設置公共屬性
			for (Field commfield : commonAttr.keySet()) {
				if (CollectionUtils.isEmpty(valueInstances))
					break;
				attrValue = BeanUtils.toObject(commfield.getType(), commonAttr.get(commfield));
				for (Object object : valueInstances) {
					BeanUtils.set(attrValue, commfield.getName(), object);
				}
			}
			return valueInstances;
		} catch (Exception e) {
			throw new LotteryException("", e);
		}
	}

	/**
	 * 處理屬性是Collection的情況
	 * @param field 對象的屬性
	 * @param coll 對象的屬性的值
	 * @return Collection 對象的屬性的值
	 * @throws ServiceException - 錯誤發生時丟出 IPMSServiceException
	 */
	public Collection<Object> invokeCollection(Field field, Collection<Object> coll) throws LotteryException {
		try {
			if (field == null)
				return null;
			ParameterizedType pt = (ParameterizedType) field.getGenericType();
			if (pt == null || pt.getActualTypeArguments().length <= 0)
				return null;
			Type type = pt.getActualTypeArguments()[0];
			if (type == null)
				return null;
			String className = type.toString();
			if (className == null || className.equals("") || className.length() <= 6)
				return null;
			className = className.substring(6);
			// 基本類型
			if (isPrimitive(className)) {

			}
			// dto
//			if(){
//				
//			}
			// Collection 嵌套暫時不支持
//			if(){
//				invokeCollection();
//			}
			BeanUtils.newInstance(className);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 根據類的signature對象的類型是否為簡單類型
	 * @param signature 類的簽名
	 * @return boolean 類型是否為簡單類型
	 */
	private static boolean isPrimitive(String signature) {
		if (signature == null)
			return true;
		if (signature == STRING || signature == INTEGER || signature == SHORT || signature == LONG || signature == BIGDECIMAL || signature == BIGINTEGER || signature == NUMBER || signature == DOUBLE
				|| signature == FLOAT || signature == BOOLEAN || signature == CHARACTER || signature == BYTE || signature == BYTE_ARRAY || signature == UTIL_DATE || signature == SQL_DATE
				|| signature == TIMESTAMP || signature == TIME) {
			return true;
		} else
			return false;
	}

	/**
	 * Purpose:產生enum的方法,方便大家產生attribute enum
	 * @param cls 要產生enum之Class
	 * @return void
	 */
	public static void generateAttributeEnum(Class<?> cls) {
		Field[] fields = cls.getDeclaredFields();
		StringBuilder enumBuild = new StringBuilder("public enum ATTRIBUTE {\n");
		StringBuilder attributeName = null;
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers()))
				continue;
			attributeName = new StringBuilder();
			for (int i = 0; i < field.getName().length(); i++) {
				if (Character.isUpperCase(field.getName().charAt(i))) {
					attributeName.append("_" + field.getName().charAt(i));
				} else {
					attributeName.append(Character.toUpperCase(field.getName().charAt(i)));
				}
			}
			enumBuild.append("	" + attributeName + "(\"" + field.getName() + "\"),\n");
		}
		enumBuild.replace(enumBuild.lastIndexOf(","), enumBuild.lastIndexOf(",") + 1, ";");
		System.out.println(enumBuild.toString());
		System.out.println("	public String value;");
		System.out.println("	ATTRIBUTE(String value) {");
		System.out.println("		this.value = value;");
		System.out.println("	};");
		System.out.println("};");
	}

	/**
	 * Purpose:綁定畫面上的數據到對應的DTO對象
	 * @param request 畫面傳遞的請求
	 * @param pojo 綁值對象
	 * @return Object 綁值成功的對象
	 * @throws LotteryException - 錯誤發生時丟出 IPMSServiceException
	 */
	public static void bindValueObject(HttpServletRequest request, Object pojo) throws LotteryException {
		bindValueObject(request, pojo, null, false);
	}

	/**
	 * Purpose:綁定畫面上的數據到對應的DTO對象
	 * note1 如果畫面上有多個同類的對象 ,可以使用suffix區別開來.
	 * note2 如果使用了suffix進行區別,畫面欄位名字必須是DTO屬性名字添加 '"_"+suffix' or 'suffix+"_"'
	 * @param request 畫面傳遞的請求
	 * @param pojo 綁值對象
	 * @param suffix 區分多個對象的關鍵字,可以為index等
	 * @param beforeOrAfter 前綴 or 後綴
	 * @return Object 綁值成功的對象
	 * @throws LotteryException - 錯誤發生時丟出 LotteryException
	 */
	public static void bindValueObject(HttpServletRequest request, Object pojo, String suffix, Boolean beforeOrAfter) throws LotteryException {
		try {
			if (request == null || pojo == null) {
				return;
			}
			Field[] fields = BeanUtils.getAllFields(pojo.getClass());
			if (fields == null || fields.length == 0) {
				return;
			}
			Class<?> fieldType = null;

			Map<String, Object> paramMap = (Map<String, Object>) request.getParameterMap();
			for (Field field : fields) {
				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				String fieldName = field.getName();
				fieldType = field.getType();
				Object attrValue = null;
				
				if (BeanUtils.isPrimitive(fieldType)) {
					String paramKey =
							beforeOrAfter ? (((suffix == null || suffix.equals("")) ? "" : (suffix + "_")) + fieldName) : (fieldName + ((suffix == null || suffix.equals("")) ? "" : ("_" + suffix)));
					if (paramMap.containsKey(paramKey)) {
						attrValue = request.getParameter(paramKey);
						if (attrValue != null) {
							attrValue = BeanUtils.toObject(fieldType, attrValue.toString());
						}
						BeanUtils.set(attrValue, fieldName, pojo);
					}
				}
			}
		} catch (Exception e) {
			throw new LotteryException("", e);
		}
	}

}
