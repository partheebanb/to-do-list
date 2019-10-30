package model.items;

import model.lists.ToDoList;

public class LowItem extends Item {

    public LowItem(ToDoList toDoList) {
        super(toDoList);
        this.setPriority("Low");
    }
}