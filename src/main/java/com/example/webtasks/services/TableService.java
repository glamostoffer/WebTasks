package com.example.webtasks.services;

import com.example.webtasks.entities.Tables;
import com.example.webtasks.entities.Task;
import com.example.webtasks.entities.User;
import com.example.webtasks.repositories.TableRepos;
import com.example.webtasks.repositories.TaskRepos;
import com.example.webtasks.repositories.UserRepos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    private final UserRepos userRepos;
    private final TableRepos tableRepos;
    private final TaskRepos taskRepos;


    //Init beans
    public TableService(UserRepos userRepos, TableRepos tableRepos, TaskRepos taskRepos) {
        this.userRepos = userRepos;
        this.tableRepos = tableRepos;
        this.taskRepos = taskRepos;
    }

    //Get all user's tables
    public Iterable<Tables> getUserTables(User user) {
        return tableRepos.findAllByUser(user);
    }

    //Create new table
    public void createTable(User user, String tableName) {
        Tables newTable = new Tables();
        newTable.setName(tableName);
        newTable.setUser(user);
        tableRepos.save(newTable);
    }


    //Find all task in table by id
    public List<Task> getTableTasks(Long id) {
        Tables table = findTableById(id);
        if(table != null) {
            return table.getTasks();
        }
        else {
            return null;
        }
    }

    //Find table by id
    public Tables findTableById(Long id) {
        return tableRepos.findById(id).orElse(null);
    }

    //Add new task to table
    public void AddNewTask(Long id, User user, String taskMessage) {
        Tables table = findTableById(id);
        if(table != null) {
            Task task = new Task();
            task.setTask(taskMessage);
            task.setAuthor(user);
            task.setTables(table);
            taskRepos.save(task);
            table.getTasks().add(task);
            tableRepos.save(table);
        }
    }
}
