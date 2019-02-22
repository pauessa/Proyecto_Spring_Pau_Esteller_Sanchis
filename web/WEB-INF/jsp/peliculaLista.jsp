<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="ejemplo03.dominio.Pelicula"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Pelicula> pelicules = (List<Pelicula>) request.getAttribute("pelicules");
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
                        <a id="btnNuevo" class="btn btn-primary" href="<%=request.getContextPath()%>/pelicula/newForInsert.html">Nueva pelicula</a>
                    </div>
                </div>
                <div class="row-fluid">


                    <div class="span12">



                        <table class="table table-bordered table-hover table-condensed">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Titulo</th>
                                    <th>Director</th>
                                    <th>Interprete</th>
                                    <th>Categora</th>

                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Pelicula pelicula : pelicules) {
                                %>
                                <tr>
                                    <td><a href="<%=request.getContextPath()%>/pelicula/readForUpdate.html?id=<%=pelicula.getIdPelicula()%>" title="Editar" ><%=pelicula.getIdPelicula()%></a></td>
                                    <td><%=HtmlUtils.htmlEscape(pelicula.getTituloPelicula())%></td>
                                    <td><%=HtmlUtils.htmlEscape(pelicula.getDirectorPelicula())%></td>
                                    <td><%=HtmlUtils.htmlEscape(pelicula.getInterpretePelicula())%></td>
                                    <td><%=HtmlUtils.htmlEscape(pelicula.getCategoriaPelicula())%></td>


                                    <td>
                                        <a href="<%=request.getContextPath()%>/pelicula/readForDelete.html?id=<%=pelicula.getIdPelicula()%>" title="Borrar" ><i class="icon-trash"></i></a>
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