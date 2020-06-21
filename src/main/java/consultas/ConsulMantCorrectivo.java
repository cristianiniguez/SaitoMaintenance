package consultas;

import clases.MantCorrectivo;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsulMantCorrectivo {

    public static int num_registros;

    private Connection cn = Conexion.getInstance();

    public DefaultTableModel mostrar(String buscarArea, String buscarMaquinaria) {

        String[] titulos = {"ID", "ID maquinaria", "Codigo maquinaria", "Codigo", "Nombre", "Descripción"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String[] registro = new String[6];
        num_registros = 0;

        //Union: area (a) - maquinaria (maq) - mantenimiento (m) - mant_correctivo (mc)
        String sSQL = "select m.id_mantenimiento, "
                + "m.id_maquinaria, maq.codigo, "
                + "m.codigo, m.nombre, mc.descripcion "
                + "from area as a inner join maquinaria as maq "
                + "on a.id_area = maq.id_area "
                + "inner join mantenimiento as m "
                + "on maq.id_maquinaria = m.id_maquinaria "
                + "inner join mant_correctivo as mc "
                + "on m.id_mantenimiento = mc.id_mantenimiento "
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
                registro[5] = rs.getString("descripcion");
                
                modelo.addRow(registro);
                num_registros++;

            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar registros de mantenimientos correctivos");
            System.err.println("Error al mostrar registros de mantenimientos correctivos: " + e);
            return null;

        }
    }

    public MantCorrectivo obtener(int idMantCorrectivo){
        
        MantCorrectivo dts = new MantCorrectivo();
        
        //Union: mantenimiento (m) - mant_correctivo (mc)
        String sSQL = "select * "
                + "from mantenimiento as m inner join mant_correctivo as mc "
                + "where m.id_mantenimiento = '" + idMantCorrectivo + "'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            if (rs.next()) {
                dts.setId(rs.getInt("m.id_mantenimiento"));
                dts.setId_maquinaria(rs.getInt("m.id_maquinaria"));
                dts.setCodigo(rs.getString("m.codigo"));
                dts.setNombre(rs.getString("m.nombre"));
                dts.setDescripcion(rs.getString("mc.descripcion"));
                return dts;
            }else{
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos de mantenimiento correctivo");
            System.err.println("Error al obtener datos de mantenimiento correctivo: " + e);
            return null;
        }
    }
    
    public boolean insertar(MantCorrectivo dts) {

        String sSQL1 = "insert into mantenimiento (id_maquinaria, codigo, nombre, tipo) values (?,?,?,'MC')";
        String sSQL2 = "insert into mant_correctivo (id_mantenimiento, descripcion) "
                + "values ((select id_mantenimiento from mantenimiento order by id_mantenimiento desc limit 1),?)";

        try {

            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst1.setInt(1, dts.getId_maquinaria());
            pst1.setString(2, dts.getCodigo());
            pst1.setString(3, dts.getNombre());

            pst2.setString(1, dts.getDescripcion());
            
            return pst1.executeUpdate() != 0 && pst2.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al insertar registro de mantenimiento correctivo");
            System.err.println("Error al insertar registro de mantenimiento correctivo: " + e);
            return false;

        }
    }

    public boolean editar(MantCorrectivo dts) {

        String sSQL1 = "update mantenimiento set id_maquinaria=?, codigo=?, nombre=? where id_mantenimiento=?";
        String sSQL2 = "update mant_correctivo set descripcion=? where id_mantenimiento=?";

        try {

            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst1.setInt(1, dts.getId_maquinaria());
            pst1.setString(2, dts.getCodigo());
            pst1.setString(3, dts.getNombre());
            pst1.setInt(4, dts.getId());

            pst2.setString(1, dts.getDescripcion());
            pst2.setInt(2, dts.getId());

            return pst1.executeUpdate() != 0 && pst2.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar registro de mantenimiento correctivo");
            System.err.println("Error al editar registro de mantenimiento correctivo: " + e);
            return false;

        }
    }

    public boolean eliminar(MantCorrectivo dts) {

        String sSQL1 = "delete from mant_correctivo where id_mantenimiento=?";
        String sSQL2 = "delete from mantenimiento where id_mantenimiento=?";

        try {

            PreparedStatement pst1 = cn.prepareStatement(sSQL1);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst1.setInt(1, dts.getId());
            pst2.setInt(1, dts.getId());

            return pst1.executeUpdate() != 0 && pst2.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar registro de mantenimiento correctivo");
            System.err.println("Error al eliminar registro de mantenimiento correctivo: " + e);
            return false;

        }
    }
}
