package com.gun.common.utils;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

/**
 * Purpose:利用Jacob將Word檔案轉換為PDF檔
 * @author candicechen
 * @since  JDK 1.5
 * @date   2014/11/20
 * @MaintenancePersonnel candicechen
 */
public class WordToPDFUtils {
	private static final Log log = LogFactory.getLog(WordToPDFUtils.class);
	static final int WORD_FORMAT_PDF = 17;// PDF 格式  
	/**
	 * Constructor:空構造函數
	 */
	public WordToPDFUtils() {
		super();
	}
	public static void wordToPdf(String inputFile,String pdfFilePath,String pdfFileName) throws Exception{
		log.debug("WordToPDFUtils.wordToPdf start!!!");
		log.debug("WordToPDFUtils.wordToPdf inputFile ========>"+inputFile);
		log.debug("WordToPDFUtils.wordToPdf pdfFilePath ========>"+pdfFilePath);
		log.debug("WordToPDFUtils.wordToPdf pdfFileName ========>"+pdfFileName);
		try{
			File fileFord = new File(pdfFilePath);
			if (!fileFord.exists() || !fileFord.isDirectory()) {
				fileFord.mkdirs();
			}
			String pdfFile = pdfFilePath + pdfFileName;
			//打開word應用程式
			ActiveXComponent app = new ActiveXComponent("Word.Application");
			//設置word不可見，否則會彈出word介面
			app.setProperty("Visible", false);
			//獲得word中所有打開的文檔,返回Documents物件
			Dispatch docs = app.getProperty("Documents").toDispatch();
			//調用Documents物件中Open方法打開文檔，並返回打開的文檔物件Document
			Dispatch doc = Dispatch.call(docs,"Open",inputFile,false,true).toDispatch();
			//調用Document物件的SaveAs方法，將文檔保存為pdf格式
			Dispatch.call(doc,"ExportAsFixedFormat",pdfFile,WORD_FORMAT_PDF);
			//關閉文檔
			Dispatch.call(doc, "Close",false);
			//關閉word應用程式
			app.invoke("Quit", 0);
		}catch(Exception e){
			log.error("WordToPDFUtils.wordToPdf is Failed!!",e);
			throw new Exception(e);
		}
	}
}
