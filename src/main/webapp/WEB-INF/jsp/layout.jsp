<%-- 
    Document   : home
    Created on : 4 Jun, 2020, 5:32:55 PM
    Author     : KANISHK
--%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"    
"http://www.w3.org/TR/html4/loose.dtd">    
<html>    
<head>    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
<title><tiles:insertAttribute name="title" ignore="true" /></title>    
 <meta name="description" content="News9india">
  <meta name="keywords" content="News9india">
  <!--Required Meta Tags-->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <!--Robot meta tags-->
  <meta name="robots" content="index, follow">
  <meta name="googlebot" content="index, follow">
    <!--Favicon-->
      <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/DashboardResources/assets/img/bg.jpg" sizes="16x16">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/DashboardResources/assets/img/bg.jpg" sizes="32x32">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" type="image/x-icon">
  <link rel="apple-touch-icon" sizes="57x57" href="${pageContext.request.contextPath}/resources/images/apple-icon-57x57.png">
  <link rel="apple-touch-icon" sizes="60x60" href="${pageContext.request.contextPath}/resources/images/apple-icon-60x60.png">
  <link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/resources/images/apple-icon-72x72.png">
  <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/resources/images/apple-icon-76x76.png">
  <link rel="apple-touch-icon" sizes="114x114" href="${pageContext.request.contextPath}/resources/images/apple-icon-114x114.png">
  <link rel="apple-touch-icon" sizes="120x120" href="${pageContext.request.contextPath}/resources/images/apple-icon-120x120.png">
  <link rel="apple-touch-icon" sizes="144x144" href="${pageContext.request.contextPath}/resources/images/apple-icon-144x144.png">
  <link rel="apple-touch-icon" sizes="152x152" href="${pageContext.request.contextPath}/resources/images/apple-icon-152x152.png">
  <link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/resources/images/apple-icon-180x180.png">
  <link rel="icon" type="image/png" sizes="192x192" href="${pageContext.request.contextPath}/resources/images/android-icon-192x192.png">
  <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/resources/images/favicon-32x32.png">
  <link rel="icon" type="image/png" sizes="96x96" href="${pageContext.request.contextPath}/resources/images/favicon-96x96.png">
  <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/resources/images/favicon-16x16.png">
  <meta name="theme-color" content="#ffffff">
  <!--Google Fonts-->
  <!--<link href="//fonts.googleapis.com/css?family=Open+Sans%3A300%2C400%2C400i%2C600%2C700%7CRoboto%3A400%2C400i%2C500%2C700" rel="stylesheet">-->
  <!--Bootstrap Styles-->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
  <!--Vendor Styles-->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/all.min.css">
  <!--Theme Styles-->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/theme.css">
  <!--Bootstrap customizer-->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-customizer.css">
</head>    
<body class="bg-repeat font-family">
  <!--Skippy-->
  <a id="skippy" class="sr-only sr-only-focusable" href="#content">
    <div class="container">
      <span class="skiplink-text">Skip to main content</span>
    </div>
  </a>
  <!--Background image-->
  <div class="bg-image"></div>
  <!-- ========== WRAPPER ========== -->
  <div class="wrapper">
   
 
       <tiles:insertAttribute name="header" /> 
        
    <tiles:insertAttribute name="menu" />
        <tiles:insertAttribute name="body" />
        
     <tiles:insertAttribute name="footer" />
  </div>
         <!-- ========== END WRAPPER ========== -->
  <!--Back to top-->
  <a class="material-scrolltop back-top btn btn-light border position-fixed r-1 b-1" href="#"><i class="fa fa-arrow-up"></i></a>

 

  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

  <!--Vendor js-->
  <script src="${pageContext.request.contextPath}/resources/js/sticky-kit.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/lazyload.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/SmoothScroll.js"></script>

  <!--Bootstrap Customizer js-->
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap-customizer.js"></script>
  <!--Theme js-->
  <script src="${pageContext.request.contextPath}/resources/js/theme.js"></script>
</body>    
</html>