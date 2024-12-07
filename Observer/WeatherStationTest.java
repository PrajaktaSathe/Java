package Observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherStationTest {
    private WeatherStation weatherStation;
    private TestObserver phoneDisplay;
    private TestObserver tvDisplay;

    @BeforeEach
    void setUp() {
        weatherStation = new WeatherStation();
        phoneDisplay = new TestObserver();
        tvDisplay = new TestObserver();
    }

    @Test
    void testAddObserverAndNotifyObservers() {
        // Add observers
        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(tvDisplay);

        // Simulate weather change
        weatherStation.setWeather("Rainy");

        // Verify that all observers received the update
        assertEquals("Rainy", phoneDisplay.getWeather());
        assertEquals("Rainy", tvDisplay.getWeather());
    }

    @Test
    void testRemoveObserver() {
        // Add observers
        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(tvDisplay);

        // Remove one observer
        weatherStation.removeObserver(phoneDisplay);

        // Simulate weather change
        weatherStation.setWeather("Cloudy");

        // Verify only the remaining observer received the update
        assertEquals(null, phoneDisplay.getWeather());
        assertEquals("Cloudy", tvDisplay.getWeather());
    }

    @Test
    void testNoObservers() {
        // Simulate weather change without any observers
        weatherStation.setWeather("Windy");

        // No exceptions should occur, nothing to verify
    }

    // Custom TestObserver class
    private static class TestObserver implements Observer {
        private String weather;

        @Override
        public void update(String weather) {
            this.weather = weather;
        }

        public String getWeather() {
            return weather;
        }
    }
}

