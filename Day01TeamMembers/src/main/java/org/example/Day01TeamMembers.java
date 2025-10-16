package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day01TeamMembers {
    public static void main(String[] args) {

        File file = new File("src/teams.txt");
        HashMap<String, ArrayList<String>> memberToTeams = new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String teamName = line.split(":")[0];
                String members = line.split(":")[1];
                String[] memberList = members.split(",");

                for (String member : memberList) {
                    memberToTeams.computeIfAbsent(member, k -> new ArrayList<>()).add(teamName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String member : memberToTeams.keySet()) {
            System.out.println(member + " plays in: " + String.join(", ", memberToTeams.get(member)));
        }
    }
}

