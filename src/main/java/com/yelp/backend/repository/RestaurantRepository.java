package com.yelp.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yelp.backend.model.Restaurant;

import jakarta.persistence.LockModeType;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Restaurant r WHERE r.id = :id")
    Optional<Restaurant> findByIdWithPessimisticLock(Long id);
}