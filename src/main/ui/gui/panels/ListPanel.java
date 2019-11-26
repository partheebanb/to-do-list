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
    private ToDoList toDoList;

    public ListPanel(MainPanel mainPanel, ToDoList toDoList, Border border) {

        this.mainPanel = mainPanel;
        this.toDoList = toDoList;
        add(new JLabel(toDoList.getName()));
        add(new OpenButton(toDoList, this));

        deleteButton = new JButton("Delete");
        setUpDeleteButton();
        add(deleteButton);
        setBorder(border);
        // setSize(600, 80);
        Dimension dimension = new Dimension();
        dimension.setSize(600, 50);
        setMaximumSize(dimension);
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