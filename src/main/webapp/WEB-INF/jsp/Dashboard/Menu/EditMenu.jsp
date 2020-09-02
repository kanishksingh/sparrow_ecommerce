<%-- 
    Document   : EditMenu
    Created on : 29 May, 2020, 7:25:16 PM
    Author     : KANISHK
--%>
<%@page import="com.kanishk.ecommerce.Dao.Authentication"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!doctype html>
<%

    boolean usercreation = Authentication.checkPermission(session.getAttribute("username").toString(), "menu_updation");
  %>
<html > 
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   
    </head>
    <body onload="document.getElementById('hide_show').value='${command.hide_show}';">
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
               Category Updation  not granted for you.!! Contact to Administrator;
            </p>
            <a href="#" onclick="history.go(-1);return false;">Go back to previous page</a>
        </div>
    </div>
<%} else {%>
          <h3 class="heading_a" style="text-align: center;font-weight: bold">${msg}</h3><hr>
        <form method="post" name="frmMenu" id="frmMenu" action="${pageContext.request.contextPath}/updatemenu" modelAttribute="updatemenuform">

            <div class="uk-grid" data-uk-grid-margin>

                <div class="uk-width-medium-1-1">
                    <div class="uk-form-row">
                        <label>Heading</label>
                        <input name="menuName" class="md-input" id="menuName" autocomplete="off" value="${command.menuName}">
                        <span class="uk-form-help-block" id="menuerror" style="color: red"></span>
                    </div>
                        <div class="uk-form-row">
                        <label>Status</label>
                        <select name="hide_show" class="md-input" id="hide_show" >
                            <option value="Y">Show</option>
                            <option value="N">Hidden</option>
                        </select>
                        <span class="uk-form-help-block" id="menuerror" style="color: red"></span>
                    </div>
                    <div class="uk-form-row">
                        <input name="sno" type="hidden" id="sno" value="${command.sno}">
                        <div class="errorMessage1" align="center" style="color:red"> ${succmsg}</div>

                        <button type="submit" class="md-btn" id="save" name="save" >Submit</button>
                        <a  class="md-btn" href="${pageContext.request.contextPath}/newmenu" ><i class="material-icons">back</i> Back</a>
                    </div>


                </div>


            </div>
        </form>

        <script>
$('.errorMessage1').fadeOut(2000);
        </script>
    <%}%>
             </div>
    </body>

</html>