<%-- 
    Document   : Authentication
    Created on : 22 Apr, 2020, 8:56:00 PM
    Author     : KANISHK
--%>

<%@page import="com.kanishk.ecommerce.Dao.Authentication"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    boolean permission = Authentication.checkPermission(session.getAttribute("username").toString(), "userauthentication");
  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!doctype html>
<html > 
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

    </head>
    <body >
     
                        <div id="UpdateUserid">
                            <%     if (permission == false) {%>
                     <div class="error_page_header">
        <div class="uk-width-8-10 uk-container-center">
            500!
        </div>
    </div>
    <div class="error_page_content">
        <div class="uk-width-8-10 uk-container-center">
            <p class="heading_b">Oops! Something went wrong...</p>
            <p class="uk-text-large">
               User Authentication  not granted for you.!! Contact to Administrator;
            </p>
            <a href="#" onclick="history.go(-1);return false;">Go back to previous page</a>
        </div>
    </div>
<%} else {%>
                        <h3 class="heading_a">Assign User Authentication</h3>
                        <form action="" method="post"  name="createuser" id="createuser" onsubmit="return validate()">

                            <div>
                                <div class="row">
                                   <div class="col-md-4">

                                    <select id="branch" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'right'}" onchange="BranchWiseUsers()" name="branch" title="Select Branch">
                                       
                                            
                                        </select>
                                                        <span class="uk-form-help-block" style="color: red" id="brancherror"></span>                
                                   
                                   </div>
                                   <div class="col-md-4">

                                    <select id="username" data-md-selectize data-md-selectize-bottom data-uk-tooltip="{pos:'right'}" onchange="BranchWiseUsers()" name="username" title="Select User">
                                        <option value="">Select User</option>  
                                      
                                            
                                        </select>
                                   
                                   </div>                   
                                  
                                                        
                                    
                                    <div class="col-md-4" style="text-align: center;">
                                        <button type="button" onclick="BranchWiseUsers();"  class="md-btn" id="save" name="save" >Show Users</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                      
                        <hr>                       
                        <div class="uk-form-row" style="overflow-x: scroll" id="tableDatalist">
                           
                        </div>
                         
                  
             
        <script>
            
function BranchWiseUsers() {
        var branch=$('#branch').val();

    if(branch=="0"){document.getElementById("brancherror").innerHTML="Select Branch";}else{
    var search=$('#username').val();
    $.ajax({
        type: "post",
        url: "Ajax/UserAssignAuthenticationTable.jsp", //this is my servlet
        data:{br:branch,search:search},
             success: function (msg) {
            $('#tableDatalist').empty();
            $('#tableDatalist').append(msg);
document.getElementById("brancherror").innerHTML="";
        }
    });
    }
}
        </script>
          <%}%>      
                        </div>
    </body>
</html>
