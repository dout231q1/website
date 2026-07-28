package com.example.website.mapper;

import com.example.website.dto.UserRequest;
import com.example.website.dto.UserResponse;
import com.example.website.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMapper {

    public static UserEntity toUser(UserRequest request){
        return new UserEntity(
                request.name(),
                request.email(),
                request.password()
        );
    }

    public static UserResponse toUserResponse(UserEntity user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
