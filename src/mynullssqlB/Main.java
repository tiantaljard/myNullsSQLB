/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynullssqlB;

import java.io.File;

/**
 *
 * @author TianTaljard
 */
public class Main {
    MainHelpDialog help = new MainHelpDialog();
    MySQLNullsApp msql = new MySQLNullsApp();
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
                java.util.logging.Logger.getLogger(MySQLNullsApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(MySQLNullsApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(MySQLNullsApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(MySQLNullsApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            
            /* Create and display the form */
            Main m = new Main();
            
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    
                    //https://www.mkyong.com/java/how-to-create-directory-in-java/
                    File file = new File(MySQLNullsApp.SPLASHTRACKER);
                    if (!file.exists()) {
                        if (file.mkdir()) {
                            System.out.println("Directory is created!");
                        } else {
                            System.out.println("Failed to create directory!");
                        }
                    }
                    
                    File f = new File(MySQLNullsApp.SPLASHTRACKER + File.separator + "lauchDPHasrun.txt");
                    if (f.exists()) {
                        m.msql.setVisible(true);
                    } else {
                        m.help.setVisible(true);
                    }
                }
            });
      
        //</editor-fold>
    }
    
}
