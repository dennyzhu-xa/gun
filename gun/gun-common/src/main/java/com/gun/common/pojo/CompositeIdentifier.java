package com.gun.common.pojo;

import java.io.Serializable;
import java.lang.reflect.Field;


/**
 * CompositeIdentifier is a superclass for a composite id you would like to
 * define.<br>
 * It have implemented equals()/hashCode() by comparing the composite-id values
 * of both objects.<br>
 * 
 * @author Edward Yen
 * @since JDK 1.5
 */
public abstract class CompositeIdentifier implements Serializable {
		private static final long serialVersionUID = 8708335742735342086L;

		/**
		 * Constructor for CompositeIdentifier.
		 */
		public CompositeIdentifier() {
		}

		/**
		 * Returns true if both identifiers are equal.
		 * 
		 * @param otherCompositeId other composite id object.
		 * @return true if both keys are equal.
		 */
		public boolean equals(java.lang.Object otherCompositeId) {
				if (otherCompositeId == null) return false;
				if (this == otherCompositeId) return true;
				if (!(otherCompositeId instanceof CompositeIdentifier)
						&& !otherCompositeId.getClass().equals(this.getClass())) return false;

				Field[] fields = BeanUtils.getAllFields(this.getClass());

				try {
						for (int i = 0; i < fields.length; i++) {
								Object obj1 = BeanUtils.get(this, fields[i].getName());

								Object obj2 = BeanUtils.get(otherCompositeId, fields[i].getName());

								if ((obj1 == null || obj2 == null) || !obj1.equals(obj2)) return false;
						}
				} catch (Throwable e) {
						return false;
				}
				return true;
		}

		/**
		 * Returns the hash code for the composite id.
		 * 
		 * @return int value of hash code for the composite id.
		 */
		public int hashCode() {
				int hashCode = 0;
				try {
						Field[] fields = BeanUtils.getAllFields(this.getClass());
						for (int i = 0; i < fields.length; i++) {
								try {
										hashCode += BeanUtils.get(this, fields[i].getName()).hashCode();
								} catch (Exception ee) {
								}
						}
				} catch (Throwable e) {
				}
				return hashCode;
		}
}
