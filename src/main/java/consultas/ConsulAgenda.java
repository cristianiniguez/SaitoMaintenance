package consultas;

import clases.Agenda;
import clases.Ejecucion;
import clases.Jornada;
import clases.MantPreventivo;
import clases.Maquina;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsulAgenda {

    public static int num_registros;

    private Connection cn = Conexion.getInstance();

    public DefaultTableModel mostrar(String buscarArea, String buscarMaquinaria) {

        String[] titulos = {"ID",
            "ID maquina", "Maquina",
            "ID mp", "Codigo mantenimiento", "Mantenimiento preventivo",
            "Fecha de inicio", "Estado", "Ejecuciones"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String[] registro = new String[9];
        num_registros = 0;

        //Union: area(a) - maquinaria(m) - maquina(maq) - agenda(ag) - mant_prev(mp) - mantenimiento(mant)
        String sSQL = "select ag.id_agenda, "
                + "ag.id_maquina, maq.codigo, "
                + "ag.id_mant_prev, mant.codigo, mant.nombre, "
                + "ag.fecha_inicio, ag.estado "
                + "from area as a inner join maquinaria as m "
                + "on a.id_area = m.id_area "
                + "inner join maquina as maq "
                + "on m.id_maquinaria = maq.id_maquinaria "
                + "inner join agenda as ag "
                + "on maq.id_maquina = ag.id_maquina "
                + "inner join mant_preventivo as mp "
                + "on ag.id_mant_prev = mp.id_mantenimiento "
                + "inner join mantenimiento as mant "
                + "on mp.id_mantenimiento = mant.id_mantenimiento "
                + "where m.codigo like '%" + buscarMaquinaria + "%' "
                + "and a.nombre like '%" + buscarArea + "%'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registro[0] = rs.getString("ag.id_agenda");
                registro[1] = rs.getString("ag.id_maquina");
                registro[2] = rs.getString("maq.codigo");
                registro[3] = rs.getString("ag.id_mant_prev");
                registro[4] = rs.getString("mant.codigo");
                registro[5] = rs.getString("mant.nombre");
                registro[6] = rs.getString("ag.fecha_inicio");
                registro[7] = rs.getString("ag.estado");

                //Obteniendo numero de ejecuciones
                Agenda ag = new Agenda();
                ag.setId(rs.getInt("ag.id_agenda"));
                ag.setId_maquina(rs.getInt("ag.id_maquina"));
                ag.setId_mant_prev(rs.getInt("ag.id_mant_prev"));
                ag.setFecha_inicio(rs.getDate("ag.fecha_inicio"));
                ag.setEstado(rs.getString("ag.estado"));
                registro[8] = "" + new ConsulEjecucion().numEjecuciones(ag);

                modelo.addRow(registro);
                num_registros++;

            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar registros de agendas");
            System.err.println("Error al mostrar registros de agendas: " + e);
            return null;

        }
    }

    /*Muestra las fechas agendadas sin ejecucion de cada agenda, dependiendo de los parámetros, en el menu principal*/
    public DefaultTableModel mostrarPendientes(String buscarArea, String buscarMaquinaria, Date fecha1, Date fecha2) {

        String[] titulos = {"ID",
            "ID maquina", "Maquina",
            "ID mp", "Cod mp", "Mantenimiento preventivo", "Tipo mp",
            "Fecha de realización"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String[] registro = new String[8];
        num_registros = 0;

        //Union: area(a) - maquinaria(m) - maquina(maq) - agenda(ag) - mant_prev(mp) - mantenimiento(mant)
        String sSQL = "select ag.id_agenda, maq.id_maquina, maq.codigo, "
                + "ag.id_mant_prev, mant.codigo, mant.nombre, mant.tipo "
                + "from area as a inner join maquinaria as m "
                + "on a.id_area = m.id_area "
                + "inner join maquina as maq "
                + "on m.id_maquinaria = maq.id_maquinaria "
                + "inner join agenda as ag "
                + "on maq.id_maquina = ag.id_maquina "
                + "inner join mant_preventivo as mp "
                + "on ag.id_mant_prev = mp.id_mantenimiento "
                + "inner join mantenimiento as mant "
                + "on mp.id_mantenimiento = mant.id_mantenimiento "
                + "where m.codigo like '%" + buscarMaquinaria + "%' "
                + "and a.nombre like '%" + buscarArea + "%' "
                + "and ag.estado = 'Iniciado'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                //Obteniendo datos
                int idMaquina = rs.getInt("maq.id_maquina");
                int idMantenimiento = rs.getInt("ag.id_mant_prev");

                //Obteniendo las fechas agendadas
                ArrayList<Date> fechasAgendadas = fechasAgendadas(rs.getInt("ag.id_agenda"), fecha1, fecha2);
                //Revisando las fechas agendadas
                for (Date fecha : fechasAgendadas) {
                    //¿Ya hubo una ejecución de este mantenimiento en esta agenda?
                    boolean hayEjecucion = new ConsulEjecucion().verificar(idMaquina, idMantenimiento, fecha);
                    if (!hayEjecucion) {
                        registro[0] = rs.getString("ag.id_agenda");
                        registro[1] = rs.getString("maq.id_maquina");
                        registro[2] = rs.getString("maq.codigo");
                        registro[3] = rs.getString("ag.id_mant_prev");
                        registro[4] = rs.getString("mant.codigo");
                        registro[5] = rs.getString("mant.nombre");
                        registro[6] = rs.getString("mant.tipo");
                        registro[7] = fecha.toString();
                        modelo.addRow(registro);
                        num_registros++;
                    }
                }
            }
            return modelo;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar registros de agendas");
            System.err.println("Error al mostrar registros de agendas: " + e);
            return null;

        }
    }

    /*Este metodo obtiene un id de agenda y devuelve la agenda correspondiente*/
    public Agenda obtener(int idAgenda) {

        Agenda dts = new Agenda();
        String sSQL = "select * from agenda where id_agenda = '" + idAgenda + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) {
                dts.setId(rs.getInt("id_agenda"));
                dts.setId_maquina(rs.getInt("id_maquina"));
                dts.setId_mant_prev(rs.getInt("id_mant_prev"));
                dts.setFecha_inicio(rs.getDate("fecha_inicio"));
                dts.setEstado(rs.getString("estado"));
                return dts;
            } else {
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener registro de agenda");
            System.err.println("Error al obtener registro de agenda: " + e);
            return null;
        }
    }

    public boolean insertar(Agenda dts) {

        String sSQL = "insert into agenda (id_maquina, id_mant_prev, fecha_inicio, estado) values (?,?,?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_maquina());
            pst.setInt(2, dts.getId_mant_prev());
            pst.setDate(3, dts.getFecha_inicio());
            pst.setString(4, dts.getEstado());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al insertar registro de agenda");
            System.err.println("Error al insertar registro de agenda: " + e);
            return false;

        }
    }

    public boolean editar(Agenda dts) {

        String sSQL = "update agenda set id_maquina=?, id_mant_prev=?, fecha_inicio=?, estado=? where id_agenda=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_maquina());
            pst.setInt(2, dts.getId_mant_prev());
            pst.setDate(3, dts.getFecha_inicio());
            pst.setString(4, dts.getEstado());
            pst.setInt(5, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al editar registro de agenda");
            System.err.println("Error al editar registro de agenda: " + e);
            return false;

        }
    }

    public boolean iniciar(int id, Date fecha_inicio) {

        String sSQL = "update agenda set fecha_inicio=?, estado=? where id_agenda=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setDate(1, fecha_inicio);
            pst.setString(2, "Iniciado");
            pst.setInt(3, id);

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al iniciar agenda");
            System.err.println("Error al iniciar agenda: " + e);
            return false;

        }
    }

    public boolean eliminar(Agenda dts) {

        String sSQL = "delete from agenda where id_agenda=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId());

            return pst.executeUpdate() != 0;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al eliminar registro de agenda");
            System.err.println("Error al eliminar registro de agenda: " + e);
            return false;

        }
    }

    /*Este metodo obtiene todas las fecha que corresponden a una agenda dentro de dos fechas determinadas*/
    public ArrayList<Date> fechasAgendadas(int idAgenda, Date fecha1, Date fecha2) {

        ArrayList<Date> fechasAgenda = new ArrayList<>();

        String sSQL = "select ag.fecha_inicio, mp.numero_frecuencia, mp.unidad_frecuencia "
                + "from agenda as ag inner join mant_preventivo as mp "
                + "on ag.id_mant_prev = mp.id_mantenimiento "
                + "where id_agenda = '" + idAgenda + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            if (rs.next()) {
                Date fecha = rs.getDate("ag.fecha_inicio");
                int numFrecuencia = rs.getInt("mp.numero_frecuencia");
                String unidFrecuencia = rs.getString("mp.unidad_frecuencia");
                //Buscando las fechas validas
                Calendar cal = Calendar.getInstance();
                while (fecha.compareTo(fecha2) <= 0) {
                    //Agregando la fecha al Array (por defecto la fecha de inicio siempre será válida)
                    if (fecha.compareTo(fecha1) >= 0) {
                        fechasAgenda.add(fecha);
                    }
                    //Calculando la siguiente fecha
                    cal.setTime(fecha);
                    switch (unidFrecuencia) {
                        case "Diario" ->
                            cal.add(Calendar.DAY_OF_MONTH, numFrecuencia);
                        case "Semanal" ->
                            cal.add(Calendar.WEEK_OF_MONTH, numFrecuencia);
                        case "Mensual" ->
                            cal.add(Calendar.MONTH, numFrecuencia);
                        case "Anual" ->
                            cal.add(Calendar.YEAR, numFrecuencia);
                    }
                    //Verifica si es fecha válida (si no, aumenta un día)
                    while (!new ConsulJornada().verificar(Auxiliar.SQLDate(cal))
                            || !new ConsulDiasLaborales().verificar(Auxiliar.SQLDate(cal))
                            || new ConsulFeriado().verificar(Auxiliar.SQLDate(cal))) {
                        cal.add(Calendar.DATE, 1);
                    }
                    fecha = Auxiliar.SQLDate(cal);
                }
            }
            return fechasAgenda;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener fechas agendadas");
            System.err.println("Error al obtener fechas agendadas: " + e);
            return null;
        }
    }

    /*Este metodo recibe una fecha de un nuevo feriado
    y busca si hay conflictos con las agendas*/
    public boolean conflictosFeriado(Date fechaFeriado) {

        boolean conflictos = false;
        String sSQL = "select id_agenda from agenda";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next() && !conflictos) {
                int idAgenda = rs.getInt("id_agenda");
                Agenda ag = obtener(idAgenda);
                //¿La fecha del feriado cae entre la fecha de inicio y la fecha de fin?
                //Obteniendo fecha de inicio
                Date fechaInicio = ag.getFecha_inicio();
                //Viendo si la fecha del feriado esta despues de la fecha de inicio
                if (fechaFeriado.compareTo(fechaInicio) >= 0) {
                    //Obteniendo fecha de fin (fecha de ultima ejecucion)
                    Date fechaFin = new ConsulEjecucion().fechaUltimaEjecucion(ag);
                    if (fechaFin != null) {
                        //Viendo si la fecha del feriado esta antes de la fecha de fin
                        if (fechaFeriado.compareTo(fechaFin) <= 0) {
                            conflictos = true;
                        }
                    }
                }
            }
            return conflictos;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al obtener conflictos de agendas y feriados");
            System.err.println("Error al obtener conflictos de agendas y feriados: " + e);
            return true;

        }
    }

    /*Este metodo recibe una nueva jornada (nuevas fechas de inicio y fin) 
    y busca si hay conflictos con las fechas de inicio de las agendas*/
    public boolean conflictosJornada(Jornada jorn) {

        int anho_jornada = jorn.getAnho();
        
        String sSQL = "select id_agenda, fecha_inicio from agenda";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                int idAgenda = rs.getInt("id_agenda");
                //Fecha de inicio
                Date fecha_inicio_agenda = rs.getDate("fecha_inicio");
                int anho_inicio_agenda = Auxiliar.YearFromDate(fecha_inicio_agenda);
                //Fecha de fin
                Agenda ag = obtener(idAgenda);
                Date fecha_fin_agenda = new ConsulEjecucion().fechaUltimaEjecucion(ag);
                int anho_fin_agenda = Auxiliar.YearFromDate(fecha_fin_agenda);
                //¿La agenda inicia despues, durante o antes del año de la nueva jornada?
                if (anho_inicio_agenda > anho_jornada) {//Inicia despues
                    //No hay conflictos
                }else if(anho_inicio_agenda == anho_jornada){//Inicia durante
                    //¿La fecha de inicio de la agenda queda fuera de la nueva jornada?
                    if (fecha_inicio_agenda.compareTo(jorn.getFecha_inicial()) < 0
                            || fecha_inicio_agenda.compareTo(jorn.getFecha_final()) > 0) {
                        return true;
                    }else{
                        //Verificar conflictos con ejecuciones (si hay ejecuciones que se saldran de la nueva jornada)
                        if (new ConsulEjecucion().conflictosJornada(ag, jorn)) {
                            return true;
                        }
                    }
                }else if(anho_inicio_agenda < anho_jornada){//Inicia antes
                    //¿La fecha de fin cae antes, durante o despues de la nueva jornada?
                    if (anho_fin_agenda < anho_jornada) {
                        //No hay conflictos
                    }else{
                        //Verificar conflictos con ejecuciones (si hay ejecuciones que se saldran de la nueva jornada)
                        if (new ConsulEjecucion().conflictosJornada(ag, jorn)) {
                            return true;
                        }
                    }
                }
            }
            return false;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al obtener conflictos de agendas y jornada");
            System.err.println("Error al obtener conflictos de agendas y jornada: " + e);
            return true;

        }
    }

    /*Este metodo es llamado cada vez que se guarda la semana laboral
    y busca si hay agendas registradas*/
    public boolean conflictosDiasLaborales() {

        String sSQL = "select * from agenda";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            return rs.next();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al obtener conflictos de agendas y dias laborales");
            System.err.println("Error al obtener conflictos de agendas y dias laborales: " + e);
            return true;

        }
    }
    
    /*Este metodo recibe una maquina
    y verifica si hay agendas relacionadas con esa maquina*/
    public boolean hayAgenda(Maquina dts){
        String sSQL = "select * from agenda "
                + "where id_maquina = '" + dts.getId() + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            return rs.next();
        } catch (Exception e) {
            System.err.println("Error al verificar si la maquina tiene agenda: " + e);
            return true;
        }
    }

    /*Este metodo recibe un mantenimiento preventivo
    y verifica si hay agendas relacionadas con ese mantenimiento*/
    public boolean hayAgenda(MantPreventivo dts) {

        String sSQL = "select * from agenda "
                + "where id_mant_prev = '" + dts.getId() + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            return rs.next();
        } catch (Exception e) {
            System.err.println("Error al verificar si el mantenimiento tiene agenda: " + e);
            return true;
        }
    }

    /* Este metodo recibe un id de maquina y un id de mantenimiento
    y verifica si no hay una agenda no detenida*/
    public boolean hayAgendaNoDetenida(int idMaquina, int idMantenimiento) {

        String sSQL = "select id_agenda "
                + "where id_maquina = '" + idMaquina + "' "
                + "and id_mant_prev = '" + idMantenimiento + "' "
                + "where not estado = 'Detenido'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            return rs.next();
        } catch (Exception e) {
            System.err.println("Error al verificar si hay agenda no detenida: " + e);
            return true;
        }
    }

    /*Este metodo recibe una ejecución nueva
    y verifica si hay una fecha agendada para esa ejecución.
    Si no encuentra agenda devuelve "sinAgenda"
    Si encuentra agenda pero no iniciada devuelve "conAgendaNoIniciada" y el id de la agenda
    Si encuentra agenda iniciada pero no fecha agendada devuelve "sinFecha"
    Si encuentra agenda iniciada y fecha agendada devuelve "conFecha"*/
    public String[] verificar(Ejecucion dts) {

        String[] respuesta = {"", ""};

        String sSQL = "select id_agenda, estado from agenda "
                + "where id_maquina = '" + dts.getId_maquina() + "' "
                + "and id_mant_prev = '" + dts.getId_mantenimiento() + "' "
                + "and not estado = 'Detenido'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) {//Hay agenda
                if (rs.getString("estado").equals("Iniciado")) {
                    int idAgenda = rs.getInt("id_agenda");
                    Date fechaEjecucion = dts.getFecha();
                    //Obtiene las fecha agendadas de ese mantenimiento
                    ArrayList<Date> fechasAgendadas = fechasAgendadas(idAgenda, fechaEjecucion, fechaEjecucion);
                    if (fechasAgendadas.isEmpty()) {
                        respuesta[0] = "sinFecha";
                    } else {
                        respuesta[0] = "conFecha";
                    }
                } else {
                    respuesta[0] = "conAgendaNoIniciada=";
                    respuesta[1] = rs.getString("id_agenda");
                }
            } else {//No hay agenda
                respuesta[0] = "sinAgenda";
            }
            return respuesta;
        } catch (Exception e) {
            System.err.println("Error al verificar agenda de ejecucion: " + e);
            return respuesta;
        }
    }

    /*Este metodo recibe una agenda detenida
    y busca la fecha de inicio de la agenda siguiente
    (si no hay una agenda siguiente devuelve null)*/
    public Date fechaSiguienteAgenda(Agenda dts) {

        String sSQL = "select fecha_inicio from agenda "
                + "where id_maquina = '" + dts.getId_maquina() + "' "
                + "and id_mant_prev = '" + dts.getId_mant_prev() + "' "
                + "and fecha_inicio > '" + dts.getFecha_inicio() + "' "
                + "limit 1";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) { //Hay siguiente agenda
                return rs.getDate("fecha_inicio");
            } else { //No hay siguiente agenda (esta agenda es la ultima)
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error al obtener la fecha de la siguiente agenda: " + e);
            return null;
        }
    }
}
