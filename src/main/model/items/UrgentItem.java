package model.items;

import model.lists.ToDoList;

public class UrgentItem extends Item {

    public UrgentItem() {
        super();
        this.setPriority("URGENT");
    }

    @Override
    public String displayItem() {
        return ("!!! " + title + " " + dueDate.toPattern());

    }
}