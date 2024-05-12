package reviewservice.reviewservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewRequestDto {
    @NotNull(message = "User Id is required")
    private Long userId;
    @NotNull(message = "Product ID is required")
    private Long productId;
    @NotNull(message = "Rating is required")
    private int rating;
    private String comment;
}
