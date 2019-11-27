package ui.gui.panels;

import model.items.Item;
import model.lists.ToDoList;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

public class AddItemPanel extends EditItemPanel {
    private ToDoList toDoList;
    private MainPanel mainPanel;

    public AddItemPanel(ToDoList toDoList, MainPanel mainPanel) {
        super(new Item(), mainPanel);
        this.mainPanel = mainPanel;
        this.toDoList = toDoList;

    }

    // MODIFIES: this, item, mainPanel, toDoList
    // EFFECTS: Take user back to list view if cancel is clicked or adds a new item to toDoList based on user input
    //      if okay is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cancel")) {
            mainPanel.displayList(mainPanel.getToDoList());
        } else if (e.getActionCommand().equals("Okay")) {
            item.setTitle(titleField.getText());
            item.setPriority(getPriority());
            try {
                item.setDueDate(new SimpleDateFormat(dateField.getText()));
            } catch (Exception ex) {
                item.setDueDate(popUpDate());
            }
            try {
                toDoList.addItem(item);
                toDoList.save();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            mainPanel.displayList(mainPanel.getToDoList());
        }
    }
}
