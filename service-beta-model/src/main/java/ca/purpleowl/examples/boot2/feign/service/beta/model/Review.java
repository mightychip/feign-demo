package ca.purpleowl.examples.boot2.feign.service.beta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Review {
    private Long id;
    private Long bookId;
    private String reviewText;
}
