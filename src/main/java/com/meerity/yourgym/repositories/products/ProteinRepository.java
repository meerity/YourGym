package com.meerity.yourgym.repositories.products;

import com.meerity.yourgym.model.entity.products.Protein;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProteinRepository extends JpaRepository<Protein, Long>, JpaSpecificationExecutor<Protein> {
}
