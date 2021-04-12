package lesson8.server.model;

import io.swagger.annotations.ApiModelProperty;
import lesson8.api.dto.WeatherMeasurementDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@SqlResultSetMapping(
        name = "userMapping",
        classes = @ConstructorResult(
                targetClass = WeatherMeasurementDto.class,
                columns = {
                        @ColumnResult(name = "id", type=Long.class),
                        @ColumnResult(name = "date", type=Date.class),
                        @ColumnResult(name = "temperature", type=Double.class)
                }
        )
)

@Entity
@Data
@EqualsAndHashCode
@Table(name = "weather")
public class WeatherMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @ApiModelProperty("entry id")
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @Column (name = "date_time")
    @ApiModelProperty("measurement date time")
    private Date dateTime;

    @Column (name = "temperature")
    @ApiModelProperty("temperature")
    private Double temperature;

    public WeatherMeasurement(JSONObject jsonObject){
        this.dateTime = Date.from(Instant.ofEpochSecond(jsonObject.getLong("dt")));
        JSONObject main = jsonObject.getJSONObject("main");
        this.temperature = main.getDouble("temp");
    }

    public WeatherMeasurement() {}

    public Date getDateTime() {
        return dateTime;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Long getId() {
        return id;
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
