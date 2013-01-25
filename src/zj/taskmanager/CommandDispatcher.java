/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager;

import zj.taskmanager.gui.components.AgendaPanel;
import zj.taskmanager.screens.AgendaScreen;

/**
 *
 * @author Owner
 */
public interface CommandDispatcher {
    enum Command{
        FILTER_LIST,
        SORT_LIST,
        EDIT_TASK,
        ENTER,
        DELETE_TASK,
        ADD_TASK,
        CHANGE_AGENDA,
        EXIT_ON_ENTRY, START_UP_EXISTING, START_UP_NEW, SAVE, DOUBLE_CLICK, CHANGE_BUTTON, ADD_SUBTASK, ADD_TASK_BUTTON, UPDATE_SUBTASK, DOUBLE_CLICK_SUBTASK, UPDATE_TASK}
    
    void setAgendaScreen(AgendaScreen agendaScreen);
    
    AgendaScreen getAgendaScreen();
    
    void dispatch(Command command);
    
    void dispatch(Command command, String arg1);
    
    void dispatch(Command command, String arg1, String arg2);
    
    void dispatch(Command command, Object obj);
}
