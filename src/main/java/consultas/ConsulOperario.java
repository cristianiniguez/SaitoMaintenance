package consultas;

import clases.Operario;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsulOperario {

    public static int num_registros;
    public static Operario USUARIO = new Operario();

    private Connection cn = Conexion.getInstance();

    public boolean login(String login, char[] pass) {

        String sSQL = "select * from operario "
                + "where user = '" + login + "' "
                + "and password = '" + String.valueOf(pass) + "' "
                + "and estado = 'Habilitado'";
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            if (rs.next()) {
                USUARIO.setId(rs.getInt("id_operario"));
                USUARIO.setNombre(rs.getString("nombre"));
                USUARIO.setAp_paterno(rs.getString("ap_paterno"));
                USUARIO.setAp_materno(rs.getString("ap_materno"));
                USUARIO.setUser(rs.getString("user"));
                USUARIO.setPassword(rs.getString("password").toCharArray());
                USUARIO.setEstado(rs.getString("estado"));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar login del operario");
            System.err.println("Error al ejecutar login del operario: " + e);
            return false;
        }
    }

    public void actualizarUsuario() {
        String sSQL = "select * from operario where id_operario = '" + USUARIO.getId() + "'";
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            if (rs.next()) {
                USUARIO.setNombre(rs.getString("nombre"));
                USUARIO.setAp_paterno(rs.getString("ap_paterno"));
                USUARIO.setAp_materno(rs.getString("ap_materno"));
                USUARIO.setUser(rs.getString("user"));
                USUARIO.setPassword(rs.getString("password").toCharArray());
                USUARIO.setEstado(rs.getString("estado"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario");
            System.err.println("Error al actualizar usuario: " + e);
        }
    }

    public Operario obtener(int idOperario) {

        Operario dts = new Operario();
        String sSQL = "select * from operario where id_operario = '" + idOperario + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            if (rs.next()) {
                dts.setId(rs.getInt("id_operario"));
                dts.setNombre(rs.getString("nombre"));
                dts.setAp_paterno(rs.getString("ap_paterno"));
                dts.setAp_materno(rs.getString("ap_materno"));
                dts.setUser(rs.getString("user"));
                dts.setPassword(rs.getString("password").toCharArray());
                dts.setEstado(rs.getString("estado"));
                return dts;
            } else {
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos de operario");
            System.err.println("Error al obtener datos de operario: " + e);
            return null;
        }
    }

    public DefaultTableModel mostrar() {

        DefaultTableModel modelo;
        String[] titulos = {"ID", "Nombre", "Apellido paterno", "Apellido materno", "User", "Password", "Estado"};
        String[] registro = new String[7];
        modelo = new DefaultTableModel(null, titulos);

        num_registros = 0;

        String sSQL = "select * from operario order by ap_paterno";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registro[0] = rs.getString("id_operario");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("ap_paterno");
                registro[3] = rs.getString("ap_materno");
                registro[4] = rs.getString("user");
                registro[5] = rs.getString("password");
                registro[6] = rs.getString("estado");

                modelo.addRow(registro);
                num_registros++;

            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar registros de operarios");
            System.err.println("Error al mostrar registros de operarios: " + e);
            return null;

        }
    }

    public boolean insertar(Operario dts) {

        String sSQL = "insert into operario (nombre, ap_paterno, ap_materno, user, password, estado) values (?,?,?,?,?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getAp_paterno());
            pst.setString(3, dts.getAp_materno());
            pst.setString(4, dts.getUser());
            pst.setString(5, String.valueOf(dts.getPassword()));
            pst.setString(6, dts.getEstado());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al insertar registro de operario");
            System.err.println("Error al insertar registro de operario: " + e);
            return false;

        }
    }

    public boolean editarPublico(Operario dts) {

        String sSQL = "update operario set nombre=?, ap_paterno=?, ap_materno=?, estado=? where id_operario=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getAp_paterno());
            pst.setString(3, dts.getAp_materno());
            pst.setString(4, dts.getEstado());
            pst.setInt(5, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar registro de operario");
            System.err.println("Error al editar registro de operario: " + e);
            return false;

        }
    }

    public boolean editarPrivado(Operario dts) {

        String sSQL = "update operario set user=?, password=? where id_operario=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getUser());
            pst.setString(2, String.valueOf(dts.getPassword()));
            pst.setInt(3, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar registro de operario");
            System.err.println("Error al editar registro de operario: " + e);
            return false;

        }
    }

    public boolean eliminar(Operario dts) {

        String sSQL = "delete from operario where id_operario=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar registro de operario");
            System.err.println("Error al eliminar registro de operario: " + e);
            return false;

        }
    }

}
