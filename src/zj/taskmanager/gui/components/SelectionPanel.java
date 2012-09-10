/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.gui.components;

import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Owner
 */
public class SelectionPanel extends JPanel {
    private JComboBox filterField;
    private JComboBox sortField;
    private JTextField filterText;
    private JCheckBox ascending;
    
    public SelectionPanel() {
        initComponents();
    }

    private void initComponents() {
        filterField = new JComboBox();
        sortField = new JComboBox();
        filterText = new JTextField();
        ascending = new JCheckBox("Ascending (uncheck = Descending)");
        JLabel filterLabel = new JLabel("Filter:");
        JLabel sortLabel = new JLabel("Sort:");
        
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
}
