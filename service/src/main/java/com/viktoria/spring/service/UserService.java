package com.viktoria.spring.service;

import com.querydsl.core.types.Predicate;
import com.viktoria.spring.database.entity.QUser;
import com.viktoria.spring.database.repository.QPredicate;
import com.viktoria.spring.database.repository.UserRepository;
import com.viktoria.spring.dto.UserCreateEditDto;
import com.viktoria.spring.dto.UserFilter;
import com.viktoria.spring.dto.UserReadDto;
import com.viktoria.spring.dto.UserUpdateDto;
import com.viktoria.spring.mapper.UserCreateEditMapper;
import com.viktoria.spring.mapper.UserReadMapper;
import com.viktoria.spring.mapper.UserUpdateMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.viktoria.spring.database.entity.QUser.user;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserReadMapper userReadMapper;
    private final UserUpdateMapper userUpdateMapper;

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        Predicate predicate = QPredicate.builder()
                .add(filter.firstName(), user.firstName::containsIgnoreCase)
                .add(filter.lastName(), user.lastName::containsIgnoreCase)
                .add(filter.phoneNumber(), user.phoneNumber::containsIgnoreCase)
                .buildAnd();

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

//это на будущее
//    @Transactional
//    public Optional<UserUpdateDto> update(Long id, UserCreateEditDto userDto) {
//        return userRepository.findById(id)
//                .map(entity -> userCreateEditMapper.map(userDto, entity))
//                .map(userRepository::saveAndFlush)
//                .map(userUpdateMapper::map);
//    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}
