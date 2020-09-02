<%-- 
    Document   : footer
    Created on : 29 May, 2020, 2:07:28 PM
    Author     : KANISHK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
  

</script>
<script>
function exportTableToExcel(tableId, filename) {
let dataType = 'application/vnd.ms-excel';
let extension = '.xls';
let base64 = function(s) {
return window.btoa(unescape(encodeURIComponent(s)))
};
let template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>';                       let render = function(template, content) {
return template.replace(/{(\w+)}/g, function(m, p) { return content[p]; });
};
let tableElement = document.getElementById(tableId);
let tableExcel = render(template, {
worksheet: filename,
table: tableElement.innerHTML
});

filename = filename + extension;

if (navigator.msSaveOrOpenBlob)
{
let blob = new Blob(
[ '\ufeff', tableExcel ],
{ type: dataType }
);

navigator.msSaveOrOpenBlob(blob, filename);
} else {
let downloadLink = document.createElement("a");

document.body.appendChild(downloadLink);

downloadLink.href = 'data:' + dataType + ';base64,' + base64(tableExcel);

downloadLink.download = filename;

downloadLink.click();
}
}
</script>    
<script src="${pageContext.request.contextPath}/DashboardResources/assets/js/common.min.js"></script>
<script src="${pageContext.request.contextPath}/DashboardResources/assets/js/uikit_custom.min.js"></script>
<script src="${pageContext.request.contextPath}/DashboardResources/assets/js/techgenysis_admin_common.min.js"></script>
<script src="${pageContext.request.contextPath}/DashboardResources/bower_components/peity/jquery.peity.min.js"></script>
<script src="${pageContext.request.contextPath}/DashboardResources/bower_components/handlebars/handlebars.min.js"></script>
<script src="${pageContext.request.contextPath}/DashboardResources/assets/js/custom/handlebars_helpers.min.js"></script>
<script src="${pageContext.request.contextPath}/DashboardResources/bower_components/clndr/clndr.min.js"></script>
<script src="${pageContext.request.contextPath}/DashboardResources/assets/js/pages/dashboard.min.js"></script>
        <script src="${pageContext.request.contextPath}/DashboardResources/js/ajaxloaderstart_stop.js"></script>
