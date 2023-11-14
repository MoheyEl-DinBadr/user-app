package com.example.mapper;

import com.example.dto.UserDTO;
import com.example.dto.UserGetDto;
import com.example.dto.UserPostDto;
import com.example.entity.UserEntity;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import java.time.Duration;

@ApplicationScoped
public class UserMapper {

    private final String signSecret;

    public UserMapper(@ConfigProperty(name = "sign.secret") String signSecret) {
        this.signSecret = signSecret;
    }

    public UserDTO mapUserEntityToDTO(UserEntity entity) {
        String jwt = Jwt.claim(Claims.email, entity.getEmail())
                .claim(Claims.full_name, entity.getFirstName() + " " + entity.getLastName())
                .expiresIn(Duration.ofMinutes(5))
                .signWithSecret(signSecret);
        return new UserDTO(entity.getId(), jwt);
    }

    public UserGetDto mapUserEntityToGetDTO(UserEntity entity) {
        return new UserGetDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.isMarketingConsent());
    }

    public Uni<UserEntity> mapUserPostDtoToEntity(UserPostDto postDto, String id) {
        return Uni.createFrom().item(postDto)
                .map(dto -> new UserEntity(id, dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.isMarketingConsent()));
    }
}
