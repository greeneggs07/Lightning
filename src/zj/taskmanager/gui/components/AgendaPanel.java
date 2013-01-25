/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.gui.components;

import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import zj.taskmanager.CommandDispatcher;
import zj.taskmanager.model.SubTask;
import zj.taskmanager.model.Task;

/**
 *
 * @author Owner
 */
public class AgendaPanel extends JPanel {
    //private JPanel panel;
    private CommandDispatcher commandDispatcher;
    private JTable table;
    private TaskEntryTabelModel model;
    private JScrollPane scrollPane;
    private List tasks;
    private int currentEditTask;

    public AgendaPanel(List tasks, CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
        this.tasks = tasks;
        initComponents();
    }
    
    
    
    private void initComponents(){
        scrollPane = new JScrollPane();
        model = new TaskEntryTabelModel((tasks != null) ? tasks : new ArrayList<Task>());
        createTable();
        
        scrollPane.setViewportView(table);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        //layout.setAutoCreateGaps(true);
        //layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
    }
    
    private void createTable(){
        table = new JTable();
        table.setModel(model);
        table.setDefaultRenderer(Task.class, new TaskEntryCellRenderer());
        table.setDefaultRenderer(SubTask.class, new SubTaskEntryCellRenderer());
        table.setRowHeight(80);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setColumnName("Tasks");
        
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2){ //TODO
                    Point p = e.getPoint();
                    Object clickedTask = table.getValueAt(table.rowAtPoint(p), table.columnAtPoint(p));
                    if (clickedTask instanceof Task)
                        commandDispatcher.dispatch(CommandDispatcher.Command.DOUBLE_CLICK, clickedTask);
                    else {
                        currentEditTask = table.getSelectedRow();
                        commandDispatcher.dispatch(CommandDispatcher.Command.DOUBLE_CLICK_SUBTASK, clickedTask);
                    }
                }
            }
         });
    }
    
    public void addTask(){
        model.addRow();
    }
    
    public void updateTask(){
        model.updateRow(currentEditTask);
    }
    
    public void deleteTask(List tasks){
        int i = table.getSelectedRow();
        if(i != -1) {
            tasks.remove(i);
            model.deleteRow(i);
        }
            
    }
    
    public void rebuildTable(List tasks){
        model.setList(tasks);
        model.fireTableDataChanged();
    }
    
    public void setColumnClass(Class c){
        model.setColumnClass(c);
    }

    public Object getSelectedTask(List tasks) {
        int i = table.getSelectedRow();
        Object ret = null;
        if(i != -1) {
            currentEditTask = i;
            ret = tasks.get(i);
        }
        return ret;
    }
    
    public void setColumnName(String name){
        table.getColumnModel().getColumn(0).setHeaderValue(name);
    }
}


