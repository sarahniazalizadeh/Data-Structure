package org.example;

public class BinaryTreeStringInt {
    private class Node {
        String key;
        int value;
        Node left, right;
    }

    Node root;
    private int nodesCount;

    public int getSize() {
        return nodesCount;
    }

    // if put attempts to insert a key that already exists then value is updated (no exception is thrown)
    // values may be duplicates but keys may not
    void put(String key, int value) {
        Node newNode = new Node();
        newNode.key = key;
        newNode.value = value;
        Node current = root;
        if (root == null) {
            root = newNode;
            nodesCount++;
            return;
        }
        while (true) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                current.value = value;
                return;
            }
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = newNode;
                    nodesCount++;
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    nodesCount++;
                    return;
                }
                current = current.right;
            }
        }
    }

    // throws exception if key is not found, otherwise returns the value
    public int getValByKey(String key) throws RuntimeException {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                return current.value;
            }
            if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        throw new RuntimeException("Key not found");
    }

    // print out all key-value pairs (one per line) from the smallest key to the largest
    void printAllKeyValPairs() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        collectKeyValPairsInOrder(root, root.key);
    }

    private void collectKeyValPairsInOrder(Node node, String pathString) {
        if (node.left != null) {
            String leftPath = pathString + " -Left-> " + node.left.key;
            collectKeyValPairsInOrder(node.left, leftPath);
        }
        System.out.println(pathString + " => " + node.value);
        if (node.right != null) {
            String rightPath = pathString + " -Right-> " + node.right.key;
            collectKeyValPairsInOrder(node.right, rightPath);
        }
    }
}
