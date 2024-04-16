package reviewservice.reviewservice.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reviewservice.reviewservice.dto.ReviewRequestDto;
import reviewservice.reviewservice.dto.ReviewResponseDto;
import reviewservice.reviewservice.entities.Review;
import reviewservice.reviewservice.exception.ReviewNotFoundException;
import reviewservice.reviewservice.repository.ReviewRepository;
import reviewservice.reviewservice.utils.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImp implements ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<ReviewResponseDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(Mapping::mapToReviewDto)
                .collect(Collectors.toList());
    }
    @Override
    public ReviewResponseDto createReview(ReviewRequestDto reviewDto) {
        var review = Mapping.mapToReviewEntity(reviewDto);
        return Mapping.mapToReviewDto(reviewRepository.save(review));
    }

    @Override
    public ReviewResponseDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review with ID " + id + " not found"));
        return Mapping.mapToReviewDto(review);
    }
    @Override
    public List<ReviewResponseDto> getAllReviewsByProductId(Long productId) {
        List<Review> reviews = reviewRepository.findAllByProductId(productId);
        if (reviews.isEmpty()) {
            throw new ReviewNotFoundException("No reviews found for product with ID " + productId);
        }
        return reviews.stream()
                .map(Mapping::mapToReviewDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ReviewResponseDto> getAllReviewsByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findAllByUserId(userId);
        if (reviews.isEmpty()) {
            throw new ReviewNotFoundException("No reviews found for user with ID " + userId);
        }
        return reviews.stream()
                .map(Mapping::mapToReviewDto)
                .collect(Collectors.toList());
    }
    @Override
    public ReviewResponseDto updateReview(Long id, ReviewRequestDto reviewRequest) throws ReviewNotFoundException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id));

        review.setComment(reviewRequest.getComment());
        review.setRating(reviewRequest.getRating());
        review.setUserId(reviewRequest.getUserId());
        review.setProductId(reviewRequest.getProductId());
        Review updatedReview = reviewRepository.save(review);
        return Mapping.mapToReviewDto(updatedReview);
    }
    @Override
    public void deleteReviewById(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ReviewNotFoundException("Review with ID " + id + " not found");
        }
        reviewRepository.deleteById(id);
    }
}
