package ui;

import model.Item;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    private ArrayList<Item> theList;
    private Scanner scanner;
    private Integer size;

    public ToDoList() {
        theList = new ArrayList<>();
        scanner = new Scanner(System.in);
        size = 0;

    }

    public ArrayList getTheList() {
        return theList;
    }

    public Integer getSize() {
        return size;
    }

    // EFFECTS: presents the menu of options and accepts user input, quiting the program if required
    private void menu() {
        String option = "";

        while (true) {
            System.out.println("Please select an option: \n 1. Add an item to the list"
                     + "\n 2. Mark an item as complete \n 3. Display all items in list \n 4. Quit");
            option = scanner.nextLine();

            if (option.equals("4")) {
                System.out.println("You have chosen to quit!");
                break;
            }

            handleInput(option);
        }
    }

    // EFFECTS: handles all user input except for quiting
    private void handleInput(String option) {
        switch (option) {
            case "1":
                addItem();
                break;
            case "2":
                chooseItemToComplete();
                break;
            case "3":
                System.out.println("You have chosen to view the list!");
                displayList();
                break;
            default:
                break;

        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new item, fills its field and adds it to theList
    private void addItem() {
        size++;
        Item item = new Item();

        System.out.println("You have chosen to add an item!");
        item = item.createItem();
        theList.add(item);
        displayList();
    }

    // MODIFIES: this
    // EFFECTS: allows the user to choose an item to mark as complete
    private void chooseItemToComplete() {
        Integer crossOff = 0;

        displayList();
        System.out.println("You have chosen mark an item as complete!");
        System.out.println("Which item would you like to mark as complete?");
        crossOff = Integer.parseInt(scanner.nextLine());
        completeItem(crossOff);
    }

    //REQUIRES: crossOff <= size of theList
    //MODIFIES: this
    //EFFECTS: marks item in theList at crossOff - 1 as complete
    public void completeItem(Integer crossOff) {
        theList.get(crossOff - 1).setStatus("Complete");
    }


    // EFFECTS: prints out all the items in theList formatted appropriately
    private void displayList() {
        for (Item item : theList) {
            System.out.println(size + ". " + item.displayItem(item));
        }
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.menu();
    }
}