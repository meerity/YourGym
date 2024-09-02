package com.meerity.yourgym.repositories.products;

import com.meerity.yourgym.model.entity.products.SportDrink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SportDrinkRepository extends JpaRepository<SportDrink, Long>, JpaSpecificationExecutor<SportDrink> {
}
