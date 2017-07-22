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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @Date July 2017
 * @author TianTaljard
 * The purpose of the class is to provide a connection to a database and
 * retrieve data from a database using SQL. This class supports MySQLNullsApp
 */
public class MySQLDBConnect {

    Connection conn;
    String username;
    String password;
    String databaseName;
    String port;
    String serverIP;

    private MySQLNullsApp mySQLNullsApp;

    private int numberOfTable;
    private int totalNumberOfTable;
    /**
     * for caching table names column count and row count
     */
    Map<String, Integer> tblCache = Collections.synchronizedMap(new HashMap<>());

    private static final String COLCOUNT = "_colcount";
    private static final String ROWCOUNT = "_rowcount";
    private static final String NULLCOUNT = "_nullcount";
    private static final String BLANKCOUNT = "_blankcount";

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    main(String[] args)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
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
            db.analyseTables();
            //db.buildTableNullsBlankSummary();
            ArrayList<String[]> transposed = db.transPoseNb("uploads");

            String result = transposed.get(5)[0];
            for (int i = 0; i < transposed.size(); i++) {
                String[] col = transposed.get(i);
                System.out.print("  \n    cnt " + i);
                System.out.print(" " + col[0]);
                System.out.print(" " + col[1]);
                System.out.print(" " + col[2]);

//                db.getColBlankCount("requests", "eao1");
//                db.getBlankTableCount("uploads");
//                db.transPoseNb("requests");
            }
//            db.getColumnNames("uploads");
//
//            ResultSet resultRS = db.analyseTables();
//            resultRS.first();
//
//            ResultSet rs = db.analyseTables();
//            TableModel table_model = db.resultSetToTableModel(rs);
//            System.out.println("TABLE MODEL COLUMN NAME " + table_model.getColumnName(1));
//
//            System.out.println("GET COLUMN NAMES " + resultRS.getString(1) + "\n");
//
//            ResultSet nullcount = db.analyseTablesNullsBlanks("requests");
//            nullcount.first();
//            System.out.println("Null Count " + nullcount.getString(1) + nullcount.getString(2) + nullcount.getString(3) + "string 11 " + nullcount.getString(11));
//
//            System.out.println("TABLE ROW COUNT" + db.getRowCount("uploads"));
//            System.out.println("TABLE Column COUNT" + db.getColCount("uploads"));
//            //db.transPoseNb("uploads");
//            System.out.println("TABLE Null COUNT" + db.getNullTableCount("uploads"));
//            System.out.println("TABLE Blank COUNT" + db.getBlankTableCount("uploads"));
//            
//            System.out.println("get null count in Main " + db.getColNullCount("uploads", "request"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    MySQLDBConnect()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * Default Constructor
     */
    public MySQLDBConnect() {
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    MySQLDBConnect(String serverIP, String databaseName, String username, String password, String port)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * MySQLDBConnect(String serverIP, String databaseName, String
     * username,String password, String port) creates an instance of the
     * class.The parameters used to connect to the database are entered in the
     * DBConnectDialog.
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

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getConnection() 
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * Establish Connection to the database. The parameters used to connect to
     * the database are entered in the DBConnectDialog.
     *
     * @throws SQLException
     */
    public void getConnection() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://" + serverIP + ":"
                + port + "/" + databaseName, username, password);
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    ResultSet getTableNames()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * Creates a list of tables that exist in the database. Not that no where
     * clause is passed. This is always the full list of tables in the database.
     *
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet getTableNames() throws SQLException {
        Statement statement = conn.createStatement();

        ResultSet tbl_results = statement.executeQuery("select  count(*) from information_schema.tables where table_schema='" + databaseName + "' and table_type='BASE TABLE'");
        tbl_results.first();
        numberOfTable = tbl_results.getInt(1);
        totalNumberOfTable = tbl_results.getInt(1);

        tbl_results = statement.executeQuery("select  table_name as \"Tables\" from information_schema.tables where table_schema='" + databaseName + "' and table_type='BASE TABLE'");

        return tbl_results;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getTableNames(String SQLwhere)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * getTableNames(String SQLwhere) returns a result set of table names from
     * information_schema filtered by the SQL where clause passed into the
     * method. This method is used to enable a reduced result set of table names
     * when navigating between the two summary tables as well as between the
     * summary tables and the data explorer view.
     *
     * @param String SQLwhere
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet getTableNames(String SQLwhere) throws SQLException {
        Statement statement = conn.createStatement();

        ResultSet tbl_results = statement.executeQuery("select  count(*) from information_schema.tables where table_schema='" + databaseName + "' and table_type='BASE TABLE' " + SQLwhere);
        tbl_results.first();
        numberOfTable = tbl_results.getInt(1);

        tbl_results = statement.executeQuery("select  table_name as \"Tables\" from information_schema.tables where table_schema='" + databaseName + "' and table_type='BASE TABLE' " + SQLwhere);

        return tbl_results;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    analyseTables() 
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * scans the database and return a result set for each table that shows the
     * table name, column count and row count
     *
     * creates a hash table to store the column count and row count for each
     * table
     *
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet analyseTables() throws SQLException {

        String masterquery = "Select table_name, column_cnt, rowcount from ( ";
        String unionquery = "";

        ResultSet tableNames = getTableNames();
        int tablecount = getNumberOfTables();
        tableNames.first();

        for (int i = 0; i < tablecount; i++) {

            unionquery += "select c.table_name,COUNT(c.COLUMN_NAME) column_cnt,\n"
                    + "(select count(*) from " + databaseName + "." + tableNames.getString(1) + " )  rowcount\n"
                    + "  from information_schema.columns c \n"
                    + "  where   c.table_schema='" + databaseName + "' \n"
                    + "  and   c.table_name='" + tableNames.getString(1) + "' \n"
                    + "  group by c.table_schema,c.table_name";

            if (i == tablecount - 1) {
                unionquery += "\n ";

            } else {
                unionquery += "\n union all \n";
            }
            tableNames.next();
        }

        masterquery = masterquery + unionquery + " ) as data1;";

        Statement statement = conn.createStatement();
        ResultSet iat_results = statement.executeQuery(masterquery);


        while (iat_results.next()) {

            tblCache.put(iat_results.getString(1) + COLCOUNT, Integer.parseInt(iat_results.getString("column_cnt")));
            tblCache.put(iat_results.getString(1) + ROWCOUNT, Integer.parseInt(iat_results.getString("rowcount")));
        }
        iat_results.first();

        return iat_results;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getColumnNames(String table_name)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * Retrieving columns names from information_schema for a given table.
     *
     * @param String table_name
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet getColumnNames(String table_name) throws SQLException {

        Statement statement = conn.createStatement();
        ResultSet getColNames = statement.executeQuery("select  column_name as \"Columns\" from information_schema.columns where table_name='" + table_name + "'  and table_schema='" + databaseName + "'");
        return getColNames;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    analyseTablesNullsBlanks(String table_name)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * Build a dynamic query statement that is used in transPoseNb() to
     * determine which if the columns of a specified table contain nulls and or
     * blanks
     *
     * @param String table_name
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet analyseTablesNullsBlanks(String table_name) throws SQLException {
        //======================================================================
        // Dynamically building nulls and blanks counts from getColumnNames
        //======================================================================
        Statement statement = conn.createStatement();
        ResultSet colCount = statement.executeQuery(" select count(*) from information_schema.columns where table_name = '" + table_name + "' and table_schema='" + databaseName + "';");
        colCount.first();
        int j = colCount.getInt(1);
        String query = "select ";

        ResultSet col_results = getColumnNames(table_name);

        if (col_results.next()) {
            for (int i = 0; i < j; i++) {
                query += "SUM(CASE WHEN `" + col_results.getString(1)
                        + "` IS NULL THEN 1 ELSE 0 END) as `nulls_"
                        + col_results.getString(1);
                query += "`, SUM(CASE WHEN `" + col_results.getString(1)
                        + "` ='' THEN 1 ELSE 0 END) as `blanks_"
                        + col_results.getString(1);
                if (i == j - 1) {
                    query += "` ";

                } else {
                    query += "`, ";
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
        return null;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getColCount(String table_name)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * get the number of columns for a table from a hash map table. The hash map
     * table is set with the column count in the analyseTables() method.
     *
     * @param table_name
     * @return int
     * @throws SQLException
     */
    public int getColCount(String table_name) throws SQLException {
        return tblCache.get(table_name + COLCOUNT);
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getRowCount(String table_name)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * get the number of rows for a table from a hash map table. The hash map
     * table is set with the row count in the analyseTables() method.
     *
     * @param String table_name
     * @return int
     * @throws SQLException
     */
    public int getRowCount(String table_name) throws SQLException {
        return tblCache.get(table_name + ROWCOUNT);
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getNullTableCount(String table_name) 
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * Gets the calculated number of NULL fields for a specified table from a
     * hash map table. The trasPoseNb() function has to be called before this
     * function can be called to ensure that the variable has a value.
     *
     * @param table_name
     * @return int
     * @throws SQLException
     */
    public int getNullTableCount(String table_name) throws SQLException {
        return tblCache.get(table_name + NULLCOUNT);
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getBlankTableCount(String table_name
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * Gets the calculated number BLANK fields for a specified table from a hash
     * map table. The trasPoseNb() function has to be called before this
     * function can be called to ensure that the variable has a value.
     *
     * @param String table_name
     * @return int
     * @throws SQLException
     */
    public int getBlankTableCount(String table_name) throws SQLException {
        return tblCache.get(table_name + BLANKCOUNT);
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    transPoseNb(String table_name)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * transPoseNb use a dynamically generated query built in
     * analyseTablesNullsBlanks() to create an array which lists column names
     * and the count of nulls, count of blanks for a specified table.
     *
     * @param table_name
     * @return ArrayList<String[]>
     * @throws SQLException
     */
    public ArrayList<String[]> transPoseNb(String table_name) throws SQLException {

        ArrayList<String[]> nbc = new ArrayList<>();

        ResultSet nBCnt = analyseTablesNullsBlanks(table_name);

        ResultSetMetaData col_meta = nBCnt.getMetaData();

        int col_numColumns = col_meta.getColumnCount();

        String colName = null;
        String colNulls = null;
        String colBlanks = null;
        int tableNulls = 0;
        int tableBlanks = 0;

        while (nBCnt.next()) {

            for (int ci = 1; ci <= col_numColumns; ci += 2) {
                String colname = col_meta.getColumnName(ci);

                colName = colname.replace("nulls_", "");

                if (nBCnt.getString(ci) != null) {
                    colNulls = nBCnt.getString(ci);
                } else {
                    colNulls = "0";
                }

                if (nBCnt.getString(ci + 1) != null) {
                    colBlanks = nBCnt.getString(ci + 1);
                } else {
                    colBlanks = "0";
                }

                String[] col = new String[]{colName, colNulls, colBlanks};

                nbc.add(col);
                tableNulls += Integer.parseInt(colNulls);
                tableBlanks += Integer.parseInt(colBlanks);

            }
        }
        tblCache.put(table_name + NULLCOUNT, tableNulls);
        tblCache.put(table_name + BLANKCOUNT, tableBlanks);

        nBCnt.first();

        return nbc;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getColNullCount(String table_name, String columnName)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * getColNullCount(String table_name, String columnName) returns the number
     * of null fields (records) found when a count of is null is done for a
     * specified column on a specified table in SQL
     *
     * @param String table_name
     * @param String columnName
     * @return int
     * @throws SQLException
     */
    public int getColNullCount(String table_name, String columnName) throws SQLException {

        //  getConnection();
        ArrayList<String[]> colNulls = transPoseNb(table_name);
        if (colNulls.size() > 0) {

            for (int i = 0; i < colNulls.size(); i++) {
                String[] col = colNulls.get(i);
                if (col[0].toString().equals(columnName)) {
                    return Integer.parseInt(col[1]);
                }
            }
        }
        return 0;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getColBlankCount(String table_name, String columnName)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * getColBlankCount() returns the number of blank fields (records) found in
     * data when a count of ='' is done for a specified column on a specified
     * table in SQL
     *
     * @param String table_name
     * @param String columnName
     * @return int
     * @throws SQLException
     */
    public int getColBlankCount(String table_name, String columnName) throws SQLException {

        getConnection();
        ArrayList<String[]> colNulls = transPoseNb(table_name);

        String result = colNulls.get(5)[0];
        for (int i = 0; i < colNulls.size(); i++) {
            String[] col = colNulls.get(i);
            if (col[0].toString().equals(columnName)) {
                return Integer.parseInt(col[2]);
            }
        }
        return 0;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getNumberOfTables()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * getNumberOfTables() return the number of tables in a database from
     * information schema database, if no selection has been made in
     * summaryTable or tableNameTable getNumberOfTables() is equal to
     * getTotalNumberOfTables. However, if table names have been selected in the
     * table view (summaryTable or tableNameTable) then getNumberOfTables()
     * reflect the count of tables selected.
     *
     * @return int
     */
    public int getNumberOfTables() {
        return numberOfTable;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getTotalNumberOfTables()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * getTotalNumberOfTables() return the total number of tables in a database
     * from information schema database
     *
     * @return int
     */
    public int getTotalNumberOfTables() {
        return totalNumberOfTable;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    resultSetToTableModel(ResultSet rs) 
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * resultSetToTableModel() converts a SQL result set into a JTable model by
     * reading the data (and meta data for column names) into two vectors and
     * then using the data and column name vectors to construct a tablemodel
     *
     * @param ResultSet columnNames
     * @return TableModel
     */
    public DefaultTableModel resultSetToTableModel(ResultSet rs) {
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

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    resultSetToColumnNameTableModel(ResultSet rs)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * resultSetToColumnNameTableModel() converts a SQL result of column names
     * into JTable model with columns for - column names - check box to allow
     * the user to indicate if column should be filtered for NULLs - check box
     * to allow the user to indicate if column should be filtered for BLANKS * -
     * a text field to allow the user to apply a simple text filter on the
     * column
     *
     * @param ResultSet columnNames
     * @return TableModel
     */
    public TableModel resultSetToColumnNameTableModel(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Vector columnNames = new Vector();

            // Get the column names
            for (int column = 0; column < numberOfColumns; column++) {

                columnNames.addElement(metaData.getColumnLabel(column + 1));
                columnNames.addElement("Null");
                columnNames.addElement("Blank");
                columnNames.addElement("Text Fiter");
            }

            // Get all rows.
            Vector rows = new Vector();

            while (rs.next()) {
                Vector newRow = new Vector();

                for (int i = 1; i <= numberOfColumns; i++) {
                    newRow.addElement(rs.getObject(i));
                    newRow.addElement(false);
                    newRow.addElement(false);
                    newRow.addElement(null);

                }

                rows.addElement(newRow);
            }

            DefaultTableModel model = new DefaultTableModel(rows, columnNames);

            return model;

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    formatDouble(double d)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * formatDouble(double d) formats a to display as cleanly as possible.
     *
     * @param d
     * @return
     */
    public static String formatDouble(double d) {
        if (d == (long) d) {
            return String.format("%d", (long) d);
        } else {
            return String.format("%s", d);
        }
    }

}
