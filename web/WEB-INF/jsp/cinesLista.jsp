<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="ejemplo03.dominio.Cine"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Cine> cines = (List<Cine>) request.getAttribute("cines");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cine</title>
        <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.css" rel="stylesheet">
        <script type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery-1.9.0.js"></script>
        <script type="text/javascript"  src="<%=request.getContextPath()%>/js/bootstrap.js" ></script>
    </head>
    <body style="background:#FDFDFD">
        <div class="row-fluid">
            <div class="span12">&nbsp;</div>
        </div>
        <div class="row-fluid">
            <div class="offset1  span10">
                <div class="row-fluid">
                    <div class="span12">
                        <a id="btnNuevo" class="btn btn-primary" href="<%=request.getContextPath()%>/cine/newForInsert.html">Nuevo Cine</a>
                    </div>
                </div>
                <div class="row-fluid">


                    <div class="span12">



                        <table class="table table-bordered table-hover table-condensed">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Nombre</th>
                                    <th>Direcion</th>
                                   
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Cine cine : cines) {
                                %>
                                <tr>
                                    <td><a href="<%=request.getContextPath()%>/cine/readForUpdate.html?id=<%=cine.getIdCine()%>" title="Editar" ><%=cine.getIdCine()%></a></td>
                                    <td><%=HtmlUtils.htmlEscape(cine.getNombreCine())%></td>
                                    <td><%=HtmlUtils.htmlEscape(cine.getDirecionCine())%></td>
                                    

                                    <td>
                                        <a href="<%=request.getContextPath()%>/cine/readForDelete.html?id=<%=cine.getIdCine()%>" title="Borrar" ><i class="icon-trash"></i></a>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>


                    </div>
                </div>
            </div>
            <div class="span1"></div>
        </div>
    </body>
</html>