package com.yelp.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yelp.backend.dto.RestaurantDto;
import com.yelp.backend.model.Restaurant;
import com.yelp.backend.repository.RestaurantRepository;
import com.yelp.backend.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    
    @Override
    public RestaurantDto getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
        return convertToDto(restaurant);
    }
    
    @Override
    public RestaurantDto addRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurantEntity = convertToEntity(restaurantDto);
        Restaurant savedEntity = restaurantRepository.save(restaurantEntity);
        return convertToDto(savedEntity);
    }
    
    @Override
    public RestaurantDto updateRestaurant(Long id, RestaurantDto newRestaurantData) {
        Restaurant restaurantToUpdate = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
                
        restaurantToUpdate.setName(newRestaurantData.getName());
        restaurantToUpdate.setLocation(newRestaurantData.getLocation());
        restaurantToUpdate.setPriceRange(newRestaurantData.getPriceRange());
        
        Restaurant savedEntity = restaurantRepository.save(restaurantToUpdate);
        return convertToDto(savedEntity);
    }
    
    private RestaurantDto convertToDto(Restaurant restaurant) {
        return new RestaurantDto(
            restaurant.getId(),
            restaurant.getName(),
            restaurant.getLocation(),
            restaurant.getPriceRange()
        );
    }

    private Restaurant convertToEntity(RestaurantDto dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(dto.getId()); 
        restaurant.setName(dto.getName());
        restaurant.setLocation(dto.getLocation());
        restaurant.setPriceRange(dto.getPriceRange());
        return restaurant;
    }
}