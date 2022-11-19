package com.emergentes.tem_practica3.controlador;

import com.emergentes.tem_practica3.modelo.Producto;
import com.emergentes.tem_practica3.utils.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    String opcion = (request.getParameter("op") != null)
            ? request.getParameter("op")
            : "list";

    ArrayList<Producto> lista = new ArrayList<>();
    ConexionDB canal = new ConexionDB("bd_almacen");
    Connection con = canal.conectar();
    PreparedStatement ps;
    ResultSet rs;

    if (opcion.equals("list")) {
      try {
        String query = "SELECT * FROM productos";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
          Producto prod = new Producto();

          prod.setId(rs.getInt("id"));
          prod.setCantidad(rs.getInt("cantidad"));
          prod.setPrecio(rs.getFloat("precio"));
          prod.setProducto(rs.getString("producto"));

          lista.add(prod);
        }

        request.setAttribute("lista", lista);
        request.getRequestDispatcher("index.jsp").forward(request, response);
      } catch (SQLException ex) {
        out.print(ex.getMessage());
      }
    }

    if (opcion.equals("nuevo")) {
      Producto prod = new Producto();

      request.setAttribute("producto", prod);
      request.getRequestDispatcher("editar.jsp").forward(request, response);
    }

    if (opcion.equals("eliminar")) {
      try {
        int id = Integer.parseInt(request.getParameter("id"));

        String sql = "DELETE FROM productos WHERE id = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        ps.executeUpdate();
        response.sendRedirect("MainController");
      } catch (SQLException ex) {
        out.print(ex.getMessage());
      }
    }

    if (opcion.equals("editar")) {
      try {
        final int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<Producto> productos = new ArrayList<>();
        
        String query = "SELECT * FROM productos";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        
        while (rs.next()) {
          Producto prod = new Producto();
          
          prod.setId(rs.getInt("id"));
          prod.setCantidad(rs.getInt("cantidad"));
          prod.setPrecio(rs.getFloat("precio"));
          prod.setProducto(rs.getString("producto"));
          
          productos.add(prod);
        }
        
        Producto buscado = productos.stream().filter(item -> {
          return item.getId() == id;
        }).findFirst().get();
        
        request.setAttribute("producto", buscado);
        request.getRequestDispatcher("editar.jsp").forward(request, response);
      } catch (SQLException ex) {
        out.print(ex.getMessage());
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    int id = Integer.parseInt(request.getParameter("id"));
    int cantidad = Integer.parseInt(request.getParameter("cantidad"));
    float precio = Float.parseFloat(request.getParameter("precio"));
    String producto = request.getParameter("producto");

    Producto prod = new Producto();

    prod.setId(id);
    prod.setCantidad(cantidad);
    prod.setPrecio(precio);
    prod.setProducto(producto);

    ConexionDB canal = new ConexionDB("bd_almacen");
    Connection con = canal.conectar();
    PreparedStatement ps;

    if (id == 0) {
      try {
        //Nuevo registro
        String sql = "INSERT INTO productos(producto, precio, cantidad) VALUES(?, ?, ?)";

        ps = con.prepareStatement(sql);
        ps.setString(1, prod.getProducto());
        ps.setFloat(2, prod.getPrecio());
        ps.setInt(3, prod.getCantidad());

        ps.executeUpdate();
        response.sendRedirect("MainController");
      } catch (SQLException ex) {
        out.print(ex.getMessage());
      }
    } else {
      try {
        //Actualizar registro
        String sql = "UPDATE productos SET producto=?, precio=?, cantidad=? WHERE id=?";

        ps = con.prepareStatement(sql);
        ps.setString(1, prod.getProducto());
        ps.setFloat(2, prod.getPrecio());
        ps.setInt(3, prod.getCantidad());
        ps.setInt(4, prod.getId());

        ps.executeUpdate();
        response.sendRedirect("MainController");
      } catch (SQLException ex) {
        out.print(ex.getMessage());
      }
    }
  }
}
