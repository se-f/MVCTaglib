package org.todoapp.todoapp.beans;

import java.io.Serializable;

public class ToDoBean implements Serializable {
    private int id;
    private String content;
    private String user;
    private String isComplete;

    public ToDoBean(int id, String content, String user, String isComplete) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.isComplete = isComplete;
    }

    public ToDoBean() {

    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}