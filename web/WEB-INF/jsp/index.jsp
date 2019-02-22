<%-- 
    Document   : index
    Created on : 22-feb-2019, 12:39:28
    Author     : Pauessa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.css" rel="stylesheet">
        <script type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery-1.8.0.js"></script>
        <script type="text/javascript"  src="<%=request.getContextPath()%>/js/bootstrap.js" ></script>
        
    </head>
    
    <body>
        
        <div class="container-fluidr">
         <a id="btnNuevo" class="btn btn-primary" href="<%=request.getContextPath()%>/index2.html"><h1>CINE</h1></a>
         <a id="btnNuevo2" class="btn btn-secondary" href="<%=request.getContextPath()%>/index3.html"><h1>PELICULAS</h1></a>

        </div>
    </body>
</html>
