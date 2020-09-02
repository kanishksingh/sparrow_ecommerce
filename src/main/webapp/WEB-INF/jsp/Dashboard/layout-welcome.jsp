<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>    
<!doctype html>
<html> 
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="refresh" content="<%=session.getMaxInactiveInterval()%>;url=login" /> 

<title><tiles:insertAttribute name="title" ignore="true" /></title>    

        <%@include file="css/css.jsp"%>
    
    </head>
     <body class="disable_transitions sidebar_main_open sidebar_main_swipe">
        <div id="page_content">
            <div id="page_content_inner">

                <div class="md-card">
                    <div class="md-card-content" >
                                 <tiles:insertAttribute name="dmenu" />

                        <div id="UpdateUserid">
         
        <tiles:insertAttribute name="dbody" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
       <tiles:insertAttribute name="dfooter" />   
    </body>

</html>