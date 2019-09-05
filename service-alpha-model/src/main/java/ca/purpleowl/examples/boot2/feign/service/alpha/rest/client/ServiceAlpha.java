package ca.purpleowl.examples.boot2.feign.service.alpha.rest.client;

import ca.purpleowl.examples.boot2.feign.service.alpha.rest.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * This interface provides the basic representation of a REST Service in a Spring application.  Spring's special
 * {@link org.springframework.web.bind.annotation.RequestMapping} annotations are used here, in order to create an interface which
 * can be easily shared between a service and a client.
 *
 * This interface is then extended on the client side in order to create an adequately decorated Feign client, or used
 * on the server side to implement a fully decorated RestClient in a Spring application.
 *
 * @author mightychip
 */
public interface ServiceAlpha {
    /**
     * Retrieve all {@link Book} assets.  These assets may optionally match (exactly, because I'm lazy) the name of
     * the Author as provided by the "authorName" query parameter.
     *
     * @param authorName - A String value representing the exact name of the author of the book.  Provide null or empty if not required.
     * @return A {@link ResponseEntity} wrapping a {@link List} populated with {@link Book} assets with a matching "authorName" if provided.
     */
    @GetMapping(path = "/books", produces = "application/json")
    ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam(name = "authorName", required = false) String authorName);

    /**
     * Retrieve a specific book by its numeric ID via a path variable.
     *
     * @param bookId - A {@link Long} value representing the ID of the book.
     * @return A {@link ResponseEntity} wrapping a {@link Book} asset representing the retrieved book or an appropriate error state if none were retrieved.
     */
    @GetMapping(path = "/books/{bookId}", produces = "application/json")
    ResponseEntity<Book> getBookById(@PathVariable("bookId") Long bookId);

    /**
     * Create a book from a provided {@link Book} object.  Don't bother providing an ID, because this will be ignored
     * in order to actually create a book.
     *
     * @param book - A {@link Book} asset representing the Book to be stored.
     * @return A {@link ResponseEntity} wrapping a {@link Book} object representing the
     */
    @PostMapping(path = "/books", produces = "application/json", consumes = "application/json")
    ResponseEntity<Book> createBook(@RequestBody Book book);
}
