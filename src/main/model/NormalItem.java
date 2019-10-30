package model;

public class NormalItem extends Item {

    public NormalItem(ToDoList toDoList) {
        super(toDoList);
        this.setPriority("Normal");
    }
}