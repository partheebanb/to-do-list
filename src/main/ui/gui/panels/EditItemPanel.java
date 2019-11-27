package ui.gui.panels;

import model.items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class EditItemPanel extends Panel implements ActionListener {
    protected Item item;
    private MainPanel mainPanel;
    protected JTextField titleField;
    protected JTextField dateField;
    private ButtonGroup buttonGroup;

    public EditItemPanel(Item item, MainPanel mainPanel) {
        this.item = item;
        this.mainPanel = mainPanel;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(Box.createVerticalStrut(15));
        add(createTitlePanel(item));
        add(Box.createVerticalStrut(15));
        add(createPriorityPanel(item));
        add(Box.createVerticalStrut(15));
        add(createDatePanel(item));
        add(Box.createVerticalGlue());
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
        Dimension maximumSize = new Dimension();

        maximumSize.setSize(300, 40);
        panel.setMaximumSize(maximumSize);
        panel.setMinimumSize(maximumSize);
        panel.add(new JLabel("Date: "));
        dateField = new JTextField(item.getDueDate().toPattern());
        dateField.setColumns(20);
        panel.add(dateField);
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
        titleField = new JTextField(item.getTitle());
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        Dimension maximumSize = new Dimension(300, 80);
        titleField.setColumns(40);

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
    // MODIFIES: mainPanel, this, item
    // EFFECTS: Take user back to list view if cancel is clicked or edits current item if okay is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cancel")) {
            mainPanel.displayList(mainPanel.getToDoList());
        } else if (e.getActionCommand().equals("Okay")) {
            item.setTitle(titleField.getText());
            item.setPriority(getPriority());
            try {
                item.setDueDate(new SimpleDateFormat(dateField.getText()));
            } catch (Exception ex) {
                item.setDueDate(popUpDate());
            }
            try {
                item.getToDoList().save();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            mainPanel.displayList(mainPanel.getToDoList());
        }
    }

    protected String getPriority() {
        if (buttonGroup.getSelection().getActionCommand().equals("Low")) {
            return "Low";
        } else if (buttonGroup.getSelection().getActionCommand().equals("Normal")) {
            return "Normal";
        } else {
            return "Urgent";
        }
    }

    protected SimpleDateFormat popUpDate() {
        try {
            return new SimpleDateFormat(JOptionPane.showInputDialog(this,
                    "Please enter a valid date?", "dd-mm-yyyy"));
        } catch (Exception e) {
            return popUpDate();
        }
    }
}