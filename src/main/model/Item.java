package model;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Item {
    private String title;
    private String status;
    private String priority;
    private SimpleDateFormat dueDate;
    private Scanner scanner;

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
    public Item inputItemData() {
        Item item = new Item();
        String title;
        String priority;
        String status = "Incomplete";
        SimpleDateFormat dueDate;

        System.out.println("Enter a title for the new item!");
        title = scanner.nextLine();
        System.out.println("Enter a priority for the new item!");
        priority = scanner.nextLine();
        System.out.println("Enter a due date for the new item in the form dd-mm-yyyy");
        dueDate = new SimpleDateFormat(scanner.nextLine());
        item = createItem(title, priority, status, dueDate);
        return item;
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