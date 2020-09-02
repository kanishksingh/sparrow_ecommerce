<%-- 
    Document   : NewUsers
    Created on : Oct 22, 2019, 12:20:46 AM
    Author     : Kanishk
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<%@page import="com.kanishk.ecommerce.Dao.Authentication"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    boolean usercreation = Authentication.checkPermission(session.getAttribute("username").toString(), "createuser");
  %>
<!doctype html>
<html > 
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

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
                        <h3 class="heading_a"> ${msg}</h3>
                        <form action="${pageContext.request.contextPath}/saveuser" method="post"  name="Userform" id="Userform" onsubmit="">
                            <div class="uk-grid" data-uk-grid-margin>
                                <div class="uk-width-medium-1-2">
                                    <div class="uk-form-row">
                                        <label>Name</label>
                                        <input type="text" class="md-input" id="name" name="name" required/>
                                    </div>
                                   
                                    <div class="uk-form-row">
                                        <label>Username</label>
                                        <input type="text" class="md-input" id="username" name="username" required/>
                                    </div>
                                    <div class="uk-form-row">
                                        <label>Passsword</label>
                                        <input type="password" class="md-input" id="password" name="password" required/>
                                    </div>
                                  
                                </div>
                                <div class="uk-width-medium-1-2">
                                    <div class="uk-form-row">
                                        <label>Mobile No.</label>
                                        <input type="number" class="md-input" id="mob" name="mob" />
                                    </div>
                                   <div class="uk-form-row">
                                        <select id="select_demo_5" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'right'}" name="designation" title="Select Designation">
                                            <option value="">Select Designation...</option>
                                            <option value="Manager">Manager</option>
                                            <option value="Account Manager">Account Manager</option>
                                          
                                          

                                        </select>
                                        <!--                                                                    <span class="uk-form-help-block">User Designation</span>-->

                                    </div>
                                    <div class="uk-form-row">
                                        <select id="roledef" name="roledef" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'top'}"  title="Select User Role">
                                           <option value="">Select role...</option>
                                            <option value="U">User</option>
                                            <option value="A">Admin</option>
                                            <option value="S">Super Admin</option>
                                          
                                         
                                        </select>
                                        <!--                                                                    <span class="uk-form-help-block">User Designation</span>-->

                                    </div>
                                    
                                    <div class="uk-form-row">
                <div class="errorMessage" align="center" style="color:red"> ${msgerr}</div>

                                        <button type="submit" class="md-btn" id="save" name="save" >Submit Detail</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                 
                        
                
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
                                        <td><a href="${pageContext.request.contextPath}/updateuser/${users.username}" target="_blank">${users.username}</a></td>
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
       <%}%>   
                        </div>
    </body>

</html>