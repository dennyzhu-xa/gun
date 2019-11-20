package com.gun.common.utils;

import com.gun.common.exception.ConvertException;

/**
 * The Convertible interface should be implemented. This interface is desinged
 * to provide a common protocal for objects and string converted each other.
 * @author Edward Yen
 * @since JDK 1.5
 */
public interface Convertible {
	public static final String ELEMENT_COLLECTION_ELEMENT_CLASS = "CollectionElementClass";
	/**
	 * Convert an object into a string.
	 * @return String - A string that includes all value of fields in the object.
	 */	
	public String toMessageString() throws ConvertException;
	/**
	 * Reverse to convert a string into a specific object.
	 * @param inputString The input string.
	 */
	public void toObject(String inputString) throws ConvertException;
}	