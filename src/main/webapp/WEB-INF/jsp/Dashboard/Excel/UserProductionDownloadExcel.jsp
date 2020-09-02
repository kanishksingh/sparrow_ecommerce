<%-- 
    Document   : UserProductionDownloadExcel
    Created on : 4 Feb, 2020, 8:55:55 PM
    Author     : KANISHK
--%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.sql.ResultSetMetaData"%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="javafx.scene.control.Cell"%>
<%@page import="org.apache.poi.ss.usermodel.Row"%>
<%@page import="org.apache.poi.xssf.usermodel.XSSFWorkbook"%>
<%@page import="org.apache.poi.ss.usermodel.Workbook"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="javax.swing.JOptionPane"%>
<%
    try{

Workbook wb = new XSSFWorkbook();

org.apache.poi.ss.usermodel.Sheet personSheet = wb.createSheet("PersonList");

Row headerRow = personSheet.createRow(0);

org.apache.poi.ss.usermodel.Cell nameHeaderCell = headerRow.createCell(0);

org.apache.poi.ss.usermodel.Cell addressHeaderCell = headerRow.createCell(1);

Connection con =DB.ConnectionPool.getNewDBConnection();

PreparedStatement ps=null;

String sql = "SELECT sno,Agent_id,Commissions,Target_Premium,Applications,Direct_Recruits,Revenue_Sharing,Products,Duration_Product,Commissions_Products,Target_Premium_Products,Carriers,Duration_Carrier,Commissions_Carrier,Target_Premium_Carrier FROM `user_production_upload`;";
ps =  con.prepareStatement(sql);
ResultSet resultSet = ps.executeQuery();    
ResultSetMetaData rsmd = resultSet.getMetaData();
int row = 1;
for(int i=0;i<rsmd.getColumnCount();i++) {
    //String name=;
    //JOptionPane.showMessageDialog(null, rsmd.getColumnName(row));
    Row dataRow = personSheet.createRow(1);

    org.apache.poi.ss.usermodel.Cell dataNameCell = headerRow.createCell(i);
    dataNameCell.setCellValue(rsmd.getColumnName(row));

    row = row + 1;
}
//int row1=1;
//while(resultSet.next()){
//    Row dataRow = personSheet.createRow(row1);
//for(int i=0;i<rsmd.getColumnCount();i++) {
//    //String name=;
//    //JOptionPane.showMessageDialog(null, rsmd.getColumnName(row));
//    org.apache.poi.ss.usermodel.Cell dataNameCell = dataRow.createCell(i);
//    dataNameCell.setCellValue(resultSet.getString(i+1));
//
//    //row = row + 1;
//}
//row1++;
//}
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length); 
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=UserProductionExcelFormat.xlsx");
            OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
    }catch(Exception e ){
    %>
    <h4><%=e%></h4>
<%
    }
%>
<h2>Your File Is Downloading...</h2>