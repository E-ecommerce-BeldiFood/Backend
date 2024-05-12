package ma.beldifood.productcatalogservice.client;

import ma.beldifood.productcatalogservice.entity.DtoRequest.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "Review-service")
public interface ReviewServiceClient {
    @GetMapping("/reviews/product/{productId}")
    List<Review> getReviewsByProductId(@RequestParam("productId") Long productId);
}
