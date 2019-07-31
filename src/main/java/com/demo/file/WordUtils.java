package com.demo.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Word操作类
 * 
 * @author chenjian
 * @createDate 2019-01-08
 */
public class WordUtils {

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @return JSON格式信息
	 */
	public static JSONObject read(String filePath) {
		String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);

		// 读取文件
		if (fileType.equalsIgnoreCase("doc")) {
			return readDoc(filePath);
		} else {
			return readDocx(filePath);
		}
	}

	/**
	 * 读取Doc文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return JSON格式信息
	 */
	private static JSONObject readDoc(String filePath) {
		JSONObject docObject = new JSONObject();

		FileInputStream fis = null;
		HWPFDocument doc = null;
		try {
			fis = new FileInputStream(filePath);
			doc = new HWPFDocument(fis);
			Range range = doc.getRange();

			// 读取内容文本
			String content = range.text();

			// 读取内容图片
			JSONArray picArray = new JSONArray();
			List<Picture> pics = doc.getPicturesTable().getAllPictures();
			for (Picture pic : pics) {
				picArray.add(pic.getContent());
			}

			// 读取内容表格
			JSONArray tableArray = new JSONArray();
			TableIterator iterator = new TableIterator(range);
			while (iterator.hasNext()) {
				Table table = iterator.next();
				JSONObject tableObject = new JSONObject();

				// 获取行的数目
				for (int i = 0; i < table.numRows(); i++) {
					// 获取每一行
					TableRow row = table.getRow(i);
					JSONArray rowArray = new JSONArray();

					for (int j = 0; j < row.numCells(); j++) {
						// 获取每一列
						TableCell cell = row.getCell(j);

						rowArray.add(cell.text().trim());
					}

					tableObject.put(Integer.valueOf(i).toString(), rowArray);
				}

				tableArray.add(tableObject);
			}

			docObject.put("content", content);
			docObject.put("pictures", picArray);
			docObject.put("tables", tableArray);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}

				if (doc != null) {
					doc.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return docObject;
	}

	/**
	 * 读取Docx文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return JSON格式信息
	 */
	private static JSONObject readDocx(String filePath) {
		JSONObject docxObject = new JSONObject();

		FileInputStream fis = null;
		XWPFWordExtractor extractor = null;
		try {
			fis = new FileInputStream(filePath);
			XWPFDocument docx = new XWPFDocument(fis);
			extractor = new XWPFWordExtractor(docx);

			// 读取内容文本
			String content = extractor.getText();

			// 读取内容图片
			JSONArray picArray = new JSONArray();
			List<XWPFPictureData> pics = docx.getAllPictures();
			for (XWPFPictureData pic : pics) {
				picArray.add(pic.getData());
			}

			// 读取内容表格
			JSONArray tableArray = new JSONArray();
			List<XWPFTable> tabs = docx.getTables();
			for (XWPFTable table : tabs) {
				JSONObject tableObject = new JSONObject();

				// 获取表格的行
				List<XWPFTableRow> rows = table.getRows();
				int rowIndex = 0;
				for (XWPFTableRow row : rows) {
					// 获取表格的每个单元格
					List<XWPFTableCell> tableCells = row.getTableCells();
					JSONArray rowArray = new JSONArray();

					for (XWPFTableCell cell : tableCells) {
						// 获取单元格的内容
						rowArray.add(cell.getText().trim());
					}

					tableObject.put(Integer.valueOf(rowIndex).toString(),
							rowArray);
					rowIndex += 1;
				}

				rowIndex = 0;
				tableArray.add(tableObject);
			}

			docxObject.put("content", content);
			docxObject.put("pictures", picArray);
			docxObject.put("tables", tableArray);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}

				if (extractor != null) {
					extractor.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return docxObject;
	}
}