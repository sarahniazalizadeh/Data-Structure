package org.example;

import java.util.Comparator;

class Car implements Comparable<Car> {
    String makeModel;
    double engineSizeL;
    int prodYear;

    public Car(String makeModel, double engineSizeL, int prodYear) {
        this.makeModel = makeModel;
        this.engineSizeL = engineSizeL;
        this.prodYear = prodYear;
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
    public String toString() {
        return "Car{" +
                "makeModel='" + makeModel + '\'' +
                ", engineSizeL=" + engineSizeL +
                ", prodYear=" + prodYear +
                '}';
    }

    @Override
    public int compareTo(Car otherCar) {
        return makeModel.compareTo(otherCar.getMakeModel());
    }

    public static final CarsSortByEngineSize SORT_BY_ENGINE_SIZE = new CarsSortByEngineSize();

    static class CarsSortByEngineSize implements Comparator<Car> {
        @Override
        public int compare(Car o1, Car o2) {
            return (int)(o1.engineSizeL*1000000 - o2.engineSizeL*1000000);
        }
    }

}

