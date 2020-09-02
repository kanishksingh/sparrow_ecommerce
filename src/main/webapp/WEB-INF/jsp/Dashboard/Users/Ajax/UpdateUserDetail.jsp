<%-- 
    Document   : UpdateUserDetail
    Created on : Oct 22, 2019, 10:03:39 PM
    Author     : Kanishk
--%>


<%
    String userid = request.getParameter("username").trim();
    UserBean us = UserDetail.getUsersListDetail(userid, "", "", "", "");
    String name = us.getName();
    String designation = us.getDesignation();
    String mobile = us.getMobile();
    String usernam = us.getUserName();
    String usertyp = us.getUsertype();
    String branch = us.getBranch();
    String password = us.getPassword();
    ArrayList<Branchbean> br1 = Branch.getBranchUserWise(userid);
    boolean permission = Authentication.checkPermission(session.getAttribute("username").toString(), "updateuser");
%>
<script type="text/javascript">

    function codeAddress() {
         document.getElementById("select_demo_5").value = '<%=designation%>';
        document.getElementById("branch").value = '<%=branch%>';
    }
    window.onload = codeAddress();
</script>
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
            User Updation  not granted for you.!! Contact to Administrator;
        </p>
        <a href="#" onclick="history.go(-1);return false;">Go back to previous page</a>
    </div>
</div>
<%} else {%>
<h3 class="heading_a">Update User</h3>
<form action="${pageContext.request.contextPath}/UpdateNewUser" method="post"  name="createuser" id="createuser" onsubmit="return validate()">

    <div class="uk-grid" data-uk-grid-margin>
        <div class="uk-width-medium-1-2">
            <div class="uk-form-row">
                <label>Name</label>
                <input type="text" class="md-input" id="name" name="name" value="<%=name%>" required/>
            </div>

            <div class="uk-form-row">
                <label>Username</label>
                <input type="text" class="md-input" id="username" name="username" value="<%=usernam%>" readonly/>
            </div>

            <div class="uk-form-row">
                <label>Passsword</label>
                <input type="text" class="md-input" id="password" value="<%=password%>" name="password" required/>
            </div>
        </div>
        <div class="uk-width-medium-1-2">
            <div class="uk-form-row">
                <label>Mobile No.</label>
                <input type="number" class="md-input" id="mob" value="<%=mobile%>" name="mob" />
            </div>
            <div class="uk-form-row">
                <select id="select_demo_5" class="menucss" name="designation" title="Select Designation">
                    <option value="">Select Designation...</option>
               <option value="Manager">Manager</option>
                                            <option value="Owner">Owner</option>
                                            <option value="Account Manager">Account Manager</option>
                                            <option value="Admin">Admin</option>
                                          


                </select>
                <!--                                                                    <span class="uk-form-help-block">User Designation</span>-->

            </div>
            <div class="uk-form-row">
                <select id="branch" class="menucss" name="branch" title="Select Branch">
                    <%for (int i = 0; i < br1.size(); i++) {%>
                    <option value="<%=br1.get(i).getSno()%>"><%=br1.get(i).getBranchName()%> (<%=br1.get(i).getBranchCode()%>) Branch </option>
                    <%}
                                            br1.clear();%>

                </select>
                <!--                                                                    <span class="uk-form-help-block">User Designation</span>-->

            </div>


            <div class="uk-form-row">
                <label></label>
                <button type="submit" class="md-btn" id="save" name="save" >Update Detail</button>
            </div>
        </div>
    </div>
</form>
<%}%>