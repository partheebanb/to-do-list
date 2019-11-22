package model.items;

import model.lists.ToDoList;

public class NormalItem extends Item {

    public NormalItem() {
        super();
        this.setPriority("Normal");
    }

    @Override
    public String displayItem() {
        return ("!! " + title + " " + dueDate.toPattern());

    }
}