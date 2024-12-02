package com.example.ProjectBE.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjectBE.dto.request.RatingDTO.RatingCreationRequest;
import com.example.ProjectBE.entities.Product;
import com.example.ProjectBE.entities.Rating;
import com.example.ProjectBE.entities.User;
import com.example.ProjectBE.repository.ProductRepository;
import com.example.ProjectBE.repository.RatingRepository;
import com.example.ProjectBE.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Rating addRating(RatingCreationRequest req) {
        Rating rating = new Rating();
        User user = userRepository.getReferenceById(req.getIdUser());
        Product product = productRepository.getReferenceById(req.getIdProduct());
        if (user == null || product == null) {
            throw new RuntimeException("User or product not found");
        }
        rating.setContent(req.getContent());
        rating.setUser(user);
        rating.setProduct(product);
        return ratingRepository.save(rating);
    }

    @Transactional
    public List<Rating> getAllRating(int productId) {
        List<Rating> ratingList = ratingRepository.findByProductIdWithUser(productId);
        return ratingList;
    }

}
