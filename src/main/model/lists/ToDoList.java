package model.lists;

import exceptions.ExceededMaxSizeException;
import model.*;
import model.items.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class ToDoList extends Observable implements Loadable, Saveable {
    private static final int MAX_SIZE = 15;
    private ArrayList<Item> theList;
    private Scanner scanner;
    private Integer size;
    private String location;
    private String name;

    public ToDoList(String name) {
        theList = new ArrayList<>();
        scanner = new Scanner(System.in);
        size = 0;
        this.name = name;
        location = "C:\\Users\\bpart\\CPSC 210\\Labs\\project_w8d2b\\data\\" + name;
        try {
            load();
        } catch (IOException e) {
            new File(location);
        }
        addObserver(new ToDoListWatcher());
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

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: increments size unless would size exceed max size, in which case it throws an exception
    public void setSize(Integer i) throws ExceededMaxSizeException {
        if (size < MAX_SIZE) {
            this.size = i;
        } else {
            throw new ExceededMaxSizeException();
        }
    }

    // MODIFES: this
    // EFFECTS: sorts the list with urgent items at the top and low priority items at the bottom
    public void sort() {
        for (int j = 0; j < theList.size() - 1; j++) {
            for (int i = 0; i < theList.size() - 1; i++) {
                if (!(theList.get(i).getPriority().equals("Urgent"))
                        && (theList.get(i + 1).getPriority().equals("Urgent"))) {
                    switchItems(i);
                }
                if ((theList.get(i).getPriority().equals("Low")) && !(theList.get(i + 1).getPriority().equals("Low"))) {
                    switchItems(i);
                }
            }
        }
    }

    // REQUIRES: size >= i + 1
    // EFFECTS: switches the positions of items at i and i+1
    public void switchItems(int i) {
        Item normalItem = theList.get(i + 1);
        theList.set(i + 1, theList.get(i));
        theList.set(i, normalItem);
    }


    //REQUIRES: crossOff <= size of theList
    //MODIFIES: this
    //EFFECTS: marks item in theList at crossOff - 1 as complete
    public void removeItem(Integer crossOff) {
        theList.remove(crossOff - 1);
        setChanged();
        notifyObservers();
        size -= 1;
    }

    // REQUIRES: item in theList
    // MODIFIES: this
    // EFFECTS: removes item from the list
    public void removeItem(Item item) throws FileNotFoundException, UnsupportedEncodingException {
        theList.remove(item);
        save();
    }

    // MODIFIES: this
    // EFFECTS: deletes all the data in theList and assigns to it all the data in generalToDoList
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(location));
        theList = new ArrayList<>();
        size = 0;

        convertLinesToItems(lines);
    }

    private void convertLinesToItems(List<String> lines) {
        for (String line : lines) {
            Item item = new Item();

            size += 1;
            ArrayList<String> parts = splitOnSpace(line);
            item.setPriority((parts.get(0)));
            item.setDueDate(new SimpleDateFormat(parts.get(1)));

            String title = "";
            for (int i = 2; i < parts.size(); i++) {
                title = title + parts.get(i) + " ";
            }
            item.setTitle(title);
            item.setToDoList(this);
            theList.add(item);
            // obtained some lines of code from FileReaderWriter.java
        }
    }

    // EFFECTS: saves all the data in toDoList into generalToDoList
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        sort();
        PrintWriter writer = new PrintWriter(location, "UTF-8");

        for (Item item : theList) {
            String line = item.getPriority() + " " + item.getDueDate().toPattern() + " " + item.getTitle();
            writer.println(line);
        }
        writer.close();
        System.out.println("File saved successfully!");
        // obtained some lines of code from FileReaderWriter.java
    }


    // obtained function from FileReaderWriter.java
    // EFFECTS: splits up a string at spaces and puts the sub-strings into an arraylist
    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    // MODIFIES: this
    // EFFECTS: adds item to the list if item isn't in the list and list doesn't exceed MAXSIZE
    public void addItem(Item item) throws ExceededMaxSizeException {
        if (!theList.contains(item)) {
            if (size++ > MAX_SIZE) {
                throw new ExceededMaxSizeException();
            } else {
                theList.add(item);
                item.setToDoList(this);
                sort();
            }
        }
    }
}