package com.ewanthau.librarysystem.service;

import com.ewanthau.librarysystem.dto.AddBookRequest;
import com.ewanthau.librarysystem.entity.Book;
import com.ewanthau.librarysystem.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LibraryServiceImp implements LibraryService {

    final BookRepository bookRepository;

    public LibraryServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book addBook(AddBookRequest addBookRequest) {
        log.debug("Adding new book: {}", addBookRequest);

        Book book = new Book();
        book.setAuthor(addBookRequest.getAuthor());
        book.setTitle(addBookRequest.getTitle());
        book.setAvailability(true);
        Book savedBook = bookRepository.save(book);
        log.info("Successfully added book: {}", savedBook);

        return savedBook;
    }

    public List<Book> getAvailableBooks() {
        log.debug("Getting available books");

        return bookRepository.findBookByAvailability(true);
    }

    public List<Book> getBooksByTitleAndAuthor(String title, String author) {
        log.debug("Searching books by title and author: {}", title);

        if (title != null && author != null) {
           return bookRepository.findBookByTitleAndAuthor(title, author);
        } else if (author != null) {
            return bookRepository.findBookByAuthor(author);
        } else if (title != null) {
            return bookRepository.findBookByTitle(title);
        } else {
            return bookRepository.findAll();
        }
    }

    public Book loanBook(Long id) {
        log.debug("Loaning a book: {}", id);
        Book book = bookRepository.getReferenceById(id);
        book.setAvailability(false);
        return bookRepository.save(book);
    }

    public Book returnBook(Long id) {
        log.debug("Returning a book: {}", id);
        Book book = bookRepository.getReferenceById(id);
        book.setAvailability(true);
        return bookRepository.save(book);
    }
}
