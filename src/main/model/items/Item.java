package model.items;

import model.lists.ToDoList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Item {
    protected String title;
    protected String status;
    protected String priority;
    protected SimpleDateFormat dueDate;
    protected Scanner scanner;
    protected ToDoList toDoList;

    public Item(ToDoList toDoList) {
        title = "";
        status = "Incomplete";
        priority = "";
        dueDate = new SimpleDateFormat("dd-MM-yyyy");
        scanner = new Scanner(System.in);
        this.toDoList = toDoList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDueDate(SimpleDateFormat dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public SimpleDateFormat getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public Item createItem(String title, String priority, String status, SimpleDateFormat dueDate) {
        this.title = title;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
        return this;
    }

    //  EFFECTS: returns a string containing all the data in the item formatted for display
    public String displayItem() {
        return ("Title: " + getTitle() + ". Priority: " + getPriority()
                + ". Status: " + getStatus() + ". Due Date: " + (getDueDate()).toPattern());
    }

    public String formatItem(Item item) {
        return item.getTitle() + " " + item.getPriority() + " " + item.getStatus() + " "
                + item.getDueDate().toPattern();
    }

    public Item convertPartsToItems(ArrayList<String> parts) {
        Item item;
        item = priorityDecider(parts.get(1));

        title = parts.get(0);
        status = parts.get(2);
        dueDate = new SimpleDateFormat(parts.get(3));
        return item;
    }


    public Item priorityDecider(String priority) {
        if (priority == "Low") {
            return new LowItem(toDoList);
        } else if (priority == "Normal") {
            return new NormalItem(toDoList);
        } else {
            return new UrgentItem(toDoList);
        }
    }
}