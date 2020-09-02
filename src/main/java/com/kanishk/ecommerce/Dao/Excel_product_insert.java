/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanishk.ecommerce.Dao;

import java.io.File;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author KANISHK
 */
public class Excel_product_insert {

//    public boolean getExceltodataBaseAdminProduction(String fileName, String path, String username) {
//        boolean success = false;
//        try {
//             JOptionPane.showMessageDialog(null, "2");
//
//            boolean saveProductionDetail = false;
//            FileInputStream file = new FileInputStream(new File(path + File.separator + fileName));
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            Row row;
//                JOptionPane.showMessageDialog(null, "3");
//
//            String prodictid, category, group, subgroup, product, style, brand, colour, applicablemodelno, modelno, applicablemfd, mfgdate, expdateapplicable, expdate,
//                    placeoforigin, hsnno, itemcode, barcode, minqtyalert, size, rate, defdate;
//            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
//                row = sheet.getRow(i);
//                try {
//                    category = (row.getCell(1).toString()).trim();
//                } catch (Exception e) {
//                    category = "";
//                }
//                try {
//                    group = (row.getCell(2).toString()).trim();
//                } catch (Exception e) {
//                    group = "";
//                }
//
//                try {
//                    subgroup = (row.getCell(3).toString()).trim();
//                } catch (Exception e) {
//                    subgroup = "";
//                }
//
//                try {
//                    product = (row.getCell(4).toString()).trim();
//                } catch (Exception e) {
//                    product = "";
//                }
//
//                try {
//                    style = (row.getCell(5).toString()).trim();
//                } catch (Exception e) {
//                    style = "";
//                }
//
//                try {
//                    brand = (row.getCell(6).toString()).trim();
//                } catch (Exception e) {
//                    brand = "";
//                }
//
//                try {
//                    colour = (row.getCell(7).toString()).trim();
//                } catch (Exception e) {
//                    colour = "";
//                }
//
//                try {
//                    applicablemodelno = (row.getCell(8).toString()).trim();
//                } catch (Exception e) {
//                    applicablemodelno = "";
//                }
//
//                try {
//                    modelno = (row.getCell(9).toString()).trim();
//                } catch (Exception e) {
//                    modelno = "";
//                }
//
//                try {
//                    applicablemfd = (row.getCell(10).toString()).trim();
//                } catch (Exception e) {
//                    applicablemfd = "";
//                }
//
//                try {
//                    mfgdate = (row.getCell(11).toString()).trim();
//                } catch (Exception e) {
//                    mfgdate = "";
//                }
//
//                try {
//                    expdateapplicable = (row.getCell(12).toString()).trim();
//                } catch (Exception e) {
//                    expdateapplicable = "";
//                }
//                try {
//                    expdate = (row.getCell(13).toString()).trim();
//                } catch (Exception e) {
//                    expdate = "";
//                }
//                try {
//                    placeoforigin = (row.getCell(14).toString()).trim();
//                } catch (Exception e) {
//                    placeoforigin = "";
//                }
//                try {
//                    hsnno = (row.getCell(15).toString()).trim();
//                } catch (Exception e) {
//                    hsnno = "";
//                }
//                try {
//                    itemcode = (row.getCell(16).toString()).trim();
//                } catch (Exception e) {
//                    itemcode = "";
//                }
//                try {
//                    barcode = (row.getCell(17).toString()).trim();
//                } catch (Exception e) {
//                    barcode = "";
//                }
//                try {
//                    minqtyalert = (row.getCell(18).toString()).trim();
//                } catch (Exception e) {
//                    minqtyalert = "";
//                }
//                try {
//                    size = (row.getCell(19).toString()).trim();
//                } catch (Exception e) {
//                    size = "";
//                }
//                try {
//                    rate = (row.getCell(20).toString()).trim();
//                } catch (Exception e) {
//                    rate = "";
//                }
//                try {
//                    defdate = (row.getCell(21).toString()).trim();
//                } catch (Exception e) {
//                    defdate = "";
//                }
//                JOptionPane.showMessageDialog(null, category);
////                if(productiontype.equalsIgnoreCase("")){break;}
////                boolean check = CRMPOJO.checkAdminProductionExist(productiontype,duration1);
////                if (check == false) {
////                    saveProductionDetail = CRMPOJO.SaveAdminProductionExcel(username, productiontype, duration1, commission1);
////                } else {
////                    saveProductionDetail = CRMPOJO.UpdateAdminProductionExcel(username, productiontype, duration1, commission1);
////                }    
//            }
//
//            if (saveProductionDetail == true) {
//                success = true;
//            }
//
//        } catch (Exception e) {
//                success = false;
//        }
//        return success;
//    }
}
