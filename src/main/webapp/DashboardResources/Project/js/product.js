/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function down_file(url,name){
var a = $("<a>")
    .attr("href", url)
    .attr("download", name)
    .appendTo("body");
a[0].click();
a.remove();
}
     function toggleAll(cb)
            {
                var val = cb.checked;
                var frm = document.forms[0];
                var len = frm.elements.length;
                var i = 0;
                for (i = 0; i < len; i++)
                {
                    if (frm.elements[i].type == "checkbox" && frm.elements[i] != cb)
                    {
                        frm.elements[i].checked = val;
                    }
                }
            }
            

       

