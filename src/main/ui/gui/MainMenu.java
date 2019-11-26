package ui.gui;

import model.Saveable;
import model.items.Item;
import model.lists.ToDoList;
import ui.gui.panels.AddNewListPanel;
import ui.gui.panels.ListPanel;
import ui.gui.panels.MainPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

//    public static void main(String[] args) throws IOException {
//        new MainMenu();
//    }

    public MainMenu() throws IOException {
        mainPanel = new MainPanel(this);
        displayMainMenu();
    }

    public void displayMainMenu() throws IOException {
        lists = new ArrayList<>();
        panels = new ArrayList<>();

        setSize(600,400);
        setTitle("Bruh List");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        loadLists();
        convertListsToPanels();
        addListPanelsToMainPanel();
        mainPanel.add(addNewListButton());
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

    public void removeList(ToDoList toDoList) {
        lists.remove(toDoList);
        save();
    }

    @Override
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
}