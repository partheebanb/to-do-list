package ui.gui;

import ui.gui.panels.ListPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButton extends JButton {
    ListPanel listPanel;
    ButtonListener buttonObserver;

    public DeleteButton(ListPanel panel) {
        buttonObserver = new ButtonListener();
        addActionListener(buttonObserver);
        setText("Delete");
        this.listPanel = panel;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listPanel.removeFromMainPanel();
        }
    }
}