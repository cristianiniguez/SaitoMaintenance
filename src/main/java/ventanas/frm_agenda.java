package ventanas;

import clases.Agenda;
import clases.Ejecucion;
import consultas.Auxiliar;
import consultas.ConsulAgenda;
import consultas.ConsulArea;
import consultas.ConsulDiasLaborales;
import consultas.ConsulEjecucion;
import consultas.ConsulFeriado;
import consultas.ConsulJornada;
import consultas.ConsulMaquinaria;
import consultas.ConsulOperario;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.JOptionPane;
import paneles.*;

public class frm_agenda extends javax.swing.JFrame {

    public static String ventana = "";

    private String accion = "guardar";

    //Estas variables sirven para validar cambios
    private int id_maquina0;
    private int id_mantenimiento0;
    private Date fecha_inicio0;
    private String estado0;

    public frm_agenda() {
        initComponents();

        //Paneles
        pnl_sesion_info.add(new pnl_sesionde());
        pnl_pie.add(new pnl_footer());

        //Rellena los combobox
        ListaAreas();
        ListaMaquinarias();

        //Muestralas agendas
        Mostrar();
    }

    //Escribe las areas registradas en el combobox
    public static void ListaAreas() {
        cmb_areas.setModel(new ConsulArea().lista());
    }

    //Escribe las maquinarias registradas en el combobox
    private void ListaMaquinarias() {
        cmb_maquinarias.setModel(new ConsulMaquinaria().lista(cmb_areas.getSelectedItem().toString()));
    }

    private void Inhabilitar() {
        dtc_fecha_inicio.setEnabled(false);
        cmb_estado.setEnabled(false);

        txt_id_agenda.setText("");
        txt_id_maquina.setText("");
        txt_codigo_maquina.setText("");
        txt_id_mantenimiento.setText("");
        txt_nombre_mantenimiento.setText("");

        btn_maquina.setEnabled(false);
        btn_mantenimiento.setEnabled(false);
        btn_guardar.setEnabled(false);
        btn_eliminar.setEnabled(false);
    }

    private void Habilitar() {
        dtc_fecha_inicio.setEnabled(true);
        cmb_estado.setEnabled(true);

        txt_id_agenda.setText("");
        txt_id_maquina.setText("");
        txt_codigo_maquina.setText("");
        txt_id_mantenimiento.setText("");
        txt_nombre_mantenimiento.setText("");
        dtc_fecha_inicio.setDate(new java.util.Date());

        btn_maquina.setEnabled(true);
        btn_mantenimiento.setEnabled(true);
        btn_guardar.setEnabled(true);
        btn_eliminar.setEnabled(false);
    }

    private void Mostrar() {
        String area = cmb_areas.getSelectedItem().toString();
        String maquinaria = cmb_maquinarias.getSelectedItem().toString();
        tab_agendas.setModel(new ConsulAgenda().mostrar(area, maquinaria));
        OcultarColumnas();
        lbl_num_registros.setText("Número de agendas: " + ConsulAgenda.num_registros);
    }

