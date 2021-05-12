package com.example.practice.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.example.practice.entity.Product;

@Component
public class ExportToExcelUtility {
	
	public ByteArrayInputStream productToExcel(List<Product> productList)
	{
		ByteArrayOutputStream outputStream = null;
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Products");
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        // Creating header
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Product Name");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Description");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(2);
	        cell.setCellValue("Price");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(3);
	        cell.setCellValue("Quantity");
	        cell.setCellStyle(headerCellStyle);
	      
	        cell = row.createCell(4);
	        cell.setCellValue("Category Name");
	        cell.setCellStyle(headerCellStyle);
	        
	        // Creating data rows for each customer
	        for(int i = 0; i < productList.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(productList.get(i).getProductName());
	        	dataRow.createCell(1).setCellValue(productList.get(i).getDescription());
	        	dataRow.createCell(2).setCellValue(productList.get(i).getPrice());
	        	dataRow.createCell(3).setCellValue(productList.get(i).getQuantity());
	            dataRow.createCell(4).setCellValue(productList.get(i).getCategory().getCategoryName());
	        }
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        
	        outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}finally {
			try {
				if(outputStream != null) {
				outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}