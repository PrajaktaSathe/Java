package factoryMethod;

public class SUVCar implements Car {
	String model;
	String wheel;
	String engine;
	
    SUVCar(String model, String wheel, String engine) {
    	 this.model = model;
 		this.wheel = wheel;
 		this.engine = engine;
    } 
    
	public String getModel() {
		return model;
	}
	
	public void setWheel(String wheel) {
		this.wheel = wheel;
	}
	
	public String getWheel() {
		return wheel;
	}
	
	public void setEngine(String engine) {
		this.engine = engine;
	}
	
	public String getEngine() {
		return engine;
	}
}
