package model.items;

import model.lists.ToDoList;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public abstract class Item {
    protected String title;
    protected String priority;
    protected SimpleDateFormat dueDate;
    protected Scanner scanner;
    protected ToDoList toDoList;

    public Item() {
        title = "";
        priority = "";
        dueDate = new SimpleDateFormat("dd-MM-yyyy");
        scanner = new Scanner(System.in);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
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

    public SimpleDateFormat getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public Item createItem(String title, String priority, SimpleDateFormat dueDate) {
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        return this;
    }

    //  EFFECTS: returns a string containing all the data in the item formatted for display
    public abstract String displayItem();
}