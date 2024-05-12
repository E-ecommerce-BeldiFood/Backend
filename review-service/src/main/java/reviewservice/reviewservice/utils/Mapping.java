package reviewservice.reviewservice.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import reviewservice.reviewservice.dto.ReviewRequestDto;
import reviewservice.reviewservice.dto.ReviewResponseDto;
import reviewservice.reviewservice.entities.Review;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class Mapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.addMappings(new PropertyMap<ReviewRequestDto, Review>() {
            @Override
            protected void configure() {
                map().setProductId(source.getProductId());
                map().setUserId(source.getUserId());
            }
        });
    }
    public static ReviewResponseDto mapToReviewDto(Review review) {
        return modelMapper.map(review, ReviewResponseDto.class);
    }

    public static Review mapToReviewEntity(ReviewRequestDto reviewRequest) {
        return modelMapper.map(reviewRequest, Review.class);
    }

}
