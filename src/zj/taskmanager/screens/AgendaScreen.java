/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.screens;

import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;
import zj.taskmanager.gui.components.AgendaPanel;
import zj.taskmanager.gui.components.ButtonPanel;
import zj.taskmanager.gui.components.SelectionPanel;

/**
 *
 * @author Owner
 */
public class AgendaScreen extends JFrame{
    private List tasks;
    protected AgendaPanel agendaPanel;
    private ButtonPanel buttonPanel;
    private SelectionPanel selectionPanel;
    
    public AgendaScreen(List tasks){
        this.tasks = tasks;
        initComponents();
    }

    private void initComponents() {
        agendaPanel = new AgendaPanel(tasks);
        buttonPanel = new ButtonPanel();
        selectionPanel = new SelectionPanel();
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        
        
        menu.add(fileMenu);
        menu.add(editMenu);
        
        this.setJMenuBar(menu);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(selectionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(agendaPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(selectionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(agendaPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
        
        pack();
    }
    
    public AgendaPanel getAgendaPanel(){
        return agendaPanel;
    }
    
}
