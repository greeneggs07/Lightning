/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanage;

import zj.taskmanager.gui.components.AgendaPanel;

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
        CHANGE_AGENDA
    }
    
    void setAgendaPanel(AgendaPanel agendaPanel);
    
    AgendaPanel getAgendaPanel();
    
    void dispatch(Command command);
    
    void dispatch(Command command, String arg1);
    
    void dispatch(Command command, String arg1, String arg2);
    
    void dispatch(Command command, Object obj);
}
