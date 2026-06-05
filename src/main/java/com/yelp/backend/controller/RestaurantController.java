package com.yelp.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yelp.backend.model.Restaurant;
import com.yelp.backend.service.RestaurantService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> restaurantDetail(@PathVariable Long id) {
        Restaurant mockRestaurant = new Restaurant(id, "Salsa", "Galway", 2);
        return ResponseEntity.status(HttpStatus.OK).body(mockRestaurant);    }
    
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        System.out.println(restaurant.getId());
        System.out.println(restaurant.getName());
        Restaurant restaurantToAdd = restaurantService.addRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantToAdd);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable Long id) {
        Restaurant restaurantToUpdate = restaurantService.updateRestaurant(id, restaurant);
        return ResponseEntity.ok(restaurantToUpdate);
    }
} 