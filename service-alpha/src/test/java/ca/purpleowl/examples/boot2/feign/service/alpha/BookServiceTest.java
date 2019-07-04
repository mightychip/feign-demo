package ca.purpleowl.examples.boot2.feign.service.alpha;

import ca.purpleowl.examples.boot2.feign.service.alpha.jpa.repository.BookRepository;
import ca.purpleowl.examples.boot2.feign.service.alpha.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookServiceTest {
    @Mock
    private BookRepository mockBookRepo;

    private BookService fixture;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        fixture = new BookService(mockBookRepo);
    }

    @Test
    public void testGetAllBooks() {
        fixture.getAllBooks();
    }
}
