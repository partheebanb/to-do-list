package model.items;

import model.lists.ToDoList;

public class UrgentItem extends Item {

    public UrgentItem(ToDoList toDoList) {
        super(toDoList);
        this.setPriority("URGENT");
    }
}