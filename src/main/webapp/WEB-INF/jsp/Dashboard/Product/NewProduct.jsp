<%-- 
    Document   : NewProduct
    Created on : 26 Aug, 2020, 4:32:24 PM
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
     
        <script type="text/javascript" src="https://cdn.rawgit.com/asvd/dragscroll/master/dragscroll.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script language='JavaScript' type="text/javascript" src="${pageContext.request.contextPath}/DashboardResources/js/wysiwyg.js" /></script>
    <style>
     
thead {
    background: #000;
    color: white;
}
input, select {
    border-bottom: 1px solid #d43d3d !important;
}
   table.toolbar2 {
    display: none;
}
    </style>
    </head>
    <body >
        <div id="UpdateUserid" style="background-color: #e8eceb80;">

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
            <h3 class="heading_a" style="text-align: center;font-weight: bold">${msg}
                <div style="    text-align: center;
                     font-weight: bold;
                     font-style: italic;
                     color: #1976d2;
                     font-size: 14px;"><label><input type="radio" id="name_of_your_radiobutton" name="name_of_your_radiobutton" value="1" checked onclick="ViewCall()">Multiple Upload</label>&nbsp;&nbsp;<-->
                    <label><input type="radio" id="name_of_your_radiobutton" name="name_of_your_radiobutton" value="2" onclick="ViewCall()">Single Upload</label>&nbsp;&nbsp;<-->
                    <label><input type="radio" id="name_of_your_radiobutton" name="name_of_your_radiobutton" value="3" onclick="ViewCall()">View Report</label></div>
            </h3>
            <hr>
            <div id="section1" style="display: none">
                <form action="" method="post"  name="UploadProductExcel" id="UploadProductExcel"  enctype="multipart/form-data">

                    <div class="uk-grid" data-uk-grid-margin>

                        <div class="uk-width-medium-1-2">

                            <div class="uk-form-row">
                                <input name="file" class="md-input" id="file" type="file" >
                                <span class="uk-form-help-block" id="menuerror" style="color: red"> Upload Product Excel</span>
                            </div>


                        </div>

                        <div class="uk-width-medium-1-2">
                            <div class="uk-form-row">
                                <div class="errorMessage" align="center" style="color:red"> ${msgerror}</div>

                                <button type="submit" class="md-btn" id="save" name="save" >Submit</button>
                                <button style="background-color: blue;color: white;" type="button" class="md-btn danger" id="download" name="download" onclick="down_file('${pageContext.request.contextPath}/resources/Dynamic_FileAttachment/ProductUploaded_Excel/sample_productexcelforweb.xlsx', 'Formated_Excel.xlsx')" >
                                    Download Format</button>
                                <!--                            <button style="background-color: blue;color: white;" type="button" class="md-btn danger" id="download" name="download" onclick="reportdata()" >
                                                            View Report</button>-->

                            </div>
                        </div>


                    </div>

                </form>
            </div>
            <div id="section2" style="display: none">

                <form action="" method="post"  name="UploadSingleProductExcel" id="UploadSingleProductExcel"  enctype="multipart/form-data">

                    <div class="uk-grid" data-uk-grid-margin>

                        <div class="uk-width-medium-1-3">

                            <div class="uk-form-row">
                                <select class="md-input" name="Category" id="Category" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'top'}"  title="Select Category" onchange="ShowSubMenu(this.value)">
                                    <option value="0">Select Category</option>
                                    <c:forEach items="${menulists}" var="menulists">
                                        <option value="${menulists.sno}">${menulists.menuName}</option>
                                    </c:forEach>
                                </select>
 <span class="uk-form-help-block" style="color: red">Select Category</span>

                            </div>
                            <div class="uk-form-row">
                                <select  name="submenu" id="submenu"  title="Select Group" style="width: 100%;
                                         height: 40px;border: none;border-bottom: 1px solid #e0e0e0;font-size: 15px;color: #303030;" onchange="ShowSubSubMenu(this.value)">
                                    <option value="0">Select Group</option>
                                </select>
                                 <span class="uk-form-help-block" style="color: red">Select Group</span>

                            </div>
                            <div class="uk-form-row">
                                <select  name="subsubmenu" id="subsubmenu"  title="Select Sub-Group" style="width: 100%;
                                         height: 40px;border: none;border-bottom: 1px solid #e0e0e0;font-size: 15px;color: #303030;" onclick="ShowSubSubMenuByProduct(this.value)">
                                    <option value="0">Select SubGroup</option>

                                </select>
                                                                     <span class="uk-form-help-block" style="color: red">Select Sub-Group</span>

                            </div>
                             <div class="uk-form-row">
                                                         <label>Product Name</label>
 <select  name="product" id="product"  title="Select Product Name" style="width: 100%;
                                         height: 40px;border: none;border-bottom: 1px solid #e0e0e0;font-size: 15px;color: #303030;" onchange="GetProductDetail(this.value)" >
                                    <option value="0">Select Product Name</option>

 </select>

