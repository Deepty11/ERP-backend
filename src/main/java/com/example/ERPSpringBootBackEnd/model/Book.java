package com.example.ERPSpringBootBackEnd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String title;
    String language;
    String yearsOfPublication;
    String authors;

//    @ElementCollection(fetch=FetchType.LAZY)
//    @CollectionTable(name="REVIEWS", joinColumns=@JoinColumn(name="BOOK_ID"))
//    @Column(name="TEXT")
//    private List<String> reviews = new ArrayList<>();
}
