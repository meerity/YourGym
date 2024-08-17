package com.meerity.yourgym.repositories;

import com.meerity.yourgym.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByInternalName(String name);

}
