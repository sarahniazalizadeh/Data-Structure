package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BinaryTreeOfInts tree = new BinaryTreeOfInts();

        tree.put(40);
        tree.put(28);
        tree.put(31);
        tree.put(81);
        tree.put(17);
        tree.put(90);
        tree.put(38);

        for (int n : tree)
        {
            System.out.println("value: " + n);
        }

    }
}