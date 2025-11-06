package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTreeOfInts implements Iterable<Integer> {

    class BinaryTreeOfIntsIterator implements Iterator<Integer> {
        private int[] values;
        private int currentIndex = 0;

        public BinaryTreeOfIntsIterator(int[] values) {
            this.values = values;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < values.length;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the tree.");
            }
            return values[currentIndex++];
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        if (root == null) {
            return new BinaryTreeOfIntsIterator(new int[0]);
        }
        int[] orderedValues = getValuesInOrder();
        return new BinaryTreeOfIntsIterator(orderedValues);
    }

    private class NodeOfInt {
        int value; // could also be key, value pair
        NodeOfInt left, right;
    }

    NodeOfInt root;
    private int nodesCount;

//    void put(int value) throws IllegalArgumentException {
//        this.root = put(this.root, value);
//    }
//
//    NodeOfInt put(NodeOfInt currentNode, int value) throws IllegalArgumentException {
//        if (currentNode==null) {
//            NodeOfInt newNode = new NodeOfInt();
//            newNode.value = value;
//            nodesCount++;
//            return newNode;
//        }
//        if(currentNode.value == value) {
//            throw new IllegalArgumentException();
//        }
//        if (value < currentNode.value) {
//            currentNode.left = put(currentNode.left, value);
//        } else {
//            currentNode.right = put(currentNode.right, value);
//        }
//        return currentNode;
//    }


    // throws exception if put attempts to insert value that already exists (a duplicate)
    void put(int value) throws IllegalArgumentException {
        NodeOfInt newNode = new NodeOfInt();
        newNode.value = value;
        NodeOfInt current = root;
        if (root == null) {
            root = newNode;
            nodesCount++;
            return;
        }
        while (true) {
            if (current.value == value) {
                throw new IllegalArgumentException();
            }
            if (value < current.value) {
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


    public int getSize() {
        return nodesCount;
    }

    public boolean hasValue(int val) {
        NodeOfInt current = root;
        if (current == null) {
            return false;
        }
        while (current != null) {
            if (current.value == val) {
                return true;
            }
            if (val < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    /// / uses recursion to compute the sum of all values in the entire tree
    public int getSumOfAllValues() {
        return getSumOfThisAndSubNodes(root);
    }

    /// / private helper recursive method to implement the above method
    private int getSumOfThisAndSubNodes(NodeOfInt node) {
        return node.value + ((node.left != null) ? getSumOfThisAndSubNodes(node.left) : 0)
                + ((node.right != null) ? getSumOfThisAndSubNodes(node.right) : 0);
    }


    /// / uses recursion to collect all values from largest to smallest
    int[] getValuesInOrder() { // from largest to smallest
        resultArray = new int[nodesCount];
        resultIndex = 0;
        collectValuesInOrder(root, resultArray);
        return resultArray;
    }

    /// / private helper recursive method to implement the above method
    private void collectValuesInOrder(NodeOfInt node, int[] resultArray) {
        if (node == null) {
            return;
        }
        if (node.right != null) {
            collectValuesInOrder(node.right, resultArray);
        }
        resultArray[resultIndex] = node.value;
        resultIndex++;
        if (node.left != null) {
            collectValuesInOrder(node.left, resultArray);
        }
    }

//// data structures used to make collecting values in order easier
private int[] resultArray;
private int resultIndex;
}
