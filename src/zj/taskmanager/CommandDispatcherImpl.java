/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager;

import java.util.ArrayList;
import zj.taskmanager.dialogs.AddSubTaskDialog;
import zj.taskmanager.dialogs.AddTaskDialog;
import zj.taskmanager.dialogs.EditSubTaskDialog;
import zj.taskmanager.dialogs.EditTaskDialog;
import zj.taskmanager.gui.components.AgendaPanel;
import zj.taskmanager.model.SubTask;
import zj.taskmanager.model.Task;
import zj.taskmanager.screens.AgendaScreen;

/**
 *
 * @author Owner
 */
public class CommandDispatcherImpl implements CommandDispatcher {
    private AgendaScreen agendaScreen;
    
    @Override
    public void setAgendaScreen(AgendaScreen agendaScreen) {
        this.agendaScreen = agendaScreen;
    }

    @Override
    public AgendaScreen getAgendaScreen() {
        return this.agendaScreen;
    }

    @Override
    public void dispatch(Command command) {
        switch (command) {
            case SAVE :
                this.agendaScreen.sendTaskToXml();
                break;
            case CHANGE_BUTTON :
                this.agendaScreen.changeButtonClicked();
                break;
            case DELETE_TASK : 
                this.agendaScreen.deleteTask();
                break;
            case EDIT_TASK :
                this.agendaScreen.editTask();
            
        }
    }

    @Override
    public void dispatch(Command command, String arg1) {
        switch (command) {
            case START_UP_EXISTING :
                agendaScreen.setPath(arg1);
                agendaScreen.getTaskFromXml(true);
                agendaScreen.showFrame();
                break;
            case START_UP_NEW :
                agendaScreen.setPath(arg1);
                agendaScreen.getTaskFromXml(false);
                agendaScreen.showFrame();
                break;
            case SORT_LIST :
                agendaScreen.sortList(arg1);
                break;
            case ADD_TASK_BUTTON :
                onShowAddDialog(arg1);
                break;
        }
    }

    @Override
    public void dispatch(Command command, String arg1, String arg2) {
        switch (command) {
            case FILTER_LIST :
                agendaScreen.filterList(arg1, arg2);
                break;
        }
    }

    @Override
    public void dispatch(Command command, Object obj) {
        switch (command) {
            case ADD_TASK : //this.agendaScreen.addTask((Task)obj);
                onAdd(obj);
                break;
//            case START_UP :
//                agendaScreen.setTaskList((ArrayList<Task>)obj);
//                agendaScreen.showFrame();
//                break;
            case DOUBLE_CLICK :
                agendaScreen.doubleClicked((Task)obj);
                break;
            case DOUBLE_CLICK_SUBTASK :
                onShowEditSubTaskDialog(obj);
                break;
            case UPDATE_SUBTASK :
                this.agendaScreen.updateTask();
            case EDIT_TASK :
                onEdit(obj);
                break;
            
        }
    }
    
    private void onShowAddDialog(String taskType){
        if(taskType.equals("Add Task"))
            onShowAddTaskDialog();
        else
            onShowAddSubTaskDialog();
    }

    private void onShowAddTaskDialog() {
        AddTaskDialog dialog = new AddTaskDialog(agendaScreen, true, this);
        dialog.setVisible(true);
    }
    
    private void onShowAddSubTaskDialog() {
        AddSubTaskDialog dialog = new AddSubTaskDialog(agendaScreen, true, this);
        dialog.setVisible(true);
    }

    private void onAdd(Object obj) {
        if(obj instanceof Task) {
            Task task = (Task) obj;
            this.agendaScreen.addTask(task);
        }
        else {
            SubTask subTask = (SubTask) obj;
            this.agendaScreen.addSubTask(subTask);
        }
            
    }

    private void onShowEditSubTaskDialog(Object obj) {
        EditSubTaskDialog dialog = new EditSubTaskDialog(agendaScreen, true, this, (SubTask)obj);
        dialog.setVisible(true);
    }

    private void onEdit(Object obj) {
        if(obj instanceof Task) {
            onShowEditTaskDialog(obj);
        }
        else {
            onShowEditSubTaskDialog(obj);
        }
    }

    private void onShowEditTaskDialog(Object obj) {
        EditTaskDialog dialog = new EditTaskDialog(agendaScreen, true, this, (Task)obj);
        dialog.setVisible(true);
    }
    
}
