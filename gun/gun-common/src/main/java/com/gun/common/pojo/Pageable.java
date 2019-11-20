/**
 * 
 */
package com.gun.common.pojo;

/**
 * 
 * @author Edward Yen
 * @since  JDK 1.5
 * @date   2014/7/30
 * @maintenance Edward Yen
 */
public interface Pageable {

		public static final String PAGE_PARAM_PAGE 		= PageInfo.PAGE_PARAM.page.name();

		public static final String PAGE_PARAM_PAGE_SIZE = PageInfo.PAGE_PARAM.size.name();

		public PageInfo getPageInfo();
		public void setPageInfo(PageInfo pageInfo);
}
