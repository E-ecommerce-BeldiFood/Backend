package reviewservice.reviewservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reviewservice.reviewservice.dto.ReviewResponseDto;
import reviewservice.reviewservice.entities.Review;

import java.util.Collection;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByProductId(Long productId);
    List<Review> findAllByUserId(Long userId);



}
