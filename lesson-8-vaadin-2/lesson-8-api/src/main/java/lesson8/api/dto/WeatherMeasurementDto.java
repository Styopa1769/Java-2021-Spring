package lesson8.api.dto;

import java.util.Date;

@SuppressWarnings("unused")
public class WeatherMeasurementDto {
    private Long id;

    private Date dateTime;

    private Double temperature;

    public WeatherMeasurementDto() {
    }

    public WeatherMeasurementDto(Long id, Date dateTime, Double temperature) {
        this.id = id;
        this.dateTime = dateTime;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
