package lesson8.server.repositories;

import lesson8.server.model.WeatherMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherMeasurementRepository extends JpaRepository<WeatherMeasurement, Long> {
    List<WeatherMeasurement> findAllByOrderByDateTime();
}
