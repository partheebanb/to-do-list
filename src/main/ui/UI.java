package ui;

import exceptions.ExceededMaxSizeException;
import exceptions.InvalidChoiceException;
import model.items.Item;
import model.lists.ExamPrepList;
import model.lists.GeneralToDoList;
import model.lists.HomeWorkList;
import model.lists.ToDoList;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class UI {

    private Scanner scanner;
    public ToDoList currentToDoList;
    public ToDoList examPrepList;
    public ToDoList homeWorkList;
    public ToDoList generalToDoList;

    public UI() throws IOException {
        scanner = new Scanner(System.in);
        generalToDoList = new GeneralToDoList();
        examPrepList = new ExamPrepList();
        homeWorkList = new HomeWorkList();
        currentToDoList = generalToDoList;
        this.chooseListMenu();
    }

    public static void main(String[] args) throws IOException {
        UI ui = new UI();
    }

    public void setCurrentToDoList(String option) throws InvalidChoiceException {
        if (option.equals("1")) {
            currentToDoList = currentToDoList;
        } else if (option.equals("2")) {
            currentToDoList = examPrepList;
        } else if (option.equals("3")) {
            currentToDoList = homeWorkList;
        } else {
            throw new InvalidChoiceException();
        }
    }

    private void chooseListMenu() throws IOException {
        String option = "";
        while (true) {
            System.out.println("Please choose a list: \n    1: General \n    2: Exam prep \n    3: Homework "
                    + "\n    9: Quit");
            option = scanner.nextLine();

            if (checkQuit(option)) {
                break;
            }

            try {
                setCurrentToDoList(option);
                this.menu();
                break;
            } catch (InvalidChoiceException e) {
                System.out.println("Choose a valid option!");
            }
        }

    }

    private boolean checkQuit(String option) {
        if (option.equals("9")) {
            System.out.println("You have chosen to quit!");
            return true;
        }
        return false;
    }

    private void menu() throws IOException {
        String option = "";

        while (true) {
            System.out.println("Please select an option: \n    1: Add an item to the list"
                    + "\n    2: Remove an item \n    3: Display all items in list \n    4: Load saved list "
                    + "\n    5: Save list into file \n    6: Choose another list \n    9: Quit");
            option = scanner.nextLine();

            if (option.equals("6")) {
                chooseListMenu();
                break;
            } else if (checkQuit(option)) {
                break;
            }

            handleInput(option);
        }
    }

    // EFFECTS: handles all user input except for quiting
    public void handleInput(String option) throws IOException {
        if (option.equals("1")) {
            try {
                addItem();
            } catch (ExceededMaxSizeException e) {
                System.out.println("There are too many items in the todo list. Please try something else.");
            }
        } else if (option.equals("2")) {
            chooseItemToComplete();
        } else if (option.equals("3")) {
            currentToDoList.displayList();
        } else if (option.equals("4")) {
            currentToDoList.load();
        } else if (option.equals("5")) {
            currentToDoList.save();
        }

    }

    // MODIFIES: this
    // EFFECTS: creates a new item, fills its field and adds it to theList
    private void addItem() throws ExceededMaxSizeException {
        Item item;
        String priority;

        System.out.println("You have chosen to add an item! Please choose a priority: \n    1: Low "
                + "\n    2: Normal \n    3: High");
        priority = scanner.nextLine();
        item = currentToDoList.handlePriority(priority);
        inputItemData(item);
        currentToDoList.addItem(item);
        currentToDoList.displayList();

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
        Integer crossOff;

        currentToDoList.displayList();
        System.out.println("You have chosen to remove an item!");
        System.out.println("Which item would you like to remove?");
        crossOff = Integer.parseInt(scanner.nextLine());
        currentToDoList.removeItem(crossOff);
    }
}