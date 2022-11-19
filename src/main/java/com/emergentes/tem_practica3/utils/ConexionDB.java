package com.emergentes.tem_practica3.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

  static String driver = "com.mysql.jdbc.Driver";
  static String url = "jdbc:mysql://localhost:3306/";
  static String usuario = "root";
  static String password = "";

  Connection con = null;

  public ConexionDB(String databaseName) {
    try {
      Class.forName(driver);
      con = DriverManager.getConnection(url.concat(databaseName), usuario, password);

      if (con != null) {
        System.out.println("Conexion OK: " + con);
      }
    } catch (ClassNotFoundException ex) {
      System.out.println("Error en Driver: " + ex.getMessage());
    } catch (SQLException ex) {
      System.out.println("Error de SQL: " + ex.getMessage());
    }
  }

  public Connection conectar() {
    return con;
  }

  public void desconectar() {
    try {
      con.close();
    } catch (SQLException ex) {
      System.out.println("Error al cerrar la conexion: " + ex.getMessage());
    }
  }
}
