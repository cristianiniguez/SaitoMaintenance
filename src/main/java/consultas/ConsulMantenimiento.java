package consultas;

import clases.Maquinaria;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsulMantenimiento {
    
    public static int num_registros;

    private Connection cn = Conexion.getInstance();

    public DefaultTableModel mostrar(String buscarArea, String buscarMaquinaria, String buscarTipo, String buscarMaquina) {

        String[] titulos = {"ID", "Nombre area", "ID maquinaria", "Codigo maquinaria", "Codigo", "Nombre", "Tipo"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String[] registro = new String[7];
        num_registros = 0;

        //Unión: area (a) - maquinaria (maq) - mantenimiento (m)
        String sSQL = "select m.id_mantenimiento, a.nombre, "
                + "m.id_maquinaria, maq.codigo, "
                + "m.codigo, m.nombre, m.tipo "
                + "from area as a inner join maquinaria as maq "
                + "on a.id_area = maq.id_area "
                + "inner join mantenimiento as m "
                + "on maq.id_maquinaria = m.id_maquinaria "
                + "inner join maquina as maq2 "
                + "on maq.id_maquinaria = maq2.id_maquinaria "
                + "where maq.codigo like '%" + buscarMaquinaria + "%' "
                + "and a.nombre like '%" + buscarArea + "%' "
                + "and maq2.id_maquina like '%" + buscarMaquina + "%' "
                + "and m.tipo like '%" + buscarTipo + "%'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registro[0] = rs.getString("m.id_mantenimiento");
                registro[1] = rs.getString("a.nombre");
                registro[2] = rs.getString("m.id_maquinaria");
                registro[3] = rs.getString("maq.codigo");
                registro[4] = rs.getString("m.codigo");
                registro[5] = rs.getString("m.nombre");
                registro[6] = rs.getString("m.tipo");
                
                modelo.addRow(registro);
                num_registros++;

            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar registros de mantenimientos");
            System.err.println("Error al mostrar registros de mantenimientos: " + e);
            return null;

        }
    }
    
    /*Este metodo recibe una maquinaria
    y verifica si hay mantenimientos en esa maquinaria*/
    public boolean hayMantenimientos(Maquinaria dts){
        
        String sSQL = "select * from mantenimiento where id_maquinaria = '" + dts.getId() + "'";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            return rs.next();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar mantenimientos de la maquinaria");
            System.err.println("Error al buscar mantenimientos de la maquinaria: " + e);
            return true;
        }
    }
}
