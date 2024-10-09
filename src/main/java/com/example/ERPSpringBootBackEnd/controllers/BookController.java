package com.example.ERPSpringBootBackEnd.controllers;

import com.example.ERPSpringBootBackEnd.model.Book;
import com.example.ERPSpringBootBackEnd.services.BooksService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private BooksService booksService;

    @GetMapping
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<List<Book>> getAllBooks() {
       return ResponseEntity.ok().body(booksService.getAllBooks());
    }

    @PostMapping("/addBook")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Book> saveNewBook(@RequestBody Book book) {
        Book newBook = booksService.save(book);
        return ResponseEntity.ok().body(newBook);
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@RequestParam Integer id,
                                           @RequestBody Book book) {
        return ResponseEntity.ok().body(booksService.update(id, book));
    }

}
