package ui.gui.panels;

import model.items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditItemPanel extends Panel implements ActionListener {
    private Item item;
    private MainPanel mainPanel;
    private JTextField titleField;
    private ButtonGroup buttonGroup;

    public EditItemPanel(Item item, MainPanel mainPanel) {
        this.item = item;
        this.mainPanel = mainPanel;
        add(createTitlePanel(item));
        add(createPriorityPanel(item));
        add(createDatePanel(item));
        add(okayAndCancelPanel(item));
    }

    private JPanel okayAndCancelPanel(Item item) {
        JPanel panel = new JPanel();

        JButton okayButton = new JButton("Okay");
        okayButton.setActionCommand("Okay");
        okayButton.addActionListener(this);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(this);

        panel.add(okayButton);
        panel.add(cancelButton);
        return panel;
    }

    private Component createDatePanel(Item item) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Date: "));
        Dimension maximumSize = new Dimension();
        maximumSize.setSize(300, 40);
        panel.setMaximumSize(maximumSize);
        panel.setMinimumSize(maximumSize);
        return panel;
    }

    private JPanel createPriorityPanel(Item item) {
        JPanel panel = new JPanel();
        Dimension maximumSize = new Dimension();
        maximumSize.setSize(300, 40);

        panel.setMaximumSize(maximumSize);
        panel.add(new JLabel("Priority: "));
        addPriorityRadioButtons(panel, item);
        add(panel);
        return panel;
    }

    private JPanel createTitlePanel(Item item) {
        JTextField titleField = new JTextField(item.getTitle());
        Dimension maximumSize = new Dimension();
        maximumSize.setSize(300, 40);
       // titleField.setSize(800, 40);
        titleField.setMaximumSize(maximumSize);
        titleField.setMinimumSize(maximumSize);
        this.titleField = titleField;
        this.titleField.setColumns(40);

        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Title: "));
        titlePanel.add(titleField);
        titlePanel.setMaximumSize(maximumSize);
        return titlePanel;
    }

    private void addPriorityRadioButtons(JPanel panel, Item item) {
        JRadioButton lowButton = new JRadioButton("Low");
        lowButton.setActionCommand("Low");
        JRadioButton normalButton = new JRadioButton("Normal");
        normalButton.setActionCommand("Normal");
        JRadioButton urgentButton = new JRadioButton("Urgent");
        urgentButton.setActionCommand("Urgent");
        buttonGroup = setUpButtonGroup(lowButton, normalButton, urgentButton);

        if (item.getPriority().equals("Low")) {
            lowButton.setSelected(true);
        } else if (item.getPriority().equals("Normal")) {
            normalButton.setSelected(true);
        } else {
            urgentButton.setSelected(true);
        }
        panel.add(lowButton);
        panel.add(normalButton);
        panel.add(urgentButton);
    }

    private ButtonGroup setUpButtonGroup(JRadioButton lowButton, JRadioButton normalButton, JRadioButton urgentButton) {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(lowButton);
        buttonGroup.add(normalButton);
        buttonGroup.add(urgentButton);
        return buttonGroup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cancel")) {
            mainPanel.displayList(mainPanel.getToDoList());
        } else if (e.getActionCommand().equals("Okay")) {
            item.setTitle(titleField.getText());
            item.setPriority(getPriority());
            try {
                item.getToDoList().save();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            mainPanel.displayList(mainPanel.getToDoList());
        }
    }

    private String getPriority() {
        if (buttonGroup.getSelection().getActionCommand().equals("Low")) {
            return "Low";
        } else if (buttonGroup.getSelection().getActionCommand().equals("Normal")) {
            return "Normal";
        } else {
            return "Urgent";
        }
    }
}