<input type="text" name="addproduct" style="display: none" id="addproduct"   title="Enter Product Name" class="md-input">
                            </div>
                             <div class="uk-form-row">
                                                         <label>Product Style</label>

<input type="text" name="style" id="style"   title="Enter Product style" class="md-input">
                            </div>
                             <div class="uk-form-row">
                                                         <label>Product Brand</label>

<input type="text" name="brand" id="brand"   title="Enter Product brand" class="md-input">
                            </div>
<div class="uk-form-row">
                                                                                          <label>Product Color</label>

<input type="text" name="colour" id="colour"  title="Enter Product Color" class="md-input">
                            </div>
                              <div class="uk-form-row">  <label>Product Applicationmodal</label>
<input type="text" name="applicationmodal" id="applicationmodal"   title="Enter applicationmodal" class="md-input">
                            </div>
                             <div class="uk-form-row">
                                   <label>Product Modalno</label>
<input type="text" name="modalno" id="modalno" title="Enter Product Modalno" class="md-input">
                            </div>
                        </div>
                        <div class="uk-width-medium-1-3">
                             
                           
  <div class="uk-form-row">
                                         <label>Product ApplicableMFD</label>

<input type="text" name="applicablemfd"  data-uk-datepicker="{format:'DD.MM.YYYY'}" id="applicablemfd"   title="Enter ApplicableMFD" class="md-input">
                            </div>
                             <div class="uk-form-row">
                                                                          <label>Product MFD</label>

<input type="text" name="mfd" id="mfd"  data-uk-datepicker="{format:'DD.MM.YYYY'}"   title="Enter MFD" class="md-input">
                            </div>
                             <div class="uk-form-row">
                           <label>Product EXP-Date applicable</label>

<input type="text" name="expdateapplicable" id="expdateapplicable"  data-uk-datepicker="{format:'DD.MM.YYYY'}"   title="Enter EXP Dateapplicable" class="md-input">
                        
                             </div>
                             <div class="uk-form-row">
                           <label>EXP-Date</label>

<input type="text" name="expdate" id="expdate"  data-uk-datepicker="{format:'DD.MM.YYYY'}"   title="Enter EXP-Date" class="md-input">
                        
                             </div>
                              <div class="uk-form-row">
                           <label>Place</label>

<input type="text" name="place" id="place"  title="Enter place" class="md-input">
                        
                             </div>
<div class="uk-form-row">
                           <label>HSN</label>

<input type="text" name="hsn" id="hsn"  title="Enter HSN Code" class="md-input">
                        
                             </div>
                            <div class="uk-form-row">
                           <label>Item Code</label>

<input type="text" name="itemcode" id="itemcode"  title="Enter itemcode" class="md-input">
                        
                             </div>
                             <div class="uk-form-row">
                           <label>Bar Code</label>

<input type="text" name="barcode" id="barcode"  title="Enter barcode" class="md-input">
                        
                             </div>
                             <div class="uk-form-row">
                           <label>Mini Qty Alert</label>

<input type="number" name="minqty" id="minqty"  title="Enter Mini Qty Alert" class="md-input">
                        
                             </div>

                        </div>
                        <div class="uk-width-medium-1-3">
                           
                            
                             <div class="uk-form-row">
                           <label>Product Size</label>

<input type="number" name="size" id="size"  title="Enter size" class="md-input">
                        
                             </div>
                             <div class="uk-form-row">
                           <label>Product Rate</label>

