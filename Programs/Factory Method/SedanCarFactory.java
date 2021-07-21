package factoryMethod;

public class SedanCarFactory implements CarFactory {
	@Override
	public Car buildCar(String model, String wheel, String engine) {
		Car car = new SedanCar(model, wheel, engine);
        return car;  
	}
}
