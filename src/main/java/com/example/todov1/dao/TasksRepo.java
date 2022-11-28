package com.example.todov1.dao;

import com.example.todov1.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepo extends JpaRepository<Tasks, Long> {

    @Override
   List<Tasks>findAll();

}
