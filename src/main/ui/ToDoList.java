package ui;

import sun.awt.geom.Crossings;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    private ArrayList<String> theList;
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
            System.out.println("Please select an option: \n 1. Add an item to the list "
                    + "\n 2. Remove an item from the list \n 3. Display all items in list \n 4. Quit" );
            option = scanner.nextLine();

            if (option.equals("4")) {
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
                displayList();
        }
    }
    private void addItem() {
        String NewItem;

        size++;
        System.out.println("Enter the new item");
        NewItem = Integer.toString(size) + ". " + scanner.nextLine();
        theList.add(NewItem);
        displayList();
    }

    private void removeItem() {
        Integer CrossOff = 0;

        displayList();
        System.out.println("Which item would you like to cross off the list?");
        CrossOff = Integer.parseInt(scanner.nextLine());
        theList.set(CrossOff - 1, theList.get(CrossOff - 1) + " (CROSSED OFF)");
    }

    private void displayList() {
        for (String item: theList) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        new ToDoList();
    }
}
