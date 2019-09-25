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
        handleIo();
    }

    private void handleIo() {
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

    private void handleInput(String option) {
        switch (option) {
            case "1":
                addItem();
                break;
            case "2":
                removeItem();
                break;
            case "3":
                System.out.println("You have chosen to view the list!");
                displayList();
                break;
            default:
                break;

        }
    }

    private void addItem() {
        size++;
        Item item = new Item();

        System.out.println("You have chosen to add an item!");
        item = item.createItem();
        theList.add(item);
        displayList();
    }

    private void removeItem() {
        Integer crossOff = 0;

        displayList();
        System.out.println("You have chosen to remove an item!");
        System.out.println("Which item would you like to mark as complete?");
        crossOff = Integer.parseInt(scanner.nextLine());
        theList.get(crossOff - 1).setStatus("Done");
    }

    private void displayList() {
        for (Item item : theList) {
            System.out.println(size + ". " + item.displayItem(item));
        }
    }

    public static void main(String[] args) {
        new ToDoList();
    }
}