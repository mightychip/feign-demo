package ca.purpleowl.examples.boot2.feign.service.alpha.service;

import ca.purpleowl.examples.boot2.feign.service.alpha.jpa.model.BookEntity;
import ca.purpleowl.examples.boot2.feign.service.alpha.jpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookLoaderService {
    private final BookRepository bookRepo;

    @Autowired
    public BookLoaderService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
        loadBooks();
    }

    private void loadBooks() {
        bookRepo.saveAndFlush(new BookEntity(null, "Legacy of Heorot", "Jerry Pournelle, Larry Niven, and Steven Barnes"));
        bookRepo.saveAndFlush(new BookEntity(null, "Red Mars", "Kim Stanley Robinson"));
        bookRepo.saveAndFlush(new BookEntity(null, "Blue Mars", "Kim Stanley Robinson"));
        bookRepo.saveAndFlush(new BookEntity(null, "Green Mars", "Kim Stanley Robinson"));
    }
}
