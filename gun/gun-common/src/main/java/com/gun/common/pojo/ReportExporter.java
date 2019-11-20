package com.gun.common.pojo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleRtfReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * Purpose: 處理JasperReport工具
 * @author Felxili
 * @since  JDK 1.7
 * @date   2017/07/04
 * @MaintenancePersonnel Felxili
 */
public class ReportExporter {
  private static final Log log = LogFactory.getLog(ReportExporter.class);
  /**
   * Purpose:導出JasperReport報表
   * @param criteria:報表匯出DTO
   * @param tempFolder:暫存路徑
   * @throws Exception:出錯時返回
   * @return boolean:返回結果
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static boolean exportReportToFile(JasperReportCriteriaDTO criteria, String tempFolder) throws Exception {
    log.debug("Enter method ReportExporter.exportReportToFile()");
    ArrayList logArrayList = new ArrayList();
    long s = System.currentTimeMillis();
    if (criteria == null) {
      return false;
    }
    //上傳檔案路徑
    File fileFord = new File(tempFolder);
    //判斷儲存路徑是否存在，若不存在，則重新新建
    if (!fileFord.exists() || !fileFord.isDirectory()) {
      fileFord.mkdirs();
    }
    ByteArrayOutputStream outputStream = null;
    FileOutputStream fos  = null;
    try {
      JasperReport jasperReport = null;
      // 需要jrXml文檔的方式
      if (criteria.isAutoBuildJasper() == false) {
        String jrXml = criteria.getJrxmlPath() + criteria.getJrxmlName() + JasperReportCriteriaDTO.JRXML_EXT_NAME;
        InputStream inputStream = ReportExporter.class.getResourceAsStream(jrXml);
        //  //System.setProperty("javax.xml.parsers.SAXParserFactory","org.apache.xerces.jaxp.SAXParserFactoryImpl");
          // //System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
        JasperDesign design = JRXmlLoader.load(inputStream);
        // 編譯報表（.jrxml -> .jasper）
        jasperReport = JasperCompileManager.compileReport(design);
        logArrayList.add("compileReport="+(System.currentTimeMillis()-s)+" ms");
      }else{
        String jasper = criteria.getJrxmlPath() + criteria.getJrxmlName() + JasperReportCriteriaDTO.JASPER_EXT_NAME;
        InputStream inputStream = ReportExporter.class.getResourceAsStream(jasper);
        jasperReport = (JasperReport)JRLoader.loadObject(inputStream);
        logArrayList.add("readJasper="+(System.currentTimeMillis()-s)+" ms");
      }
      
      // 填充報表
      long f = System.currentTimeMillis();
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, criteria.getParameters(), 
            new JRBeanCollectionDataSource(criteria.getResult()));
      logArrayList.add("fillReport="+(System.currentTimeMillis()-f)+" ms");
      
      outputStream = new ByteArrayOutputStream();
      
      JRAbstractExporter exporter = null;
      String reportExtName = "";
      if (StringUtils.hasText(criteria.getType()) == false) {
        exporter = new JRPdfExporter();
        reportExtName = JasperReportCriteriaDTO.REPORT_EXT_NAME_PDF;
      } else if (criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSWORD)) {
        exporter = new JRRtfExporter();
        reportExtName = JasperReportCriteriaDTO.REPORT_EXT_NAME_MSWORD;
      } else if (criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSEXCEL)) {
        exporter = new JRXlsExporter();
        reportExtName = JasperReportCriteriaDTO.REPORT_EXT_NAME_MSEXCEL;
      }else if (criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_CSV)){
        exporter = new JRCsvExporter();
//        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
        reportExtName = JasperReportCriteriaDTO.REPORT_EXT_NAME_CSV;
      } else {
        exporter = new JRPdfExporter();
        reportExtName = JasperReportCriteriaDTO.REPORT_EXT_NAME_PDF;
      }
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      // 如果是word的話用不同的流
      if(criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSWORD)){
        exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
      }else{
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
      }
      long e = System.currentTimeMillis();
      exporter.exportReport();
      logArrayList.add("exportReport="+(System.currentTimeMillis()-e)+" ms");
      
      byte[] bytes = null;      
      //輸出保教到指定目錄
      bytes = outputStream.toByteArray();
      
      log.debug("Report File="+tempFolder+criteria.getReportFileName()+reportExtName);
      File file = new File(tempFolder+criteria.getReportFileName()+reportExtName);
      fos = new FileOutputStream(file);
      if(bytes != null ){
        //long w = System.currentTimeMillis();
        fos.write(bytes);
      }
      logArrayList.add("exportReportToFile.total="+(System.currentTimeMillis()-s)+" ms");
      log.debug("Exit method ReportExporter.exportReportToFile()");
      for(int i=0;i<logArrayList.size();i++){
        log.debug(logArrayList.get(i));
      }
      return true;
    } catch (Exception e) {
      log.error("Exception: method ReportExporter.exportReportToFile() - ", e);
      return false;
    } finally {
      if (fos != null) {
        fos.close();
      }
      if (outputStream != null) {
        outputStream.close();
      }
    }
  }
  
  /**
   * Purpose:導出多sheet excel報表(包含子报表)
   * @param criteria:JasperReport條件設定DTO List集合
   * @param response:HttpServletResponse
   * @return 導出是否成功
   * @throws Exception
   */
  public static boolean exportReportForSheets(List<JasperReportCriteriaDTO> criterias,HttpServletResponse response) throws Exception {
    log.debug("Enter method ReportExporter.exportReportForSheets()");
    if (CollectionUtils.isEmpty(criterias)) {
      return false;
    }
    ByteArrayOutputStream outputStream = null;
    ServletOutputStream oStream = null;
    try {
      int sheetsSize = criterias.size();
      JasperReport[] jasperReports = new JasperReport[sheetsSize];
      JasperReportCriteriaDTO criteria = null;
      List<JasperPrint> prints = new ArrayList<JasperPrint>();
      JasperPrint jasperPrint = null;
      //sheet名稱
      String[] sheetNames = new String[sheetsSize];
      String type = criterias.get(0).getType();
      String reportFileName = criterias.get(0).getReportFileName();
      for(int i=0;i<sheetsSize;i++){
        criteria = criterias.get(i);
        // 需要jrXml文檔的方式
        if (criteria.isAutoBuildJasper() == false) {
          String jrXml = criteria.getJrxmlPath()
              + criteria.getJrxmlName()
              + JasperReportCriteriaDTO.JRXML_EXT_NAME;
          InputStream inputStream = ReportExporter.class
              .getResourceAsStream(jrXml);
  //        //System.setProperty("javax.xml.parsers.SAXParserFactory","org.apache.xerces.jaxp.SAXParserFactoryImpl");
           // //System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
          JasperDesign design = JRXmlLoader.load(inputStream);
          // 編譯報表（.jrxml -> .jasper）
          jasperReports[i] = JasperCompileManager.compileReport(design);
          // 編譯sub報表（.jrxml -> .jasper）
          String subjrXml = null;
          InputStream subinputStream = null;
          JasperDesign subDesign = null;
          JasperReport subJasperReport = null;
          Map<String, String> subjrXmlNames = criteria.getSubjrXmlNames();
          if(subjrXmlNames!=null){
            for(String subjrXmlName: subjrXmlNames.keySet()){
              subjrXml = criteria.getJrxmlPath() + subjrXmlName + JasperReportCriteriaDTO.JRXML_EXT_NAME;
              subinputStream = ReportExporter.class.getResourceAsStream(subjrXml);
              subDesign = JRXmlLoader.load(subinputStream);
                subJasperReport = JasperCompileManager.compileReport(subDesign);
                // update by riverjin 2015/08/31 
              if(criteria.getParameters() == null){
                Map<String, Object> parameters = new  HashMap<String, Object>();
                criteria.setParameters(parameters);
              }
                criteria.getParameters().put(subjrXmlNames.get(subjrXmlName).toString(), subJasperReport);
            }
          }
        }
        // 填充報表
        jasperPrint = JasperFillManager.fillReport(
            jasperReports[i], criteria.getParameters(),
            new JRBeanCollectionDataSource(criteria.getResult()));
        prints.add(jasperPrint);
        sheetNames[i] = criteria.getSheetName();
      }
      
      outputStream = new ByteArrayOutputStream();
      JRXlsExporter exporter = new JRXlsExporter();
      exporter.setExporterInput(SimpleExporterInput.getInstance(prints));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
      SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
      configuration.setSheetNames(sheetNames);
      configuration.setOnePagePerSheet(Boolean.FALSE);
      configuration.setDetectCellType(Boolean.TRUE);
      configuration.setCollapseRowSpan(Boolean.FALSE);
      configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
      configuration.setWhitePageBackground(Boolean.TRUE);
      exporter.setConfiguration(configuration);
      exporter.exportReport();

      byte[] bytes = outputStream.toByteArray();
      response.setContentType(JasperReportCriteriaDTO.getContentTypeMap()
          .get(type));
      response.setCharacterEncoding(JasperReportCriteriaDTO.CHARACTER_ENCODING);
      response.setHeader(
          "Content-disposition",
          "attachment;filename="
              + URLEncoder.encode(reportFileName,
                  JasperReportCriteriaDTO.CHARACTER_ENCODING)
              + JasperReportCriteriaDTO.getReportExtNameMap()
                  .get(type));
      response.setContentLength(bytes.length);
      outputStream.close();
      oStream = response.getOutputStream();
      oStream.write(bytes, 0, bytes.length);
      oStream.flush();
      oStream.close();
      log.debug("Exit method ReportExporter.exportReportForSheets()");
      return true;
    } catch (Exception e) {
      log.error(
          "Exception: method ReportExporter.exportMainAndSubReport() - "
              + e, e);
      return false;
    } finally {
      if (oStream != null) {
        oStream.close();
      }
      if (outputStream != null) {
        outputStream.close();
      }
    }
  }

  
  /**
   * 導出可變表頭報表
   * @param criteria - JasperReport條件設定DTO
   * @param response - HttpServletResponse
   * @return 導出是否成功
   * @throws Exception
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static boolean exportChangeHeadReport(JasperReportCriteriaDTO criteria, HttpServletResponse response,int width) throws Exception {
    log.debug("Enter method ReportExporter.exportChangeHeadReport()");
    if (criteria == null) {
      return false;
    }
    ByteArrayOutputStream outputStream = null;
    ServletOutputStream oStream = null;
    try {
      JasperReport jasperReport = null;
      // 需要jrXml文檔的方式
      if (criteria.isAutoBuildJasper() == false) {
        String jrXml = criteria.getJrxmlPath() + criteria.getJrxmlName() + JasperReportCriteriaDTO.JRXML_EXT_NAME;
        InputStream inputStream = ReportExporter.class.getResourceAsStream(jrXml);
        JasperDesign design = JRXmlLoader.load(inputStream);
        JRDesignBand cTitle = (JRDesignBand) design.getTitle();
        JRElement[] es_Title = cTitle.getElements();
        es_Title[0].setWidth(width);
        
        // 編譯報表（.jrxml -> .jasper）
        jasperReport = JasperCompileManager.compileReport(design);
      } else {
        String jasper = criteria.getJrxmlPath() + criteria.getJrxmlName() + JasperReportCriteriaDTO.JASPER_EXT_NAME;
          InputStream inputStream = ReportExporter.class.getResourceAsStream(jasper);
          jasperReport = (JasperReport)JRLoader.loadObject(inputStream);
      }
      
      // 填充報表
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, criteria.getParameters(), 
            new JRBeanCollectionDataSource(criteria.getResult()));
      
      outputStream = new ByteArrayOutputStream();
      
      JRAbstractExporter exporter = null;
      if (StringUtils.hasText(criteria.getType()) == false) {
        exporter = new JRPdfExporter();
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
      } else if (criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSWORD)) {
        exporter = new JRRtfExporter();
        SimpleRtfReportConfiguration configuration = new SimpleRtfReportConfiguration();
        exporter.setConfiguration(configuration);
      } else if (criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSEXCEL)) {
        exporter = new JRXlsxExporter();
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        String sheetName = criteria.getSheetName();
        if (StringUtils.hasText(sheetName)) {
          criteria.setSheetName(sheetName);
        }
        configuration.setOnePagePerSheet(true);
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
      } else {
        exporter = new JRPdfExporter();
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
      }
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      // 如果是word的話用不同的流
      if(criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSWORD)){
        exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
      }else{
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
      }
      exporter.exportReport();
      
      byte[] bytes = outputStream.toByteArray();
      response.setContentType(JasperReportCriteriaDTO.getContentTypeMap().get(criteria.getType()));
      response.setCharacterEncoding(JasperReportCriteriaDTO.CHARACTER_ENCODING);
      response.setHeader("Content-disposition", "attachment;filename="
          + URLEncoder.encode(criteria.getReportFileName(), JasperReportCriteriaDTO.CHARACTER_ENCODING) + JasperReportCriteriaDTO.getReportExtNameMap().get(criteria.getType()));
      response.setContentLength(bytes.length);
      outputStream.close();
      
      oStream = response.getOutputStream();
      oStream.write(bytes, 0, bytes.length);
      oStream.flush();
      oStream.close();
      log.debug("Exit method ReportExporter.exportChangeHeadReport()");
      return true;
    } catch (Exception e) {
      log.error("Exception: method ReportExporter.exportChangeHeadReport() - " + e, e);
      return false;
    } finally {
      if (oStream != null) {
        oStream.close();
      }
      if (outputStream != null) {
        outputStream.close();
      }
    }
  }
  
  
  /**
   * Purpose:導出多sheet excel報表(包含子报表)
   * @author HungLi
   * @param criteria:JasperReport條件設定DTO List集合
   * @param tempFolder:占存路径
   * @return 導出是否成功
   * @throws Exception
   */
  @SuppressWarnings("resource")
  public static boolean exportReportForSheetsToFile(List<JasperReportCriteriaDTO> criterias,String tempFolder) throws Exception {
    log.debug("Enter method ReportExporter.exportReportForSheets()");
    if (CollectionUtils.isEmpty(criterias)) {
      return false;
    }
    //上傳檔案路徑
    File fileFord = new File(tempFolder);
    //判斷儲存路徑是否存在，若不存在，則重新新建
    if (!fileFord.exists() || !fileFord.isDirectory()) {
      fileFord.mkdirs();
    }
    ByteArrayOutputStream outputStream = null;
    ServletOutputStream oStream = null;
    FileOutputStream fos  = null;
    try {
      int sheetsSize = criterias.size();
      JasperReport[] jasperReports = new JasperReport[sheetsSize];
      JasperReportCriteriaDTO criteria = null;
      List<JasperPrint> prints = new ArrayList<JasperPrint>();
      JasperPrint jasperPrint = null;
      //sheet名稱
      String[] sheetNames = new String[sheetsSize];
//      String type = criterias.get(0).getType();
//      String reportFileName = criterias.get(0).getReportFileName();
      for(int i=0;i<sheetsSize;i++){
        criteria = criterias.get(i);
        // 需要jrXml文檔的方式
        if (criteria.isAutoBuildJasper() == false) {
          String jrXml = criteria.getJrxmlPath()
              + criteria.getJrxmlName()
              + JasperReportCriteriaDTO.JRXML_EXT_NAME;
          InputStream inputStream = ReportExporter.class
              .getResourceAsStream(jrXml);
          ////System.setProperty("javax.xml.parsers.SAXParserFactory","org.apache.xerces.jaxp.SAXParserFactoryImpl");
          //System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
          JasperDesign design = JRXmlLoader.load(inputStream);
          // 編譯報表（.jrxml -> .jasper）
          jasperReports[i] = JasperCompileManager.compileReport(design);
          // 編譯sub報表（.jrxml -> .jasper）
          String subjrXml = null;
          InputStream subinputStream = null;
          JasperDesign subDesign = null;
          JasperReport subJasperReport = null;
          Map<String, String> subjrXmlNames = criteria.getSubjrXmlNames();
          if(subjrXmlNames!=null){
            for(String subjrXmlName: subjrXmlNames.keySet()){
              subjrXml = criteria.getJrxmlPath() + subjrXmlName + JasperReportCriteriaDTO.JRXML_EXT_NAME;
              subinputStream = ReportExporter.class.getResourceAsStream(subjrXml);
                ////System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
                subDesign = JRXmlLoader.load(subinputStream);
                subJasperReport = JasperCompileManager.compileReport(subDesign);
                // update by riverjin 2015/08/31 
              if(criteria.getParameters() == null){
                Map<String, Object> parameters = new  HashMap<String, Object>();
                criteria.setParameters(parameters);
              }
                criteria.getParameters().put(subjrXmlNames.get(subjrXmlName).toString(), subJasperReport);
            }
          }
        }
        // 填充報表
        jasperPrint = JasperFillManager.fillReport(
            jasperReports[i], criteria.getParameters(),
            new JRBeanCollectionDataSource(criteria.getResult()));
        prints.add(jasperPrint);
        sheetNames[i] = criteria.getSheetName();
      }
      
      outputStream = new ByteArrayOutputStream();
      JRXlsExporter exporter = new JRXlsExporter();
      exporter.setExporterInput(SimpleExporterInput.getInstance(prints));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
      SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
      configuration.setSheetNames(sheetNames);
      configuration.setOnePagePerSheet(Boolean.FALSE);
      configuration.setDetectCellType(Boolean.TRUE);
      configuration.setCollapseRowSpan(Boolean.FALSE);
      configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
      configuration.setWhitePageBackground(Boolean.TRUE);
      exporter.setConfiguration(configuration);
      //隱藏邊框
      exporter.exportReport();

      byte[] bytes = outputStream.toByteArray();
         File file = new File(fileFord,tempFolder+criteria.getReportFileName()+JasperReportCriteriaDTO.REPORT_EXT_NAME_MSEXCEL);
         fos = new FileOutputStream(file);
         if(null != fos && null != bytes ){
          fos.write(bytes);
         }   
      log.debug("Exit method ReportExporter.exportReportToFile()");
      return true;
    } catch (Exception e) {
      log.error("Exception: method ReportExporter.exportMainAndSubReport() - "+ e, e);
      return false;
    } finally {
      if (oStream != null) {
        oStream.close();
      }
      if (outputStream != null) {
        outputStream.close();
      }
    }
  }
  /**
   * Purpose:包含子報表的JasperReport報表匯出
   * @param criteria:報表匯出DTO
   * @param subjrXmlNames:子報表集合
   * @param tempFolder:暫存路徑
   * @throws Exception:出錯時返回
   * @return boolean:返回結果
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static boolean exportReportToFile(JasperReportCriteriaDTO criteria,Map<String,String> subjrXmlNames,String tempFolder) throws Exception {
    log.debug("Enter method ReportExporter.exportReportToFile()");
    if (criteria == null) {
      return false;
    }
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    FileOutputStream fos  = null;
    try {
      JasperReport jasperReport = null;
      // 需要jrXml文檔的方式
      if (criteria.isAutoBuildJasper() == false) {
        //編譯main報表
        String jrXml = criteria.getJrxmlPath() + criteria.getJrxmlName() + JasperReportCriteriaDTO.JRXML_EXT_NAME;
        InputStream inputStream = ReportExporter.class.getResourceAsStream(jrXml);
        // //System.setProperty("javax.xml.parsers.SAXParserFactory","org.apache.xerces.jaxp.SAXParserFactoryImpl");
         //System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
        JasperDesign design = JRXmlLoader.load(inputStream);
        jasperReport = JasperCompileManager.compileReport(design);
        // 編譯sub報表（.jrxml -> .jasper）
        String subjrXml = null;
        InputStream subinputStream = null;
        JasperDesign subDesign = null;
        JasperReport subJasperReport = null;
        for(String subjrXmlName: subjrXmlNames.keySet()){
          subjrXml = criteria.getJrxmlPath() + subjrXmlName + JasperReportCriteriaDTO.JRXML_EXT_NAME;
          subinputStream = ReportExporter.class.getResourceAsStream(subjrXml);
            //System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
            subDesign = JRXmlLoader.load(subinputStream);
            subJasperReport = JasperCompileManager.compileReport(subDesign);
          criteria.getParameters().put(subjrXmlNames.get(subjrXmlName).toString(), subJasperReport);
        }
      }
      
      // 填充報表
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, criteria.getParameters(), 
            new JRBeanCollectionDataSource(criteria.getResult()));
      
      JRAbstractExporter exporter = null;
      String reportExtName = "";
      if (StringUtils.hasText(criteria.getType()) == false) {
        exporter = new JRPdfExporter();
        reportExtName = JasperReportCriteriaDTO.REPORT_EXT_NAME_PDF;
      } else if (criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSWORD)) {
        exporter = new JRRtfExporter();
        reportExtName = JasperReportCriteriaDTO.REPORT_EXT_NAME_MSWORD;
      } else if (criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSEXCEL)) {
        exporter = new JRXlsExporter();
        reportExtName = JasperReportCriteriaDTO.REPORT_EXT_NAME_MSEXCEL;
      }else if (criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_CSV)){
        exporter = new JRCsvExporter();
        reportExtName = JasperReportCriteriaDTO.REPORT_EXT_NAME_CSV;
      } else {
        exporter = new JRPdfExporter();
        reportExtName = JasperReportCriteriaDTO.REPORT_EXT_NAME_PDF;
      }
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      // 如果是word的話用不同的流
      if(criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSWORD)){
        exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
      }else{
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
      }
      exporter.exportReport();
      
      byte[] bytes = null;      
      //輸出保教到指定目錄
      bytes = outputStream.toByteArray();     

      log.debug("Report File="+tempFolder+criteria.getReportFileName()+reportExtName);
      File file = new File(tempFolder + tempFolder+criteria.getReportFileName()+reportExtName);
      fos = new FileOutputStream(file);
      if( null != bytes ){
        fos.write(bytes);
      }
      log.debug("Exit method ReportExporter.exportReportToFile()");     
      return true;
    } catch (Exception e) {
      log.error("Exception: method ReportExporter.exportReportToFile() - " + e, e);
      return false;
    } finally {
      if (fos != null) {
        fos.close();
      }   
      outputStream.close();     
    }
  }
  /**
   * 導出報表
   * @param criteria - JasperReport條件設定DTO
   * @param response - HttpServletResponse
   * @return 導出是否成功
   * @throws Exception
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static boolean exportReport(JasperReportCriteriaDTO criteria, HttpServletResponse response) throws Exception {
    log.debug("Enter method ReportExporter.exportReport()");
    
    if (criteria == null) {
      return false;
    }
    
    ByteArrayOutputStream outputStream = null;
    ServletOutputStream oStream = null;
    try {
      JasperReport jasperReport = null;
      // 需要jrXml文檔的方式
      if (criteria.isAutoBuildJasper() == false) {
        String jrXml = criteria.getJrxmlPath() + criteria.getJrxmlName() + JasperReportCriteriaDTO.JRXML_EXT_NAME;
        InputStream inputStream = ReportExporter.class.getResourceAsStream(jrXml);
        JasperDesign design = JRXmlLoader.load(inputStream);
        // 編譯報表（.jrxml -> .jasper）
        jasperReport = JasperCompileManager.compileReport(design);
      } else {
        String jasper = criteria.getJrxmlPath() + criteria.getJrxmlName() + JasperReportCriteriaDTO.JASPER_EXT_NAME;
          InputStream inputStream = ReportExporter.class.getResourceAsStream(jasper);
          jasperReport = (JasperReport)JRLoader.loadObject(inputStream);
      }
      
      // 填充報表
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, criteria.getParameters(), 
            new JRBeanCollectionDataSource(criteria.getResult()));
      
      outputStream = new ByteArrayOutputStream();
      
      JRAbstractExporter exporter = null;
      if (StringUtils.hasText(criteria.getType()) == false) {
        exporter = new JRPdfExporter();
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
      } else if (criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSWORD)) {
        exporter = new JRRtfExporter();
        SimpleRtfReportConfiguration configuration = new SimpleRtfReportConfiguration();
        exporter.setConfiguration(configuration);
      } else if (criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSEXCEL)) {
        exporter = new JRXlsExporter();
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        String sheetName = criteria.getSheetName();
        if (StringUtils.hasText(sheetName)) {
          criteria.setSheetName(sheetName);
        }
        configuration.setOnePagePerSheet(true);
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
      } else {
        exporter = new JRPdfExporter();
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
      }
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      // 如果是word的話用不同的流
      if(criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSWORD)){
        exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
      }else{
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
      }
      
      exporter.exportReport();
      
      byte[] bytes = outputStream.toByteArray();
      response.setContentType(JasperReportCriteriaDTO.getContentTypeMap().get(criteria.getType()));
      response.setCharacterEncoding(JasperReportCriteriaDTO.CHARACTER_ENCODING);
      response.setHeader("Content-disposition", "attachment;filename="
          + URLEncoder.encode(criteria.getReportFileName(), JasperReportCriteriaDTO.CHARACTER_ENCODING) + JasperReportCriteriaDTO.getReportExtNameMap().get(criteria.getType()));
      response.setContentLength(bytes.length);
      outputStream.close();
      
      oStream = response.getOutputStream();
      oStream.write(bytes, 0, bytes.length);
      oStream.flush();
      oStream.close();
      log.debug("Exit method ReportExporter.exportReport()");
      return true;
    } catch (Exception e) {
      log.error("Exception: method ReportExporter.exportReport() - " + e, e);
      return false;
    } finally {
      if (oStream != null) {
        oStream.close();
      }
      if (outputStream != null) {
        outputStream.close();
      }
    }
  }
  /**
   * 導出報表(包含子报表)
   * 
   * @param criteria
   *            - JasperReport條件設定DTO
   * @param response
   *            - HttpServletResponse
   * @return 導出是否成功
   * @throws Exception
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static boolean exportMainAndSubReport(
      JasperReportCriteriaDTO criteria,
      Map<String, String> subjrXmlNames, HttpServletResponse response)
      throws Exception {
    log.debug("Enter method ReportExporter.exportMainAndSubReport()");

    if (criteria == null) {
      return false;
    }
    ByteArrayOutputStream outputStream = null;
    ServletOutputStream oStream = null;
    try {
      JasperReport jasperReport = null;
      // 需要jrXml文檔的方式
      if (criteria.isAutoBuildJasper() == false) {
        String jrXml = criteria.getJrxmlPath()
            + criteria.getJrxmlName()
            + JasperReportCriteriaDTO.JRXML_EXT_NAME;
        InputStream inputStream = ReportExporter.class
            .getResourceAsStream(jrXml);
        ////System.setProperty("javax.xml.parsers.SAXParserFactory","org.apache.xerces.jaxp.SAXParserFactoryImpl");
        //System.setProperty("javax.xml.parsers.SAXParserFactory",
        //  "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
        JasperDesign design = JRXmlLoader.load(inputStream);
        // 編譯報表（.jrxml -> .jasper）
        jasperReport = JasperCompileManager.compileReport(design);
        // 編譯sub報表（.jrxml -> .jasper）
        String subjrXml = null;
        InputStream subinputStream = null;
        JasperDesign subDesign = null;
        JasperReport subJasperReport = null;
        for(String subjrXmlName: subjrXmlNames.keySet()){
          subjrXml = criteria.getJrxmlPath() + subjrXmlName + JasperReportCriteriaDTO.JRXML_EXT_NAME;
          subinputStream = ReportExporter.class.getResourceAsStream(subjrXml);
          //  //System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
            subDesign = JRXmlLoader.load(subinputStream);
            subJasperReport = JasperCompileManager.compileReport(subDesign);
          criteria.getParameters().put(subjrXmlNames.get(subjrXmlName).toString(), subJasperReport);
        }
      }

      // 填充報表
      JasperPrint jasperPrint = JasperFillManager.fillReport(
          jasperReport, criteria.getParameters(),
          new JRBeanCollectionDataSource(criteria.getResult()));

      outputStream = new ByteArrayOutputStream();

      JRAbstractExporter exporter = null;
      if (StringUtils.hasText(criteria.getType()) == false) {
        exporter = new JRPdfExporter();
      } else if (criteria.getType().equals(
          JasperReportCriteriaDTO.REPORT_TYPE_MSWORD)) {
        exporter = new JRRtfExporter();
      } else if (criteria.getType().equals(
          JasperReportCriteriaDTO.REPORT_TYPE_MSEXCEL)) {
        exporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
      } else {
        exporter = new JRPdfExporter();
      }
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      // 如果是word的話用不同的流
      if(criteria.getType().equals(JasperReportCriteriaDTO.REPORT_TYPE_MSWORD)){
        exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
      }else{
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
      }
      exporter.exportReport();

      byte[] bytes = outputStream.toByteArray();
      response.setContentType(JasperReportCriteriaDTO.RESPONSE_CONTENT_TYPE_2007_MSEXCEL);
      response.setCharacterEncoding(JasperReportCriteriaDTO.CHARACTER_ENCODING);
      response.setHeader(
          "Content-disposition",
          "attachment;filename="
              + URLEncoder.encode(criteria.getReportFileName(),
                  JasperReportCriteriaDTO.CHARACTER_ENCODING)
              + JasperReportCriteriaDTO.REPORT_EXT_NAME_2007_MSEXCEL);
      response.setContentLength(bytes.length);
      outputStream.close();
      oStream = response.getOutputStream();
      oStream.write(bytes, 0, bytes.length);
      oStream.flush();
      oStream.close();
      log.debug("Exit method ReportExporter.exportMainAndSubReport()");
      return true;
    } catch (Exception e) {
      log.error(
          "Exception: method ReportExporter.exportMainAndSubReport() - "
              + e, e);
      return false;
    } finally {
      if (oStream != null) {
        oStream.close();
      }
      if (outputStream != null) {
        outputStream.close();
      }
    }
  }

  /*public static void main(String[] args){

    try {
      String jrXml = "/com/fet/cms/common/report/jrxml/CMS_CNTR_CONTENT" + JasperReportCriteriaDTO.JRXML_EXT_NAME;
      InputStream inputStream = ReportExporter.class.getResourceAsStream(jrXml);
      ////System.setProperty("javax.xml.parsers.SAXParserFactory","org.apache.xerces.jaxp.SAXParserFactoryImpl");
       //System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
      JasperDesign design = JRXmlLoader.load(inputStream);
      // 編譯報表（.jrxml -> .jasper）
      JasperCompileManager.compileReportToFile(design, "E:\\JAVA\\CMS_CNTR_CONTENT.jasper");
    } catch (JRException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();  
    }
  
  }*/
}
