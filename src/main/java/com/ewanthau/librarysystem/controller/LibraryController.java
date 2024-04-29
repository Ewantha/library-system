package com.ewanthau.librarysystem.controller;

import com.ewanthau.librarysystem.dto.AddBookRequest;
import com.ewanthau.librarysystem.entity.Book;
import com.ewanthau.librarysystem.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/book/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        return new ResponseEntity<>(libraryService.getAvailableBooks(), HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> addBook(@RequestBody AddBookRequest addBookRequest) {
        return new ResponseEntity<>(libraryService.addBook(addBookRequest), HttpStatus.CREATED);
    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> searchBook(@RequestParam(required = false) String title,
                                                 @RequestParam(required = false) String author) {
        return new ResponseEntity<>(libraryService.getBooksByTitleAndAuthor(title, author), HttpStatus.OK);
    }

    @PutMapping("/book/loan/{id}")
    public ResponseEntity<Book> loanBook(@PathVariable  Long id) {
        return new ResponseEntity<>(libraryService.loanBook(id), HttpStatus.OK);
    }

    @PutMapping("/book/return/{id}")
    public ResponseEntity<Book> returnBook(@PathVariable  Long id) {
        return new ResponseEntity<>(libraryService.returnBook(id), HttpStatus.OK);
    }
}
