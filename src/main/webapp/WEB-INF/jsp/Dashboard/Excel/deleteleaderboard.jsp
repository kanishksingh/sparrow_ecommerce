<%-- 
    Document   : deleteleaderboard
    Created on : Oct 10, 2019, 1:30:05 PM
    Author     : Kanishk
--%>
<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int sno = Integer.parseInt(request.getParameter("sno"));
 
    try{
    Connection con = DB.ConnectionPool.getNewDBConnection();
    PreparedStatement ps = con.prepareStatement("delete from leaderboard_upload where sno=?");
    ps.setInt(1,sno);
    int rows = ps.executeUpdate();
    if (rows > 0) {
       
        %>
Data Deleted Successfully
<%
    }
con.close();
ps.close();
}catch(Exception e){
System.out.println("className.methodName()"+e);
}
%>
