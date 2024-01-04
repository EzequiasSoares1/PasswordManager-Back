package com.Tech.PasswordManager.model.service;
import com.Tech.PasswordManager.model.dto.UserDTO;
import com.Tech.PasswordManager.model.entity.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public static User convertToUser(UserDTO userDTO) {
        return new User(
                userDTO.id(),
                userDTO.login(),
                userDTO.name(),
                userDTO.password()
        );
    }

    public static UserDTO convertToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getLogin(),
                user.getName(),
                null
        );
    }
    public static List<UserDTO> convertToUserDTOList(List<User> users) {
        return users.stream()
                .map(UserConverter::convertToUserDTO)
                .collect(Collectors.toList());
    }
}
