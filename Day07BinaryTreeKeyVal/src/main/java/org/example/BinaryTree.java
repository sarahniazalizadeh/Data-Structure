package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTree<K extends Comparable<K>,V> implements Iterable<Pair<K,V>> {

    class BinaryTreeIterator implements Iterator<Pair<K,V>> {
        private Pair<K,V>[] pairs;
        private int currentIndex = 0;

        public BinaryTreeIterator(Pair<K,V>[] pairs) {
            this.pairs = pairs;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < pairs.length;
        }

        @Override
        public Pair<K,V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the tree");
            }
            return pairs[currentIndex++];
        }
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        if (root == null) {
            return new BinaryTreeIterator((Pair<K,V>[]) new Pair[0]);
        }
        Pair<K,V>[] orderedPairs = getAllKeyValuePairsInOrder();
        return new BinaryTreeIterator(orderedPairs);
    }

    private Pair<K,V>[] getAllKeyValuePairsInOrder() {
        Pair<K,V>[] orderedPairs = (Pair<K,V>[]) new Pair[nodesCount];
        int[] indexWrapper = new int[]{0};
        getAllKeyValPairsInOrder(root, orderedPairs, indexWrapper);
        return orderedPairs;
    }

    private void getAllKeyValPairsInOrder(Node node, Pair<K,V>[] pairs, int[] indexWrapper) {
        if (node != null) {
            getAllKeyValPairsInOrder(node.left, pairs, indexWrapper);
            pairs[indexWrapper[0]++] = new Pair<>(node.key, node.value);
            getAllKeyValPairsInOrder(node.right, pairs, indexWrapper);
        }
    }

    private class Node {
        K key;
        V value;
        Node left, right;
    }

    Node root;
    private int nodesCount;

    public int getSize() {
        return nodesCount;
    }

    // if put attempts to insert a key that already exists then value is updated (no exception is thrown)
    // values may be duplicates but keys may not
    void put(K key, V value) {
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
    public V getValByKey(K key) throws RuntimeException {
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
//    void printAllKeyValPairs() {
//        collectKeyValPairsInOrder(root);
//    }
//
//    private void collectKeyValPairsInOrder(Node node) {
//        if (node != null) {
//            collectKeyValPairsInOrder(node.left);
//            System.out.println(node.key + " : " + node.value);
//            collectKeyValPairsInOrder(node.right);
//        }
//    }

    void printAllKeyValPairs() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        collectKeyValPairsInOrder(root, root.key.toString());
    }

    private void collectKeyValPairsInOrder(BinaryTree.Node node, String pathString) {
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

    K[] getKeysInOrder() {
        K[] result = (K[]) new Comparable[nodesCount];
        collectKeysInOrder(root, result, new int[]{0});
        return result;
    }

    private void collectKeysInOrder(Node node, K[] result, int[] index) {
        if (node != null) {
            collectKeysInOrder(node.left, result, index);
            result[index[0]++] = node.key;
            collectKeysInOrder(node.right, result, index);
        }
    }
}
