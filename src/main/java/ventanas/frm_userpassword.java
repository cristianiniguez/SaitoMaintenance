package ventanas;

import clases.Operario;
import consultas.ConsulOperario;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class frm_userpassword extends javax.swing.JFrame {

    public int id_op;
    public char[] password_op;

    public frm_userpassword() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_title = new javax.swing.JLabel();
        lbl_user = new javax.swing.JLabel();
        txt_user = new javax.swing.JTextField();
        chb_cambiar_password = new javax.swing.JCheckBox();
        lbl_actualPassword = new javax.swing.JLabel();
        txt_actualPassword = new javax.swing.JPasswordField();
        lbl_newPassword1 = new javax.swing.JLabel();
        txt_newPassword1 = new javax.swing.JPasswordField();
        lbl_newPassword2 = new javax.swing.JLabel();
        txt_newPassword2 = new javax.swing.JPasswordField();
        btn_editar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cambiar usuario y contraseña");
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(400, 300));
        setResizable(false);
        setSize(new java.awt.Dimension(400, 300));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbl_title.setBackground(new java.awt.Color(132, 129, 122));
        lbl_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_title.setForeground(new java.awt.Color(255, 255, 255));
        lbl_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_title.setText("CAMBIAR NOMBRE DE USUARIO Y CONTRASEÑA");
        lbl_title.setOpaque(true);

        lbl_user.setText("Usuario:");

        chb_cambiar_password.setText("Cambiar contraseña");
        chb_cambiar_password.setOpaque(false);
        chb_cambiar_password.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chb_cambiar_passwordStateChanged(evt);
            }
        });

        lbl_actualPassword.setText("Contraseña actual:");

        txt_actualPassword.setEnabled(false);

        lbl_newPassword1.setText("Nueva contraseña:");

        txt_newPassword1.setEnabled(false);

        lbl_newPassword2.setText("Confirmar contraseña:");

        txt_newPassword2.setEnabled(false);

        btn_editar.setBackground(new java.awt.Color(204, 204, 204));
        btn_editar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_editar.setText("Editar");
        btn_editar.setContentAreaFilled(false);
        btn_editar.setOpaque(true);
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chb_cambiar_password)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_user)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_user, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_newPassword1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_newPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_newPassword2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(txt_newPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_actualPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_actualPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(lbl_title, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_user)
                    .addComponent(txt_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chb_cambiar_password)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_actualPassword)
                    .addComponent(txt_actualPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_newPassword1)
                    .addComponent(txt_newPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_newPassword2)
                    .addComponent(txt_newPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(366, 339));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chb_cambiar_passwordStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chb_cambiar_passwordStateChanged
        if (chb_cambiar_password.isSelected()) {
            txt_actualPassword.setEnabled(true);
            txt_newPassword1.setEnabled(true);
            txt_newPassword2.setEnabled(true);
        } else {
            txt_actualPassword.setEnabled(false);
            txt_newPassword1.setEnabled(false);
            txt_newPassword2.setEnabled(false);
        }
        txt_actualPassword.setText("");
        txt_newPassword1.setText("");
        txt_newPassword2.setText("");
    }//GEN-LAST:event_chb_cambiar_passwordStateChanged

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        //Validacion de los datos
        if (txt_user.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Se debe ingresar un usuario");
            txt_user.requestFocus();
            return;
        }

        //Lectura de datos
        Operario dts = new Operario();
        dts.setId(id_op);
        dts.setUser(txt_user.getText());
        //Lectura de password
        if (chb_cambiar_password.isSelected()) {
            //Validación de la contraseña
            if (Arrays.equals(txt_actualPassword.getPassword(), password_op) && Arrays.equals(txt_newPassword1.getPassword(), txt_newPassword2.getPassword())) {
                dts.setPassword(txt_newPassword1.getPassword());
            } else {
                JOptionPane.showMessageDialog(this, "Las contraseñas no son iguales");
                txt_actualPassword.setText("");
                txt_newPassword1.setText("");
                txt_newPassword2.setText("");
                txt_actualPassword.requestFocus();
                return;
            }
        } else {
            dts.setPassword(password_op);
        }

        //Envio de datos
        ConsulOperario func = new ConsulOperario();
        if (func.editarPrivado(dts)) {
            JOptionPane.showMessageDialog(this, "Datos privados del operario editados satisfactoriamente");
            //Actualiza los datos si el usuario cambión sus propios datos
            if (dts.getId() == ConsulOperario.USUARIO.getId()) {
                new ConsulOperario().actualizarUsuario();
            }
            frm_operarios.Mostrar();
            frm_operarios.Inhabilitar();
            this.dispose();
        }
    }//GEN-LAST:event_btn_editarActionPerformed

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
            java.util.logging.Logger.getLogger(frm_userpassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_userpassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_userpassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_userpassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_userpassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton btn_editar;
    private javax.swing.JCheckBox chb_cambiar_password;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_actualPassword;
    private javax.swing.JLabel lbl_newPassword1;
    private javax.swing.JLabel lbl_newPassword2;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JLabel lbl_user;
    private javax.swing.JPasswordField txt_actualPassword;
    private javax.swing.JPasswordField txt_newPassword1;
    private javax.swing.JPasswordField txt_newPassword2;
    public javax.swing.JTextField txt_user;
    // End of variables declaration//GEN-END:variables
}
