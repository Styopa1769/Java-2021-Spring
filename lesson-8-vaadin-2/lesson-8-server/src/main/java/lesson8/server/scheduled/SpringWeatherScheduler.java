package lesson8.server.scheduled;

import lesson8.server.repositories.WeatherMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpringWeatherScheduler {

    @Autowired
    WeatherMeasurementRepository repo;

    @Scheduled(cron = "*/30 * * * * *")
    public void getWeather() throws IOException {
        new WeatherLoadRunnable(repo).run();
    }
}
