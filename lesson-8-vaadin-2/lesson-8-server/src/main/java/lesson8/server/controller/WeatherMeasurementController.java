package lesson8.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lesson8.server.model.WeatherMeasurement;
import lesson8.server.repositories.WeatherMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("weather measurements controller")
public class WeatherMeasurementController {
    @Autowired
    WeatherMeasurementRepository repo;

    @GetMapping("/weather/measurement")
    @ApiOperation("get all weather measurements")
    public ResponseEntity<List<WeatherMeasurement>> getUsers() {
        return ResponseEntity.ok(repo.findAllByOrderByDateTime());
    }
}
