package com.gun.common.pojo;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;

import com.gun.common.utils.Convertible;
import com.gun.common.utils.DateTimeUtils;
import com.gun.common.utils.StringUtils;

/**
 * This class extends from BeanUtils of spring framework. 
 * It provides additional miscellaneous bean utility methods.
 * @author Edward Yen
 * @since JDK 1.5
 */
@SuppressWarnings("rawtypes")
public abstract class BeanUtils extends org.springframework.beans.BeanUtils {
    public static final Class STRING_CLASS			 = String.class;
		public static final Class INTEGER_CLASS			 = Integer.class;
		public static final Class SHORT_CLASS			 = Short.class;
		public static final Class LONG_CLASS			 = Long.class;
		public static final Class BIGDECIMAL_CLASS		 = java.math.BigDecimal.class;
		public static final Class BIGINTEGER_CLASS		 = java.math.BigInteger.class;
		public static final Class NUMBER_CLASS			 = Number.class;
		public static final Class DOUBLE_CLASS			 = Double.class;
		public static final Class FLOAT_CLASS			 = Float.class;
		public static final Class BOOLEAN_CLASS			 = Boolean.class;
		public static final Class CHARACTER_CLASS		 = Character.class;
		public static final Class BYTE_CLASS			 = Byte.class;
		public static final Class BYTE_ARRAY_CLASS		 = byte[].class;
		public static final Class UTIL_DATE_CLASS		 = java.util.Date.class;
		public static final Class SQL_DATE_CLASS		 = java.sql.Date.class;
		public static final Class TIMESTAMP_CLASS		 = java.sql.Timestamp.class;
		public static final Class TIME_CLASS			 = java.sql.Time.class;
		public static final Class FILE_CLASS       = org.springframework.web.multipart.commons.CommonsMultipartFile.class;


