package testgroup.filmography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.filmography.model.User;


import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {


    @Autowired
    private  UserRepository userRepository;

    @Override
    @Transactional
    public void createUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean userExistByName(String name) {
        return userRepository.existsUserByName(name);
    }

    @Override
    public boolean userExistById(long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user =  userRepository.findByName(name);
        user.getAuthorities().size();
        return user;
    }
}
