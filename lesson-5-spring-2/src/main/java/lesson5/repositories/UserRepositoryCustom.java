package lesson5.repositories;

import lesson5.dto.UserDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDto> getAll();
    List<UserDto> getAllByName(String name);
}
