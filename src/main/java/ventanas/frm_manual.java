package ventanas;

import clases.Paso;
import consultas.ConsulArea;
import consultas.ConsulMaquinaria;
import consultas.ConsulPasos;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import paneles.*;

public class frm_manual extends javax.swing.JFrame {
    
    public frm_manual() {
        initComponents();
        
        //Paneles
        pnl_sesion_info.add(new pnl_sesionde());
        pnl_pie.add(new pnl_footer());

        //Rellena los combobox
        ListaAreas();
        ListaMaquinarias();
        
        //Estado inicial
        Vaciar();
        MostrarPasos();
        
        //Oculta el txt_id_paso
        txt_id_paso.setVisible(false);
    }

    //Escribe las areas registradas en el combobox
    public static void ListaAreas() {
        cmb_area.setModel(new ConsulArea().lista());
    }

    //Escribe las maquinarias registradas en el combobox
    public static void ListaMaquinarias() {
        cmb_maquinaria.setModel(new ConsulMaquinaria().lista(cmb_area.getSelectedItem().toString()));
    }
    
    //Muestra los pasos
    public static void MostrarPasos() {
        tab_pasos.setModel(new ConsulPasos().mostrar(txt_id_mantenimiento.getText()));
        OcultarColumnasPaso();
        lbl_num_registros_paso.setText("Pasos: " + ConsulPasos.num_registros);
        btn_cambiar_imagen.setEnabled(false);
    }
    
    private static void OcultarColumnasPaso() {
        int[] columnas = {0, 1, 2};
        for (int columna : columnas) {
            tab_pasos.getColumnModel().getColumn(columna).setMaxWidth(0);
            tab_pasos.getColumnModel().getColumn(columna).setMinWidth(0);
            tab_pasos.getColumnModel().getColumn(columna).setPreferredWidth(0);
        }
        
        tab_pasos.getColumnModel().getColumn(3).setMaxWidth(50);
        tab_pasos.getColumnModel().getColumn(3).setMinWidth(50);
        tab_pasos.getColumnModel().getColumn(3).setPreferredWidth(50);
    }

    //Muestra la imagen del paso
    private void MostrarImagen() {
        ImageIcon imagen = new ConsulPasos().mostrarImagen(Integer.parseInt(txt_id_paso.getText()));
        if (imagen != null) {
            imagen = new ImageIcon(imagen.getImage().getScaledInstance(lbl_imagen.getWidth(), lbl_imagen.getHeight(), Image.SCALE_DEFAULT));
            lbl_imagen.setIcon(imagen);
            lbl_mensaje.setForeground(Color.WHITE);
        }else{
            lbl_imagen.setIcon(null);
            lbl_mensaje.setForeground(Color.RED);
        }
    }
    
