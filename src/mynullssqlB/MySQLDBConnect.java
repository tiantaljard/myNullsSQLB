/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynullssqlB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author TianTaljard
 */
public class MySQLDBConnect {

    Connection conn;
    String username;
    String password;
    String databaseName;
    String port;
    String serverIP;
    private int numberOfTable;
    /**
     * for caching table names column count and row count
     */
    Map<String, Integer> tblCache = Collections.synchronizedMap(new HashMap<>());

    private static final String COLCOUNT = "_colcount";
    private static final String ROWCOUNT = "_rowcount";

    /**
     * MAIN METHOD - ONLY USED FOR TESTING IN THIS CLASS
     *
     * @param args
     */
    public static void main(String[] args) {
        //======================================================================
        // MAIN FOR TESTING
        //======================================================================

        MySQLDBConnect db = new MySQLDBConnect("127.0.0.1", "reqlocaldb", "root", "Zppsit0!", "3306");
        try {
            db.getConnection();
            ArrayList<String[]> transposed = db.transPoseNb("requests");
//            System.out.println("Before  transposed");
//            System.out.print(transposed.toString());
            //           System.out.println("\n After  transposed");
            for (int i = 0; i < transposed.size(); i++) {
                String[] col = transposed.get(i);

                System.out.print(col[0]);
                System.out.print(col[1]);
                System.out.println(col[2]);
                System.out.println("\n");

            }

            db.initialAnalyseTables();

            System.out.println(" Row count " + db.getRowCount("requests"));
            System.out.println("Col Count " + db.getColCount("requests"));
//            ResultSet coldetail = db.secondAnalyseTablesNulls("requests");
//            while (coldetail.next()) {
//                System.out.println(coldetail.getString(1));
//            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //======================================================================
    // AFTER MAIN 
    //======================================================================

    /**
     * Default Constructor
     */
    public MySQLDBConnect() {
    }

    /**
     *
     * @param serverIP
     * @param databaseName
     * @param username
     * @param password
     * @param port
     */
    public MySQLDBConnect(String serverIP, String databaseName,
            String username, String password, String port) {
        this.serverIP = serverIP;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        this.port = port;

    }

    /**
     * Establish Connection to the database
     *
     * @throws SQLException
     */
    public void getConnection() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://" + serverIP + ":"
                + port + "/" + databaseName, username, password);
    }

    /**
     * creates a list of table that exist in the database
     *
     * @return a list of tables in the database schema
     * @throws SQLException
     */
    public ResultSet showTables() throws SQLException {
        Statement statement = conn.createStatement();

        ResultSet tbl_results = statement.executeQuery("select  count(*) from information_schema.tables where table_schema='" + databaseName + "'");
        tbl_results.first();
        numberOfTable = tbl_results.getInt(1);

        tbl_results = statement.executeQuery("select  table_name as \"Tables\" from information_schema.tables where table_schema='" + databaseName + "'");

        return tbl_results;
    }

    /**
     * scans the database and return a result set for show each table shows the
     * table name, column count and row count
     *
     * creates a hash table to store the column count and row count for each
     * table
     *
     * @return table name,column count, row count
     * @throws SQLException
     */
    public ResultSet initialAnalyseTables() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet iat_results = statement.executeQuery("select c.table_name,COUNT(c.COLUMN_NAME) column_cnt,\n"
                + "(select table_rows from  information_schema.tables \n"
                + "where c.table_schema=table_schema and c.table_name= table_name)  rowcount\n"
                + "  from information_schema.columns c \n"
                + "  where   c.table_schema='" + databaseName + "' \n"
                + "  group by c.table_schema,c.table_name;");

        while (iat_results.next()) {

            tblCache.put(iat_results.getString(1) + COLCOUNT, Integer.parseInt(iat_results.getString("column_cnt")));
            tblCache.put(iat_results.getString(1) + ROWCOUNT, Integer.parseInt(iat_results.getString("rowcount")));
        }
        iat_results.first();

