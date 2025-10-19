package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static HashMap<String, ArrayList<String>> playersByTeams = new HashMap<>();

    public static void main(String[] args) {

        try (Scanner fileInput = new Scanner(new File("teams.txt"))) {
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] lineSplit = line.split(":");

                if (lineSplit.length != 2) continue;

                String teamName = lineSplit[0];
                String[] teamMembers = lineSplit[1].split(",");

                for (String member : teamMembers) {
                    playersByTeams.computeIfAbsent(member, k -> new ArrayList<>()).add(teamName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String member : playersByTeams.keySet()) {
            System.out.println(member + " plays in: " + String.join(", ", playersByTeams.get(member)));
        }
    }
}