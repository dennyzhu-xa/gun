/**
 * 
 */
package com.gun.common.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;


/**
 * 
 * @author Edward Yen
 * @since  JDK 1.5
 * @date   2014/7/30
 * @maintenance Edward Yen
 */
public class PageInfo implements Serializable {

		private static final long serialVersionUID = -4898823376089052189L;

		public static enum PAGE_PARAM {page, size};
					
		/**
		 * 目前頁次
		 */
		private int page = 0;
		/**
		 * 一頁筆數
		 */
		private int size = 10;
		/**
		 * 總筆數
		 */
		private int total = 0;
		/**
		 * 最大索引數
		 */
		private int maxIndex = 10;

		public PageInfo(){
		}
		
		public PageInfo(HttpServletRequest request) {
			String page = validate((String) request.getParameter(PAGE_PARAM.page.name()));
			this.setPage((!StringUtils.hasText(page)) ? 0 : Integer.parseInt(page));
		}
		
		public boolean getIsFirstPage() {
			return this.isFirstPage();
		}
		public boolean getIsLastPage() {
			return this.isLastPage();
		}

		/**
		 * 取得目前頁次
		 * @return
		 */
		public int getPage() {
			return page;
		}
		/**
		 * 設定目前頁次
		 * @param page 目前頁次
		 */
		public void setPage(int page) {
			this.page = (page < 0) ? 0 : page;
			if (this.page < 0) this.page = 0;
		}
		/**
		 * 取得一頁筆數
		 * @return
		 */
		public int getSize() {
			return size;
		}
		/**
		 * 設定一頁筆數
		 * @param size 一頁筆數
		 */
		public void setSize(int size) {
			this.size = size;
		}
		/**
		 * 取得總筆數
		 * @return
		 */
		public int getTotal() {
			return total;
		}
		/**
		 * 設定總筆數
		 * @param total 總筆數
		 */
		public void setTotal(int total) {
			this.total = total;
		}
		/**
		 * 取得最大索引數
		 * @return
		 */
		public int getMaxIndex() {
			return maxIndex;
		}
		/**
		 * 設定最大索引數
		 * @param maxIndex 最大索引數
		 */
		public void setMaxIndex(int maxIndex) {
			this.maxIndex = maxIndex;
		}
		/**
		 * 取得頁數
		 * @return
		 */
		public int getPageNumbers() {
			return (int) Math.ceil((double) total / size);
		}
		/**
		 * 取得前一索引
		 * @return
		 */
		public int getPrevPage() {
			int prevPage = page - 1;
			return (prevPage < 0) ? 0 : prevPage;
		}
		/**
		 * 取得下一索引
		 * @return
		 */
		public int getNextPage() {
			int lastPage = getPageNumbers() - 1;
			int nextPage = page + 1;
			return (nextPage > lastPage) ? lastPage : nextPage;
		}
		/**
		 * 判斷是否為首頁
		 * @return
		 */
		public boolean isFirstPage() {
			return 0 == page;
		}
		/**
		 * 判對是否為尾頁
		 * @return
		 */
		public boolean isLastPage() {
			int pageNumbers = this.getPageNumbers();
			return (pageNumbers - 1) == page;
		}
		/**
		 * 取得索引清單
		 * @return
		 */
		public int[] getIndexList() {
			int[] range = getIndexRange();
			int[] ilist = new int[range[1] - range[0] + 1];
			for (int i = 0; i < ilist.length; i++) {
				ilist[i] = range[0] + i;
			}
			return ilist;
		}
		/**
		 * 取得索引範圍
		 * @return
		 */
		public int[] getIndexRange() {
			int startIndex = page - maxIndex / 2;
			int endIndex = startIndex + maxIndex - 1;

			if (startIndex < 0) {
				endIndex -= startIndex;
				startIndex = 0;
			}

			int lastIndex = getPageNumbers() - 1;
			
			if (endIndex > (lastIndex)) {
				startIndex -= (endIndex - lastIndex);
				endIndex = lastIndex;
			}

			if (startIndex < 0) startIndex = 0;
	
			return new int[] { startIndex, endIndex };
		}
		
		public List<ParamItem> getPageDropList() {
			List<ParamItem> items = new ArrayList<ParamItem>();
			int count = this.getPageNumbers() + 1;
			for (int i=1; i < count; i++){
				ParamItem item = new ParamItem();
				item.setName(String.valueOf(i));
				item.setValue(String.valueOf(i));
				items.add(item);
			}
			return items;
		}
		
		public String validate(String strParam) {
			return strParam;    
		}
		
}
