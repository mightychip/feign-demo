package ca.purpleowl.examples.boot2.feign.service.alpha.service;

import ca.purpleowl.examples.boot2.feign.service.alpha.jpa.model.BookEntity;
import ca.purpleowl.examples.boot2.feign.service.alpha.jpa.repository.BookRepository;
import ca.purpleowl.examples.boot2.feign.service.alpha.rest.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepo;

    @Autowired
    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll()
                       .stream()
                       .map(this::entityToAsset)
                       .collect(Collectors.toList());
    }

    public List<Book> getAllBooksByAuthor(String authorName) {
        return bookRepo.findAllByAuthorName(authorName)
                       .stream()
                       .map(this::entityToAsset)
                       .collect(Collectors.toList());
    }

    public Optional<Book> getBookById(Long bookId) {
        return bookRepo.findById(bookId)
                       .map(this::entityToAsset);
    }

    public Long createBook(Book book) {
        return bookRepo.save(assetToEntity(book)).getId();
    }

    private Book entityToAsset(BookEntity entity) {
        return new Book(entity.getId(), entity.getBookName(), entity.getAuthorName());
    }

    private BookEntity assetToEntity(Book asset) {
        return new BookEntity(null, asset.getBookName(), asset.getAuthorName());
    }
}
