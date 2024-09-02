package com.meerity.yourgym.repositories.products;

import com.meerity.yourgym.model.entity.products.Vitamin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VitaminRepository extends JpaRepository<Vitamin, Long>, JpaSpecificationExecutor<Vitamin> {
}
