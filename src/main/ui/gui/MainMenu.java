package ui.gui;

import model.lists.ToDoList;

import java.awt.Toolkit;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

public class MainMenu extends JFrame {
    Toolkit toolkit;
    JPanel mainPanel;
    ArrayList<JPanel> panels;
    ArrayList<ToDoList> lists;
    final String fileLocations = "C:\\Users\\bpart\\CPSC 210\\Labs\\project_w8d2b\\data\\ListLocations";
    //ListenForButton listenForButton;




    public static void main(String[] args) throws IOException {
        new MainMenu();
    }

    public MainMenu() throws IOException {
        toolkit = Toolkit.getDefaultToolkit();
        lists = new ArrayList<>();
        mainPanel = new JPanel();
        panels = new ArrayList<>();

        setSize(600,400);
        setTitle("Bruh List");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        loadLists();
        setUpMainPanel();
        convertListsToPanels();
        addListPanelsToMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private void addListPanelsToMainPanel() {
        for (JPanel panel: panels) {
            mainPanel.add(panel);
        }
    }

    private void convertListsToPanels() {
        Border border = BorderFactory.createRaisedBevelBorder();

        for (ToDoList toDoList : lists) {
            JPanel panel = new JPanel();
            panel.add(new JLabel(toDoList.getName()));
            panel.add(new JButton("Delete"));
            panel.setBorder(border);
            panel.setSize(600, 80);
            panels.add(panel);
        }
    }

    private void setUpMainPanel() {
        toolkit = Toolkit.getDefaultToolkit();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    }

//    private class ListenForButton implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == button) {
//                buttonClicked++;
//                textField.setText(Integer.toString(buttonClicked));
//            }
//        }
//    }

    private void loadLists() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileLocations));
        for (String line: lines) {
            lists.add(new ToDoList(line));
        }
    }
}