package ventanas;

import clases.MantCorrectivo;
import clases.Paso;
import consultas.ConsulArea;
import consultas.ConsulEjecucion;
import consultas.ConsulMantCorrectivo;
import consultas.ConsulMaquinaria;
import consultas.ConsulPasos;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import paneles.*;

public class frm_mant_corr extends javax.swing.JFrame {

    public static String ventana = "";

    private String accionMantenimiento = "guardar";
    private String accionPaso = "guardar";

    private int numPaso0 = 0;

    public frm_mant_corr() {
        initComponents();

        //Paneles
        pnl_sesion_info.add(new pnl_sesionde());
        pnl_pie.add(new pnl_footer());

        //Panel Mantenimiento
        ListaAreas();
        ListaMaquinarias();
        MostrarMantenimientos();

        //Panel Pasos
        MostrarPasos();

    }

    //Escribe las areas registradas en el combobox
    public static void ListaAreas() {
        cmb_areas.setModel(new ConsulArea().lista());
    }

    //Escribe las maquinarias registradas en el combobox
    public static void ListaMaquinarias() {
        cmb_maquinarias.setModel(new ConsulMaquinaria().lista(cmb_areas.getSelectedItem().toString()));
    }

    private void InhabilitarMantenimiento() {
        txt_codigo.setEnabled(false);
        txt_nombre.setEnabled(false);
        txt_descripcion.setEnabled(false);

        txt_id_maquinaria.setText("");
        txt_codigo_maquinaria.setText("");
        txt_codigo.setText("");
        txt_nombre.setText("");
        txt_descripcion.setText("");

        btn_maquinaria.setEnabled(false);
        btn_guardar_mantenimiento.setEnabled(false);
        btn_eliminar_mantenimiento.setEnabled(false);
    }

    private void HabilitarMantenimiento() {
        txt_codigo.setEnabled(true);
        txt_nombre.setEnabled(true);
        txt_descripcion.setEnabled(true);

        txt_id_mantenimiento.setText("");
        txt_id_maquinaria.setText("");
        txt_codigo_maquinaria.setText("");
        txt_codigo.setText("");
        txt_nombre.setText("");
        txt_descripcion.setText("");

        btn_maquinaria.setEnabled(true);
        btn_guardar_mantenimiento.setEnabled(true);
        btn_eliminar_mantenimiento.setEnabled(false);
    }

    public static void MostrarMantenimientos() {
        String area = cmb_areas.getSelectedItem().toString();
        String maquinaria = cmb_maquinarias.getSelectedItem().toString();
        tab_mantenimientos.setModel(new ConsulMantCorrectivo().mostrar(area, maquinaria));
        OcultarColumnasMantenimiento();
        lbl_num_registros_mantenimiento.setText("Número de mantenimientos preventivos: " + ConsulMantCorrectivo.num_registros);
    }

    private static void OcultarColumnasMantenimiento() {
        int[] columnas = {0, 1};
        for (int columna : columnas) {
            tab_mantenimientos.getColumnModel().getColumn(columna).setMaxWidth(0);
            tab_mantenimientos.getColumnModel().getColumn(columna).setMinWidth(0);
            tab_mantenimientos.getColumnModel().getColumn(columna).setPreferredWidth(0);
        }
    }

    private void InhabilitarPaso() {
        spn_numero.setEnabled(false);
        txt_descripcion_paso.setEnabled(false);

        txt_id_mantenimiento2.setText("");
        txt_codigo_mantenimiento2.setText("");
        txt_descripcion_paso.setText("");

        btn_guardar_paso.setEnabled(false);
        btn_eliminar_paso.setEnabled(false);
    }

    private void HabilitarPaso() {
        spn_numero.setEnabled(true);
        txt_descripcion_paso.setEnabled(true);

        txt_id_mantenimiento2.setText("");
        txt_codigo_mantenimiento2.setText("");
        txt_descripcion_paso.setText("");

        btn_guardar_paso.setEnabled(true);
        btn_eliminar_paso.setEnabled(false);
    }

