/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.dialogs;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Owner
 */
public class AddTaskDialog extends JDialog {

    private JTextField nameText;
    private JTextField priorityText;
    private JTextField monthText;
    private JTextField dayText;
    private JTextField yearText;
    private JTextArea descText
    public AddTaskDialog(Frame owner, boolean modal) {
        super(owner, modal);
        initComponents();
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    
    
}
