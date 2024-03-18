package com.jakan.webflux.ws;

import com.jakan.webflux.dto.UserDto;
import com.jakan.webflux.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService){
        this.userService=userService;
    }

    @GetMapping("/")
    public Flux<UserDto> findAll(){
        return userService.findAll();
    }
    @GetMapping("/id/{id}")
    public Mono<UserDto> findById(@PathVariable String id){
        return userService.findById(id);
    }
    @PostMapping("/")
    public Mono<UserDto> save(@RequestBody UserDto dto){
        return userService.save(dto);
    }
    @PostMapping("/list/")
    public Flux<UserDto> save(@RequestBody List<UserDto> dtos){
        return userService.save(dtos);
    }
    @PutMapping("/id/{id}")
    public Mono<UserDto> updateById(@PathVariable String id,@RequestBody UserDto dto){
        return userService.updateById(id,dto);
    }
    @DeleteMapping("/id/{id}")
    public Mono<Void> deleteById(@PathVariable String id){
        return userService.deleteById(id);
    }
}
