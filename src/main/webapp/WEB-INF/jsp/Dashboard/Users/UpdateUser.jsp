<%-- 
    Document   : UpdateUser
    Created on : 25 Aug, 2020, 4:32:12 PM
    Author     : KANISHK
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<%@page import="com.kanishk.ecommerce.Dao.Authentication"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    boolean usercreation = Authentication.checkPermission(session.getAttribute("username").toString(), "updateuser");
  %>
<!doctype html>
<html > 
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    </head>
    <body onload="setForm()" >
     
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
               User-Updation  not granted for you.!! Contact to Administrator;
            </p>
            <a href="#" onclick="history.go(-1);return false;">Go back to previous page</a>
        </div>
    </div>
<%} else {%>
  
<h3 class="heading_a" style="text-align: center;font-weight: bold"> ${msg}</h3>
<hr>
<form action="" method="post"  name="Userform" id="Userform"  enctype="multipart/form-data">
                            <table border="1" width="100%" cellspacing="0"> 
                            <div class="uk-grid" data-uk-grid-margin>
                                <div class="uk-width-medium-1-2">
                                    <div class="uk-form-row">
                                        <label>Name</label>
                                        <input type="text" class="md-input" id="name" name="name" required value="${usrsdetail.name}"/>
                                    </div>
                                   
                                    <div class="uk-form-row">
                                        <label>Username</label>
                                        <input type="text" class="md-input" id="username" name="username" value="${usrsdetail.userName}" readonly/>
                                    </div>
                                    <div class="uk-form-row">
                                        <label>Mobile No.</label>
                                        <input type="number" class="md-input" id="mob" name="mob" value="${usrsdetail.mobile}"/>
                                    </div>
                                  
                                </div>
                                <div class="uk-width-medium-1-2">
                                   
                                   <div class="uk-form-row">
                                        <select id="designation" style="    width: 100%;
    height: 46px;
    border: none;" name="designation" title="Select Designation">
                                            <option value="">Select Designation...</option>
                                            <option value="Manager">Manager</option>
                                            <option value="Account Manager">Account Manager</option>
                                          
                                          

                                        </select>
                                        <!--                                                                    <span class="uk-form-help-block">User Designation</span>-->

                                    </div>
                                    <div class="uk-form-row">
                                        <select id="roledef" name="roledef" style="    width: 100%;
    height: 46px;
    border: none;" title="Select User Role">
                                           <option value="">Select role...</option>
                                            <option value="U">User</option>
                                            <option value="A">Admin</option>
                                            <option value="S">Super Admin</option>
                                          
                                         
                                        </select>
                                        <!--                                                                    <span class="uk-form-help-block">User Designation</span>-->

                                    </div>
                                   <div class="uk-form-row">

                                       <input type="file" name="photo" id="photo" placeholder="Profile Photo">
                                        <span class="uk-form-help-block">Upload Profile Photo</span>

                                    </div>
                                    <div class="uk-form-row">
                <div class="errorMessage" align="center" style="color:red"> ${msgerr}</div>

                                        <button type="submit" class="md-btn" id="save" name="save" >Submit Detail</button>
                                    </div>
                                </div>
                            </div>
                            </table>
                        </form>
                 
                        <br>
                
                <div class="uk-form-row">
                    <div style="overflow-x:auto;">

                 <table id="dt_tableExport" border="" class="uk-table" cellspacing="0" width="100%" >
                                <thead>
                                    <tr>
                                        <th>Sno</th>

                                        <th>Username</th>
                                        <th>Password</th>
                                        <th>Role</th>

                                        <th>Name</th>
                                        <th>Designation</th>
                                        <th>Mobile</th>
                                        <th>Action</th>

                                    </tr>
                                </thead>

                               
                                <tbody>
                                    <c:forEach items="${usrslist}" var="users" varStatus="theCount">
                                    <tr>
                                        <td>${theCount.count}</td>
                                        <td>${users.username}</td>
                                        <td>${users.password}</td>
                                        <td>${users.userType}</td>

                                        <td>${users.name}</td>
                                        <td>${users.designation}</td>
                                        <td>${users.mobile}</td>
                                        <td><a href="${pageContext.request.contextPath}/updateuser/{${users.username}}" target="_blank"><i class="">edit</i></a>&nbsp; &nbsp; &nbsp; 
                                        <a href="" target="_blank"><i class="">delete</i></a></td>

                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                    </div>
                </div>
                         <script>
    function setForm(){
        document.getElementById("roledef").value='${usrsdetail.role}';
        document.getElementById('select_demo_5').value='${usrsdetail.designation}';
    }
          $("form#Userform").submit(function (e) {
            //    var menus=$('.drop-display').text();
    var doSave = confirm('Are you sure you want to submit?');
    if (doSave) {
        e.preventDefault();
        var formDat = new FormData(this);
        
        $.ajax({
            url: '${pageContext.request.contextPath}/UpdateUserDetail',
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
                window.location.replace("newuser");
            }
        });
    } else {
        return false;
    }

});

        </script>
       <%}%>   
                        </div>
    </body>

</html>