<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.tem_practica3.modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  ArrayList<Producto> productos = (ArrayList<Producto>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Productos</title>
    <link rel="stylesheet" href="./css/indexStyle.css">
  </head>
  <body>
    <main>
      <h2>Productos</h2>
      <a class="button" href="MainController?op=nuevo">Nuevo Producto</a>
      
      <table border="1">
        <tr>
          <th>ID</th>
          <th>Producto</th>
          <th>Precio</th>
          <th>Cantidad</th>
          <th></th>
          <th></th>
        </tr>
        
        <% if(productos.isEmpty()) { %>
          <tr>
            <td colspan="6">LISTA VACIA</td>
          </tr>          
        <% } %>
        
        
        <c:forEach var="item" items="${requestScope.lista}">
          <tr>
            <td>${item.id}</td>
            <td>${item.producto}</td>
            <td>${item.precio}</td>
            <td>${item.cantidad}</td>
            <td>
              <a class="edit" 
                 href="MainController?op=editar&id=${item.id}">
                Editar
              </a>
            </td>
            <td>
              <a class="delete" 
                 href="MainController?op=eliminar&id=${item.id}">
                Eliminar
              </a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </main>    
  </body>
</html>
