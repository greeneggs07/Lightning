/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.dialogs;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import zj.taskmanager.CommandDispatcher;
import zj.taskmanager.model.SubTask;
import zj.taskmanager.model.Task;

/**
 *
 * @author Owner
 */
public class AddSubTaskDialog extends JDialog {

    private CommandDispatcher commandDispatcher;
    private JPanel contentPanel;
    private JPanel taskFieldsPanel;
    private JTextField nameText;
    private JSpinner prioritySpinner;
    private JScrollPane descScrollPane;
    private JTextArea descText;
    private JPanel buttonPanel;  
    private JButton cancelButton;
    private JButton addButton;
    
    public AddSubTaskDialog(Frame owner, boolean modal, CommandDispatcher commandDispatcher) {
        super(owner, modal);
        this.commandDispatcher = commandDispatcher;
        initComponents();
    }

    private void initComponents() {
        
        this.setSize(new Dimension(407, 294));
        contentPanel = new JPanel();
        // Task Fields Panel
        taskFieldsPanel = new JPanel();
        JLabel nameLabel = new JLabel("Name:");
        JLabel priorityLabel = new JLabel("Priority:");
        JLabel descLabel = new JLabel("Description:");
        nameText = new JTextField(25);
        createPrioritySpinner();
        descText = new JTextArea(4, 50);
        descText.setLineWrap(true);
        descText.setWrapStyleWord(true);
        descScrollPane = new JScrollPane(descText, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        
        GroupLayout taskFieldsLayout = new GroupLayout(taskFieldsPanel);
        taskFieldsPanel.setLayout(taskFieldsLayout);
        
        taskFieldsLayout.setHorizontalGroup(
                taskFieldsLayout.createSequentialGroup()
                    .addGroup(taskFieldsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(taskFieldsLayout.createSequentialGroup()
                            .addGroup(taskFieldsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(nameLabel)
                                .addComponent(priorityLabel)
                                .addComponent(descLabel))
                            .addGroup(taskFieldsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameText)
                                .addComponent(prioritySpinner)))
                        .addComponent(descScrollPane)));
        taskFieldsLayout.setVerticalGroup(
                taskFieldsLayout.createSequentialGroup()
                    .addGroup(taskFieldsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameText))
                    .addGroup(taskFieldsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(priorityLabel)
                        .addComponent(prioritySpinner))
                    .addComponent(descLabel)
                    .addComponent(descScrollPane));
        
        
        buttonPanel = new JPanel();
        cancelButton = getCancelButton();
        addButton = getAddButton();
        
        GroupLayout buttonPanelLayout = new GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        
        buttonPanelLayout.setHorizontalGroup(
                buttonPanelLayout.createSequentialGroup()
                    .addComponent(cancelButton)
                    .addGap(0, 200, Short.MAX_VALUE)
                    .addComponent(addButton));
        buttonPanelLayout.setVerticalGroup(
                buttonPanelLayout.createSequentialGroup()
                    .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cancelButton)
                        .addComponent(addButton)));
        
        
        GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        
        contentPanelLayout.setHorizontalGroup(
                contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(taskFieldsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        contentPanelLayout.setVerticalGroup(
                contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(contentPanelLayout.createSequentialGroup()
                    .addComponent(taskFieldsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)));
        this.setContentPane(contentPanel);
        this.setTitle("Add Task");
    }

    private JButton getAddButton() {
        if(addButton == null){
            addButton = new JButton("Add");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addButtonActionPerformed(e);
                }
            });
        }
        return addButton;
    }
    
    private void addButtonActionPerformed(ActionEvent e){
        if (validTask()){
            setVisible(false);
            SubTask task = new SubTask();
            task.setName(nameText.getText());
            task.setDesc((descText.getText() != null) ? descText.getText() : "");
            commandDispatcher.dispatch(CommandDispatcher.Command.ADD_TASK, task);
            dispose();
        }
    }
    
    private boolean validTask(){
        boolean retVal = false;
        if(nameText.getText() == null || nameText.getText().isEmpty())
            JOptionPane.showMessageDialog(null, "Name cannot be blank.");
        else
            retVal = true;
        //TODO: finish task validation
        return retVal;
    }

    private JButton getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cancelButtonActionPerformed(e);
                }
            });
        }
        return cancelButton;
    }
    
    private void cancelButtonActionPerformed(ActionEvent e){
        setVisible(false);
        dispose();
    }
    
    private void createPrioritySpinner() {
        SpinnerModel priorityModel = new SpinnerNumberModel(0, 0, 10, 1);
        prioritySpinner = new JSpinner(priorityModel);
    }
    
}
