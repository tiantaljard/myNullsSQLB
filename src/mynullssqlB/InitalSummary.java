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
public class InitalSummary {

    public InitalSummary(String tableName, int columnCount, int rowCount) {
        this.tableName = tableName;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
    }
    private String tableName;
    private int columnCount;
    private int rowCount;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
}
