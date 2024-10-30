package com.example.ProjectBE.repository;

import com.example.ProjectBE.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
