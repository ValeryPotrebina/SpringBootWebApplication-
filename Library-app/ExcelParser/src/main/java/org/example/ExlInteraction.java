package org.example;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.objects.ExlLine;
import org.example.objects.PaymentType;

import java.io.*;
import java.util.*;

public class ExlInteraction {

    @SuppressWarnings("deprecation")
    public void writeIntoExcel(String file, String sheetName) throws IOException {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet(sheetName);
        // Нумерация начинается с нуля
        Row row = sheet.createRow(0);

        // Мы запишем имя и дату в два столбца
        // имя будет String, а дата рождения --- Date,
        // формата dd.mm.yyyy
        Cell name = row.createCell(0);
        name.setCellValue("John");

        Cell birthdate = row.createCell(1);

        DataFormat format = book.createDataFormat();
        CellStyle dateStyle = book.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
        birthdate.setCellStyle(dateStyle);


        // Нумерация лет начинается с 1900-го
        birthdate.setCellValue(new Date(110, 10, 10));

        // Меняем размер столбца
        sheet.autoSizeColumn(1);

        // Записываем всё в файл
        book.write(new FileOutputStream(file));
        book.close();
    }

    public ArrayList<ExlLine> readFromExcel(String file, String sheetName) throws IOException {
        ArrayList<ExlLine> lines = new ArrayList<>();
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
//      Получение листа
        XSSFSheet myExcelSheet = myExcelBook.getSheet(sheetName);

        Header header = myExcelSheet.getHeader();
//        System.out.println(header);
        for (int i = 1; i < myExcelSheet.getLastRowNum() + 1; i++) {
            XSSFRow row = myExcelSheet.getRow(i);
            if (row != null) {
                ExlLine exlLine = new ExlLine();
                if (Objects.isNull(row.getCell(0))){
                    exlLine.setDate(new Date());
                } else {
                    exlLine.setDate(row.getCell(0).getDateCellValue());
                }
                String type = row.getCell(1).getStringCellValue();
                switch (type) {
                    case "бн" -> exlLine.setPaymentType(PaymentType.NONCASH);
                    case "нал" -> exlLine.setPaymentType(PaymentType.CASH);
                    default -> exlLine.setPaymentType(PaymentType.NONE);
                }
                exlLine.setPaymentInvoice(row.getCell(2).getNumericCellValue());
                exlLine.setCustomerName(row.getCell(3).getStringCellValue());
                exlLine.setExecutorName(row.getCell(4).getStringCellValue());
                exlLine.setTechniqueName(row.getCell(5).getStringCellValue());
                exlLine.setAccount(row.getCell(6).getStringCellValue());
                exlLine.setWorkSum(row.getCell(7).getNumericCellValue());
                exlLine.setNDS(row.getCell(8).getNumericCellValue());
                exlLine.setDispatcherSum(row.getCell(9).getNumericCellValue());
//                проверить если клетка пустая
                exlLine.setRemark(row.getCell(10).getStringCellValue());
                exlLine.setReferenceNum(row.getCell(11).getStringCellValue());
                DataFormatter formatter = new DataFormatter();
                XSSFCell cell = row.getCell(12);
                exlLine.setActNum(formatter.formatCellValue(cell));
                exlLine.setOrganization(row.getCell(13).getStringCellValue());
                exlLine.setSumWithNDS(row.getCell(14).getNumericCellValue());
                lines.add(exlLine);
            }
        }

        myExcelBook.close();
        return lines;
    }

    public Map<Integer, List<String>> readExcel(String file, String sheetName) throws IOException {
        Map<Integer, List<String>> data = new HashMap<>();
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workbook.getSheet(sheetName);
        System.out.println(getRowCount(sheet) + ", " + getColumnCount(sheet));
        workbook.close();
        return new HashMap<>();
    }
    private int getRowCount(XSSFSheet sheet){
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
