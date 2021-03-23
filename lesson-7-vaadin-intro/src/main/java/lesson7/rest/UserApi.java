package lesson7.rest;

import com.fasterxml.jackson.databind.JavaType;
import lesson6.api.dto.UserDto;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.List;

/**
 * Позволяет веб-клиенту получать из API информацию о пользователях
 */
public final class UserApi extends BaseApi{
    private final static String USERS_PATH = "users";

    private final CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public UserDto getById(long id){
        JavaType user = OBJECT_MAPPER.getTypeFactory().constructType(UserDto.class);
        return getResponse(HttpGet.METHOD_NAME, "/"+id, user, new UserDto());
    }

    public List<UserDto> getAll(){
        JavaType userList = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, UserDto.class);
        return getResponse(HttpGet.METHOD_NAME, "", userList, List.of());
    }

    @Override
    protected String getApiPath() {
        return USERS_PATH;
    }

    @Override
    protected HttpClient getClient() {
        return httpClient;
    }
}
