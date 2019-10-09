package ui;

import model.Item;
import model.LowItem;
import model.NormalItem;
import model.UrgentItem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ToDoList implements Loadable, Saveable {
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

    public Item getItem(Integer i) {
        return theList.get(i);
    }

    // EFFECTS: presents the menu of options and accepts user input, quiting the program if required
    private void menu() throws IOException {
        String option = "";

        while (true) {
            System.out.println("Please select an option: \n 1: Add an item to the list"
                     + "\n 2: Mark an item as complete \n 3: Display all items in list \n 4: Load saved list "
                     + "\n 5: Save list into file \n 6: Quit");
            option = scanner.nextLine();

            if (option.equals("6")) {
                System.out.println("You have chosen to quit!");
                break;
            }

            handleInput(option);
        }
    }

    // EFFECTS: handles all user input except for quiting
    private void handleInput(String option) throws IOException {
        switch (option) {
            case "1":
                addItem();
                break;
            case "2":
                chooseItemToComplete();
                break;
            case "3":
                displayList();
                break;
            case "4":
                load("ListData.txt");
                break;
            case "5":
                save("ListData.txt");
                break;
            default: break;
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new item, fills its field and adds it to theList
    private void addItem() {
        size++;
        Item item;
        String option = "";

        System.out.println("You have chosen to add an item! Please choose a priority: \n 1: Low "
                + "\n 2: Normal \n 3: High");
        option = scanner.nextLine();
        item = handlePriority(option);
        item.inputItemData();
        theList.add(item);
        displayList();
    }

    private Item handlePriority(String option) {
        switch (option) {
            case "1":
                return new LowItem();
            case "3":
                return new UrgentItem();
            default: return new NormalItem();
        }
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
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + theList.get(i).displayItem());
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes all the data in theList and assigns to it all the data in listData
    public void load(String location) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(location));
        theList = new ArrayList<>();
        size = 0;

        for (String line : lines) {
            NormalItem normalItem = new NormalItem();
            SimpleDateFormat dueDate;
            size += 1;

            ArrayList<String> parts = splitOnSpace(line);
            normalItem.setTitle(parts.get(0));
            normalItem.setPriority(parts.get(1));
            normalItem.setStatus(parts.get(2));
            dueDate = new SimpleDateFormat(parts.get(3));
            normalItem.setDueDate(dueDate);

            theList.add(normalItem);

            // obtained some lines of code from FileReaderWriter.java
        }
    }

    // EFFECTS: saves all the data in toDoList into listData
    public void save(String location) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(location, "UTF-8");

        for (Item item : theList) {
            String line = item.getTitle() + " " + item.getPriority() + " " + item.getStatus() + " "
                    + item.getDueDate().toPattern();
            writer.println(line);
        }

        writer.close();

        // obtained some lines of code from FileReaderWriter.java
    }

    // obtained function from FileReaderWriter.java
    // EFFECTS: splits up a string at spaces and puts the sub-strings into an arraylist
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public static void main(String[] args) throws IOException {
        ToDoList toDoList = new ToDoList();
        toDoList.menu();
    }
}