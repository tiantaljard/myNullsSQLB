/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynullssqlB;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import mynullssqlB.Database;

public class DisplayCategory extends javax.swing.JFrame {

    Connection con = null;
    Statement stm = null;
    String databaseName = "reqlocaldb";
    String dbTable_name = null;

    public DisplayCategory() {
        initComponents();
        pop_tree();//call to populate the JTree
    }

    //System generated code
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cat_tree = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Product List");

        jScrollPane1.setViewportView(cat_tree);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    @SuppressWarnings("CallToThreadDumpStack")
    public final void pop_tree() {
        try {

            try {
                con = Database.getConnection();
                stm = con.createStatement();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ArrayList list = new ArrayList();
            list.add(databaseName);
            String sql = "select table_name  from information_schema.tables where table_schema='" + databaseName + "'";

            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Object value[] = {rs.getString(1)};
                list.add(value);
            }
            Object hierarchy[] = list.toArray();
            DefaultMutableTreeNode root = processHierarchy(hierarchy);

            DefaultTreeModel treeModel = new DefaultTreeModel(root);
            cat_tree.setModel(treeModel);
        } catch (Exception e) {
        }

    }

    @SuppressWarnings("CallToThreadDumpStack")
    public DefaultMutableTreeNode processHierarchy(Object[] hierarchy) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(hierarchy[0]);
        try {
            int ctrow = 0;
            int i = 0;
            try {

                try {
                    con = Database.getConnection();
                    stm = con.createStatement();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                String sql = "select table_name  from information_schema.tables where table_schema='" + databaseName + "'";
                ResultSet rs = stm.executeQuery(sql);

                while (rs.next()) {
                    ctrow = rs.getRow();
                }
                String[] L1TableName = new String[ctrow];

                ResultSet rs1 = stm.executeQuery(sql);
                while (rs1.next()) {
                    L1TableName[i] = rs1.getString("table_name");
                    dbTable_name = (rs1.getString("table_name"));
                    i++;
                }
                DefaultMutableTreeNode child, grandchild;
                for (int childIndex = 0; childIndex < L1TableName.length; childIndex++) {
                    child = new DefaultMutableTreeNode(L1TableName[childIndex]);
                    node.add(child);//add each created child to root
                    //System.out.println("tablenamePrintedHere");
                    System.out.println(dbTable_name);
                    String sql2 = "select column_name  from information_schema.columns where table_name='" + dbTable_name + "' and table_schema='" + databaseName + "'";

                    ResultSet rs3 = stm.executeQuery(sql2);
                    while (rs3.next()) {
                        System.out.println("something");

                        System.out.println(rs3.getString(1));
                        grandchild = new DefaultMutableTreeNode(rs3.getString("column_name"));
                        child.add(grandchild);//add each grandchild to each child
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception e) {
        }

        return (node);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DisplayCategory().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify
    private javax.swing.JTree cat_tree;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}
