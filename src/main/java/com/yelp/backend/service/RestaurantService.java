package com.yelp.backend.service;

import java.util.List;

import com.yelp.backend.dto.RestaurantDto;

public interface RestaurantService {
    List<RestaurantDto> getAllRestaurants();
    RestaurantDto getRestaurantById(Long id);
    RestaurantDto addRestaurant(RestaurantDto restaurantDto);
    RestaurantDto updateRestaurant(Long id, RestaurantDto newRestaurantData);
}