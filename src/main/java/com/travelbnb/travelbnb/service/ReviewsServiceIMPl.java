package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.dto.ReviewsDto;
import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Property;
import com.travelbnb.travelbnb.entity.Reviews;
import com.travelbnb.travelbnb.exception.ResourceNotException;
import com.travelbnb.travelbnb.repository.AppUserRepository;
import com.travelbnb.travelbnb.repository.PropertyEntityRepository;
import com.travelbnb.travelbnb.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsServiceIMPl implements ReviewsServiceIF {
    private ReviewsRepository reviewsRepository;
    private PropertyEntityRepository propertyEntityRepository;
    private AppUserRepository  appUserRepository ;

    public ReviewsServiceIMPl(ReviewsRepository reviewsRepository, PropertyEntityRepository propertyEntityRepository, AppUserRepository appUserRepository) {
        this.reviewsRepository = reviewsRepository;

        this.propertyEntityRepository = propertyEntityRepository;
        this.appUserRepository = appUserRepository;
    }
    @Override
    public Reviews addReview(AppUser user, long propertyId, Reviews reviews) {
        Optional<Property> OpProperty = propertyEntityRepository.findById(propertyId);
        if (OpProperty.isPresent()) {
            Property property = OpProperty.get();
            if (reviewsRepository.findReviewByUser(user, property) != null) {
                throw new ResourceNotException("Review Exists");


            }

        else {

                Integer ratings = reviews.getRatings();
                if (ratings < 1 || ratings > 5) {
                    throw new ResourceNotException("Rating should be between 1 and 5");
                }
                reviews.setAppUser(user);
                reviews.setProperty(property);
                Reviews save = reviewsRepository.save(reviews);
                return save;
            }
        } else {
            throw new ResourceNotException("PropertyId Not Found Id: " + propertyId);
        }

    }




    @Override
    public void deleteReview(long reviewId) {
        reviewsRepository.deleteById(reviewId);

    }

    @Override
    public Reviews updateReviews(long reviewId, long propertyId, long appUserId, Reviews reviews) {
        Optional<Reviews> OpReviews = reviewsRepository.findById(reviewId);

        Reviews reviewsEntity = null;
        if (OpReviews.isPresent()) {

            reviewsEntity = OpReviews.get();
            reviewsEntity.setRatings(reviews.getRatings());
            reviewsEntity.setDescription(reviews.getDescription());
            reviewsEntity.setProperty(reviews.getProperty());
            reviewsEntity.setAppUser(reviews.getAppUser());

            Property property = propertyEntityRepository.findById(propertyId).get();
            reviewsEntity.setProperty(property);

            AppUser appUser = appUserRepository.findById(appUserId).get();
            reviewsEntity.setAppUser(appUser);

            Reviews save = reviewsRepository.save(reviewsEntity);
            return save;
        }else {
            throw new ResourceNotException ("Not Found User ID "+ reviewId);
        }
    }

    @Override
    public List<Reviews> getAllReviews() {
        return reviewsRepository.findAll();
    }

    @Override
    public Reviews getByReview(long reviewId) {
        Reviews reviews = reviewsRepository.findById(reviewId).get();
        return reviews;
    }

    @Override
    public List<Reviews> findByUserReview(AppUser user) {
        List<Reviews> byAppUser = reviewsRepository.findByUserReview(user);


        return byAppUser;
    }


}

