/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.test;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import zj.taskmanager.gui.components.TaskEntryCellRenderer;
import zj.taskmanager.gui.components.TaskEntryTabelModel;
import zj.taskmanager.model.SubTask;
import zj.taskmanager.model.Task;

/**
 *
 * @author Owner
 */
public class TestingTaskEntryTable extends javax.swing.JDialog {

    
    /**
     * Creates new form TestingTaskEntryTable
     */
    public TestingTaskEntryTable(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
                ArrayList<Task> tasks = new ArrayList<Task>();

    
    // create books
    Task task1 = new Task();
    task1.setName("task 1");
    task1.setDesc("task 1 desc");
    task1.setPriority(1);
    task1.setPercentComplete(44);
    
    ArrayList<SubTask> subtasks = new ArrayList<SubTask>();
    SubTask stask1 = new SubTask();
    stask1.setName("task 1.1");
    stask1.setDesc("task 1.1 desc");
    stask1.setPriority(6);
    subtasks.add(stask1);
    SubTask stask2 = new SubTask();
    stask2.setName("task 1.2");
    stask2.setDesc("task 1.2 desc");
    stask2.setPriority(6);
    subtasks.add(stask2);
    task1.setSubtaskList(subtasks);            
    tasks.add(task1);
    
    Task task2 = new Task();
    task2.setName("task 2");
    task2.setDesc("task 2 desc");
    task2.setPriority(1);
    task2.setPercentComplete(88);
    
    ArrayList<SubTask> subtasks2 = new ArrayList<SubTask>();
    SubTask stask21 = new SubTask();
    stask21.setName("task 2.1");
    stask21.setDesc("task 2.1 desc");
    stask21.setPriority(8);
    subtasks2.add(stask21);
    SubTask stask22 = new SubTask();
    stask22.setName("task 2.2");
    stask22.setDesc("task 2.2 desc");
    stask22.setPriority(7);
    subtasks2.add(stask22);
    SubTask stask23 = new SubTask();
    stask23.setName("task 2.3");
    stask23.setDesc("task 2.3 desc");
    stask23.setPriority(6);
    subtasks2.add(stask23);
    task2.setSubtaskList(subtasks2);
    tasks.add(task2);
        initComponents(tasks);
    }

    @SuppressWarnings("unchecked")
   
    private void initComponents(List tasks) {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new TaskEntryTabelModel(tasks));
        jTable1.setDefaultRenderer(Task.class, new TaskEntryCellRenderer());
        jTable1.setRowHeight(80);
                
        jTable1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2){
                    Point p = e.getPoint();
                    System.out.println(" double click on task:" );
                    Task clickedTask = (Task)(jTable1.getValueAt(jTable1.rowAtPoint(p), jTable1.columnAtPoint(p)));
                    System.out.println(clickedTask.getName());
                }
            }
         } );
   
        
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestingTaskEntryTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestingTaskEntryTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestingTaskEntryTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestingTaskEntryTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TestingTaskEntryTable dialog = new TestingTaskEntryTable(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