    private void OcultarColumnas() {
        int[] columnas = {0, 1, 3};
        for (int columna : columnas) {
            tab_agendas.getColumnModel().getColumn(columna).setMaxWidth(0);
            tab_agendas.getColumnModel().getColumn(columna).setMinWidth(0);
            tab_agendas.getColumnModel().getColumn(columna).setPreferredWidth(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_cabecera = new javax.swing.JPanel();
        lbl_titulo = new javax.swing.JLabel();
        pnl_sesion_info = new javax.swing.JPanel();
        pnl_contenido = new javax.swing.JPanel();
        pnl_controles = new javax.swing.JPanel();
        txt_id_agenda = new javax.swing.JTextField();
        lbl_maquina = new javax.swing.JLabel();
        txt_id_maquina = new javax.swing.JTextField();
        txt_codigo_maquina = new javax.swing.JTextField();
        btn_maquina = new javax.swing.JButton();
        lbl_mantenimiento = new javax.swing.JLabel();
        txt_id_mantenimiento = new javax.swing.JTextField();
        txt_nombre_mantenimiento = new javax.swing.JTextField();
        btn_mantenimiento = new javax.swing.JButton();
        lbl_fecha_inicio = new javax.swing.JLabel();
        dtc_fecha_inicio = new com.toedter.calendar.JDateChooser();
        lbl_estado = new javax.swing.JLabel();
        cmb_estado = new javax.swing.JComboBox<>();
        pnl_botones = new javax.swing.JPanel();
        btn_nuevo = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        pnl_tabla = new javax.swing.JPanel();
        lbl_buscar_area = new javax.swing.JLabel();
        cmb_areas = new javax.swing.JComboBox<>();
        lbl_buscar_maquinaria = new javax.swing.JLabel();
        cmb_maquinarias = new javax.swing.JComboBox<>();
        jScrollPane_tabla_agendas = new javax.swing.JScrollPane();
        tab_agendas = new javax.swing.JTable();
        lbl_num_registros = new javax.swing.JLabel();
        pnl_pie = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agendas");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1280, 640));
        setSize(new java.awt.Dimension(1280, 640));
        getContentPane().setLayout(new java.awt.BorderLayout(0, 4));

        pnl_cabecera.setBackground(new java.awt.Color(71, 71, 135));
        pnl_cabecera.setPreferredSize(new java.awt.Dimension(1280, 80));
        pnl_cabecera.setLayout(new java.awt.BorderLayout());

        lbl_titulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_titulo.setText("GESTOR DE AGENDAS");
        pnl_cabecera.add(lbl_titulo, java.awt.BorderLayout.CENTER);

        pnl_sesion_info.setBackground(new java.awt.Color(112, 111, 211));
        pnl_sesion_info.setPreferredSize(new java.awt.Dimension(120, 80));
        pnl_sesion_info.setLayout(new java.awt.GridLayout(1, 0));
        pnl_cabecera.add(pnl_sesion_info, java.awt.BorderLayout.LINE_END);

        getContentPane().add(pnl_cabecera, java.awt.BorderLayout.PAGE_START);

        pnl_contenido.setBackground(new java.awt.Color(204, 204, 204));
        pnl_contenido.setLayout(new java.awt.GridLayout(1, 0, 4, 4));

        pnl_controles.setBackground(new java.awt.Color(255, 255, 255));

        txt_id_agenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_id_agenda.setEnabled(false);

        lbl_maquina.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_maquina.setText("Maquina:");

        txt_id_maquina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_id_maquina.setEnabled(false);

        txt_codigo_maquina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_codigo_maquina.setEnabled(false);

        btn_maquina.setText("...");
        btn_maquina.setEnabled(false);
        btn_maquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_maquinaActionPerformed(evt);
            }
        });

        lbl_mantenimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_mantenimiento.setText("Mantenimiento:");

        txt_id_mantenimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_id_mantenimiento.setEnabled(false);

        txt_nombre_mantenimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nombre_mantenimiento.setEnabled(false);

