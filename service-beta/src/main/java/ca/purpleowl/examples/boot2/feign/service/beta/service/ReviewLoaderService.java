package ca.purpleowl.examples.boot2.feign.service.beta.service;

import ca.purpleowl.examples.boot2.feign.service.beta.jpa.model.ReviewEntity;
import ca.purpleowl.examples.boot2.feign.service.beta.jpa.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewLoaderService {
    private ReviewRepository reviewRepo;

    @Autowired

    public ReviewLoaderService(ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
        loadReviews();
    }

    private void loadReviews() {
        reviewRepo.saveAndFlush(new ReviewEntity(null, 1L, "What a great read!"));
        reviewRepo.saveAndFlush(new ReviewEntity(null, 1L, "A little violent for my tastes!"));
        reviewRepo.saveAndFlush(new ReviewEntity(null, 2L, "Would totally read this again!"));
        reviewRepo.saveAndFlush(new ReviewEntity(null, 2L, "Totally a literary classic!"));
        reviewRepo.saveAndFlush(new ReviewEntity(null, 3L, "What a great read!"));
        reviewRepo.saveAndFlush(new ReviewEntity(null, 4L, "Third in the series and easily as great as the first!"));
    }
}
