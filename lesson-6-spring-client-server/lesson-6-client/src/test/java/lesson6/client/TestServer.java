package lesson6.client;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson6.api.dto.UserDto;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static lesson6.client.config.Config.API_ADDRESS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestServer {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    @DisplayName("Тест сервера")
    void test_server(){
        try (CloseableHttpClient httpclient = HttpClientBuilder.create().build()){
            InputStream jsonUser = getContentFromUri(API_ADDRESS + "/users/1", httpclient);
            UserDto userDto = OBJECT_MAPPER.readValue(jsonUser, UserDto.class);
            Assertions.assertEquals("name", userDto.getName());

            JavaType userList = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, UserDto.class);

            InputStream jsonUsers = getContentFromUri(API_ADDRESS + "/users", httpclient);
            List<UserDto> users = OBJECT_MAPPER.readValue(jsonUsers, userList);
            Assertions.assertTrue(users.size() >= 1);
            Assertions.assertTrue(users.stream().anyMatch(user -> user.getName().equals("name")));
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

    private static InputStream getContentFromUri(String uri, HttpClient httpClient) throws IOException {
        HttpGet httpget = new HttpGet(uri);
        String base64Auth = Base64.getEncoder().encodeToString("styopa:hash".getBytes(StandardCharsets.UTF_8));
        httpget.addHeader("Authorization", "Basic " + base64Auth);
        HttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        return entity.getContent();
    }
}
