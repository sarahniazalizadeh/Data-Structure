package org.example;

class Car {
    String makeModel;
    double engineSizeL;
    int prodYear;

    public Car(String makeModel, double engineSizeL, int prodYear) {
        this.makeModel = makeModel;
        this.engineSizeL = engineSizeL;
        this.prodYear = prodYear;
    }

    @Override
    public String toString() {
        return "Car{" +
                "makeModel='" + makeModel + '\'' +
                ", engineSizeL=" + engineSizeL +
                ", prodYear=" + prodYear +
                '}';
    }
}

