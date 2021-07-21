package factoryMethod;

public class SUVCarFactory implements CarFactory {
	@Override
	public Car buildCar(String model, String wheel, String engine) {      
        Car car = new SUVCar(model, wheel, engine);
        return car;   
	}
}
