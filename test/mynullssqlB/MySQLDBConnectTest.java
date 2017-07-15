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
     * check that the correct column names for the table are passed back
     */
    @Test
    public void testGetColumnNames() throws Exception {
        System.out.println("getColumnNames");
        ResultSet resultRS = instance.getColumnNames(TABLENAMEWITHROWS);
        resultRS.first();
        String expResult="request";
        String result=resultRS.getString(1);
        assertEquals(expResult, result);

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
        String resultString = result.getString(11);
        String expResult="4";
        
        assertEquals(expResult,resultString);

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
     * check that an array with column number and number of nulls is 
     * passed correctly
     * 
     */
    @Test
    public void testTransPoseNb() throws Exception {
        System.out.println("transPoseNb");
        
        ArrayList<String[]> resultRs = instance.transPoseNb(TABLENAMEWITHROWS);
        String result=resultRs.get(5)[1];
        String result1=resultRs.get(5)[2];
        String result2=resultRs.get(5)[0].toString();
        String expResult="4";
        String expResult1="3";
        String expResult2="eao1";
        assertEquals(expResult, result);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getNumberOfTable method, of class MySQLDBConnect.
     * check the number of tables in database correctly returned.
     */
    @Test
    public void testGetNumberOfTable() throws SQLException {
        System.out.println("getNumberOfTable");
        instance.showTables();
        
        int expResult = 4;
        int result = instance.getNumberOfTable();
        assertEquals(expResult, result);
    }

    /**
     * Test of resultSetToTableModel method, of class MySQLDBConnect.
     */
    @Test
    public void testResultSetToTableModel() throws SQLException {
        System.out.println("resultSetToTableModel");
        ResultSet rs = instance.initialAnalyseTables();
        TableModel table_model =instance.resultSetToTableModel(rs);
        String result =table_model.getColumnName(1);
        String expResult ="column_cnt";
        
        assertEquals(expResult, result);
        
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
