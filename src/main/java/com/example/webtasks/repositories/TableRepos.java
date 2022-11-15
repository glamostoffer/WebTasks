package com.example.webtasks.repositories;

import com.example.webtasks.entities.Tables;
import com.example.webtasks.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepos extends JpaRepository<Tables, Long> {
    Iterable<Tables> findAllByUser(User user);
}
