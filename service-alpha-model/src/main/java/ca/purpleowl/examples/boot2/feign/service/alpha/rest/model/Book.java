package ca.purpleowl.examples.boot2.feign.service.alpha.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Book {
    private Long id;
    private String bookName;
    private String authorName;
}
