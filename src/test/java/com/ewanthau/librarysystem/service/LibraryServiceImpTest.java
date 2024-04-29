package com.ewanthau.librarysystem.service;

import com.ewanthau.librarysystem.dto.AddBookRequest;
import com.ewanthau.librarysystem.entity.Book;
import com.ewanthau.librarysystem.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LibraryServiceImpTest {

    @InjectMocks
    LibraryServiceImp libraryService;

    @Mock
    BookRepository bookRepository;

    @Test
    void addBook() {
        assertDoesNotThrow(()->libraryService.addBook(new AddBookRequest()));
    }

    @Test
    void getAvailableBooks() {
        assertDoesNotThrow(()->libraryService.getAvailableBooks());
    }

    @Test
    void getBooksByTitleAndAuthor() {
        assertDoesNotThrow(()->libraryService.getBooksByTitleAndAuthor("Hello", "Hello"));
    }

    @Test
    void loanBook() {
        when(bookRepository.getReferenceById(anyLong())).thenReturn(new Book());
        assertDoesNotThrow(()->libraryService.loanBook(1L));
    }

    @Test
    void returnBook() {
        when(bookRepository.getReferenceById(anyLong())).thenReturn(new Book());
        assertDoesNotThrow(()->libraryService.returnBook(1L));
    }
}