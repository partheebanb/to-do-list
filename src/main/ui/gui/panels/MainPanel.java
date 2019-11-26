package ui.gui.panels;

import model.items.Item;
import model.lists.ToDoList;
import ui.gui.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainPanel extends JPanel implements ActionListener {
    private MainMenu mainMenu;
    private ToDoList toDoList;
    private Item item;

    public MainPanel(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public ToDoList getToDoList() {
        return toDoList;
    }


    public void displayList(ToDoList toDoList) {
        this.toDoList = toDoList;

        removeAll();
        ArrayList<Item> items = toDoList.getTheList();


        for (Item item : items) {
            add(new ItemPanel(item, this));
        }

        add(Box.createVerticalGlue());
        add(createListMenuPanel());
        add(Box.createRigidArea(new Dimension(20,20)));

        updateUI();
    }

    private JPanel createListMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton addItemButton = new JButton("+");
        addItemButton.setActionCommand("Add item");
        addItemButton.addActionListener(this);

        JButton backButton = new JButton("Back");
        backButton.setActionCommand("Back");
        backButton.addActionListener(this);

        panel.add(addItemButton);
        panel.add(backButton);
        return panel;
    }

    public void displayEditItemMenu(Item item) {
        removeAll();
        add(new EditItemPanel(item, this));
        updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            try {
                removeAll();
                mainMenu.displayMainMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals("Add item")) {
            removeAll();
            add(new AddItemPanel(toDoList, this));
            updateUI();
        }
    }
}