/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynullssqlB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.TableModel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author TianTaljard
 */
public class MySQLDBConnectTest {

    MySQLDBConnect instance;
    static final String TABLENAME = "comments";
    static final String TABLENAMEWITHROWS = "requests";
    static final String DATABASENAME = "reqlocaldb";
    Statement statement;
    Map<String, Integer> tblCache = Collections.synchronizedMap(new HashMap<>());
    private static final String COLCOUNT = "_colcount";
    private static final String ROWCOUNT = "_rowcount";

    public MySQLDBConnectTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException {
        instance = new MySQLDBConnect("127.0.0.1", "reqlocaldb", "root", "Zppsit0!", "3306");
        instance.getConnection();
        statement = instance.conn.createStatement();

    }

    /**
     * Test of getConnection method, of class MySQLDBConnect. Make sure
     * connection to database established.
     */
    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");

        instance.getConnection();

        assertTrue(instance.conn.isValid(3));

    }

    /**
     * Test of showTables method, of class MySQLDBConnect. Check that table
     * names are returned from the schema
     */
    @Test
    public void testShowTables() throws Exception {
        System.out.println("showTables");
        ResultSet result = instance.showTables();
        assertNotNull(result);
        result.first();
        String s = result.getString(1);
        assertTrue(s.length() > 0);
        assertTrue(s.equals(TABLENAME));

    }

    /**
     * Test of initialAnalyseTables method, of class MySQLDBConnect. Check
     * initial result set for tables, column count and row count
     *
     */
    @Test
    public void testInitialAnalyseTables() throws SQLException {
        System.out.println("initialAnalyseTables");
        // CHECK RESULT SET FROM SQL STATEMENT    
        ResultSet expResult = null;

        expResult = statement.executeQuery("select c.table_name,COUNT(c.COLUMN_NAME) column_cnt,\n"
                + "(select table_rows from  information_schema.tables \n"
                + "where c.table_schema=table_schema and c.table_name= table_name)  rowcount\n"
                + "  from information_schema.columns c \n"
                + "  where   c.table_schema='" + DATABASENAME + "' \n"
                + "  group by c.table_schema,c.table_name;");
        expResult.first();
        String expected = expResult.getString(1);

        ResultSet result = instance.initialAnalyseTables();
        result.first();
        String resultString = result.getString(1);
        assertEquals(expected, resultString);
    }

    /**
     * Test of getColumnNames method, of class MySQLDBConnect.
     */
    @Test
    public void testGetColumnNames() throws Exception {
        System.out.println("getColumnNames");
        String table_name = TABLENAME;

        ResultSet expResult = statement.executeQuery("select  column_name as \"Columns\" from information_schema.columns where table_name='" + TABLENAME + "'  and table_schema='" + DATABASENAME + "'");
        ResultSet result = instance.getColumnNames(table_name);

        expResult.first();
        result.first();

        String expected = expResult.getString(1);
        String resultString = result.getString(1);

        assertEquals(expected, resultString);

    }

    /**
     * Test of secondAnalyseTablesNulls method, of class MySQLDBConnect. check
     * that a result set is returned.
     */
    @Test
    public void testSecondAnalyseTablesNulls() throws Exception {
        System.out.println("secondAnalyseTablesNulls");
        ResultSet result = instance.secondAnalyseTablesNulls(TABLENAMEWITHROWS);
        assertNotNull(result);
        result.first();
        String s = result.getString(1);
        assertTrue(s.length() > 0);

    }

    /**
     * Test of getColCount method, of class MySQLDBConnect. Check if the number
     * of columns match the number of columns for the table specified
     */
    @Test
    public void testGetColCount() throws Exception {
        System.out.println("getColCount");
        instance.initialAnalyseTables();
        int expResult=6;

        int result = instance.getColCount(TABLENAME);
        
        assertEquals(expResult, result);

    }

    /**
     * Test of getRowCount method, of class MySQLDBConnect.
     */
    @Test
    public void testGetRowCount() throws Exception {
        System.out.println("getRowCount");
        instance.initialAnalyseTables();
        int expResult=19;

        int result = instance.getRowCount(TABLENAMEWITHROWS);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of transPoseNb method, of class MySQLDBConnect.
     */
    @Test
    public void testTransPoseNb() throws Exception {
        System.out.println("transPoseNb");
        String table_name = "";
        MySQLDBConnect instance = new MySQLDBConnect();
        ArrayList expResult = null;
        ArrayList result = instance.transPoseNb(table_name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfTable method, of class MySQLDBConnect.
     */
    @Test
    public void testGetNumberOfTable() {
        System.out.println("getNumberOfTable");
        MySQLDBConnect instance = new MySQLDBConnect();
        int expResult = 0;
        int result = instance.getNumberOfTable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resultSetToTableModel method, of class MySQLDBConnect.
     */
    @Test
    public void testResultSetToTableModel() {
        System.out.println("resultSetToTableModel");
        ResultSet rs = null;
        MySQLDBConnect instance = new MySQLDBConnect();
        TableModel expResult = null;
        TableModel result = instance.resultSetToTableModel(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resultSetToColumnNameTableModel method, of class MySQLDBConnect.
     */
    @Test
    public void testResultSetToColumnNameTableModel() {
        System.out.println("resultSetToColumnNameTableModel");
        ResultSet rs = null;
        MySQLDBConnect instance = new MySQLDBConnect();
        TableModel expResult = null;
        TableModel result = instance.resultSetToColumnNameTableModel(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
