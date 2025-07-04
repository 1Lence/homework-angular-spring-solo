package org.example.bakcendspring.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.bakcendspring.dto.filters.FilterUserDto;
import org.example.bakcendspring.dto.request.UserRequest;
import org.example.bakcendspring.dto.response.UserResponse;
import org.example.bakcendspring.entity.UserEntity;
import org.example.bakcendspring.repositories.UserRepository;
import org.example.bakcendspring.services.base.CrudService;
import org.example.bakcendspring.services.mappers.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements CrudService<Long, UserRequest, UserResponse, FilterUserDto> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + id)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        return userMapper.toUserDto(findById(id));
    }

    @Transactional(readOnly = true)
    public UserResponse getAll(FilterUserDto filterUserDto) {
        var entity = userRepository
                .findByFilters(filterUserDto.userName(), filterUserDto.fullName(), filterUserDto.email()).orElseThrow(
                        () -> new EntityNotFoundException("user not found")
                );

        return userMapper.toUserDto(entity);
    }

    @Override
    @Transactional
    public void create(UserRequest userRequest) {
        var entity = userRepository.findByUserNameOrEmail(userRequest.userName(),userRequest.email());

        if (entity != null) {
            throw new EntityExistsException("Пользователь с таким ником или почтой уже существует");
        }

        userRepository.save(userMapper.toUserEntity(userRequest));
    }

    @Override
    @Transactional
    public void update(UserRequest userRequest, Long id) {
        var entity = findById(id);

        entity.setEmail(userRequest.email());
        entity.setFullName(userRequest.fullName());
        entity.setUserName(userRequest.userName());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}