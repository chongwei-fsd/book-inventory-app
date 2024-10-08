package com.cw.bookapi.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Title is mandatory")
    @Size(min = 3, message = "Name must be at least 3 characters.")
    @Size(max = 255, message = "Name must not be more than 255 characters.")
    private  String title;

    @Column
    @NotBlank(message = "Author is mandatory")
    @Size(min = 3, message = "Name must be at least 3 characters.")
    @Size(max = 255, message = "Name must not be more than 255 characters.")
    private String author;
}
