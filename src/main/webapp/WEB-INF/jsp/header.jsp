<%-- 
    Document   : header
    Created on : 4 Jun, 2020, 6:09:54 PM
    Author     : KANISHK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    function getDateTime() {
        var now     = new Date(); 
        var year    = now.getFullYear();
        var month   = now.getMonth()+1; 
       var months = new Array('January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December');
//       var months = new Array('जनवरी', 'फ़रवरी', 'मार्च', 'अप्रैल', 'मई', 'जून', 'जुलाई', 'अगस्त', 'सितम्बर', 'अक्टूबर', 'नवम्बर', 'दिसम्बर');
	var curMonth = months[month];
        var day     = now.getDate();
        var hour    = now.getHours();
        var minute  = now.getMinutes();
        var second  = now.getSeconds(); 
      	var curHour = hour > 12 ? hour - 12 : (hour < 10 ? "0" + hour : hour);
	var curMinute = minute < 10 ? "0" + minute : minute;
	var curSeconds = second < 10 ? "0" + second : second;
	var curMeridiem = hour > 12 ? "PM" : "AM";
        if(day.toString().length == 1) {
             day = '0'+day;
        } 
        if(curHour.toString().length == 1) {
             curHour = '0'+curHour;
        } 
        var dateTime =curMonth+' '+ day+', '+year+" "+curHour+':'+curMinute+':'+curSeconds+" "+curMeridiem;   

        //var dateTime =" <i class='material-icons' style='color: white;'>date_range</i> "+ day+'-'+month+'-'+year+" <i class='material-icons' style='color: white;'>watch_later</i> "+hour+':'+minute+':'+second;   
         return dateTime;
    }
//var objToday = new Date(),
//	weekday = new Array('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'),
//	dayOfWeek = weekday[objToday.getDay()],
//	domEnder = function() { var a = objToday; if (/1/.test(parseInt((a + "").charAt(0)))) return "th"; a = parseInt((a + "").charAt(1)); return 1 == a ? "st" : 2 == a ? "nd" : 3 == a ? "rd" : "th" }(),
//	dayOfMonth = today + ( objToday.getDate() < 10) ? '0' + objToday.getDate() + domEnder : objToday.getDate() + domEnder,
//	months = new Array('January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'),
//	curMonth = months[objToday.getMonth()],
//	curYear = objToday.getFullYear(),
//	curHour = objToday.getHours() > 12 ? objToday.getHours() - 12 : (objToday.getHours() < 10 ? "0" + objToday.getHours() : objToday.getHours()),
//	curMinute = objToday.getMinutes() < 10 ? "0" + objToday.getMinutes() : objToday.getMinutes(),
//	curSeconds = objToday.getSeconds() < 10 ? "0" + objToday.getSeconds() : objToday.getSeconds(),
//	curMeridiem = objToday.getHours() > 12 ? "PM" : "AM";
//var today = curHour + ":" + curMinute + "." + curSeconds + curMeridiem + " " + dayOfWeek + " " + dayOfMonth + " of " + curMonth + ", " + curYear;
//
//document.getElementsByTagName('h1')[0].textContent = today;
    // example usage: realtime clock
    setInterval(function(){
        currentTime = getDateTime();
        document.getElementById("digital-clock").innerHTML =currentTime;
    }, 1000);
</script>

  
   <!--Header start-->
    <header class="header">
      
       
      <!-- top menu -->
      <div class="top-menu bg-four">
        <div class="container">
          <!--Navbar-->
          <nav class="navbar navbar-expand-lg navbar-dark px-0 py-0">
            <!--Hamburger button-->
            <a id="showLeftPush" class="navbar-toggler side-hamburger border-0 px-0" href="#">
              <span class="hamburger-icon">
                <span></span><span></span><span></span><span></span>
              </span>
            </a>
            <!-- Mobile logo -->
            <a href="#">
              <img class="mobile-logo img-fluid d-lg-none mx-auto" src="${pageContext.request.contextPath}/resources/images/ecommercelogo.png" alt="Logo site">
            </a>
            <!--Right Toggle Button-->
            <button class="navbar-toggler px-0" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo2" aria-controls="navbarTogglerDemo2" aria-expanded="false" aria-label="Toggle navigation">
              <span class="fa fa-search"></span>
            </button>
            <!--Top Navbar-->
            <div id="navbarTogglerDemo" class="collapse navbar-collapse">
              <!-- Time -->
              <div class="date-area d-none d-lg-block">
                <time class="navbar-text mr-2" datetime="" id="digital-clock"></time>
              </div>
              <!--Top left menu-->
             <!--  <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link active" href="#">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="about-us.html">About Us</a></li>
                <li class="nav-item"><a class="nav-link" href="contact-us.html">Contact Us</a></li>
                <li class="nav-item"><a class="nav-link" href="#" target="_blank">Buy Now</a></li>
              </ul> -->
              <!--end top left menu-->
              <!--Top right menu-->

 


               <ul class="navbar-nav ml-auto text-center">
                <li class="nav-item"><a class="nav-link" href="https://facebook.com" target="_blank"><i class="fab fa-facebook"></i></a></li>
                <li class="nav-item"><a class="nav-link" href="https://twitter.com" target="_blank"><i class="fab fa-twitter"></i></a></li>
                <li class="nav-item"><a class="nav-link" href="https://youtube.com" target="_blank"><i class="fab fa-youtube"></i></a></li>
                <li class="nav-item"><a class="nav-link" href="https://instagram.com" target="_blank"><i class="fab fa-instagram"></i></a></li>
                <li class="nav-item"><a class="nav-link" href="https://vimeo.com" target="_blank"><i class="fab fa-vimeo"></i></a></li>
              </ul>  
              <!--end top right menu-->
            </div>
          </nav>
          <!-- End Navbar -->
        </div>
      </div>
      <!--end top menu-->

      <!--start second header-->
      <div class="second-header">
        <div class="container">
          <div class="row">
            <!--Left content logo-->
            <div class="col-lg-4">
              <div id="main-logo" class="main-logo my-2 my-lg-4 d-none d-lg-block">
                <a class="navbar-brand" href="#">
                  <img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/ecommercelogo.png" alt="Logo site">
                </a>
              </div>
            </div>
            <!--Right content ads-->
            <div class="col-lg-8">
              <div class="my-2 my-md-3 my-lg-4 d-none d-md-block text-center">
                <a href="#" target="_blank">
                  <img class="img-fluid bg-light" src="${pageContext.request.contextPath}/resources/images/728-demo.png" alt="Advertiser space">
                </a>
              </div>
            </div>
            <!--search mobile-->
            <div class="collapse navbar-collapse col-12 py-2" id="navbarTogglerDemo2">
              <form class="form-inline" action="../category/search.html">
                <div class="input-group w-100 bg-white">
                  <input type="text" class="form-control border border-right-0" placeholder="Search..." aria-label="search">
                  <div class="input-group-prepend bg-light-dark">
                    <button class="btn bg-transparent border-left-0 input-group-text border" type="submit"><i class="fa fa-search"></i></button>
                  </div>
                </div>
              </form>
            </div>
            <!--end search mobile-->
          </div>
        </div>
      </div>
      <!--End second header-->
    </header>
    <!--End header-->
