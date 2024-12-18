package Adapter;

public class Adapter implements TargetInterface {
    private final ServiceInterface service;

    public Adapter(ServiceInterface service) {
        this.service = service;
    }

    @Override
    public void processRequest(String input) {
        // Translating the Client's request to the Service's interface
        service.performAction(input);
    }
}