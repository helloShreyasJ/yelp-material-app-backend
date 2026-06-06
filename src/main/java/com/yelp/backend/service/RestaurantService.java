package com.yelp.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yelp.backend.model.Restaurant;
import com.yelp.backend.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElseThrow();
    }

    public Restaurant updateRestaurant(Long id, Restaurant newRestaurantData) {
        Restaurant restaurantToUpdate = getRestaurantById(id);
        restaurantToUpdate.setName(newRestaurantData.getName());
        restaurantToUpdate.setLocation(newRestaurantData.getLocation());
        restaurantToUpdate.setPriceRange(newRestaurantData.getPriceRange());
        return restaurantRepository.save(restaurantToUpdate);
    }
}