<%-- 
    Document   : menu
    Created on : 4 Jun, 2020, 6:10:19 PM
    Author     : KANISHK
--%>
<%@page import="com.kanishk.ecommerce.Dao.MenuDao"%>
<%@page import="java.util.List"%>
<%@page import="com.kanishk.ecommerce.Beans.Menu"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!--Main menu-->
   <!--Main menu-->
    <div id="showbacktop" class="full-nav bg-dark-blue border-none border-lg-1 border-bottom shadow-b-sm py-0">
      <div class="container">
        <nav id="main-menu" class="main-menu navbar navbar-expand-lg navbar-light px-2 px-lg-0 py-0">
          <!--Navbar menu-->
          <div id="navbarTogglerDemo1" class="collapse navbar-collapse hover-mode">
            <!-- logo in navbar -->
            <div class="logo-showbacktop">
              <a href="/">
                <img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/default-logo.png" alt="Logo site">
              </a>
            </div>

            <!--left main menu start-->
            <ul id="left-main" class="navbar-nav navbar-uppercase main-nav navbar-style-1 first-left-lg-0">
              <li class="nav-item">
                <a id="navbarhome" class="nav-link active" href="${pageContext.request.contextPath}/"><i class="fa fa-home" aria-hidden="true"></i></a>
                <!--link-->
                
                <!-- end link -->
              </li>
			<%
                            MenuDao dao=new MenuDao();
                        List<Menu> list = dao.getMenus();
                        for(Menu e:list){                    
                        int menusno=e.getSno();
                        String type="",type1="";
                        List<Menu> list1 = dao.getSubMenuByMenu(menusno);
                        if(list1.size()>0){type="dropdown-toggle";type1="data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'";}
                        %>
                  <li class="nav-item">
                            
			<a id="navbarhome" class="nav-link <%=type%>" href="#" <%=type1%>> <%=e.getMenuName()%></a>
			<!--link-->
                        <% if(list1.size()>0){%>
                        <ul class="dropdown-menu" aria-labelledby="navbarhome">
                            <%for(Menu e1:list1){%>
			<li><a class="dropdown-item" href="#"><%=e1.getSubMenuName()%></a></li> 
                        <%}%>
			</ul>
                        <%}%>

			<!-- end link -->
			</li>
       <%}%>

				


  <!--end dropdown menu-->
            </ul>
            <!--end left main menu-->

            <!--Search form-->
            <div class="navbar-nav ml-auto d-none d-lg-block">
              <div class="dropdown dropleft">

  <a href="#" class="live-btn btn">LIVE <div class="blob white"></div></a> 


                <!--button-->
                <!-- <button id="dropdownMenuButton" class="btn btn-light bg-transparent border-0" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="fa fa-search"></i>
                </button> -->
                <!--hide search-->
                <div class="dropdown-menu no-shadow border-0 py-0" aria-labelledby="dropdownMenuButton">
                  <form class="form-inline" action="../category/search.html">
                    <div class="input-group">
                      <input type="text" class="form-control" name="keywords" value="" placeholder="Search..." aria-label="search">
                    </div>
                  </form>
                </div>
                <!--end hide search-->
              </div>
            </div>
            <!--end search form-->
          </div>
          <!--End navbar menu-->
        </nav>
      </div>
    </div>
    <!-- End main menu -->

    <!-- sidebar mobile menu -->
    <div class="mobile-side">
      <!--Left Mobile menu-->
      <a href="#" class="back-menu">
        <span class="hamburger-icon open">
          <span></span><span></span><span></span><span></span>
        </span>
      </a>
      <nav id="mobile-menu" class="menu-mobile d-flex flex-column push push-left shadow-r-sm bg-white">
        <!-- mobile menu content -->
        <div class="mobile-content mb-auto">
          <!--logo-->
          <div class="logo-sidenav p-2">
            <a href="index.html">
              <img src="${pageContext.request.contextPath}/resources/images/default-logo.png" class="img-fluid" alt="logo">
            </a>
          </div>
          <!--navigation-->
          <div class="sidenav-menu">
            <nav class="navbar navbar-inverse">
              <ul id="side-menu" class="nav navbar-nav list-group list-unstyled side-link">
                <li class="menu-item nav-item"><a href="${pageContext.request.contextPath}/" class="nav-link active">Home</a></li>
                <!-- dropdown menu start -->
                	<%
                        for(Menu e:list){                    
                        int menusno=e.getSno();
                        String type="",type1="";
                        List<Menu> list1 = dao.getSubMenuByMenu(menusno);
                        if(list1.size()>0){type="menu-item menu-item-has-children dropdown mega-dropdown";
                        type1="data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' class='dropdown-toggle nav-link' id='menu-1"+menusno+"'";}
                        %>
                  <li class="<%=type%> nav-item">
                            
			<a  href="#" <%=type1%>> <%=e.getMenuName()%></a>
			<!--link-->
                        <% if(list1.size()>0){%>
                        <ul class="dropdown-menu" aria-labelledby="menu-1<%=menusno%>" role="menu">
                            <%for(Menu e1:list1){%>
			<li class="menu-item nav-item"><a href="#"><%=e1.getSubMenuName()%></a></li> 
                        <%}%>
			</ul>
                        <%}%>

			<!-- end link -->
			</li>
       <%}%>
               </ul>
            </nav>
          </div>
        </div>
        <!-- copyright mobile sidebar menu -->
        <div class="mobile-copyright mt-5 text-center">
          <p>Copyright News9india - All right reserved</p>
        </div>
      </nav>
    </div>
    <!-- end sidebar mobile menu -->

