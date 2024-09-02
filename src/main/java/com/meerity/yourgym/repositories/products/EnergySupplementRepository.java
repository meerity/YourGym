package com.meerity.yourgym.repositories.products;

import com.meerity.yourgym.model.entity.products.EnergySupplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergySupplementRepository extends JpaRepository<EnergySupplement, Long>, JpaSpecificationExecutor<EnergySupplement> {

}
