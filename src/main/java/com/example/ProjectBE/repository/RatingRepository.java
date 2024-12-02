package com.example.ProjectBE.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.ProjectBE.entities.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query("SELECT r FROM Rating r JOIN FETCH r.user u WHERE r.product.idProduct = :productId")
    List<Rating> findByProductIdWithUser(@Param("productId") int productId);
}