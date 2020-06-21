package consultas;

import clases.Area;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsulArea {

    public static int num_registros;

    private Connection cn = Conexion.getInstance();

    public DefaultTableModel mostrar(String buscar) {

        String[] titulos = {"ID", "Nombre", "Descripción"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        String[] registro = new String[3];
        num_registros = 0;

        String sSQL = "select * from area where nombre like '%" + buscar + "%'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registro[0] = rs.getString("id_area");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("descripcion");

                modelo.addRow(registro);
                num_registros++;

            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar registros de areas");
            System.err.println("Error al mostrar registros de areas: " + e);
            return null;

        }
    }
    
    public DefaultComboBoxModel<String> lista(){
        
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("");
        
        String sSQL = "select nombre from area";
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                modelo.addElement(rs.getString("nombre"));
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar lista de areas");
            System.err.println("Error al mostrar lista de areas: " + e);
            return null;
        }
    }
    
    public Area obtener(int idArea){
        
        Area dts = new Area();
        String sSQL = "select * from area where id_area = '" + idArea + "'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            if (rs.next()) {
                dts.setId(rs.getInt("id_area"));
                dts.setNombre(rs.getString("nombre"));
                dts.setDescripcion(rs.getString("descripcion"));
                return dts;
            }else{
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener area");
            System.err.println("Error al obtener area: " + e);
            return null;
        }
    }

    public boolean insertar(Area dts) {

        String sSQL = "insert into area (nombre, descripcion) values (?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al insertar registro de area");
            System.err.println("Error al insertar registro de area: " + e);
            return false;

        }
    }

    public boolean editar(Area dts) {

        String sSQL = "update area set nombre=?, descripcion=? where id_area=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setInt(3, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar registro de area");
            System.err.println("Error al editar registro de area: " + e);
            return false;

        }
    }

    public boolean eliminar(Area dts) {

        String sSQL = "delete from area where id_area=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar registro de area");
            System.err.println("Error al eliminar registro de area: " + e);
            return false;

        }
    }
}
