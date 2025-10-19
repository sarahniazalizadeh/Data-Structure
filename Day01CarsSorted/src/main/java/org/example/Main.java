package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    static ArrayList<Car> parking = new ArrayList<>();

    public static void main(String[] args) {

        readDataFromFile();

        for (Car car : parking) {
            System.out.println(car);
        }

        System.out.println("\nAfter sorting by make model (Comparable):\n");
        Collections.sort(parking);
        for (Car car : parking) {
            System.out.println(car);
        }

        System.out.println("\nAfter sorting by engine size (Comparator):\n");
        Collections.sort(parking, Car.SORT_BY_ENGINE_SIZE);
        for (Car car : parking) {
            System.out.println(car);
        }

        System.out.println("\nAfter sorting by production year (lambda):\n");
        parking.sort((Car c1, Car c2) -> c1.prodYear - c2.prodYear);
        for (Car car : parking) {
            System.out.println(car);
        }

        System.out.println("\nAfter sorting by production year and then by make model (comparator):\n");
        Comparator<Car> compareYearMakeModel = Comparator
                .comparingInt(Car::getProdYear)
                .thenComparing(Car::getMakeModel);
        parking.sort(compareYearMakeModel);
        for (Car car : parking) {
            System.out.println(car);
        }
    }

    static void readDataFromFile() {
        try (Scanner fileInput = new Scanner(new File("cars.txt"))) {
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] parts = line.split(";");
                if (parts.length != 3) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }
                String makeModel = parts[0];
                double engineSizeL = Double.parseDouble(parts[1]);
                int prodYear = Integer.parseInt(parts[2]);
                Car car = new Car(makeModel, engineSizeL, prodYear);
                parking.add(car);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        }
    }
}
