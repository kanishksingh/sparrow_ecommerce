<%-- 
    Document   : SingleProduct
    Created on : 31 Aug, 2020, 6:23:30 PM
    Author     : KANISHK
--%>
<%@page import="com.kanishk.ecommerce.Dao.Authentication"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%

    boolean usercreation = Authentication.checkPermission(session.getAttribute("username").toString(), "product_creation");
%>
<!doctype html>
<html> 
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script language='JavaScript' type="text/javascript" src="${pageContext.request.contextPath}/DashboardResources/js/wysiwyg.js" /></script>
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
                        Product Creation  not granted for you.!! Contact to Administrator;
                    </p>
                    <a href="#" onclick="history.go(-1);return false;">Go back to previous page</a>
                </div>
            </div>
            <%} else {%>
            <h3 class="heading_a" style="text-align: center;font-weight: bold">${msg}</h3><hr>
            <form action="" method="post"  name="UpdateSingleProductDetail" id="UpdateSingleProductDetail"  enctype="multipart/form-data">

                <div class="uk-grid" data-uk-grid-margin>

                    <div class="uk-width-medium-1-3">

                        <div class="uk-form-row">
                            <select class="md-input" name="Category" id="Category" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'top'}"  title="Select Category" onchange="ShowSubMenu(this.value)">
                                <option value="0">Select Category</option>
                                <c:forEach items="${menulists}" var="menulists">
                                    <option value="${menulists.sno}">${menulists.menuName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="uk-form-row">
                            <select  name="submenu" id="submenu"  title="Select Group" style="width: 100%;
    height: 35px;border: none;border-bottom: 1px solid #e0e0e0;font-size: 15px;color: #303030;" onchange="ShowSubSubMenu(this.value)">
                                <option value="0">Select Group</option>
                            </select>
                        </div>
                        <div class="uk-form-row">
                            <select  name="subsubmenu" id="subsubmenu"  title="Select Sub-Group" style="width: 100%;
                                     height: 35px;border: none;border-bottom: 1px solid #e0e0e0;font-size: 15px;color: #303030;" onclick="ShowSubSubMenuByProduct(this.value)">
                                <option value="0">Select SubGroup</option>
                            </select>
                        </div>
                         
                    </div>
                                        <div class="uk-width-medium-1-3">
<div class="uk-form-row">
                            <select  name="product" id="product"  title="Select Product Name" style="width: 100%;
                                     height: 35px;border: none;border-bottom: 1px solid #e0e0e0;font-size: 15px;color: #303030;">
                                <option value="0">Select Product</option>
                            </select>
                              
                        </div>
                        
                                        </div>
                                        <div class="uk-width-medium-1-3">
<div class="uk-form-row">
                            <div class="errorMessage" align="center" style="color:red"> ${msgerror}</div>

                            <button type="submit" class="md-btn" id="save" name="save" >Submit</button>
                          
                        </div>
                         
                                        </div>
                       


                    </div>
                <br>

<!--                    <div class="uk-width-medium-1-2">
                        


                    </div>-->
          
<textarea id="content" cols="100" rows="5" name="content"></textarea> 
					
            </form>
 
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


function ShowSubSubMenuByProduct(submenu){
   var url = '<%=Utils.ConnectionUtils.getConnectionURL()%>/SubSubMenubyProduct/' + submenu + '';
    $.ajax({
        url: url,
        type: "POST",
        dataType: 'json',
        ContentType: "text/html; charset=UTF-8",
        success: function (receipts) {
            console.log(receipts);
            $('#product').empty();
            if (receipts == "") {
                $('#product').append(
                        ' <option value="0">No Product</option>'
                        )
            } else {
                $(receipts).each(function (index, item) {
                    if (index > 0) {
                        $('#product').append(
                                '<option value="' + item.sno + '">' + item.productName +
                                '</option>'
                                )
                    } else {
                        $('#product').append(
                                ' <option value="0">Select Product</option><option value="' + item.sno + '">' + item.productName +
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


          $("form#UpdateSingleProductDetail").submit(function (e) {
            //    var menus=$('.drop-display').text();
    var doSave = confirm('Are you sure you want to Update Detail?');
    if (doSave) {
        e.preventDefault();
        var formDat = new FormData(this);
        
        $.ajax({
            url: '${pageContext.request.contextPath}/UploadProductDetail',
            type: 'POST',
            data: formDat,
            cache: false,
            contentType: false,
            processData: false,
            beforeSend: function (xhr) {
                ajaxindicatorstart('Uploading... Please Wait');
            },
            success: function (response) {
                ajaxindicatorstop();
                alert("Detail Updated saved successfully !!  Status ="+response);
                window.location.replace("newproduct");
            }
        });
    } else {
        return false;
    }

});
     </script>

            <%}%>
        </div>
          <script language="javascript1.2">
              generate_wysiwyg('content');
//			$(document).ready(function() {alert();
//				$('#txtEditorcall').Editor();alert();
//			});
                        
		</script>
     
    </body>

</html>