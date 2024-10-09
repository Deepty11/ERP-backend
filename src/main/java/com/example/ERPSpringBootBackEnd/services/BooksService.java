package com.example.ERPSpringBootBackEnd.services;

import com.example.ERPSpringBootBackEnd.model.Book;
import com.example.ERPSpringBootBackEnd.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {
    @Autowired
    private  BookRepository booksRepo;
    
    public List<Book> getAllBooks() {
        return booksRepo.findAll();
    }

    public Book save(Book book) {
        return booksRepo.save(book);
    }

    public Book update(Integer id, Book book) {
        Book prevBook = booksRepo.findById(id).orElseThrow();
        prevBook.setTitle(book.getTitle());
        prevBook.setAuthors(book.getAuthors());
        prevBook.setYearsOfPublication(book.getYearsOfPublication());
        prevBook.setLanguage(book.getLanguage());

        return booksRepo.save(prevBook);
    }


//    public List<Book> findBooks(Integer size, Integer pageNumber, String sortField, String sortDirection) {
//        return booksRepo.findAll(size, pageNumber, sortField, sortDirection);
//    }
//
//    public Book findBookById(Integer id) {
//        return booksRepo.find(id)
//                .orElseThrow(() -> new ResourceNotFoundException(Book.class.getSimpleName(), id));
//    }
//
//    public Book createBook(Book book) {
//        Optional<Book> registeredBook = booksRepo.findBookByTitle(book.getTitle());
//        if (registeredBook.isPresent())
//            throw new ResourceAlreadyExistsException(Book.class.getSimpleName(), registeredBook.get().getId());
//        return booksRepo.save(book);
//    }
//
//    public Book updateBook(Book book) {
//        abortIfBookDoesNotExist(book.getId());
//        Book updated = booksRepo.update(book);
//        return updated;
//    }
//
//    public void deleteBook(Integer id) {
//        abortIfBookDoesNotExist(id);
//        booksRepo.delete(id);
//    }
//
//    public List<String> findReviews(Integer bookId) {
//        abortIfBookDoesNotExist(bookId);
//        return booksRepo.findReviewsOfBook(bookId);
//    }
//
//    public String addReview(Integer bookId, String review) {
//        abortIfBookDoesNotExist(bookId);
//        return booksRepo.addReview(bookId, review);
//    }
//
//
//    private void abortIfBookDoesNotExist(Integer id) {
//        booksRepo.find(id).orElseThrow(() -> new ResourceNotFoundException(Book.class.getSimpleName(), id));
//    }
}
