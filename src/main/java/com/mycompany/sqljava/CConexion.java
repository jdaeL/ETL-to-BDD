package com.mycompany.sqljava;


import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;

public class CConexion {
    Connection conectar = null;
    String usuario="root";
    String contrasena="1234";
    String bd="eat_n_go";
    String ip="localhost";
    String puerto = "3306";
    
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar=DriverManager.getConnection(cadena, usuario, contrasena);
            JOptionPane.showMessageDialog(null, "conexion establecida");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion " + e.toString());
        }
        return conectar;
    }
}


