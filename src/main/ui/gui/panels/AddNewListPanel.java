package ui.gui.panels;

import model.lists.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddNewListPanel extends Panel implements ActionListener {
    private JTextField listName;
    private JLabel label;
    private MainPanel mainPanel;
    private JButton backButton;
    private JButton addButton;

    public AddNewListPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        label = new JLabel();
        listName = new JTextField();
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        addButton = new JButton("Add");
        addButton.addActionListener(this);

        label.setText("Name: ");
        listName.setColumns(40);
        add(label);
        add(listName);
        add(addButton);
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            try {
                mainPanel.removeAll();
                mainPanel.getMainMenu().displayMainMenu();
                mainPanel.updateUI();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == addButton) {
            mainPanel.getMainMenu().addList(new ToDoList(listName.getText()));
        }
    }
}