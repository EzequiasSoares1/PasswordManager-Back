package com.Tech.PasswordManager.unit.service;

import com.Tech.PasswordManager.ConfigSpringTest;
import com.Tech.PasswordManager.model.dto.UserDTO;
import com.Tech.PasswordManager.model.entity.User;
import com.Tech.PasswordManager.service.UserConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserConverterTest extends ConfigSpringTest {
    private UserDTO userDTO;
    private User user;

    @BeforeEach
    public void setUp() {
        userDTO = new UserDTO(1L, "testUser", "John", "password123");
        user = new User(1L, "testUser", "John", "password123");
    }

    @Test
    public void testConvertToUser() {
        User result = UserConverter.convertToUser(userDTO);
        assertNotNull(result);
        assertEquals(userDTO.id(), result.getId());
        assertEquals(userDTO.login(), result.getLogin());
        assertEquals(userDTO.name(), result.getName());
        assertEquals(userDTO.password(), result.getPassword());
    }

    @Test
    public void testConvertToUserDTO() {
        UserDTO result = UserConverter.convertToUserDTO(user);
        assertNotNull(result);
        assertEquals(user.getId(), result.id());
        assertEquals(user.getLogin(), result.login());
        assertEquals(user.getName(), result.name());
        // Password in UserDTO is expected to be null
        assertEquals(null, result.password());
    }

    @Test
    public void testConvertToUserDTOList() {
        List<User> users = Arrays.asList(user, user);
        List<UserDTO> result = UserConverter.convertToUserDTOList(users);
        assertNotNull(result);
        assertEquals(users.size(), result.size());
    }
}

