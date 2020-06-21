package consultas;

import clases.Jornada;
import java.sql.*;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsulJornada {

    private Connection cn = Conexion.getInstance();

    public DefaultTableModel mostrar() {

        DefaultTableModel modelo;
        String[] titulos = {"Año", "Fecha inicial", "Fecha final"};
        String[] registro = new String[3];
        modelo = new DefaultTableModel(null, titulos);

        String sSQL = "select * from jornada";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registro[0] = rs.getString("anho");
                registro[1] = rs.getString("fecha_inicial");
                registro[2] = rs.getString("fecha_final");

                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar registros de jornadas");
            System.err.println("Error al mostrar registros de jornadas: " + e);
            return null;

        }
    }

    public boolean guardar(Jornada dts) {

        String sSQL = "select * from jornada where anho = '" + dts.getAnho() + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            if (rs.next()) {
                sSQL = "update jornada set fecha_inicial=?, fecha_final=? where anho=?";
                PreparedStatement pst = cn.prepareStatement(sSQL);
                pst.setDate(1, dts.getFecha_inicial());
                pst.setDate(2, dts.getFecha_final());
                pst.setInt(3, dts.getAnho());
                return pst.executeUpdate() != 0;
            } else {
                sSQL = "insert into jornada (anho, fecha_inicial, fecha_final) values (?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sSQL);
                pst.setInt(1, dts.getAnho());
                pst.setDate(2, dts.getFecha_inicial());
                pst.setDate(3, dts.getFecha_final());
                return pst.executeUpdate() != 0;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al guardar registro de jornada");
            System.err.println("Error al guardar registro de jornada: " + e);
            return false;

        }
    }

    /*Este metodo recibe una fecha
    y verifica si esta fecha esta dento de la jornada laboral anual*/
    public boolean verificar(Date fecha) {

        //Obteniendo el año de la fecha
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int anho = cal.get(Calendar.YEAR);

        String sSQL = "select * from jornada where anho = '" + anho + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            if (rs.next()) {
                Date fecha_inicial = rs.getDate("fecha_inicial");
                Date fecha_final = rs.getDate("fecha_final");
                return fecha.compareTo(fecha_inicial) >= 0 && fecha.compareTo(fecha_final) <= 0;
            } else {
                return true;
            }

        } catch (Exception e) {
            System.err.println("Error al verificar jornada: " + e);
            return true;
        }
    }
}
