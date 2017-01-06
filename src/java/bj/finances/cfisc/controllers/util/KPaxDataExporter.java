/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers.util;

import java.awt.Color;
import java.io.File;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdom2.Document;

/**
 *
 * @author SANNI Emmanuel
 */
public class KPaxDataExporter {
    
        public static XSSFWorkbook exportXlsx( KPaxData kPaxData) {
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("recoupement");

        List<Object[]> lf = kPaxData.getData();
        String[] titles = kPaxData.getTitles();
        Row row = sheet.createRow(0);
        for(int i = 0; i <titles.length; i++){
            Cell titleCell = row.createCell((short) i);
            titleCell.setCellValue(titles[i]);
        }
        for (int i = 0; i < lf.size(); i++) {
            row = sheet.createRow(i + 1);
            Object[] ligne = lf.get(i);
            XSSFCellStyle style = wb.createCellStyle();
            XSSFColor backGroundColor = null;
            int type = (int) ligne[kPaxData.getSrcFlag()];
            switch (type) {
                case 1: {
                    backGroundColor = new XSSFColor(Color.WHITE);
                    break;
                }
                case 2: {
                    backGroundColor = new XSSFColor(Color.ORANGE);
                    break;
                }
                case 3: {
                    backGroundColor = new XSSFColor(Color.GRAY);
                    break;
                }
            }

            style.setFillForegroundColor(backGroundColor);
            style.setFillBackgroundColor(backGroundColor);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            for (int j = 0; j < kPaxData.getSrcFlag(); j++) {
                Object cell = ligne[j];
                Cell xcell;
                xcell = row.createCell((short) j);
                if (cell == null) {
                    xcell.setCellStyle(style);
                    continue;
                }
                if (cell instanceof java.util.Date) {
                    xcell.setCellValue((Date) cell);
                } else if (cell instanceof String) {
                    xcell.setCellValue(cell.toString());
                } else if (cell instanceof Boolean) {
                    xcell.setCellValue((Boolean) cell);
                } else {
                    xcell.setCellValue(Double.valueOf(cell.toString()));
                }
                xcell.setCellStyle(style);
            }
        }

        return wb;
    }
        
    public static File exportCSV( KPaxData kPaxData) {
        return null;
    }
    
    public static Document exportXml( KPaxData kPaxData) {
        return null;
    }
    
}
