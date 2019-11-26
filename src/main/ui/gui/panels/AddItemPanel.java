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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cancel")) {
            mainPanel.displayList(mainPanel.getToDoList());
        } else if (e.getActionCommand().equals("Okay")) {
            item.setTitle(titleField.getText());
            item.setPriority(getPriority());
            item.setDueDate(new SimpleDateFormat(dateField.getText()));
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
