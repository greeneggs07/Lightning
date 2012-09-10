/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.gui.components;

import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import zj.taskmanager.model.Task;

/**
 *
 * @author Owner
 */
public class AgendaPanel extends JPanel {
    //private JPanel panel;
    private JTable table;
    private JScrollPane scrollPane;
    private List tasks;

    public AgendaPanel(List tasks) {
        this.tasks = tasks;
        initComponents();
    }
    
    
    
    private void initComponents(){
        table = new JTable();
        scrollPane = new JScrollPane();
        
        table.setModel(new TaskEntryTabelModel(tasks));
        table.setDefaultRenderer(Task.class, new TaskEntryCellRenderer());
        table.setRowHeight(80);
        
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2){ //TODO
                    Point p = e.getPoint();
                    System.out.println(" double click on task:" );
                    Task clickedTask = (Task)(table.getValueAt(table.rowAtPoint(p), table.columnAtPoint(p)));
                    System.out.println(clickedTask.getName());
                }
            }
         });
        
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
    
    public void addRowListener(MouseAdapter ma){
        table.addMouseListener(ma);
    }
    
    public Task getValueAtMouse(MouseEvent e){
        Point p = e.getPoint();
        return (Task)(table.getValueAt(table.rowAtPoint(p), table.columnAtPoint(p)));
    }
}


