package consultas;

import clases.Agenda;
import clases.Ejecucion;
import clases.Jornada;
import clases.MantCorrectivo;
import clases.Mantenimiento;
import clases.Operario;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsulEjecucion {

    public static int num_registros;

    private Connection cn = Conexion.getInstance();

    public DefaultTableModel mostrar(String buscarArea, String buscarMaquinaria, Date fecha1, Date fecha2) {

        DefaultTableModel modelo;
        String[] titulos = {"ID", "ID maquina", "Codigo maquina", "ID mantenimiento", "Codigo mantenimiento", "Nombre Mantenimiento", "Tipo", "ID operario", "Nombre operario", "Fecha", "Observaciones"};
        String[] registro = new String[11];
        modelo = new DefaultTableModel(null, titulos);

        num_registros = 0;

        //Union: maquina(maq) - maquinaria(m) - mantenimiento(mant) - ejecucion(ej) - operario(op)
        String sSQL = "select ej.id_ejecucion, "
                + "ej.id_maquina, maq.codigo, "
                + "ej.id_mantenimiento, mant.codigo, mant.nombre, mant.tipo, "
                + "ej.id_operario, op.nombre, op.ap_paterno, op.ap_materno, "
                + "ej.fecha, ej.observaciones "
                + "from ejecucion as ej inner join operario as op "
                + "on ej.id_operario = op.id_operario "
                + "inner join mantenimiento as mant "
                + "on ej.id_mantenimiento = mant.id_mantenimiento "
                + "inner join maquina as maq "
                + "on ej.id_maquina = maq.id_maquina "
                + "inner join maquinaria as m "
                + "on maq.id_maquinaria = m.id_maquinaria "
                + "inner join area as a "
                + "on m.id_area = a.id_area "
                + "where m.codigo like '%" + buscarMaquinaria + "%' "
                + "and a.nombre like '%" + buscarArea + "%' "
                + "and ej.fecha between '" + fecha1 + "' and '" + fecha2 + "'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registro[0] = rs.getString("ej.id_ejecucion");
                registro[1] = rs.getString("ej.id_maquina");
                registro[2] = rs.getString("maq.codigo");
                registro[3] = rs.getString("ej.id_mantenimiento");
                registro[4] = rs.getString("mant.codigo");
                registro[5] = rs.getString("mant.nombre");
                registro[6] = rs.getString("mant.tipo");
                registro[7] = rs.getString("ej.id_operario");
                registro[8] = rs.getString("op.nombre") + " "
                        + rs.getString("op.ap_paterno") + " "
                        + rs.getString("op.ap_materno");
                registro[9] = rs.getString("ej.fecha");
                registro[10] = rs.getString("ej.observaciones");

                modelo.addRow(registro);
                num_registros++;

            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar registros de ejecuciones");
            System.err.println("Error al mostrar registros de ejecuciones: " + e);
            return null;

        }
    }

    public boolean insertar(Ejecucion dts) {

        String sSQL = "insert into ejecucion (id_maquina, id_mantenimiento, id_operario, fecha, observaciones) values (?,?,?,?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_maquina());
            pst.setInt(2, dts.getId_mantenimiento());
            pst.setInt(3, dts.getId_operario());
            pst.setDate(4, dts.getFecha());
            pst.setString(5, dts.getObservaciones());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al insertar registro de ejecucion");
            System.err.println("Error al insertar registro de ejecucion: " + e);
            return false;

        }
    }

    public boolean editar(Ejecucion dts) {

        String sSQL = "update ejecucion set id_maquina=?, id_mantenimiento=?, id_operario=?, fecha=?, observaciones=? where id_ejecucion=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_maquina());
            pst.setInt(2, dts.getId_mantenimiento());
            pst.setInt(3, dts.getId_operario());
            pst.setDate(4, dts.getFecha());
            pst.setString(5, dts.getObservaciones());
            pst.setInt(6, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar registro de ejecucion");
            System.err.println("Error al editar registro de ejecucion: " + e);
            return false;

        }
    }

    public boolean eliminar(Ejecucion dts) {

        String sSQL = "delete from ejecucion where id_ejecucion=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar registro de ejecucion");
            System.err.println("Error al eliminar registro de ejecucion: " + e);
            return false;

        }
    }

    /*Este metodo recibe un id de maquina, un id de mantenimiento 
    y una fecha y verifica si hay una ejecucion con esos parámetros*/
    public boolean verificar(int idMaquina, int idMantenimiento, Date fecha) {

        String sSQL = "select * from ejecucion "
                + "where id_maquina = '" + idMaquina + "' "
                + "and id_mantenimiento = '" + idMantenimiento + "' "
                + "and fecha = '" + fecha + "'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            return rs.next();

        } catch (Exception e) {
            System.err.println("Error al verificar ejecucion: " + e);
            return true;
        }
    }

    /*Este metodo recibe una ejecucion 
    y busca si ya ha sido registrada*/
    public boolean haSidoRegistrada(Ejecucion dts) {

        String sSQL = "select * from ejecucion "
                + "where id_maquina = '" + dts.getId_maquina() + "' "
                + "and id_mantenimiento = '" + dts.getId_mantenimiento() + "' "
                + "and fecha = '" + dts.getFecha() + "'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            return rs.next();

        } catch (Exception e) {
            System.err.println("Error al verificar si ha sido registrado: " + e);
            return true;
        }
    }

    /*Este metodo recibe los datos de una agenda
    y busca el numero de ejecuciones realizadas relacionadas con esa agenda*/
    public int numEjecuciones(Agenda dts) {

        String sSQL = "select count(*) as numEjec from ejecucion "
                + "where id_maquina = '" + dts.getId_maquina() + "' "
                + "and id_mantenimiento = '" + dts.getId_mant_prev() + "' "
                + "and fecha >= '" + dts.getFecha_inicio() + "'";

        if (dts.getEstado().equals("Detenido")) {
            Date fecha2 = new ConsulAgenda().fechaSiguienteAgenda(dts);
            if (fecha2.compareTo(dts.getFecha_inicio()) > 0) {
                sSQL += " and fecha < '" + fecha2 + "'";
            }
        }

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) {
                return rs.getInt("numEjec");
            } else {
                return 0;
            }

        } catch (Exception e) {
            System.err.println("Error al obtener numero de ejecuciones de la agenda: " + e);
            return 0;
        }
    }
    
    /*Este metodo recibe los datos de un operario
    y busca el numero de ejecuciones realizadas por ese operario*/
    public int numEjecuciones(Operario dts) {

        String sSQL = "select count(*) as numEjec from ejecucion "
                + "where id_operario = '" + dts.getId() + "'";
        System.out.println(sSQL);
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) {
                return rs.getInt("numEjec");
            } else {
                return 0;
            }

        } catch (Exception e) {
            System.err.println("Error al obtener numero de ejecuciones del operario: " + e);
            return 0;
        }
    }
    
    /*Este metodo recibe el id de un mantenimiento
    y busca el numero de ejecuciones realizadas de ese mantenimiento*/
    public int numEjecuciones(int idMantenimiento) {

        String sSQL = "select count(*) as numEjec from ejecucion "
                + "where id_mantenimiento = '" + idMantenimiento + "'";
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) {
                return rs.getInt("numEjec");
            } else {
                return 0;
            }

        } catch (Exception e) {
            System.err.println("Error al obtener numero de ejecuciones del mantenimiento: " + e);
            return 0;
        }
    }

    /*Este metodo recibe una fecha de un nuevo feriado
    y busca si hay conflictos con las fechas de ejecucion*/
    public boolean conflictosFeriado(Date fecha_feriado) {

        String sSQL = "select * from ejecucion "
                + "where fecha >= '" + fecha_feriado + "'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            return rs.next();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al obtener conflictos de ejecuciones y feriados");
            System.err.println("Error al obtener conflictos de ejecuciones y feriados: " + e);
            return true;

        }
    }

    /*Este metodo recibe una agenda iniciada o detenida
    y busca la fecha de la ultima ejecucion relacionada con esa agenda*/
    public Date fechaUltimaEjecucion(Agenda dts) {

        String sSQL = "select fecha from ejecucion "
                + "where id_maquina = '" + dts.getId_maquina() + "' "
                + "and id_mantenimiento = '" + dts.getId_mant_prev() + "' "
                + "and fecha >= '" + dts.getFecha_inicio() + "'";

        Date fecha2 = new ConsulAgenda().fechaSiguienteAgenda(dts);
        if (fecha2 != null) {//Si esta no es la ultima agenda
            sSQL += " and fecha < '" + fecha2 + "'";
        }
        sSQL += " order by fecha desc limit 1";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) {
                return rs.getDate("fecha");
            } else {
                return null;//Esta agenda no tiene ejecuciones
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener fecha de la ultima ejecucion");
            System.err.println("Error al obtener fecha de la ultima ejecucion: " + e);
            return null;
        }
    }

    /*Este metodo recibe una agenda y una nueva jornada
    y verifica si alguna ejecucion de la agenda determinada
    registrada en el año de la nueva jornada
    queda fuera de la nueva jornada*/
    public boolean conflictosJornada(Agenda ag, Jornada jorn) {

        Date fecha_inicio_anho = Auxiliar.FirstDayOfTheYear(jorn.getAnho());
        Date fecha_fin_anho = Auxiliar.LastDayOfTheYear(jorn.getAnho());

        String sSQL = "select fecha from ejecucion "
                + "where id_maquina = '" + ag.getId_maquina() + "' "
                + "and id_mantenimiento = '" + ag.getId_mant_prev() + "' "
                + "and fecha between '" + fecha_inicio_anho + "' "
                + "and '" + fecha_fin_anho + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                Date fechaEjecucion = rs.getDate("fecha");
                //La fecha de ejecucion cae fuera de la nueva jornada
                if (fechaEjecucion.compareTo(jorn.getFecha_inicial()) < 0
                        || fechaEjecucion.compareTo(jorn.getFecha_final()) > 0) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener conflictos de ejecuciones y jornada");
            System.err.println("Error al obtener conflictos de ejecuciones y jornada: " + e);
            return true;
        }
    }
}
