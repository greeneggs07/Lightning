/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.gui.components;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Owner
 */
public class ButtonPanel extends JPanel {
    
    private JButton editButton;
    private JButton enterButton;
    private JButton deleteButton;
    private JButton addTaskButton;
    private JButton changeAgendaButton;
    
    public ButtonPanel(){
        initComponents();
    }

    private void initComponents() {
        editButton = new JButton("Edit");
        enterButton = new JButton("Enter");
        deleteButton = new JButton("Delete");
        addTaskButton = new JButton("Add Task");
        changeAgendaButton = new JButton("Change Agenda");
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(editButton)
                    .addComponent(enterButton)
                    .addComponent(deleteButton))
                .addComponent(addTaskButton)
                .addComponent(changeAgendaButton));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(enterButton)
                    .addComponent(deleteButton))
                .addComponent(addTaskButton)
                .addComponent(changeAgendaButton));
        
    }
    
}
