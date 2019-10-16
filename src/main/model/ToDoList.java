package model;

import exceptions.ExceededMaxSizeException;

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
    private static final int MAX_SIZE = 15;
    private ArrayList<Item> theList;
    private Scanner scanner;
    private Integer size;

    public ToDoList() {
        theList = new ArrayList<>();
        scanner = new Scanner(System.in);
        size = 0;
    }

    public Integer getMaxSize() {
        return MAX_SIZE;
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

    public void setSize(Integer i) throws ExceededMaxSizeException {
        if (size < MAX_SIZE) {
            this.size = i;
        } else {
            throw new ExceededMaxSizeException();
        }
    }

    public void sort() {
        for (int j = 0; j < size - 1; j++) {
            for (int i = 0; i < size - 1; i++) {
                if (!(theList.get(i).getPriority() == "URGENT") && (theList.get(i + 1).getPriority() == "URGENT")) {
                    switchItems(i);
                }
                if ((theList.get(i).getPriority() == "Low") && !(theList.get(i + 1).getPriority() == "Low")) {
                    switchItems(i);
                }
            }
        }
    }

    public void switchItems(int i) {
        Item normalItem = theList.get(i + 1);
        theList.set(i + 1, theList.get(i));
        theList.set(i, normalItem);
        if (i > 0) {
            i--;
        }
    }

    public Item handlePriority(String option) {
        switch (option) {
            case "1":
                return new LowItem();
            case "3":
                return new UrgentItem();
            default:
                return new NormalItem();
        }
    }

    //REQUIRES: crossOff <= size of theList
    //MODIFIES: this
    //EFFECTS: marks item in theList at crossOff - 1 as complete
    public void removeItem(Integer crossOff) {
        theList.remove(crossOff - 1);
        size -= 1;
    }


    // EFFECTS: prints out all the items in theList formatted appropriately
    public void displayList() {
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + theList.get(i).displayItem());
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes all the data in theList and assigns to it all the data in listData
    public void load(String location) throws IOException {
        try {
            List<String> lines = Files.readAllLines(Paths.get(location));
            theList = new ArrayList<>();
            size = 0;

            for (String line : lines) {
                Item item;
                size += 1;
                ArrayList<String> parts = splitOnSpace(line);

                item = priorityDecider(parts.get(1));

                item.createItem(parts.get(0), parts.get(1), parts.get(2), new SimpleDateFormat(parts.get(3)));
                theList.add(item);
                // obtained some lines of code from FileReaderWriter.java
            }

        } catch (FileNotFoundException e) {
            System.out.println("This file could not be found. Please enter a valid file location: ");
            String loc = scanner.nextLine();
            save(loc);
        }
    }

    public Item priorityDecider(String priority) {
        if (priority == "Low") {
            return new LowItem();
        } else if (priority == "Normal") {
            return new NormalItem();
        } else {
            return new UrgentItem();
        }
    }

    // EFFECTS: saves all the data in toDoList into listData
    public void save(String location) throws FileNotFoundException, UnsupportedEncodingException {
        try {
            PrintWriter writer = new PrintWriter(location, "UTF-8");

            for (Item item : theList) {
                String line = item.getTitle() + " " + item.getPriority() + " " + item.getStatus() + " "
                        + item.getDueDate().toPattern();
                writer.println(line);
            }

            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("This file could not be found. Please enter a valid file location: ");
            String loc = scanner.nextLine();
            save(loc);
        } finally {
            System.out.println("File saved successfully!");
        }

        // obtained some lines of code from FileReaderWriter.java
    }

    // obtained function from FileReaderWriter.java
    // EFFECTS: splits up a string at spaces and puts the sub-strings into an arraylist
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}