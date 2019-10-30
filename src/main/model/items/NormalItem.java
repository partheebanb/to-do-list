package model.items;

import model.lists.ToDoList;

public class NormalItem extends Item {

    public NormalItem(ToDoList toDoList) {
        super(toDoList);
        this.setPriority("Normal");
    }
}