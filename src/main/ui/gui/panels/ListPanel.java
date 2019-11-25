package ui.gui.panels;

import model.lists.ToDoList;
import ui.gui.DeleteButton;
import ui.gui.OpenButton;

import javax.swing.*;
import javax.swing.border.Border;


public class ListPanel extends JPanel {
    MainPanel mainPanel;

    public ListPanel(MainPanel mainPanel, ToDoList toDoList, Border border) {

        this.mainPanel = mainPanel;
        add(new JLabel(toDoList.getName()));
        add(new OpenButton(toDoList, this));
        add(new DeleteButton(this));
        setBorder(border);
        setSize(600, 80);
    }

    public void removeFromMainPanel() {
        mainPanel.remove(this);
        mainPanel.updateUI();
    }

    @Override
    public MainPanel getParent() {
        return mainPanel;
    }
}