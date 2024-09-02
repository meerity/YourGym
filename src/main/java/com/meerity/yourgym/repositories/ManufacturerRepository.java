package com.meerity.yourgym.repositories;

import com.meerity.yourgym.model.entity.Category;
import com.meerity.yourgym.model.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    List<Manufacturer> findByCategories(Set<Category> categories);
}
