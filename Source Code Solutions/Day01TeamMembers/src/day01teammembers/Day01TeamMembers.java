package day01teammembers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day01TeamMembers {

    static HashMap<String, ArrayList<String>> playersWithTeams = new HashMap<>();
    
    public static void main(String[] args) {
        try (Scanner fileInput = new Scanner(new File("teams.txt"))) {
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine(); // ex
                String []lineSplit = line.split(":");
                if (lineSplit.length != 2) {
                    System.out.println("Invalid data in line, skipping");
                    continue;
                }
                String teamName = lineSplit[0];
                String []teamMembers = lineSplit[1].split(",");
                //
                for (String memberName : teamMembers) {
                    if (!playersWithTeams.containsKey(memberName)) {
                        // player not found, create a new entry (list with one item)
                        ArrayList teamsList = new ArrayList();
                        teamsList.add(teamName);
                        playersWithTeams.put(memberName, teamsList);
                    } else {
                        // player is found, add team name to an existing entry
                        ArrayList<String> teamsList = playersWithTeams.get(memberName);
                        teamsList.add(teamName);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Fatal error: unable to read file, " + ex.getMessage());
        }
        // done reading file, display the result
        for (String memberName : playersWithTeams.keySet()) {
            ArrayList<String> teamsList = playersWithTeams.get(memberName);
            System.out.printf("%s plays in : %s\n", memberName, String.join(", ", teamsList));
        }
    }

}
