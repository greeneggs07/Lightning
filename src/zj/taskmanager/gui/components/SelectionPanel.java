/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import zj.taskmanager.CommandDispatcher;
import zj.taskmanager.model.Task;

/**
 *
 * @author Owner
 */
public class SelectionPanel extends JPanel {
    private CommandDispatcher commandDispatcher;
    private JComboBox filterField;
    private JComboBox sortField;
    private JTextField filterText;
    private JCheckBox ascending;
    
    public SelectionPanel(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
        initComponents();
    }

    private void initComponents() {
        filterField = new JComboBox();
        createSortField();
        createFilterText();
        ascending = new JCheckBox("Ascending (uncheck = Descending)");
        JLabel filterLabel = new JLabel("Filter:");
        JLabel sortLabel = new JLabel("Sort:");
        
        filterField.setModel(new DefaultComboBoxModel(Task.getFields()));
        sortField.setModel(new DefaultComboBoxModel(Task.getFields()));
        ascending.setSelected(true);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(filterLabel)
                    .addComponent(sortLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(filterField)
                    .addComponent(sortField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(filterText)
                    .addComponent(ascending))
                );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(filterLabel)
                    .addComponent(filterField)
                    .addComponent(filterText))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sortLabel)
                    .addComponent(sortField)
                    .addComponent(ascending)));
        
                
    }
    
    private void createFilterText(){
        filterText = new JTextField();
        filterText.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                commandDispatcher.dispatch(CommandDispatcher.Command.FILTER_LIST, (String)filterField.getSelectedItem(), filterText.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                commandDispatcher.dispatch(CommandDispatcher.Command.FILTER_LIST, (String)filterField.getSelectedItem(), filterText.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("changed");
            }
        });
    }
    
    private void createSortField(){
        sortField = new JComboBox(Task.getFields());
        sortField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                commandDispatcher.dispatch(CommandDispatcher.Command.SORT_LIST, (String)sortField.getSelectedItem());
            }
        });
    }
}
