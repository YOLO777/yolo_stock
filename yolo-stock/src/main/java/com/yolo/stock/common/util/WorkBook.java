package com.yolo.stock.common.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class WorkBook {
    /**
     * 创建excel头部信息
     *
     * @param columnNames 列明
     * @param sheetName   sheet名称
     * @return
     */
    public static XSSFWorkbook createWorkBook(String[] columnNames, String sheetName) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);
        //创建第一行数据
        XSSFRow row = sheet.createRow(0);
        row.setHeight((short) 0x280);
        // 样式
        XSSFCellStyle style = wb.createCellStyle();
        //上下左右边框和居中对齐
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 0x10);
        style.setFont(font);
        //设置第一行数据
        for (int i = 0; i < columnNames.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(style);
            sheet.setColumnWidth(i, 6200);
        }
        return wb;
    }
}
