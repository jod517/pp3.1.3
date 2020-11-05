package testgroup.filmography.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testgroup.filmography.dto.UserDto;
import testgroup.filmography.model.Role;
import testgroup.filmography.model.User;
import testgroup.filmography.service.RoleService;
import testgroup.filmography.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/rest")
public class RestUserController {

    private final UserService userService;

    private final RoleService roleService;

    public RestUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> readAllUsers() {
        List<UserDto> usersDto = new ArrayList<>();
        for (User u : userService.getAllUsers()) {
            usersDto.add(entityToDto(new UserDto(), u));
        }

        if (usersDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    public UserDto entityToDto(UserDto userDto, User user) {
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(user.getRoles().stream()
                .map(Role::getRole)
                .collect(Collectors.toList()));
        return userDto;
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> readUser(@PathVariable(name = "id") long id) {
        if (!userService.userExistById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDto userDto = entityToDto(new UserDto(), userService.getUserById(id));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        if (userService.userExistByName(userDto.getUsername())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.createUser(dtoToEntity(new User(), userDto));
        userDto.setId(userService.getUserByName(userDto.getUsername()).getId());
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    public User dtoToEntity(User user, UserDto userDto) {
        user.setName(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRoles(getRoles(userDto.getRoles()));
        return user;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable(name = "id") long id, @RequestBody UserDto userDto) {
        if (!userService.userExistById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userService.getUserById(id);
        Set<Role> roles = user.getRoles();
        userService.updateUser(dtoToEntity(user, userDto));
        roles.forEach(x -> roleService.deleteRole(x.getId()));
        userDto.setId(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable(name = "id") long id) {
        if (!userService.userExistById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public Set<Role> getRoles(List<String> rolesForUser) {
        Set<Role> roles = new HashSet<>();
        if (rolesForUser.contains("ADMIN")) {
            roles.add(new Role("ADMIN"));
        }
        if (rolesForUser.contains("USER")) {
            roles.add(new Role("USER"));
        }
        return roles;
    }
}
