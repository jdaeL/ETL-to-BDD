package com.mycompany.sqljava;

import java.sql.CallableStatement;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CTabla {

    String id;
    String desc;
    String estReg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEstReg() {
        return estReg;
    }

    public void setEstReg(String estReg) {
        this.estReg = estReg;
    }
    
    public void Insertar(JTextField id, JTextField desc, JTextField estReg){
        setEstReg(id.getText());
        setDesc(desc.getText());
        setDesc(estReg.getText());
        CConexion conexion = new CConexion();
        String consulta = "INSERT INTO REGIONES (RegCod, RegNom, RegEstReg) VALUES (?, ?, ?);";
        try {
            CallableStatement cs = conexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, id.getText());
            cs.setString(2, desc.getText());            
            cs.setString(3, estReg.getText());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente: " + e.toString());

        }
    }
    
    public void Mostrar(JTable tabla) {
    CConexion conexion = new CConexion();
    
    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };
    
    TableRowSorter<TableModel> OrdTabla = new TableRowSorter<TableModel>(modelo);
    tabla.setRowSorter(OrdTabla);
    
    String consulta = "SELECT * FROM REGIONES;";
        
    modelo.addColumn("Código");
    modelo.addColumn("Nombre");
    modelo.addColumn("Estado");;
    
    String[] datos = new String[3];
    Statement st;
    
    try {
        st = conexion.estableceConexion().createStatement();
        ResultSet rs = st.executeQuery(consulta);
        
        while (rs.next()) {
            datos[0] = rs.getString(1);
            datos[1] = rs.getString(2);
            datos[2] = rs.getString(3);
            modelo.addRow(datos);
        }
        
        tabla.setModel(modelo);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo mostrar correctamente " + e.toString());
    }
}
    
    public void Seleccionar(JTable tabla,JTextField id, JTextField desc, JTextField estReg){
        try {
            int fila = tabla.getSelectedRow();

            if (fila >= 0) {
                id.setText((tabla.getValueAt(fila, 0).toString())); 
                desc.setText((tabla.getValueAt(fila, 1).toString()));
                estReg.setText((tabla.getValueAt(fila, 2).toString()));
            }        
            else {
                JOptionPane.showMessageDialog(null, "No se pudo seleccionar la fila ");

            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo seleccionar la fila " + e.toString());       
        }
    }
    
    public void Modificar(JTextField id, JTextField desc, JTextField estReg) {
        // Obtener los valores de los campos de texto
        String regCod = id.getText();
        String regNom = desc.getText();
        String regEstReg = estReg.getText();

        // Crear una nueva instancia de la clase CConexion para establecer la conexión
        CConexion conexion = new CConexion();

        // Definir la consulta SQL de actualización
        String consulta = "UPDATE REGIONES SET RegNom = ?, RegEstReg = ? WHERE RegCod = ?;";

        try (Connection con = conexion.estableceConexion();
             CallableStatement cs = con.prepareCall(consulta)) {

            // Configurar los parámetros de la consulta
            cs.setString(1, regNom);      // Establecer la descripción (RegNom)
            cs.setString(2, regEstReg);   // Establecer el estado del registro (RegEstReg)
            cs.setString(3, regCod);      // Establecer el ID (RegCod)

            // Ejecutar la consulta
            cs.execute();

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "Se modificó correctamente");

        } catch (Exception e) {
            // Manejar cualquier excepción y mostrar mensaje de error
            JOptionPane.showMessageDialog(null, "No se pudo modificar: " + e.toString());
        }
    }

    
    public void Eliminar(JTextField id, JTextField desc, JTextField estReg){
        setId(id.getText()); 
        
        setEstReg(id.getText());
        setDesc(desc.getText());
        setDesc(estReg.getText());
        CConexion conexion = new CConexion();
        String consulta = "UPDATE REGIONES SET RegEstReg = '*' WHERE RegCod = ?;";
        try {
            CallableStatement cs = conexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, id.getText());
            cs.execute();           
            JOptionPane.showMessageDialog(null, "Se elimino correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se elimino correctamente: " + e.toString());

        }
    }
    
    public void Reactivar(JTextField id, JTextField desc, JTextField estReg){
        setEstReg(id.getText());
        setDesc(desc.getText());
        setDesc(estReg.getText());
        CConexion conexion = new CConexion();
        String consulta = "UPDATE REGIONES SET RegEstReg = 'A' WHERE RegCod = ?;";
        try {
            CallableStatement cs = conexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, id.getText());
            cs.execute();           
            JOptionPane.showMessageDialog(null, "Se reactivo correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se reactivo correctamente: " + e.toString());

        }
    }
    public void Inactivar(JTextField id, JTextField desc, JTextField estReg){
        setEstReg(id.getText());
        setDesc(desc.getText());
        setDesc(estReg.getText());
        CConexion conexion = new CConexion();
        String consulta = "UPDATE REGIONES SET RegEstReg = 'I' WHERE RegCod = ?;";
        try {
            CallableStatement cs = conexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, id.getText());
            cs.execute();           
            JOptionPane.showMessageDialog(null, "Se inactivo correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inactivo correctamente: " + e.toString());
        }
    }
}