        btn_mantenimiento.setText("...");
        btn_mantenimiento.setEnabled(false);
        btn_mantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mantenimientoActionPerformed(evt);
            }
        });

        lbl_fecha_inicio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_fecha_inicio.setText("Fecha de inicio:");

        dtc_fecha_inicio.setEnabled(false);

        lbl_estado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_estado.setText("Estado:");

        cmb_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No iniciado", "Iniciado", "Detenido" }));
        cmb_estado.setEnabled(false);

        pnl_botones.setLayout(new java.awt.GridLayout(1, 0, 4, 4));

        btn_nuevo.setBackground(new java.awt.Color(204, 204, 204));
        btn_nuevo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.setContentAreaFilled(false);
        btn_nuevo.setOpaque(true);
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });
        pnl_botones.add(btn_nuevo);

        btn_guardar.setBackground(new java.awt.Color(204, 204, 204));
        btn_guardar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setContentAreaFilled(false);
        btn_guardar.setEnabled(false);
        btn_guardar.setOpaque(true);
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        pnl_botones.add(btn_guardar);

        btn_eliminar.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setContentAreaFilled(false);
        btn_eliminar.setEnabled(false);
        btn_eliminar.setOpaque(true);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        pnl_botones.add(btn_eliminar);

        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(255, 0, 0));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(2);
        jTextArea1.setText("Es altamente preferible iniciar la agenda el mismo día que se realice el mantenimiento respectivo por primera vez, para evitar conflictos.");
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout pnl_controlesLayout = new javax.swing.GroupLayout(pnl_controles);
        pnl_controles.setLayout(pnl_controlesLayout);
        pnl_controlesLayout.setHorizontalGroup(
            pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_controlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_botones, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(pnl_controlesLayout.createSequentialGroup()
                        .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_maquina)
                            .addGroup(pnl_controlesLayout.createSequentialGroup()
                                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_mantenimiento)
                                    .addComponent(lbl_fecha_inicio)
                                    .addComponent(lbl_estado))
                                .addGap(43, 43, 43)
                                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_id_agenda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnl_controlesLayout.createSequentialGroup()
                                        .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_id_mantenimiento)
                                            .addComponent(txt_id_maquina, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_codigo_maquina, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                                            .addComponent(txt_nombre_mantenimiento))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_maquina)
                                            .addComponent(btn_mantenimiento, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(dtc_fecha_inicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmb_estado, javax.swing.GroupLayout.Alignment.LEADING, 0, 122, Short.MAX_VALUE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl_controlesLayout.setVerticalGroup(
            pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_controlesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_id_agenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id_maquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_codigo_maquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_maquina)
                    .addComponent(btn_maquina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_mantenimiento)
                    .addComponent(txt_nombre_mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_id_mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mantenimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_fecha_inicio)
                    .addComponent(dtc_fecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_estado)
                    .addComponent(cmb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(pnl_botones, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnl_contenido.add(pnl_controles);

        pnl_tabla.setBackground(new java.awt.Color(255, 255, 255));

        lbl_buscar_area.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_buscar_area.setText("Areas de trabajo:");

        cmb_areas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_areasItemStateChanged(evt);
            }
        });

        lbl_buscar_maquinaria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_buscar_maquinaria.setText("Máquinaria:");

        cmb_maquinarias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_maquinariasItemStateChanged(evt);
            }
        });

        tab_agendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_agendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_agendasMouseClicked(evt);
            }
        });
        jScrollPane_tabla_agendas.setViewportView(tab_agendas);

        lbl_num_registros.setText("Número de agendas:");

        javax.swing.GroupLayout pnl_tablaLayout = new javax.swing.GroupLayout(pnl_tabla);
        pnl_tabla.setLayout(pnl_tablaLayout);
        pnl_tablaLayout.setHorizontalGroup(
            pnl_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tablaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane_tabla_agendas, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_tablaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_num_registros))
                    .addGroup(pnl_tablaLayout.createSequentialGroup()
                        .addComponent(lbl_buscar_area)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_areas, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_buscar_maquinaria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_maquinarias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl_tablaLayout.setVerticalGroup(
            pnl_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tablaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_buscar_area)
                    .addComponent(cmb_areas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_buscar_maquinaria)
                    .addComponent(cmb_maquinarias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane_tabla_agendas, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_num_registros)
                .addContainerGap())
        );

        pnl_contenido.add(pnl_tabla);

        getContentPane().add(pnl_contenido, java.awt.BorderLayout.CENTER);

        pnl_pie.setBackground(new java.awt.Color(82, 190, 128));
        pnl_pie.setPreferredSize(new java.awt.Dimension(1280, 40));
        pnl_pie.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnl_pie, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(1296, 679));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        //Validacion de datos
        if (txt_id_maquina.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Se debe seleccionar una máquina");
            btn_maquina.requestFocus();
            return;
        }
        if (txt_id_mantenimiento.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Se debe seleccionar una actividad de mantenimiento preventivo");
            btn_mantenimiento.requestFocus();
            return;
        }

        //Lectura de datos (agenda)
        Agenda dts = new Agenda();
        dts.setId_maquina(Integer.parseInt(txt_id_maquina.getText()));
        dts.setId_mant_prev(Integer.parseInt(txt_id_mantenimiento.getText()));
        dts.setFecha_inicio(Auxiliar.SQLDate(dtc_fecha_inicio.getCalendar()));
        dts.setEstado(cmb_estado.getSelectedItem().toString());

        //Validacion de la fecha de inicio
        if (!new ConsulJornada().verificar(dts.getFecha_inicio())
                || !new ConsulDiasLaborales().verificar(dts.getFecha_inicio())
                || new ConsulFeriado().verificar(dts.getFecha_inicio())) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio especificada no corresponde a un día laboral");
            return;
        }

        //¿La agenda es iniciada el dia de hoy?
        boolean registrarEjecucion = false;
        Date fecha_hoy = Auxiliar.SQLDate(Calendar.getInstance());
        if (dts.getEstado().equals("Iniciado") && dts.getFecha_inicio().compareTo(fecha_hoy) == 0) {
            String msg = "La agenda que quiere registrar se está iniciando el día de hoy\n"
                    + "¿Quiere registrar también la primera ejecución?\n"
                    + "(La ejecución será registrada con usted como operario y sin observaciones)";
            int confirmacion = JOptionPane.showConfirmDialog(this, msg, "Confirmar", 2);
            if (confirmacion == 0) {
                registrarEjecucion = true;
            }
        }

        //Envio de datos (agenda)
        ConsulAgenda func = new ConsulAgenda();
        if (accion.equals("guardar")) {
            //¿La agenda será iniciada o no iniciada?
            if (!dts.getEstado().equals("Detenido")) {
                //Verifica si ya hay una agenda no detenida
                if (new ConsulAgenda().hayAgendaNoDetenida(dts.getId_maquina(), dts.getId_mant_prev())) {
                    String msg2 = "Ya existe una agenda iniciada o no iniciada\n"
                            + "No pueden haber dos para la mismas maquina y mantenimiento";
                    JOptionPane.showMessageDialog(this, msg2);
                    return;
                }
            }
            if (func.insertar(dts)) {
                JOptionPane.showMessageDialog(this, "Agenda registrada satisfactoriamente");
                Mostrar();
                Inhabilitar();
            }
        } else if (accion.equals("editar")) {
            dts.setId(Integer.parseInt(txt_id_agenda.getText()));
            //Verificar cambios de maquina, mantenimiento o fecha de inicio
            if (id_maquina0 != dts.getId_maquina()
                    || id_mantenimiento0 != dts.getId_mant_prev()
                    || fecha_inicio0.compareTo(dts.getFecha_inicio()) != 0) {
                //Verifica si hay ejecuciones realizadas con esta agenda
                if (new ConsulEjecucion().verificar(id_maquina0, id_mantenimiento0, fecha_inicio0)) {
                    String msg = "No se pueden ejecutar estos cambios\n"
                            + "Hay ejecuciones realizadas relacionadas con esta agenda\n"
                            + "Si quiere puede detener esta agenda y crear otra";
                    JOptionPane.showMessageDialog(this, msg);
                    return;
                }
            }
            //Verifica cambios de estado
            if (estado0.equals("Iniciado") && dts.getEstado().equals("No iniciado")) {//Si cambia de iniciado a no iniciado
                //Verifica si hay ejecuciones realizadas con esta agenda
                if (new ConsulEjecucion().verificar(id_maquina0, id_mantenimiento0, fecha_inicio0)) {//Si hay
                    String msg = "Esta agenda ya está iniciada y tiene ejecuciones realizadas\n"
                            + "Solo puede darla por detenida";
                    JOptionPane.showMessageDialog(this, msg);
                    return;
                }
            } else if (estado0.equals("No iniciado") && dts.getEstado().equals("Detenido")) {//Si cambia de no iniciado a detenido
                String msg = "Está deteniendo una agenda que no ha sido iniciada\n"
                        + "Esto significa que esta agenda no tiene ejecuciones\n"
                        + "Por lo tanto esta agenda será eliminada\n"
                        + "¿Esta seguro de continuar?";
                int confirmacion = JOptionPane.showConfirmDialog(this, msg, "Confirmar", 2);
                if (confirmacion == 0) {
                    //Elimina la agenda en vez de darla por detenida
                    if (func.eliminar(dts)) {
                        JOptionPane.showMessageDialog(this, "Agenda eliminada satisfactoriamente");
                        Mostrar();
                        Inhabilitar();
                    }
                }

                if (func.eliminar(dts)) {
                    JOptionPane.showMessageDialog(this, "Agenda eliminada satisfactoriamente");
                    Mostrar();
                    Inhabilitar();
                }
                return;
            } else if (estado0.equals("Detenido") && !dts.getEstado().equals("Detenido")) {//Si cambia de detenido a otra opcion
                String msg = "Está intentande volver a iniciar una agenda que ya detuvo antes\n"
                        + "Esto recalculará los mantenimientos desde la última ejecución\n"
                        + "¿Esta seguro de hacer esto?\n"
                        + "Recuerde que puede crear otra agenda y dejar esta como detenida";
                int confirmacion = JOptionPane.showConfirmDialog(this, msg, "Confirmar", 2);
                if (confirmacion == 0) {
                    //Verifica si ya hay una agenda no detenida
                    if (new ConsulAgenda().hayAgendaNoDetenida(dts.getId_maquina(), dts.getId_mant_prev())) {
                        String msg2 = "Ya existe una agenda iniciada o no iniciada\n"
                                + "No pueden haber dos para la mismas maquina y mantenimiento";
                        JOptionPane.showMessageDialog(this, msg2);
                        return;
                    }
                } else {
                    return;
                }
            }
            //Ejecuta los cambios (edita la agenda)
            if (func.editar(dts)) {
                JOptionPane.showMessageDialog(this, "Agenda editada satisfactoriamente");
                Mostrar();
                Inhabilitar();
            }
        }

        if (registrarEjecucion) {
            //Lectura de datos (ejecucion)
            Ejecucion dts2 = new Ejecucion();
            dts2.setId_maquina(dts.getId_maquina());
            dts2.setId_mantenimiento(dts.getId_mant_prev());
            dts2.setId_operario(ConsulOperario.USUARIO.getId());
            dts2.setFecha(dts.getFecha_inicio());
            dts2.setObservaciones("Sin observaciones");
            //Envio de datos (ejecucion)
            if (new ConsulEjecucion().insertar(dts2)) {
                JOptionPane.showMessageDialog(this, "Ejecución registrada satisfactoriamente");
            }
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        if (!txt_id_agenda.getText().equals("")) {
            //Lectura y obtencion de datos
            Agenda dts = new ConsulAgenda().obtener(Integer.parseInt(txt_id_agenda.getText()));
            //La agenda tiene ejecuciones realizadas
            if (new ConsulEjecucion().numEjecuciones(dts) > 0) {
                String msg = "Esta agenda no se puede eliminar\n"
                        + "Hay ejecuciones registradas de esta agenda";
                JOptionPane.showMessageDialog(this, msg);
                return;
            }
            //Confirmacion
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar la agenda?", "Confirmar", 2);
            if (confirmacion == 0) {
                //Envio de datos
                ConsulAgenda func = new ConsulAgenda();
                if (func.eliminar(dts)) {
                    JOptionPane.showMessageDialog(this, "Agenda eliminada satisfactoriamente");
                    Mostrar();
                    Inhabilitar();
                }
            }
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        Habilitar();
        btn_guardar.setText("Guardar");
        accion = "guardar";
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void tab_agendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_agendasMouseClicked
        //Habilitacion de campos
        btn_guardar.setText("Editar");
        Habilitar();
        btn_eliminar.setEnabled(true);
        accion = "editar";
        //Rellenado de campos
        int fila = tab_agendas.rowAtPoint(evt.getPoint());
        txt_id_agenda.setText(tab_agendas.getValueAt(fila, 0).toString());
        txt_id_maquina.setText(tab_agendas.getValueAt(fila, 1).toString());
        txt_codigo_maquina.setText(tab_agendas.getValueAt(fila, 2).toString());
        txt_id_mantenimiento.setText(tab_agendas.getValueAt(fila, 3).toString());
        txt_nombre_mantenimiento.setText(tab_agendas.getValueAt(fila, 5).toString());
        dtc_fecha_inicio.setDate(Date.valueOf(tab_agendas.getValueAt(fila, 6).toString()));
        cmb_estado.setSelectedItem(tab_agendas.getValueAt(fila, 7).toString());
        //Guardando datos actuales
        id_maquina0 = Integer.parseInt(tab_agendas.getValueAt(fila, 1).toString());
        id_mantenimiento0 = Integer.parseInt(tab_agendas.getValueAt(fila, 3).toString());
        fecha_inicio0 = Date.valueOf(tab_agendas.getValueAt(fila, 6).toString());
        estado0 = tab_agendas.getValueAt(fila, 7).toString();

    }//GEN-LAST:event_tab_agendasMouseClicked

    private void cmb_areasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_areasItemStateChanged
        ListaMaquinarias();
        Mostrar();
    }//GEN-LAST:event_cmb_areasItemStateChanged

    private void cmb_maquinariasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_maquinariasItemStateChanged
        Mostrar();
    }//GEN-LAST:event_cmb_maquinariasItemStateChanged

    private void btn_maquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_maquinaActionPerformed
        frm_buscar_maquina form = new frm_buscar_maquina();
        frm_buscar_maquina.destino = "AG";
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btn_maquinaActionPerformed

    private void btn_mantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mantenimientoActionPerformed
        if (txt_id_maquina.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Primero seleccione una maquina");
            btn_maquina.requestFocus();
        } else {
            frm_buscar_mantenimiento.tipo = "MP";
            frm_buscar_mantenimiento.id_maquina = txt_id_maquina.getText();
            frm_buscar_mantenimiento.destino = "AG";
            frm_buscar_mantenimiento form = new frm_buscar_mantenimiento();
            form.toFront();
            form.setVisible(true);
        }
    }//GEN-LAST:event_btn_mantenimientoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_agenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_mantenimiento;
    private javax.swing.JButton btn_maquina;
    private javax.swing.JButton btn_nuevo;
    public static javax.swing.JComboBox<String> cmb_areas;
    private javax.swing.JComboBox<String> cmb_estado;
    public static javax.swing.JComboBox<String> cmb_maquinarias;
    private com.toedter.calendar.JDateChooser dtc_fecha_inicio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane_tabla_agendas;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_buscar_area;
    private javax.swing.JLabel lbl_buscar_maquinaria;
    private javax.swing.JLabel lbl_estado;
    private javax.swing.JLabel lbl_fecha_inicio;
    private javax.swing.JLabel lbl_mantenimiento;
    private javax.swing.JLabel lbl_maquina;
    private javax.swing.JLabel lbl_num_registros;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JPanel pnl_botones;
    private javax.swing.JPanel pnl_cabecera;
    private javax.swing.JPanel pnl_contenido;
    private javax.swing.JPanel pnl_controles;
    private javax.swing.JPanel pnl_pie;
    private javax.swing.JPanel pnl_sesion_info;
    private javax.swing.JPanel pnl_tabla;
    private javax.swing.JTable tab_agendas;
    public static javax.swing.JTextField txt_codigo_maquina;
    private javax.swing.JTextField txt_id_agenda;
    public static javax.swing.JTextField txt_id_mantenimiento;
    public static javax.swing.JTextField txt_id_maquina;
    public static javax.swing.JTextField txt_nombre_mantenimiento;
    // End of variables declaration//GEN-END:variables
}
