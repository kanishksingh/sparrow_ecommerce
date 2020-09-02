<%-- 
    Document   : UserAssignAuthenticationTable
    Created on : 22 Apr, 2020, 11:50:51 PM
    Author     : KANISHK
--%>
<%@page import="POJO.UserDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    String branch=request.getParameter("br");
        String search=request.getParameter("search");

    ArrayList<UserBean> userlist = UserDetail.getUsersList(search, branch, "","","");

%>
<style>
    .icheckbox {
    border: 2px solid rgba(0,0,0,.54);
    border-radius: 2px;
    width: 18px;
    height: 18px;
    background: #fff;
    -webkit-transition: all .2s ease-out;
    transition: all .2s ease-out;
}

</style>
<form action="${pageContext.request.contextPath}/AuthenticationAssign" method="post"  name="createuser" id="createuser" onsubmit="return validate()">

<table id="dt_tableExport" class="uk-table" cellspacing="0" width="100%">
    
        <thead>
            <tr>
                <th colspan="8" style="text-align: center">
                 <button type="submit" class="md-btn" id="save" name="save" >Assign Rights</button>
                </th>
            </tr >
            <tr style="    background: lightgray;">
                <th>Name</th>
                <th>Block User</th>
                <th>User Authenticate</th>

                <th>Create user</th>
                <th>Update user</th>
                <th>Delete User</th>
                  <th>Create Item</th>
                <th>Update Item</th>
                <th>Delete Item</th>
                  <th>Create Party</th>
                <th>Update Party</th>
                <th>Delete Party</th>
                  <th>Create Challan</th>
                <th>Update Challan</th>
                <th>Delete Challan</th>
                <th>Print Challan</th>
                  <th>Create Invoice</th>
                <th>Update Invoice</th>
                <th>Delete Invoice</th>
                <th>Print Invoice</th>

            </tr>
        </thead>


        <tbody>
            <%
                for (int index = 0; index < userlist.size(); index++) {
                    String username=userlist.get(index).getUserName();
                    String blockstatus=userlist.get(index).getBlockedStatus();
                    String CreateUserStatus=userlist.get(index).getCreateUserStatus();
                    String UpdateUserStatus=userlist.get(index).getUpdateUserStatus();
                    String DeleteUserStatus=userlist.get(index).getDeleteUserStatus();
                    String CreateItemStatus=userlist.get(index).getCreateItemStatus();
                    String UpdateItemStatus=userlist.get(index).getUpdateItemStatus();
                    String DeleteItemStatus=userlist.get(index).getDeleteItemStatus();
                    String CreatepartyStatus=userlist.get(index).getCreatepartyStatus();
                    String UpdatepartyStatus=userlist.get(index).getUpdatepartyStatus();
                    String DeletepartyStatus=userlist.get(index).getDeletepartyStatus();
                    String CreatechallanStatus=userlist.get(index).getCreatechallanStatus();
                    String UpdatechallanStatus=userlist.get(index).getUpdatechallanStatus();
                    String DeletechallanStatus=userlist.get(index).getDeletechallanStatus();
                   String PrintchallanStatus=userlist.get(index).getPrintchallanStatus();

                    String CreateinvoiceStatus=userlist.get(index).getCreateinvoiceStatus();
                    String UpdateinvoiceStatus=userlist.get(index).getUpdateinvoiceStatus();
                    String DeleteinvoiceStatus=userlist.get(index).getDeleteinvoiceStatus();
                        String PrintinvoiceStatus=userlist.get(index).getPrintinvoiceStatus();
                String AuthenticationUserStatus=userlist.get(index).getAuthenticationUserStatus();
                    String BlockedCheckedUncheck=userlist.get(index).getBlockedCheckedUncheck();
                    String CreateUserCheckedUncheck=userlist.get(index).getCreateUserCheckedUncheck();
                    String UpdateUserCheckedUncheck=userlist.get(index).getUpdateUserCheckedUncheck();
                    String DeleteUserCheckedUncheck=userlist.get(index).getDeleteUserCheckedUncheck();
                    String CreateItemCheckedUncheck=userlist.get(index).getCreateItemCheckedUncheck();
                    String UpdateItemCheckedUncheck=userlist.get(index).getUpdateItemCheckedUncheck();
                    String DeleteItemCheckedUncheck=userlist.get(index).getDeleteItemCheckedUncheck();
                    String CreatepartyCheckedUncheck=userlist.get(index).getCreatepartyCheckedUncheck();
                    String UpdatepartyCheckedUncheck=userlist.get(index).getUpdatepartyCheckedUncheck();
                    String DeletepartyCheckedUncheck=userlist.get(index).getDeletepartyCheckedUncheck();
                    String CreatechallanCheckedUncheck=userlist.get(index).getCreatechallanCheckedUncheck();
                    String UpdatechallanCheckedUncheck=userlist.get(index).getUpdatechallanCheckedUncheck();
                    String DeletechallanCheckedUncheck=userlist.get(index).getDeletechallanCheckedUncheck();
                    String PrintchallanCheckedUncheck=userlist.get(index).getPrintchallanCheckedUncheck();
                       String CreateinvoiceCheckedUncheck=userlist.get(index).getCreateinvoiceCheckedUncheck();
                    String UpdateinvoiceCheckedUncheck=userlist.get(index).getUpdateinvoiceCheckedUncheck();
                    String DeleteinvoiceCheckedUncheck=userlist.get(index).getDeleteinvoiceCheckedUncheck();
                    String PrintinvoiceCheckedUncheck=userlist.get(index).getPrintinvoiceCheckedUncheck();
                    String AuthenticationUserCheckedUncheck=userlist.get(index).getAuthenticationUserCheckedUncheck();
            %>   
            <tr>
                <td><%=userlist.get(index).getName()%></td>
                <td><input type="checkbox" class="icheckbox" name="block" id="block" value="<%=username%>" <%=BlockedCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="authenticate" id="authenticate" value="<%=username%>" <%=AuthenticationUserCheckedUncheck%>></td>

                <td><input type="checkbox" class="icheckbox" name="createuser" id="createuser" value="<%=username%>" <%=CreateUserCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="updateuser" id="updateuser" value="<%=username%>" <%=UpdateUserCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="deleteuser" id="deleteuser" value="<%=username%>" <%=DeleteUserCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="createitem" id="createitem" value="<%=username%>" <%=CreateItemCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="updateitem" id="updateitem" value="<%=username%>" <%=UpdateItemCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="deleteitem" id="deleteitem" value="<%=username%>" <%=DeleteItemCheckedUncheck%>></td>
  <td><input type="checkbox" class="icheckbox" name="createparty" id="createparty" value="<%=username%>" <%=CreatepartyCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="updateparty" id="updateparty" value="<%=username%>" <%=UpdatepartyCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="deleteparty" id="deleteparty" value="<%=username%>" <%=DeletepartyCheckedUncheck%>></td>
 <td><input type="checkbox" class="icheckbox" name="createchallan" id="createchallan" value="<%=username%>" <%=CreatechallanCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="updatechallan" id="updatechallan" value="<%=username%>" <%=UpdatechallanCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="deletechallan" id="deletechallan" value="<%=username%>" <%=DeletechallanCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="printchallan" id="printchallan" value="<%=username%>" <%=PrintchallanCheckedUncheck%>></td>

                <td><input type="checkbox" class="icheckbox" name="createinvoice" id="createinvoice" value="<%=username%>" <%=CreateinvoiceCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="updateinvoice" id="updateinvoice" value="<%=username%>" <%=UpdateinvoiceCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="deleteinvoice" id="deleteinvoice" value="<%=username%>" <%=DeleteinvoiceCheckedUncheck%>></td>
                <td><input type="checkbox" class="icheckbox" name="printinvoice" id="printinvoice" value="<%=username%>" <%=PrintinvoiceCheckedUncheck%>></td>


            </tr>
            <%}%>
            <tr>
                <td colspan="8" style="text-align: center">
                    <input type="hidden" name="branch" id="branch" value="<%=branch%>">
                 <button type="submit" class="md-btn" id="save" name="save" >Assign Rights</button>
                </td>
            </tr >
        </tbody>
    </table>
</form>