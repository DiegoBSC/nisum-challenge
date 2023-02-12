package com.nisum.service.apirestusers.controller;

import com.nisum.service.apirestusers.dto.user.UserDto;
import com.nisum.service.apirestusers.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Detalle del usuario", description = "Retorna el detalle del usuario a partir de su Id")
    @GetMapping("/show/{userId}")
    public UserDto showUser(@PathVariable UUID userId){
       return userService.showUser(userId);
    }

    @Operation(summary = "Crea un usuario", description = "Se recibe los datos del usuario, si la información esta correcta se crea un usuario ")
    @PostMapping("/create")
    public UserDto createUser(@Valid @RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @Operation(summary = "Actualiza un usuario", description = "Se recibe los datos del usuario, si la información esta correcta se actualiza un usuario a partir de su id ")
    @PutMapping("/update/{userId}")
    public UserDto updateUser(@PathVariable UUID userId, @RequestBody UserDto userDto){
        return userService.updateUser(userId, userDto);
    }

    @Operation(summary = "Elimina un usuario", description = "Elimina un usuario a partir de su id ")
    @DeleteMapping("delete/{userId}")
    public void deleteUser(@PathVariable UUID userId){
        userService.deleteUser(userId);
    }
}
