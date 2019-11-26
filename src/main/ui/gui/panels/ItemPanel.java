package ui.gui.panels;

import model.items.Item;
import ui.gui.buttons.EditItemButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class ItemPanel extends JPanel implements ActionListener {
    private JCheckBox removeBox;
    private MainPanel mainPanel;
    private EditItemButton editItemButton;
    private Item item;
    private Dimension maxSize;

    public ItemPanel(Item item, MainPanel mainPanel) {
        removeBox = new JCheckBox("Remove");
        this.mainPanel = mainPanel;
        this.item = item;
        editItemButton = new EditItemButton();
        maxSize = new Dimension();
        maxSize.setSize(600, 50);

        setMaximumSize(maxSize);
        add(new JLabel(item.displayItem()));
        add(editItemButton);
        add(removeBox);
        removeBox.addActionListener(this);
        editItemButton.addActionListener(this);
        setSize(600, 80);
        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeBox) {
            mainPanel.remove(this);
            try {
                item.removeFromToDoList();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            mainPanel.updateUI();
        } else {
            mainPanel.displayEditItemMenu(item);
        }
    }
}