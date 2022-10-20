package com.example.webtasks.services;

import com.example.webtasks.entities.User;
import com.example.webtasks.repositories.UserRepos;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        userRepos.save(newUser);
        return true;
    }

    public Iterable<User> findAll() {
        return userRepos.findAll();
    }

    public Boolean deleteById(Integer id) {
        userRepos.deleteById(id);
        return true;
    }
}
