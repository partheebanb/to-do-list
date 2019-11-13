package model;

import java.util.Observable;
import java.util.Observer;

public class ToDoListWatcher implements Observer {
    int completed;

    public ToDoListWatcher() {
        completed = 0;
    }

    @Override
    public void update(Observable o, Object arg) {
        completed++;
        System.out.println("You have completed " + completed + " items in this list.");
    }
}

