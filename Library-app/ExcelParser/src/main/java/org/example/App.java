package org.example;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.*;
import org.example.objects.ExlLine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class App 
{
    public static final String exlPath = "D:\\excelWork\\testExcelFile.xlsx";
    public static final String sheetName = "Отчет";
//    public static final String exlPath = "D:\\excelWork\\test.xls";
//    public static final String sheetName = "Birthdays";




    public static void main( String[] args ) throws IOException {

        ExlInteraction exlInteraction = new ExlInteraction();
        exlInteraction.readExcel(exlPath, sheetName);
//        exlInteraction.writeIntoExcel(exlPath, sheetName);
//        ArrayList<ExlLine> lines;
//        lines = exlInteraction.readFromExcel(exlPath, sheetName);
//        lines.forEach(System.out::println);

//        XSSFWorkbook xssfSheets = new XSSFWorkbook(new FileInputStream(exlPath));
//        XSSFSheet xssfSheet = xssfSheets.getSheet(sheetName);
//        XSSFRow row = xssfSheet.getRow(1);
//        System.out.println(row.getRowNum());
//        XSSFCell cell = row.getCell(10);
//        System.out.println(cell.getCellFormula());
//        double a = cell.getNumericCellValue();
//        System.out.println(a);
//        System.out.println(cell.getDateCellValue());



    }

}
