package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;

public class ExcelConfig {
    private FileInputStream fileInput;
    private FileOutputStream fileOutput;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private Row row;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public void setExcelFile(String ExcelPath, String SheetName) throws Exception {
        try {
            File file = new File(ExcelPath);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File doesn't exist, so created!");
            }
            fileInput = new FileInputStream(ExcelPath);
            workbook = WorkbookFactory.create(fileInput);
            sheet = workbook.getSheet(SheetName);
            //sheet = workbook.getSheetAt(0); //0 - index of 1st sheet
            if (sheet == null) {
                sheet = workbook.createSheet(SheetName);
            }
            this.excelFilePath = ExcelPath;
            //adding all the column header names to the map 'columns'
            sheet.getRow(0).forEach(cell -> {columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCellData(int rowNum, int column) throws Exception {
        try {
            cell = sheet.getRow(rowNum).getCell(column);
            String cellData = null;
            switch(cell.getCellType()) {
                case STRING:
                    cellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellData = String.valueOf(cell.getDateCellValue());
                    }
                    else {
                        cellData = String.valueOf((long)cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    cellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    cellData = "";
                    break;
                case ERROR:
                	break;
                case FORMULA:
                	break;
                case _NONE:
                	break;
                default:
                	break;
            }
            return cellData;
        } catch(Exception e) {
            return "";
        }
    }

    public String getCellData(String columnName, int rowNum) throws Exception {
        return getCellData(rowNum, columns.get(columnName));
    }

    public void setCellData(String text, int rowNum, int column) throws Exception {
        try {
            row = sheet.getRow(rowNum);
            if(row == null) {
                row = sheet.createRow(rowNum);
            }
            cell = row.getCell(column);
            if(cell == null) {
                cell = row.createCell(column);
            }
            cell.setCellValue(text);
            fileOutput = new FileOutputStream(excelFilePath);
            workbook.write(fileOutput);
            fileOutput.flush();
            fileOutput.close();
        } catch(Exception e) {
            throw(e);
        }
    }
}