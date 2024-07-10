package org.example.objects;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MergeTables {
    public static void main(String[] args) throws IOException {
        String file1 = "D:\\excelWork\\table1.xlsx";
        String file2 = "D:\\excelWork\\table2.xlsx";
        String file3 = "D:\\excelWork\\table3.xlsx";

        String outputFile = "D:\\excelWork\\merged_table.xlsx";

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Merged tables");

        copyHeader(file1, sheet, 0, workbook);
        copySheet(file1, sheet, getRowCount(sheet), workbook);
        copySheet(file2, sheet, getRowCount(sheet), workbook);
        copySheet(file3, sheet, getRowCount(sheet), workbook);

        workbook.write(new FileOutputStream(outputFile));
        workbook.close();
    }

    private static void copyHeader(String sourceFile, XSSFSheet sheet, int startRow, XSSFWorkbook targetWorkBook) throws IOException {
        XSSFWorkbook sourceWorkBook = new XSSFWorkbook(new FileInputStream(sourceFile));
        XSSFSheet sourceSheet = sourceWorkBook.getSheetAt(0);

        XSSFRow headerRow = sourceSheet.getRow(0);
        XSSFRow newHeaderRow = sheet.createRow(startRow);
        copyRow(headerRow, newHeaderRow, targetWorkBook);
        sourceWorkBook.close();

    }

    private static void copyRow(XSSFRow sourceRow, XSSFRow targetRow, XSSFWorkbook targetWorkBook){
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            XSSFCell sourceCell = sourceRow.getCell(i);
            XSSFCell targetCell = targetRow.createCell(i);
            switch (sourceCell.getCellType()) {
                case XSSFCell.CELL_TYPE_STRING -> targetCell.setCellValue(sourceCell.getStringCellValue());
                case XSSFCell.CELL_TYPE_NUMERIC -> targetCell.setCellValue(sourceCell.getNumericCellValue());
                case XSSFCell.CELL_TYPE_BOOLEAN -> targetCell.setCellValue(sourceCell.getBooleanCellValue());
                case XSSFCell.CELL_TYPE_FORMULA -> targetCell.setCellFormula(sourceCell.getCellFormula());
                case XSSFCell.CELL_TYPE_BLANK -> targetCell.setCellValue("");//???
                default -> System.out.println("cannot understand the type of cell");
            }
            XSSFCellStyle sourceCellStyle = sourceCell.getCellStyle();
            XSSFCellStyle newCellStyle = targetWorkBook.createCellStyle();
            copyCellStyle(sourceCellStyle, newCellStyle);
            targetCell.setCellStyle(newCellStyle);
        }
    }
    private static void copySheet(String sourceFile, XSSFSheet sheet, int startRow, XSSFWorkbook targetWorkBook) throws IOException {
        XSSFWorkbook sourceWorkBook = new XSSFWorkbook(new FileInputStream(sourceFile));
//      Получение листа первого в файле
        XSSFSheet sourceSheet = sourceWorkBook.getSheetAt(0);
        for (int i = 1; i < getRowCount(sourceSheet); i++) {
            XSSFRow sourceRow = sourceSheet.getRow(i);
            XSSFRow targetRow = sheet.createRow(startRow + i - 1);//??
            copyRow(sourceRow, targetRow, targetWorkBook);
        }
        sourceWorkBook.close();
    }

    private static void copyCellStyle(XSSFCellStyle sourceCellStyle, XSSFCellStyle newCellStyle) {
        newCellStyle.setAlignment(sourceCellStyle.getAlignment());
        newCellStyle.setBorderBottom(sourceCellStyle.getBorderBottom());
        newCellStyle.setBorderLeft(sourceCellStyle.getBorderLeft());
        newCellStyle.setBorderRight(sourceCellStyle.getBorderRight());
        newCellStyle.setBorderTop(sourceCellStyle.getBorderTop());
        newCellStyle.setFillBackgroundColor(sourceCellStyle.getFillBackgroundColor());
        newCellStyle.setFillForegroundColor(sourceCellStyle.getFillForegroundColor());
        newCellStyle.setFillPattern(sourceCellStyle.getFillPattern());
//        newCellStyle.setFont(sourceCellStyle);
        newCellStyle.setDataFormat(sourceCellStyle.getDataFormat());
        newCellStyle.setWrapText(sourceCellStyle.getWrapText());
        // Копирование настроек границ
        newCellStyle.setBottomBorderColor(sourceCellStyle.getBottomBorderColor());
        newCellStyle.setLeftBorderColor(sourceCellStyle.getLeftBorderColor());
        newCellStyle.setRightBorderColor(sourceCellStyle.getRightBorderColor());
        newCellStyle.setTopBorderColor(sourceCellStyle.getTopBorderColor());

        // Копирование настроек выравнивания
        newCellStyle.setVerticalAlignment(sourceCellStyle.getVerticalAlignment());
//        newCellStyle.setIndentation(sourceCellStyle.getIndentation());
        newCellStyle.setRotation(sourceCellStyle.getRotation());

        // Копирование настроек заливки
        short backgroundColor = sourceCellStyle.getFillBackgroundColor();
//      // Поправить
        newCellStyle.setFillBackgroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        newCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        newCellStyle.setFillPattern(sourceCellStyle.getFillPattern());

        // Копирование других настроек
        newCellStyle.setHidden(sourceCellStyle.getHidden());
        newCellStyle.setLocked(sourceCellStyle.getLocked());
//        newCellStyle.setIndention(sourceCellStyle.getIndentation());
    }
    private static int getRowCount(XSSFSheet sheet){
        int numOfRows = 0;
        for (Row row : sheet) {
            boolean hasData = false;
            for (Cell cell : row) {
                if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                    hasData = true;
                    break;
                }
            }
            if (hasData) {
                numOfRows ++;
            }
        }
        return numOfRows;
    }
    private int getColumnCount(XSSFSheet sheet){
        return sheet.getRow(0).getLastCellNum();
    }
}


