package com.scaler.ecom.Repository;

import com.scaler.ecom.Modle.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    @Override
//    Optional<Category> findById(Long aLong);
}
