package ca.purpleowl.examples.boot2.feign.service.alpha.jpa.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ActiveProfiles({"test"})
@DataJpaTest
@RunWith(SpringRunner.class)
public class BookEntityTest {
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateAndWrite() {
        BookEntity fixture = new BookEntity();
        fixture.setBookName("Red Mars");
        fixture.setAuthorName("Green Mars");

        BookEntity result = entityManager.persistAndFlush(fixture);

        assertNotNull(fixture.getId());
        assertEquals(fixture.getBookName(), result.getBookName());
        assertEquals(fixture.getAuthorName(), result.getAuthorName());

        BookEntity readEntity = entityManager.find(BookEntity.class, fixture.getId());

        assertEquals(result, readEntity);
    }
}
