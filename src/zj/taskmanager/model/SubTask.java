/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zj.taskmanager.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Owner
 */
@XmlRootElement(name = "subtask")
public class SubTask implements Comparable<SubTask> {
    private String name;
    private int priority;
    private String desc;

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

    @Override
    public int compareTo(SubTask s) {
        int i = this.priority - s.priority;
        if(i == 0) 
            i = this.name.compareTo(s.name);
        return i;  
    }
}