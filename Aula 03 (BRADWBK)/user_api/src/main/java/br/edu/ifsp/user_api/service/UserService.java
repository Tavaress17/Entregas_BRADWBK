package br.edu.ifsp.user_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsp.user_api.model.User;
import br.edu.ifsp.user_api.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserList(){
        return userRepository.getAllUsers();
    }

    public User getUserById(int id){
        return userRepository.getUserById(id);
    }

    public User updateUser(int id, User user) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            existingUser.setLogin(user.getLogin());
            existingUser.setPassword(user.getPassword());
            return existingUser;
        }
        return null;
    }

    public boolean deleteUser(int id) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            userRepository.deleteUserById(id);
            return true;
        }
        return false;
    }
}