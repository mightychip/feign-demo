package ca.purpleowl.examples.boot2.feign.service.beta.jpa.repository;

import ca.purpleowl.examples.boot2.feign.service.beta.jpa.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByBookId(Long bookId);
}
