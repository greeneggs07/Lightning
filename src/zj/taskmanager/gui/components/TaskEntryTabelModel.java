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

    public TaskEntryTabelModel(List tasks) {
        this.tasks = tasks;
    }
    
   public Class getColumnClass(int columnIndex){return Task.class;}    
   
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
    
}
