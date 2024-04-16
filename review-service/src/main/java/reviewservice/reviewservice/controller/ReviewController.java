package reviewservice.reviewservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reviewservice.reviewservice.dto.ReviewRequestDto;
import reviewservice.reviewservice.dto.ReviewResponseDto;
import reviewservice.reviewservice.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ReviewResponseDto createReview(@RequestBody ReviewRequestDto reviewDto) {
        return reviewService.createReview(reviewDto);
    }

    @GetMapping("/{id}")
    public ReviewResponseDto getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping("/product/{productId}")
    public List<ReviewResponseDto> getAllReviewsByProductId(@PathVariable Long productId) {
        return reviewService.getAllReviewsByProductId(productId);
    }

    @GetMapping("/user/{userId}")
    public List<ReviewResponseDto> getAllReviewsByUserId(@PathVariable Long userId) {
        return reviewService.getAllReviewsByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteReviewById(@PathVariable Long id) {
        reviewService.deleteReviewById(id);
    }
}
