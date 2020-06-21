package consultas;

import clases.Maquina;
import clases.Maquinaria;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsulMaquina {
    
    public static int num_registros;

    private Connection cn = Conexion.getInstance();

    public DefaultTableModel mostrar(String area, String maquinaria) {

        String[] titulos = {"ID", "Código", "Nombre", "ID maquinaria", "Codigo Maquinaria"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        String[] registro = new String[5];
        num_registros = 0;

        //Union: area (a) - maquinaria (m1) - maquina (m2)
        String sSQL = "select m2.id_maquina, m2.codigo, m2.nombre, m2.id_maquinaria, m1.codigo "
                + "from area as a inner join maquinaria as m1 "
                + "on a.id_area = m1.id_area "
                + "inner join maquina as m2 "
                + "on m1.id_maquinaria = m2.id_maquinaria "
                + "where m1.codigo like '%" + maquinaria + "%' "
                + "and a.nombre like '%" + area + "%'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registro[0] = rs.getString("id_maquina");
                registro[1] = rs.getString("m2.codigo");
                registro[2] = rs.getString("nombre");
                registro[3] = rs.getString("id_maquinaria");
                registro[4] = rs.getString("m1.codigo");

                modelo.addRow(registro);
                num_registros++;

            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar registros de maquinas");
            System.err.println("Error al mostrar registros de maquinas: " + e);
            return null;

        }
    }

    public Maquina obtener(int idMaquina){
        
        Maquina dts = new Maquina();
        String sSQL = "select * from maquina where id_maquina = '" + idMaquina + "'";
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            if (rs.next()) {
                dts.setId(rs.getInt("id_maquina"));
                dts.setId_maquinaria(rs.getInt("id_maquinaria"));
                dts.setCodigo(rs.getString("codigo"));
                dts.setNombre(rs.getString("nombre"));
                return dts;
            }else{
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener registro de maquina");
            System.err.println("Error al obtener registro de maquina: " + e);
            return null;
        }
    }
    
    public boolean insertar(Maquina dts) {

        String sSQL = "insert into maquina (id_maquinaria, codigo, nombre) values (?,?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_maquinaria());
            pst.setString(2, dts.getCodigo());
            pst.setString(3, dts.getNombre());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al insertar registro de maquina");
            System.err.println("Error al insertar registro de maquina: " + e);
            return false;

        }
    }

    public boolean editar(Maquina dts) {

        String sSQL = "update maquina set id_maquinaria=?, codigo=?, nombre=? where id_maquina=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_maquinaria());
            pst.setString(2, dts.getCodigo());
            pst.setString(3, dts.getNombre());
            pst.setInt(4, dts.getId());
            
            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar registro de maquina");
            System.err.println("Error al editar registro de maquina: " + e);
            return false;

        }
    }

    public boolean eliminar(Maquina dts) {

        String sSQL = "delete from maquina where id_maquina=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar registro de maquina");
            System.err.println("Error al eliminar registro de maquina: " + e);
            return false;

        }
    }
    
    /*Este metodo recibe una maquinaria
    y verifica si hay maquinas en esa maquinaria*/
    public boolean hayMaquina(Maquinaria dts){
        
        String sSQL = "select * from maquina where id_maquinaria = '" + dts.getId() + "'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            return rs.next();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar maquinas de la maquinaria");
            System.err.println("Error al buscar maquinas de la maquinaria: " + e);
            return true;
        }
    }
}
