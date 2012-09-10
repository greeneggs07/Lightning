/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanage;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import zj.taskmanager.model.SubTask;
import zj.taskmanager.model.Task;
import zj.taskmanager.screens.AgendaScreen;

/**
 *
 * @author Owner
 */
public class Application {
    //private ArrayList<Task> tasks;
    
    public static void main(String[] args){
        //test
      ArrayList<Task>  tasks = new ArrayList<>();
        Task task1 = new Task();
    task1.setName("task 1");
    task1.setDesc("task 1 desc");
    task1.setPriority(1);
    task1.setPercentComplete(44);
    
    ArrayList<SubTask> subtasks = new ArrayList<>();
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
        //test
        AgendaScreen aS = new AgendaScreen(tasks);
        aS.getAgendaPanel().addRowListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2){ //TODO
                    System.out.println(" double click on task:" );
                    Task clickedTask = aS.getAgendaPanel().getValueAtMouse(e);
                    System.out.println(clickedTask.getName());
                }
            }
         });
        aS.setVisible(true);
    }
    
}

class 