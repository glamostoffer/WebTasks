package com.example.webtasks.services;

import com.example.webtasks.entities.Role;
import com.example.webtasks.entities.User;
import com.example.webtasks.repositories.UserRepos;
import com.example.webtasks.viewModels.UserViewModel;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepos userRepos;

    public UserService(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepos.findByUsername(username);
    }

    public Boolean addNewUser(String name, String email, String password) {
        User newUser = new User();
        newUser.setUsername(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRoles(Collections.singleton(Role.DefaultUser));
        userRepos.save(newUser);
        return true;
    }

    public Boolean addNewAdmin(String name, String email, String password) {
        User newUser = new User();
        newUser.setUsername(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRoles(Collections.singleton(Role.Administrator));
        userRepos.save(newUser);
        return true;
    }

    public Iterable<User> findAll() {
        return userRepos.findAll();
    }

    public Boolean deleteById(Long id) {
        userRepos.deleteById(id);
        return true;
    }

    public Boolean deleteAll() {
        userRepos.deleteAll();
        return true;
    }



    public void changeData(Long id, UserViewModel user, String description) {
        User userFromDB = userRepos.findById(id).get();
        if (userFromDB == null) {
            return;
        }
        if(userRepos.findByUsername(user.getUsername()) == null) {
            userFromDB.setUsername(user.getUsername());
        }
        userFromDB.setPassword(user.getPassword());
        userFromDB.setDescription(description);
        userRepos.save(userFromDB);
    }
}
