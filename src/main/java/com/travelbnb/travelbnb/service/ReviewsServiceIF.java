package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.dto.ReviewsDto;
import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.entity.Reviews;

import java.util.List;

public interface ReviewsServiceIF {

    Reviews addReview(AppUser user, long propertyId, Reviews reviews  );

    void deleteReview(long reviewId);

    Reviews updateReviews(long reviewId, long propertyId, long appUserId, Reviews reviews);

    List<Reviews> getAllReviews();

    Reviews getByReview(long reviewId);

    List<Reviews> findByUserReview(AppUser user);
}
