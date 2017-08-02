/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynullssqlB;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Paths;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import static org.jfree.data.general.DatasetUtilities.isEmptyOrNull;

/**
 * @Date July 2017
 * @author TianTaljard The purpose of the class is to provide a GUI to find,
 * analyse and explore nulls and blanks in data
 */
public class MySQLNullsApp extends javax.swing.JFrame {

    private MySQLDBConnect db;
    private String dynamic_query_rowcount;
    private int dynamic_rowcount;
    private String dynamicSelectFrom;
    private String dynamicSQLWhere;
    private String dynamicFilterSelectFrom;

    //private String dynamicCSVSelectFrom;
    private String dynamicCSVSelect;
    private String dynamicCSVFrom;
    private String dynamicCSVColumnNames;

    private String dynamicFilterCSVSelect;
    private String dynamicFilterCSVFrom;
    private String dynamicFilterCSVColumnNames;

    private String lastSQLQueryCSVSelect;
    private String lastSQLQueryCSVFrom;
    private String lastSQLCSVColumnNames;

    int columnNameTableSelctionColumn;

    int limitRowsFilterInt = 0;
    String limitRows = "";

    @SuppressWarnings("UseOfObsoleteCollectionType")
    private Vector columnNamesSelectedInColumnNameTable = new Vector();
    // Create DataSet Data Object for Table Summary Chart
    private DefaultCategoryDataset summaryChartDataset = new DefaultCategoryDataset();
    // Create DataSet Data Object for Table Column  Chart
    private DefaultCategoryDataset tableColumnBarChartDataset = new DefaultCategoryDataset();
    // Create data set for rows analysis by number of columns null
    private DefaultPieDataset rowsColsNullsPieChartData = new DefaultPieDataset();
    // Create data set for rows analysis by number of columns blank
    private DefaultPieDataset rowsColsBlanksPieChartData = new DefaultPieDataset();
    // create data model for summary Table Column nulls and blanks
    private ArrayTableModel summaryTableColumnTableModel = new ArrayTableModel();
    // create data model for rows percentage per column null or blank
    private ArrayTableModel rowsNulsBlankArrayTableModel = new ArrayTableModel();
    // Create a Date format for a unique file identifyer.
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss.SSS");
    // CSV Delimeter
    private String csvDelimiter = "','";

    public ArrayTableModel getSummaryTableColumnTableModel() {
        return summaryTableColumnTableModel;
    }

    private static final Object[] columnsTableColumnSummary = {"Column Name", "Row Count", "Nulls Count", "Blanks Count", "% Nulls ", "% Blanks"};

    private String cardViewInUse = "";
    private static final String CHARTPANEL = "chartPanel";
    private static final String COLUMNDETAILSPLITPANEL = "columnDetailsSpiltPanel";
    private static final String DATAEXPLORERCARD = "dataExplorerCard";
    private static final String CARDDETAILANALYSISPANEL = "cardDetailAnalysisPanel";

    private String tableInUse = "";
    private static final String INITIALSUMMARYDATATABLE = "initialsummary";
    private static final String NBSUMMARYDATATABLE = "nbSummary";
    private static final String COLUMNNBSUMMARYDATATABLE = "columnnbsummary";
    private static final String ROWNBSUMMARYDATATABLE = "rownbsummary";
    private static final String SQLDATATABLE = "sqldata";

    private static final String INITIALTABLESUMMARY = "Table_Column_Row_Count_Summary_";
    private static final String NBCOLUMNSUMMARY = "Nulls_Blanks_Table_Summary_";
    private static final String TABLECOLUMNSUMMARY = "Table_Column_Summary_";
    private static final String ROWSCOLUMNSUMMARY = "Rows_Column_Analysis_";

    private int tableNameTableLastSelectedRow = -1;
    private int columnNameTableLastSelectedRow = -1;

    /**
     * Creates new form MySQLNullsApp
     */
    public MySQLNullsApp() {

        try {
            /*
            ResultTableModel tableModel = new ResultTableModel();
            System.out.println(db.getNumberOfTables());
            
            tableModel.setResultset(tbls);
            
            tableModel.setsqlRowCount(db.getNumberOfTables());
             */
            initComponents();
            showConnectionDialog();
            //initializeModel();
            setInitialSummaryTable();
            tableInUse = INITIALSUMMARYDATATABLE;

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

        tablePopupMenu = new javax.swing.JPopupMenu();
        showInitialSummaryTbl = new javax.swing.JMenuItem();
        showNBSummaryTbl = new javax.swing.JMenuItem();
        showNBSummaryTblAllTables = new javax.swing.JMenuItem();
        showNBSummaryTblForSelected = new javax.swing.JMenuItem();
        showDataNavigator = new javax.swing.JMenuItem();
        showDataNavigatorAllTables = new javax.swing.JMenuItem();
        showSummaryChart = new javax.swing.JMenuItem();
        showRowsNullsBlanksPerColumnTable = new javax.swing.JMenuItem();
        showRowsColumnNullsPieChart = new javax.swing.JMenuItem();
        showRowsColumnBlanksPieChart = new javax.swing.JMenuItem();
        exportRowsColumnsNullsBlanks = new javax.swing.JMenuItem();
        showTableColumnSummary = new javax.swing.JMenuItem();
        showTableAllColumnSummary = new javax.swing.JMenuItem();
        showTableColumnChartSelectedRows = new javax.swing.JMenuItem();
        showTableColumnChart = new javax.swing.JMenuItem();
        exportTableColumnsSummary = new javax.swing.JMenuItem();
        noDataMainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        chartErrorMessageTextPanel = new javax.swing.JScrollPane();
        chartErrorMessageTextArea = new javax.swing.JTextArea();
        exportDataTableSQL = new javax.swing.JMenuItem();
        exportNBSummary = new javax.swing.JMenuItem();
        exportInitialTableSummary = new javax.swing.JMenuItem();
        showColumnData = new javax.swing.JMenuItem();
        mainJPanel = new javax.swing.JPanel();
        summaryPanel = new javax.swing.JPanel();
        summaryScrollPanel = new javax.swing.JScrollPane();
        summaryTable = new javax.swing.JTable();
        topInitialSummaryView = new javax.swing.JPanel();
        intialSummaryfilterPanel = new javax.swing.JPanel();
        initialSummaryTableFilter = new javax.swing.JTextField();
        dataExplorerSplitPane = new javax.swing.JSplitPane();
        tblNmParentPanel = new javax.swing.JPanel();
        tblNmFilterPanel = new javax.swing.JPanel();
        tableNameFilter = new javax.swing.JTextField();
        tblNmScrollPanel = new javax.swing.JScrollPane();
        tableNameTable = new javax.swing.JTable();
        mainRightPanel = new javax.swing.JPanel();
        columnDetailsSpiltPanel = new javax.swing.JSplitPane();
        colNmParentPanel = new javax.swing.JPanel();
        colNmFilterPanel = new javax.swing.JPanel();
        columnNameFilter = new javax.swing.JTextField();
        limitRowsFilter = new javax.swing.JTextField();
        colNmScrollPanel = new javax.swing.JScrollPane();
        columnNameTable = new javax.swing.JTable(){
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                    return String.class;
                    case 1:
                    return Boolean.class;
                    case 2:
                    return Boolean.class;
                    case 3:
                    return String.class;
                    default:
                    return String.class;
                }
            }
        };
        columnDetailPanel = new javax.swing.JPanel();
        dataTableScrollPane = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        columnDetailChartPanel = new javax.swing.JPanel();
        cardChartPanel = new javax.swing.JPanel();
        chartPanel = new javax.swing.JPanel();
        detailAnalysisPanel = new javax.swing.JPanel();
        detailAnalysisScrollPanel = new javax.swing.JScrollPane();
        detailAnalysisTable = new javax.swing.JTable();
        topDetailAnalysisView = new javax.swing.JPanel();
        detailAnalysisFilterPanel = new javax.swing.JPanel();
        detailAnalysisFilter = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        filejMenu = new javax.swing.JMenu();
        editjMenu = new javax.swing.JMenu();

        tablePopupMenu =new JPopupMenu();

