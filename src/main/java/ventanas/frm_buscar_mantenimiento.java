package ventanas;

import consultas.ConsulArea;
import consultas.ConsulMantenimiento;
import consultas.ConsulMaquinaria;

public class frm_buscar_mantenimiento extends javax.swing.JFrame {

    public static String destino = "";
    public static String tipo = "";
    public static String id_maquina = "";

    public frm_buscar_mantenimiento() {
        initComponents();

        //Rellena los combobox
        ListaAreas();
        ListaMaquinarias();

        //Mostrar mantenimientos
        MostrarMantenimientos();
    }

    //Escribe las areas registradas en el combobox
    public static void ListaAreas() {
        cmb_areas.setModel(new ConsulArea().lista());
    }

    //Escribe las maquinarias registradas en el combobox
    public static void ListaMaquinarias() {
        cmb_maquinarias.setModel(new ConsulMaquinaria().lista(cmb_areas.getSelectedItem().toString()));
    }

    public static void MostrarMantenimientos() {
        String area = cmb_areas.getSelectedItem().toString();
        String maquinaria = cmb_maquinarias.getSelectedItem().toString();
        tab_mantenimientos.setModel(new ConsulMantenimiento().mostrar(area, maquinaria, tipo, id_maquina));
        OcultarColumnasMantenimiento();
        lbl_num_registros_mantenimiento.setText("Número de mantenimientos: " + ConsulMantenimiento.num_registros);
    }

    private static void OcultarColumnasMantenimiento() {
        int[] columnas = {0, 1, 2};
        for (int columna : columnas) {
            tab_mantenimientos.getColumnModel().getColumn(columna).setMaxWidth(0);
            tab_mantenimientos.getColumnModel().getColumn(columna).setMinWidth(0);
            tab_mantenimientos.getColumnModel().getColumn(columna).setPreferredWidth(0);
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

        pnl_tabla1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_mantenimientos = new javax.swing.JTable();
        cmb_areas = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cmb_maquinarias = new javax.swing.JComboBox<>();
        lbl_num_registros_mantenimiento = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimientos preventivos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(640, 640));

        pnl_tabla1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Areas de trabajo:");

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab_mantenimientosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tab_mantenimientos);

        cmb_areas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_areasItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Máquinaria:");

        cmb_maquinarias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_maquinariasItemStateChanged(evt);
            }
        });

        lbl_num_registros_mantenimiento.setText("Número de mantenimientos:");

        javax.swing.GroupLayout pnl_tabla1Layout = new javax.swing.GroupLayout(pnl_tabla1);
        pnl_tabla1.setLayout(pnl_tabla1Layout);
        pnl_tabla1Layout.setHorizontalGroup(
            pnl_tabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tabla1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_tabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                    .addGroup(pnl_tabla1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_areas, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_maquinarias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_tabla1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_num_registros_mantenimiento)))
                .addContainerGap())
        );
        pnl_tabla1Layout.setVerticalGroup(
            pnl_tabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tabla1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_tabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmb_areas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cmb_maquinarias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_num_registros_mantenimiento)
                .addContainerGap())
        );

        getContentPane().add(pnl_tabla1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(656, 679));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_maquinariasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_maquinariasItemStateChanged
        MostrarMantenimientos();
    }//GEN-LAST:event_cmb_maquinariasItemStateChanged

    private void cmb_areasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_areasItemStateChanged
        ListaMaquinarias();
        MostrarMantenimientos();
    }//GEN-LAST:event_cmb_areasItemStateChanged

    private void tab_mantenimientosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_mantenimientosMousePressed
        if (evt.getClickCount() == 2) {

            int fila = tab_mantenimientos.getSelectedRow();

            if (destino.equals("MN")) {//Si lo llama el manual
                frm_manual.cmb_area.setSelectedItem(tab_mantenimientos.getValueAt(fila, 1).toString());
                frm_manual.cmb_maquinaria.setSelectedItem(tab_mantenimientos.getValueAt(fila, 3).toString());

                frm_manual.txt_id_mantenimiento.setText(tab_mantenimientos.getValueAt(fila, 0).toString());
                frm_manual.txt_nombre_mantenimiento.setText(tab_mantenimientos.getValueAt(fila, 5).toString());
                frm_manual.txt_codigo_mantenimiento.setText(tab_mantenimientos.getValueAt(fila, 4).toString());
                frm_manual.txt_tipo_mantenimiento.setText(tab_mantenimientos.getValueAt(fila, 6).toString());

                frm_manual.MostrarPasos();
                frm_manual.btn_cambiar_imagen.setEnabled(false);
            } else if (destino.equals("AG")) {//Si lo llama el gestor de agendas
                frm_agenda.txt_id_mantenimiento.setText(tab_mantenimientos.getValueAt(fila, 0).toString());
                frm_agenda.txt_nombre_mantenimiento.setText(tab_mantenimientos.getValueAt(fila, 5).toString());
            } else if (destino.equals("EJ")) {//Si lo llama el gestor de ejecuciones
                frm_ejecucion.txt_id_mantenimiento.setText(tab_mantenimientos.getValueAt(fila, 0).toString());
                frm_ejecucion.txt_codigo_mantenimiento.setText(tab_mantenimientos.getValueAt(fila, 4).toString());
                frm_ejecucion.txt_nombre_mantenimiento.setText(tab_mantenimientos.getValueAt(fila, 5).toString());
                frm_ejecucion.txt_tipo_mantenimiento.setText(tab_mantenimientos.getValueAt(fila, 6).toString());
            }

            this.dispose();
        }
    }//GEN-LAST:event_tab_mantenimientosMousePressed

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
            java.util.logging.Logger.getLogger(frm_buscar_mantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_buscar_mantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_buscar_mantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_buscar_mantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_buscar_mantenimiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> cmb_areas;
    public static javax.swing.JComboBox<String> cmb_maquinarias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JLabel lbl_num_registros_mantenimiento;
    private javax.swing.JPanel pnl_tabla1;
    public static javax.swing.JTable tab_mantenimientos;
    // End of variables declaration//GEN-END:variables
}