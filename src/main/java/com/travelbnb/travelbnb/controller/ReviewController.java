package com.travelbnb.travelbnb.controller;


import com.travelbnb.travelbnb.dto.ReviewsDto;
import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Reviews;
import com.travelbnb.travelbnb.service.PropertyServiceIF;
import com.travelbnb.travelbnb.service.ReviewsServiceIF;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

   private ReviewsServiceIF reviewsServiceIF ;
   private PropertyServiceIF propertyServiceIF ;

    public ReviewController(ReviewsServiceIF reviewsServiceIF, PropertyServiceIF propertyServiceIF) {
        this.reviewsServiceIF = reviewsServiceIF;
        this.propertyServiceIF = propertyServiceIF;
    }

//http://localhost:8080/api/v1/reviews/addReview?propertyId=5

    @PostMapping("/addReview")
    public ResponseEntity<?>addReview(
            @Valid
            @AuthenticationPrincipal AppUser user ,
            @RequestParam long propertyId,
            @RequestBody Reviews reviews
            ) {

        Reviews reviews1 = reviewsServiceIF.addReview(user, propertyId, reviews);

        return new ResponseEntity<>("Thank You For Review",HttpStatus.CREATED);
    }


    //http://localhost:8080/api/v1/reviews

    @DeleteMapping
    public ResponseEntity<String>deleteReview(@RequestParam long reviewId ){

        reviewsServiceIF.deleteReview(reviewId);
        return new ResponseEntity<>("Successfully Deleted Review",HttpStatus.OK);
    }



    //http://localhost:8080/api/v1/reviews/2/1/4
@PutMapping("/{reviewId}/{propertyId}/{appUserId}")
    public ResponseEntity<Reviews>updateReviews(
            @PathVariable long reviewId,
            @RequestBody Reviews reviews,
            @PathVariable long propertyId,
            @PathVariable long appUserId
    ){
        Reviews reviewsEn = reviewsServiceIF.updateReviews(reviewId,propertyId,appUserId,reviews);
        return new ResponseEntity<>(reviewsEn,HttpStatus.OK);
    }

//http://localhost:8080/api/v1/reviews
    @GetMapping
    public ResponseEntity<List<Reviews>>getAllReviews(){
        List<Reviews> reviews =  reviewsServiceIF.getAllReviews();
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/reviews?getByReview=2
    @GetMapping("/getByReview")
    public ResponseEntity<Reviews>getByReviews(@RequestParam long reviewId){
        Reviews reviews =  reviewsServiceIF.getByReview(reviewId);
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/reviews
    @GetMapping("/getUserReview")
    public ResponseEntity <List<Reviews>>getUserReviews(
            @AuthenticationPrincipal AppUser user
    ){
        List<Reviews>reviews = reviewsServiceIF.findByUserReview(user);
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

    }

