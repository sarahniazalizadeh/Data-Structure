/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package day01carssorted;

import java.util.Comparator;

/**
 *
 * @author grego
 */
public class Car implements Comparable<Car> {

    static final double FP_THRESHOLD = 0.000000001;

    String makeModel;

    double engineSizeL;
    int prodYear;

    public Car(String makeModel, double engineSizeL, int prodYear) {
        this.makeModel = makeModel;
        this.engineSizeL = engineSizeL;
        this.prodYear = prodYear;
    }
    
    @Override
    public boolean equals(Object o) {
        Car c = (Car) o;
        System.out.println("Equals on car called");
        return (makeModel.equals(c.makeModel) && engineSizeL == c.engineSizeL && prodYear == c.prodYear);
    }

    @Override
    public String toString() {
        return "Car{" + "makeModel=" + makeModel + ", engineSizeL=" + engineSizeL + ", prodYear=" + prodYear + '}';
    }

    public String getMakeModel() {
        return makeModel;
    }

    public double getEngineSizeL() {
        return engineSizeL;
    }

    public int getProdYear() {
        return prodYear;
    }

    @Override
    public int compareTo(Car o) {
        return makeModel.compareTo(o.makeModel);
    }

    public static final CarsSortByProdYear sortByProdYear = new CarsSortByProdYear();

    static class CarsSortByProdYear implements Comparator<Car> {

        @Override
        public int compare(Car o1, Car o2) {
            System.out.print("C ");
            return o1.prodYear - o2.prodYear;
        }

    }

    public static final CarSortByEngineSizeComparator sortByEngineSizeComparator = new CarSortByEngineSizeComparator();

    static class CarSortByEngineSizeComparator implements Comparator<Car> {

        @Override
        public int compare(Car car1, Car car2) {
            /* // SIMPLEST, no delta
            // return (int)(car1.engineSizeL - car2.engineSizeL); // WRONG!!!
            if (car1.engineSizeL == car2.engineSizeL) {
                return 0;
            }
            return (car1.engineSizeL > car2.engineSizeL) ? 1 : -1;
             */
 /* // TOLERANCE version 1
            if (Math.abs(car1.engineSizeL - car2.engineSizeL) < FP_THRESHOLD) return 0;
            return (car1.engineSizeL > car2.engineSizeL) ? 1 : -1;
             */
            return (int) (1000000 * car1.engineSizeL) - (int) (1000000 * car2.engineSizeL); // works for integer value
        }
    }

}
