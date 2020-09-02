<%-- 
    Document   : ProductImage
    Created on : 28 Aug, 2020, 4:01:45 PM
    Author     : KANISHK
--%>
<%@page import="com.kanishk.ecommerce.Dao.Authentication"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%

    boolean usercreation = Authentication.checkPermission(session.getAttribute("username").toString(), "product_imageupload");
%>
<!doctype html>
<html> 
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <style>
               table.toolbar2 {
    display: none;
}
            thead {
                background-color: #060606;
                color: white;
            }
            table {
                border-collapse: collapse;
                border-spacing: 0;
                width: 100%;
                border: 1px solid #ddd;
            }

            th, td {
                text-align: left;
                padding: 8px;
            }

            /*tr:nth-child(even){background-color: #f2f2f2}*/
        </style>
                <script type="text/javascript" src="https://cdn.rawgit.com/asvd/dragscroll/master/dragscroll.js"></script>

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
                        Product Image Uploading  not granted for you.!! Contact to Administrator;
                    </p>
                    <a href="#" onClick="history.go(-1);return false;">Go back to previous page</a>
                </div>
            </div>
            <%} else {%>
            <h3 class="heading_a" style="text-align: center;font-weight: bold">${msg}</h3><hr>

            <div class="uk-grid" data-uk-grid-margin>

                <div class="uk-width-medium-1">

                    <div class="uk-form-row" style="text-align:center; overflow-x: scroll">

                        <table border="1" cellpadding="0" cellspacing="0" width="70%" >
                            <tr><td valign="top" align="top" width="60%">
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%"  >

                                        <tr>
                                            <td valign="top" align="top" width="30%"><label>Filter</label></td>
                                            <td width="70%"><select  class="md-input" name="filter" id="filter" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'top'}"  title="Select Filter Image Type">
                                                    <option value="Select">All</option>
                                                    <option value="Y">Product With Image</option>
                                                    <option value="N">Product Without Image</option>
                                                </select></td>
                                        </tr>
                                        <tr>
                                            <td  width="30%"><label>Category</label></td>
                                            <td width="70%"><select class="md-input" name="Category" id="Category" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'top'}"  title="Select Category">
                                                    <option value="0">All</option>
                                                    <c:forEach items="${menulists}" var="menulists">
                                                        <option value="${menulists.sno}">${menulists.menuName}</option>
                                                    </c:forEach>
                                                </select></td>
                                        </tr>
                                        <tr>
                                            <td  width="30%"><label>Group</label></td>
                                            <td width="70%"><select class="md-input" name="Group" id="Group" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'top'}"  title="Select Group">
                                                    <option value="0">All</option>
                                                    <c:forEach items="${submenulist}" var="smenulists">
                                                        <option value="${smenulists.sno}">${smenulists.subMenuName}</option>
                                                    </c:forEach>
                                                </select></td>
                                        </tr>
                                        <tr>
                                            <td  width="30%"><label>Sub-Group</label></td>
                                            <td width="70%"><select class="md-input" name="subGroup" id="subGroup" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'top'}"  title="Select Sub-Group">
                                                    <option value="0">All</option>
                                                    <c:forEach items="${subsubmenulist}" var="ssmenulists">
                                                        <option value="${ssmenulists.sno}">${ssmenulists.subSubMenuName}</option>
                                                    </c:forEach>
                                                </select></td>
                                        </tr>

                                    </table>
                                </td>
                                <td  width="60%" valign="top" align="top">
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%" >
                                        <tr>
                                            <td width="30%"><label>Item Code</label></td>
                                            <td width="70%">
                                                <select class="md-input" name="itemcode" id="itemcode" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'top'}"  title="Select Product Item Code">
                                                    <option value="Select">All</option>
                                                    <c:forEach items="${productitemcode}" var="pitemlists">
                                                        <option value="${pitemlists.sno}">${pitemlists.itemCode}</option>
                                                    </c:forEach>
                                                </select></td>
                                        </tr>
                                        <tr>
                                            <td width="30%"><label>BarCode</label></td>
                                            <td width="70%"> <select class="md-input" name="BarCode" id="BarCode" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'top'}"  title="Select Product BarCode">
                                                    <option value="Select">All</option>
                                                    <c:forEach items="${productbarcode}" var="pbarlists">
                                                        <option value="${pbarlists.sno}">${pbarlists.itemCode}</option>
                                                    </c:forEach>
                                                </select></td>
                                        </tr>
                                        <tr align="center" valign="bottom" >
                                            <td colspan="2" align="center" style="text-align:center"><button  type="button" onclick="reportdata()" name="filter" id="filter"  style="background:#F90; color:Black"class="md-btn" >Search Products</button></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <form action="" method="post"  name="UploadProductImage" id="UploadProductImage"  enctype="multipart/form-data">

                <div class="row" id="tableDatalist" style="width: 100%;overflow-x:auto;">

                    <table width="100%" class="" id="example2" style="">
                        <tr>
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0" width="100%" >
                                    <tr>
                                        <td width="30%"><label>Select Image 1</label></td>
                                        <td width="70%">
                                            <input type="file" accept="image/x-png,image/gif,image/jpeg" name="image1" id="image1" class="md-input">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="30%"><label>Select Image 2</label></td>
                                        <td width="70%">
                                            <input type="file" accept="image/x-png,image/gif,image/jpeg" name="image2" id="image2" class="md-input">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="30%"><label>Select Image 3</label></td>
                                        <td width="70%">
                                            <input type="file" accept="image/x-png,image/gif,image/jpeg" name="image3" id="image3" class="md-input">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="30%"><label>Select Image 4</label></td>
                                        <td width="70%">
                                            <input type="file" accept="image/x-png,image/gif,image/jpeg" name="image4" id="image4" class="md-input">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="30%"><label>Select Video</label></td>
                                        <td width="70%">
                                            <input type="file" accept="video/mp4,video/x-m4v,video/*" name="video" id="video" class="md-input">
                                        </td>
                                    </tr>

                                </table>
                            </td> 
                            <td>
                                <table border="0" width="100%" >


                                    <tr align="center" valign="bottom" >
                                        <td colspan="2" align="center" style="text-align:center"><button  type="submit" name="upload" id="upload"  style="background:#F90; color:Black"class="md-btn" >Upload Image/Video</button></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                     <div class="uk-width-medium-1">
                         <textarea id="content" cols="100" rows="7" name="content" placeholder=""></textarea> 

                        </div><script language="javascript1.2">
              generate_wysiwyg('content');
			
		</script>
                    <br>
                    <div id="totalproductcount" style="text-align: center;font-size: 24px;"></div>
                                                <div class="dragscroll" style="width: 100%; height: 415px; overflow: scroll; cursor: grab; cursor : -o-grab; cursor : -moz-grab; cursor : -webkit-grab;">

                        <table width="100%" class="" id="example" style="">
                            <thead style="border-bottom: 1px solid gray">
                                <tr>
                                    <th style="width: 3%"><input type="checkbox" name="item" id="item"  onchange="toggleAll(this)"></th>
                                    <th style="text-align: left;width: 5%">Category</th>
                                    <th style="width: 10%">Group</th>
                                    <th style="width: 10%">Sub-Group</th>
                                    <th style="width: 10%">Product</th>
                                    <th style="width: 10% ">Itemcode</th>
                                    <th style="width: 10% ">Barcode</th>
                                    <th style="width: 10% ">Image 1</th>
                                    <th style="width: 10% ">Image 2</th>
                                    <th style="width: 10% ">Image 3</th>
                                    <th style="width: 10% ">Image 4</th>
                                    <th style="width: 10% ">Video</th>
                                    <th style="width: 10% ">Update</th>
                                </tr>
                            </thead>
                            <tbody id="tablebodydata">

                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
            <!-- MODAL AREA START -->
            <button type="button" style="display:none" data-uk-modal="{target:'#myModal'}" id="openmodal">OPEN</button>
            <!-- Modal -->
            <div class="uk-modal" id="myModal">
                <div class="uk-modal-dialog">
                    <form method="post" name="frmMenu" id="frmMenu" action="${pageContext.request.contextPath}/UpdateProductImage" modelAttribute="updatemenuform">

                        <div class="uk-modal-header">
                            <h3 class="uk-modal-title">Update Image <input type="hidden" name="modelproductsno" id="modelproductsno"> <i class="material-icons" data-uk-tooltip="{pos:'top'}" title="Update New Product Image, Old Image deleted automatically ">&#xE8FD;</i></h3>
                        </div>

                        <div class="uk-grid" data-uk-grid-margin>

                            <div class="uk-width-medium-1-2">
                                <div class="uk-form-row">
                                    <input type="file" name="img1" accept="image/x-png,image/gif,image/jpeg" id="img1" class="md-input" >
                                    <span class="uk-form-help-block" style="color: red">Select Image 1</span>

                                </div>
                                <div class="uk-form-row">
                                    <input type="file" name="img2" accept="image/x-png,image/gif,image/jpeg" id="img2" class="md-input" >
                                    <span class="uk-form-help-block" style="color: red">Select Image 2</span>

                                </div>
                                <div class="uk-form-row">
                                    <input type="file" name="img3" accept="image/x-png,image/gif,image/jpeg" id="img3" class="md-input" >
                                    <span class="uk-form-help-block" style="color: red">Select Image 3</span>

                                </div>
                            </div>
                            <div class="uk-width-medium-1-2">
                                <div class="uk-form-row">
                                    <input type="file" name="img4" accept="image/x-png,image/gif,image/jpeg" id="img4" class="md-input" >
                                    <span class="uk-form-help-block" style="color: red">Select Image 4</span>

                                </div>
                                <div class="uk-form-row">
                                    <input type="file" name="vid" accept="video/mp4,video/x-m4v,video/*" id="vid" class="md-input" >
                                    <span class="uk-form-help-block" style="color: red">Select Video</span>

                                </div>

                            </div>
                        </div>

                        <div class="uk-modal-footer uk-text-right">
                            <button type="button" class="md-btn md-btn-flat uk-modal-close" style="background-color: graytext">Close</button>
                            <button type="submit" class="md-btn md-btn-flat md-btn-flat-primary" style="background-color:" >Upload</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>

        <script>
            $(document).ready(function () {
            });
        </script>
        <!-- MODAL AREA END -->

        <script>
            function toggleAll(cb)
            {
                var val = cb.checked;
                var frm = document.forms[0];
                var len = frm.elements.length;
                var i = 0;
                for (i = 0; i < len; i++)
                {
                    if (frm.elements[i].type == "checkbox" && frm.elements[i] != cb)
                    {
                        frm.elements[i].checked = val;
                    }
                }
            }
            
            $("form#frmMenu").submit(function (e) {
                var doSave = confirm('Are you sure you want to Update Images?');
                if (doSave) {
                    e.preventDefault();
                    var formDat = new FormData(this);
                    var url = '${pageContext.request.contextPath}/UpdateProductImages';
                    $.ajax({
                        url: url,
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
                            alert("Detail Updated saved successfully");
                            window.location.replace("uploadProductimage");
                        }
                    });
                } else {
                    return false;
                }

            });
            $("form#UploadProductImage").submit(function (e) {
                var menus = $('#totalproductcount').text();
                var doSave = confirm('Are you sure you want to Upload Images for ' + menus + '?');
                if (doSave) {
                    e.preventDefault();
                    var formDat = new FormData(this);
                    var url = '${pageContext.request.contextPath}/UploadProductImagesMultiple';
                    $.ajax({
                        url: url,
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
                            alert("Detail Updated saved successfully");
                            window.location.replace("uploadProductimage");
                        }
                    });
                } else {
                    return false;
                }

            });
            function reportdata() {
                var filter = $('#filter').val();
                var Category = parseInt($('#Category').val());
                var Group = parseInt($('#Group').val());
                var subGroup = parseInt($('#subGroup').val());
                var itemcode = $('#itemcode').val();
                var BarCode = $('#BarCode').val();
                var url = '<%=Utils.ConnectionUtils.getConnectionURL()%>/AjaxPageCall/' + filter + '/' + Category + '/' + Group + '/' + subGroup + '/' + itemcode + '/' + BarCode + '';
                jQuery.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json', // ** ensure you add this line **
                    contentType: "application/json",
                    success: function (response) {
                        $('#tablebodydata').empty();
                        $('#totalproductcount').empty();
                        var htmlText = '';
                        if (response == "") {
                            htmlText += '<tr >';
                            htmlText += '<td colspan="7" style="text-align:center;background-color: #ff9900;color: white;font-size: 24px;">No Data Found</td>';
                            htmlText += '</tr>';

                        } else {
                            for (var key in response) {
                                var values = response[key].sno + '>' + response[key].menuName + '>' + response[key].subMenuName;
                                htmlText += '<tr onMouseout="this.bgColor="#FFFFFF" ">';
                                htmlText += '<td><input type="checkbox" name="masteritems" id="masteritems" value="' + values + '"  checked></td>';
                                htmlText += '<td>' + response[key].menuName + '</td>';
                                htmlText += '<td>' + response[key].subMenuName + '</td>';
                                htmlText += '<td>' + response[key].subSubMenuName + '</td>';
                                htmlText += '<td>' + response[key].productName + '</td>';
                                htmlText += '<td>' + response[key].itemCode + '</td>';
                                htmlText += '<td>' + response[key].barCode + '</td>';
                                if (response[key].image1_Filename == "") {
                                    htmlText += '<td>No Image</td>';
                                } else {
                                    htmlText += '<td><a href="${pageContext.request.contextPath}/' + response[key].folderName + '/' + response[key].image1_Filename + '" target="_blank"><img src=${pageContext.request.contextPath}/' + response[key].folderName + '/' + response[key].image1_Filename + ' width=40 height=20 alt="Image 1"/></a></td>';
                                }
                                if (response[key].image2_Filename == "") {
                                    htmlText += '<td>No Image</td>';
                                } else {
                                    htmlText += '<td><a href="${pageContext.request.contextPath}/' + response[key].folderName + '/' + response[key].image2_Filename + '" target="_blank"><img src=${pageContext.request.contextPath}/' + response[key].folderName + '/' + response[key].image2_Filename + ' width=40 height=20 alt="Image 2"/></a></td>';
                                }
                                if (response[key].image3_Filename == "") {
                                    htmlText += '<td>No Image</td>';
                                } else {
                                    htmlText += '<td><a href="${pageContext.request.contextPath}/' + response[key].folderName + '/' + response[key].image3_Filename + '" target="_blank"><img src=${pageContext.request.contextPath}/' + response[key].folderName + '/' + response[key].image3_Filename + ' width=40 height=20 alt="Image 3"/></a></td>';
                                }
                                if (response[key].image4_Filename == "") {
                                    htmlText += '<td>No Image</td>';
                                } else {
                                    htmlText += '<td><a href="${pageContext.request.contextPath}/' + response[key].folderName + '/' + response[key].image4_Filename + '" target="_blank"><img src=${pageContext.request.contextPath}/' + response[key].folderName + '/' + response[key].image4_Filename + ' width=40 height=20 alt="Image 4"/></a></td>';
                                }
                                if (response[key].image5_Filename == "") {
                                    htmlText += '<td>No Video</td>';
                                } else {
                                    htmlText += '<td><a href="${pageContext.request.contextPath}/' + response[key].folderName + '/' + response[key].image5_Filename + '" target="_blank"><img src=${pageContext.request.contextPath}/' + response[key].folderName + '/' + response[key].image5_Filename + ' width=40 height=20 alt="Video"/></a></td>';
                                }
                                htmlText += '<td><button type="button" id="" class="btn btn-info btn-lg updateimagecall" onclick=UploadImage(' + response[key].sno + ') ">UpDate Image</button></td>';

                                htmlText += '</tr>';
                            }
                            var totproducts = 'Total Products ' + response.length;
                            $('#totalproductcount').append(totproducts);
                        }
                        $('#tablebodydata').append(htmlText);

                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert("some error");
                    }
                });
            }
         
            function UploadImage(sno) {
                $('#openmodal').click();
                $('#modelproductsno').val(sno);
            }
        </script>
        <%}%>
    </div>
</body>

</html>