package com.example.website.Mappers;

import com.example.website.DTOS.UserRequest;
import com.example.website.DTOS.UserResponse;
import com.example.website.Entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserMapper {

    public static UserEntity toUser(UserRequest request){
        UserEntity user = new UserEntity();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        return user;
    }

    public static UserResponse toUserResponse(UserEntity user){
        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
        return response;
    }
}
