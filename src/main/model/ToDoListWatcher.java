package model;

import java.util.Observable;
import java.util.Observer;

public class ToDoListWatcher implements Observer {
    int completed;

    public ToDoListWatcher() {
        completed = 0;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: increments counter for completed items and prints out the number of completed items whenever an item
    //      is completed
    public void update(Observable o, Object arg) {
        completed++;
        System.out.println("You have completed " + completed + " items in this list.");
    }
}