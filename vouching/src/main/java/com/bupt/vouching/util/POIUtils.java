package com.bupt.vouching.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bupt.vouching.frame.Consts;
import com.bupt.vouching.mapper.ClassMapper;
import com.bupt.vouching.type.base.TemplateType;
import com.bupt.vouching.type.excel.UserTemplate;

/**
 * POI工具类
 * @author Hogan
 *
 */
@Component("poiUtils")
@Scope("singleton")
public class POIUtils {
	
	@Resource
	private ClassMapper classMapper;
	
	private Logger log = Logger.getLogger(getClass());
	
	private String EXCEL_2007 = ".xlsx";
	
	/**
	 * 读取EXCEL文件
	 * 
	 * @param file
	 * @param bean
	 * @return
	 */
	public <T> List<T> read(MultipartFile file, TemplateType templateType, Class<T> beanClass){
		if (beanClass != null) {
			String fileName = file.getOriginalFilename();
			List<T> result = new ArrayList<T>();
			int rowCount = 0;
			Workbook workbook = null;
			try {
				if (fileName.contains(EXCEL_2007)) {
					workbook = new XSSFWorkbook(file.getInputStream());
				} else {
					workbook = new HSSFWorkbook(file.getInputStream());
				}
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rows = sheet.rowIterator();
				while (rows.hasNext()) {
					if(rowCount++ != 0){
						Row row = rows.next();
						Iterator<Cell> cells = row.cellIterator();
						T bean = cellToBean(cells, templateType, beanClass);
						result.add(bean);
					} else {
						rows.next();
					}
				}
			} catch (Exception e) {
				log.error("文件打开失败!",e);
				return null;
			} finally {
				try {
					if (workbook != null) {
						workbook.close();
					}
				} catch (IOException e) {
					log.error("Excel关闭失败!",e);
					return null;
				}
			}
			return result;
		} else {
			return null;
		}
	}
	
	/**
	 * 读取EXCEL文件
	 * 
	 * @param file
	 * @param bean
	 * @return
	 */
	public <T> List<T> read(File file, TemplateType templateType, Class<T> beanClass){
		if (beanClass != null) {
			String fileName = file.getName();
			List<T> result = new ArrayList<T>();
			int rowCount = 0;
			Workbook workbook = null;
			try {
				if (fileName.contains(EXCEL_2007)) {
					workbook = new XSSFWorkbook(new FileInputStream(file));
				} else {
					workbook = new HSSFWorkbook(new FileInputStream(file));
				}
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rows = sheet.rowIterator();
				while (rows.hasNext()) {
					if(rowCount++ != 0){
						Row row = rows.next();
						Iterator<Cell> cells = row.cellIterator();
						T bean = cellToBean(cells, templateType, beanClass);
						result.add(bean);
					} else {
						rows.next();
					}
				}
			} catch (Exception e) {
				log.error("文件打开失败!",e);
				return null;
			} finally {
				try {
					if (workbook != null) {
						workbook.close();
					}
				} catch (IOException e) {
					log.error("Excel关闭失败!",e);
					return null;
				}
			}
			return result;
		} else {
			return null;
		}
	}
	
	/**
	 * 将单元格的值映射到bean中
	 * 
	 * @param cells
	 * @param templateType
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	private <T> T cellToBean(Iterator<Cell> cells, TemplateType templateType, Class<T> beanClass) throws Exception{
		T bean = beanClass.newInstance();
		int count = 1;
		while (cells.hasNext()) {
			valueToBean(templateType.byId(count++), getCellValue(cells.next()), bean);
		}
		return bean;
	}
	
	/**
	 * 获取单元格内的值
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellValue(Cell cell) {
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				return String.valueOf(cell.getNumericCellValue());
			case HSSFCell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			default :
				return null;
		}
	}
	
	/**
	 * 将单元格的值封装到bean中
	 * 
	 * @param templateType
	 * @param value
	 * @param bean
	 * @throws Exception 
	 */
	public void valueToBean(TemplateType templateType, String value, Object bean) throws Exception {
		Method[] methods = bean.getClass().getDeclaredMethods();
		String targetMethodName = "set" + templateType.getDescription();
		for (Method method : methods) {
			String methodName = method.getName();
			if (targetMethodName.equals(methodName)) {
				Object param = null;
				Class<?>[] types = method.getParameterTypes();
				if (types[0].isInstance(new String())) {
					param = value;
				} else if (types[0].isInstance(new Integer(0))) {
					if (templateType == UserTemplate.SEX) {
						param = "男".equals(value) ? 1 : 0;
					} else if (templateType == UserTemplate.CLASS){
						com.bupt.vouching.bean.Class clasz = classMapper.findCLassByName(value);
						if(clasz != null){
							param = clasz.getClassId();
						} else {
							param = Consts.DEFAULT_CLASS_ID;
						}
					} else {
						param = (int) Double.parseDouble(value);
					}
				}
				method.invoke(bean, param);
				break;
			}
		}
	}
}
