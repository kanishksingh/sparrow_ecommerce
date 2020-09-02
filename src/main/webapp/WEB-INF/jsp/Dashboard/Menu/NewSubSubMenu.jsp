<%-- 
    Document   : NewSubSubMenu
    Created on : 26 Aug, 2020, 2:10:04 PM
    Author     : KANISHK
--%>
<%@page import="com.kanishk.ecommerce.Dao.Authentication"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!doctype html>
<%

    boolean usercreation = Authentication.checkPermission(session.getAttribute("username").toString(), "subsubmenu_creation");
  %>
<html> 
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>New Sub Menu</title>
 
    </head>
    <body>
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
               Sub-Group Creation  not granted for you.!! Contact to Administrator;
            </p>
            <a href="#" onclick="history.go(-1);return false;">Go back to previous page</a>
        </div>
    </div>
<%} else {%>
          <h3 class="heading_a" style="text-align: center;font-weight: bold">${msg}</h3><hr>
                            <form method="post" name="frmMenu" id="frmMenu" action="${pageContext.request.contextPath}/saveSubSubmenu" modelAttribute="menuform">

                                <div class="uk-grid" data-uk-grid-margin>

                                    <div class="uk-width-medium-1-2">
                                        <div class="uk-form-row">
                                       <select name="menulists" id="menulists" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'right'}" onchange="ShowSubMenu(this.value)">
                                           <option value="0">Select Category</option>
                                           <c:forEach items="${menulists}" var="menulists">
            <option value="${menulists.sno}">${menulists.menuName}</option>
        </c:forEach>
    </select>
                                         
  <span class="uk-form-help-block" id="submenuerror" style="color: red"></span>

                                        </div>
                                        <div class="uk-form-row">
                                           <select id="submenu" class="form-control" name="submenu" title="Select Group Name" style="    border-left: hidden;
                                        border-right: hidden;
                                        border-top: hidden;
                                        height: 70px;width: 100%;    font-size: 16px;" >
                                    <option value="0">Select Group</option>

                                </select>
                                            <span class="uk-form-help-block" id="submenuerror" style="color: red"></span>
                                        </div>
                                         <div class="uk-form-row">
                                            <label>Sub Sub-Group</label>
                                         
                                            <input name="subsubmenuName" class="md-input" id="subsubmenuName" autocomplete="off">
                                            <span class="uk-form-help-block" id="menuerror" style="color: red"></span>
                                        </div>
                                        <div class="uk-form-row">
                                            <div class="errorMessage" align="center" style="color:red"> ${msgerror}</div>

                                            <button type="submit" class="md-btn" id="save" name="save" >Submit</button>
                                        <a  class="md-btn" href="${pageContext.request.contextPath}/newsubsubmenu" ><i class="material-icons">back</i> Back</a>

                                        </div>


                                    </div>

                                    <div class="uk-width-medium-1-2">
                                        <div class="uk-form-row" id="tableDatalist">
                                        <table width="100%" class="table table-striped table-bordered table-hover" id="example" style="overflow-x: scroll">

                                                <thead>
                                                    <tr>
                                                        <th>#</th>

                                                        <th style="text-align: left">Category Name</th>
                                                        <th style="text-align: left">Group Name</th>
                                                        <th style="text-align: left">Sub-Group Name</th>
                                                        <th style="text-align: left">Hide/Show</th>


                                                        <th style="text-align: right"></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="menu" items="${subsubmenulist}" varStatus="loop"> 
                                                        <tr>
                                                            <td>${loop.count}</td>
                                                            <td>${menu.menuName}</td>
                                                            <td>${menu.subMenuName}</td>
                                                            <td>${menu.subSubMenuName}</td>
                                                            <td>${menu.hide_show}</td>

                                                            <td style="float:right"><a href="${pageContext.request.contextPath}/editsubsubmenu/${menu.sno}">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/deletesubsubmenu/${menu.sno}">Delete</a></td>
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
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script>
     function ShowSubMenu(menu) {
    var url = '<%=Utils.ConnectionUtils.getConnectionURL()%>/Menubysubmenu/' + menu + '';
  $.ajax({
        url: url,
        type: "POST",
        dataType: 'json',
        ContentType: "text/html; charset=UTF-8",
        success: function (receipts) {
            console.log(receipts);
            $('#submenu').empty();
            if (receipts == "") {
                $('#submenu').append(
                        ' <option value="0">No Group</option>'
                        )
            } else {
                $(receipts).each(function (index, item) {
                    if (index > 0) {
                        $('#submenu').append(
                                '<option value="' + item.sno + '">' + item.subMenuName +
                                '</option>'
                                )
                    } else {
                        $('#submenu').append(
                                ' <option value="0">Select Group</option><option value="' + item.sno + '">' + item.subMenuName +
                                '</option>'
                                )
                    }
                });
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);

        }

    });
}

function ShowSubSubMenu(submenu){
   var url = '<%=Utils.ConnectionUtils.getConnectionURL()%>/SubMenubysubsubmenu/' + submenu + '';
    $.ajax({
        url: url,
        type: "POST",
        dataType: 'json',
        ContentType: "text/html; charset=UTF-8",
        success: function (receipts) {
            console.log(receipts);
            $('#subsubmenu').empty();
            if (receipts == "") {
                $('#subsubmenu').append(
                        ' <option value="0">No Group</option>'
                        )
            } else {
                $(receipts).each(function (index, item) {
                    if (index > 0) {
                        $('#subsubmenu').append(
                                '<option value="' + item.sno + '">' + item.subSubMenuName +
                                '</option>'
                                )
                    } else {
                        $('#subsubmenu').append(
                                ' <option value="0">Select Group</option><option value="' + item.sno + '">' + item.subSubMenuName +
                                '</option>'
                                )
                    }
                });
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);

        }

    });

}

     </script>

        <%}%>
                </div>
    </body>

</html>