		/**
		 * Instantiate a class with the given class name.
		 * 
		 * @param className a class name to instantiate.
		 * @return the new instance.
		 * @throws Exception if the bean cannot be instantiated.
		 */
		public static Object newInstance(String className) throws Exception {
				return newInstance(className, null);
		}
		/**
		 * Instantiate a class using the class name and the given arguments. 
		 * 
		 * @param className a class name to instantiate.
		 * @param args the arguments of a class constructor.
		 * @return the new instance.
		 * @throws Exception if the bean cannot be instantiated.
		 */
		@SuppressWarnings("deprecation")
    public static Object newInstance(String className, Object[] args) throws Exception {
				Class clazz = ClassUtils.forName(className);

				if (args == null || args.length == 0) return newInstance(clazz);

				Object obj = null;

				Constructor[] ctors = clazz.getConstructors();

				for (int i = 0; i < ctors.length; i++) {
						try {
								obj = newInstance(ctors[i], args);
								if (obj != null) break;
						} catch (Throwable e) {
						}
				}

				if (obj == null) throw new BeanInstantiationException(clazz, className + "'constructor is not found !");

				return obj;
		}
		/**
		 * This method is the same as <code>BeanUtils.instantiateClass()</code>.
		 * 
		 * @param clazz class to instantiate.
		 * @return the new instance.
		 * @throws Exception if the bean cannot be instantiated.
		 */
		@SuppressWarnings("unchecked")
		public static Object newInstance(Class clazz) throws Exception {
				return instantiateClass(clazz);
		}
		/**
		 * This method is the same as <code>BeanUtils.instantiateClass</code>.
		 * 
		 * @param ctor constructor to instantiate.
		 * @param args the arguments of the given constructor.
		 * @return the new instance.
		 * @throws Exception if the bean cannot be instantiated.
		 */
		@SuppressWarnings("unchecked")
		public static Object newInstance(Constructor ctor, Object[] args) throws Exception {
				return instantiateClass(ctor, args);
		}
		/**
		 * Invoke a Getter method with the given field name, declared on the
		 * given object.
		 * 
		 * @param object the object the underlying method is invoked from.
		 * @param fieldName the given field name.
		 * @return Object the return object from the field's Getter.
		 * @throws NoSuchMethodException
		 */
		public static Object invokeGetter(Object object, String fieldName) throws Exception {
				if (object == null) return null;
				if (!StringUtils.hasText(fieldName)) throw new NoSuchMethodException("The invoked field name can't be null !");
				String suffix = "";
				try {
						suffix = fieldName.substring(0, 1).toUpperCase();
						return invokeMethod(object, "get" + suffix + fieldName.substring(1), null, null);
				} catch(NoSuchMethodException nsme) {
						try {
								Field field = getField(object, fieldName);
								if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {
									try {
										return invokeMethod(object, fieldName, null, null);
									}catch(NoSuchMethodException nsme2)	{
										return invokeMethod(object, "is" + suffix + fieldName.substring(1), null, null);
									}
								}
						} catch (Throwable e) {
								if (e instanceof NoSuchMethodException) throw (NoSuchMethodException) e;
								else throw new NoSuchMethodException(e.getMessage());
						}
						throw nsme;
				}catch(Exception e){
					throw e;
				}
		}
		/**
		 * Invoke a Setter method with the given field name, declared on the
		 * given object.
		 * 
		 * @param object the object the underlying method is invoked from.
		 * @param fieldName the given field name.
		 * @param arg the argument of the given field
		 * @throws NoSuchMethodException
		 */
		public static void invokeSetter(Object object, String fieldName, Object arg) throws Exception {
				if (object == null) return;
				String methodName = null;
				try {
						methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);	
						invokeMethod(object, methodName, new Object[] { arg });
				} catch (Throwable e) {
						if (e instanceof NoSuchMethodException) {
							Field field = getField(object, fieldName);
							if (field == null) throw new NoSuchMethodException();
							invokeMethod(object, methodName, new Class[] { field.getType() }, new Object[] { arg });
						} else throw new NoSuchMethodException(e.getMessage());
				}
		}
		/**
		 * Invoke a method with the given method name and the given parameter
		 * types, declared on the given class or one of its superclasses.
		 * 
		 * @param object the object the underlying method is invoked from.
		 * @param methodName the name of the method to invoke.
		 * @param paramTypes the parameter types of the method to invoke.
		 * @param args the arguments used for the method call.
		 * @return the result of dispatching the method represented by this
		 *         object with parameters args
		 */
		public static Object invokeMethod(Object object, String methodName, Class[] paramTypes, Object[] args) throws Exception {
				if (object == null) return null;
				if (!StringUtils.hasText(methodName)) throw new NoSuchMethodException("The invoked method name can't be null !");
				try {
						Class cls = object.getClass();
						Method method = findMethod(cls, methodName, paramTypes);
						if (method == null) throw new NoSuchMethodException("No such method "+methodName+" !");
						return method.invoke(object, args);
				} catch (Exception e) {
					throw e;
				}
		}
		/**
		 * Invoke a method with the given method name and the given parameter
		 * types are the same as the types of the given args, declared on the
		 * given class or one of its superclasses. 
		 * 
		 * @param object the object the underlying method is invoked from.
		 * @param methodName the name of the method to invoke.
		 * @param args the arguments used for the method call.
		 * @return the result of dispatching the method represented by this
		 *         object with parameters args
		 */
		public static Object invokeMethod(Object object, String methodName, Object[] args) throws Exception {
				Class[] fieldTypes = null;
				if (args != null && args.length > 0) {
						fieldTypes = new Class[args.length];
						for (int i = 0; i < args.length; i++) {
								if (args[i] != null) fieldTypes[i] = args[i].getClass();
						}
				}
				return invokeMethod(object, methodName, fieldTypes, args);
		}
		/**
		 * Returns a Class object that identifies the declared type for the
		 * given field name represented by the given object.
		 * 
		 * @param object the object the underlying field is gotten from.
		 * @param fieldName the name of the field.
		 * @return a Class object identifying the declared type of the field
		 *         represented by this object.
		 * @throws Exception
		 */
		public static Class getFieldType(Object object, String fieldName) throws Exception {
				Class objClass = object.getClass();
				Class fieldType = getField(objClass, fieldName).getType();
				if (fieldType != null && !BeanUtils.isPrimitive(fieldType) && fieldType == Object.class) {
					try {
						String suffix = fieldName.substring(0, 1).toUpperCase();
						Method method = BeanUtils.findDeclaredMethodWithMinimalParameters(objClass, "get" + suffix + fieldName.substring(1));
						Class returnType = method.getReturnType();
						if (returnType != null) fieldType = returnType;
					}catch(Exception e) {}	
				}
				return fieldType;
		}
		/**
		 * Returns a object class that identifies the declared type for the
		 * given field name represented by the given object.
		 * 
		 * @param clazz class to instantiate.
		 * @param fieldName the name of the field.
		 * @return a Class object identifying the declared type of the field
		 *         represented by this class.
		 * @throws Exception
		 */
		public static Class getFieldType(Class clazz, String fieldName) throws Exception {
			return getField(clazz, fieldName).getType();
		}
		/**
		 * Clone a specific object.
		 * 
		 * @return Object - a clone object.
		 */
		public static Object clone(Object object) {
				if (object == null) return null;
				try {
						Object target = newInstance(object.getClass());
						copy(object, target);
						return target;
				} catch (Throwable e) {
						return null;
				}
		}
		/**
		 * Check whether the given field type is primitive or not. The primitive
		 * definition here is if it is a String, Integer, Short, Long,
		 * BigDecimal, BigInteger, Number, Double, Float, Boolean , Character,
		 * Byte, byte array, Date, Timestamp, Time or primitive is declared in
		 * Java class.
		 * 
		 * @param fieldType the Class type of the given field.
		 * @return is primitive class or not.
		 */
		public static boolean isPrimitive(Class fieldType) {
				if (fieldType == null) return true;

				if (fieldType.isPrimitive()) return true;
				if (ClassUtils.isPrimitiveOrWrapper(fieldType)) return true;
				
				if (fieldType == STRING_CLASS || fieldType == INTEGER_CLASS || fieldType == SHORT_CLASS
						|| fieldType == LONG_CLASS || fieldType == BIGDECIMAL_CLASS
						|| fieldType == BIGINTEGER_CLASS || fieldType == NUMBER_CLASS
						|| fieldType == DOUBLE_CLASS || fieldType == FLOAT_CLASS || fieldType == BOOLEAN_CLASS
						|| fieldType == CHARACTER_CLASS || fieldType == BYTE_CLASS || fieldType == BYTE_ARRAY_CLASS
						|| fieldType == UTIL_DATE_CLASS || fieldType == SQL_DATE_CLASS
						|| fieldType == TIMESTAMP_CLASS || fieldType == TIME_CLASS || fieldType == FILE_CLASS) {
						return true;
				} else return false;
		}
		/**
		 * Copy source object content to target object.
		 * @param source source object.
		 * @param target target object.
		 * @throws Exception
		 */
		public static void copy(Object source, Object target) throws Exception {
				copy(source, target, null);
		}
		/**
		 * Copy source object content to target object with some specific attributes.
		 * @param source source object.
		 * @param target target object.
		 * @param attrs some specific attributes of source object would be copied.
		 * @throws Exception
		 */
		public static void copy(Object source, Object target, String[] attrs) throws Exception {
				if (attrs == null) {
					Field[] fields = getAllFields(source.getClass());
					if (fields == null) {
						// donothing
						return;
					}
					for (int i = 0 ; i < fields.length ; i++) {
						 if(Modifier.isStatic(fields[i].getModifiers())) continue;
						 String fieldName = fields[i].getName();
						 Object attr = get(source, fieldName);
						 set(attr, fieldName, target);
					}
				}else {
					for (int i = 0 ; i < attrs.length ; i++) {
						String name = attrs[i];
						Object attr = get(source, name);
						set(attr, name, target);
					}
				}	
		}
		/**
		 * Get all fields of a specific class.
		 * @param clazz A specific class.
		 * @return An array of Field objects.
		 */
		public static Field[] getAllFields(Class clazz) {
				return getAllFields(clazz, Object.class);
		}
		/**
		 * Get all fields of a specific class with an assigned root class.
		 * @param clazz A specific class.
		 * @param rootClass an assigned root class
		 * @return
		 */
		public static Field[] getAllFields(Class clazz, Class rootClass) {
				Field[] fields = clazz.getDeclaredFields();
				if (rootClass != null && rootClass.equals(clazz)) return fields;
				// check super class
				Class superClass = clazz.getSuperclass();
				if (!superClass.equals((rootClass == null) ? Object.class : rootClass)) {
					Field[] derivedFields = getAllFields(superClass);
					Field[] merged = arrayMerge(fields, derivedFields);
					return merged;
				}
				return fields;
		}
		/**
		 * Returns the value of the field represented by this Field, on the specified object. 
		 * The value is automatically wrapped in an object if it has a primitive type.
		 * @param object object from which the represented field's value is to be extracted 
		 * @param attrName attribute name.
		 * @return the value of the represented field in object obj; 
		 * 	primitive values are wrapped in an appropriate object before being returned
		 * @throws Exception
		 */
		public static Object get(Object object, String attrName) throws Exception {
			Field field = getField(object, attrName);
			return field.get(object);
		}
		/**
		 * Sets the value of a field as an object on the specified object.
		 * @param attrValue the new value for the field of an assigned attribute name being modified 
		 * @param attrName an assigned attribute name
		 * @param target the object whose field should be modified
		 * @throws Exception
		 */
		public static void set(Object attrValue, String attrName, Object target) throws Exception {
			Field field = getField(target, attrName);
			field.set(target, attrValue);
		}
		/**
		 * Sets the value of a field as a boolean on the specified object.
		 * @param attrValue the new value for the field of an assigned attribute name being modified 
		 * @param attrName an assigned attribute name
		 * @param target the object whose field should be modified
		 * @throws Exception
		 */
		public static void set(boolean attrValue, String attrName, Object target) throws Exception {
			Field field = getField(target, attrName);
			field.setBoolean(target, attrValue);
		}
		/**
		 * Sets the value of a field as a byte on the specified object.
		 * @param attrValue the new value for the field of an assigned attribute name being modified 
		 * @param attrName an assigned attribute name
		 * @param target the object whose field should be modified
		 * @throws Exception
		 */
		public static void set(byte attrValue, String attrName, Object target) throws Exception {
			Field field = getField(target, attrName);
			field.setByte(target, attrValue);
		}
		/**
		 * Sets the value of a field as a char on the specified object.
		 * @param attrValue the new value for the field of an assigned attribute name being modified 
		 * @param attrName an assigned attribute name
		 * @param target the object whose field should be modified
		 * @throws Exception
		 */
		public static void set(char attrValue, String attrName, Object target) throws Exception {
			Field field = getField(target, attrName);
			field.setChar(target, attrValue);
		}
		
		/**
		 * Sets the value of a field as a double on the specified object.
		 * @param attrValue the new value for the field of an assigned attribute name being modified 
		 * @param attrName an assigned attribute name
		 * @param target the object whose field should be modified
		 * @throws Exception
		 */
		public static void set(double attrValue, String attrName, Object target) throws Exception {
			Field field = getField(target, attrName);
			field.setDouble(target, attrValue);
		}
		
		/**
		 * Sets the value of a field as a float on the specified object.
		 * @param attrValue the new value for the field of an assigned attribute name being modified 
		 * @param attrName an assigned attribute name
		 * @param target the object whose field should be modified
		 * @throws Exception
		 */
		public static void set(float attrValue, String attrName, Object target) throws Exception {
			Field field = getField(target, attrName);
			field.setFloat(target, attrValue);
		}
		
		/**
		 * Sets the value of a field as a int on the specified object.
		 * @param attrValue the new value for the field of an assigned attribute name being modified 
		 * @param attrName an assigned attribute name
		 * @param target the object whose field should be modified
		 * @throws Exception
		 */
		public static void set(int attrValue, String attrName, Object target) throws Exception {
			Field field = getField(target, attrName);
			field.setInt(target, attrValue);
		}
		
		/**
		 * Sets the value of a field as a long on the specified object.
		 * @param attrValue the new value for the field of an assigned attribute name being modified 
		 * @param attrName an assigned attribute name
		 * @param target the object whose field should be modified
		 * @throws Exception
		 */
		public static void set(long attrValue, String attrName, Object target) throws Exception {
			Field field = getField(target, attrName);
			field.setLong(target, attrValue);
		}
		
		/**
		 * Sets the value of a field as a short on the specified object.
		 * @param attrValue the new value for the field of an assigned attribute name being modified 
		 * @param attrName an assigned attribute name
		 * @param target the object whose field should be modified
		 * @throws Exception
		 */
		public static void set(short attrValue, String attrName, Object target) throws Exception {
			Field field = getField(target, attrName);
			field.setShort(target, attrValue);
		}
		
		/**
		 * Get a specific field object of an object with assigned attribute name.
		 * @param object an specific object.
		 * @param attrName attribute name.
		 * @return Field object.
		 * @throws Exception
		 */
		public static Field getField(Object object, String attrName) throws Exception {
			Class classType = object.getClass();
			return getField(classType, attrName);
		}
		
		/**
		 * Get a specific field object of a class with assigned attribute name.
		 * @param classType a class.
		 * @param attrName attribute name.
		 * @return Field object.
		 * @throws Exception
		 */
		private static Field getField(Class classType, String attrName) throws Exception {
			if (classType == null) {
				return null;
			}
			try {
			    Field field = classType.getDeclaredField(attrName);
			    field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException ex) {
				return getField(classType.getSuperclass(), attrName);
			}
		}
		
		/**
		 * Merge two arrays of Fields.
		 * @param array1 array of Field 1.
		 * @param array2 array of Field 2.
		 * @return merged Field array.
		 */
		private static Field[] arrayMerge(Field[] array1, Field[] array2) {
	        int count = array1.length + array2.length;
	        // create new array
	        Field[] rv = (Field[]) Array.newInstance(Field.class ,count);
	        int start = 0;
	        System.arraycopy(array1,0,rv,start,array1.length);
	        start += array1.length;
	        System.arraycopy(array2,0,rv,start,array2.length);
	        return rv;
	    }
		
		/**
		 * Merge any count of Array.
		 * @param type a class type of Array.
		 * @param arrays manay arrays
		 * @return merged array
		 */
		@SuppressWarnings("unchecked")
		public static <T> T[] arrayMerge(Class<T> type, T[]... arrays) {
				int count = 0;
				for (T[] array : arrays) {
						count += array.length;
				}
				// create new array
				T[] rv = (T[]) Array.newInstance(type, count);
				int start = 0;
				for (T[] array : arrays) {
						System.arraycopy(array, 0, rv, start, array.length);
						start += array.length;
				}
				return (T[]) rv;
		}
		
		/**
		 *Translate from a specific string to an object.
		 * @param fieldType To be translated class type.
		 * @param value a string would be translated.
		 * @return Object
		 */
		@SuppressWarnings("unchecked")
		public static Object toObject(Class fieldType, String value) throws Exception{
				if (value == null) return null;
				value=StringUtils.trimWhitespace(value);
				Number number = null;	
				if (fieldType == STRING_CLASS) return value;
				else if (fieldType == int.class || fieldType == INTEGER_CLASS) {
					if(StringUtils.hasText(value)){
						number = NumberUtils.parseNumber(value, fieldType, NumberFormat.getInstance());
					}
					return ((number == null) ? null : Integer.valueOf(number.intValue()));
				}
				else if (fieldType == short.class || fieldType == SHORT_CLASS) {
					if(StringUtils.hasText(value)){
						number = NumberUtils.parseNumber(value, fieldType, NumberFormat.getInstance());
					}
					return ((number == null) ? null : Short.valueOf(number.shortValue()));
				}
				else if (fieldType == long.class || fieldType == LONG_CLASS) {
					if(StringUtils.hasText(value)){
						number = NumberUtils.parseNumber(value, fieldType, NumberFormat.getInstance());
					}
					return ((number == null) ? null : Long.valueOf(number.longValue()));
				}
				else if (fieldType == BIGDECIMAL_CLASS) return new java.math.BigDecimal(value);
				else if (fieldType == BIGINTEGER_CLASS) return new java.math.BigInteger(value);
				else if (fieldType == NUMBER_CLASS) {
					return NumberUtils.parseNumber(value, fieldType, NumberFormat.getInstance());
				}
				else if (fieldType == double.class || fieldType == DOUBLE_CLASS) {
					if(StringUtils.hasText(value)){
						number =  NumberUtils.parseNumber(value, fieldType, NumberFormat.getInstance());
					}
					return ((number == null) ? null : Double.valueOf(number.doubleValue()));
				}
				else if (fieldType == float.class || fieldType == FLOAT_CLASS) {
					if(StringUtils.hasText(value)){
						number =  NumberUtils.parseNumber(value, fieldType, NumberFormat.getInstance());
					}
					return ((number == null) ? null : Float.valueOf(number.floatValue()));
				}
				else if (fieldType == boolean.class || fieldType == BOOLEAN_CLASS) return new Boolean(value);
				else if (fieldType == char.class || fieldType == CHARACTER_CLASS) return new Character(value.charAt(0));
				else if (fieldType == byte.class || fieldType == BYTE_CLASS) return new Byte(value);
				else if (fieldType == BYTE_ARRAY_CLASS) return value.getBytes();
				else if (fieldType == UTIL_DATE_CLASS) {
						java.util.Date dt = null;
						try {
							dt = DateTimeUtils.toDate(value);
						}catch(Exception e) {}
						return dt;
				} 
				else if(fieldType == TIMESTAMP_CLASS) {
						java.sql.Timestamp ts=null;
						try {
							ts = java.sql.Timestamp.valueOf(value);
						}catch(Exception e) {
								try {
									ts = DateTimeUtils.toTimestamp(value);
								}catch(Exception eee) {}		
						}
						return ts;
				} 
				else if (fieldType == TIME_CLASS) {
						java.sql.Time time = null;
						try {
							if(value.indexOf(":") == -1) {
								value=value.substring(0,2)+":"+value.substring(2,4)+":"+value.substring(4);
							}
							time = java.sql.Time.valueOf(value);
						}catch(Exception e) {}
						return time;
				}else if (Convertible.class.isAssignableFrom(fieldType)) {
					Convertible obj = null;
					try {
						obj = (Convertible)BeanUtils.newInstance(fieldType);
						obj.toObject(value);
					}catch(Exception e){}
					return obj;
				}else return value;
		}
		/**
		 * Compare two objects whether they are equal or not.
		 * @param obj1 object 1
		 * @param obj2 object 2
		 * @return
		 */
		public static boolean equals(Object obj1, Object obj2) {
			if (obj1 == null && obj2 == null) return true;
			else if (obj1 == null || obj2 == null) {
				if ((obj1 != null && obj1.getClass() == STRING_CLASS) || (obj2 != null && obj2.getClass() == STRING_CLASS)){
					String str1 = (obj1 == null) ? "" : obj1.toString();
					String str2 = (obj2 == null) ? "" : obj2.toString();
					
					str1 = StringUtils.trimWhitespace(str1);
					str2 = StringUtils.trimWhitespace(str2);
					return (str1.equals(str2));
				}else return false;
			}else {
				if (obj1.getClass() == STRING_CLASS || obj2.getClass() == STRING_CLASS){
					String str1 = (obj1.getClass() != STRING_CLASS) ? obj1.toString() : (String)obj1;
					String str2 = (obj2.getClass() != STRING_CLASS) ? obj2.toString() : (String)obj2;
					
					str1 = StringUtils.trimWhitespace(str1);
					str2 = StringUtils.trimWhitespace(str2);
					return (str1.equals(str2));
				}else if (obj1 instanceof Number && obj2 instanceof Number) {
					double d1 = ((Number)obj1).doubleValue();
					double d2 = ((Number)obj2).doubleValue();
					return (d1==d2);
				}else if (obj1 instanceof Collection && obj2 instanceof Collection) {
						int size1 =((Collection)obj1).size();
						int size2 = ((Collection)obj2).size();
						if (size1 != size2) return false;
						boolean isEqual = true;
						if (!CollectionUtils.isEmpty((Collection)obj1)) {
    						Iterator iter1 = ((Collection)obj1).iterator();
    						Iterator iter2 = ((Collection)obj2).iterator();
    						
    						while (iter1.hasNext()) {
    							Object item1 = iter1.next();
    							Object item2 =iter2.next();
    				    		if(!BeanUtils.equals(item1, item2)) {
    				    				isEqual = false;
    				    				break;
    				    		}
    				    	}
						}	
						return isEqual;
				}else if (obj1.getClass().isArray() && obj2.getClass().isArray()) {
						 int length1=Array.getLength(obj1);
						 int length2 = Array.getLength(obj2);
						 if (length1 != length2) return false;
						 boolean isEqual = true;
						 if (length1 > 0) {
					 	     for (int i = 0;i < length1; i++)  {
					 	          Object item1 = Array.get(obj1, i);
					 	          Object item2 = Array.get(obj2, i);
	    				    		if(!BeanUtils.equals(item1, item2)) {
	    				    				isEqual = false;
	    				    				break;
	    				    		}
	    				    }
						 }
						 return isEqual;
				}else return (obj1.equals(obj2));
			}
		}
}
