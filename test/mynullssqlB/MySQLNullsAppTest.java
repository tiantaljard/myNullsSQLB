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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static jdk.nashorn.internal.objects.Global.instance;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author TianTaljard
 */
public class MySQLNullsAppTest {

    MySQLDBConnect instance;
    static final String TABLENAME = "comments";
    static final String TABLENAMEWITHROWS = "requests";
    static final String DATABASENAME = "reqlocaldb";
    Statement statement;

    public MySQLNullsAppTest() {
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
     * Test of rowsColOneSelected method, of class MySQLNullsApp.
     */
    @Test
    public void testRowsColOneSelected() {
        System.out.println("rowsColOneSelected");
        JTable jTable = null;
        MySQLNullsApp instance = new MySQLNullsApp();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.rowsColOneSelected(jTable);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rowColOneSelected method, of class MySQLNullsApp.
     */
    @Test
    public void testRowColOneSelected() {
        System.out.println("rowColOneSelected");
        JTable jTable = null;
        MySQLNullsApp instance = new MySQLNullsApp();
        String expResult = "";
        String result = instance.rowColOneSelected(jTable);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnData method, of class MySQLNullsApp.
     */
    @Test
    public void testGetColumnData() throws Exception {
        System.out.println("getColumnData");
        MySQLNullsApp instance = new MySQLNullsApp();
        ResultSet expResult = null;
        ResultSet result = instance.getColumnData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColumnNameTable method, of class MySQLNullsApp.
     */
    @Test
    public void testSetColumnNameTable() {
        System.out.println("setColumnNameTable");
        MySQLNullsApp instance = new MySQLNullsApp();
        instance.setColumnNameTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataTable method, of class MySQLNullsApp.
     */
    @Test
    public void testSetDataTable() {
        System.out.println("setDataTable");
        ResultSet data = null;
        MySQLNullsApp instance = new MySQLNullsApp();
        instance.setDataTable(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTableRowSorter method, of class MySQLNullsApp.
     */
    @Test
    public void testSetTableRowSorter() {
        System.out.println("setTableRowSorter");
        JTable table = null;
        MySQLNullsApp instance = new MySQLNullsApp();
        TableRowSorter<TableModel> expResult = null;
        TableRowSorter<TableModel> result = instance.setTableRowSorter(table);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setJTableColOneFilter method, of class MySQLNullsApp.
     */
    @Test
    public void testSetJTableColOneFilter() {
        System.out.println("setJTableColOneFilter");
        JTable table_name = null;
        JTextField textFilter = null;
        MySQLNullsApp instance = new MySQLNullsApp();
        instance.setJTableColOneFilter(table_name, textFilter);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showConnectionDialog method, of class MySQLNullsApp.
     */
    @Test
    public void testShowConnectionDialog() {
        System.out.println("showConnectionDialog");
        MySQLNullsApp instance = new MySQLNullsApp();
        instance.showConnectionDialog();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeModel method, of class MySQLNullsApp.
     */
    @Test
    public void testInitializeModel() throws Exception {
        System.out.println("initializeModel");
        MySQLNullsApp instance = new MySQLNullsApp();
        instance.initializeModel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildTransposeTable method, of class MySQLNullsApp.
     */
    @Test
    public void testBuildTransposeTable() throws Exception {
        System.out.println("buildTransposeTable");
        String table_name = "";
        MySQLNullsApp instance = new MySQLNullsApp();
        instance.buildTransposeTable(table_name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildColumnDataSQLWhere method, of class MySQLNullsApp.
     */
    @Test
    public void testBuildColumnDataSQLWhere() {
        System.out.println("buildColumnDataSQLWhere");
        MySQLNullsApp instance = new MySQLNullsApp();
        instance.buildColumnDataSQLWhere();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildColumnDataQuery method, of class MySQLNullsApp.
     */
    @Test
    public void testBuildColumnDataQuery() {
        System.out.println("buildColumnDataQuery");
        MySQLNullsApp instance = new MySQLNullsApp();
        instance.buildColumnDataQuery();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
