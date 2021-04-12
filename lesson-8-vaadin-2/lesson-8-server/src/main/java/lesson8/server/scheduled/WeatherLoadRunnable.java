package lesson8.server.scheduled;

import lesson8.server.json.JsonReader;
import lesson8.server.model.WeatherMeasurement;
import lesson8.server.repositories.WeatherMeasurementRepository;

import java.io.IOException;

public class WeatherLoadRunnable implements Runnable{

    private final WeatherMeasurementRepository repo;

    public WeatherLoadRunnable(WeatherMeasurementRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run() {
        String key = "de85a2f93f3b5233586d28b2077f0319";
        String moscowId = "524901";
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?id=%s&appid=%s", moscowId, key);
        try {
            WeatherMeasurement weather = new WeatherMeasurement(JsonReader.readJsonFromUrl(url));
            // лучше логгер, мне лень настраивать :)
            System.out.println("Measurement recorded: " + repo.save(weather));
        } catch (IOException e) {
            // лучше логгер, мне лень настраивать :)
            System.out.println("Measurement not recorded because of: " + e.getMessage());
        }
    }
}
