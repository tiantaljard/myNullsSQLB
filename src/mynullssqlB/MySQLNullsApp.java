/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynullssqlB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.RowFilter;

/**
 *
 * @author TianTaljard
 */
public class MySQLNullsApp extends javax.swing.JFrame {

    private MySQLDBConnect db;
    private String dynamic_query;

    /**
     * Creates new form MySQLNullsApp
     */
    public MySQLNullsApp() {

        try {
            /*
            ResultTableModel tableModel = new ResultTableModel();
            System.out.println(db.getNumberOfTable());
            
            tableModel.setResultset(tbls);
            
            tableModel.setsqlRowCount(db.getNumberOfTable());
             */
            initComponents();
            showConnectionDialog();
            initializeModel();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
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

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        colNmParentPanel = new javax.swing.JPanel();
        colNmFilterPanel = new javax.swing.JPanel();
        columnNameFilter = new javax.swing.JTextField();
        colNmScrollPanel = new javax.swing.JScrollPane();
        columnNameTable = new javax.swing.JTable();
        tblNmParentPanel = new javax.swing.JPanel();
        tblNmFilterPanel = new javax.swing.JPanel();
        tableNameFilter = new javax.swing.JTextField();
        tblNmScrollPanel = new javax.swing.JScrollPane();
        tableNameTable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setPreferredSize(new java.awt.Dimension(800, 536));

        jSplitPane3.setPreferredSize(new java.awt.Dimension(450, 532));

        jScrollPane4.setPreferredSize(new java.awt.Dimension(300, 404));

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(dataTable);

        jSplitPane3.setRightComponent(jScrollPane4);

        colNmParentPanel.setLayout(new java.awt.BorderLayout());

        colNmFilterPanel.setLayout(new java.awt.BorderLayout());

        columnNameFilter.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        columnNameFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                columnNameFilterKeyReleased(evt);
            }
        });
        colNmFilterPanel.add(columnNameFilter, java.awt.BorderLayout.CENTER);

        colNmParentPanel.add(colNmFilterPanel, java.awt.BorderLayout.NORTH);

        colNmScrollPanel.setPreferredSize(new java.awt.Dimension(100, 500));
        colNmScrollPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                colNmScrollPanelMouseReleased(evt);
            }
        });

        columnNameTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Columns"
            }
        ));
        columnNameTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                columnNameTableMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                columnNameTableMouseClicked(evt);
            }
        });
        columnNameTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                columnNameTablePropertyChange(evt);
            }
        });
        columnNameTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                columnNameTableKeyReleased(evt);
            }
        });
        colNmScrollPanel.setViewportView(columnNameTable);

        colNmParentPanel.add(colNmScrollPanel, java.awt.BorderLayout.CENTER);

        jSplitPane3.setLeftComponent(colNmParentPanel);

        jSplitPane1.setRightComponent(jSplitPane3);

        tblNmParentPanel.setLayout(new java.awt.BorderLayout());

        tblNmFilterPanel.setLayout(new java.awt.BorderLayout());

        tableNameFilter.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tableNameFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableNameFilterKeyReleased(evt);
            }
        });
        tblNmFilterPanel.add(tableNameFilter, java.awt.BorderLayout.CENTER);

        tblNmParentPanel.add(tblNmFilterPanel, java.awt.BorderLayout.NORTH);

        tblNmScrollPanel.setPreferredSize(new java.awt.Dimension(100, 500));

        tableNameTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Tables"
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
        tblNmScrollPanel.setViewportView(tableNameTable);

        tblNmParentPanel.add(tblNmScrollPanel, java.awt.BorderLayout.CENTER);
        tblNmScrollPanel.getAccessibleContext().setAccessibleParent(colNmParentPanel);

        jSplitPane1.setLeftComponent(tblNmParentPanel);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableNameTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNameTableMouseClicked
        setColumnNameTable();
        setJTableColOneFilter(columnNameTable, columnNameFilter);

    }//GEN-LAST:event_tableNameTableMouseClicked

    private void tableNameTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableNameTableKeyReleased
        setColumnNameTable();
        setJTableColOneFilter(columnNameTable, columnNameFilter);


    }//GEN-LAST:event_tableNameTableKeyReleased

    private void columnNameTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_columnNameTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_columnNameTableMouseClicked

    private void columnNameTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_columnNameTableKeyReleased
        try {
            setDataTable(getColumnData());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        //setColumnDataQuery();
    }//GEN-LAST:event_columnNameTableKeyReleased

    private void tableNameFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableNameFilterKeyReleased
        setJTableColOneFilter(tableNameTable, tableNameFilter);
        setColumnNameTable();
        setJTableColOneFilter(columnNameTable, columnNameFilter);
        
    }//GEN-LAST:event_tableNameFilterKeyReleased

    private void columnNameFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_columnNameFilterKeyReleased
        setJTableColOneFilter(columnNameTable, columnNameFilter);
    }//GEN-LAST:event_columnNameFilterKeyReleased

    private void columnNameTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_columnNameTablePropertyChange

    }//GEN-LAST:event_columnNameTablePropertyChange

    private void colNmScrollPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colNmScrollPanelMouseReleased

    }//GEN-LAST:event_colNmScrollPanelMouseReleased

    private void columnNameTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_columnNameTableMouseReleased
        try {
            setDataTable(getColumnData());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_columnNameTableMouseReleased

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

    /**
     * Method the get the currently selected rows from a JTable
     *
     * @return
     */
    public ArrayList<String> rowsColOneSelected(JTable jTable) {
        ArrayList<String> rowsColOneSelected = new ArrayList<String>();
        try {
            int[] rows = jTable.getSelectedRows();
            for (int i = 0; i < rows.length; i++) {
                rowsColOneSelected.add(jTable.getValueAt(rows[i], 0).toString());
            }
        } catch (Exception e) {
            rowsColOneSelected = null;
        }
        return rowsColOneSelected;
    }

    public String rowColOneSelected(JTable jTable) {
        int[] rows = jTable.getSelectedRows();
        String rowColOneSelected = null;

        try {
            int row = jTable.getSelectedRow();
            rowColOneSelected = (jTable.getValueAt(rows[0], 0).toString());
        } catch (Exception e) {
            rowColOneSelected = null;
        }

        return rowColOneSelected;

    }

    public ResultSet getColumnData() throws SQLException {
        String query = setColumnDataQuery();

        Statement statement = db.conn.createStatement();

        ResultSet getColData = statement.executeQuery(query);

        return getColData;

    }

    public void setColumnNameTable() {
        ResultSet columns = null;
        try {
            columns = db.getColumnNames(rowColOneSelected(tableNameTable));
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        columnNameTable.setModel(db.resultSetToTableModel(columns));
        setTableRowSorter(columnNameTable);
    }

    public void setDataTable(ResultSet data) {

        dataTable.setModel(db.resultSetToTableModel(data));
        setTableRowSorter(dataTable);
    }

    public TableRowSorter<TableModel> setTableRowSorter(JTable table) {
        TableRowSorter<TableModel> sorter
                = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        return sorter;

    }

    public void setJTableColOneFilter(JTable table_name, JTextField textFilter) {
        TableRowSorter<TableModel> sorter = setTableRowSorter(table_name);

        String textEntered = textFilter.getText();
        if (textEntered.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(textEntered));
        }

    }

    /**
     * Shows the dialog box to enter database server parameters
     *
     */
    public void showConnectionDialog() {

        DBConnectDialog dbconnectdialog = new DBConnectDialog();
        dbconnectdialog.setModal(true);
        dbconnectdialog.setVisible(true);
        db = dbconnectdialog.getDb();

    }

    public void initializeModel() throws SQLException {
        /*
            @todo makes this threadsafe
         */

        ResultSet tbls = db.showTables();
        tableNameTable.setModel(db.resultSetToTableModel(tbls));
        TableRowSorter<TableModel> tableNameTableSorter = setTableRowSorter(tableNameTable);
    }

    /**
     * @todo complete the funtion: still to make the model.
     * @param table_name
     * @throws SQLException
     */
    public void buildTransposeTable(String table_name) throws SQLException {

        ArrayList<String[]> l = db.transPoseNb(table_name);

    }

    public void buildColumnDataSQLWhereBlanks(ArrayList<String> columns_selected) {

        String sqlWhere = " and ";

        for (int i = 0; i < columns_selected.size(); i++) {

            sqlWhere += columns_selected.get(i).toString() + " ='' ";
            if (i == columns_selected.size() - 1) {
                sqlWhere += "";
            } else {
                sqlWhere += " and ";
            }
        };
        dynamic_query += sqlWhere;

    }

    public void buildColumnDataSQLWhereNulls(ArrayList<String> columns_selected) {

        String sqlWhere = " and ";

        for (int i = 0; i < columns_selected.size(); i++) {

            sqlWhere += columns_selected.get(i).toString() + " is null ";
            if (i == columns_selected.size() - 1) {
                sqlWhere += "";
            } else {
                sqlWhere += " and ";
            }
        };
        dynamic_query += sqlWhere;

    }

    public void buildColumnDataSQLEnd() {
        dynamic_query += ";";

    }
    
        public String setColumnDataQuery() {
        ArrayList<String> columns_selected;

        ResultSet columns = null;

        String table_name = (rowColOneSelected(tableNameTable));
        columns_selected = rowsColOneSelected(columnNameTable);

        String query = "select ";

        for (int i = 0; i < columns_selected.size(); i++) {
            query += columns_selected.get(i).toString();
            if (i == columns_selected.size() - 1) {
                query += "";
            } else {
                query += ",";
            }
        }

        dynamic_query = query;

        query += " from " + table_name + ";";

        dynamic_query += " from " + table_name + " where 1=1 ";

        return query;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel colNmFilterPanel;
    private javax.swing.JPanel colNmParentPanel;
    private javax.swing.JScrollPane colNmScrollPanel;
    private javax.swing.JTextField columnNameFilter;
    private javax.swing.JTable columnNameTable;
    private javax.swing.JTable dataTable;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTextField tableNameFilter;
    private javax.swing.JTable tableNameTable;
    private javax.swing.JPanel tblNmFilterPanel;
    private javax.swing.JPanel tblNmParentPanel;
    private javax.swing.JScrollPane tblNmScrollPanel;
    // End of variables declaration//GEN-END:variables
}
