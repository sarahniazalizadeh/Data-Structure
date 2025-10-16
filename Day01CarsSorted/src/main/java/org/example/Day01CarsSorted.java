package org.example;

import java.util.ArrayList;
import java.util.Comparator;

public class Day01CarsSorted implements Comparator<Car> {

    public static void main(String[] args) {
        ArrayList<Car> parking = new ArrayList<>();
        Car car1 = new Car("Toyota Camry", 2.5, 2020);
        Car car2 = new Car("Volvo XC90", 2.0, 2019);
        Car car3 = new Car("Ford Mustang", 5.0, 2021);
        Car car4 = new Car("Nissan Leaf", 2.5, 2020);
        Car car5 = new Car("Jeep Wrangler", 3.5, 2017);
        parking.add(car1);
        parking.add(car2);
        parking.add(car3);
        parking.add(car4);
        parking.add(car5);

        for (Car car : parking) {
            System.out.println(car.toString());
        }
    }

    @Override
    public int compare(Car o1, Car o2) {
        int engineCompare = o1.makeModel.compareTo(o2.makeModel);
        int yearCompare = o1.makeModel.compareTo(o2.makeModel);
        int makeModelCompare = o1.makeModel.compareTo(o2.makeModel);
        return 0;
    }
}

