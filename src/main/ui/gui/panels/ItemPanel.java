package ui.gui.panels;

import model.items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemPanel extends JPanel implements ActionListener {
    JCheckBox checkBox;
    MainPanel mainPanel;

    public ItemPanel(Item item, MainPanel mainPanel) {
        checkBox = new JCheckBox("Remove");
        this.mainPanel = mainPanel;

        add(new JLabel(item.displayItem()));
        add(checkBox);
        checkBox.addActionListener(this);
        setSize(600, 80);
        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public void actionPerformed(ActionEvent e) {
        mainPanel.remove(this);
        mainPanel.updateUI();
    }
}