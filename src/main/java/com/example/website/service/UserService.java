package com.example.website.service;

import com.example.website.infra.EmailAlreadyExistsException;
import com.example.website.infra.ErrorMessages;
import com.example.website.dto.UserRequest;
import com.example.website.dto.UserResponse;
import com.example.website.entity.UserEntity;
import com.example.website.infra.EntityNotFoundException;
import com.example.website.mapper.UserMapper;
import com.example.website.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request){
        UserEntity user = UserMapper.toUser(request);
        validateUniqueEmail(user.getEmail());
        UserEntity savedUser = userRepository.save(user);
        return UserMapper.toUserResponse(savedUser);
    }

    public UserResponse getUserById(Long id){
        UserEntity user = findUserById(id);
        return UserMapper.toUserResponse(user);
    }

    public List<UserResponse> listUsers(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toUserResponse)
                .toList();
    }

    public UserResponse updateUser(Long id, UserRequest request){
        UserEntity user = findUserById(id);
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        UserEntity updatedUser = userRepository.save(user);
        return UserMapper.toUserResponse(updatedUser);
    }

    public void deleteUser(Long id){
        UserEntity user = findUserById(id);
        userRepository.delete(user);
    }

    private UserEntity findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND.formatted(id)));
    }

    private void validateUniqueEmail(String email){
        if(userRepository.existsByEmail(email)){
            throw new EmailAlreadyExistsException(ErrorMessages.EMAIL_ALREADY_EXISTS.formatted(email));
        }
    }
}
