package com.example.service;

import com.example.dto.UserDTO;
import com.example.dto.UserPostDto;
import com.example.entity.UserEntity;
import com.example.mapper.UserMapper;
import com.example.repo.UserRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Uni<UserEntity> findById(String id) {
        return userRepository.findById(ObjectId.get());
    }

    public Uni<UserDTO> saveUser(UserPostDto postDto) {
        return userMapper.mapUserPostDtoToEntity(postDto)
                .flatMap(userRepository::persist)
                .map(userMapper::mapUserEntityToDTO)
    }
}
