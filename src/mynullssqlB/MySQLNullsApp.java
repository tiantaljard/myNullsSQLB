/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynullssqlB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author TianTaljard
 */
public class MySQLNullsApp extends javax.swing.JFrame {

    private JTextField jtfSearch;
    private MySQLDBConnect db;

    /**
     * Creates new form MySQLNullsApp
     */
    public MySQLNullsApp() {

        /*
            ResultTableModel tableModel = new ResultTableModel();
            System.out.println(db.getNumberOfTable());
            
            tableModel.setResultset(tbls);
            
            tableModel.setsqlRowCount(db.getNumberOfTable());
         */
        initComponents();
        showConnectionDialog();
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableNameTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        columnNameTable = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable3);

        jSplitPane3.setRightComponent(jScrollPane4);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(100, 404));

        tableNameTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        tableNameTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNameTableMouseClicked(evt);
            }
        });
        tableNameTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableNameTableKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tableNameTable);

        jSplitPane3.setLeftComponent(jScrollPane3);

        jSplitPane1.setRightComponent(jSplitPane3);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(100, 404));

        columnNameTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Columns"
            }
        ));
        jScrollPane2.setViewportView(columnNameTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 77;
        gridBagConstraints.ipady = 501;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel1.add(jScrollPane2, gridBagConstraints);

        jTextField1.setText("jTextField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(500, 14, 0, 0);
        jPanel1.add(jTextField1, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel1);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 3707, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableNameTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNameTableMouseClicked
        setcolumnNameTable();
    }//GEN-LAST:event_tableNameTableMouseClicked

    private void tableNameTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableNameTableKeyReleased
        setcolumnNameTable();
    }//GEN-LAST:event_tableNameTableKeyReleased

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MySQLNullsApp().setVisible(true);
            }
        });
    }

    public class ResultTableModel extends DefaultTableModel {

        private ResultSet resultset;
        private int sqlRowCount;

        @Override
        public int getColumnCount() {
            try {
                return resultset.getMetaData().getColumnCount();
                //return super.getColumnCount(); //To change body of generated methods, choose Tools | Templates.
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return -1;
        }

        @Override
        public int getRowCount() {
            return sqlRowCount;
        }

        @Override
        public String getColumnName(int column) {
            return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object getValueAt(int row, int column) {
            try {
                resultset.absolute(row); //To change body of generated methods, choose Tools | Templates.
                return resultset.getString(column);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "";
        }

        public ResultSet getResultset() {
            return resultset;
        }

        public void setResultset(ResultSet resultset) {
            this.resultset = resultset;
        }

        public void setsqlRowCount(int rows) {
            sqlRowCount = rows;
        }

    }

    public ArrayList<String> rowsColOneSelected() {
        ArrayList<String> rowsColOneSelected = new ArrayList<String>();
        int[] rows = tableNameTable.getSelectedRows();

        for (int i = 0; i < rows.length; i++) {
            rowsColOneSelected.add(tableNameTable.getValueAt(rows[i], 0).toString());
        }
        return rowsColOneSelected;
    }

    public String rowColOneSelected() {
        int[] rows = tableNameTable.getSelectedRows();
        String rowColOneSelected = null;

        int row = tableNameTable.getSelectedRow();
        rowColOneSelected = (tableNameTable.getValueAt(rows[0], 0).toString());

        return rowColOneSelected;
    }

    public void setcolumnNameTable() {
        ResultSet columns = null;
        try {
            columns = db.getColumnNames(rowColOneSelected());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        columnNameTable.setModel(db.resultSetToTableModel(columns));
        setTableRowSorter(columnNameTable);
    }

    public void setTableRowSorter(JTable table) {
        TableRowSorter<TableModel> sorter
                = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

    }

    public void showConnectionDialog() {

        try {
            DBConnectDialog dbconnectdialog = new DBConnectDialog();
            dbconnectdialog.setModal(true);
            dbconnectdialog.setVisible(true);
            db = dbconnectdialog.getDb();

            /*
            @todo makes this threadsafe
             */
            ResultSet tbls = db.showTables();
            ArrayList<String[]> l = db.transPoseNb("requests");

            tableNameTable.setModel(db.resultSetToTableModel(tbls));
            setTableRowSorter(tableNameTable);
            

        } catch (SQLException e) {
            e.printStackTrace();
        };

    }

    /*
    private void newFilter(JTable table) {
        
        TableModel tablemodel = table.getModel();
        
        RowFilter< MyTableModel  , Object> rf = null; 
    //declare a row filter for your table model
    Try
    {
        rf = RowFilter.regexFilter("^" + jtfSearch.getText(), 0);  
        //initialize with a regular expression
    }
    catch (java.util.regex.PatternSyntaxException e)
    {
        return;
    }
    sorter.setRowFilter(rf);
    }
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable columnNameTable;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableNameTable;
    // End of variables declaration//GEN-END:variables
}
