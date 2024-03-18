package com.jakan.webflux.transformer;

import com.jakan.webflux.bean.User;
import com.jakan.webflux.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer extends AbstractTransformer<User, UserDto>{
    @Override
    public User toEntity(UserDto dto) {
        if(dto == null){
            return null;
        }else{
            User entity= new User();
            entity.setId(dto.id());
            entity.setUsername(dto.username());
            entity.setAge(dto.age());
            entity.setEmail(dto.email());
            entity.setPassword(dto.password());
            return entity;
        }
    }

    @Override
    public UserDto toDto(User entity) {
        if(entity == null){
            return null;
        }else{
            UserDto dto=new UserDto(
                    entity.getId(),
                    entity.getUsername(),
                    entity.getAge(),
                    entity.getEmail(),
                    entity.getPassword()
            );
            return dto;
        }
    }
}
