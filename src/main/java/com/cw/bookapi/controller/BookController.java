package com.cw.bookapi.controller;

import com.cw.bookapi.exception.ResourceNotFoundException;
import com.cw.bookapi.model.Book;
import com.cw.bookapi.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity<Object> allBooks() {
        List<Book> bookList = bookService.findAll();
        if (bookList.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable("id") Long bookId) {
        Book book = bookService.findById(bookId).orElseThrow(() -> new ResourceNotFoundException());
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createBook(@RequestBody @Valid Book book){
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBookById(@PathVariable("id") Long bookId, @RequestBody @Valid Book book) {
        Book updateBook = bookService.findById(bookId).map(b -> {
            b.setAuthor(book.getAuthor());
            b.setTitle(book.getTitle());
            return bookService.save(b);
        }).orElseThrow(() -> new ResourceNotFoundException());
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBookById(@PathVariable("id") Long bookId) {

        Book deleteBook = bookService.findById(bookId).map(b -> {
            bookService.deleteById(b.getId());
            return b;
        }).orElseThrow(ResourceNotFoundException::new);

        String response = String.format("%s deleted successfully", deleteBook.getTitle());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Object> countTotalBooks() {

        long count = bookService.count();

        if (count <= 0)
            return new ResponseEntity<>("No book found.", HttpStatus.NOT_FOUND);

        Map<String, Object> totalBooks = new HashMap<String, Object>();
        totalBooks.put("total", count);

        return new ResponseEntity<>(totalBooks, HttpStatus.OK);
    }

}
