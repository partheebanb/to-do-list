package model.items;

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