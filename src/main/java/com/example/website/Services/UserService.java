package com.example.website.Services;

import com.example.website.DTOS.UserRequest;
import com.example.website.DTOS.UserResponse;
import com.example.website.Entities.UserEntity;
import com.example.website.Mappers.UserMapper;
import com.example.website.Repositories.UserRepository;
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
        UserEntity savedUser = userRepository.save(user);
        return UserMapper.toUserResponse(savedUser);
    }

    public UserResponse getUserById(Long id){
        UserEntity user = userRepository.findById(id).orElse(null);
        if(user == null){
            return null;
        }
        return UserMapper.toUserResponse(user);
    }

    public List<UserResponse> listUsers(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toUserResponse)
                .toList();
    }
}
