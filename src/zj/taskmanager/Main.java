/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager;

import zj.taskmanager.dialogs.EntryDialog;

/**
 *
 * @author Owner
 */
public class Main {
    protected Main() {}
    
    private static void createAndShowGUI() {
        CommandDispatcher commandDispatcher = new CommandDispatcherImpl();
        EntryDialog entry = new EntryDialog(commandDispatcher);
        entry.setVisible(true);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
		createAndShowGUI();
            }
	});
    }
    
}
