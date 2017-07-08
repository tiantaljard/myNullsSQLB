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

    public MySQLDBConnect() {

    }

    public MySQLDBConnect(String serverIP, String databaseName,
            String username, String password, String port) {
        this.serverIP = serverIP;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public static void main(String[] args) {
        
        MySQLDBConnect db = new MySQLDBConnect("127.0.0.1", "reqlocaldb", "root", "Zppsit0!", "3306");
        try {
            db.getConnection();
            ArrayList<String[]>  transposed = db.transPoseNb("requests");
            System.out.println("");
            for ( int i=0 ; i<transposed.size(); i++) {
                String[] col = transposed.get(i);
                
                System.out.print(col);
                System.out.print(col[1]);
                System.out.println(col[2]);
                
            }
//            ResultSet coldetail = db.secondAnalyseTablesNulls("requests");
//            while (coldetail.next()) {
//                System.out.println(coldetail.getString(1));
//            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getConnection() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://" + serverIP + ":"
                + port + "/" + databaseName, username, password);
    }

    public ResultSet showTables() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet tbl_results = statement.executeQuery("show tables");
        return tbl_results;
    }

    public ResultSet initialAnalyseTables() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet iat_results = statement.executeQuery("select c.table_name,COUNT(c.COLUMN_NAME) column_cnt,\n"
                + "(select table_rows from  information_schema.tables \n"
                + "where c.table_schema=table_schema and c.table_name= table_name)  rowcount\n"
                + "  from information_schema.columns c \n"
                + "  where   c.table_schema='" + databaseName + "' \n"
                + "  group by c.table_schema,c.table_name;");
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
     * @todo This method is not implemented
     * @param table_name
     * @return
     */
    public int getRowcount(String table_name) {
        return 343;
        /* 
        * For implementation we could call the initialAnalyzeTable 
        * in the constructor of this method
        
        * and createa HashMap that stores pairs of table nae and row count
         */
    }

    public ArrayList<String[]> transPoseNb(String table_name) throws SQLException {
        ArrayList<String[]> nbc = new ArrayList<>();

        ResultSet nBCnt = secondAnalyseTablesNulls(table_name);

        while (nBCnt.next()) {
            String[] col = new String[3];
            ResultSetMetaData col_meta = nBCnt.getMetaData();
            int col_numColumns = col_meta.getColumnCount();
            System.out.print(col_numColumns);
            for (int ci = 1; ci <= col_numColumns; ci += 2) {
                String colname = col_meta.getColumnName(ci);
                
                col[0]=colname.replace("nulls_", "");
                col[1]=nBCnt.getString(ci);
                col[2]=nBCnt.getString(ci+1);
                
                System.out.print("THIS PLANCE "+col[0]);
                
               nbc.add(col);
                
                // System.out.print("Column Header\t - " + col_results.getObject(i));
            }

        }

        return nbc;

    }

}
