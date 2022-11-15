package com.example.webtasks.repositories;

import com.example.webtasks.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepos extends JpaRepository<Task, Long> {
}
