package ui.gui.panels;

import model.lists.ToDoList;
import ui.gui.buttons.OpenButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ListPanel extends JPanel implements ActionListener {
    private MainPanel mainPanel;
    private JButton deleteButton;
    private JLabel nameLabel;
    private ToDoList toDoList;
    private JPanel buttonPanel;

    public ListPanel(MainPanel mainPanel, ToDoList toDoList, Border border) {

        this.mainPanel = mainPanel;
        this.toDoList = toDoList;
        nameLabel = new JLabel(toDoList.getName());
        deleteButton = new JButton("Delete");
        buttonPanel = new JPanel();

        setUpDeleteButton();
        setBorder(border);
        // setSize(600, 80);
        Dimension dimension = new Dimension();
        dimension.setSize(600, 50);
        setMaximumSize(dimension);
        nameLabel.setPreferredSize(new Dimension(300, 45));
        add(nameLabel);

        buttonPanel.add(new OpenButton(toDoList, this));
        buttonPanel.add(deleteButton);
        add(buttonPanel);

    }

    private void setUpDeleteButton() {
        deleteButton.addActionListener(this);
    }

    public void removeFromMainPanel() {
        mainPanel.remove(this);
        mainPanel.updateUI();
    }

    @Override
    public MainPanel getParent() {
        return mainPanel;
    }


    public void actionPerformed(ActionEvent e) {
        getParent().getMainMenu().removeList(toDoList);
        removeFromMainPanel();
    }
}