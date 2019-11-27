package ui.gui;

import model.Saveable;
import model.lists.ToDoList;
import ui.gui.panels.AddNewListPanel;
import ui.gui.panels.ListPanel;
import ui.gui.panels.MainPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

public class MainMenu extends JFrame implements Saveable, ActionListener {
    private MainPanel mainPanel;
    private ArrayList<JPanel> panels;
    private ArrayList<ToDoList> lists;
    private final String fileLocations = "C:\\Users\\bpart\\CPSC 210\\Labs\\project_w8d2b\\data\\ListLocations";

    public MainMenu() throws IOException {
        mainPanel = new MainPanel(this);
        displayMainMenu();
    }

    // MODIFIES: this
    // EFFECTS: displays the main menu of the program
    public void displayMainMenu() throws IOException {
        lists = new ArrayList<>();
        panels = new ArrayList<>();
        setSize(600,400);
        setTitle("A Magnificent To Do List");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        loadLists();
        convertListsToPanels();
        addListPanelsToMainPanel();
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(addNewListButton());
        mainPanel.add(Box.createRigidArea(new Dimension(20,20)));
        add(mainPanel);
        setVisible(true);
    }

    private JButton addNewListButton() {
        JButton newListButton = new JButton("+");
        newListButton.addActionListener(this);
        newListButton.setActionCommand("New");
        return newListButton;
    }

    private void addListPanelsToMainPanel() {
        for (JPanel panel: panels) {
            mainPanel.add(Box.createHorizontalGlue());
            mainPanel.add(panel);
        }
    }

    private void convertListsToPanels() {
        Border border = BorderFactory.createRaisedBevelBorder();
        for (ToDoList toDoList : lists) {
            panels.add(new ListPanel(mainPanel, toDoList, border));
        }
    }

    private void loadLists() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileLocations));
        for (String line: lines) {
            lists.add(new ToDoList(line));
        }
    }

    // MODIFIES: this
    // EFFECTS: removes ToDoList from lists and saves lists
    public void removeList(ToDoList toDoList) {
        lists.remove(toDoList);
        save();
    }


    // EFFECTS: saves the name of all the toDoLists in lists to a file
    public void save() {
        try {
            PrintWriter writer = new PrintWriter(fileLocations, "UTF-8");

            for (ToDoList list : lists) {
                String line = list.getName();
                writer.println(line);
            }
            writer.close();
            System.out.println("File saved successfully!");
        } catch (Exception e) {
            System.out.println("Invalid file location");
        }
    }

    @Override
    // MODIFIES: this, mainPanel
    // EFFECTS: opens up the add list view if the + button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New")) {
            addListScreen();
        }
    }

    private void addListScreen() {
        mainPanel.removeAll();
        mainPanel.add(new AddNewListPanel(mainPanel));
        mainPanel.updateUI();
    }

    // MODIFIES: this
    //EFFECTS: adds ToDoList to lists if its name isn't a duplicate
    public void addList(ToDoList toDoList) {
        Boolean duplicate = false;
        for (ToDoList list : lists) {
            if (list.getName().equals(toDoList.getName())) {
                duplicate = true;
            }
        }
        if (!duplicate) {
            lists.add(toDoList);
            save();
        }
    }
}