package lesson8.rest;

import com.fasterxml.jackson.databind.JavaType;
import lesson8.api.dto.WeatherMeasurementDto;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.List;

/**
 * Позволяет веб-клиенту получать из API информацию о пользователях
 */
public final class WeatherMeasurementApi extends BaseApi{

    private final CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public List<WeatherMeasurementDto> getAll(){
        JavaType userList = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, WeatherMeasurementDto.class);
        return getResponse(HttpGet.METHOD_NAME, "", userList, List.of());
    }

    @Override
    protected String getApiPath() {
        return "weather/measurements";
    }

    @Override
    protected HttpClient getClient() {
        return httpClient;
    }
}
