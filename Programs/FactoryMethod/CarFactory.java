package FactoryMethod;

public interface CarFactory {
    public abstract Car buildCar(String model, String wheel, String engine);
}
