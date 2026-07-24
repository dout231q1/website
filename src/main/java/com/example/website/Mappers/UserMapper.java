package com.example.website.Mappers;

import com.example.website.DTOS.UserRequest;
import com.example.website.DTOS.UserResponse;
import com.example.website.Entities.UserEntity;
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
