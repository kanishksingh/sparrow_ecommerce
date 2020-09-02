<%-- 
    Document   : css
    Created on : Oct 21, 2019, 11:43:25 PM
    Author     : Kanishk
--%>
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Dashboard/assets/img/bg.jpg" sizes="16x16">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Dashboard/assets/img/bg.jpg" sizes="32x32">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/Dashboard/bower_components/uikit/css/uikit.almost-flat.min.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Dashboard/assets/css/style_switcher.min.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Dashboard/assets/css/main.min.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Dashboard/assets/css/themes/themes_combined.min.css" media="all">


    <!-- altair admin error page -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Dashboard/assets/css/error_page.min.css" />
    
    <style>
        
/* Onbording page **/
.mt-40 {
    margin-top: 40px;
}

.production-box,.production-table {display: flex;flex-wrap: wrap;text-align: center;justify-content: center;flex-direction: column;min-height: 150px;border-radius: 5px;}

.production-box p {
    color: #40525d;
    font-size: 20px;
}

.production-box h4 {font-size: 35px;}
.text-light-blue {
    color: #63cacf;
}
.header-sky-blue .dataTables_wrapper .dataTable thead,
.header-sky-blue .dataTables_wrapper .dataTable tfoot  {
    background: var(--light-green ) !important;
}
 .header-sky-blue .dataTables_wrapper .dataTable tfoot 
 {
     color:#fff;
 }

.table-box-height-min .dataTables_wrapper .dataTable tr th,
.table-box-height-min .dataTables_wrapper .dataTable tr td {
    height: 35px;
    display: table-cell;
    vertical-align: inherit;
}

.table-top-box {
     background: var(--grey-light ) !important;
     text-align: center;
     padding: 5px 0px;
     margin-top: 20px;
     margin-bottom: -7px;
/*	 margin-right: -191px;*/
}

.table-top-box h3 {
    font-size: 25px;
    font-weight: 500;
    color: #455660;
}

    </style>