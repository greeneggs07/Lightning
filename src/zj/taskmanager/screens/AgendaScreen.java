/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import javax.xml.bind.JAXBException;
import zj.taskmanager.CommandDispatcher;
import zj.taskmanager.CommandDispatcherImpl;
import zj.taskmanager.gui.components.AgendaPanel;
import zj.taskmanager.gui.components.ButtonPanel;
import zj.taskmanager.gui.components.SelectionPanel;
import zj.taskmanager.gui.components.ZJMenu;
import zj.taskmanager.model.Agenda;
import zj.taskmanager.model.SubTask;
import zj.taskmanager.model.Task;
import zj.taskmanager.util.XmlMediator;

/**
 *
 * @author Owner
 */
public class AgendaScreen extends JFrame{
    private List tasks;
    private List filteredList;
    private Agenda agenda;
    private Task currentTask;
    protected AgendaPanel agendaPanel;
    private ButtonPanel buttonPanel;
    private SelectionPanel selectionPanel;
    private ZJMenu menu;
    private XmlMediator xmlMediator;
    private String path;
    private CommandDispatcher commandDispatcher;
    private boolean mainList;
    
    public AgendaScreen(CommandDispatcher commandDispatcher){
        this.commandDispatcher = commandDispatcher;
        mainList = true;
        try {
            xmlMediator = new XmlMediator();
        } catch (JAXBException ex) {
            Logger.getLogger(AgendaScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.commandDispatcher.setAgendaScreen(this);
        initComponents();
    }

    private void initComponents() {
        agendaPanel = new AgendaPanel(tasks, commandDispatcher);
        buttonPanel = new ButtonPanel(commandDispatcher);
        selectionPanel = new SelectionPanel(commandDispatcher);
        menu = new ZJMenu(commandDispatcher);
        
        this.setJMenuBar(menu);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lightning!");
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(selectionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(agendaPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(selectionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(agendaPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
        
        pack();
    }
  
    public void setPath(String path){
        this.path = path;
    }
    
    public void addTask(Task task){
        tasks.add(task);
        agendaPanel.addTask();
    }
    
    public void setTaskList(List tasks){
        this.tasks = tasks;
        agendaPanel.rebuildTable(tasks); 
    }
    
    public void showFrame(){
        this.setVisible(true);
    }
    
    public ArrayList<Task> getTaskFromXml(boolean existing){
        if (existing){
            try {
                agenda = xmlMediator.getAgenda(path);
            } catch (JAXBException ex) {
                agenda=new Agenda();
                Logger.getLogger(AgendaScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            agenda = new Agenda();
        if(agenda.getTaskList() ==  null)
            agenda.setTaskList(new ArrayList<Task>());
        setTaskList(agenda.getTaskList());
        return agenda.getTaskList();
    }
    
    public void sendTaskToXml(){
        try {
            xmlMediator.writeAgenda(path, agenda);
        } catch (JAXBException ex) {
            Logger.getLogger(AgendaScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AgendaScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void filterList(String arg1, String arg2) {
        if(mainList)
            filterTasks(arg1, arg2);
        else
            filterSubTasks(arg1, arg2);
    }
    
    private void filterTasks(String field, String pattern){
        if(pattern.length() == 0){
            setTaskList(agenda.getTaskList());
        }
        else {
            pattern = ".*"+pattern+"+.*";
            filteredList = new ArrayList<Task>();
            for(Task t : agenda.getTaskList()){
                if(t.getName().toLowerCase().matches(pattern.toLowerCase()))
                    filteredList.add(t);
            }
            setTaskList(filteredList);
        }
    }
    
    private void filterSubTasks(String field, String pattern){
        if(pattern.length() == 0){
            setTaskList(currentTask.getSubtaskList());
        }
        else {
            filteredList = new ArrayList<SubTask>();
            for(SubTask t : currentTask.getSubtaskList()){
                if(t.getName().contains(pattern))
                    filteredList.add(t);
            }
            setTaskList(filteredList);
        }
    }
    
    public void sortList(String field){
        Collections.sort(tasks);
        agendaPanel.rebuildTable(tasks);
    }

    public void doubleClicked(Object obj) {
        if(obj instanceof Task) {
            currentTask = (Task) obj;
            
            if (currentTask.getSubtaskList() == null)
                currentTask.setSubtaskList(new ArrayList<SubTask>());
            buttonPanel.setChangeButtonText("Back to Agenda");
            buttonPanel.setAddButtonText("Add Subtask");
            agendaPanel.setColumnClass(SubTask.class);
            setTaskList(currentTask.getSubtaskList());
            agendaPanel.setColumnName("Subtasks");
            mainList = false;
        }
        
        
    }

    public void changeButtonClicked() {
        if(mainList)
            ;//TODO : change this damn agenda
        else {
            buttonPanel.setChangeButtonText("Change Agenda");
            buttonPanel.setAddButtonText("Add Task");
            agendaPanel.setColumnClass(Task.class);
            setTaskList(agenda.getTaskList());
            agendaPanel.setColumnName("Tasks");
            mainList = true;
        }
    }

    public void addSubTask(SubTask subTask) {
        tasks.add(subTask);
        agendaPanel.addTask();
    }

    public void deleteTask() {
        agendaPanel.deleteTask(tasks);
    }
    
    public void updateTask(){
        agendaPanel.updateTask();
    }

    public void editTask() {
        Object taskToEdit = agendaPanel.getSelectedTask(tasks);
        if(taskToEdit != null)
            commandDispatcher.dispatch(CommandDispatcher.Command.EDIT_TASK, taskToEdit);
    }
    
}
