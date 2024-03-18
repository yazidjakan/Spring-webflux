package com.jakan.webflux.service.impl;

import com.jakan.webflux.bean.User;
import com.jakan.webflux.dao.UserDao;
import com.jakan.webflux.dto.UserDto;
import com.jakan.webflux.service.facade.UserService;
import com.jakan.webflux.transformer.UserTransformer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {

    @Autowired private UserDao userDao;
    @Autowired private UserTransformer userTransformer;
    @Override
    public Flux<UserDto> findAll() {
        Flux<User> users=userDao.findAll();
        return users
                .map(user -> userTransformer.toDto(user))
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<UserDto> findById(String id) {
        Mono<User> foundedUser=userDao.findById(id);
        return foundedUser
                .map(user -> userTransformer.toDto(user));
    }

    @Override
    public Mono<UserDto> save(UserDto dto) {
        Mono<User> savedUser=userDao.save(userTransformer.toEntity(dto));
        return savedUser
                .map(user -> userTransformer.toDto(user));
    }

    @Override
    public Flux<UserDto> save(List<UserDto> dtos) {
        Flux<User> savedUsers=Flux.empty();
        for(UserDto dto: dtos){
            Mono<User> savedUser=userDao.save(userTransformer.toEntity(dto));
            savedUsers=savedUsers.concatWith(savedUser);
        }
        return savedUsers.map(userTransformer::toDto);
    }

    @Override
    public Mono<UserDto> updateById(String id, UserDto dto) {
        User userToUpdate=userTransformer.toEntity(dto);
        return userDao.findById(id)
                .flatMap(existingUser ->{
                    existingUser.setUsername(userToUpdate.getUsername());
                    existingUser.setAge(userToUpdate.getAge());
                    existingUser.setEmail(userToUpdate.getEmail());
                    existingUser.setPassword(userToUpdate.getPassword());
                    return userDao.save(existingUser);
                })
                .map(userTransformer::toDto);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        findById(id);
        return userDao.deleteById(id);
    }
}
