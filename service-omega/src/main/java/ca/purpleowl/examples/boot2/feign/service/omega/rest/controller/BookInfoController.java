package ca.purpleowl.examples.boot2.feign.service.omega.rest.controller;

import ca.purpleowl.examples.boot2.feign.service.alpha.model.Book;
import ca.purpleowl.examples.boot2.feign.service.beta.model.Review;
import ca.purpleowl.examples.boot2.feign.service.omega.client.ServiceAlphaClient;
import ca.purpleowl.examples.boot2.feign.service.omega.client.ServiceBetaClient;
import ca.purpleowl.examples.boot2.feign.service.omega.rest.model.BookInfo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Log
@RestController
@RequestMapping(path = "book-info")
public class BookInfoController {
    private final ServiceAlphaClient serviceAlpha;
    private final ServiceBetaClient serviceBeta;

    @Autowired
    public BookInfoController(ServiceAlphaClient serviceAlpha, ServiceBetaClient serviceBeta) {
        this.serviceAlpha = serviceAlpha;
        this.serviceBeta = serviceBeta;
    }

    @GetMapping(path = "/{bookId}", produces = "application/json")
    public ResponseEntity<BookInfo> getBookInfo(@PathVariable("bookId") Long bookId) {
        ResponseEntity<Book> bookResponse = serviceAlpha.getBookById(bookId);
        if(bookResponse.getStatusCode().equals(HttpStatus.OK)) {
            ResponseEntity<List<Review>> reviewResponse = serviceBeta.getReviewsByBookId(bookId);

            return ResponseEntity.ok(
                    new BookInfo(bookResponse.getBody(),
                                 reviewResponse.getStatusCode().equals(HttpStatus.OK) ?
                                            reviewResponse.getBody() :
                                            Collections.emptyList())
            );
        }

        log.warning("Looks like something went wrong...");
        return ResponseEntity.ok(new BookInfo());
    }
}
