/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.gui.components;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import zj.taskmanager.model.Task;

/**
 *
 * @author Owner
 */
public class TaskEntryTabelModel extends AbstractTableModel {

    List tasks;
    Class columnType;
    
    
    public TaskEntryTabelModel(List tasks) {
        this.columnType = Task.class;
        this.tasks = tasks;
    }
    
    public Class getColumnClass(int columnIndex){return columnType;}    
   
    public void setColumnClass(Class c) {
        columnType = c;
        this.fireTableStructureChanged();
        this.fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return (tasks == null) ? 0 : tasks.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return (tasks == null) ? null : tasks.get(rowIndex);
    }
    
    public void addRow(){
        this.fireTableRowsInserted(tasks.size() - 1, tasks.size() - 1);
    }
    
    public void deleteRow(int i){
        this.fireTableRowsDeleted(i, i);
    }
    
    public void updateRow(int i){
        this.fireTableCellUpdated(i, 1);
    }
    
    public void setList(List tasks){
        this.tasks = tasks;
    }
}
