package lesson8.server.scheduled;

import lesson8.server.repositories.WeatherMeasurementRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleWeatherScheduler implements ApplicationRunner {
    @Autowired
    WeatherMeasurementRepository repo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(
                new SimpleWeatherSchedulerThreadFactory());
        scheduledExecutorService.scheduleWithFixedDelay(new WeatherLoadRunnable(repo), 0, 5, TimeUnit.SECONDS);
    }

    private static final class SimpleWeatherSchedulerThreadFactory implements ThreadFactory{
        private final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public Thread newThread(@NotNull Runnable r) {
            Thread t = new Thread(r, "SimpleWeatherScheduler#" + counter.getAndIncrement());
            t.setDaemon(true);
            return t;
        }
    }
}
