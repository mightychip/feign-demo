package ca.purpleowl.examples.boot2.feign.service.beta.service;

import ca.purpleowl.examples.boot2.feign.service.beta.jpa.model.ReviewEntity;
import ca.purpleowl.examples.boot2.feign.service.beta.jpa.repository.ReviewRepository;
import ca.purpleowl.examples.boot2.feign.service.beta.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepo;

    @Autowired
    public ReviewService(ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    public List<Review> getAllByBookId(Long bookId) {
        return reviewRepo.findAllByBookId(bookId)
                         .stream()
                         .map(this::entityToAsset)
                         .collect(Collectors.toList());
    }

    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepo.findById(reviewId)
                         .map(this::entityToAsset);
    }

    public Long createReview(Review review) {
        return reviewRepo.saveAndFlush(assetToEntity(review)).getId();
    }

    private Review entityToAsset(ReviewEntity entity) {
        return new Review(entity.getId(), entity.getBookId(), entity.getReviewText());
    }

    private ReviewEntity assetToEntity(Review asset) {
        //We ignore the ID for now... since the only way we are creating Entities is via POST.
        return new ReviewEntity(null, asset.getBookId(), asset.getReviewText());
    }
}
