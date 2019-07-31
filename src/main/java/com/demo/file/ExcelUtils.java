package com.demo.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Excel操作类
 * 
 * @author chejian
 * @createDate 2019-01-07
 */
public class ExcelUtils {

	private static Workbook wb;

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param sheetIndex
	 *            工作表索引
	 * @return
	 */
	public static JSONArray read(String filePath, int sheetIndex) {
		JSONArray xlsArray = new JSONArray();
		String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);

		InputStream is = null;
		try {
			is = new FileInputStream(filePath);

			// 读取工作簿
			if (fileType.equalsIgnoreCase("xls")) {
				wb = new HSSFWorkbook(is);
			} else if (fileType.equalsIgnoreCase("xlsx")) {
				wb = new XSSFWorkbook(is);
			} else {
				return null;
			}

			// 读取指定索引工作表
			Sheet sheet = wb.getSheetAt(sheetIndex);

			if (sheet != null) {
				Iterator<Row> rows = sheet.rowIterator();
				int rowIndex = 0;
				int cellIndex = 0;
				List<String> headerList = new ArrayList<String>();

				while (rows.hasNext()) {
					Row row = rows.next();
					Iterator<Cell> cells = row.cellIterator();
					JSONObject rowObject = new JSONObject();

					while (cells.hasNext()) {
						Cell cell = cells.next();

						// 判断是否是头
						if (rowIndex == 0) {
							headerList.add(getCellValue(cell).toString());
						} else {
							rowObject.put(headerList.get(cellIndex),
									getCellValue(cell));
						}

						cellIndex += 1;
					}

					rowIndex += 1;
					cellIndex = 0;

					if (rowObject.size() > 0) {
						xlsArray.add(rowObject);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return xlsArray;
	}

	/**
	 * 写文件内容
	 * 
	 * @param headerList
	 *            头集合
	 * @param list
	 *            内容集合(集合包括List<Object>或Map<String, Object>)
	 * @param sheetIndex
	 *            工作表索引
	 * @param sheetName
	 *            工作表名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static InputStream write(List<String> headerList, List<Object> list,
			int sheetIndex, String sheetName) {
		InputStream is = null;
		ByteArrayOutputStream bos = null;

		try {
			Row row = null;
			Cell cell = null;
			int rowIndex = 0;
			int cellIndex = 0;

			// 创建工作簿
			wb = new HSSFWorkbook();

			// 创建指定索引工作表
			Sheet sheet = wb.createSheet();
			wb.setSheetName(sheetIndex, sheetName);

			// 设置头
			if (headerList != null && headerList.size() > 0) {
				// 字体(粗体)
				Font font = wb.createFont();
				font.setBold(true);
				font.setFontName("黑体");
				font.setFontHeightInPoints((short) 16);// 设置字体大小

				// 设置头样式
				CellStyle styleHeader = wb.createCellStyle();
				styleHeader.setFont(font);
				styleHeader.setBorderBottom(BorderStyle.THIN); // 下边框
				styleHeader.setBorderLeft(BorderStyle.THIN);// 左边框
				styleHeader.setBorderTop(BorderStyle.THIN);// 上边框
				styleHeader.setBorderRight(BorderStyle.THIN);// 右边框

				row = sheet.createRow(rowIndex);
				for (int i = 0; i < headerList.size(); i++) {
					cell = row.createCell(i);
					cell.setCellStyle(styleHeader);
					cell.setCellValue(headerList.get(i));
				}

				rowIndex += 1;
			}

			// 设置内容
			if (list != null && list.size() > 0) {
				// 设置内容样式
				CellStyle styleContent = wb.createCellStyle();
				styleContent.setBorderBottom(BorderStyle.THIN); // 下边框
				styleContent.setBorderLeft(BorderStyle.THIN);// 左边框
				styleContent.setBorderTop(BorderStyle.THIN);// 上边框
				styleContent.setBorderRight(BorderStyle.THIN);// 右边框

				for (int i = 0; i < list.size(); i++) {
					row = sheet.createRow(i + rowIndex);
					Object rowObject = list.get(i);

					if (rowObject instanceof List) {
						List<Object> datas = (List<Object>) rowObject;

						for (int j = 0; j < datas.size(); j++) {
							Object object = datas.get(j);
							if (object instanceof List) {

							}

							cell = row.createCell(j);
							cell.setCellStyle(styleContent);
							if (object instanceof String) {
								cell.setCellValue(object.toString());
							} else if (object instanceof BigDecimal) {
								cell.setCellValue(new BigDecimal(object
										.toString()).doubleValue());
							} else if (object instanceof Boolean) {
								cell.setCellValue(Boolean.valueOf(object
										.toString()));
							} else {
								cell.setCellValue(object.toString());
							}
						}
					} else if (rowObject instanceof Map) {
						Map<String, Object> map = (Map<String, Object>) rowObject;

						for (Entry<String, Object> entry : map.entrySet()) {
							Object object = entry.getValue();

							cell = row.createCell(cellIndex);
							cell.setCellStyle(styleContent);
							if (object instanceof String) {
								cell.setCellValue(object.toString());
							} else if (object instanceof BigDecimal) {
								cell.setCellValue(new BigDecimal(object
										.toString()).doubleValue());
							} else if (object instanceof Boolean) {
								cell.setCellValue(Boolean.valueOf(object
										.toString()));
							} else {
								cell.setCellValue(object.toString());
							}

							cellIndex += 1;
						}
					}

					cellIndex = 0;
				}
			}

			bos = new ByteArrayOutputStream();
			wb.write(bos);
			byte[] barray = bos.toByteArray();
			is = new ByteArrayInputStream(barray);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return is;
	}

	/**
	 * 写文件内容
	 * 
	 * @param headerMap
	 *            头集合
	 * @param list
	 *            内容集合
	 * @param sheetIndex
	 *            工作表索引
	 * @param sheetName
	 *            工作表名称
	 * @return
	 */
	public static InputStream write(Map<String, String> headerMap,
			List<Map<String, Object>> list, int sheetIndex, String sheetName) {
		InputStream is = null;
		ByteArrayOutputStream bos = null;

		try {
			Row row = null;
			Cell cell = null;
			int rowIndex = 0;
			int cellIndex = 0;

			// 创建工作簿
			wb = new HSSFWorkbook();

			// 创建指定索引工作表
			Sheet sheet = wb.createSheet();
			wb.setSheetName(sheetIndex, sheetName);

			// 判断头和内容
			if (headerMap != null && headerMap.size() > 0 && list != null
					&& list.size() > 0) {
				List<String> headList = new ArrayList<String>();

				// 设置头
				// 字体(粗体)
				Font font = wb.createFont();
				font.setBold(true);
				font.setFontName("黑体");
				font.setFontHeightInPoints((short) 16);// 设置字体大小

				// 设置头样式
				CellStyle styleHeader = wb.createCellStyle();
				styleHeader.setFont(font);
				styleHeader.setBorderBottom(BorderStyle.THIN); // 下边框
				styleHeader.setBorderLeft(BorderStyle.THIN);// 左边框
				styleHeader.setBorderTop(BorderStyle.THIN);// 上边框
				styleHeader.setBorderRight(BorderStyle.THIN);// 右边框

				row = sheet.createRow(rowIndex);
				for (Entry<String, String> entry : headerMap.entrySet()) {
					cell = row.createCell(cellIndex);
					cell.setCellStyle(styleHeader);
					cell.setCellValue(entry.getValue());

					cellIndex += 1;
					headList.add(entry.getKey());
				}

				rowIndex += 1;

				// 设置内容
				// 设置内容样式
				CellStyle styleContent = wb.createCellStyle();
				styleContent.setBorderBottom(BorderStyle.THIN); // 下边框
				styleContent.setBorderLeft(BorderStyle.THIN);// 左边框
				styleContent.setBorderTop(BorderStyle.THIN);// 上边框
				styleContent.setBorderRight(BorderStyle.THIN);// 右边框

				for (int i = 0; i < list.size(); i++) {
					row = sheet.createRow(i + rowIndex);
					Map<String, Object> map = list.get(i);

					for (int j = 0; j < headList.size(); j++) {
						Object object = map.get(headList.get(j));

						cell = row.createCell(j);
						cell.setCellStyle(styleContent);
						if (object instanceof String) {
							cell.setCellValue(object.toString());
						} else if (object instanceof BigDecimal) {
							cell.setCellValue(new BigDecimal(object.toString())
									.doubleValue());
						} else if (object instanceof Boolean) {
							cell.setCellValue(Boolean.valueOf(object.toString()));
						} else {
							cell.setCellValue(object.toString());
						}
					}
				}
			}

			bos = new ByteArrayOutputStream();
			wb.write(bos);
			byte[] barray = bos.toByteArray();
			is = new ByteArrayInputStream(barray);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return is;
	}

	/**
	 * 读取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	private static Object getCellValue(Cell cell) {
		Object val = "";

		switch (cell.getCellType()) {
		case STRING:
			val = cell.getStringCellValue();
			break;
		case NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				val = format.format(HSSFDateUtil.getJavaDate(cell
						.getNumericCellValue()));
			} else {
				val = new BigDecimal(cell.getNumericCellValue());
			}
			break;
		case BOOLEAN:
			val = cell.getBooleanCellValue();
			break;
		case BLANK:
			break;
		case FORMULA:
			val = cell.getCellFormula();
			break;
		default:
			break;
		}

		return val;
	}
}