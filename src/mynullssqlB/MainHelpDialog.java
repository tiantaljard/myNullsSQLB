/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynullssqlB;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author TianTaljard
 */
public class MainHelpDialog extends javax.swing.JDialog {

    private String quitDirect = "";

    private static final String SPLASHTRACKER = "splashtracker";

    /**
     * Creates new form myNullsSQLJFrame
     */
    public MainHelpDialog() {
        quitDirect = "";
        initComponents();
        File f = new File(SPLASHTRACKER + File.separator + "lauchDPHasrun.txt");

        if (!f.exists()) {
            firstTimeWelcome();
        }
    }

    public MainHelpDialog(String screenName) throws IOException {
        quitDirect = "quitDirect";
        initComponents();

        buildPictureImage(screenName);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        launchHelpPanel = new javax.swing.JPanel();
        buttonsjPanel2 = new javax.swing.JPanel();
        eastjPanel3 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(30, 32767));
        centerjPanel4 = new javax.swing.JPanel();
        okJButton1 = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        westjPanel5 = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(30, 32767));
        helpImageContainer = new javax.swing.JPanel();
        pictureLabel = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(new java.awt.Rectangle(300, 100, 0, 0));
        setName("JFrame4"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                onWindowClose(evt);
            }
        });

        launchHelpPanel.setPreferredSize(new java.awt.Dimension(720, 480));
        launchHelpPanel.setLayout(new java.awt.BorderLayout());

        buttonsjPanel2.setPreferredSize(new java.awt.Dimension(680, 35));
        buttonsjPanel2.setLayout(new java.awt.BorderLayout());

        eastjPanel3.setLayout(new java.awt.BorderLayout());
        eastjPanel3.add(filler1, java.awt.BorderLayout.CENTER);

        buttonsjPanel2.add(eastjPanel3, java.awt.BorderLayout.EAST);

        centerjPanel4.setLayout(new java.awt.BorderLayout());

        okJButton1.setText("Continue...");
        okJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okJButton1ActionPerformed(evt);
            }
        });
        centerjPanel4.add(okJButton1, java.awt.BorderLayout.CENTER);
        centerjPanel4.add(filler3, java.awt.BorderLayout.SOUTH);

        buttonsjPanel2.add(centerjPanel4, java.awt.BorderLayout.CENTER);

        westjPanel5.setLayout(new java.awt.BorderLayout());
        westjPanel5.add(filler2, java.awt.BorderLayout.CENTER);

        buttonsjPanel2.add(westjPanel5, java.awt.BorderLayout.WEST);

        launchHelpPanel.add(buttonsjPanel2, java.awt.BorderLayout.PAGE_END);

        helpImageContainer.setPreferredSize(new java.awt.Dimension(680, 440));
        helpImageContainer.setLayout(new java.awt.BorderLayout());
        helpImageContainer.add(pictureLabel, java.awt.BorderLayout.CENTER);
        helpImageContainer.add(filler4, java.awt.BorderLayout.SOUTH);
        helpImageContainer.add(filler5, java.awt.BorderLayout.EAST);
        helpImageContainer.add(filler6, java.awt.BorderLayout.WEST);
        helpImageContainer.add(filler7, java.awt.BorderLayout.NORTH);

        launchHelpPanel.add(helpImageContainer, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(launchHelpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(launchHelpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okJButton1ActionPerformed

        File f = new File(SPLASHTRACKER + File.separator + "lauchDPHasrun.txt");
        if (quitDirect.equals("quitDirect")) {
            this.setVisible(false);
            return;
        } else if (f.exists()) {
            this.setVisible(false);
            return;
        } else {
            File f2 = new File(SPLASHTRACKER + File.separator + "WelcomeHasrun.txt");

            if (!f2.exists()) {
                try {
                    buildPictureImage("DP1.png");

                    f2.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(MainHelpDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                try {
                    this.setVisible(false);
                    new MySQLNullsApp().setVisible(true);
                    f.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(MainHelpDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_okJButton1ActionPerformed

    private void onWindowClose(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onWindowClose
        this.setVisible(false);
        File f = new File(SPLASHTRACKER + File.separator + "lauchDPHasrun.txt");
        if (f.exists()) {
            return;
        } else {
            File ff = new File(SPLASHTRACKER + File.separator + "clearedWhileRunning.txt");
            if (ff.exists()) {
                return;
            } else {

                try {
                    new MySQLNullsApp().setVisible(true);
                    f.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(MainHelpDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_onWindowClose
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
            java.util.logging.Logger.getLogger(MainHelpDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainHelpDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainHelpDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainHelpDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainHelpDialog().setVisible(true);
            }
        });
    }

    private void buildPictureImage(String imagename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("helpcontent" + File.separator + imagename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg = img.getScaledInstance(pictureLabel.getWidth(), pictureLabel.getHeight(),
                Image.SCALE_SMOOTH);

        ImageIcon imageIcon = new ImageIcon(dimg);
        pictureLabel.removeAll();
        pictureLabel.setIcon(imageIcon);
        helpImageContainer.add(pictureLabel);
        helpImageContainer.setVisible(true);
    }

    /**
     * buildBusyBar(String title) generates a progress bar to display during
 long running transactions.
     *
     * @param title
     * @return
     */
    public JDialog buildProgressBar(String title) {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JDialog progressBarDialog = new JDialog(this, title, false);
        progressBarDialog.add(BorderLayout.CENTER, progressBar);
        progressBarDialog.setSize(300, 75);
        progressBarDialog.setLocationRelativeTo(MainHelpDialog.this.helpImageContainer);
        progressBarDialog.setLocation(500, 300);
        progressBarDialog.setVisible(true);
        return progressBarDialog;
    }

    public void firstTimeWelcome() {

        JDialog progressBarDialog = buildProgressBar("Preparing to Show Welcome");
        launchHelpPanel.setVisible(true);
        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                buildPictureImage("WelcomeToMyNullsSQL.png");
                return null;
            }

            @Override
            protected void done() {
                progressBarDialog.setVisible(false);
                launchHelpPanel.setVisible(true);

            }
        };
        worker.execute();

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsjPanel2;
    private javax.swing.JPanel centerjPanel4;
    private javax.swing.JPanel eastjPanel3;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JPanel helpImageContainer;
    private javax.swing.JPanel launchHelpPanel;
    private javax.swing.JButton okJButton1;
    private javax.swing.JLabel pictureLabel;
    private javax.swing.JPanel westjPanel5;
    // End of variables declaration//GEN-END:variables
}
