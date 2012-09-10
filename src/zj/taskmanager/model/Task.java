/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.model;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import zj.taskmanager.util.SubTaskManager;

/**
 *
 * @author Owner
 */
@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class Task implements Comparable<Task> {
    private String name;
    private int priority;
    private String desc;
    private int percentComplete;
    private Date dateCreated;
    private Date estCompleteDate;
    
    @XmlElementWrapper(name = "subtasks")
    @XmlElement(name = "subtask")
    private ArrayList<SubTask> subtaskList;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPercentComplete() {
        return percentComplete;
    }

    public void setPercentComplete(int percentComplete) {
        this.percentComplete = percentComplete;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getEstCompleteDate() {
        return estCompleteDate;
    }

    public void setEstCompleteDate(Date estCompleteDate) {
        this.estCompleteDate = estCompleteDate;
    }

    public ArrayList<SubTask> getSubtaskList() {
        return subtaskList;
    }

    public void setSubtaskList(ArrayList<SubTask> subtasks) {
        this.subtaskList = subtasks;
    }

    
    /**
     *
     * @param t
     * @return
     */
    @Override
    public int compareTo(Task t) {
        int i = this.priority - t.priority;
        if(i == 0) 
            i = this.name.compareTo(t.name);
        return i;       
    }


}
