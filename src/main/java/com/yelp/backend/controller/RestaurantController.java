package com.yelp.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yelp.backend.dto.RestaurantDto;
import com.yelp.backend.service.RestaurantService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> restaurantDetail(@PathVariable Long id) {
        RestaurantDto restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }
    
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurant) {
        System.out.println(restaurant.getId());
        System.out.println(restaurant.getName());
        RestaurantDto restaurantToAdd = restaurantService.addRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantToAdd);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<RestaurantDto> updateRestaurant(@RequestBody RestaurantDto restaurant, @PathVariable Long id) {
        RestaurantDto restaurantToUpdate = restaurantService.updateRestaurant(id, restaurant);
        return ResponseEntity.ok(restaurantToUpdate);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Map<String, String>> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok(Map.of("message", "Restaurant Deleted")); 
    }
} 