        return iat_results;
    }

    /**
     * Retrieving columns names from a given table.
     *
     * @param table_name
     * @return
     * @throws SQLException
     */
    public ResultSet getColumnNames(String table_name) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet getColNames = statement.executeQuery("show columns from " + table_name);

        return getColNames;
    }

    /**
     *
     * @param table_name
     * @return
     * @throws SQLException
     */
    public ResultSet secondAnalyseTablesNulls(String table_name) throws SQLException {
        //======================================================================
        // Dynamically building nulls and blanks counts from getColumnNames
        //======================================================================
        Statement statement = conn.createStatement();
        ResultSet colCount = statement.executeQuery(" select count(*) from information_schema.columns where table_name = '" + table_name + "';");
        colCount.first();
        int j = colCount.getInt(1);

        String query = "select ";

        ResultSet col_results = getColumnNames(table_name);
        col_results.first();

        for (int i = 0; i < j; i++) {
            query += "SUM(CASE WHEN " + col_results.getString(1)
                    + " IS NULL THEN 1 ELSE 0 END) as nulls_"
                    + col_results.getString(1);
            query += ", SUM(CASE WHEN " + col_results.getString(1)
                    + " ='' THEN 1 ELSE 0 END) as blanks_"
                    + col_results.getString(1);
            if (i == j - 1) {
                query += " ";

            } else {
                query += ", ";
            }
            col_results.next();

        }

        query += "from " + table_name + ";";

        //======================================================================
        // Excecuting dynamically built queries to get counts for nulls and blanks from tables
        //======================================================================
        ResultSet nbCnt = statement.executeQuery(query);
        return nbCnt;

    }

    /**
     * get the number of columns for a table from a hash map table
     *
     * @param table_name
     * @return count of columns in table
     * @throws SQLException
     */
    public int getColCount(String table_name) throws SQLException {
        return tblCache.get(table_name + COLCOUNT);
    }

    /**
     * get the number of rows for a table from a hash map table
     *
     * @param table_name
     * @return count of rows in table
     * @throws SQLException
     */
    public int getRowCount(String table_name) throws SQLException {
        return tblCache.get(table_name + ROWCOUNT);
    }

    /**
     * method to transpose count of null and blank values in each column of a
     * table.
     *
     * @param table_name
     * @return
     * @throws SQLException
     */
    public ArrayList<String[]> transPoseNb(String table_name) throws SQLException {

        ArrayList<String[]> nbc = new ArrayList<>();

        ResultSet nBCnt = secondAnalyseTablesNulls(table_name);

        ResultSetMetaData col_meta = nBCnt.getMetaData();

        int col_numColumns = col_meta.getColumnCount();

        String coll0 = null;
        String coll1 = null;
        String coll2 = null;

        while (nBCnt.next()) {

            System.out.print(col_numColumns);

            for (int ci = 1; ci <= col_numColumns; ci += 2) {
                String colname = col_meta.getColumnName(ci);

                coll0 = colname.replace("nulls_", "");
                coll1 = nBCnt.getString(ci);
                coll2 = nBCnt.getString(ci + 1);

                String[] col = new String[]{coll0, coll1, coll2};
                System.out.println("print col " + col[0]);

                nbc.add(col);
            }
        }
        return nbc;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public DefaultTreeModel populateTreeModel() throws SQLException {
        Statement statement = null;

        try {
            statement = conn.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ArrayList list = new ArrayList();
        list.add(databaseName);
        String sql = "select table_name  from information_schema.tables where table_schema='" + databaseName + "'";

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            Object value[] = {rs.getString(1)};
            list.add(value);
        }
        Object hierarchy[] = list.toArray();
        DefaultMutableTreeNode root = processHierarchy(hierarchy);

        DefaultTreeModel treeModel = new DefaultTreeModel(root);

        return treeModel;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public DefaultMutableTreeNode processHierarchy(Object[] hierarchy) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(hierarchy[0]);
        Statement statement = null;
        String dbTable_name = null;

        try {
            int ctrow = 0;
            int i = 0;
            try {

                try {

                    statement = conn.createStatement();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                String sql = "select table_name  from information_schema.tables where table_schema='" + databaseName + "'";
                ResultSet rs = statement.executeQuery(sql);

                while (rs.next()) {
                    ctrow = rs.getRow();
                }
                String[] L1TableName = new String[ctrow];

                ResultSet rs1 = statement.executeQuery(sql);
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

                    ResultSet rs3 = statement.executeQuery(sql2);
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

    public int getNumberOfTable() {
        return numberOfTable;
    }
    
        public  TableModel resultSetToTableModel(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Vector columnNames = new Vector();

            // Get the column names
            for (int column = 0; column < numberOfColumns; column++) {
                columnNames.addElement(metaData.getColumnLabel(column + 1));
            }

            // Get all rows.
            Vector rows = new Vector();

            while (rs.next()) {
                Vector newRow = new Vector();

                for (int i = 1; i <= numberOfColumns; i++) {
                    newRow.addElement(rs.getObject(i));
                }

                rows.addElement(newRow);
            }

            return new DefaultTableModel(rows, columnNames);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
    

}
