package ui.gui.panels;

import model.items.Item;
import ui.gui.buttons.EditItemButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemPanel extends JPanel implements ActionListener {
    private JCheckBox removeBox;
    private MainPanel mainPanel;
    private EditItemButton editItemButton;
    private Item item;
    private Dimension maxSize;
    private JLabel itemLabel;

    public ItemPanel(Item item, MainPanel mainPanel) {
        removeBox = new JCheckBox("Remove");
        this.mainPanel = mainPanel;
        this.item = item;
        editItemButton = new EditItemButton();
        maxSize = new Dimension(600, 50);

        setMaximumSize(maxSize);
        itemLabel = new JLabel(item.displayItem());
        itemLabel.setPreferredSize(new Dimension(300, 50));
        add(itemLabel, BorderLayout.CENTER);
        add(editItemButton, BorderLayout.CENTER);
        add(removeBox, BorderLayout.LINE_END);
        removeBox.addActionListener(this);
        editItemButton.addActionListener(this);
        setSize(600, 80);
        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    // MODIFIES: this,  mainPanel
    // EFFECTS: removes selected item from its ToDoList and thus the panel if the remove box is selected or open the
    //      edit item menu in the main panel
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeBox) {
            mainPanel.remove(this);
            try {
                item.removeFromToDoList();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            mainPanel.updateUI();
        } else if (e.getSource() == editItemButton) {
            mainPanel.displayEditItemMenu(item);
        }
    }
}