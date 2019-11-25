package ui.gui.panels;

import model.items.Item;
import model.lists.ToDoList;
import javax.swing.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {

    public MainPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void displayList(ToDoList toDoList) {
        removeAll();
        ArrayList<Item> items = toDoList.getTheList();

        for (Item item : items) {
            add(new ItemPanel(item, this));
        }
        updateUI();
    }
}
