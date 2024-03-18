package com.jakan.webflux.dao;

import com.jakan.webflux.bean.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends ReactiveMongoRepository<User, String> {
}
