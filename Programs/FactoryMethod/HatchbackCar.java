package FactoryMethod;

public class HatchbackCar implements FactoryMethod.Car {
    String model;
    String wheel;
    String engine;

    HatchbackCar(String model, String wheel, String engine) {
        this.model = model;
        this.wheel = wheel;
        this.engine = engine;
    }

    public String getModel() {
        return model;
    }

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}
