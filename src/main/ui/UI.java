//package ui;
//
//import exceptions.ExceededMaxSizeException;
//import exceptions.InvalidChoiceException;
//import model.items.Item;
//import model.lists.ToDoList;
//import network.Welcome;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class UI {
//
//    private Scanner scanner;
//    public ArrayList<ToDoList> lists;
//    private ToDoList currentToDoList;
//    final String fileLocations = "C:\\Users\\bpart\\CPSC 210\\Labs\\project_w8d2b\\data\\ListLocations";
//
//    public static void main(String[] args) throws IOException {
//        Welcome.welcome();
//        new UI();
//    }
//
//    public UI() throws IOException {
//        scanner = new Scanner(System.in);
//        lists = new ArrayList<>();
//        loadLists();
//        chooseListMenu();
//    }
//
//    private void loadLists() throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get(fileLocations));
//        for (String line: lines) {
//            lists.add(new ToDoList(line));
//        }
//    }
//
//    private void chooseListMenu() throws IOException {
//        String option = "";
//        while (true) {
//            System.out.println("Please choose a list:");
//            printLists();
//            option = scanner.nextLine();
//
//            if (option.equals("99")) {
//                System.out.println("You have chosen to quit!");
//                break;
//            }
//
//            if (option.equals("98")) {
//                addNewList();
//            }
//
//            try {
//                setCurrentToDoList(Integer.parseInt(option));
//                menu();
//                break;
//            } catch (Exception e) {
//                System.out.println("Choose a valid option!");
//            }
//        }
//    }
//
//    private void addNewList() throws IOException {
//        System.out.println("Name your new list");
//        String name = scanner.nextLine();
//        lists.add(new ToDoList(name));
//        Files.write(Paths.get(fileLocations), ("\n" + name).getBytes(), StandardOpenOption.APPEND);
//        // obtained above line from StackOverflow
//
//    }
//
//    private void setCurrentToDoList(Integer option) throws Exception {
//        currentToDoList = lists.get(option - 1);
//    }
//
//    private void printLists() {
//        for (int i = 0; i < lists.size(); i++) {
//            System.out.println("    " + (i + 1) + ". " + lists.get(i).getName());
//        }
//        System.out.println("    " + 98 + ". Add a new list");
//        System.out.println("    " + 99 + ". Quit");
//    }
//
//    private void menu() throws IOException {
//        String option;
//
//        while (true) {
//            System.out.println("Please select an option: \n    1: Add an item to the list"
//                    + "\n    2: Remove an item \n    3: Display all items in list \n    4: Load saved list "
//                    + "\n    5: Save list into file \n    6: Choose another list \n    7: Quit");
//            option = scanner.nextLine();
//
//            if (option.equals("6")) {
//                chooseListMenu();
//                break;
//            } else if (option.equals("7")) {
//                System.out.println("You have chosen to quit!");
//                break;
//            }
//
//            handleInput(option);
//        }
//    }
//
//    // EFFECTS: handles all user input except for quiting
//    private void handleInput(String option) throws IOException {
//        if (option.equals("1")) {
//            addItem();
//        } else if (option.equals("2")) {
//            chooseItemToComplete();
//        } else if (option.equals("3")) {
//            currentToDoList.displayList();
//        } else if (option.equals("4")) {
//            currentToDoList.load();
//        } else if (option.equals("5")) {
//            currentToDoList.save();
//        }
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS: creates a new item, fills its field and adds it to theList
//    private void addItem() {
//        try {
//            Item item;
//            String option;
//
//            System.out.println("You have chosen to add an item! Please choose a priority: \n    1: Low "
//                    + "\n    2: Normal \n    3: Urgent");
//            option = scanner.nextLine();
//            item = currentToDoList.handlePriority(option);
//            inputItemData(item);
//            currentToDoList.addItem(item);
//            currentToDoList.sort();
//        } catch (ExceededMaxSizeException e) {
//            System.out.println("There are too many items in the todo list. Please try something else.");
//        } finally {
//            currentToDoList.displayList();
//        }
//    }
//
//    private Item inputItemData(Item item) {
//        System.out.println("Enter a title for the new item!");
//        item.setTitle(scanner.nextLine());
//        System.out.println("Enter a due date for the new item in the form dd-mm-yyyy");
//        item.setDueDate(new SimpleDateFormat(scanner.nextLine()));
//        return item;
//    }
//
//    // MODIFIES: this
//    // EFFECTS: allows the user to choose an item to mark as complete
//
//    private void chooseItemToComplete() {
//        Integer crossOff;
//
//        currentToDoList.displayList();
//        System.out.println("You have chosen to remove an item!");
//        System.out.println("Which item would you like to remove?");
//        crossOff = Integer.parseInt(scanner.nextLine());
//        currentToDoList.removeItem(crossOff);
//    }
//}