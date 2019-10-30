package model;

public class LowItem extends Item {

    public LowItem(ToDoList toDoList) {
        super(toDoList);
        this.setPriority("Low");
    }
}