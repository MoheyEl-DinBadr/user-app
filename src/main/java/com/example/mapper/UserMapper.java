package com.example.mapper;

import com.example.dto.UserDTO;
import com.example.dto.UserPostDto;
import com.example.entity.UserEntity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class UserMapper {

    public UserDTO mapUserEntityToDTO(UserEntity entity) {

        //TODO generate ID to send with salted text
        return null;
    }

    public Uni<UserEntity> mapUserPostDtoToEntity(UserPostDto postDto) {
        return Uni.createFrom().item(postDto)
                .map(dto -> new UserEntity(ObjectId.get(), dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.isMarketingConsent()));
    }
}
