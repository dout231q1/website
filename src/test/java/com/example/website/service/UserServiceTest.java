package com.example.website.service;

import com.example.website.dto.UserRequest;
import com.example.website.dto.UserResponse;
import com.example.website.entity.UserEntity;
import com.example.website.infra.EntityNotFoundException;
import com.example.website.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_shouldReturnUserResponse_whenValidRequest() {
        UserRequest request = new UserRequest("Jobert", "jobert@gmail.com", "123456");

        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> {
            UserEntity savedUser = invocation.getArgument(0);
            ReflectionTestUtils.setField(savedUser, "id", 1L);
            return savedUser;
        });
        UserResponse response = userService.createUser(request);

        verify(userRepository).save(any(UserEntity.class));
        assertNotNull(response);
        assertEquals(new UserResponse(1L, "Jobert", "jobert@gmail.com"), response);
    }

    @Test
    void getUserById_shouldReturnUser_whenIdExists() {
        UserEntity user = new UserEntity("Jobert", "jobert@gmail.com", "123456");
        ReflectionTestUtils.setField(user, "id", 1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserResponse response = userService.getUserById(1L);

        verify(userRepository).findById(1L);
        assertNotNull(response);
        assertEquals(new UserResponse(1L, "Jobert", "jobert@gmail.com"), response);
    }

    @Test
    void getUserById_shouldThrowNotFoundException_whenIdDoesNotExist() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getUserById(99L));
        verify(userRepository).findById(99L);
    }

    @Test
    void listUsers_shouldReturnAllUsers() {
        UserEntity user = new UserEntity("Jobert", "jobert@gmail.com", "123456");
        UserEntity user2 = new UserEntity("Bruce", "bruce@gmail.com", "654321");
        ReflectionTestUtils.setField(user, "id", 1L);
        ReflectionTestUtils.setField(user2, "id", 2L);

        when(userRepository.findAll()).thenReturn(List.of(user, user2));
        List<UserResponse> response = userService.listUsers();

        verify(userRepository).findAll();
        assertEquals(2, response.size());
        assertEquals(List.of(
                new UserResponse(user.getId(), "Jobert", "jobert@gmail.com"),
                new UserResponse(user2.getId(), "Bruce", "bruce@gmail.com")),
                response
        );
    }

    @Test
    void listUsers_shouldReturnEmptyList_whenNoUsersExist() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<UserResponse> response = userService.listUsers();

        verify(userRepository).findAll();
        assertTrue(response.isEmpty());
    }

    @Test
    void updateUser_shouldReturnUpdatedUser_whenIdExists() {
        UserEntity existingUser = new UserEntity("Jobert", "jobert@gmail.com", "123456");
        ReflectionTestUtils.setField(existingUser, "id", 1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserRequest updatedRequest = new UserRequest("Bruce", "bruce@gmail.com", "654321");
        UserResponse response = userService.updateUser(1L, updatedRequest);

        verify(userRepository).findById(1L);
        verify(userRepository).save(any(UserEntity.class));
        assertNotNull(response);
        assertEquals(new UserResponse(1L, "Bruce", "bruce@gmail.com"), response);
    }

    @Test
    void updateUser_shouldThrowNotFoundException_whenIdDoesNotExist() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        UserRequest request = new UserRequest("Jobert", "jobert@gmail.com", "123456");

        assertThrows(EntityNotFoundException.class, () -> userService.updateUser(99L, request));
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}