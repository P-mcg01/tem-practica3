<%@page import="com.emergentes.tem_practica3.modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  Producto prod = (Producto) request.getAttribute("producto");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Editar</title>
    <link rel="stylesheet" href="./css/styleForm.css">
  </head>
  <body>
    <h1>
      <%= prod.getId() == 0 ? "Nuevo Producto" : "Editar Producto"%>
    </h1>

    <form action="MainController" method="POST">
      <input type="hidden" name="id" value="${requestScope.producto.id}">
      <label for="txtProducto">Producto: </label>
      <input id="txtProducto"
             type="text"
             required
             value="${requestScope.producto.producto}"
             name="producto">

      <label for="txtCantidad">Cantidad: </label>
      <input id="txtCantidad" 
             type="number" 
             min="0" 
             max="99999"
             required 
             value="${requestScope.producto.cantidad}"
             name="cantidad">
      <label for="txtPrecio">Precio: </label>
      <input id="txtPrecio" 
             type="number" 
             min="0" 
             max="99999" 
             step="0.01"
             required 
             value="${requestScope.producto.precio}"
             name="precio">

      <input type="submit" value="Guardar">
    </form>
  </body>
</html>
