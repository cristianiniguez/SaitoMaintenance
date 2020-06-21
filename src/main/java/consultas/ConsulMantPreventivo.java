package consultas;

import clases.MantPreventivo;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsulMantPreventivo {

    public static int num_registros;

    private Connection cn = Conexion.getInstance();

    public DefaultTableModel mostrar(String buscarArea, String buscarMaquinaria) {

        String[] titulos = {"ID", "ID maquinaria", "Codigo maquinaria", "Codigo", "Nombre", "NumFrecuencia", "UnidFrecuencia", "Frecuencia"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String[] registro = new String[8];
        num_registros = 0;

        //Union: area (a) - maquinaria (maq) - mantenimiento (m) - mant_preventivo (mp)
        String sSQL = "select m.id_mantenimiento, "
                + "m.id_maquinaria, maq.codigo, "
                + "m.codigo, m.nombre, "
                + "mp.numero_frecuencia, mp.unidad_frecuencia "
                + "from area as a inner join maquinaria as maq "
                + "on a.id_area = maq.id_area "
                + "inner join mantenimiento as m "
                + "on maq.id_maquinaria = m.id_maquinaria "
                + "inner join mant_preventivo as mp "
                + "on m.id_mantenimiento = mp.id_mantenimiento "
                + "where maq.codigo like '%" + buscarMaquinaria + "%' "
                + "and a.nombre like '%" + buscarArea + "%'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registro[0] = rs.getString("id_mantenimiento");
                registro[1] = rs.getString("id_maquinaria");
                registro[2] = rs.getString("maq.codigo");
                registro[3] = rs.getString("m.codigo");
                registro[4] = rs.getString("nombre");
                registro[5] = rs.getString("numero_frecuencia");
                registro[6] = rs.getString("unidad_frecuencia");
                
                switch(registro[6]){
                    case "Diario":
                        registro[7] = "Cada " + registro[5] + " día(s)";
                        break;
                    case "Semanal":
                        registro[7] = "Cada " + registro[5] + " semana(s)";
                        break;
                    case "Mensual":
                        registro[7] = "Cada " + registro[5] + " mes(es)";
                        break;
                    case "Anual":
                        registro[7] = "Cada " + registro[5] + " año(s)";
                        break;
                    default:
                        registro[7] = "???";   
                }
                
                modelo.addRow(registro);
                num_registros++;

            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar registros de mantenimientos preventivos");
            System.err.println("Error al mostrar registros de mantenimientos preventivos: " + e);
            return null;

        }
    }
    
    public MantPreventivo obtener(int idMantPreventivo){
        
        MantPreventivo dts = new MantPreventivo();
        
        //Union: mantenimiento (m) - mant_preventivo (mp)
        String sSQL = "select * "
                + "from mantenimiento as m inner join mant_preventivo as mp "
                + "where m.id_mantenimiento = '" + idMantPreventivo + "'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            if (rs.next()) {
                dts.setId(rs.getInt("m.id_mantenimiento"));
                dts.setId_maquinaria(rs.getInt("m.id_maquinaria"));
                dts.setCodigo(rs.getString("m.codigo"));
                dts.setNombre(rs.getString("m.nombre"));
                dts.setNumero_frecuencia(rs.getInt("mp.numero_frecuencia"));
                dts.setUnidad_frecuencia(rs.getString("unidad_frecuencia"));
                return dts;
            }else{
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos de mantenimiento preventivo");
            System.err.println("Error al obtener datos de mantenimiento preventivo: " + e);
            return null;
        }
    }

    public boolean insertar(MantPreventivo dts) {

        String sSQL1 = "insert into mantenimiento (id_maquinaria, codigo, nombre, tipo) values (?,?,?,'MP')";
        String sSQL2 = "insert into mant_preventivo (id_mantenimiento, numero_frecuencia, unidad_frecuencia) "
                + "values ((select id_mantenimiento from mantenimiento order by id_mantenimiento desc limit 1),?,?)";

        try {

            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst1.setInt(1, dts.getId_maquinaria());
            pst1.setString(2, dts.getCodigo());
            pst1.setString(3, dts.getNombre());

            pst2.setInt(1, dts.getNumero_frecuencia());
            pst2.setString(2, dts.getUnidad_frecuencia());
            
            return pst1.executeUpdate() != 0 && pst2.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al insertar registro de mantenimiento preventivo");
            System.err.println("Error al insertar registro de mantenimiento preventivo: " + e);
            return false;

        }
    }

    public boolean editar(MantPreventivo dts) {

        String sSQL1 = "update mantenimiento set id_maquinaria=?, codigo=?, nombre=? where id_mantenimiento=?";
        String sSQL2 = "update mant_preventivo set numero_frecuencia=?, unidad_frecuencia=? where id_mantenimiento=?";

        try {

            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst1.setInt(1, dts.getId_maquinaria());
            pst1.setString(2, dts.getCodigo());
            pst1.setString(3, dts.getNombre());
            pst1.setInt(4, dts.getId());

            pst2.setInt(1, dts.getNumero_frecuencia());
            pst2.setString(2, dts.getUnidad_frecuencia());
            pst2.setInt(3, dts.getId());

            return pst1.executeUpdate() != 0 && pst2.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar registro de mantenimiento preventivo");
            System.err.println("Error al editar registro de mantenimiento preventivo: " + e);
            return false;

        }
    }

    public boolean eliminar(MantPreventivo dts) {

        String sSQL1 = "delete from mant_preventivo where id_mantenimiento=?";
        String sSQL2 = "delete from mantenimiento where id_mantenimiento=?";

        try {

            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst1.setInt(1, dts.getId());
            pst2.setInt(1, dts.getId());

            return pst1.executeUpdate() != 0 && pst2.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar registro de mantenimiento preventivo");
            System.err.println("Error al eliminar registro de mantenimiento preventivo: " + e);
            return false;

        }
    }
}
