package com.gun.server.controller.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

public class CsvExportManager {

	public static void exportCSV(List<String[]> conditionList, List<Object[]> srcList, String outPathFileName) throws Exception {
		OutputStream outs = null;
		try {
			outs = new FileOutputStream(outPathFileName);
			outs.write(239);
			outs.write(187);
			outs.write(191);
			CSVWriter writer = new CSVWriter(new OutputStreamWriter(outs, Charset.forName("UTF8")), ',', '\"');

			if (conditionList != null && conditionList.size() > 0) {
				writer.writeAll(conditionList);
				writer.writeNext(new String[] { "" });
			} else {
				writer.writeNext(new String[] { "查詢條件", "無" });
			}

			List<String[]> outList = new ArrayList<String[]>();
			for (int j = 0; j < srcList.size(); j++) {
				Object[] srcRow = (Object[]) srcList.get(j);
				String[] outRow = new String[srcRow.length];
				for (int i = 0; i < srcRow.length; i++) {
					if (srcRow[i] != null) {
						outRow[i] = srcRow[i].toString();
					} else {
						outRow[i] = "";
					}
				}
				outList.add(outRow);
			}
			writer.writeAll(outList);
			outs.close();
			writer.close();
		} catch (Exception e) {
			throw e;
		}finally{
			if(outs != null)outs.close();
		}
	}

	public static void exportCSV(List<String[]> conditionList, String[] header, List<Object[]> srcList, String outPathFileName) throws Exception {
		OutputStream outs = null;
		try {
			outs = new FileOutputStream(outPathFileName);
			outs.write(239);
			outs.write(187);
			outs.write(191);
			CSVWriter writer = new CSVWriter(new OutputStreamWriter(outs, Charset.forName("UTF8")), ',', '\"');
			// 查詢條件
			if (conditionList != null && conditionList.size() > 0) {
				writer.writeAll(conditionList);
				writer.writeNext(new String[] { "" });
			} else {
				writer.writeNext(new String[] { "查詢條件", "無" });
			}
			// 表頭
			if (header != null && header.length > 0) {
				writer.writeNext(header);
			} else {
				writer.writeNext(new String[] { "表頭", "無" });
			}
			// 表身
			List<String[]> outList = new ArrayList<String[]>();
			for (int j = 0; j < srcList.size(); j++) {
				Object[] srcRow = (Object[]) srcList.get(j);
				String[] outRow = new String[srcRow.length];
				for (int i = 0; i < srcRow.length; i++) {
					if (srcRow[i] != null) {
						outRow[i] = srcRow[i].toString();
					} else {
						outRow[i] = "";
					}
				}
				outList.add(outRow);
			}
			writer.writeAll(outList);
			outs.close();
			writer.close();
		} catch (Exception e) {
			throw e;
		}finally{
			if(outs != null)outs.close();
		}
	}
}
