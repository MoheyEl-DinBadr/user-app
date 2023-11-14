package com.example.service;

import com.example.dto.UserDTO;
import com.example.dto.UserGetDto;
import com.example.dto.UserPostDto;
import com.example.mapper.UserMapper;
import com.example.repo.UserRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BadRequestException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static com.example.utils.EncryptionUtil.encryptText;
import static com.example.utils.ValidationUtil.validateUserPostDto;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final String userSalt;


    public UserService( UserRepository userRepository, UserMapper userMapper,
                       @ConfigProperty(name = "user.salt") String userSalt) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userSalt = userSalt;
    }

    public Uni<UserGetDto> findById(String id) {
        return userRepository.findById(id)
                .onItem()
                .ifNull()
                .failWith(new BadRequestException("Request User doesn't exist"))
                .map(userMapper::mapUserEntityToGetDTO)
                .invoke(dto -> {
                    if (!dto.isMarketingConsent()) dto.setEmail(null);
                });
    }

    public Uni<UserDTO> saveUser(UserPostDto postDto) {
        validateUserPostDto(postDto);
        var id = encryptText(postDto.getEmail().toLowerCase(), userSalt);
        return userMapper.mapUserPostDtoToEntity(postDto, id)
                .flatMap(userRepository::persist)
                .map(userMapper::mapUserEntityToDTO);
    }
}
