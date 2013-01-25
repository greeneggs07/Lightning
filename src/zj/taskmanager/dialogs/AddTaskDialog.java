/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.dialogs;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import zj.taskmanager.CommandDispatcher;
import zj.taskmanager.model.SubTask;
import zj.taskmanager.model.Task;

/**
 *
 * @author Owner
 */
public class AddTaskDialog extends JDialog {

    private CommandDispatcher commandDispatcher;
    private JPanel contentPanel;
    private JPanel taskFieldsPanel;
    private JTextField nameText;
    private JSpinner prioritySpinner;
    private JSpinner dateSpinner;
    private JScrollPane descScrollPane;
    private JTextArea descText;
    private JPanel buttonPanel;  
    private JButton cancelButton;
    private JButton addButton;
    
    public AddTaskDialog(Frame owner, boolean modal, CommandDispatcher commandDispatcher) {
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
        JLabel dateLabel = new JLabel("Est. Comp. Date:");
        JLabel descLabel = new JLabel("Description:");
        createDateSpinner();
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
                                .addComponent(dateLabel)
                                .addComponent(descLabel))
                            .addGroup(taskFieldsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameText)
                                .addComponent(prioritySpinner)
                                .addComponent(dateSpinner)))
                            .addComponent(descScrollPane)));
        taskFieldsLayout.setVerticalGroup(
                taskFieldsLayout.createSequentialGroup()
                    .addGroup(taskFieldsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameText))
                    .addGroup(taskFieldsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(priorityLabel)
                        .addComponent(prioritySpinner))
                    .addGroup(taskFieldsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dateLabel)
                        .addComponent(dateSpinner))
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
            Task task = new Task();
            task.setName(nameText.getText());
            task.setDesc((descText.getText() != null) ? descText.getText() : "");
            task.setDateCreated(Calendar.getInstance().getTime());
            task.setEstCompleteDate((Date)dateSpinner.getValue());
            task.setPriority((Integer)prioritySpinner.getValue());
            task.setPercentComplete(0);
            task.setSubtaskList(new ArrayList<SubTask>());
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

    private void createDateSpinner() {
        Calendar cal = Calendar.getInstance();
        Date initDate = cal.getTime();
        cal.add(Calendar.YEAR, -2);
        Date minDate = cal.getTime();
        cal.add(Calendar.YEAR, 4);
        Date maxDate = cal.getTime();
        SpinnerDateModel model = new SpinnerDateModel(initDate, minDate, maxDate, Calendar.YEAR);
        dateSpinner = new JSpinner(model);
    }

    private void createPrioritySpinner() {
        SpinnerModel priorityModel = new SpinnerNumberModel(0, 0, 10, 1);
        prioritySpinner = new JSpinner(priorityModel);
    }
    
}
