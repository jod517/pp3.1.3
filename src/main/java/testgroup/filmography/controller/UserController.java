package testgroup.filmography.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import testgroup.filmography.dto.UserDto;
import testgroup.filmography.model.User;
import testgroup.filmography.service.RoleService;
import testgroup.filmography.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {




    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "user")
    public String userPage(Authentication authentication, ModelMap model) {
        User user = userService.getUserByName(authentication.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "admin")
    public String adminPanel(Authentication authentication, ModelMap model,
                             @ModelAttribute("userDto") UserDto userDto) {
        List<User> users = userService.getAllUsers();
        User admin = userService.getUserByName(authentication.getName());
        model.addAttribute("users", users);
        model.addAttribute("admin", admin);
        return "adminPage";
    }

//    @PostMapping(value = "admin/add")
//    public String addUser(@ModelAttribute("userDto") UserDto userDto) {
//        userService.createUser(dtoToEntity(new User(), userDto));
//        return "redirect:/admin";
//    }
//
//    @PostMapping(value = "admin/edit")
//    public String editUser(@ModelAttribute("userDto") UserDto userDto) {
//        User user = userService.getUserById(userDto.getId());
//        Set<Role> roles = user.getRoles();
//        userService.updateUser(dtoToEntity(user, userDto));
//        roles.forEach(x -> roleService.deleteRole(x.getId()));
//        return "redirect:/admin";
//    }
//
//    @PostMapping(value = "admin/delete")
//    public String deleteUser(@ModelAttribute("userDto") UserDto userDto) {
//        userService.deleteUser(userDto.getId());
//        return "redirect:/admin";
//    }
//
//    public User dtoToEntity(User user, UserDto userDto) {
//        user.setName(userDto.getUsername());
//        user.setPassword(userDto.getPassword());
//        user.setRoles(getRoles(userDto.getRoles()));
//        return user;
//    }
//
//    public Set<Role> getRoles(List<String> rolesForUser) {
//        Set<Role> roles = new HashSet<>();
//        if (rolesForUser.contains("admin")) {
//            roles.add(new Role("ROLE_ADMIN"));
//        }
//        if (rolesForUser.contains("user")) {
//            roles.add(new Role("ROLE_USER"));
//        }
//        return roles;
    }
