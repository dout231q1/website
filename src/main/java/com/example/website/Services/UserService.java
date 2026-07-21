package com.example.website.Services;

import com.example.website.DTOS.UserRequest;
import com.example.website.DTOS.UserResponse;
import com.example.website.Entities.UserEntity;
import com.example.website.Mappers.UserMapper;
import com.example.website.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request){
        UserEntity user = UserMapper.toUser(request);
        UserEntity savedUser = userRepository.save(user);
        UserResponse response = UserMapper.toUserResponse(savedUser);
        return response;
    }
}
