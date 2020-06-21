package consultas;

import clases.Paso;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsulPasos {

    public static int num_registros;

    private Connection cn = Conexion.getInstance();

    public DefaultTableModel mostrar(String buscar) {

        String[] titulos = {"ID", "ID mantenimiento", "Codigo mantenimiento", "Numero", "Descripcion"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String[] registro = new String[5];
        num_registros = 0;

        //Union: mantenimiento (m) - paso(p)
        String sSQL = "select p.id_paso, "
                + "p.id_mantenimiento, m.codigo, "
                + "p.numero, p.descripcion "
                + "from paso as p inner join mantenimiento as m "
                + "on p.id_mantenimiento = m.id_mantenimiento "
                + "where p.id_mantenimiento = '" + buscar + "' "
                + "order by p.numero";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registro[0] = rs.getString("id_paso");
                registro[1] = rs.getString("id_mantenimiento");
                registro[2] = rs.getString("codigo");
                registro[3] = rs.getString("numero");
                registro[4] = rs.getString("descripcion");

                modelo.addRow(registro);
                num_registros++;

            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar pasos");
            System.err.println("Error al mostrar pasos: " + e);
            return null;

        }
    }

    public boolean insertar(Paso dts) {

        //+1 a los numeros de los pasos que vendrán después
        if (!recorrerPasos(dts.getId_mantenimiento(), dts.getNumero(), true)) {
            return false;
        }

        //Insertando el nuevo paso a la BD
        String sSQL = "insert into paso (id_mantenimiento, numero, descripcion) values (?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_mantenimiento());
            pst.setInt(2, dts.getNumero());
            pst.setString(3, dts.getDescripcion());
            return pst.executeUpdate() != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar paso");
            System.err.println("Error al insertar paso: " + e);
            return false;
        }

    }

    public boolean editar(Paso dts) {

        String sSQL = "update paso set id_mantenimiento=?, numero=?, descripcion=? where id_paso=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_mantenimiento());
            pst.setInt(2, dts.getNumero());
            pst.setString(3, dts.getDescripcion());
            pst.setInt(4, dts.getId());
            return pst.executeUpdate() != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar paso");
            System.err.println("Error al editar paso: " + e);
            return false;
        }
    }

    public boolean editarNumero(int idPaso, int numPaso) {

        String sSQL = "update paso set numero=? where id_paso=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, numPaso);
            pst.setInt(2, idPaso);
            return pst.executeUpdate() != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar el numero de paso");
            System.err.println("Error al editar el numero de paso: " + e);
            return false;
        }
    }

    public boolean eliminar(Paso dts) {

        //-1 a los numeros de los pasos que vendrán después
        if (!recorrerPasos(dts.getId_mantenimiento(), dts.getNumero() + 1, false)) {
            return false;
        }

        //Eliminando el paso de la BD
        String sSQL = "delete from paso where id_paso=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId());
            return pst.executeUpdate() != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar paso");
            System.err.println("Error al eliminar paso: " + e);
            return false;
        }

    }

    /*Este metodo devuelve los id's de los pasos del mantenimiento recibido
    ordenados por numero de paso*/
    private ArrayList<Integer> listaIdPasos(int idMantenimiento) {

        ArrayList<Integer> listaIDs = new ArrayList<>();
        String sSQL = "select id_paso from paso "
                + "where id_mantenimiento = '" + idMantenimiento + "' "
                + "order by numero";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                listaIDs.add(rs.getInt("id_paso"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener id's de pasos");
            System.err.println("Error al obtener id's de pasos: " + e);
        }
        return listaIDs;
    }

    /*Este metodo recorre los pasos de un mantenimiento 
    un lugar hacia adelante (dir = 1) o hacia atras (dir = -1)
    desde una posicion*/
    public boolean recorrerPasos(int idMantenimiento, int numPaso1, boolean subir) {
        ArrayList<Integer> ids = listaIdPasos(idMantenimiento);
        for (int i = numPaso1 - 1; i < ids.size(); i++) {
            int id = ids.get(i);
            int pos = numeroPaso(id);
            if (pos != 0) {
                if (subir) {
                    pos++;
                } else {
                    pos--;
                }
                if (!editarNumero(id, pos)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /*Este metodo devuelve el numero(lugar) del paso recibido*/
    private int numeroPaso(int idPaso) {

        String sSQL = "select numero "
                + "from paso where id_paso = '" + idPaso + "'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            if (rs.next()) {
                return rs.getInt("numero");
            }
            return 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al obtener el numero de paso");
            System.err.println("Error al obtener el numero de paso: " + e);
            return 0;
        }
    }

    public int numeroPasos(String id_mantenimiento) {
        int numPasos = 0;

        String sSQL = "select count(*) as numPasos "
                + "from paso where id_mantenimiento = '" + id_mantenimiento + "'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            if (rs.next()) {
                numPasos = rs.getInt("numPasos");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar numero de pasos");
            System.err.println("Error al numero de pasos: " + e);

        }

        return numPasos;
    }

    public ImageIcon mostrarImagen(int idPaso) {

        ImageIcon imagen = null;

        String sSQL = "select imagen from paso where id_paso = '" + idPaso + "' ";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            if (rs.next()) {
                Blob blob = rs.getBlob("imagen");
                if (blob != null) {
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    imagen = new ImageIcon(ImageIO.read(new ByteArrayInputStream(data)));
                }
            }
            return imagen;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar imagen");
            System.err.println("Error al mostrar imagen: " + e);
            return null;

        }
    }

    public boolean insertarImagen(Paso dts) {

        String sSQL = "update paso set imagen=? where id_paso=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setBinaryStream(1, new FileInputStream(new File(dts.getImagen())));
            pst.setInt(2, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al insertar imagen en paso");
            System.err.println("Error al insertar imagen en paso: " + e);
            return false;

        }
    }
}
