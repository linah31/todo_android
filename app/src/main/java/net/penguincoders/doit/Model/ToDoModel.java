package net.penguincoders.doit.Model;

public class ToDoModel {
    private int id, status;
    private String task;
    private boolean isSelected;


    public ToDoModel(String task){
        this.task = task;

    }
    public ToDoModel(){
       // this.task = task;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
