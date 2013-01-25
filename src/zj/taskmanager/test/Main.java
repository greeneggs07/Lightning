/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.test;

import zj.taskmanager.CommandDispatcherImpl;
import zj.taskmanager.CommandDispatcher;
import java.util.ArrayList;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import zj.taskmanager.dialogs.EntryDialog;
import zj.taskmanager.model.Task;
import zj.taskmanager.screens.AgendaScreen;

/**
 *
 * @author Owner
 */
public class Main {
    protected Main() {}
    
    private static void createAndShowGUI() {
        setLookAndFeel();
        CommandDispatcher commandDispatcher = new CommandDispatcherImpl();
        TestUtil test = new TestUtil();
        ArrayList<Task> tasks = test.getTestArray();
        EntryDialog entry = new EntryDialog(commandDispatcher);
        entry.setVisible(true);
        AgendaScreen aS = new AgendaScreen(commandDispatcher);
//        EntryDialog entry = new EntryDialog(commandDispatcher);
//        entry.setVisible(true);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
		createAndShowGUI();
            }
	});
    }
    
    private static void setLookAndFeel(){
        try {
            // Set cross-platform Java L&F (also called "Metal")
       for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
    } 
    catch (UnsupportedLookAndFeelException e) {
       // handle exception
    }
    catch (ClassNotFoundException e) {
       // handle exception
    }
    catch (InstantiationException e) {
       // handle exception
    }
    catch (IllegalAccessException e) {
       // handle exception
    }
    }
}
