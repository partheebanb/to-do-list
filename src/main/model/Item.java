package model;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public abstract class Item {
    protected String title;
    protected String status;
    protected String priority;
    protected SimpleDateFormat dueDate;
    protected Scanner scanner;

    public Item() {
        title = "";
        status = "";
        priority = "";
        dueDate = new SimpleDateFormat("dd-MM-yyyy");
        scanner = new Scanner(System.in);
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


    // MODIFIES: this
    // EFFECTS: uses user input to create a new item
    public void inputItemData() {
        this.setStatus("Incomplete");
        System.out.println("Enter a title for the new item!");
        this.setTitle(scanner.nextLine());
        System.out.println("Enter a due date for the new item in the form dd-mm-yyyy");
        this.setDueDate(new SimpleDateFormat(scanner.nextLine()));
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
}