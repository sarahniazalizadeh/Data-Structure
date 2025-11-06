package day01carssorted;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Day01CarsSorted {

    static ArrayList<Car> parking = new ArrayList<>();

    public static void main(String[] args) {
        
        Car cx1 = new Car("aaa", 2.2, 2000);
        Car cx2 = new Car("bbb", 2.3, 2001);
        
        System.out.println(cx1.equals(cx2));
  
        
        
/*
        double x = 1.0;
        for (int i = 0; i < 10; i++) {
            x -= 0.1;
        }
        System.out.println("x = " + x);
        if (x == 0) {
            System.out.println("Zero");
        } */

        readDataFromFile();
        System.out.println("==== Parking:");
        for (Car car : parking) {
            System.out.println(car);
        }
        System.out.println("==== Natural sort by make model using Comparable:");
        Collections.sort(parking);
        for (Car car : parking) {
            System.out.println(car);
        }
        System.out.println("==== Sort by production year using Comparator:");
        Collections.sort(parking, Car.sortByProdYear);
        for (Car car : parking) {
            System.out.println(car);
        }
        System.out.println("==== Sort by engine size using Comparator:");
        Collections.sort(parking, Car.sortByEngineSizeComparator);
        for (Car car : parking) {
            System.out.println(car);
        }
        System.out.println("==== Parking sorted by prod year:");
        // full anonymous method
        parking.sort((Car c1, Car c2) -> {
            System.out.println("Calling Dr. Lambda!");
            return c2.prodYear - c1.prodYear;
        });
        // short notation, one expression
        parking.sort((Car c1, Car c2) -> c2.prodYear - c1.prodYear);
        // print
        for (Car car : parking) {
            System.out.println(car);
        }
        //
        System.out.println("==== Parking sorted by prod year then make model:");
        Comparator<Car> compProdYearMakeModel = Comparator.comparing(Car::getProdYear).thenComparing(Car::getMakeModel); //.reversed();
        parking.sort(compProdYearMakeModel);
        for (Car car : parking) {
            System.out.println(car);
        }
    }

    static void readDataFromFile() {
        try (Scanner fileInput = new Scanner(new File("cars.txt"))) {
            while (fileInput.hasNextLine()) {
                try {
                    String line = fileInput.nextLine();
                    String[] data = line.split(";");
                    if (data.length != 3) {
                        System.out.println("Error in line, skipping");
                        continue;
                    }
                    String makeModel = data[0];
                    double engSize = Double.parseDouble(data[1]); // ex NumberFormatException
                    int yop = Integer.parseInt(data[2]); // ex NumberFormatException
                    parking.add(new Car(makeModel, engSize, yop));
                } catch (NumberFormatException ex) {
                    System.out.println("Error in line, skipping: " + ex);
                }
            }
        } catch (IOException ex) {
            System.out.println("Fatal error reading file: " + ex);
        }
    }

}
