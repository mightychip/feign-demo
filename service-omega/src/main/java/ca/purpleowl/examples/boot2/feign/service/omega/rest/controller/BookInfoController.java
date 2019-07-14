package ca.purpleowl.examples.boot2.feign.service.omega.rest.controller;

import ca.purpleowl.examples.boot2.feign.service.alpha.rest.model.Book;
import ca.purpleowl.examples.boot2.feign.service.beta.rest.model.Review;
import ca.purpleowl.examples.boot2.feign.service.omega.rest.model.BookInfo;
import ca.purpleowl.examples.boot2.feign.service.omega.service.StabilizedServiceAlpha;
import ca.purpleowl.examples.boot2.feign.service.omega.service.StabilizedServiceBeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "book-info")
public class BookInfoController {
    private final StabilizedServiceAlpha serviceAlpha;
    private final StabilizedServiceBeta serviceBeta;

    @Autowired
    public BookInfoController(StabilizedServiceAlpha serviceAlpha, StabilizedServiceBeta serviceBeta) {
        this.serviceAlpha = serviceAlpha;
        this.serviceBeta = serviceBeta;
    }

    @GetMapping(path = "/{bookId}", produces = "application/json")
    public ResponseEntity<BookInfo> getBookInfo(@PathVariable("bookId") Long bookId) {
        Book bookResponse = serviceAlpha.getBookById(bookId);
        List<Review> reviewResponse = serviceBeta.getReviewsByBookId(bookId);

        return ResponseEntity.ok(
                new BookInfo(bookResponse,
                             reviewResponse)
        );
    }
}
