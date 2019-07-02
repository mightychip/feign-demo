package ca.purpleowl.examples.boot2.feign.service.alpha.jpa.repository;

import ca.purpleowl.examples.boot2.feign.service.alpha.jpa.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAllByAuthorName(String authorName);
}
