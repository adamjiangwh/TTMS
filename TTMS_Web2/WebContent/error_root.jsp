<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" errorPage="error.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="eng" lang="eng">
  <head> 
  <base href="<%=basePath%>">
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <meta name="author" content="Yigit Yigit Ce.[pulyavserdce.com]" />
  <meta name="description" content="Site description" />
  <meta name="keywords" content="keywords, keyword, seo, google" /> 
  <meta name="apple-mobile-web-app-status-bar-style" content="black" /> 
  <meta name="apple-mobile-web-app-capable" content="yes" /> 
  <title>无访问权限</title>
   <link rel="stylesheet" media="screen" href="_css/style.css" type="text/css" />
   <link rel="shortcut icon" type="image/x-icon" href="favicon.png" />
   <link rel="icon" type="image/x-icon" href="favicon.png" />
   <link rel="apple-touch-icon" href="favicon.png" />
   <link rel="apple-touch-startup-image" href="favicon.png" />
   </head> 
   <body> 
   <div class="controller">
    <div class="objects"> 
    <!-- text area -->
    <div class="text-area rotate">
    <p class="error">Unauthorized Access </p>
    <p class="details">There was a problem<br /><br />Forbidden You don't have permission to access.</p> 
    </div> 
    <!-- text area -->
    <!-- home page --> 
    <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >Website Template</a></div>
    <div class="homepage rotate">
    <a href="/TTMS_Web2/home.jsp">Back to homepage</a> 
    </div> <!-- home page --> 
    </div> <!-- social-icons --> 
    <div class="social">
    <ul class="social-icons">
    <li><a href="#"><img src="_images/forrst.png" alt="Forrst" /></a></li>
    <li><a href="#"><img src="_images/dribbble.png" alt="Dribbble" /></a></li>
    <li><a href="#"><img src="_images/deviantart.png" alt="DeviantArt" /></a></li>
    <li><a href="#"><img src="_images/flickr.png" alt="Flickr" /></a></li>
    <li><a href="#"><img src="_images/twitter.png" alt="Twitter" /></a></li>
    <li><a href="#"><img src="_images/facebook.png" alt="Facebook" /></a></li>
    <li><a href="#"><img src="_images/skype.png" alt="Skype" /></a></li>
    </ul> 
    </div> 
    <!-- social-icons --> 
    </div> 

</body>
</html>