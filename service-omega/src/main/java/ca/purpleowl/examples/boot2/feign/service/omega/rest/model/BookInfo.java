package ca.purpleowl.examples.boot2.feign.service.omega.rest.model;

import ca.purpleowl.examples.boot2.feign.service.alpha.model.Book;
import ca.purpleowl.examples.boot2.feign.service.beta.model.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BookInfo {
    private Book book;
    private List<Review> reviews = new ArrayList<>();
}
