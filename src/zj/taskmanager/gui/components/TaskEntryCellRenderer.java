/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.gui.components;

import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import zj.taskmanager.model.Task;

/**
 *
 * @author Owner
 */
public class TaskEntryCellRenderer implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Task task = (Task) value;
        JLabel taskName = new JLabel(task.getName());
        JLabel taskDesc = new JLabel(task.getDesc());
        JProgressBar progressBar = new JProgressBar();
        JCheckBox checkBox = new JCheckBox();
        JPanel panel = new JPanel();
        
        checkBox.setEnabled(true);
        progressBar.setValue(task.getPercentComplete());
        
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                    .addComponent(checkBox)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(taskName)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(10,10,10)
                            .addComponent(taskDesc))
                        .addComponent(progressBar))
                    );
        
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addComponent(taskName)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(checkBox)
                        .addComponent(taskDesc))
                    .addComponent(progressBar));
        
        if (isSelected) 
            panel.setBackground((table.getSelectionBackground()));
        else
            panel.setForeground((table.getSelectionForeground()));
        
        return panel;
        
    }
    
}
