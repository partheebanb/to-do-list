package model.items;

import model.lists.ToDoList;

public class LowItem extends Item {

    public LowItem() {
        super();
        this.setPriority("Low");
    }

    @Override
    public String displayItem() {
        return ("! " + title + " " + dueDate.toPattern());
    }
}