<input type="text" name="Rate" id="Rate"  title="Enter Rate" class="md-input">
                        
                             </div>
                              <div class="uk-form-row">
                           <label>DEF Date</label>

<input type="text" name="DEFdate" id="DEFdate" data-uk-datepicker="{format:'DD.MM.YYYY'}"   title="Enter DEF date" class="md-input">
                        
                             </div>
                              <div class="uk-form-row">

<input type="file" name="img1" id="img1" class="md-input">
                         <span class="uk-form-help-block" style="color: red">Select Image 1</span>

                             </div>
                            <div class="uk-form-row">

<input type="file" name="img2" id="img2" class="md-input">
                                                 <span class="uk-form-help-block" style="color: red">Select Image 2</span>

                             </div>
                            <div class="uk-form-row">

<input type="file" name="img3" id="img3" class="md-input">
                                                 <span class="uk-form-help-block" style="color: red">Select Image 3</span>

                             </div>
                            <div class="uk-form-row">

<input type="file" name="img4" id="img4" class="md-input">
                                                 <span class="uk-form-help-block" style="color: red">Select Image 4</span>

                             </div>
                              <div class="uk-form-row">

<input type="file" name="img5" id="img5" class="md-input">
                                                 <span class="uk-form-help-block" style="color: red">Select Video</span>

                             </div>
                            
                           
                        </div>
                    </div>
                        <br>

                                                <div class="uk-width-medium-1">
                                                     <div class="uk-form-row" style="    text-align: center;" >
                            <textarea id="content" cols="100" rows="7" name="content"></textarea> 
                                                     </div>
                                                    <div class="uk-form-row" style="    text-align: center;" >

                             <button type="submit" name="save" id="save" class="md-btn">Save Product Detail</button>
                           
                             </div>
                                                </div>
                             
                    </div>
                </form>

            </div>                       

            <div id="section3" style="display: none">


                <div class="row" id="tableDatalist" >
                    <div style="overflow-x:auto;">
                        <div class="dragscroll" style="width: 100%; height: 415px; overflow: scroll; cursor: grab; cursor : -o-grab; cursor : -moz-grab; cursor : -webkit-grab;">

                            <table width="250%" class="" id="example" style="">

                                <thead style="border-bottom: 1px solid gray">
                                    <tr>
                                        <th style="width: 3%">#</th>

                                        <th style="text-align: left;width: 10%">Category</th>

                                        <th style="width: 10%">Group</th>
                                        <th style="width: 10%">Sub-Group</th>
                                        <th style="width: 20%">Product</th>
                                        <th style="width: 10%">Style</th>
                                        <th style="width: 10%">Brand</th>
                                        <th style="width: 10%">Colour</th>
                                        <th style="width: 20%">Applicable ModelNo</th>
                                        <th style="width: 20%">Model No</th>
                                        <th style="width: 20%">Applicable MFD</th>
                                        <th style="width: 10%">MFD</th>
                                        <th style="width: 10%">ExpdateApplicable</th>
                                        <th style="width: 10% ">EXP</th>
                                        <th style="width: 10% ">Place</th>
                                        <th style="width: 10% ">hsn</th>
                                        <th style="width: 10% ">Itemcode</th>
                                        <th style="width: 10% ">Barcode</th>
                                        <th style="width: 10% ">MiniQty Alert</th>
                                        <th style="width: 10% ">size</th>
                                        <th style="width: 10% ">rate</th>
                                        <th style="width: 10% ">DEF Date</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="menu" items="${productlists}" varStatus="loop"> 
                                        <tr>
                                            <td>${loop.count}</td>
                                            <td>${menu.menuName}</td>
                                            <td>${menu.subMenuName}</td>
                                            <td>${menu.subSubMenuName}</td>
                                            <td>${menu.productName}</td>
                                            <td>${menu.style}</td>
                                            <td>${menu.brand}</td>
                                            <td>${menu.colour}</td>
                                            <td>${menu.applicableModelNo}</td>
                                            <td>${menu.modelNo}</td>
                                            <td>${menu.applicableMFD}</td>
                                            <td>${menu.manufd}</td>
                                            <td>${menu.expdateApplicable}</td>
                                            <td>${menu.expDate}</td>
                                            <td>${menu.placeofOrigin}</td>
                                            <td>${menu.hsnNo}</td>
                                            <td>${menu.itemCode}</td>
                                            <td>${menu.barCode}</td>
                                            <td>${menu.minqtyalert}</td>
                                            <td>${menu.size}</td>
                                            <td>${menu.rate}</td>
                                            <td>${menu.defDate}</td>

                                            <!--                                        <td style="float:right"><a href="">Download</a></td>-->
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>         </div>
            </div>
            <script>
                function GetProductDetail(psno){
                   var url = '<%=Utils.ConnectionUtils.getConnectionURL()%>/ProductDetail/' + psno + '';
                    $.ajax({
                        url: url,
                        type: "POST",
                        dataType: 'json',
                        ContentType: "text/html; charset=UTF-8",
                        success: function (result) {
                           $('#style').val(result.style);
                           $('#brand').val(result.brand);
                           $('#colour').val(result.colour);
                           $('#itemcode').val(result.itemCode);
                           $('#barcode').val(result.barCode);
                           $('#minqty').val(result.minqtyalert);
                           $('#applicationmodal').val(result.applicableModelNo);
                           $('#size').val(result.size);
                           $('#Rate').val(result.rate);
                           $('#hsn').val(result.hsnNo);
                           $('#DEFdate').val(result.defDate);
                           $('#place').val(result.place);
                           $('#expdateapplicable').val(result.expdateApplicable);
                           $('#expdate').val(result.expDate);
                           $('#applicablemfd').val(result.applicableMFD);
                           $('#mfd').val(result.manufd);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            console.log(XMLHttpRequest);
                            console.log(textStatus);
                            console.log(errorThrown);

                        }

                    });   
                }
             
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
                function ShowSubSubMenu(submenu) {
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
                function ShowSubSubMenuByProduct(submenu) {
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
                function reportdata(divid) {
                    var filter = "Select";
                    var Category = '0';
                    var Group = '0';
                    var subGroup = '0';
                    var itemcode = "Select";
                    var BarCode = "Select";
                    var url = '<%=Utils.ConnectionUtils.getConnectionURL()%>/AjaxPageCall/' + filter + '/' + Category + '/' + Group + '/' + subGroup + '/' + itemcode + '/' + BarCode + '';
                    jQuery.ajax({
                        type: 'POST',
                        url: url,
                        dataType: 'json', // ** ensure you add this line **
                        contentType: "application/json",
                        success: function (response) {
                            $('#' + divid).empty();
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
                            }
                            $('#' + divid).append(htmlText);

                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert("some error");
                        }
                    });
                }

         $("form#UploadProductExcel").submit(function (e) {
            //    var menus=$('.drop-display').text();
    var doSave = confirm('Are you sure you want to Upload File?');
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
    $("form#UploadSingleProductExcel").submit(function (e) {
            //    var menus=$('.drop-display').text();
    var doSave = confirm('Are you sure you want to Upload File?');
    if (doSave) {
        e.preventDefault();
        var formDat = new FormData(this);
        
        $.ajax({
            url: '${pageContext.request.contextPath}/UploadSingleProductDetail',
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

                function ViewCall() {
                    var radiotype = $('input[name="name_of_your_radiobutton"]:checked').val();
                    if (radiotype == '1')
                    {
                        $("#section2").css("display", "none")
                        $("#section3").css("display", "none")
                        $("#section1").css("display", "block")
                    }
                    if (radiotype == '2')
                    {
                        $("#section1").css("display", "none")
                        $("#section3").css("display", "none")
                        $("#section2").css("display", "block")
                    }
                    if (radiotype == '3')
                    {
                        $("#section1").css("display", "none")
                        $("#section2").css("display", "none")
                        $("#section3").css("display", "block")
                    }
                }
            </script>
  <script language="javascript1.2">
              generate_wysiwyg('content');
			$(document).ready(function() {
				ViewCall();
                                ShowSubSubMenuByProduct(0);
                               // ShowAddtextbox('product','addproduct');
			});
                        
		</script>
     
            <script src="${pageContext.request.contextPath}/DashboardResources/Project/js/product.js" />

            <%}%>
        </div>
    </body>

</html>