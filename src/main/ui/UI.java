package ui;

import exceptions.ExceededMaxSizeException;
import model.Item;
import model.ToDoList;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class UI {

    private Scanner scanner;
    public ToDoList toDoList;

    public UI() throws IOException {
        scanner = new Scanner(System.in);
        toDoList = new ToDoList();
        this.menu();
    }

    public static void main(String[] args) throws IOException {
        UI ui = new UI();
    }

    private void menu() throws IOException {
        String option = "";

        while (true) {
            System.out.println("Please select an option: \n    1: Add an item to the list"
                    + "\n    2: Remove an item \n    3: Display all items in list \n    4: Load saved list "
                    + "\n    5: Save list into file \n    6: Quit");
            option = scanner.nextLine();

            if (option.equals("6")) {
                System.out.println("You have chosen to quit!");
                break;
            }

            handleInput(option);
        }
    }

    // EFFECTS: handles all user input except for quiting
    public void handleInput(String option) throws IOException {
        switch (option) {
            case "1":
                addItem();
                break;
            case "2":
                chooseItemToComplete();
                break;
            case "3":
                toDoList.displayList();
                break;
            case "4":
                toDoList.load("C:\\Users\\bpart\\CPSC 210\\Labs\\project_w8d2b\\data\\listData");
                break;
            case "5":
                toDoList.save("C:\\Users\\bpart\\CPSC 210\\Labs\\project_w8d2b\\data\\listData");
                break;
            default: break;
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new item, fills its field and adds it to theList
    private void addItem() throws IOException {
        try {
            toDoList.setSize(toDoList.getSize() + 1);
            Item item;
            String option = "";

            System.out.println("You have chosen to add an item! Please choose a priority: \n    1: Low "
                    + "\n    2: Normal \n    3: High");
            option = scanner.nextLine();
            item = toDoList.handlePriority(option);
            item = inputItemData(item);
            toDoList.getTheList().add(item);
            toDoList.sort();
        } catch (ExceededMaxSizeException e) {
            System.out.println("There are too many items in the todo list. Please try something else.");
        } finally {
            toDoList.displayList();
        }
    }

    private Item inputItemData(Item item) {
        System.out.println("Enter a title for the new item!");
        item.setTitle(scanner.nextLine());
        System.out.println("Enter a due date for the new item in the form dd-mm-yyyy");
        item.setDueDate(new SimpleDateFormat(scanner.nextLine()));
        return item;
    }

    // MODIFIES: this
    // EFFECTS: allows the user to choose an item to mark as complete

    private void chooseItemToComplete() {
        Integer crossOff = 0;

        toDoList.displayList();
        System.out.println("You have chosen mark an item to remove!");
        System.out.println("Which item would you like to remove?");
        crossOff = Integer.parseInt(scanner.nextLine());
        toDoList.removeItem(crossOff);
    }
}