    private void MostrarPasos() {
        tab_pasos.setModel(new ConsulPasos().mostrar(txt_id_mantenimiento2.getText()));
        OcultarColumnasPaso();
        lbl_num_registros_paso.setText("Pasos: " + ConsulPasos.num_registros);
    }

    private void OcultarColumnasPaso() {
        int[] columnas = {0, 1};
        for (int columna : columnas) {
            tab_pasos.getColumnModel().getColumn(columna).setMaxWidth(0);
            tab_pasos.getColumnModel().getColumn(columna).setMinWidth(0);
            tab_pasos.getColumnModel().getColumn(columna).setPreferredWidth(0);
        }
    }

    //Número de pasos en el spin
    private void NumeroPasos() {
        int numPasos = new ConsulPasos().numeroPasos(txt_id_mantenimiento2.getText());
        if (accionPaso.equals("guardar")) {
            spn_numero.setModel(new SpinnerNumberModel(numPasos + 1, 1, numPasos + 1, 1));
        } else if (accionPaso.equals("editar")) {
            spn_numero.setModel(new SpinnerNumberModel(1, 1, numPasos, 1));
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
        pnl_controles_mantenimiento = new javax.swing.JPanel();
        txt_id_mantenimiento = new javax.swing.JTextField();
        lbl_maquinaria = new javax.swing.JLabel();
        txt_id_maquinaria = new javax.swing.JTextField();
        txt_codigo_maquinaria = new javax.swing.JTextField();
        btn_maquinaria = new javax.swing.JButton();
        lbl_codigo = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        lbl_nombre = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        lbl_descripcion_mantenimiento = new javax.swing.JLabel();
        jScrollPane_descripcion_mantenimiento = new javax.swing.JScrollPane();
        txt_descripcion = new javax.swing.JTextArea();
        pnl_botones_mantenimiento = new javax.swing.JPanel();
        btn_nuevo_mantenimiento = new javax.swing.JButton();
        btn_guardar_mantenimiento = new javax.swing.JButton();
        btn_eliminar_mantenimiento = new javax.swing.JButton();
        pnl_tabla_mantenimiento = new javax.swing.JPanel();
        lbl_buscar_area = new javax.swing.JLabel();
        cmb_areas = new javax.swing.JComboBox<>();
        lbl_buscar_maquinaria = new javax.swing.JLabel();
        cmb_maquinarias = new javax.swing.JComboBox<>();
        btn_gestionar_maquinas = new javax.swing.JButton();
        jScrollPane_tabla_mantenimiento = new javax.swing.JScrollPane();
        tab_mantenimientos = new javax.swing.JTable();
        lbl_num_registros_mantenimiento = new javax.swing.JLabel();
        pnl_controles_pasos = new javax.swing.JPanel();
        txt_id_paso = new javax.swing.JTextField();
        lbl_mantenimiento = new javax.swing.JLabel();
        txt_id_mantenimiento2 = new javax.swing.JTextField();
        txt_codigo_mantenimiento2 = new javax.swing.JTextField();
        lbl_numero = new javax.swing.JLabel();
        spn_numero = new javax.swing.JSpinner();
        lbl_descripcion = new javax.swing.JLabel();
        jScrollPane_descripcion = new javax.swing.JScrollPane();
        txt_descripcion_paso = new javax.swing.JTextArea();
        pnl_botones_pasos = new javax.swing.JPanel();
        btn_nuevo_paso = new javax.swing.JButton();
        btn_guardar_paso = new javax.swing.JButton();
        btn_eliminar_paso = new javax.swing.JButton();
        pnl_tabla_pasos = new javax.swing.JPanel();
        jScrollPane_tabla_paso = new javax.swing.JScrollPane();
        tab_pasos = new javax.swing.JTable();
        lbl_num_registros_paso = new javax.swing.JLabel();
        pnl_pie = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimientos correctivos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(1280, 720));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.BorderLayout(0, 4));

        pnl_cabecera.setBackground(new java.awt.Color(24, 106, 59));
        pnl_cabecera.setPreferredSize(new java.awt.Dimension(1280, 80));
        pnl_cabecera.setLayout(new java.awt.BorderLayout());

