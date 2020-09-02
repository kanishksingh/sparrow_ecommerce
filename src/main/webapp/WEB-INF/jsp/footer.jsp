<%-- 
    Document   : footer
    Created on : 4 Jun, 2020, 6:10:08 PM
    Author     : KANISHK
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.news9live_spring_jdbc.Beans.Menu"%>
<%@page import="com.mycompany.news9live_spring_jdbc.Dao.MenuDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <!--Footer start-->
    <footer>
      <!--Footer content-->
      <div id="footer" class="footer-dark bg-dark bg-footer py-5 px-3">
        <div class="container">
          <div class="row">
            <!-- left widget -->
            <div class="widget col-md-3">
              <h3 class="h5 widget-title border-bottom border-smooth">About Us</h3>
              <div class="widget-content">
    <ul class="footerTopList">
        	<%
                            MenuDao dao=new MenuDao();
                        List<Menu> list = dao.getMenus();
                        for(Menu e:list){                    
                        int menusno=e.getSno();
                      %>
                
	<li><a target="_blank" rel="nofollow" href="#"><%=e.getMenuName()%></a></li>
	<%}%>
</ul>

              
              </div>
            </div>
            <!-- center widget -->
            <div class="widget  col-md-3">
              <h3 class="h5 widget-title border-bottom border-smooth">Popular Posts</h3>
                <div class="widget-content">
             <ul class="footerTopList">
              <li><a target="_blank" rel="nofollow" href="#" title="Live TV">Live TV</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="न्यूज़">न्यूज़</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="राज्यों से">राज्यों से</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="खेल">खेल</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="करियर">करियर</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="स्त्री">स्त्री</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="फोटो">फोटो</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="वीडियो">वीडियो</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="मूवी मसाला">मूवी मसाला</a></li>
            </ul>
                
              
              </div>
            </div>

            <div class="widget  col-md-3">
              <h3 class="h5 widget-title border-bottom border-smooth">Popular Posts</h3>
                <div class="widget-content">
                
                 <ul class="footerTopList">
              <li><a target="_blank" rel="nofollow" href="#" title="गैजेट">गैजेट</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="जुर्म">जुर्म</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="धर्म">धर्म</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="कार्यक्रम">कार्यक्रम</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="आर्काइव">आर्काइव</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="निवेशक">निवेशक</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="BREAKING NEWS">BREAKING NEWS</a></li>
              <li><a target="_blank" rel="nofollow" href="#" title="ख़बरें अब तक">ख़बरें अब तक</a></li>
            </ul>
              
              </div>
            </div>
            <!-- right widget -->
            <div class="widget col-md-3">
              <h3 class="h5 widget-title border-bottom border-smooth">Editor Picks</h3>
              <div class="posts-list">
                <!--post list-->
                <article class="card card-full hover-a mb-4">
                  <div class="row">
                    <!--thumbnail-->
                    <div class="col-3 col-md-4 pr-2 pr-md-0">
                      <div class="ratio_115-80 image-wrapper">
                        <a href="../post/single1.html">
                          <img class="img-fluid lazy" src="${pageContext.request.contextPath}/resources/images/lazy-empty.png" data-src="../../assets/img/115x80/img3.jpg" alt="Image description">
                        </a>
                      </div>
                    </div>
                    <!-- title & date -->
                    <div class="col-9 col-md-8">
                      <div class="card-body pt-0">
                        <h3 class="card-title h6">
                          <a href="../post/single1.html">The West Hoped for Democracy in Turkey. Erdogan Had Other Ideas</a>
                        </h3>
                        <div class="card-text small text-muted">
                          <time datetime="2019-10-16">Oct 16, 2019</time>
                        </div>
                      </div>
                    </div>
                  </div>
                </article>
                <!--post list-->
                <article class="card card-full hover-a mb-4">
                  <div class="row">
                    <!--thumbnail-->
                    <div class="col-3 col-md-4 pr-2 pr-md-0">
                      <div class="ratio_115-80 image-wrapper">
                        <a href="../post/single1.html">
                          <img class="img-fluid lazy" src="${pageContext.request.contextPath}/resources/images/lazy-empty.png" data-src="../../assets/img/115x80/img2.jpg" alt="Image description">
                        </a>
                      </div>
                    </div>
                    <!-- title & date -->
                    <div class="col-9 col-md-8">
                      <div class="card-body pt-0">
                        <h3 class="card-title h6">
                          <a href="../post/single1.html">Dale Earnhardt Jr. Returns to the Racetrack and an Old Hobby</a>
                        </h3>
                        <div class="card-text small text-muted">
                          <time datetime="2019-10-15">Oct 15, 2019</time>
                        </div>
                      </div>
                    </div>
                  </div>
                </article>
                <!--post list-->
                <article class="card card-full hover-a mb-4">
                  <div class="row">
                    <!--thumbnail-->
                    <div class="col-3 col-md-4 pr-2 pr-md-0">
                      <div class="ratio_115-80 image-wrapper">
                        <a href="../post/single1.html">
                          <img class="img-fluid lazy" src="${pageContext.request.contextPath}/resources/images/lazy-empty.png" data-src="../../assets/img/115x80/img1.jpg" alt="Image description">
                        </a>
                      </div>
                    </div>
                    <!-- title & date -->
                    <div class="col-9 col-md-8">
                      <div class="card-body pt-0">
                        <h3 class="card-title h6">
                          <a href="../post/single1.html">Yankees Party Like It’s 1998, at Least for a Day</a>
                        </h3>
                        <div class="card-text small text-muted">
                          <time datetime="2019-10-14">Oct 14, 2019</time>
                        </div>
                      </div>
                    </div>
                  </div>
                </article>
                <!--End post list-->
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--End footer content-->
 
      <!-- footer copyright menu -->
      <div class="footer-copyright footer-dark bg-dark">
        <div class="container">
          <!--Navbar-->
          <nav class="navbar navbar-expand navbar-dark px-0">
            <!--footer left menu-->
            <ul class="navbar-nav footer-nav text-center first-left-lg-0">
              <li class="nav-item"><a class="nav-link" href="about-us.html">About</a></li>
              <li class="nav-item"><a class="nav-link" href="contact-us.html">Contact</a></li>
              <li class="nav-item"><a class="nav-link" href="term-of-use.html">Term Of Use</a></li>
              <li class="nav-item"><a class="nav-link" href="privacy-policy.html">Privacy</a></li>
              <li class="nav-item"><a class="nav-link" href="#" target="_blank">Buy Now</a></li>
            </ul>
            <!--end footer left menu-->
            <!-- footer copyright -->
            <ul class="navbar-nav ml-auto text-center">
              <li class="d-inline navbar-text">Copyright &copy; News9india - All rights reserved.</li>
            </ul>
            <!-- end footer copyright -->
          </nav>
          <!-- End Navbar -->
        </div>
      </div>
      <!--end copyright menu-->


    </footer>
    <!-- End Footer -->
    
