package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BinaryTreeStringInt tree = new BinaryTreeStringInt();
        tree.put("Liz", 31);
        tree.put("Ali", 40);
        tree.put("Samira", 28);
        tree.put("Jonathan", 81);
        tree.put("Yuji", 17);
        tree.put("Minh", 90);
        tree.put("Belinda", 38);
        tree.put("Abbas", 25);

        tree.printAllKeyValPairs();
    }
}