        lbl_titulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_titulo.setText("GESTOR DE MANTENIMIENTOS CORRECTIVOS");
        pnl_cabecera.add(lbl_titulo, java.awt.BorderLayout.CENTER);

        pnl_sesion_info.setBackground(new java.awt.Color(82, 190, 128));
        pnl_sesion_info.setPreferredSize(new java.awt.Dimension(120, 80));
        pnl_sesion_info.setLayout(new java.awt.GridLayout(1, 0));
        pnl_cabecera.add(pnl_sesion_info, java.awt.BorderLayout.LINE_END);

        getContentPane().add(pnl_cabecera, java.awt.BorderLayout.PAGE_START);

        pnl_contenido.setBackground(new java.awt.Color(204, 204, 204));
        pnl_contenido.setLayout(new java.awt.GridLayout(2, 0, 4, 4));

        pnl_controles_mantenimiento.setBackground(new java.awt.Color(255, 255, 255));

        txt_id_mantenimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_id_mantenimiento.setEnabled(false);

        lbl_maquinaria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_maquinaria.setText("Maquinaria:");

        txt_id_maquinaria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_id_maquinaria.setEnabled(false);

        txt_codigo_maquinaria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_codigo_maquinaria.setEnabled(false);

        btn_maquinaria.setText("...");
        btn_maquinaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_maquinariaActionPerformed(evt);
            }
        });

        lbl_codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_codigo.setText("Código:");

        txt_codigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbl_nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_nombre.setText("Nombre:");

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbl_descripcion_mantenimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_descripcion_mantenimiento.setText("Descripción:");

        txt_descripcion.setColumns(20);
        txt_descripcion.setLineWrap(true);
        txt_descripcion.setRows(4);
        jScrollPane_descripcion_mantenimiento.setViewportView(txt_descripcion);

        pnl_botones_mantenimiento.setLayout(new java.awt.GridLayout(1, 0, 4, 4));

        btn_nuevo_mantenimiento.setBackground(new java.awt.Color(204, 204, 204));
        btn_nuevo_mantenimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_nuevo_mantenimiento.setText("Nuevo");
        btn_nuevo_mantenimiento.setContentAreaFilled(false);
        btn_nuevo_mantenimiento.setOpaque(true);
        btn_nuevo_mantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevo_mantenimientoActionPerformed(evt);
            }
        });
        pnl_botones_mantenimiento.add(btn_nuevo_mantenimiento);

        btn_guardar_mantenimiento.setBackground(new java.awt.Color(204, 204, 204));
        btn_guardar_mantenimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_guardar_mantenimiento.setText("Guardar");
        btn_guardar_mantenimiento.setContentAreaFilled(false);
        btn_guardar_mantenimiento.setEnabled(false);
        btn_guardar_mantenimiento.setOpaque(true);
        btn_guardar_mantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_mantenimientoActionPerformed(evt);
            }
        });
        pnl_botones_mantenimiento.add(btn_guardar_mantenimiento);

        btn_eliminar_mantenimiento.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminar_mantenimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_eliminar_mantenimiento.setText("Eliminar");
        btn_eliminar_mantenimiento.setContentAreaFilled(false);
        btn_eliminar_mantenimiento.setEnabled(false);
        btn_eliminar_mantenimiento.setOpaque(true);
        btn_eliminar_mantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_mantenimientoActionPerformed(evt);
            }
        });
        pnl_botones_mantenimiento.add(btn_eliminar_mantenimiento);

        javax.swing.GroupLayout pnl_controles_mantenimientoLayout = new javax.swing.GroupLayout(pnl_controles_mantenimiento);
        pnl_controles_mantenimiento.setLayout(pnl_controles_mantenimientoLayout);
        pnl_controles_mantenimientoLayout.setHorizontalGroup(
            pnl_controles_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_controles_mantenimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_controles_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_botones_mantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(pnl_controles_mantenimientoLayout.createSequentialGroup()
                        .addGroup(pnl_controles_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_codigo)
                            .addComponent(lbl_nombre)
                            .addComponent(lbl_descripcion_mantenimiento))
                        .addGap(39, 39, 39)
                        .addGroup(pnl_controles_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane_descripcion_mantenimiento)
                            .addComponent(txt_codigo)
                            .addComponent(txt_nombre)
                            .addGroup(pnl_controles_mantenimientoLayout.createSequentialGroup()
                                .addComponent(txt_id_mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 376, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_controles_mantenimientoLayout.createSequentialGroup()
                        .addComponent(lbl_maquinaria)
                        .addGap(42, 42, 42)
                        .addComponent(txt_id_maquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo_maquinaria, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_maquinaria)))
                .addContainerGap())
        );
        pnl_controles_mantenimientoLayout.setVerticalGroup(
            pnl_controles_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_controles_mantenimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_id_mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controles_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id_maquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_codigo_maquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_maquinaria)
                    .addComponent(btn_maquinaria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controles_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_codigo)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controles_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nombre)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controles_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_controles_mantenimientoLayout.createSequentialGroup()
                        .addComponent(jScrollPane_descripcion_mantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_botones_mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_controles_mantenimientoLayout.createSequentialGroup()
                        .addComponent(lbl_descripcion_mantenimiento)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnl_contenido.add(pnl_controles_mantenimiento);

        pnl_tabla_mantenimiento.setBackground(new java.awt.Color(255, 255, 255));

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

        btn_gestionar_maquinas.setText("Gestionar maquinas");
        btn_gestionar_maquinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gestionar_maquinasActionPerformed(evt);
            }
        });

        tab_mantenimientos.setModel(new javax.swing.table.DefaultTableModel(
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
        tab_mantenimientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_mantenimientosMouseClicked(evt);
            }
        });
        jScrollPane_tabla_mantenimiento.setViewportView(tab_mantenimientos);

        lbl_num_registros_mantenimiento.setText("Número de mantenimientos preventivos:");

        javax.swing.GroupLayout pnl_tabla_mantenimientoLayout = new javax.swing.GroupLayout(pnl_tabla_mantenimiento);
        pnl_tabla_mantenimiento.setLayout(pnl_tabla_mantenimientoLayout);
        pnl_tabla_mantenimientoLayout.setHorizontalGroup(
            pnl_tabla_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tabla_mantenimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_tabla_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane_tabla_mantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(pnl_tabla_mantenimientoLayout.createSequentialGroup()
                        .addComponent(lbl_buscar_area)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_areas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_buscar_maquinaria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_maquinarias, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_gestionar_maquinas))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_tabla_mantenimientoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_num_registros_mantenimiento)))
                .addContainerGap())
        );
        pnl_tabla_mantenimientoLayout.setVerticalGroup(
            pnl_tabla_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tabla_mantenimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_tabla_mantenimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_buscar_area)
                    .addComponent(cmb_areas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_buscar_maquinaria)
                    .addComponent(cmb_maquinarias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_gestionar_maquinas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane_tabla_mantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_num_registros_mantenimiento)
                .addContainerGap())
        );

        pnl_contenido.add(pnl_tabla_mantenimiento);

        pnl_controles_pasos.setBackground(new java.awt.Color(255, 255, 255));

        txt_id_paso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_id_paso.setEnabled(false);

        lbl_mantenimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_mantenimiento.setText("Mantenimiento:");

        txt_id_mantenimiento2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_id_mantenimiento2.setEnabled(false);

        txt_codigo_mantenimiento2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_codigo_mantenimiento2.setEnabled(false);

        lbl_numero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_numero.setText("Paso #:");

        spn_numero.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1, 1));

        lbl_descripcion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_descripcion.setText("Descripción:");

        txt_descripcion_paso.setColumns(20);
        txt_descripcion_paso.setRows(5);
        jScrollPane_descripcion.setViewportView(txt_descripcion_paso);

        pnl_botones_pasos.setLayout(new java.awt.GridLayout(1, 0, 4, 4));

        btn_nuevo_paso.setBackground(new java.awt.Color(204, 204, 204));
        btn_nuevo_paso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_nuevo_paso.setText("Nuevo");
        btn_nuevo_paso.setContentAreaFilled(false);
        btn_nuevo_paso.setOpaque(true);
        btn_nuevo_paso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevo_pasoActionPerformed(evt);
            }
        });
        pnl_botones_pasos.add(btn_nuevo_paso);

        btn_guardar_paso.setBackground(new java.awt.Color(204, 204, 204));
        btn_guardar_paso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_guardar_paso.setText("Guardar");
        btn_guardar_paso.setContentAreaFilled(false);
        btn_guardar_paso.setEnabled(false);
        btn_guardar_paso.setOpaque(true);
        btn_guardar_paso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_pasoActionPerformed(evt);
            }
        });
        pnl_botones_pasos.add(btn_guardar_paso);

        btn_eliminar_paso.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminar_paso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_eliminar_paso.setText("Eliminar");
        btn_eliminar_paso.setContentAreaFilled(false);
        btn_eliminar_paso.setEnabled(false);
        btn_eliminar_paso.setOpaque(true);
        btn_eliminar_paso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_pasoActionPerformed(evt);
            }
        });
        pnl_botones_pasos.add(btn_eliminar_paso);

        javax.swing.GroupLayout pnl_controles_pasosLayout = new javax.swing.GroupLayout(pnl_controles_pasos);
        pnl_controles_pasos.setLayout(pnl_controles_pasosLayout);
        pnl_controles_pasosLayout.setHorizontalGroup(
            pnl_controles_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_controles_pasosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_controles_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_controles_pasosLayout.createSequentialGroup()
                        .addComponent(pnl_botones_pasos, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnl_controles_pasosLayout.createSequentialGroup()
                        .addGroup(pnl_controles_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_mantenimiento)
                            .addComponent(lbl_numero)
                            .addComponent(lbl_descripcion))
                        .addGap(25, 25, 25)
                        .addGroup(pnl_controles_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_controles_pasosLayout.createSequentialGroup()
                                .addGroup(pnl_controles_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnl_controles_pasosLayout.createSequentialGroup()
                                        .addComponent(txt_id_mantenimiento2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_codigo_mantenimiento2))
                                    .addComponent(jScrollPane_descripcion))
                                .addContainerGap())
                            .addGroup(pnl_controles_pasosLayout.createSequentialGroup()
                                .addGroup(pnl_controles_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_id_paso, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spn_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        pnl_controles_pasosLayout.setVerticalGroup(
            pnl_controles_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_controles_pasosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_id_paso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controles_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_mantenimiento)
                    .addGroup(pnl_controles_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_codigo_mantenimiento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id_mantenimiento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controles_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_numero)
                    .addComponent(spn_numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controles_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_descripcion)
                    .addComponent(jScrollPane_descripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_botones_pasos, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnl_contenido.add(pnl_controles_pasos);

        pnl_tabla_pasos.setBackground(new java.awt.Color(255, 255, 255));

        tab_pasos.setModel(new javax.swing.table.DefaultTableModel(
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
        tab_pasos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_pasosMouseClicked(evt);
            }
        });
        jScrollPane_tabla_paso.setViewportView(tab_pasos);

        lbl_num_registros_paso.setText("Pasos:");

        javax.swing.GroupLayout pnl_tabla_pasosLayout = new javax.swing.GroupLayout(pnl_tabla_pasos);
        pnl_tabla_pasos.setLayout(pnl_tabla_pasosLayout);
        pnl_tabla_pasosLayout.setHorizontalGroup(
            pnl_tabla_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tabla_pasosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_tabla_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane_tabla_paso, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_tabla_pasosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_num_registros_paso)))
                .addContainerGap())
        );
        pnl_tabla_pasosLayout.setVerticalGroup(
            pnl_tabla_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tabla_pasosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane_tabla_paso, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_num_registros_paso)
                .addContainerGap())
        );

        pnl_contenido.add(pnl_tabla_pasos);

        getContentPane().add(pnl_contenido, java.awt.BorderLayout.CENTER);

        pnl_pie.setBackground(new java.awt.Color(82, 190, 128));
        pnl_pie.setPreferredSize(new java.awt.Dimension(1280, 40));
        pnl_pie.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnl_pie, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(1296, 759));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardar_mantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_mantenimientoActionPerformed
        //Validacion de datos
        if (txt_id_maquinaria.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Se debe seleccionar la maquinaria");
            btn_maquinaria.requestFocus();
            return;
        }
        if (txt_codigo.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Se debe ingresar un código para el mantenimiento preventivo");
            txt_codigo.requestFocus();
            return;
        }
        if (txt_nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Se debe ingresar un nombre descriptivo para el mantenimiento");
            txt_nombre.requestFocus();
            return;
        }
        if (txt_descripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Se debe ingresar una descripción para el mantenimiento");
            txt_descripcion.requestFocus();
            return;
        }

        //Lectura de datos
        MantCorrectivo dts = new MantCorrectivo();
        dts.setId_maquinaria(Integer.parseInt(txt_id_maquinaria.getText()));
        dts.setCodigo(txt_codigo.getText());
        dts.setNombre(txt_nombre.getText());
        dts.setDescripcion(txt_descripcion.getText());

        //Envio de datos
        ConsulMantCorrectivo func = new ConsulMantCorrectivo();
        if (accionMantenimiento.equals("guardar")) {
            if (func.insertar(dts)) {
                JOptionPane.showMessageDialog(this, "Mantenimiento correctivo registrado satisfactoriamente");
                MostrarMantenimientos();
                InhabilitarMantenimiento();
            }
        } else if (accionMantenimiento.equals("editar")) {
            dts.setId(Integer.parseInt(txt_id_mantenimiento.getText()));
            if (func.editar(dts)) {
                JOptionPane.showMessageDialog(this, "Mantenimiento correctivo editado satisfactoriamente");
                MostrarMantenimientos();
                InhabilitarMantenimiento();
            }
        }
    }//GEN-LAST:event_btn_guardar_mantenimientoActionPerformed

    private void btn_eliminar_mantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_mantenimientoActionPerformed
        if (!txt_id_mantenimiento.getText().equals("")) {
            //Lectura y obtencion de datos
            MantCorrectivo dts = new ConsulMantCorrectivo().obtener(Integer.parseInt(txt_id_mantenimiento.getText()));
            //El mantenimiento tiene ejecuciones realizadas
            if (new ConsulEjecucion().numEjecuciones(dts.getId())> 0) {
                String msg = "Este mantenimiento no se puede eliminar\n"
                        + "Hay ejecuciones registradas de este mantenimiento";
                JOptionPane.showMessageDialog(this, msg);
                return;
            }
            //Confirmacion
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar el mantenimiento?", "Confirmar", 2);
            if (confirmacion == 0) {
                //Envio de datos
                ConsulMantCorrectivo func = new ConsulMantCorrectivo();
                if (func.eliminar(dts)) {
                    JOptionPane.showMessageDialog(this, "Mantenimiento eliminado satisfactoriamente");
                    MostrarMantenimientos();
                    InhabilitarMantenimiento();
                }
            }
        }
    }//GEN-LAST:event_btn_eliminar_mantenimientoActionPerformed

    private void btn_guardar_pasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_pasoActionPerformed
        //Validacion de datos
        if (txt_id_mantenimiento2.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Se debe seleccionar el mantenimiento");
            return;
        }
        if (txt_descripcion_paso.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Se debe ingresar una descripción para el paso");
            txt_descripcion_paso.requestFocus();
            return;
        }

        //Lectura de datos
        Paso dts = new Paso();
        dts.setId_mantenimiento(Integer.parseInt(txt_id_mantenimiento2.getText()));
        dts.setNumero(Integer.parseInt(spn_numero.getValue().toString()));
        dts.setDescripcion(txt_descripcion_paso.getText());

        //Envio de datos
        ConsulPasos func = new ConsulPasos();
        if (accionPaso.equals("guardar")) {
            if (func.insertar(dts)) {
                JOptionPane.showMessageDialog(this, "Paso registrado satisfactoriamente");
                MostrarPasos();
                InhabilitarPaso();
            }
        } else if (accionPaso.equals("editar")) {
            dts.setId(Integer.parseInt(txt_id_paso.getText()));
            if (func.recorrerPasos(dts.getId_mantenimiento(), numPaso0 + 1, false)) {
                if (func.recorrerPasos(dts.getId_mantenimiento(), dts.getNumero() + 1, true)) {
                    if (func.editar(dts)) {
                        JOptionPane.showMessageDialog(this, "Paso editado satisfactoriamente");
                        MostrarPasos();
                        InhabilitarPaso();
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_guardar_pasoActionPerformed

    private void btn_eliminar_pasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_pasoActionPerformed
        if (!txt_id_paso.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este paso?", "Confirmar", 2);
            if (confirmacion == 0) {
                //Lectura de datos
                Paso dts = new Paso();
                dts.setId_mantenimiento(Integer.parseInt(txt_id_mantenimiento2.getText()));
                dts.setNumero(numPaso0);
                dts.setId(Integer.parseInt(txt_id_paso.getText()));
                //Envio de datos
                ConsulPasos func = new ConsulPasos();
                if (func.eliminar(dts)) {
                    JOptionPane.showMessageDialog(this, "Paso eliminado satisfactoriamente");
                    MostrarPasos();
                    InhabilitarPaso();
                }
            }
        }
    }//GEN-LAST:event_btn_eliminar_pasoActionPerformed

    private void btn_maquinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_maquinariaActionPerformed
        frm_buscar_maquinarias.destino = "MC";
        frm_buscar_maquinarias form = new frm_buscar_maquinarias();
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btn_maquinariaActionPerformed

    private void btn_gestionar_maquinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gestionar_maquinasActionPerformed
        frm_maquinas.ventana = "MC";
        new frm_maquinas().setVisible(true);
    }//GEN-LAST:event_btn_gestionar_maquinasActionPerformed

    private void btn_nuevo_mantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevo_mantenimientoActionPerformed
        HabilitarMantenimiento();
        btn_guardar_mantenimiento.setText("Guardar");
        accionMantenimiento = "guardar";
    }//GEN-LAST:event_btn_nuevo_mantenimientoActionPerformed

    private void cmb_areasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_areasItemStateChanged
        ListaMaquinarias();
        MostrarMantenimientos();
    }//GEN-LAST:event_cmb_areasItemStateChanged

    private void cmb_maquinariasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_maquinariasItemStateChanged
        MostrarMantenimientos();
    }//GEN-LAST:event_cmb_maquinariasItemStateChanged

    private void tab_mantenimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_mantenimientosMouseClicked
        //Habilitacion de campos mantenimiento
        btn_guardar_mantenimiento.setText("Editar");
        HabilitarMantenimiento();
        btn_eliminar_mantenimiento.setEnabled(true);
        accionMantenimiento = "editar";

        //Rellenado de campos mantenimiento
        int fila = tab_mantenimientos.rowAtPoint(evt.getPoint());
        txt_id_mantenimiento.setText(tab_mantenimientos.getValueAt(fila, 0).toString());
        txt_id_maquinaria.setText(tab_mantenimientos.getValueAt(fila, 1).toString());
        txt_codigo_maquinaria.setText(tab_mantenimientos.getValueAt(fila, 2).toString());
        txt_codigo.setText(tab_mantenimientos.getValueAt(fila, 3).toString());
        txt_nombre.setText(tab_mantenimientos.getValueAt(fila, 4).toString());
        txt_descripcion.setText(tab_mantenimientos.getValueAt(fila, 5).toString());

        //Rellenado de campos paso
        if (accionPaso.equals("guardar")) {
            txt_id_mantenimiento2.setText(tab_mantenimientos.getValueAt(fila, 0).toString());
            txt_codigo_mantenimiento2.setText(tab_mantenimientos.getValueAt(fila, 3).toString());
            MostrarPasos();
            NumeroPasos();
        }

    }//GEN-LAST:event_tab_mantenimientosMouseClicked

    private void btn_nuevo_pasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevo_pasoActionPerformed
        HabilitarPaso();
        btn_guardar_paso.setText("Guardar");
        accionPaso = "guardar";
        NumeroPasos();
    }//GEN-LAST:event_btn_nuevo_pasoActionPerformed

    private void tab_pasosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_pasosMouseClicked
        //Habilitacion de campos paso
        btn_guardar_paso.setText("Editar");
        HabilitarPaso();
        btn_eliminar_paso.setEnabled(true);
        accionPaso = "editar";

        //Rellenado de campos paso
        int fila = tab_pasos.rowAtPoint(evt.getPoint());
        txt_id_paso.setText(tab_pasos.getValueAt(fila, 0).toString());
        txt_id_mantenimiento2.setText(tab_pasos.getValueAt(fila, 1).toString());
        txt_codigo_mantenimiento2.setText(tab_pasos.getValueAt(fila, 2).toString());
        spn_numero.setValue(Integer.parseInt(tab_pasos.getValueAt(fila, 3).toString()));
        txt_descripcion_paso.setText(tab_pasos.getValueAt(fila, 4).toString());

        //Guardando datos actuales
        numPaso0 = Integer.parseInt(tab_pasos.getValueAt(fila, 3).toString());

    }//GEN-LAST:event_tab_pasosMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        if (ventana.equals("MN")) {
            frm_manual.Vaciar();
            frm_manual.MostrarPasos();
        }
        ventana = "";

    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(frm_mant_corr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_mant_corr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_mant_corr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_mant_corr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_mant_corr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar_mantenimiento;
    private javax.swing.JButton btn_eliminar_paso;
    private javax.swing.JButton btn_gestionar_maquinas;
    private javax.swing.JButton btn_guardar_mantenimiento;
    private javax.swing.JButton btn_guardar_paso;
    private javax.swing.JButton btn_maquinaria;
    private javax.swing.JButton btn_nuevo_mantenimiento;
    private javax.swing.JButton btn_nuevo_paso;
    public static javax.swing.JComboBox<String> cmb_areas;
    public static javax.swing.JComboBox<String> cmb_maquinarias;
    private javax.swing.JScrollPane jScrollPane_descripcion;
    private javax.swing.JScrollPane jScrollPane_descripcion_mantenimiento;
    private javax.swing.JScrollPane jScrollPane_tabla_mantenimiento;
    private javax.swing.JScrollPane jScrollPane_tabla_paso;
    private javax.swing.JLabel lbl_buscar_area;
    private javax.swing.JLabel lbl_buscar_maquinaria;
    private javax.swing.JLabel lbl_codigo;
    private javax.swing.JLabel lbl_descripcion;
    private javax.swing.JLabel lbl_descripcion_mantenimiento;
    private javax.swing.JLabel lbl_mantenimiento;
    private javax.swing.JLabel lbl_maquinaria;
    private javax.swing.JLabel lbl_nombre;
    private static javax.swing.JLabel lbl_num_registros_mantenimiento;
    private static javax.swing.JLabel lbl_num_registros_paso;
    private javax.swing.JLabel lbl_numero;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JPanel pnl_botones_mantenimiento;
    private javax.swing.JPanel pnl_botones_pasos;
    private javax.swing.JPanel pnl_cabecera;
    private javax.swing.JPanel pnl_contenido;
    private javax.swing.JPanel pnl_controles_mantenimiento;
    private javax.swing.JPanel pnl_controles_pasos;
    private javax.swing.JPanel pnl_pie;
    private javax.swing.JPanel pnl_sesion_info;
    private javax.swing.JPanel pnl_tabla_mantenimiento;
    private javax.swing.JPanel pnl_tabla_pasos;
    private javax.swing.JSpinner spn_numero;
    private static javax.swing.JTable tab_mantenimientos;
    private javax.swing.JTable tab_pasos;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_codigo_mantenimiento2;
    public static javax.swing.JTextField txt_codigo_maquinaria;
    private javax.swing.JTextArea txt_descripcion;
    private javax.swing.JTextArea txt_descripcion_paso;
    private javax.swing.JTextField txt_id_mantenimiento;
    private javax.swing.JTextField txt_id_mantenimiento2;
    public static javax.swing.JTextField txt_id_maquinaria;
    private javax.swing.JTextField txt_id_paso;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
