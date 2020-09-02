<%-- 
    Document   : Tabdata
    Created on : 17 Jul, 2020, 11:44:05 PM
    Author     : KANISHK
--%>

<%@page import="com.mycompany.news9live_spring_jdbc.Dao.NewsDao"%>
<%@page import="com.mycompany.news9live_spring_jdbc.Beans.News"%>
<%@page import="java.util.List"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
int menusn=Integer.parseInt(request.getParameter("men"));
int submenusn=Integer.parseInt(request.getParameter("submen"));
int count=Integer.parseInt(request.getParameter("count"));
  NewsDao newsdao=new NewsDao();     
List<News> nnlist = newsdao.viewKhabarByPage("",menusn,submenusn,0,6);

%>
<!DOCTYPE html>

<div class="tab-pane active" id="tab<%=count%>">
    <div class="row" style="width: 100%">
        <!--post left start-->
        <!--end post left-->
            <!--post list-->
            <%for(News e:nnlist){%>
        <div class="col-md-6">

            <article class="card card-full hover-a mb-4">
                <div class="row">
                    <!--thumbnail-->
                    <div class="col-4 col-md-4 pr-2 pr-md-0">
                        <div class="ratio_115-80 image-wrapper">
                            <a href="newsdetail/<%=e.getSno()%>/<%=menusn%>">
                                <img class="img-fluid lazy" style="    height: 73px;" src="${pageContext.request.contextPath}/<%=e.getFolderPath()%>/<%=e.getImagefirstName()%>" data-src="${pageContext.request.contextPath}/<%=e.getFolderPath()%>/<%=e.getImagefirstName()%>" alt="<%=e.getImagefirstName()%>">
                            </a>
                        </div>
                    </div>
                    <!-- title & date -->
                    <div class="col-8 col-md-8">
                        <div class="card-body pt-0">
                            <h3 class="card-title h6 h5-sm h6-lg">
                                                            <a href="newsdetail/<%=e.getSno()%>/<%=menusn%>"><%=e.getTitle()%></a>
                            </h3>
                            <div class="card-text small text-muted">
                                <time datetime="<%=e.getEnteredOn()%>"><%=e.getEnteredOn()%></time>
                            </div>
                        </div>
                    </div>
                </div>
            </article>
            <!--post list-->
                    </div>

           <%}%>
         
     
        <!--end post right list-->
    </div>
           <div class="pull-right"><a href="CategorywiseNews/<%=menusn%>"><b>और देखो</b></a></div>

</div>
