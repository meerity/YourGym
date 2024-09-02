package com.meerity.yourgym.repositories;

import com.meerity.yourgym.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c ORDER BY c.categoryId")
    List<Category> getAllOrderById();

    Category findByInternalName(String name);

}
