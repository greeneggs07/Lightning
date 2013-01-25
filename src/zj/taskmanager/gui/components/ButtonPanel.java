/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import zj.taskmanager.CommandDispatcher;

/**
 *
 * @author Owner
 */
public class ButtonPanel extends JPanel {
    
    private CommandDispatcher commandDispatcher;
    private JButton editButton;
    private JButton enterButton;
    private JButton deleteButton;
    private JButton addButton;
    private JButton changeButton;
    
    public ButtonPanel(CommandDispatcher commandDispatcher){
        this.commandDispatcher = commandDispatcher;
        initComponents();
    }

    private void initComponents() {
        enterButton = new JButton("Enter");
        addButton = getAddTaskButton();
        createChangeButton();
        createDeleteButton();
        createEditButton();
        
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
                .addComponent(addButton)
                .addComponent(changeButton));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(enterButton)
                    .addComponent(deleteButton))
                .addComponent(addButton)
                .addComponent(changeButton));
        
    }

    private JButton getAddTaskButton() {
        if (addButton == null) {
            addButton = new JButton("Add Task");
            addButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    addTaskButtonActionPerformed(e);
                }
            });
        }
        return addButton;
    }
    
    
    private void addTaskButtonActionPerformed(ActionEvent e){
        commandDispatcher.dispatch(CommandDispatcher.Command.ADD_TASK_BUTTON, ((String)addButton.getText()));
    }
    
    public void setChangeButtonText(String str){
        changeButton.setText(str);
    }

    private void createChangeButton() {
        changeButton = new JButton("Change Agenda");
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeButtonActionPerformed(e);
            }
        });
    }
    
    private void changeButtonActionPerformed(ActionEvent e){
        commandDispatcher.dispatch(CommandDispatcher.Command.CHANGE_BUTTON);
    }

    public void setAddButtonText(String str) {
        addButton.setText(str);
    }

    private void createDeleteButton() {
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonActionPerformed(e);
            }
        });
    }
    
    private void deleteButtonActionPerformed(ActionEvent e){
        commandDispatcher.dispatch(CommandDispatcher.Command.DELETE_TASK);
    }

    private void createEditButton() {
        editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editButtonActionPerformed(e);
            }

        });
    }
    
    private void editButtonActionPerformed(ActionEvent e) {
        commandDispatcher.dispatch(CommandDispatcher.Command.EDIT_TASK);
    }
}
