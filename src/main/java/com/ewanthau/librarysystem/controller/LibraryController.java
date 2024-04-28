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
}
