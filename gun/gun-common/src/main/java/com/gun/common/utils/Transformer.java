package com.gun.common.utils;

import com.gun.common.exception.ConvertException;

/**
* A transformer interface
* @author felixli
* @since JDK 1.7
*/
public interface Transformer {
		/**
		 * 
		 * @param fromObject a source object you would like to convert from.
		 * @param toObject a target object you would like to convert to.
		 * @return Object a converted object
		 * @throws ConvertException
		 */
		public Object transform(Object fromObject, Object toObject)throws ConvertException;
		
}
