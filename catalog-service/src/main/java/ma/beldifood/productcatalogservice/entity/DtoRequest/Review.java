package ma.beldifood.productcatalogservice.entity.DtoRequest;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private Long userId;
    private Long productId;
    private int rating;
    private String comment;
}
