package Adapter;

public class Client {

    private final TargetInterface target;

    public Client(TargetInterface target) {
        this.target = target;
    }

    public void execute(String request) {
        target.processRequest(request);
    }
}