package consultas;

import clases.Area;
import clases.Maquinaria;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsulMaquinaria {
    
    public static int num_registros;

    private Connection cn = Conexion.getInstance();

    public DefaultTableModel mostrar(String buscar) {

        String[] titulos = {"ID", "ID area", "Area", "Código", "Descripción"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String[] registro = new String[5];
        num_registros = 0;

        //Union: area (a) - maquinaria (m)
        String sSQL = "select m.id_maquinaria, "
                + "m.id_area, a.nombre, "
                + "m.codigo, m.descripcion "
                + "from maquinaria as m inner join area as a "
                + "on m.id_area = a.id_area "
                + "where a.nombre like '%" + buscar + "%'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registro[0] = rs.getString("id_maquinaria");
                registro[1] = rs.getString("id_area");
                registro[2] = rs.getString("nombre");
                registro[3] = rs.getString("codigo");
                registro[4] = rs.getString("descripcion");

                modelo.addRow(registro);
                num_registros++;

            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar registros de maquinarias");
            System.err.println("Error al mostrar registros de maquinarias: " + e);
            return null;

        }
    }

    public DefaultComboBoxModel<String> lista(String buscar){
        
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("");
        
        String sSQL = "select m.codigo from maquinaria as m inner join area as a on m.id_area=a.id_area "
                + "where a.nombre like '%" + buscar + "%'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                modelo.addElement(rs.getString("codigo"));
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar lista de maquinarias");
            System.err.println("Error al mostrar lista de maquinarias: " + e);
            return null;
        }
    }
    
    public Maquinaria obtener(int idMaquinaria){
        
        Maquinaria dts = new Maquinaria();
        String sSQL = "select * from maquinaria where id_maquinaria = '" + idMaquinaria + "'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            if (rs.next()) {
                dts.setId(rs.getInt("id_maquinaria"));
                dts.setId_area(rs.getInt("id_area"));
                dts.setCodigo(rs.getString("codigo"));
                dts.setDescripcion(rs.getString("descripcion"));
                return dts;
            }else{
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener maquinaria");
            System.err.println("Error al obtener maquinaria: " + e);
            return null;
        }
    }
    
    public boolean insertar(Maquinaria dts) {

        String sSQL = "insert into maquinaria (id_area, codigo, descripcion) values (?,?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_area());
            pst.setString(2, dts.getCodigo());
            pst.setString(3, dts.getDescripcion());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al insertar registro de maquinaria");
            System.err.println("Error al insertar registro de maquinaria: " + e);
            return false;

        }
    }

    public boolean editar(Maquinaria dts) {

        String sSQL = "update maquinaria set id_area=?, codigo=?, descripcion=? where id_maquinaria=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_area());
            pst.setString(2, dts.getCodigo());
            pst.setString(3, dts.getDescripcion());
            pst.setInt(4, dts.getId());
            
            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar registro de maquinaria");
            System.err.println("Error al editar registro de maquinaria: " + e);
            return false;

        }
    }

    public boolean eliminar(Maquinaria dts) {

        String sSQL = "delete from maquinaria where id_maquinaria=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar registro de maquinaria");
            System.err.println("Error al eliminar registro de maquinaria: " + e);
            return false;

        }
    }
    
    /*Este metodo recibe una area
    y verifica si hay maquinarias en esa area*/
    public boolean hayMaquinaria(Area dts){
        
        String sSQL = "select * from maquinaria where id_area = '" + dts.getId() + "'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            return rs.next();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar maquinarias de area");
            System.err.println("Error al buscar maquinarias de area: " + e);
            return true;
        }
    }
}