    //Vacia todo y lo deja en blanco
    public static void Vaciar(){
        txt_id_mantenimiento.setText("");
        txt_nombre_mantenimiento.setText("");
        txt_codigo_mantenimiento.setText("");
        txt_tipo_mantenimiento.setText("");
        txt_id_paso.setText("");
        txt_num_paso.setText("");
        lbl_imagen.setIcon(null);
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
        lbl_area = new javax.swing.JLabel();
        cmb_area = new javax.swing.JComboBox<>();
        btn_gestionar_areas = new javax.swing.JButton();
        lbl_maquinaria = new javax.swing.JLabel();
        cmb_maquinaria = new javax.swing.JComboBox<>();
        btn_gestionar_maquinas = new javax.swing.JButton();
        lbl_mantenimiento = new javax.swing.JLabel();
        txt_id_mantenimiento = new javax.swing.JTextField();
        txt_nombre_mantenimiento = new javax.swing.JTextField();
        btn_buscar_mantenimiento = new javax.swing.JButton();
        lbl_tipo_mantenimiento = new javax.swing.JLabel();
        txt_tipo_mantenimiento = new javax.swing.JTextField();
        lbl_codigo_mantenimiento = new javax.swing.JLabel();
        txt_codigo_mantenimiento = new javax.swing.JTextField();
        btn_gestionar_mantenimiento = new javax.swing.JButton();
        pnl_info = new javax.swing.JPanel();
        pnl_pasos = new javax.swing.JPanel();
        jScrollPane_tabla_pasos = new javax.swing.JScrollPane();
        tab_pasos = new javax.swing.JTable();
        lbl_num_registros_paso = new javax.swing.JLabel();
        pnl_imagen = new javax.swing.JPanel();
        pnl_datos_imagen = new javax.swing.JPanel();
        lbl_paso = new javax.swing.JLabel();
        txt_id_paso = new javax.swing.JTextField();
        txt_num_paso = new javax.swing.JTextField();
        lbl_mensaje = new javax.swing.JLabel();
        btn_cambiar_imagen = new javax.swing.JButton();
        pnl_imageicon = new javax.swing.JPanel();
        lbl_imagen = new javax.swing.JLabel();
        pnl_pie = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscador de mantenimientos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1280, 640));
        setSize(new java.awt.Dimension(1280, 640));
        getContentPane().setLayout(new java.awt.BorderLayout(0, 4));

        pnl_cabecera.setBackground(new java.awt.Color(204, 142, 53));
        pnl_cabecera.setPreferredSize(new java.awt.Dimension(1280, 80));
        pnl_cabecera.setLayout(new java.awt.BorderLayout());

        lbl_titulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(255, 255, 255));
        lbl_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_titulo.setText("BUSCADOR DE MANTENIMIENTOS");
        pnl_cabecera.add(lbl_titulo, java.awt.BorderLayout.CENTER);

        pnl_sesion_info.setBackground(new java.awt.Color(255, 177, 66));
        pnl_sesion_info.setPreferredSize(new java.awt.Dimension(120, 80));
        pnl_sesion_info.setLayout(new java.awt.GridLayout(1, 0));
        pnl_cabecera.add(pnl_sesion_info, java.awt.BorderLayout.LINE_END);

        getContentPane().add(pnl_cabecera, java.awt.BorderLayout.PAGE_START);

        pnl_contenido.setBackground(new java.awt.Color(204, 204, 204));
        pnl_contenido.setLayout(new java.awt.BorderLayout(4, 4));

        pnl_controles.setBackground(new java.awt.Color(255, 255, 255));
        pnl_controles.setPreferredSize(new java.awt.Dimension(1280, 120));

        lbl_area.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_area.setText("Area:");

        cmb_area.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_areaItemStateChanged(evt);
            }
        });

        btn_gestionar_areas.setText("Gestionar areas");
        btn_gestionar_areas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gestionar_areasActionPerformed(evt);
            }
        });

        lbl_maquinaria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_maquinaria.setText("Máquinaria:");

        cmb_maquinaria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_maquinariaItemStateChanged(evt);
            }
        });

        btn_gestionar_maquinas.setText("Gestionar maquinas");
        btn_gestionar_maquinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gestionar_maquinasActionPerformed(evt);
            }
        });

        lbl_mantenimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_mantenimiento.setText("Mantenimiento:");

        txt_id_mantenimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_id_mantenimiento.setEnabled(false);

        txt_nombre_mantenimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nombre_mantenimiento.setEnabled(false);

        btn_buscar_mantenimiento.setText("Buscar");
        btn_buscar_mantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_mantenimientoActionPerformed(evt);
            }
        });

        lbl_tipo_mantenimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_tipo_mantenimiento.setText("Tipo:");

        txt_tipo_mantenimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_tipo_mantenimiento.setEnabled(false);

        lbl_codigo_mantenimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_codigo_mantenimiento.setText("Código:");

        txt_codigo_mantenimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_codigo_mantenimiento.setEnabled(false);

        btn_gestionar_mantenimiento.setText("Gestionar mantenimiento");
        btn_gestionar_mantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gestionar_mantenimientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_controlesLayout = new javax.swing.GroupLayout(pnl_controles);
        pnl_controles.setLayout(pnl_controlesLayout);
        pnl_controlesLayout.setHorizontalGroup(
            pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_controlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_area)
                    .addComponent(lbl_maquinaria)
                    .addComponent(lbl_mantenimiento))
                .addGap(18, 18, 18)
                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnl_controlesLayout.createSequentialGroup()
                        .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmb_area, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_maquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_gestionar_maquinas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_gestionar_areas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_codigo_mantenimiento)
                            .addComponent(lbl_tipo_mantenimiento))
                        .addGap(18, 18, 18)
                        .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_codigo_mantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(txt_tipo_mantenimiento)))
                    .addGroup(pnl_controlesLayout.createSequentialGroup()
                        .addComponent(txt_id_mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nombre_mantenimiento)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_gestionar_mantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_buscar_mantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(449, Short.MAX_VALUE))
        );
        pnl_controlesLayout.setVerticalGroup(
            pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_controlesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_area)
                    .addComponent(btn_gestionar_areas)
                    .addComponent(txt_tipo_mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_tipo_mantenimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_codigo_mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_codigo_mantenimiento)
                        .addComponent(btn_gestionar_mantenimiento))
                    .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_maquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_maquinaria)
                        .addComponent(btn_gestionar_maquinas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nombre_mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar_mantenimiento)
                    .addComponent(lbl_mantenimiento)
                    .addComponent(txt_id_mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnl_contenido.add(pnl_controles, java.awt.BorderLayout.NORTH);

        pnl_info.setBackground(new java.awt.Color(204, 204, 204));
        pnl_info.setLayout(new java.awt.GridLayout(1, 0, 4, 4));

        pnl_pasos.setBackground(new java.awt.Color(255, 255, 255));

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
        jScrollPane_tabla_pasos.setViewportView(tab_pasos);

        lbl_num_registros_paso.setText("Pasos:");

        javax.swing.GroupLayout pnl_pasosLayout = new javax.swing.GroupLayout(pnl_pasos);
        pnl_pasos.setLayout(pnl_pasosLayout);
        pnl_pasosLayout.setHorizontalGroup(
            pnl_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pasosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane_tabla_pasos, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_pasosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_num_registros_paso)))
                .addContainerGap())
        );
        pnl_pasosLayout.setVerticalGroup(
            pnl_pasosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_pasosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane_tabla_pasos, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_num_registros_paso)
                .addContainerGap())
        );

        pnl_info.add(pnl_pasos);

        pnl_imagen.setLayout(new java.awt.BorderLayout());

        pnl_datos_imagen.setBackground(new java.awt.Color(255, 255, 255));
        pnl_datos_imagen.setPreferredSize(new java.awt.Dimension(913, 50));

        lbl_paso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_paso.setText("Paso:");

        txt_id_paso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_id_paso.setEnabled(false);

        txt_num_paso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_num_paso.setEnabled(false);

        lbl_mensaje.setBackground(new java.awt.Color(255, 255, 255));
        lbl_mensaje.setForeground(new java.awt.Color(255, 255, 255));
        lbl_mensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_mensaje.setText("ESTE PASO NO TIENE IMAGEN");
        lbl_mensaje.setOpaque(true);
        lbl_mensaje.setPreferredSize(new java.awt.Dimension(146, 100));

        javax.swing.GroupLayout pnl_datos_imagenLayout = new javax.swing.GroupLayout(pnl_datos_imagen);
        pnl_datos_imagen.setLayout(pnl_datos_imagenLayout);
        pnl_datos_imagenLayout.setHorizontalGroup(
            pnl_datos_imagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_datos_imagenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_paso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_id_paso, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_num_paso, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                .addComponent(lbl_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnl_datos_imagenLayout.setVerticalGroup(
            pnl_datos_imagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_datos_imagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txt_num_paso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbl_paso, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txt_id_paso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lbl_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnl_imagen.add(pnl_datos_imagen, java.awt.BorderLayout.PAGE_START);

        btn_cambiar_imagen.setBackground(new java.awt.Color(153, 153, 153));
        btn_cambiar_imagen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cambiar_imagen.setForeground(new java.awt.Color(255, 255, 255));
        btn_cambiar_imagen.setText("Cambiar imagen");
        btn_cambiar_imagen.setContentAreaFilled(false);
        btn_cambiar_imagen.setEnabled(false);
        btn_cambiar_imagen.setOpaque(true);
        btn_cambiar_imagen.setPreferredSize(new java.awt.Dimension(143, 50));
        btn_cambiar_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiar_imagenActionPerformed(evt);
            }
        });
        pnl_imagen.add(btn_cambiar_imagen, java.awt.BorderLayout.SOUTH);

        pnl_imageicon.setBackground(new java.awt.Color(255, 255, 255));

        lbl_imagen.setBackground(new java.awt.Color(255, 255, 255));
        lbl_imagen.setOpaque(true);

        javax.swing.GroupLayout pnl_imageiconLayout = new javax.swing.GroupLayout(pnl_imageicon);
        pnl_imageicon.setLayout(pnl_imageiconLayout);
        pnl_imageiconLayout.setHorizontalGroup(
            pnl_imageiconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_imageiconLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_imagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_imageiconLayout.setVerticalGroup(
            pnl_imageiconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_imageiconLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_imagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl_imagen.add(pnl_imageicon, java.awt.BorderLayout.CENTER);

        pnl_info.add(pnl_imagen);

        pnl_contenido.add(pnl_info, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_contenido, java.awt.BorderLayout.CENTER);

        pnl_pie.setBackground(new java.awt.Color(82, 190, 128));
        pnl_pie.setPreferredSize(new java.awt.Dimension(1280, 40));
        pnl_pie.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnl_pie, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(1296, 679));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_areaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_areaItemStateChanged
        ListaMaquinarias();
        Vaciar();
        MostrarPasos();
    }//GEN-LAST:event_cmb_areaItemStateChanged

    private void btn_buscar_mantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_mantenimientoActionPerformed
        frm_buscar_mantenimiento.cmb_areas.setSelectedItem(cmb_area.getSelectedItem());
        frm_buscar_mantenimiento.cmb_maquinarias.setSelectedItem(cmb_maquinaria.getSelectedItem());
        frm_buscar_mantenimiento.destino = "MN";
        new frm_buscar_mantenimiento().setVisible(true);
    }//GEN-LAST:event_btn_buscar_mantenimientoActionPerformed

    private void btn_gestionar_areasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gestionar_areasActionPerformed
        frm_areas.ventana = "MAN";
        new frm_areas().setVisible(true);
    }//GEN-LAST:event_btn_gestionar_areasActionPerformed

    private void btn_gestionar_maquinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gestionar_maquinasActionPerformed
        frm_maquinas.ventana = "MN";
        new frm_maquinas().setVisible(true);
    }//GEN-LAST:event_btn_gestionar_maquinasActionPerformed

    private void btn_cambiar_imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiar_imagenActionPerformed
        
        JFileChooser jfc = new JFileChooser();
        int ap = jfc.showOpenDialog(this);
        
        if (ap == JFileChooser.APPROVE_OPTION) {
            String ruta = jfc.getSelectedFile().getAbsolutePath();

            //Lectura de datos
            Paso dts = new Paso();
            dts.setId(Integer.parseInt(txt_id_paso.getText()));
            dts.setImagen(ruta);
            
            //Envio de datos
            ConsulPasos func = new ConsulPasos();
            if (func.insertarImagen(dts)) {
                JOptionPane.showMessageDialog(this, "Imagen registrada satisfactoriamente");
                MostrarImagen();
            }
            
        }

    }//GEN-LAST:event_btn_cambiar_imagenActionPerformed

    private void tab_pasosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_pasosMouseClicked
        //Muestra la imagen correspondiente al paso (si hay) y rellena los campos
        int fila = tab_pasos.rowAtPoint(evt.getPoint());
        txt_id_paso.setText(tab_pasos.getValueAt(fila, 0).toString());
        txt_num_paso.setText(tab_pasos.getValueAt(fila, 3).toString());
        MostrarImagen();
        btn_cambiar_imagen.setEnabled(true);
        
    }//GEN-LAST:event_tab_pasosMouseClicked

    private void btn_gestionar_mantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gestionar_mantenimientoActionPerformed
        
        if (txt_tipo_mantenimiento.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "No selecciono una actividad");
            btn_buscar_mantenimiento.requestFocus();
        }else if (txt_tipo_mantenimiento.getText().equals("MP")) {
            new frm_mant_prev().setVisible(true);
            frm_mant_prev.cmb_areas.setSelectedItem(cmb_area.getSelectedItem());
            frm_mant_prev.cmb_maquinarias.setSelectedItem(cmb_maquinaria.getSelectedItem());
            frm_mant_prev.ventana = "MN";
        }else if (txt_tipo_mantenimiento.getText().equals("MC")) {
            new frm_mant_corr().setVisible(true);
            frm_mant_corr.cmb_areas.setSelectedItem(cmb_area.getSelectedItem());
            frm_mant_corr.cmb_maquinarias.setSelectedItem(cmb_maquinaria.getSelectedItem());
            frm_mant_corr.ventana = "MN";
        }
        
    }//GEN-LAST:event_btn_gestionar_mantenimientoActionPerformed

    private void cmb_maquinariaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_maquinariaItemStateChanged
        Vaciar();
        MostrarPasos();
    }//GEN-LAST:event_cmb_maquinariaItemStateChanged

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
            java.util.logging.Logger.getLogger(frm_manual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_manual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_manual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_manual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_manual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar_mantenimiento;
    public static javax.swing.JButton btn_cambiar_imagen;
    private javax.swing.JButton btn_gestionar_areas;
    private javax.swing.JButton btn_gestionar_mantenimiento;
    private javax.swing.JButton btn_gestionar_maquinas;
    public static javax.swing.JComboBox<String> cmb_area;
    public static javax.swing.JComboBox<String> cmb_maquinaria;
    private javax.swing.JScrollPane jScrollPane_tabla_pasos;
    private javax.swing.JLabel lbl_area;
    private javax.swing.JLabel lbl_codigo_mantenimiento;
    public static javax.swing.JLabel lbl_imagen;
    private javax.swing.JLabel lbl_mantenimiento;
    private javax.swing.JLabel lbl_maquinaria;
    private javax.swing.JLabel lbl_mensaje;
    private static javax.swing.JLabel lbl_num_registros_paso;
    private javax.swing.JLabel lbl_paso;
    private javax.swing.JLabel lbl_tipo_mantenimiento;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JPanel pnl_cabecera;
    private javax.swing.JPanel pnl_contenido;
    private javax.swing.JPanel pnl_controles;
    private javax.swing.JPanel pnl_datos_imagen;
    private javax.swing.JPanel pnl_imageicon;
    private javax.swing.JPanel pnl_imagen;
    private javax.swing.JPanel pnl_info;
    private javax.swing.JPanel pnl_pasos;
    private javax.swing.JPanel pnl_pie;
    private javax.swing.JPanel pnl_sesion_info;
    private static javax.swing.JTable tab_pasos;
    public static javax.swing.JTextField txt_codigo_mantenimiento;
    public static javax.swing.JTextField txt_id_mantenimiento;
    public static javax.swing.JTextField txt_id_paso;
    public static javax.swing.JTextField txt_nombre_mantenimiento;
    public static javax.swing.JTextField txt_num_paso;
    public static javax.swing.JTextField txt_tipo_mantenimiento;
    // End of variables declaration//GEN-END:variables
}