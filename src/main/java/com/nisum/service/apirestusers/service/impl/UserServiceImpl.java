package com.nisum.service.apirestusers.service.impl;

import com.nisum.service.apirestusers.dto.phone.PhoneMapper;
import com.nisum.service.apirestusers.dto.user.UserDto;
import com.nisum.service.apirestusers.dto.user.UserMapper;
import com.nisum.service.apirestusers.enums.GlobalConfigurationEnum;
import com.nisum.service.apirestusers.model.Phone;
import com.nisum.service.apirestusers.model.User;
import com.nisum.service.apirestusers.repository.UserRepository;
import com.nisum.service.apirestusers.service.GlobalConfigurationService;
import com.nisum.service.apirestusers.service.UserService;
import com.nisum.service.apirestusers.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PhoneMapper phoneMapper;

    private final GlobalConfigurationService globalConfigurationService;

    @Override
    public UserDto createUser(UserDto userDto) {

        validateEmail(userDto.getEmail(), null);

        User user = userMapper.toModel(userDto);
        user.setToken(JwtTokenUtil.addToken(userDto));

        user.getPhones().forEach(phone -> phone.setUser(user));

        validateUser(user);

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto showUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "Error: No se encontro el usuario con id: " +userId));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UUID userId, UserDto userDto) {
        validateEmail(userDto.getEmail(), userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "Error: No se encontro el usuario con id: " +userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        List<Phone> phones = userDto.getPhones().stream().map(phoneMapper::toModel).toList();
        phones.forEach(phone -> phone.setUser(user));

        user.getPhones().clear();
        user.getPhones().addAll(phones);
        validateUser(user);
        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe"));
        userRepository.delete(user);
    }

    private void validateEmail(String email, UUID userId){
        User userEmail = userRepository.findByEmail(email).orElse(null);
        if(userEmail == null)
            return;

        if (userId == null || !userId.equals(userEmail.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email ya se encuentra registrado");
    }

    private void validateUser(User user) {
        Pattern emailPattern;
        Pattern passwordPattern;
        String email = user.getEmail();
        String password = user.getPassword();
        String emailRegex = getPatterEmail();
        String passwordRegex = getPatterPassword();

        if (isBlankOrEmpty(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el usuario debe ingresar el email");
        }

        if (isBlankOrEmpty(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el usuario debe ingresar el password");
        }

        emailPattern = Pattern.compile(emailRegex);
        passwordPattern = Pattern.compile(passwordRegex);

        if (!emailPattern.matcher(email).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("El email no cumple el patrón establecido: %s", emailRegex));
        }

        if (!passwordPattern.matcher(password).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("La contraseña debe cumplir con el patrón establecido: %s", passwordRegex));
        }
    }

    public String getPatterEmail(){
        return globalConfigurationService.getPatternByName(GlobalConfigurationEnum.EMAIL_REGULAR_EXPRESSION);
    }

    public String getPatterPassword(){
        return globalConfigurationService.getPatternByName(GlobalConfigurationEnum.PASSWORD_REGULAR_EXPRESSION);
    }
    private boolean isBlankOrEmpty(String str) {
        return str == null || str.isEmpty() || str.isBlank();
    }
}
