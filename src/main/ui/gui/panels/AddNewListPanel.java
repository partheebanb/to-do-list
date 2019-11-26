package ui.gui.panels;

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

    public AddNewListPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        label = new JLabel();
        listName = new JTextField();
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        label.setText("Name: ");
        listName.setColumns(40);
        add(label);
        add(listName);
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            mainPanel.removeAll();
            mainPanel.getMainMenu().displayMainMenu();
            mainPanel.updateUI();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}