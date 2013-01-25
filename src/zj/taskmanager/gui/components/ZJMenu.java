/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import zj.taskmanager.CommandDispatcher;

/**
 *
 * @author Owner
 */
public class ZJMenu extends JMenuBar {
    private JMenu file;
    private JMenu edit;
    private JMenuItem save;
    private CommandDispatcher commandDispatcher;
    
    public ZJMenu(CommandDispatcher commandDispatcher){
        this.commandDispatcher = commandDispatcher;
        file = new JMenu("File");
        edit = new JMenu("Edit");
        save = new JMenuItem("Save", 's');
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveActionPerformed(e);
            }
        });
        file.add(save);
        this.add(file);
        this.add(edit);
    }
    
    private void saveActionPerformed(ActionEvent e){
        commandDispatcher.dispatch(CommandDispatcher.Command.SAVE);
    }
}
