package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class App {
    private JButton btSelectDirectory;
    private JPanel mainPanel;
    private JLabel lblPath;
    private JLabel lblSumNonRec;
    private JLabel lblSumRec;

    public static JFrame frame;
    private File selectedDirectory;

    public App() {
        btSelectDirectory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Choose a directory");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    selectedDirectory = chooser.getSelectedFile();
                    lblPath.setText(selectedDirectory.toString());
                    double sizeNonRec = getDirSizeNonRec(selectedDirectory);
                    lblSumNonRec.setText(String.format("%.2f", sizeNonRec));
                    double sizeRec = getDirSizeRec(selectedDirectory);
                    lblSumRec.setText(String.format("%.2f", sizeRec));
                } else {
                    System.out.println("No Selection ");
                }
            }
        });
    }

    private double getDirSizeNonRec(File dir) {
        long sum = 0L;
        File[] files = dir.listFiles();
        for (File file : files) {
            sum += file.length();
        }
        return sum / (double) 1024;
    }

    private double getDirSizeRec(File dir) {
        long sum = 0L;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                sum += getDirSizeRec(file) * 1024;
            } else {
                sum += file.length();
            }
        }
        return sum / (double) 1024;
    }

    public static void main(String[] args) {
        frame = new JFrame("App");
        frame.setContentPane(new App().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