        showInitialSummaryTbl.setText("Show Initial Table Summary");
        showInitialSummaryTbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showInitialSummaryTblActionPerformed(evt);
            }
        });

        showNBSummaryTbl.setText("Show Nulls & Blank Summary Table");
        showNBSummaryTbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showNBSummaryTblActionPerformed(evt);
            }
        });

        showNBSummaryTblAllTables.setText("Show Nulls & Blank Summary for All Tables");
        showNBSummaryTblAllTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showNBSummaryTblAllTablesActionPerformed(evt);
            }
        });

        showNBSummaryTblForSelected.setText("Show Nulls & Blank Summary Table for Selected Records");
        showNBSummaryTblForSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showNBSummaryTblForSelectedActionPerformed(evt);
            }
        });

        showDataNavigator.setText("Show Table Explorer");
        showDataNavigator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDataNavigatorActionPerformed(evt);
            }
        });

        showDataNavigatorAllTables.setText("Show All Tables");
        showDataNavigatorAllTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDataNavigatorAllTablesActionPerformed(evt);
            }
        });

        showSummaryChart.setText("Show Summary Chart");
        showSummaryChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSummaryChartActionPerformed(evt);
            }
        });

        showRowsNullsBlanksPerColumnTable.setText("Show Analysis of Rows per Column Null or Blank");
        showRowsNullsBlanksPerColumnTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showRowsNullsBlanksPerColumnTableActionPerformed(evt);
            }
        });

        showRowsColumnNullsPieChart.setText("Show Rows Percentage per Null Column Count Pie Chart");
        showRowsColumnNullsPieChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showRowsColumnNullsPieChartActionPerformed(evt);
            }
        });
        showRowsColumnNullsPieChart.getAccessibleContext().setAccessibleName("");

        showRowsColumnBlanksPieChart.setText("Show Rows Percentage per Blank Column Count Pie Chart");
        showRowsColumnBlanksPieChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showRowsColumnBlanksPieChartActionPerformed(evt);
            }
        });

        exportRowsColumnsNullsBlanks.setText("Export Analysis of Rows per Column Null or Blank ");
        exportRowsColumnsNullsBlanks.setToolTipText("");
        exportRowsColumnsNullsBlanks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportRowsColumnsNullsBlanksActionPerformed(evt);
            }
        });

        showTableColumnSummary.setText("Show Table Nulls & Blanks Column Summary");
        showTableColumnSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTableColumnSummaryActionPerformed(evt);
            }
        });

        showTableAllColumnSummary.setText("Show Table Nulls & Blanks Column Summary for All Columns");
        showTableAllColumnSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTableAllColumnSummaryActionPerformed(evt);
            }
        });

        showTableColumnChartSelectedRows.setText("Show Table Nulls & Blanks Column Chart for Selected Rows");
        showTableColumnChartSelectedRows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTableColumnChartSelectedRowsActionPerformed(evt);
            }
        });

        showTableColumnChart.setLabel("Show Nulls & Blanks Column Chart");
        showTableColumnChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTableColumnChartActionPerformed(evt);
            }
        });
        showTableColumnChart.getAccessibleContext().setAccessibleName("");

        exportTableColumnsSummary.setText("Export Table Nulls & Blanks Column Summary");
        exportTableColumnsSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportTableColumnsSummaryActionPerformed(evt);
            }
        });

        noDataMainPanel.setMaximumSize(new java.awt.Dimension(200, 200));
        noDataMainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 564, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        noDataMainPanel.add(jPanel1, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 564, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        noDataMainPanel.add(jPanel2, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        noDataMainPanel.add(jPanel3, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        noDataMainPanel.add(jPanel4, java.awt.BorderLayout.LINE_START);

        chartErrorMessageTextArea.setColumns(20);
        chartErrorMessageTextArea.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        chartErrorMessageTextArea.setLineWrap(true);
        chartErrorMessageTextArea.setRows(5);
        chartErrorMessageTextArea.setWrapStyleWord(true);
        chartErrorMessageTextPanel.setViewportView(chartErrorMessageTextArea);

        noDataMainPanel.add(chartErrorMessageTextPanel, java.awt.BorderLayout.CENTER);

        exportDataTableSQL.setText("Export Selected Table Data");
        exportDataTableSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportDataTableSQLActionPerformed(evt);
            }
        });

        exportNBSummary.setText("Export Nulls & Blank Summary Table");
        exportNBSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportNBSummaryActionPerformed(evt);
            }
        });

        exportInitialTableSummary.setText("Export Table Column & Row Count Summary");
        exportInitialTableSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportInitialTableSummaryActionPerformed(evt);
            }
        });

        showColumnData.setText("Explore Column Data");
        showColumnData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showColumnDataActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainJPanel.setLayout(new java.awt.CardLayout());

        summaryPanel.setLayout(new java.awt.BorderLayout());

        summaryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                summaryTableMouseClicked(evt);
            }
        });
        summaryScrollPanel.setViewportView(summaryTable);

        summaryPanel.add(summaryScrollPanel, java.awt.BorderLayout.CENTER);

        topInitialSummaryView.setLayout(new java.awt.BorderLayout());

        initialSummaryTableFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                initialSummaryTableFilterKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout intialSummaryfilterPanelLayout = new javax.swing.GroupLayout(intialSummaryfilterPanel);
        intialSummaryfilterPanel.setLayout(intialSummaryfilterPanelLayout);
        intialSummaryfilterPanelLayout.setHorizontalGroup(
            intialSummaryfilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intialSummaryfilterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(initialSummaryTableFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(639, Short.MAX_VALUE))
        );
        intialSummaryfilterPanelLayout.setVerticalGroup(
            intialSummaryfilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, intialSummaryfilterPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(initialSummaryTableFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        topInitialSummaryView.add(intialSummaryfilterPanel, java.awt.BorderLayout.CENTER);

        summaryPanel.add(topInitialSummaryView, java.awt.BorderLayout.NORTH);

        mainJPanel.add(summaryPanel, "initialSummaryCard");

        dataExplorerSplitPane.setPreferredSize(new java.awt.Dimension(800, 536));

        tblNmParentPanel.setLayout(new java.awt.BorderLayout());

        tblNmFilterPanel.setLayout(new java.awt.BorderLayout());

        tableNameFilter.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tableNameFilter.setToolTipText("Table name filter");
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

        dataExplorerSplitPane.setLeftComponent(tblNmParentPanel);

        mainRightPanel.setLayout(new java.awt.CardLayout());

        columnDetailsSpiltPanel.setPreferredSize(new java.awt.Dimension(450, 532));

        colNmParentPanel.setLayout(new java.awt.BorderLayout());

        colNmFilterPanel.setLayout(new java.awt.BorderLayout());

        columnNameFilter.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        columnNameFilter.setToolTipText("Column name filter");
        columnNameFilter.setPreferredSize(new java.awt.Dimension(100, 26));
        columnNameFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                columnNameFilterKeyReleased(evt);
            }
        });
        colNmFilterPanel.add(columnNameFilter, java.awt.BorderLayout.WEST);

        limitRowsFilter.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        limitRowsFilter.setToolTipText("Return top n rows");
        limitRowsFilter.setPreferredSize(new java.awt.Dimension(55, 26));
        limitRowsFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limitRowsFilterActionPerformed(evt);
            }
        });
        limitRowsFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                limitRowsFilterKeyReleased(evt);
            }
        });
        colNmFilterPanel.add(limitRowsFilter, java.awt.BorderLayout.EAST);

        colNmParentPanel.add(colNmFilterPanel, java.awt.BorderLayout.NORTH);

        colNmScrollPanel.setToolTipText("\n\n");
        colNmScrollPanel.setPreferredSize(new java.awt.Dimension(150, 500));
        colNmScrollPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                colNmScrollPanelMouseClicked(evt);
            }
        });

        columnNameTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Colums", "Nulls", "Blanks", "null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        columnNameTable.setToolTipText("<html>\n<br>Specify columns and conditons for data display<br><br>\n\n<html>Columns: select the table to display<br><br>\n<html>Null: Select to limit result set to only rows where column is null<br> <br>\n<html>Blank: Select to limit result set to only rows where column is blank<br><br>\n<html>Text: Enter text to limit result to only rows that contain entered text<br><br></html>");
        columnNameTable.setSelectionMode(1);
        columnNameTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                columnNameTableMouseReleased(evt);
            }
        });
        columnNameTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                columnNameTableKeyReleased(evt);
            }
        });
        colNmScrollPanel.setViewportView(columnNameTable);

        colNmParentPanel.add(colNmScrollPanel, java.awt.BorderLayout.CENTER);

        columnDetailsSpiltPanel.setLeftComponent(colNmParentPanel);

        columnDetailPanel.setPreferredSize(new java.awt.Dimension(300, 404));
        columnDetailPanel.setLayout(new java.awt.BorderLayout());

        dataTableScrollPane.setPreferredSize(new java.awt.Dimension(300, 404));

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
        dataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataTableMouseClicked(evt);
            }
        });
        dataTableScrollPane.setViewportView(dataTable);

        columnDetailPanel.add(dataTableScrollPane, java.awt.BorderLayout.CENTER);

        columnDetailChartPanel.setLayout(new java.awt.BorderLayout());
        columnDetailPanel.add(columnDetailChartPanel, java.awt.BorderLayout.PAGE_START);

        columnDetailsSpiltPanel.setRightComponent(columnDetailPanel);

        mainRightPanel.add(columnDetailsSpiltPanel, "cardColumnDetailSplitPanel");

        chartPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout cardChartPanelLayout = new javax.swing.GroupLayout(cardChartPanel);
        cardChartPanel.setLayout(cardChartPanelLayout);
        cardChartPanelLayout.setHorizontalGroup(
            cardChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
        );
        cardChartPanelLayout.setVerticalGroup(
            cardChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardChartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainRightPanel.add(cardChartPanel, "chartPanel");

        detailAnalysisPanel.setLayout(new java.awt.BorderLayout());

        detailAnalysisTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detailAnalysisTableMouseClicked(evt);
            }
        });
        detailAnalysisScrollPanel.setViewportView(detailAnalysisTable);

        detailAnalysisPanel.add(detailAnalysisScrollPanel, java.awt.BorderLayout.CENTER);

        topDetailAnalysisView.setLayout(new java.awt.BorderLayout());

        detailAnalysisFilterPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                detailAnalysisFilterPanelKeyReleased(evt);
            }
        });

        detailAnalysisFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                detailAnalysisFilterKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout detailAnalysisFilterPanelLayout = new javax.swing.GroupLayout(detailAnalysisFilterPanel);
        detailAnalysisFilterPanel.setLayout(detailAnalysisFilterPanelLayout);
        detailAnalysisFilterPanelLayout.setHorizontalGroup(
            detailAnalysisFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailAnalysisFilterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(detailAnalysisFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(457, Short.MAX_VALUE))
        );
        detailAnalysisFilterPanelLayout.setVerticalGroup(
            detailAnalysisFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailAnalysisFilterPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(detailAnalysisFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        topDetailAnalysisView.add(detailAnalysisFilterPanel, java.awt.BorderLayout.CENTER);

        detailAnalysisPanel.add(topDetailAnalysisView, java.awt.BorderLayout.NORTH);

        mainRightPanel.add(detailAnalysisPanel, "cardDetailAnalysisPanel");

        dataExplorerSplitPane.setRightComponent(mainRightPanel);

        mainJPanel.add(dataExplorerSplitPane, "dataExplorerCard");

        getContentPane().add(mainJPanel, java.awt.BorderLayout.CENTER);

        filejMenu.setText("File");
        jMenuBar1.add(filejMenu);

        editjMenu.setText("Edit");
        jMenuBar1.add(editjMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void tableNameTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNameTableMouseClicked
        // check that a talbe name has been selected. If Not, do not show menu's.
        if (tableNameTable.getSelectedRow() > -1) {
            // only do the following if a DIFFERENT from previous table name
            // is selected from the table navigator table window
            if (tableNameTableLastSelectedRow != tableNameTable.getSelectedRow()) {
                try {
                    setTableColumnSummaryTable();
                    tableInUse = COLUMNNBSUMMARYDATATABLE;
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
                }
                setDetailAnalysisCard();
                columnNameTableLastSelectedRow = -1;
                resetDynamicVariables();
                setColumnNameTable();
                setJTableColOneFilter(columnNameTable, columnNameFilter);
            }
            // if the mouse button is pressed to launch the pop up menus
            if (evt.getButton() == MouseEvent.BUTTON3) {
                removeTableMenuPopupItems();

                if (tableNameTableLastSelectedRow == tableNameTable.getSelectedRow()
                        && tableInUse != COLUMNNBSUMMARYDATATABLE) {
                    tablePopupMenu.add(showTableColumnSummary);
                }

                if (tableNameTableLastSelectedRow == tableNameTable.getSelectedRow()
                        && tableInUse != ROWNBSUMMARYDATATABLE) {
                    tablePopupMenu.add(showRowsNullsBlanksPerColumnTable);
                }

                if (tableNameTableLastSelectedRow == tableNameTable.getSelectedRow()
                        && tableInUse != SQLDATATABLE) {
                    tablePopupMenu.add(showColumnData);

                }

                if (db.getTotalNumberOfTables() != tableNameTable.getModel().getRowCount()) {
                    tablePopupMenu.add(showDataNavigatorAllTables);
                }

                tablePopupMenu.add(showSummaryChart);
                tablePopupMenu.add(showInitialSummaryTbl);

                tablePopupMenu.show(tableNameTable, evt.getX(), evt.getY());

            }

            if (tableNameTableLastSelectedRow == tableNameTable.getSelectedRow()
                    && cardViewInUse.equals(CHARTPANEL)) {

                setDetailAnalysisCard();
            }

            tableNameTableLastSelectedRow = tableNameTable.getSelectedRow();
        }
    }//GEN-LAST:event_tableNameTableMouseClicked

    private void tableNameTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableNameTableKeyReleased
        resetDynamicVariables();
        setColumnNameTable();
        setJTableColOneFilter(columnNameTable, columnNameFilter);
        try {
            setTableColumnSummaryTable();
            tableInUse = COLUMNNBSUMMARYDATATABLE;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableNameTableLastSelectedRow = tableNameTable.getSelectedRow();
        columnNameTableLastSelectedRow = -1;
        setDetailAnalysisCard();


    }//GEN-LAST:event_tableNameTableKeyReleased

    private void columnNameTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_columnNameTableKeyReleased
        try {
            columnNameTableSelctionColumn = columnNameTable.getSelectedColumn();
            setDataTable(getColumnData());
            tableInUse = SQLDATATABLE;
            dataTableScrollPane.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        columnNameTableLastSelectedRow = columnNameTable.getSelectedRow();

    }//GEN-LAST:event_columnNameTableKeyReleased

    private void tableNameFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableNameFilterKeyReleased
        setJTableColOneFilter(tableNameTable, tableNameFilter);
        tableNameTableLastSelectedRow = -1;
        setColumnNameTable();
        setJTableColOneFilter(columnNameTable, columnNameFilter);
        //buildSummaryTableDataBarChartExplorerPanel();
        setExplorerChartCard();
        tableInUse = NBSUMMARYDATATABLE;
    }//GEN-LAST:event_tableNameFilterKeyReleased

    private void columnNameFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_columnNameFilterKeyReleased
        setJTableColOneFilter(columnNameTable, columnNameFilter);
        columnNameTableLastSelectedRow = -1;
    }//GEN-LAST:event_columnNameFilterKeyReleased

    private void columnNameTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_columnNameTableMouseReleased
        if (columnNameTable.getSelectedRow() > -1) {

            columnNameTableSelctionColumn = columnNameTable.columnAtPoint(evt.getPoint());
            try {
                setDataTable(getColumnData());
                tableInUse = SQLDATATABLE;
                dataTableScrollPane.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            columnNameTableLastSelectedRow = columnNameTable.getSelectedRow();

        }
    }//GEN-LAST:event_columnNameTableMouseReleased

    private void initialSummaryTableFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_initialSummaryTableFilterKeyReleased
        setJTableColOneFilter(summaryTable, initialSummaryTableFilter);
    }//GEN-LAST:event_initialSummaryTableFilterKeyReleased

    private void summaryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_summaryTableMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {

            removeTableMenuPopupItems();

            if (summaryTable.getModel().getColumnCount() == 3) {
                tablePopupMenu.add(showNBSummaryTbl);
                tablePopupMenu.add(showDataNavigator);
                tablePopupMenu.add(exportInitialTableSummary);
            }
            if (summaryTable.getModel().getColumnCount() == 7) {
                tablePopupMenu.add(showInitialSummaryTbl);
                tablePopupMenu.add(showDataNavigator);
                tablePopupMenu.add(showSummaryChart);

                if (db.getTotalNumberOfTables() != summaryTable.getModel().getRowCount()) {
                    tablePopupMenu.add(showNBSummaryTblAllTables);
                }
                if (summaryTable.getSelectedRowCount() > 0 && summaryTable.getModel().getRowCount() != summaryTable.getSelectedRowCount()) {
                    tablePopupMenu.add(showNBSummaryTblForSelected);
                }
                tablePopupMenu.add(exportNBSummary);
            }

            tablePopupMenu.show(summaryTable, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_summaryTableMouseClicked

    private void showNBSummaryTblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showNBSummaryTblActionPerformed
        try {
            setNullsBlankSummaryTable();
            tableInUse = NBSUMMARYDATATABLE;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        setInitialSummaryCard();
        initialSummaryTableFilter.setText(null);


    }//GEN-LAST:event_showNBSummaryTblActionPerformed

    private void showInitialSummaryTblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showInitialSummaryTblActionPerformed
        try {
            setInitialSummaryTable();
            tableInUse = INITIALSUMMARYDATATABLE;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        setInitialSummaryCard();
        initialSummaryTableFilter.setText(null);

    }//GEN-LAST:event_showInitialSummaryTblActionPerformed

    private void showDataNavigatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDataNavigatorActionPerformed
        try {
            initializeModel(buildTableSQLWhere());
            if (summaryTable.getModel().getColumnCount() == 3) {
                setNullsBlankSummaryTable();
            }
            buildSummaryTableDataBarChartExplorerPanel();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        setExplorerMain();
        setExplorerChartCard();
        tableInUse = NBSUMMARYDATATABLE;

        tableNameTableLastSelectedRow = -1;
        columnNameTableLastSelectedRow = -1;

    }//GEN-LAST:event_showDataNavigatorActionPerformed

    private void showDataNavigatorAllTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDataNavigatorAllTablesActionPerformed
        try {
            initializeModel();
            setInitialSummaryTable();
            setNullsBlankSummaryTable();
            buildSummaryTableDataBarChartExplorerPanel();

        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        setExplorerMain();
        setExplorerChartCard();
        tableInUse = NBSUMMARYDATATABLE;
        tableNameTableLastSelectedRow = -1;
        columnNameTableLastSelectedRow = -1;

    }//GEN-LAST:event_showDataNavigatorAllTablesActionPerformed

    private void showNBSummaryTblAllTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showNBSummaryTblAllTablesActionPerformed

        try {
            setInitialSummaryTable();
            setNullsBlankSummaryTable();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        setInitialSummaryCard();
        tableInUse = NBSUMMARYDATATABLE;
        initialSummaryTableFilter.setText(null);

    }//GEN-LAST:event_showNBSummaryTblAllTablesActionPerformed

    private void showRowsNullsBlanksPerColumnTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showRowsNullsBlanksPerColumnTableActionPerformed

        try {
            setRowsNullsBlanksPerColumnTable();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        setDetailAnalysisCard();
        buildRowsColumnNullsPieChartExplorerPanel("null");
        buildRowsColumnNullsPieChartExplorerPanel("blank");
        tableInUse = ROWNBSUMMARYDATATABLE;

    }//GEN-LAST:event_showRowsNullsBlanksPerColumnTableActionPerformed

    private void showSummaryChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showSummaryChartActionPerformed
        if (cardViewInUse.equals("initialSummaryCard")) {
            buildSummaryTableBarChartPopup();

        } else {
            buildSummaryTableDataBarChartExplorerPanel();
            setExplorerChartCard();
        }
    }//GEN-LAST:event_showSummaryChartActionPerformed

    private void showNBSummaryTblForSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showNBSummaryTblForSelectedActionPerformed

        try {
            // setInitialSummaryTable();
            setNullsBlankSummaryTable();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        initialSummaryTableFilter.setText(null);

    }//GEN-LAST:event_showNBSummaryTblForSelectedActionPerformed

    private void showTableColumnChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTableColumnChartActionPerformed

        //buildTableColumnSummary();
        //buildTableColumnBarChartExplorerPanel();
        //setExplorerChartCard();
//      System.out.println("GOT HERE build now chart dataset");
        // createTableColumnBarChartDatSetFromDetailAnalysisTable();
        buildTableColumnBarChartBarChartPopup();


    }//GEN-LAST:event_showTableColumnChartActionPerformed

    private void showRowsColumnNullsPieChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showRowsColumnNullsPieChartActionPerformed
        buildRowsColumnNullsPieChartExplorerPanel("null");
        setExplorerChartCard();
    }//GEN-LAST:event_showRowsColumnNullsPieChartActionPerformed

    private void showTableColumnSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTableColumnSummaryActionPerformed
        try {
            //dataTable.setModel(getSummaryTableColumnTableModel());
            setTableColumnSummaryTable();
            tableInUse = COLUMNNBSUMMARYDATATABLE;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        setDetailAnalysisCard();
    }//GEN-LAST:event_showTableColumnSummaryActionPerformed

    private void dataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTableMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            removeTableMenuPopupItems();

            // tablePopupMenu.add(showSummaryChart);
            if (tableInUse.equals(SQLDATATABLE)) {
                tablePopupMenu.add(exportDataTableSQL);
            }
            tablePopupMenu.show(dataTable, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_dataTableMouseClicked

    private void showRowsColumnBlanksPieChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showRowsColumnBlanksPieChartActionPerformed
        buildRowsColumnNullsPieChartExplorerPanel("blank");
        setExplorerChartCard();
    }//GEN-LAST:event_showRowsColumnBlanksPieChartActionPerformed

    private void colNmScrollPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colNmScrollPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_colNmScrollPanelMouseClicked

    private void exportDataTableSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportDataTableSQLActionPerformed
        if (tableInUse.equals(SQLDATATABLE)) {
            try {
                JOptionPane.showMessageDialog(null, getSQLcsv());
            } catch (SQLException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_exportDataTableSQLActionPerformed

    private void exportTableColumnsSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportTableColumnsSummaryActionPerformed
        if (tableInUse.equals(COLUMNNBSUMMARYDATATABLE)) {
            try {
                JOptionPane.showMessageDialog(null, writeCSVfile(dataTable, TABLECOLUMNSUMMARY));
            } catch (IOException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_exportTableColumnsSummaryActionPerformed

    private void exportRowsColumnsNullsBlanksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportRowsColumnsNullsBlanksActionPerformed
        if (tableInUse.equals(ROWNBSUMMARYDATATABLE)) {
            try {
                JOptionPane.showMessageDialog(null, writeCSVfile(dataTable, ROWSCOLUMNSUMMARY));
            } catch (IOException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_exportRowsColumnsNullsBlanksActionPerformed

    private void exportNBSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportNBSummaryActionPerformed
        if (tableInUse.equals(NBSUMMARYDATATABLE)) {
            try {
                JOptionPane.showMessageDialog(null, writeCSVfile(summaryTable, NBCOLUMNSUMMARY));
            } catch (IOException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_exportNBSummaryActionPerformed

    private void exportInitialTableSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportInitialTableSummaryActionPerformed
        if (tableInUse.equals(INITIALSUMMARYDATATABLE)) {
            try {
                JOptionPane.showMessageDialog(null, writeCSVfile(summaryTable, INITIALTABLESUMMARY));
            } catch (IOException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_exportInitialTableSummaryActionPerformed

    private void limitRowsFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limitRowsFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_limitRowsFilterActionPerformed

    private void limitRowsFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_limitRowsFilterKeyReleased
        try {
            if (Integer.parseInt(limitRowsFilter.getText()) == (int) Integer.parseInt(limitRowsFilter.getText())) {
                limitRowsFilterInt = Integer.parseInt(limitRowsFilter.getText());
                if (limitRowsFilterInt > 0) {
                    limitRows = " limit " + limitRowsFilterInt;
                } else {
                    limitRows = "";
                }
            }
        } catch (Exception e) {

            if (!limitRowsFilter.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "You did not enter a number. \n Data records will not be limited.");
            }
            limitRowsFilter.setText(null);
            limitRowsFilterInt = 0;
            limitRows = "";

        }

        if (tableInUse.equals(SQLDATATABLE)) {
            try {
                setDataTable(getColumnData());
            } catch (SQLException ex) {
                Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_limitRowsFilterKeyReleased

    private void detailAnalysisTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailAnalysisTableMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            removeTableMenuPopupItems();

            if (tableInUse.equals(COLUMNNBSUMMARYDATATABLE)) {
                tablePopupMenu.add(showTableColumnChart);
                tablePopupMenu.add(exportTableColumnsSummary);

                if (columnNameTable.getRowCount() != detailAnalysisTable.getModel().getRowCount()) {
                    tablePopupMenu.add(showTableAllColumnSummary);
                }

                if (detailAnalysisTable.getSelectedRowCount() > 0 && detailAnalysisTable.getModel().getRowCount() != detailAnalysisTable.getSelectedRowCount()) {
                    tablePopupMenu.add(showTableColumnChartSelectedRows);
                }

            }

            if (tableInUse.equals(ROWNBSUMMARYDATATABLE)) {
                tablePopupMenu.add(showRowsColumnNullsPieChart);
                tablePopupMenu.add(showRowsColumnBlanksPieChart);

                tablePopupMenu.add(exportRowsColumnsNullsBlanks);
            }

            tablePopupMenu.show(detailAnalysisTable, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_detailAnalysisTableMouseClicked

    private void detailAnalysisFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_detailAnalysisFilterKeyReleased

        setJTableColOneFilter(detailAnalysisTable, detailAnalysisFilter);    }//GEN-LAST:event_detailAnalysisFilterKeyReleased

    private void detailAnalysisFilterPanelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_detailAnalysisFilterPanelKeyReleased

    }//GEN-LAST:event_detailAnalysisFilterPanelKeyReleased

    private void showColumnDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showColumnDataActionPerformed

        setExplorerTableCard();

    }//GEN-LAST:event_showColumnDataActionPerformed

    private void showTableAllColumnSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTableAllColumnSummaryActionPerformed
        detailAnalysisTable.setModel(summaryTableColumnTableModel);
        detailAnalysisTable.validate();
    }//GEN-LAST:event_showTableAllColumnSummaryActionPerformed

    private void showTableColumnChartSelectedRowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTableColumnChartSelectedRowsActionPerformed

        createTableColumnBarChartDatSetFromSelectedTableRows();


    }//GEN-LAST:event_showTableColumnChartSelectedRowsActionPerformed
    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    main(String args[])
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * main(String args[]) is the main method
     *
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

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getSummaryChartDataset()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * Get the dataset built for the Summary Chart based on the data in the
     * summary table model currently generated.
     *
     * @return DefaultCategoryDataset
     */
    public DefaultCategoryDataset getSummaryChartDataset() {
        return summaryChartDataset;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getTableColumnBarChartDataset()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * Get the dataset built for the Summary Chart based on the data in the
     * summary table model currently generated.
     *
     * @return getTableColumnBarChartDataset()
     */
    public DefaultCategoryDataset getTableColumnBarChartDataset() {
        return tableColumnBarChartDataset;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    ResultTableModel extends DefaultTableModel
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * ResultTableModel extends DefaultTableModel to allow a table model to be
     * easily created from a SQL ResultSet
     */
    public class ResultTableModel extends DefaultTableModel {

        private ResultSet resultset;
        private int sqlRowCount;

        /*
        getColumnClass(int columnIndex) determine the object class of the field
        value and return the class type to the table model
         */
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Class columnClass = getValueAt(0, columnIndex).getClass();
            return columnClass;
        }

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
                resultset.absolute(row + 1); //To change body of generated methods, choose Tools | Templates.
                Object resp = resultset.getObject(column + 1);
                if (resp == null) {
                    return "[null]";
                }
                if (resp.equals("")) {
                    return "[blank]";
                }
                return resp;

            } catch (SQLException e) {
                // this is being handled. Not an issue.
                // we should send this to the log.
                //e.printStackTrace();
                System.err.println(e.getMessage());
            }
            return "";
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
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

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    ArrayTableModel extends the DefaultTableModel
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * ArrayTableModel extends the DefaultTableModel to implement getColumnClass
     * which allows the data model to be aware of the class or data type of the
     * data in each column.
     */
    public class ArrayTableModel extends DefaultTableModel {

        public Class<?> getColumnClass(int columnIndex) {
            Class columnClass = getValueAt(0, columnIndex).getClass();
            return columnClass;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getRowsColOneSelectedArray(JTable jTable)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * getRowsColOneSelectedArray(JTable jTable) returns an Array of values of
     * the first column of the selected rows in the table selected.
     *
     * @param jTable
     * @return ArrayList<String>
     */
    public ArrayList<String> getRowsColOneSelectedArray(JTable jTable) {
        ArrayList<String> rowsColOneSelected = new ArrayList<>();
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

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getColumnNamesForDataTable(JTable jTable)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * getColumnNamesForDataTable(JTable jTable) returns am vector of values
     * that is used to specify the column names of the table model of the
     * dataTable JTable. The contents of the vector depends on what rows the
     * user have selected in the columnNameTable, if any. If the user click on a
     * cell to change a filter the column name selection should not change.If
     * the user did not choose specific columns before selecting filters (see
     * getColumnData()), then all the columns for the "selected" table is used
     * to build the dataTable with.
     *
     * @param jTable
     * @return
     */
    public Vector getColumnNamesForDataTable(JTable jTable) throws SQLException {

        if (dynamicSelectFrom.length() > 5 && columnNameTableSelctionColumn == 0) {

            columnNamesSelectedInColumnNameTable.removeAll(columnNamesSelectedInColumnNameTable);

            try {
                int[] rows = jTable.getSelectedRows();
                for (int i = 0; i < rows.length; i++) {
                    columnNamesSelectedInColumnNameTable.add(jTable.getValueAt(rows[i], 0).toString());
                }
            } catch (Exception e) {
                columnNamesSelectedInColumnNameTable = null;
            }
            return columnNamesSelectedInColumnNameTable;

        } else if (dynamicSelectFrom.length() > 5 && columnNameTableSelctionColumn != 0) {
            return columnNamesSelectedInColumnNameTable;
        } else {
            columnNamesSelectedInColumnNameTable.removeAll(columnNamesSelectedInColumnNameTable);
            int colcount = db.getColCount(getRowColOneSelected(tableNameTable));
            ResultSet rs = db.getColumnNames(getRowColOneSelected(tableNameTable));
            rs.first();
            for (int rsi = 0; rsi < colcount; rsi++) {
                columnNamesSelectedInColumnNameTable.add(rs.getObject(1).toString());
                rs.next();
            }
            return columnNamesSelectedInColumnNameTable;
        }
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getRowColOneSelected(JTable jTable)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * getRowColOneSelected(JTable jTable) returns the value of the first column
     * of the selected row in the table selected in the view.
     *
     * @param jTable
     * @return String
     */
    public String getRowColOneSelected(JTable jTable) {
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

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getColumnData()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * getColumnData() gets the data from the database for the column names
     * selected in the columnNamesTable in the data explorer view.
     *
     * @return
     * @throws SQLException
     */
    public ResultSet getColumnData() throws SQLException {

        String query = "";
        String queryCSVSelect = "";
        String queryCSVFrom = "";
        String queryFilterCSVSelect = "";
        String queryFilterCSVFrom = "";
        String csvColumnNames = "";
        String filterCSVColumnNames = "";
        // keeps track of the number of rows the dynamic query has
        dynamic_query_rowcount = "select count(*) from " + getRowColOneSelected(tableNameTable) + " where 1=1";

        if (columnNameTableSelctionColumn == 0) {
            buildColumnDataSelectOnColumnNameSelect();
            query = dynamicSelectFrom;

            queryCSVSelect = dynamicCSVSelect;
            queryCSVFrom = dynamicCSVFrom;
            csvColumnNames = dynamicCSVColumnNames;

        }
        // check which part of the columnNameTable the user is interacting with
        // and if a previous column selection has been made for the database
        // table that is currently being explored.

        if (columnNameTableSelctionColumn != 0 && dynamicSelectFrom.length() < 5) {

            buildColumnDataSelectOnFilterSelect();
            query = dynamicFilterSelectFrom;

            queryCSVSelect = dynamicFilterCSVSelect;
            queryCSVFrom = dynamicFilterCSVFrom;
            csvColumnNames = dynamicFilterCSVColumnNames;

        } else {
            query = dynamicSelectFrom;

            queryCSVSelect = dynamicCSVSelect;
            queryCSVFrom = dynamicCSVFrom;
            csvColumnNames = dynamicCSVColumnNames;

        }

        buildColumnDataSQLWhere();

        query = query + dynamicSQLWhere + limitRows;
        queryCSVFrom = queryCSVFrom + dynamicSQLWhere + limitRows;

        dynamic_query_rowcount = dynamic_query_rowcount + dynamicSQLWhere;

        Statement statement = db.conn.createStatement();

        // Gets and sets the row count of the selected Query. this is to allow the table 
        // model to show the correct number or rows. 
        ResultSet getColDataRowCount = statement.executeQuery(dynamic_query_rowcount);
        getColDataRowCount.first();
        dynamic_rowcount = Integer.parseInt(getColDataRowCount.getObject(1).toString());

        if (dynamic_rowcount > limitRowsFilterInt && limitRowsFilterInt != 0) {
            dynamic_rowcount = limitRowsFilterInt;
        }
        ResultSet getColData = statement.executeQuery(query);

        lastSQLCSVColumnNames = csvColumnNames;
        lastSQLQueryCSVSelect = queryCSVSelect;
        lastSQLQueryCSVFrom = queryCSVFrom;

        getColData.first();
        return getColData;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    setColumnNameTable()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * setColumnNameTable() set the table model for the columnNameTable in the
     * data explorer view.
     */
    public void setColumnNameTable() {
        ResultSet columns = null;
        try {
            columns = db.getColumnNames(getRowColOneSelected(tableNameTable));

        } catch (SQLException ex) {

            Logger.getLogger(MySQLNullsApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        columnNameTable.setModel(db.resultSetToColumnNameTableModel(columns));
        columnNameTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        columnNameTable.getColumnModel().getColumn(0).setMinWidth(90);
        columnNameTable.getColumnModel().getColumn(0).setMaxWidth(260);
        // columnNameTable.getColumnModel().getColumn(1).setMinWidth(15);
        columnNameTable.getColumnModel().getColumn(1).setPreferredWidth(35);
        columnNameTable.getColumnModel().getColumn(1).setMaxWidth(35);
        // columnNameTable.getColumnModel().getColumn(2).setMinWidth(15);
        columnNameTable.getColumnModel().getColumn(2).setPreferredWidth(45);
        columnNameTable.getColumnModel().getColumn(2).setMaxWidth(45);
        // columnNameTable.getColumnModel().getColumn(3).setMinWidth(10);
        columnNameTable.getColumnModel().getColumn(3).setPreferredWidth(1);
        columnNameTable.getColumnModel().getColumn(3).setMaxWidth(Integer.MAX_VALUE);

        columnNameTable.getTableHeader().setReorderingAllowed(false);

        setTableRowSorter(columnNameTable);
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    setInitialSummaryTable()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * setInitialSummaryTable() sets the table model for the Initial Summary
     * Table.
     *
     * @throws SQLException
     */
    public void setInitialSummaryTable() throws SQLException {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        ResultSet rs = db.analyseTables();
        int rowCount = db.getNumberOfTables();

        Object[] columns = {"Table Name", "Column Count", "Row Count"};
        ResultTableModel summaryTableModel = new ResultTableModel();
        summaryTableModel.setResultset(rs);

        summaryTableModel.setColumnIdentifiers(columns);
        summaryTableModel.setsqlRowCount(rowCount);

        summaryTable.setModel(summaryTableModel);
        summaryTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        ((DefaultTableCellRenderer) summaryTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);

        summaryTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        summaryTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        summaryTable.getTableHeader().setReorderingAllowed(false);

        summaryTable.setAutoCreateRowSorter(true);

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    setNullsBlankSummaryTable()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * setNullsBlankSummaryTable() sets the table model for the Nulls Blank
     * Summary Table.
     *
     * @throws SQLException
     */
    public void setNullsBlankSummaryTable() throws SQLException {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        Object[][] tableNullsBlankSummaryArray = buildArrayNullsBlankSummary();

        Object[] columns = {"Table Name", "Column Count", "Row Count", "Table Nulls", "% Nulls", "Table Blanks", "% Blanks"};
        ArrayTableModel summaryNullsBlankTableModel = new ArrayTableModel();
        //DefaultTableModel summaryNullsBlankTableModel = new DefaultTableModel(tableNullsBlankSummaryArray, columns);
        summaryNullsBlankTableModel.setDataVector(tableNullsBlankSummaryArray, columns);
        summaryTable.setModel(summaryNullsBlankTableModel);

        summaryTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        summaryTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        summaryTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        summaryTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        summaryTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        summaryTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        setTableRowSorter(summaryTable);
    }

    public void setRowsNullsBlanksPerColumnTable() throws SQLException {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setVerticalAlignment(SwingConstants.CENTER);

        Object[][] rowsNulsBlankArray = buildRowsNullsBlanksPerColumnArray();

        Object[] columns = {"<html><center>Number of Columns Affected<center><br></html>",
            "<html><center>Number of Rows Null for Affected Columns<center><br></html>",
            "<html><center>% of Rows Null for Affected Columns<center><br></html>",
            "<html><center>Number of Rows Blank for Affected Columns<center><br></html>",
            "<html><center>% of Rows Blank for Affected Columns<center><br></html>"
        };

        //DefaultTableModel summaryNullsBlankTableModel = new DefaultTableModel(tableNullsBlankSummaryArray, columns);
        rowsNulsBlankArrayTableModel.setDataVector(rowsNulsBlankArray, columns);
        detailAnalysisTable.setModel(rowsNulsBlankArrayTableModel);
        detailAnalysisTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        detailAnalysisTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        detailAnalysisTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        detailAnalysisTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        detailAnalysisTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        setTableRowSorter(detailAnalysisTable);

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    setDataTable(ResultSet data)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * setDataTable(ResultSet data) sets the data for the data view table in the
     * data navigation view. The data is the result set of select (*) from the
     * selected table columns in the tableNameTable and columnNameTable. Thus,
     * the user selects a table, then columns and then data for those columns
     * are displayed.
     *
     * @param data
     * @throws SQLException
     */
    public void setDataTable(ResultSet data) throws SQLException {

        ResultTableModel dataTableModel = new ResultTableModel();

        dataTableModel.setColumnIdentifiers(getColumnNamesForDataTable(columnNameTable));
        dataTableModel.setsqlRowCount(dynamic_rowcount);
        dataTableModel.setResultset(data);

        dataTable.setModel(dataTableModel);
        dataTable.setAutoCreateRowSorter(true);

        /*
        TableModel model = db.resultSetToTableModel(data);
        dataTable.setModel(model);
        setTableRowSorter(dataTable);
         */
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    setTableRowSorter(JTable table)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * Creates a table row sorter to allow JTABLE to be sorted and filtered.
     *
     * @param table
     * @return TableRowSorter
     */
    public TableRowSorter<TableModel> setTableRowSorter(JTable table) {
        TableRowSorter<TableModel> sorter
                = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        return sorter;

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    setJTableColOneFilter(JTable table_name, JTextField textFilter)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * setJTableColOneFilter(JTable table_name, JTextField textFilter) determine
     * if text has been entered in a text field for filtering table data. if
     * there are
     *
     * @param table_name
     * @param textFilter
     */
    public void setJTableColOneFilter(JTable table_name, JTextField textFilter) {
        TableRowSorter<TableModel> sorter = setTableRowSorter(table_name);

        String textEntered = textFilter.getText();
        if (textEntered.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(textEntered));
        }

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    showConnectionDialog()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * Shows the dialog box to enter database server parameters to allow the
     * application to connect to a database.
     *
     *
     */
    public void showConnectionDialog() {

        DBConnectDialog dbconnectdialog = new DBConnectDialog();
        dbconnectdialog.setModal(true);
        dbconnectdialog.setVisible(true);
        db = dbconnectdialog.getDb();

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    initializeModel()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * initializeModel() populates the table with database table names in the
     * data explorer view. Since it does NOT take a SQLwhere clause it populates
     * the tableNameTable with all the table names for a database from the
     * information schema database.
     *
     * @throws SQLException
     */
    public void initializeModel() throws SQLException {
        /*
            @todo makes this threadsafe
         */

        ResultSet tbls = db.getTableNames();
        int rowCount = db.getNumberOfTables();

        Object[] columns = {"Tables"};
        ResultTableModel tableNameTableModel = new ResultTableModel();
        tableNameTableModel.setResultset(tbls);

        tableNameTableModel.setColumnIdentifiers(columns);
        tableNameTableModel.setsqlRowCount(rowCount);

        tableNameTable.setModel(tableNameTableModel);
        tableNameTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ((DefaultTableCellRenderer) tableNameTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);

        tableNameTable.setAutoCreateRowSorter(true);

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    initializeModel(String whereSQL)
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * initializeModel(String whereSQL) populates the table with database table
     * names in the data explorer view. It takes a SQLWhere clause as a
     * parameter to limit the number of tables displayed. This method is called
     * from the summary tables which and the SQLwhere allows only the tables
     * that was selected in the summary tables to be displayed in the
     * tableNameTable.
     *
     * @param whereSQL
     * @throws SQLException
     */
    public void initializeModel(String whereSQL) throws SQLException {
        /*
            @todo makes this threadsafe
         */

        ResultSet tbls = db.getTableNames(whereSQL);
        int rowCount = db.getNumberOfTables();

        Object[] columns = {"Tables"};
        ResultTableModel tableNameTableModel = new ResultTableModel();
        tableNameTableModel.setResultset(tbls);

        tableNameTableModel.setColumnIdentifiers(columns);
        tableNameTableModel.setsqlRowCount(rowCount);

        tableNameTable.setModel(tableNameTableModel);
        tableNameTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ((DefaultTableCellRenderer) tableNameTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);

        tableNameTable.setAutoCreateRowSorter(true);
        /*
        ResultSet tbls = db.getTableNames(whereSQL);
        tableNameTable.setModel(db.resultSetToTableModel(tbls));
        setTableRowSorter(tableNameTable);
        tableNameTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //TableModel tableNameTableModel =tableNameTable.getModel();
         */
        //tableNameTable.S; 

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getTableSQLWhereRecordCount()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * buildTableSQLWhere() builds a where clause to restrict the table
     * selection to only those selected in the table window
     *
     * @return
     */
    public String buildTableSQLWhere() {

        ArrayList<String> selectedTables = getRowsColOneSelectedArray(summaryTable);
        String sqlWhere = "";
        // If it is the initial Summary Table Model get the selected tables
        // or all tables in database if none are selected.
        TableModel tableModel = summaryTable.getModel();
        if (tableModel.getColumnCount() == 3) {

            if (selectedTables.isEmpty()) {
                sqlWhere = " and 1=1 ";
            } else {
                sqlWhere = "and table_name in (";
                for (int i = 0; i < selectedTables.size(); i++) {
                    sqlWhere += "'" + selectedTables.get(i).toString() + "'";
                    if (i == selectedTables.size() - 1) {
                        sqlWhere += ")";
                    } else {
                        sqlWhere += ",";
                    }
                }
            }
        }

        // if it is the Nulls Blank Summary Table get the selected tables
        // else only the tables in the Nulls Blank table model
        if (tableModel.getColumnCount() == 7) {

            if (selectedTables.isEmpty()) {
                sqlWhere = "and table_name in (";
                int tableCount = tableModel.getRowCount();
                for (int tc = 0; tc < tableCount; tc++) {
                    sqlWhere += "'" + tableModel.getValueAt((tc), (0)) + "'";
                    if (tc == tableCount - 1) {
                        sqlWhere += ")";
                    } else {
                        sqlWhere += ",";
                    }
                }

            } else {
                sqlWhere = "and table_name in (";
                for (int i = 0; i < selectedTables.size(); i++) {
                    sqlWhere += "'" + selectedTables.get(i).toString() + "'";
                    if (i == selectedTables.size() - 1) {
                        sqlWhere += ")";
                    } else {
                        sqlWhere += ",";
                    }
                }
            }
        }
        return sqlWhere;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getTableSQLWhereRecordCount()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * getTableSQLWhereRecordCount() determine how many records have been
     * selected in the summary table. if none are selected a count of all the
     * tables in the database is returned.
     *
     * @return
     */
    public int getTableSQLWhereRecordCount() {
        int selectedArrayCount;
        ArrayList<String> selectedTables = getRowsColOneSelectedArray(summaryTable);
        if (selectedTables.isEmpty()) {
            selectedArrayCount = db.getNumberOfTables();

        } else {
            selectedArrayCount = selectedTables.size();
        }
        return selectedArrayCount;

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    buildColumnDataSQLWhere()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * buildColumnDataSQLWhere() builds the "where" part of the SQL query to
     * retrieve data from the database for the column names selected in the
     * "column names navigator" table. See
     * buildColumnDataSelectOnColumnNameSelect().
     */
    public void buildColumnDataSQLWhere() {

        TableModel columnNames = columnNameTable.getModel();
        String sqlWhere = "";
        for (int i = 0; i < columnNames.getRowCount(); i++) {
            if (columnNames.getValueAt(i, 1).equals(true)) {
                sqlWhere += " and " + columnNames.getValueAt(i, 0) + " is null";
            }
            if (columnNames.getValueAt(i, 2).equals(true)) {
                sqlWhere += " and " + columnNames.getValueAt(i, 0) + " =''";
            }
            if (columnNames.getValueAt(i, 3) != null) {
                sqlWhere += " and " + columnNames.getValueAt(i, 0) + " like '%" + columnNames.getValueAt(i, 3) + "%'";
            }
        }
        dynamicSQLWhere = sqlWhere + "";

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    buildColumnDataSelectOnColumnNameSelect()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * buildColumnDataSelectOnColumnNameSelect() builds the "select column" part
     * of the SQL query to retrieve data from the database for the column names
     * selected in the "column names navigator" table. See
     * buildColumnDataSQLWhere()
     */
    public void buildColumnDataSelectOnColumnNameSelect() {
        ArrayList<String> columns_selected;

        String table_name = (getRowColOneSelected(tableNameTable));
        columns_selected = getRowsColOneSelectedArray(columnNameTable);

        String query = "select ";
        String csvColumnNames = "select '";

        for (int i = 0; i < columns_selected.size(); i++) {
            query += columns_selected.get(i).toString();
            csvColumnNames += columns_selected.get(i).toString();
            if (i == columns_selected.size() - 1) {
                query += "";
                csvColumnNames += "'";

            } else {
                query += ",";
                csvColumnNames += "','";
            }
        }
        dynamicSelectFrom = query + " from " + table_name + " where 1=1 ";

        dynamicCSVColumnNames = csvColumnNames;
        dynamicCSVSelect = query + " into OUTFILE '" + table_name + "_";
        dynamicCSVFrom = ".csv' FIELDS TERMINATED BY " + csvDelimiter + " OPTIONALLY ENCLOSED BY '\"' ESCAPED BY '\\\\'   from " + table_name + " where 1=1 ";
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    buildColumnDataSelectOnFilterSelect()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * buildColumnDataSelectOnFilterSelect() build a dynamic select list in case
     * the user did not make column selection before starting to apply data
     * filters.
     *
     * @throws SQLException
     */
    public void buildColumnDataSelectOnFilterSelect() throws SQLException {

        ResultSet columns = null;

        String table_name = (getRowColOneSelected(tableNameTable));
        int colCount = db.getColCount(table_name);
        columns = db.getColumnNames(table_name);
        columns.first();

        String query = "select ";
        String csvColumnNames = "select '";

        for (int i = 0; i < colCount; i++) {
            query += columns.getObject(1).toString();
            csvColumnNames += columns.getObject(1).toString();
            if (i == colCount - 1) {
                query += "";
                csvColumnNames += "'";
            } else {
                query += ",";
                csvColumnNames += "','";
            }
            columns.next();
        }

        dynamicFilterSelectFrom = query + " from " + table_name + " where 1=1 ";

        dynamicFilterCSVColumnNames = csvColumnNames;
        dynamicFilterCSVSelect = query + " into OUTFILE '" + table_name + "_";
        dynamicFilterCSVFrom = ".csv' FIELDS TERMINATED BY " + csvDelimiter + " OPTIONALLY ENCLOSED BY '\"' ESCAPED BY '\\\\'   from " + table_name + " where 1=1 ";

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    buildArrayNullsBlankSummary()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * buildArrayNullsBlankSummary() builds an array with which to create a
     * table model for the summary of database tables showing their percentage
     * nulls and blanks
     *
     *
     * @return
     * @throws SQLException
     */
    public Object[][] buildArrayNullsBlankSummary() throws SQLException {

        summaryChartDataset.clear();

        int ArrayrowCount = getTableSQLWhereRecordCount();

        int ArraycolCount = 7;

        Object[][] tableNullBlankSummaryArray = new Object[ArrayrowCount][ArraycolCount];
        ResultSet tableNames = db.getTableNames(buildTableSQLWhere());
        tableNames.first();
        String tableName;
        double columnCount;
        double rowCount;
        double tableNulls;
        double tableBlanks;
        double totalfields;
        double percentageTableNulls;
        double percentageTableBlanks;
        double zeroValue = 0;
        double hundredValue = 100;
        DecimalFormat to2DP = new DecimalFormat("0.00");
        //  to2DP.format(balance) 

        int count = 0;
        for (int i = 0; i < db.getNumberOfTables(); i++) {

            tableName = tableNames.getString(1);

            columnCount = db.getColCount(tableName);
            rowCount = db.getRowCount(tableName);
            totalfields = columnCount * rowCount * 1.0f;
            zeroValue = 0;

            db.transPoseNb(tableName);
            tableNulls = db.getNullTableCount(tableName);
            tableBlanks = db.getBlankTableCount(tableName);

            if (totalfields == zeroValue) {

                percentageTableNulls = 0;
                percentageTableBlanks = 0;
            } else {
                percentageTableNulls = (tableNulls / totalfields) * hundredValue;
                percentageTableBlanks = (tableBlanks / totalfields) * hundredValue;
            }
            // Building the array from which to create the table model    
            tableNullBlankSummaryArray[count] = new Object[]{tableName, (int) columnCount, (int) rowCount, (int) tableNulls, to2DP.format(percentageTableNulls), (int) tableBlanks, to2DP.format(percentageTableBlanks)};

            // Adding data to the Chart Data Object
            if (percentageTableNulls > 0) {
                summaryChartDataset.addValue(percentageTableNulls, "Nulls %", tableName);
            }
            if (percentageTableBlanks > 0) {
                summaryChartDataset.addValue(percentageTableBlanks, "Blanks %", tableName);
            }

            tableNames.next();
            count++;

        }

        return tableNullBlankSummaryArray;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    buildArrayNullsBlankSummary()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * this method clears variables that are used to keep track of columns
     * selected, and where clauses specified as the user interact with the
     * columnNameTable to build a query for the dataTable to view data.
     *
     */
    public void resetDynamicVariables() {
        dynamic_query_rowcount = "";
        dynamic_rowcount = 0;
        dynamicSelectFrom = "";
        dynamicSQLWhere = "";
        dynamicFilterSelectFrom = "";
        columnNameTableSelctionColumn = -1;
        ;
        dataTable.setModel(new DefaultTableModel());

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    buildTableColumnSummary() 
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * buildTableColumnSummary() builds an array from which to create a table
     * model for a summary of null and blank values for a specified table.
     *
     * @return Object[][]
     * @throws SQLException
     */
    public Object[][] buildTableColumnSummary() throws SQLException {
        tableColumnBarChartDataset.clear();

        String tableName = getRowColOneSelected(tableNameTable);

        int ArrayrowCount = db.getColCount(tableName);
        int ArraycolCount = 6;
                Object[][] tableColumnSummaryArray = new Object[ArrayrowCount][ArraycolCount];        
        
        
      
                
        ResultSet columnNames = db.getColumnNames(tableName);
        columnNames.first();
        String columnName;
        double rowCount;
        double columnNulls;
        double columnBlanks;
        double percentageColumnNulls;
        double percentageColumnBlanks;
        double zeroValue = 0;
        double hundredValue = 100;
        double totalfields;
        DecimalFormat to2DP = new DecimalFormat("0.00");
        //  to2DP.format(balance) 
        
                int count = 0;
        for (int i = 0; i < ArrayrowCount; i++) {
            columnName = columnNames.getString(1);
            rowCount = db.getRowCount(tableName);
            /*
             for ultimate performance  db.transPoseNb(tableName) should only be 
            called once in the  sequencing of the program, but for now, since 
            the data sets are small it is called every time.
             IF it were to be called only once, the user should be forced to 
             use it only after the nulls blank summary has been performed on a 
             table.
             */
            db.transPoseNb(tableName);
            columnNulls = db.getColNullCount(tableName, columnName);
            columnBlanks = db.getColBlankCount(tableName, columnName);
            totalfields = rowCount * 1.0f;

            if (totalfields == zeroValue) {

                percentageColumnNulls = 0;
                percentageColumnBlanks = 0;
            } else {
                percentageColumnNulls = (columnNulls / totalfields) * hundredValue;
                percentageColumnBlanks = (columnBlanks / totalfields) * hundredValue;
            }

            tableColumnSummaryArray[count] = new Object[]{columnName, (int) rowCount, (int) columnNulls, (int) columnBlanks, to2DP.format(percentageColumnNulls), to2DP.format(percentageColumnBlanks)};

            // Adding data to the Chart Data Object
            if (percentageColumnNulls > 0) {
                tableColumnBarChartDataset.addValue(percentageColumnNulls, "Nulls %", columnName);
            }
            if (percentageColumnBlanks > 0) {
                tableColumnBarChartDataset.addValue(percentageColumnBlanks, "Blanks %", columnName);
            }

            columnNames.next();
            count++;

        }
           
        return tableColumnSummaryArray;

    }

    public void createTableColumnBarChartDatSetFromSelectedTableRows() {

        TableModel tableModel = detailAnalysisTable.getModel();
        tableColumnBarChartDataset.clear();

        int[] selectedRows = detailAnalysisTable.getSelectedRows();
        for (int targetRow = 0; targetRow < selectedRows.length; targetRow++) {
            int row = selectedRows[targetRow];
            int modelRow = detailAnalysisTable.convertRowIndexToModel(row);

            System.out.println("row " + row + " modelRow \n" + modelRow);
            System.out.println(" row " + Double.parseDouble(tableModel.getValueAt(row, 4).toString()));
            System.out.println(" modelRow " + Double.parseDouble(tableModel.getValueAt(modelRow, 4).toString()) + "\n");

            tableColumnBarChartDataset.addValue(Double.parseDouble(tableModel.getValueAt(modelRow, 4).toString()), "Nulls %", tableModel.getValueAt(modelRow, 0).toString());
            tableColumnBarChartDataset.addValue(Double.parseDouble(tableModel.getValueAt(modelRow, 5).toString()), "Blanks %", tableModel.getValueAt(modelRow, 0).toString());

        }
    }

    public void createTableColumnBarChartDatSetFromDetailAnalysisTable() {
        DefaultTableModel tableModel = (DefaultTableModel) detailAnalysisTable.getModel();
        tableColumnBarChartDataset.clear();
        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tableColumnBarChartDataset.addValue(Double.parseDouble(tableModel.getValueAt(i, 4).toString()), "Nulls %", tableModel.getValueAt(i, 0).toString());
            tableColumnBarChartDataset.addValue(Double.parseDouble(tableModel.getValueAt(i, 5).toString()), "Blanks %", tableModel.getValueAt(i, 0).toString());
        }
    }

    /*    
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    setTableColumnSummaryTable()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * setTableColumnSummaryTable() sets the table model for dataTable to
     * display a summary of NULLS and BLANKS by columns for a selected table.
     *
     * @throws SQLException
     */
    public void setTableColumnSummaryTable() throws SQLException {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        JDialog progressBarDialog  = buildProgressBar("Analysing Table");
        
                SwingWorker worker = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Object[][] tableColumnSummaryArray = buildTableColumnSummary();

 return null;    
            }
            @Override
            protected void done(){
                progressBarDialog.setVisible(false); 
            }
        };
        
          worker.execute();
        

        

        summaryTableColumnTableModel.setDataVector(tableColumnSummaryArray, columnsTableColumnSummary);
        detailAnalysisTable.setModel(summaryTableColumnTableModel);

        detailAnalysisTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        detailAnalysisTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        detailAnalysisTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        detailAnalysisTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        detailAnalysisTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        setTableRowSorter(detailAnalysisTable);
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    buildRowsNullsBlanksPerColumnArray() 
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * buildRowsNullsBlanksPerColumnArray() first builds an array that holds a
     * pair of values for each field to determine if the field contain a null or
     * a blank value. (1 being true and zero false). Then the method determine
     * which percentage of all the rows has which number or columns containing
     * nulls or blanks. The method returns an Object[][] from which to build a
     * data model to display the summary in a jTable.
     *
     * @return Object[][]
     * @throws SQLException
     */
    public Object[][] buildRowsNullsBlanksPerColumnArray() throws SQLException {

        rowsColsNullsPieChartData.clear();
        rowsColsBlanksPieChartData.clear();
        String tableName = getRowColOneSelected(tableNameTable);
        int tableRowCount = db.getRowCount(tableName);
        // Array list to hold 0 and 1 for each field
        // in a row for nulls and for blanks
        ArrayList<int[]> rowColNulBlankCount = new ArrayList<>();
        // Array List to hold the count of rows for each number of columns
        // with nulls or blanks
        int arrayRows = db.getColCount(tableName) + 1;
        int ArraycolCount = 5;

        Object[][] rowsNullsBlankArray = new Object[arrayRows][ArraycolCount];
        DecimalFormat to2DP = new DecimalFormat("0.00");

        ResultSet rowsNullsBlankRs = db.analyseTableRowsNullsBlanks(tableName);
        ResultSetMetaData col_meta = rowsNullsBlankRs.getMetaData();
        rowsNullsBlankRs.first();

        int col_numColumns = col_meta.getColumnCount();

        int rowNum = 1;

        // loop through every result set row
        for (int resultsetloop = 1; resultsetloop <= tableRowCount; resultsetloop++) {
            //while (rowsNullsBlankRs.next()) {
            int rowNulls = 0;
            int rowBlanks = 0;

            // loop through each column of the result set to determine if it has
            // nulls or blanks. because nulls and blanks are held in the same line 
            // of each result set the loop is progressed by 2 to jump nulls and 
            // and blanks. thus ci = nulls ci+1 = blanks
            for (int ci = 1; ci <= col_numColumns; ci += 2) {

                if (rowsNullsBlankRs.getString(ci) != null) {
                    rowNulls = rowNulls + Integer.parseInt(rowsNullsBlankRs.getString(ci));
                } else {
                    rowNulls = 0;
                }
                if (rowsNullsBlankRs.getString(ci + 1) != null) {
                    rowBlanks = rowBlanks + Integer.parseInt(rowsNullsBlankRs.getString(ci + 1));
                } else {
                    rowBlanks = 0;
                }
            }

            int[] row = new int[]{rowNum, rowNulls, rowBlanks};
            rowColNulBlankCount.add(row);

            rowNum = rowNum + 1;
            rowsNullsBlankRs.next();
        }
        // loop through column count to determine how many rows has
        // nulls and blanks columns equal to the column count

        for (int colno = 0; colno < (col_numColumns / 2) + 1; colno++) {
            int colNulls = 0;
            int colBlanks = 0;

            for (int rowarrayNo = 0; rowarrayNo < rowColNulBlankCount.size(); rowarrayNo++) {

                int[] rowFromArray = rowColNulBlankCount.get(rowarrayNo);

                if (colno == rowFromArray[1]) {
                    colNulls++;
                }
                if (colno == rowFromArray[2]) {
                    colBlanks++;
                }
            }

            String percentageRowsNull = "0.00";
            String percentageRowsBlank = "0.00";

            if (rowColNulBlankCount.size() != 0) {
                percentageRowsNull = to2DP.format((double) colNulls / (double) tableRowCount * 100);
                percentageRowsBlank = to2DP.format((double) colBlanks / (double) tableRowCount * 100);
            }

            // Array to hold the hold the number of table records with that has 
            // columns and blank column count for each count of the number of 
            // columns the table has.
            rowsNullsBlankArray[colno] = new Object[]{colno, colNulls, Double.parseDouble(percentageRowsNull), colBlanks, Double.parseDouble(percentageRowsBlank)};

            if (Double.parseDouble(percentageRowsNull) > 0) {
                rowsColsNullsPieChartData.setValue("Column Count: " + colno, Double.parseDouble(percentageRowsNull));
            }

            if (Double.parseDouble(percentageRowsBlank) > 0) {
                rowsColsBlanksPieChartData.setValue("Column Count: " + colno, Double.parseDouble(percentageRowsBlank));
            }

        }

        rowsNullsBlankRs.first();

        return rowsNullsBlankArray;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    buildSummaryTableBarChartPopup()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * buildSummaryTableBarChartPopup() creates pop-up window with a chart
     * displaying a summary of nulls and blanks for selected tables. If no
     * tables are selected that contains any nulls or blanks a message is
     * displayed informing the user that chart can not be displayed due to
     * insufficient data.
     */
    public void buildSummaryTableBarChartPopup() {
        if (isEmptyOrNull(getSummaryChartDataset())) {
            JOptionPane.showMessageDialog(null, "No data for Chart.\n\n"
                    + "None of the tables in the result set contain any NULLS or BLANKS.\n\n"
                    + "Hence not possible to dispay a chart.  ");

        } else {

            JFreeChart barchart = ChartFactory.createBarChart(
                    "Tables Nulls and Blank Percentages",
                    "Tables",
                    "Percentage %",
                    getSummaryChartDataset());

//        
            ChartFrame summaryTableChartFrame = new ChartFrame(
                    "Tables Nulls and Blank Percentages Bar Chart", barchart);
            summaryTableChartFrame.pack();
            summaryTableChartFrame.setVisible(true);
        }
    }

    /**
     *
     */
    public void buildTableColumnBarChartBarChartPopup() {
        if (isEmptyOrNull(getSummaryChartDataset())) {
            JOptionPane.showMessageDialog(null, "No data for Chart.\n\n"
                    + "None of the columns selected for the table contain any NULLS or BLANKS.\n\n"
                    + "Hence it is not possible to dispay a chart.  ");

        } else {

            JFreeChart barchart = ChartFactory.createBarChart(
                    "Columns Nulls and Blank Percentages",
                    "Columns",
                    "Percentage %",
                    getTableColumnBarChartDataset());

            ChartFrame summaryTableChartFrame = new ChartFrame(
                    "Columns Nulls and Blank Percentages Bar Chart", barchart);
            summaryTableChartFrame.pack();
            summaryTableChartFrame.setVisible(true);
        }
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    buildSummaryTableDataBarChartExplorerPanel()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * buildSummaryTableDataBarChartExplorerPanel() creates panel in the data
     * explorer panel views with a chart displaying a summary of nulls and
     * blanks for selected tables. If no tables are selected that contains any
     * nulls or blanks a message is displayed informing the user that chart can
     * not be displayed due to insufficient data.
     */
    public void buildSummaryTableDataBarChartExplorerPanel() {
        if (isEmptyOrNull(getSummaryChartDataset())) {
            chartErrorMessageTextArea.setText(
                    "No Chart Produced. \n"
                    + "\n"
                    + "None of the tables in the current result set "
                    + "contain any NULLS or BLANKS.");
            chartPanel.removeAll();
            chartPanel.add(noDataMainPanel, BorderLayout.CENTER);
            chartPanel.validate();
        } else {
            JFreeChart summaryBarchart = ChartFactory.createBarChart(
                    "Tables Nulls and Blank Percentages",
                    "Tables",
                    "Percentage %",
                    getSummaryChartDataset());
            ChartPanel summaryTableChartPanel = new ChartPanel(summaryBarchart);
            //      "Tables Nulls and Blank Percentages Bar Chart"
            chartPanel.removeAll();
            chartPanel.add(summaryTableChartPanel, BorderLayout.CENTER);
            chartPanel.validate();
        }
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    buildTableColumnBarChartExplorerPanel()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     *
     */
    public void buildTableColumnBarChartExplorerPanel() {
        if (isEmptyOrNull(getTableColumnBarChartDataset())) {
            chartErrorMessageTextArea.setText(
                    "No Chart Produced. \n"
                    + "\n"
                    + "None of the columns in the selected table "
                    + "contain any NULLS or BLANKS.");
            chartPanel.removeAll();
            chartPanel.add(noDataMainPanel, BorderLayout.CENTER);
            chartPanel.validate();
        } else {
            JFreeChart columnBarchart = ChartFactory.createBarChart("Columns Nulls and Blank Percentages",
                    "Columns",
                    "Percentage %",
                    getTableColumnBarChartDataset());

            ChartPanel tableColumnChartPanel = new ChartPanel(columnBarchart);
            //      "Columns Nulls and Blank Percentages Bar Chart"
            chartPanel.removeAll();
            chartPanel.add(tableColumnChartPanel, BorderLayout.CENTER);
            chartPanel.validate();
        }
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    buildTableColumnBarChartExplorerPanel()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     *
     */
    public void buildRowsColumnNullsPieChartExplorerPanel(String NullBlank) {
        DefaultPieDataset pieDataSet = null;
        String pieChartTitle = null;
        if (NullBlank.equals("null")) {
            pieDataSet = rowsColsNullsPieChartData;
            pieChartTitle = "Percentage of rows in table per Null column count";

        } else {
            pieDataSet = rowsColsBlanksPieChartData;
            pieChartTitle = "Percentage of rows in table per Blank column count";
        }

        if (isEmptyOrNull(pieDataSet)) {
            chartErrorMessageTextArea.setText(
                    "No Chart Produced. \n"
                    + "\n"
                    + "The table does not contain any rows");
            chartPanel.removeAll();
            chartPanel.add(noDataMainPanel, BorderLayout.CENTER);
            chartPanel.validate();
        } else {
            JFreeChart rowsNullBlankPiechart = ChartFactory.createPieChart(
                    pieChartTitle,
                    pieDataSet);

            ChartPanel tableColumnChartPanel = new ChartPanel(rowsNullBlankPiechart);

            chartPanel.removeAll();
            chartPanel.add(tableColumnChartPanel, BorderLayout.CENTER);
            chartPanel.validate();
        }
    }


    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    setInitialSummaryCard()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * setInitialSummaryCard() makes the panel visible in which the Table
     * Summary Table is displayed.
     */
    public void setInitialSummaryCard() {
        CardLayout card = (CardLayout) mainJPanel.getLayout();
        card.show(mainJPanel, "initialSummaryCard");
        cardViewInUse = "initialSummaryCard";
        summaryPanel.setVisible(true);
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    setExplorerMain()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * setExplorerMain() makes the main panel visible in which contain the data
     * explorer navigation, data and chart panels
     */
    public void setExplorerMain() {
        CardLayout card = (CardLayout) mainJPanel.getLayout();
        card.show(mainJPanel, DATAEXPLORERCARD);
        cardViewInUse = DATAEXPLORERCARD;
        columnDetailsSpiltPanel.setVisible(false);
        detailAnalysisPanel.setVisible(false);
        dataExplorerSplitPane.setVisible(true);
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    setExplorerChartCard()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * setExplorerChartCard() makes the main panel visible in which contain the
     * data explorer view chart panel.
     */
    public void setExplorerChartCard() {
        CardLayout card = (CardLayout) mainRightPanel.getLayout();
        card.show(mainRightPanel, CHARTPANEL);
        cardViewInUse = CHARTPANEL;
        columnDetailsSpiltPanel.setVisible(false);
        detailAnalysisPanel.setVisible(false);
        mainRightPanel.setVisible(true);
        cardChartPanel.setVisible(true);
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    setExplorerTableCard()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * setExplorerTableCard() makes the main panel visible in contain the data
     * explorer view data panel.
     */
    public void setExplorerTableCard() {
        CardLayout card = (CardLayout) mainRightPanel.getLayout();
        card.show(mainRightPanel, COLUMNDETAILSPLITPANEL);
        cardViewInUse = COLUMNDETAILSPLITPANEL;
        cardChartPanel.setVisible(false);
        detailAnalysisPanel.setVisible(false);
        columnDetailsSpiltPanel.setVisible(true);
        if (dataTable.getModel().getRowCount() > 0) {
            dataTableScrollPane.setVisible(true);
        } else {
            dataTableScrollPane.setVisible(false);
        }

    }

    public void setExplorerColumnChartCard() {
        CardLayout card = (CardLayout) mainRightPanel.getLayout();
        card.show(mainRightPanel, COLUMNDETAILSPLITPANEL);
        cardViewInUse = COLUMNDETAILSPLITPANEL;
        cardChartPanel.setVisible(false);
        dataTableScrollPane.setVisible(false);
        mainRightPanel.setVisible(true);
        columnDetailPanel.setVisible(true);
        columnDetailsSpiltPanel.setVisible(true);
        columnDetailChartPanel.setVisible(true);
    }

    public void setDetailAnalysisCard() {
        CardLayout card = (CardLayout) mainRightPanel.getLayout();
        card.show(mainRightPanel, CARDDETAILANALYSISPANEL);
        cardViewInUse = CARDDETAILANALYSISPANEL;
        cardChartPanel.setVisible(false);
        columnDetailsSpiltPanel.setVisible(false);
        mainRightPanel.setVisible(true);
        detailAnalysisPanel.setVisible(true);
        mainRightPanel.validate();

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    removeTableMenuPopupItems()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * removeTableMenuPopupItems() Removes Menu items from tableMenu Pop-up.
     */
    public void removeTableMenuPopupItems() {
        tablePopupMenu.remove(showDataNavigator);
        tablePopupMenu.remove(showDataNavigatorAllTables);

        tablePopupMenu.remove(showInitialSummaryTbl);

        tablePopupMenu.remove(showRowsColumnNullsPieChart);
        tablePopupMenu.remove(showRowsColumnBlanksPieChart);

        tablePopupMenu.remove(showRowsNullsBlanksPerColumnTable);

        tablePopupMenu.remove(showNBSummaryTbl);
        tablePopupMenu.remove(showNBSummaryTblAllTables);
        tablePopupMenu.remove(showNBSummaryTblForSelected);

        tablePopupMenu.remove(showSummaryChart);

        tablePopupMenu.remove(showTableColumnChart);
        tablePopupMenu.remove(showTableColumnSummary);

        tablePopupMenu.remove(exportDataTableSQL);
        tablePopupMenu.remove(exportTableColumnsSummary);
        tablePopupMenu.remove(exportRowsColumnsNullsBlanks);

        tablePopupMenu.remove(exportNBSummary);
        tablePopupMenu.remove(exportInitialTableSummary);

        tablePopupMenu.remove(showColumnData);

        tablePopupMenu.remove(showTableColumnChartSelectedRows);
        tablePopupMenu.remove(showTableAllColumnSummary);

    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getSQLcsv()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * getSQLcsv() create a CSV file from the query that created the last
     * dataTable SQL result set. The method returns a message containing
     * information regarding the name and location of the file created.
     *
     * @return String
     * @throws SQLException
     */
    public String getSQLcsv() throws SQLException {
        String uniqueFile = sdf.format(new Date());

        String lastSQLQueryCSV = lastSQLCSVColumnNames + " union all " + lastSQLQueryCSVSelect + uniqueFile + lastSQLQueryCSVFrom;
        String sqlDataDirQuery = "SHOW VARIABLES LIKE 'datadir'; ";

        Statement statement = db.conn.createStatement();
        Statement statementSQLDir = db.conn.createStatement();

        statement.executeQuery(lastSQLQueryCSV);

        ResultSet sqlExportDirRs = statementSQLDir.executeQuery(sqlDataDirQuery);
        sqlExportDirRs.first();

        //System.out.println(lastSQLQueryCSV);
        //  String dataDir = sqlExportDirRs.getObject(2).toString() + db.databaseName;
        String fileName = getRowColOneSelected(tableNameTable) + uniqueFile + ".csv";
        String message = "Data for the selected table was exported to:\n"
                + fileName + "\n"
                + "which is located at: \n"
                + sqlExportDirRs.getObject(2).toString() + db.databaseName;

        return message;
    }

    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getSQLcsv()
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    /**
     * https://stackoverflow.com/questions/16436928/how-to-export-a-jtable-to-a-csv-file
     *
     * @param table
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String writeCSVfile(JTable table, String summarytitle) throws IOException, ClassNotFoundException, SQLException {
        Writer writer = null;
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount();
        int nCol = dtm.getColumnCount();
        String message = "";
        String uniqueFile = sdf.format(new Date());
        String fileTitle = summarytitle + uniqueFile + ".csv";
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileTitle), "utf-8"));

            //write the header information
            StringBuffer bufferHeader = new StringBuffer();
            for (int j = 0; j < nCol; j++) {
                bufferHeader.append(dtm.getColumnName(j));
                if (j != nCol) {
                    bufferHeader.append(", ");
                }
            }
            writer.write(bufferHeader.toString() + "\r\n");

            //write row information
            for (int i = 0; i < nRow; i++) {
                StringBuffer buffer = new StringBuffer();
                for (int j = 0; j < nCol; j++) {
                    buffer.append(dtm.getValueAt(i, j));
                    if (j != nCol) {
                        buffer.append(", ");
                    }
                }
                writer.write(buffer.toString() + "\r\n");
            }
        } finally {
            writer.close();
        }
        if (summarytitle.equals(TABLECOLUMNSUMMARY)) {
            message = "The  Nulls & Blanks Column Summary ";
        }

        if (summarytitle.equals(ROWSCOLUMNSUMMARY)) {
            message = "The Analysis of Rows per Column Null or Blank ";
        }

        if (summarytitle.equals(NBCOLUMNSUMMARY)) {
            message = "The Nulls & Blank by Table Summary ";
        }

        if (summarytitle.equals(INITIALTABLESUMMARY)) {
            message = "The Table Column and Row Count Summary ";
        }

        message = message + "was exported to:\n"
                + fileTitle + "\n"
                + "which is located at: \n"
                + Paths.get("").toAbsolutePath().toString();

        return message;

    }
    public JDialog buildProgressBar (String title){
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JDialog progressBarDialog = new JDialog(this, "Analysing tables", false);
        progressBarDialog.add(BorderLayout.CENTER,progressBar);
        progressBarDialog.setSize(200,75);
        progressBarDialog.setLocationRelativeTo(this);
        progressBarDialog.setVisible(true);
        
        return progressBarDialog;
        
    }
     
    
    /*
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardChartPanel;
    private javax.swing.JTextArea chartErrorMessageTextArea;
    private javax.swing.JScrollPane chartErrorMessageTextPanel;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JPanel colNmFilterPanel;
    private javax.swing.JPanel colNmParentPanel;
    private javax.swing.JScrollPane colNmScrollPanel;
    private javax.swing.JPanel columnDetailChartPanel;
    private javax.swing.JPanel columnDetailPanel;
    private javax.swing.JSplitPane columnDetailsSpiltPanel;
    private javax.swing.JTextField columnNameFilter;
    private javax.swing.JTable columnNameTable;
    private javax.swing.JSplitPane dataExplorerSplitPane;
    private javax.swing.JTable dataTable;
    private javax.swing.JScrollPane dataTableScrollPane;
    private javax.swing.JTextField detailAnalysisFilter;
    private javax.swing.JPanel detailAnalysisFilterPanel;
    private javax.swing.JPanel detailAnalysisPanel;
    private javax.swing.JScrollPane detailAnalysisScrollPanel;
    private javax.swing.JTable detailAnalysisTable;
    private javax.swing.JMenu editjMenu;
    private javax.swing.JMenuItem exportDataTableSQL;
    private javax.swing.JMenuItem exportInitialTableSummary;
    private javax.swing.JMenuItem exportNBSummary;
    private javax.swing.JMenuItem exportRowsColumnsNullsBlanks;
    private javax.swing.JMenuItem exportTableColumnsSummary;
    private javax.swing.JMenu filejMenu;
    private javax.swing.JTextField initialSummaryTableFilter;
    private javax.swing.JPanel intialSummaryfilterPanel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField limitRowsFilter;
    private javax.swing.JPanel mainJPanel;
    private javax.swing.JPanel mainRightPanel;
    private javax.swing.JPanel noDataMainPanel;
    private javax.swing.JMenuItem showColumnData;
    private javax.swing.JMenuItem showDataNavigator;
    private javax.swing.JMenuItem showDataNavigatorAllTables;
    private javax.swing.JMenuItem showInitialSummaryTbl;
    private javax.swing.JMenuItem showNBSummaryTbl;
    private javax.swing.JMenuItem showNBSummaryTblAllTables;
    private javax.swing.JMenuItem showNBSummaryTblForSelected;
    private javax.swing.JMenuItem showRowsColumnBlanksPieChart;
    private javax.swing.JMenuItem showRowsColumnNullsPieChart;
    private javax.swing.JMenuItem showRowsNullsBlanksPerColumnTable;
    private javax.swing.JMenuItem showSummaryChart;
    private javax.swing.JMenuItem showTableAllColumnSummary;
    private javax.swing.JMenuItem showTableColumnChart;
    private javax.swing.JMenuItem showTableColumnChartSelectedRows;
    private javax.swing.JMenuItem showTableColumnSummary;
    private javax.swing.JPanel summaryPanel;
    private javax.swing.JScrollPane summaryScrollPanel;
    private javax.swing.JTable summaryTable;
    private javax.swing.JTextField tableNameFilter;
    private javax.swing.JTable tableNameTable;
    private javax.swing.JPopupMenu tablePopupMenu;
    private javax.swing.JPanel tblNmFilterPanel;
    private javax.swing.JPanel tblNmParentPanel;
    private javax.swing.JScrollPane tblNmScrollPanel;
    private javax.swing.JPanel topDetailAnalysisView;
    private javax.swing.JPanel topInitialSummaryView;
    // End of variables declaration//GEN-END:variables
}
