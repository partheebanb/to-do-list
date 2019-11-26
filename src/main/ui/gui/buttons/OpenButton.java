package ui.gui.buttons;

import model.lists.ToDoList;
import ui.gui.panels.ListPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenButton extends JButton {
    private OpenButtonListener openButtonListener;
    private ToDoList toDoList;
    private ListPanel listPanel;

    public OpenButton(ToDoList toDoList, ListPanel listPanel) {
        openButtonListener = new OpenButtonListener();
        this.toDoList = toDoList;
        this.listPanel = listPanel;
        addActionListener(new OpenButtonListener());
        setText("Open List");
    }

    private class OpenButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            listPanel.getParent().removeAll();
            listPanel.getParent().displayList(toDoList);
        }
    }
}