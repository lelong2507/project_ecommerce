package com.example.ProjectBE.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectBE.dto.request.RatingDTO.RatingCreationRequest;
import com.example.ProjectBE.service.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/get-all-rating/{id}")
    public ResponseEntity<?> getAllRatingByProductId(@PathVariable(name = "id") String productId) {
        try {
            int idProduct = Integer.parseInt(productId);
            if (idProduct <= 0) {
                return new ResponseEntity<>("Invalid product ID", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(ratingService.getAllRating(idProduct), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid product ID", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-rating")
    ResponseEntity<?> addRating(@RequestBody RatingCreationRequest request) {
        return new ResponseEntity<>(ratingService.addRating(request), HttpStatus.OK);
    }

}
