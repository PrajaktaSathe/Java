package factoryMethod;

public class TestFactoryPattern {
	CarFactory carBuilder;
	Car car;
    public static void main(String[] args) {
    	TestFactoryPattern client = new TestFactoryPattern();
    	client.buildCarMethod();
    }
    
    public void buildCarMethod() {
    	System.out.println("Building Hatchback car...");
		carBuilder = new HatchbackCarFactory();
		car = carBuilder.buildCar("Polo", "Michelin", "VW");
        System.out.println("Car is assembled!");
        System.out.println("Specifications of car are: ");
        System.out.println("Model: " + car.getModel());
        System.out.println("Engine: " + car.getEngine());
        System.out.println("Wheel: " + car.getWheel());
		
        System.out.println();
        
		System.out.println("Building Sedan car...");
		carBuilder = new SedanCarFactory();
		car = carBuilder.buildCar("Dzire", "MRF", "Suzuki");
		System.out.println("Car is assembled!");
        System.out.println("Specifications of car are: ");
        System.out.println("Model: " + car.getModel());
        System.out.println("Engine: " + car.getEngine());
        System.out.println("Wheel: " + car.getWheel());
		
        System.out.println();
        
		System.out.println("Building SUV car...");
		carBuilder = new SUVCarFactory();
		car = carBuilder.buildCar("Innova", "Bridgestone", "Fiat");
		System.out.println("Car is assembled!");
        System.out.println("Specifications of car are: ");
        System.out.println("Model: " + car.getModel());
        System.out.println("Engine: " + car.getEngine());
        System.out.println("Wheel: " + car.getWheel());
    }
}
