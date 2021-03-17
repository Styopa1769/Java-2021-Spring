package lesson6.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson6.api.dto.UserDto;

import java.io.IOException;
import java.net.URL;

import static lesson6.client.config.Config.API_ADDRESS;

public class ClientApp {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        try {
            UserDto userDto = OBJECT_MAPPER.readValue(new URL(API_ADDRESS + "/users/1"), UserDto.class);
            System.out.println(userDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
