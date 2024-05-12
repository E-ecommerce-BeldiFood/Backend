package reviewservice.reviewservice.service;

import reviewservice.reviewservice.dto.ReviewRequestDto;
import reviewservice.reviewservice.dto.ReviewResponseDto;
import reviewservice.reviewservice.exception.ReviewNotFoundException;

import java.util.List;

public interface ReviewService {
    List<ReviewResponseDto> getAllReviews();

    ReviewResponseDto createReview(ReviewRequestDto reviewDto);
    ReviewResponseDto getReviewById(Long id);

    List<ReviewResponseDto> getAllReviewsByProductId(Long productId);

    List<ReviewResponseDto> getAllReviewsByUserId(Long userId);

    ReviewResponseDto updateReview(Long id, ReviewRequestDto reviewRequest) throws ReviewNotFoundException;

    void deleteReviewById(Long id);



}
