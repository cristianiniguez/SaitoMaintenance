package consultas;

import java.sql.*;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class ConsulDiasLaborales {

    private Connection cn = Conexion.getInstance();

    public boolean[] mostrar() {

        boolean[] dias_laborales = new boolean[7];

        String sSQL = "select laboral from dias_laborales order by dia";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            int i = 0;
            while (rs.next()) {
                if (rs.getInt("laboral") == 1) {
                    dias_laborales[i] = true;
                } else {
                    dias_laborales[i] = false;
                }
                i++;
            }

            return dias_laborales;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener dias laborales");
            System.err.println("Error al obtener dias laborales: " + e);
            return null;
        }
    }

    public boolean actualizar(boolean[] dts) {

        String sSQL = "update dias_laborales set laboral=? where dia=?";

        try {
            boolean enviado = true;
            for (int i = 0; i < 7; i++) {
                PreparedStatement pst = cn.prepareStatement(sSQL);
                if (dts[i]) {
                    pst.setInt(1, 1);
                }else{
                    pst.setInt(1, 0);
                }
                pst.setInt(2, i);
                if (pst.executeUpdate() == 0) {
                    enviado = false;
                }
            }
            return enviado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar dias laborales");
            System.err.println("Error al editar dias laborales: " + e);
            return false;
        }
    }
    
    /*Este metodo recibe una fecha
    y verifica si esta fecha es un dia laboral*/
    public boolean verificar(Date fecha){
        
        //Obteniendo el día de la semana de la fecha
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int diaSemana = cal.get(Calendar.DAY_OF_WEEK) - 1;
        String sSQL = "select laboral from dias_laborales where dia = '" + diaSemana + "'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            if (rs.next()) {
                return rs.getInt("laboral") == 1;
            }else{
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error al verificar dia laboral: " + e);
            return true;
        }
        
    }
}
