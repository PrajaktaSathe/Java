package Adapter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AdapterTest {

    // Custom implementation of ServiceInterface for testing purposes
    static class TestService implements ServiceInterface {
        private String capturedData; // Stores the last action performed

        @Override
        public void performAction(String data) {
            this.capturedData = data;
        }

        public String getCapturedData() {
            return capturedData;
        }
    }

    @Test
    void testClientRequestThroughAdapter() {
        // Arrange: Create a test implementation of ServiceInterface
        TestService testService = new TestService();

        // Wrap the test service with the Adapter
        TargetInterface adapter = new Adapter(testService);

        // Create the Client with the Adapter
        Client client = new Client(adapter);

        // Act: Client sends a request
        String testRequest = "Test Request";
        client.execute(testRequest);

        // Assert: Verify the service captured the correct input
        assertEquals(testRequest, testService.getCapturedData(), "The service should receive the correct request from the adapter.");
    }

    @Test
    void testAdapterDirectTranslation() {
        // Arrange: Create a test implementation of ServiceInterface
        TestService testService = new TestService();

        // Wrap the test service with the Adapter
        TargetInterface adapter = new Adapter(testService);

        // Act: Call the adapter's method directly
        String testInput = "Adapter Test Input";
        adapter.processRequest(testInput);

        // Assert: Verify the service captured the correct input
        assertEquals(testInput, testService.getCapturedData(), "The adapter should directly translate the request to the service.");
    }

    @Test
    void testMultipleRequests() {
        // Arrange: Create a test implementation of ServiceInterface
        TestService testService = new TestService();

        // Wrap the test service with the Adapter
        TargetInterface adapter = new Adapter(testService);

        // Create the Client with the Adapter
        Client client = new Client(adapter);

        // Act: Client sends multiple requests
        String request1 = "Request One";
        String request2 = "Request Two";

        client.execute(request1);
        assertEquals(request1, testService.getCapturedData(), "The service should capture the first request.");

        client.execute(request2);
        assertEquals(request2, testService.getCapturedData(), "The service should capture the second request.");
    }
}