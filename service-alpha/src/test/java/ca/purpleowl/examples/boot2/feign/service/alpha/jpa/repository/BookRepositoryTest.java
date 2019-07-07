package ca.purpleowl.examples.boot2.feign.service.alpha.jpa.repository;

import ca.purpleowl.examples.boot2.feign.service.alpha.jpa.model.BookEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BookRepositoryTest {
    @Autowired
    private BookRepository fixture;

    @Autowired
    private EntityManager entityManager;

    @Before
    public void setup() {
        BookEntity book1 = new BookEntity(null, "Red Mars", "Kim Stanley Robinson");
        BookEntity book2 = new BookEntity(null, "Green Mars", "Kim Stanley Robinson");
        BookEntity book3 = new BookEntity(null, "Blue Mars", "Kim Stanley Robinson");
        BookEntity book4 = new BookEntity(null, "Leviathan Wakes", "James S. A. Corey");

        entityManager.persist(book1);
        entityManager.persist(book2);
        entityManager.persist(book3);
        entityManager.persist(book4);
        entityManager.flush();
    }

    @Test
    public void testReadAllBooks() {
        List<BookEntity> result = fixture.findAll();

        assertNotNull(result);
        assertEquals(4, result.size());
        assertTrue(result.stream().anyMatch(book -> book.getBookName().equalsIgnoreCase("Red Mars")));
    }

    @Test
    public void testFindAllByAuthorName() {
        List<BookEntity> result = fixture.findAllByAuthorName("Kim Stanley Robinson");

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.stream().anyMatch(book -> book.getBookName().equalsIgnoreCase("Red Mars")));

        result = fixture.findAllByAuthorName("James S. A. Corey");

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
