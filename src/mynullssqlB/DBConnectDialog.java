/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynullssqlB;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author TianTaljard
 */
public class DBConnectDialog extends javax.swing.JDialog {

    private MySQLDBConnect db;

    public MySQLDBConnect getDb() {
        return db;
    }

    /**
     * Creates new form myNullsSQLJFrame
     */
    public DBConnectDialog() {
        initComponents();
        analyseJButton.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dbConnectJPanel = new javax.swing.JPanel();
        serverIPJLabel = new javax.swing.JLabel();
        serverIPJTextField = new javax.swing.JTextField();
        databaseNameJLabel = new javax.swing.JLabel();
        databaseNameJTextField = new javax.swing.JTextField();
        portJLabel = new javax.swing.JLabel();
        portJTextField = new javax.swing.JTextField();
        usernameJLabel = new javax.swing.JLabel();
        usernameJTextField = new javax.swing.JTextField();
        passwordJLabel = new javax.swing.JLabel();
        testDBConnectJButton = new javax.swing.JButton();
        analyseJButton = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Database Connection Parameters");
        setName("JFrame4"); // NOI18N

        dbConnectJPanel.setName("Database Connection Parameters"); // NOI18N
        dbConnectJPanel.setPreferredSize(new java.awt.Dimension(1214, 311));

        serverIPJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        serverIPJLabel.setText("Server IP:");
        serverIPJLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        serverIPJTextField.setText("127.0.0.1");
        serverIPJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverIPJTextFieldActionPerformed(evt);
            }
        });

        databaseNameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        databaseNameJLabel.setText("Database Name:");
        databaseNameJLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        databaseNameJLabel.setPreferredSize(new java.awt.Dimension(72, 16));

        databaseNameJTextField.setText("sakila");
        databaseNameJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                databaseNameJTextFieldActionPerformed(evt);
            }
        });

        portJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        portJLabel.setText("Port:");
        portJLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        portJLabel.setPreferredSize(new java.awt.Dimension(67, 16));

        portJTextField.setText("3306");
        portJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portJTextFieldActionPerformed(evt);
            }
        });

        usernameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        usernameJLabel.setText("Username:");
        usernameJLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        usernameJLabel.setPreferredSize(new java.awt.Dimension(102, 16));

        usernameJTextField.setText("root");

        passwordJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        passwordJLabel.setText("Password:");
        passwordJLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        testDBConnectJButton.setText("Test");
        testDBConnectJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testDBConnectJButtonActionPerformed(evt);
            }
        });

        analyseJButton.setText("Connect");
        analyseJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyseJButtonActionPerformed(evt);
            }
        });

        jPasswordField1.setText("Zppsit0!");
        jPasswordField1.setPreferredSize(new java.awt.Dimension(36, 26));

        javax.swing.GroupLayout dbConnectJPanelLayout = new javax.swing.GroupLayout(dbConnectJPanel);
        dbConnectJPanel.setLayout(dbConnectJPanelLayout);
        dbConnectJPanelLayout.setHorizontalGroup(
            dbConnectJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dbConnectJPanelLayout.createSequentialGroup()
                .addGroup(dbConnectJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dbConnectJPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dbConnectJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serverIPJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(databaseNameJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(portJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(passwordJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dbConnectJPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(testDBConnectJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dbConnectJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serverIPJTextField)
                    .addComponent(databaseNameJTextField)
                    .addComponent(portJTextField)
                    .addComponent(usernameJTextField)
                    .addGroup(dbConnectJPanelLayout.createSequentialGroup()
                        .addComponent(analyseJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(883, 883, 883))
        );

        dbConnectJPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {databaseNameJLabel, passwordJLabel, portJLabel, serverIPJLabel, usernameJLabel});

        dbConnectJPanelLayout.setVerticalGroup(
            dbConnectJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dbConnectJPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(dbConnectJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(serverIPJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serverIPJLabel))
                .addGap(6, 6, 6)
                .addGroup(dbConnectJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(dbConnectJPanelLayout.createSequentialGroup()
                        .addComponent(databaseNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(portJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(usernameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(passwordJLabel))
                    .addGroup(dbConnectJPanelLayout.createSequentialGroup()
                        .addComponent(databaseNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(portJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(usernameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dbConnectJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(analyseJButton)
                    .addComponent(testDBConnectJButton))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dbConnectJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dbConnectJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void testDBConnectJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testDBConnectJButtonActionPerformed
        //======================================================================
        // Obtain text from input fields
        //======================================================================
        String serverIP = serverIPJTextField.getText();
        String databaseName = databaseNameJTextField.getText();
        String port = portJTextField.getText();
        String username = usernameJTextField.getText();
        String password = String.valueOf(jPasswordField1.getPassword());

        //======================================================================
        // Validate text for null and blank values
        //======================================================================
        if (serverIP == null || serverIP.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Server IP Address can not be null");
        }
        if (databaseName == null || databaseName.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Database Name can not be null");
        }
        if (port == null || port.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Port can not be null");
        }
        if (username == null || username.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Username can not be null");
        }
        if (password == null || password.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Password can not be null");
        }
        //======================================================================
        // Connect to Database
        //======================================================================
        try { // endeavour to create connection to server and database
            db = new MySQLDBConnect(serverIP, databaseName, username, password, port);
            db.getConnection();

            Object[] options = {"OK"};
            Component frame;

//            int n = JOptionPane.showOptionDialog(null,
//                    "Server and Database Connection Successful",
//                    "Connection Success",
//                    JOptionPane.PLAIN_MESSAGE,
//                    JOptionPane.INFORMATION_MESSAGE,
//                    null,
//                    options,
//                    options[0]);

            analyseJButton.setEnabled(true);

        } catch (SQLException e) { // if server and database connection fail run this
            JOptionPane.showMessageDialog(null,
                    "The connection to the server and database was not Successful \n"
                    + "Connection Error: " + e);
        }
    }//GEN-LAST:event_testDBConnectJButtonActionPerformed

    private void portJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portJTextFieldActionPerformed

    private void databaseNameJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_databaseNameJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseNameJTextFieldActionPerformed

    private void serverIPJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverIPJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serverIPJTextFieldActionPerformed

    private void analyseJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyseJButtonActionPerformed
        //======================================================================
        // Obtain text from input fields
        //======================================================================
        String serverIP = serverIPJTextField.getText();
        String databaseName = databaseNameJTextField.getText();
        String port = portJTextField.getText();
        String username = usernameJTextField.getText();
        String password = String.valueOf(jPasswordField1.getPassword());

        //======================================================================
        // Validate text for null and blank values
        //======================================================================
        if (serverIP == null || serverIP.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Server IP Address can not be null");
        }
        if (databaseName == null || databaseName.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Database Name can not be null");
        }
        if (port == null || port.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Port can not be null");
        }
        if (username == null || username.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Username can not be null");
        }
        if (password == null || password.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Password can not be null");
        }
        //======================================================================
        // Connect to Database
        //======================================================================
        try { // endeavour to create connection to server and database
            db = new MySQLDBConnect(serverIP, databaseName, username, password, port);
            db.getConnection();

            Object[] options = {"OK"};
            Component frame;

            setVisible(false);

            ResultSet tbl_results = db.getTableNames();
            
        } catch (SQLException e) { // if server and database connection fail run this
            JOptionPane.showMessageDialog(null,
                    "The connection to the server and database was not Successful \n"
                    + " Please confirm the database connection parameters are correct \n"
            //             +"Connection Error: " + e);
            );

        }
    }//GEN-LAST:event_analyseJButtonActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DBConnectDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DBConnectDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DBConnectDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DBConnectDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DBConnectDialog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyseJButton;
    private javax.swing.JLabel databaseNameJLabel;
    private javax.swing.JTextField databaseNameJTextField;
    private javax.swing.JPanel dbConnectJPanel;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel passwordJLabel;
    private javax.swing.JLabel portJLabel;
    private javax.swing.JTextField portJTextField;
    private javax.swing.JLabel serverIPJLabel;
    private javax.swing.JTextField serverIPJTextField;
    private javax.swing.JButton testDBConnectJButton;
    private javax.swing.JLabel usernameJLabel;
    private javax.swing.JTextField usernameJTextField;
    // End of variables declaration//GEN-END:variables
}
