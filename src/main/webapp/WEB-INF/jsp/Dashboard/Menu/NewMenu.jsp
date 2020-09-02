<%-- 
    Document   : NewMenu
    Created on : 8 May, 2020, 11:28:21 PM
    Author     : KANISHK
--%>
<%@page import="com.kanishk.ecommerce.Dao.Authentication"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%

    boolean usercreation = Authentication.checkPermission(session.getAttribute("username").toString(), "menu_creation");
  %>
<!doctype html>
<html> 
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    

    </head>
    <body >
                                <div id="UpdateUserid">

    <%     if (usercreation == false) {%>
                     <div class="error_page_header">
        <div class="uk-width-8-10 uk-container-center">
            500!
        </div>
    </div>
    <div class="error_page_content">
        <div class="uk-width-8-10 uk-container-center">
            <p class="heading_b">Oops! Something went wrong...</p>
            <p class="uk-text-large">
               User Creation  not granted for you.!! Contact to Administrator;
            </p>
            <a href="#" onclick="history.go(-1);return false;">Go back to previous page</a>
        </div>
    </div>
<%} else {%>
          <h3 class="heading_a" style="text-align: center;font-weight: bold">${msg}</h3><hr>
        <form method="post" name="frmMenu" id="frmMenu" action="${pageContext.request.contextPath}/savemenu" modelAttribute="menuform">

            <div class="uk-grid" data-uk-grid-margin>

                <div class="uk-width-medium-1-2">
                    <div class="uk-form-row">
                        <label>Heading</label>
                        <input name="menuName" class="md-input" id="menuName" autocomplete="off">
                        <span class="uk-form-help-block" id="menuerror" style="color: red"></span>
                    </div>
                    <div class="uk-form-row">
                        <div class="errorMessage" align="center" style="color:red"> ${msgerror}</div>

                        <button type="submit" class="md-btn" id="save" name="save" >Submit</button>
                    </div>


                </div>

                <div class="uk-width-medium-1-2">
                    <div class="uk-form-row" id="tableDatalist">
                        <table width="100%" class="table table-striped table-bordered table-hover" id="example" style="overflow-x: scroll">

                            <thead>
                                <tr>
                                    <th>#</th>

                                    <th style="text-align: left">Name</th>
                                    <th style="text-align: left">Status(Hide/Show)</th>


                                    <th style="text-align: right"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="menu" items="${menulist}" varStatus="loop"> 
                                    <tr>
                                        <td>${loop.count}</td>
                                        <td>${menu.menuName}</td>
                                        <td>${menu.hide_show}</td>

                                        <td style="float:right"><a href="${pageContext.request.contextPath}/editmenu/${menu.sno}">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/deletemenu/${menu.sno}">Delete</a></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                    </div>

                </div>
            </div>
        </form>
   
        <script>
          $('.errorMessage').fadeOut(2000);
        </script>
        <%}%>
                                </div>
    </body>

</html>