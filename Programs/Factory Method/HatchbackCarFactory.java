package factoryMethod;

public class HatchbackCarFactory implements CarFactory {
	@Override
	public Car buildCar(String model, String wheel, String engine) {
        Car car = new HatchbackCar(model, wheel, engine);
        return car;  
	}
}
