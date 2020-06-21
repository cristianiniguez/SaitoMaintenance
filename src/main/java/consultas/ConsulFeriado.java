package consultas;

import clases.Feriado;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsulFeriado {

    private Connection cn = Conexion.getInstance();

    public DefaultTableModel mostrar() {

        DefaultTableModel modelo;
        String[] titulos = {"ID", "Fecha", "Descripción"};
        String[] registro = new String[3];
        modelo = new DefaultTableModel(null, titulos);

        String sSQL = "select * from feriado";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registro[0] = rs.getString("id_feriado");
                registro[1] = rs.getString("fecha");
                registro[2] = rs.getString("descripcion");
                
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar registros de feriados");
            System.err.println("Error al mostrar registros de feriados: " + e);
            return null;

        }
    }
    
    public Feriado obtener(int idFeriado){
        
        Feriado dts = new Feriado();
        String sSQL = "select * from feriado where id_feriado = '" + idFeriado + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) {
                dts.setId(rs.getInt("id_feriado"));
                dts.setFecha(rs.getDate("fecha"));
                dts.setDescripcion(rs.getString("descripcion"));
                return dts;
            } else {
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener registro de feriado");
            System.err.println("Error al obtener registro de feriado: " + e);
            return null;
        }
        
    }

    public boolean insertar(Feriado dts) {

        String sSQL = "insert into feriado (fecha, descripcion) values (?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setDate(1, dts.getFecha());
            pst.setString(2, dts.getDescripcion());
            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al insertar registro de feriado");
            System.err.println("Error al insertar registro de feriado: " + e);
            return false;

        }
    }

    public boolean editar(Feriado dts) {

        String sSQL = "update feriado set fecha=?, descripcion=? where id_feriado=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setDate(1, dts.getFecha());
            pst.setString(2, dts.getDescripcion());
            pst.setInt(3, dts.getId());
            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar registro de feriado");
            System.err.println("Error al editar registro de feriado: " + e);
            return false;

        }
    }

    public boolean eliminar(Feriado dts) {

        String sSQL = "delete from feriado where id_feriado=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar registro de feriado");
            System.err.println("Error al eliminar registro de feriado: " + e);
            return false;

        }
    }

    /*Este metodo recibe una fecha
    y verifica si hay un feriado en esa fecha*/
    public boolean verificar(Date fecha) {

        String sSQL = "select fecha from feriado where fecha = '" + fecha + "'";
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            return rs.next();

        } catch (Exception e) {
            System.err.println("Error al verificar feriado: " + e);
            return true;
        }

    }
}
