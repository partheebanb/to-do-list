package model;

public class UrgentItem extends Item {

    public UrgentItem(ToDoList toDoList) {
        super(toDoList);
        this.setPriority("URGENT");
    }
}