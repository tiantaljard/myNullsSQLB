/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynullssqlB;

/**
 *
 * @author TianTaljard
 */
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;

public class MySQLDB {

    //static String dbName = "db1511762_db";
    static String dbName = "reqlocaldb";
    static String DB_URL = "jdbc:mysql://127.0.0.1:3306/" + dbName;

    public static void main(String args[]) {
        try { // attempt to establish connection to database
            MysqlDataSource db = new MysqlDataSource();
            db.setUrl(DB_URL);
            Connection connection = db.getConnection(
                    "root", "Zppsit0!");

            Statement tbl_statement = null;
            ResultSet tbl_resultset = null;
            Statement col_statement = null;
            ResultSet col_resultset = null;

//  GET TABLE DATA
            Statement tbl_query = connection.createStatement();
            ResultSet tbl_results = tbl_query.executeQuery("show tables");
            ResultSetMetaData tbl_meta = tbl_results.getMetaData();
            int tbl_numColumns = tbl_meta.getColumnCount();
            for (int i = 1; i <= tbl_numColumns; i++) {
                System.out.print("Table Header\t - " + tbl_meta.getColumnName(i));
            }
            System.out.println();
            while (tbl_results.next()) {
                for (int ti = 1; ti <= tbl_numColumns; ti++) {
                    System.out.print("Table Name\t - " + ti + " - " + tbl_results.getObject(ti));
                    System.out.println();
                    
                    Statement col_query = connection.createStatement();
                    ResultSet col_results = col_query.executeQuery("show columns from " + tbl_results.getObject(ti));
                    ResultSetMetaData col_meta = col_results.getMetaData();
                    
                    
                    int col_numColumns = col_meta.getColumnCount();
                    
                    System.out.print(col_numColumns);
                    
                    for (int ci = 1; ci <= col_numColumns; ci++) {
                   // System.out.print("Column Header\t - " + col_results.getObject(i));
            }

////  GET COLUMN DATA                   
//                  
//                    ResultSet col_results = col_query.executeQuery("show columns from " + tbl_results.getObject(i));
//                    ResultSetMetaData col_meta = col_results.getMetaData();
//                    int col_numColumns = col_meta.getColumnCount();
//                    
//                    for (int ci = 1; ci <= tbl_numColumns; ci++) {
//                        System.out.print("\t" + col_meta.getColumnName(ci));
//                    }
                }
            }

        } catch (SQLException e) { // code to run if connection fails
            System.out.println(
                    "Something went wrong with DB connection \n"
                    + "Error message is: " + e);
        }
    }
}
