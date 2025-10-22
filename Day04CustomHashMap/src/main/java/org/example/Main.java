package org.example;


public class Main {
    public static void main(String[] args) {
        CustomHashMapStringString myHashTable = new CustomHashMapStringString();
        myHashTable.putValue("paint","red");
        myHashTable.printDebug();
    }
}