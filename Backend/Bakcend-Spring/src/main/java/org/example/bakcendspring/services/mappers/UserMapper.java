package org.example.bakcendspring.services.mappers;

import org.example.bakcendspring.dto.request.UserRequest;
import org.example.bakcendspring.dto.response.UserResponse;
import org.example.bakcendspring.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserResponse toUserDto(UserEntity userEntity) {
        return UserResponse
                .builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .userName(userEntity.getUserName())
                .fullName(userEntity.getFullName())
                .build();
    }

    public UserEntity toUserEntity(UserRequest userRequest) {
        return UserEntity
                .builder()
                .email(userRequest.email())
                .fullName(userRequest.fullName())
                .userName(userRequest.userName())
                .build();
    }
}