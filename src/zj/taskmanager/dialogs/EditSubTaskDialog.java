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
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import zj.taskmanager.CommandDispatcher;
import zj.taskmanager.model.SubTask;

/**
 *
 * @author Owner
 */
public class EditSubTaskDialog extends JDialog {
    private JTextField nameText;
    private JSpinner priorityText;
    private JLabel percentText;
    private JSlider percentSlider;
    private JScrollPane descScrollPane;
    private JTextArea descText;
    private JButton updateButton;
    private JButton cancelButton;
    private JPanel contentPanel;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private CommandDispatcher commandDispatcher;
    private SubTask subtask;
    
    public EditSubTaskDialog(Frame owner, boolean modal, CommandDispatcher commandDispatcher, SubTask subtask){
        super(owner, modal);
        this.commandDispatcher = commandDispatcher;
        this.subtask = subtask;
        initComponents();
        
    }

    private void initComponents() {
        this.setSize(new Dimension(407, 294));
        contentPanel = new JPanel();
        inputPanel = new JPanel();
        nameText  = new JTextField(subtask.getName());
        createPriorityText();
        percentText = new JLabel(subtask.getPercentComplete()+"");
        createPercentSlider();
        descText = new JTextArea(subtask.getDesc());
        descScrollPane = new JScrollPane(descText, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JLabel name = new JLabel("Name:");
        JLabel priority = new JLabel("Priority:");
        JLabel percent = new JLabel("Percent Complete:");
        JLabel desc = new JLabel("Description:");
        buttonPanel = new JPanel();
        createUpdateButton();
        createCancelButton();
        
        GroupLayout inputLayout = new GroupLayout(inputPanel);
        inputPanel.setLayout(inputLayout);
        
        inputLayout.setHorizontalGroup(
                inputLayout.createSequentialGroup()
                    .addGroup(inputLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(inputLayout.createSequentialGroup()
                            .addGroup(inputLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(name)
                                .addComponent(priority)
                                .addComponent(percent)
                                .addComponent(desc))
                             .addGroup(inputLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameText)
                                .addComponent(priorityText)
                                .addComponent(percentText)))
                        .addComponent(percentSlider)
                        .addComponent(descScrollPane)));
        inputLayout.setVerticalGroup(
                inputLayout.createSequentialGroup()
                    .addGroup(inputLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(name)
                        .addComponent(nameText))
                    .addGroup(inputLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(priority)
                        .addComponent(priorityText))
                    .addGroup(inputLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(percent)
                        .addComponent(percentText))
                    .addComponent(percentSlider)
                    .addComponent(desc)
                    .addComponent(descScrollPane));
        
        GroupLayout buttonLayout = new GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonLayout);
        
        buttonLayout.setHorizontalGroup(
                buttonLayout.createSequentialGroup()
                    .addComponent(cancelButton)
                    .addGap(0, 200, Short.MAX_VALUE)
                    .addComponent(updateButton));
        buttonLayout.setVerticalGroup(
                buttonLayout.createSequentialGroup()
                    .addGroup(buttonLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cancelButton)
                        .addComponent(updateButton)));
        
        GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        
        contentPanelLayout.setHorizontalGroup(
                contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(inputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        contentPanelLayout.setVerticalGroup(
                contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(contentPanelLayout.createSequentialGroup()
                    .addComponent(inputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)));
        this.setContentPane(contentPanel);
        this.setTitle("Edit SubTask");
        
    }

    private void createUpdateButton() {
        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateButtonActionPerformed(e);
            }
        });
    }
    
    private void updateButtonActionPerformed(ActionEvent e){
        if(fieldsValid()){
            setVisible(false);
            subtask.setName(nameText.getText());
            subtask.setDesc(descText.getText());
            subtask.setPriority(Integer.parseInt(priorityText.getValue().toString()));
            subtask.setPercentComplete(percentSlider.getValue());
            commandDispatcher.dispatch(CommandDispatcher.Command.UPDATE_SUBTASK);
            dispose();
        }
    }

    private boolean fieldsValid() {
        boolean retVal = false;
        if(nameText.getText() == null || nameText.getText().isEmpty())
            JOptionPane.showMessageDialog(null, "Name cannot be blank.");
        else
            retVal = true;
        //TODO: finish task validation
        return retVal;
    }

    private void createCancelButton() {
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonActionPerformed(e);
            }
        });
    }
    
    private void cancelButtonActionPerformed(ActionEvent e){
        setVisible(false);
        dispose();
    }

    private void createPriorityText() {
        SpinnerModel priorityModel = new SpinnerNumberModel(subtask.getPriority(), 0, 10, 1);
        priorityText = new JSpinner(priorityModel);
    }

    private void createPercentSlider() {
        percentSlider = new JSlider(0, 100, subtask.getPercentComplete());
        percentSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                percentText.setText(""+percentSlider.getValue());
            }
        });
    }
}
