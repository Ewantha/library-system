package com.ewanthau.librarysystem.service;

import com.ewanthau.librarysystem.dto.AddBookRequest;
import com.ewanthau.librarysystem.dto.BookAvailableMessage;
import com.ewanthau.librarysystem.entity.Book;
import com.ewanthau.librarysystem.exception.BookNotFoundException;
import com.ewanthau.librarysystem.repository.BookRepository;
import com.ewanthau.librarysystem.util.WebSocketClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@CacheConfig(cacheNames = {"books"})
public class LibraryServiceImp implements LibraryService {

    final BookRepository bookRepository;
    final WebSocketClient webSocketClient;

    @CacheEvict(allEntries = true)
    public Book addBook(AddBookRequest addBookRequest) {
        log.debug("Adding new book: {}", addBookRequest);

        Book book = new Book();
        book.setAuthor(addBookRequest.getAuthor());
        book.setTitle(addBookRequest.getTitle());
        book.setAvailability(true);
        Book savedBook = bookRepository.save(book);
        log.info("Successfully added book: {}", savedBook);

        webSocketClient.sendMessage(BookAvailableMessage.builder()
                .content(book.getTitle() + " by " + book.getAuthor() + " has been newly added!").build());

        return savedBook;
    }

    @Cacheable(value = "availableBooks")
    public List<Book> getAvailableBooks() {
        log.debug("Getting available books");

        return bookRepository.findBookByAvailability(true);
    }

    @Cacheable(value = "searchBooks")
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

    @CacheEvict(allEntries = true)
    public Book loanBook(Long id) {
        try {
            log.debug("Loaning a book: {}", id);
            Book book = bookRepository.getReferenceById(id);
            book.setAvailability(false);
            return bookRepository.save(book);
        } catch (EntityNotFoundException e) {
            log.warn("Book not found to loan: {}", id);
            throw new BookNotFoundException("Book with id " + id + " not found");
        }
    }

    @CacheEvict(allEntries = true)
    public Book returnBook(Long id) {
        try {
            log.debug("Returning a book: {}", id);
            Book book = bookRepository.getReferenceById(id);
            book.setAvailability(true);
            book = bookRepository.save(book);

            webSocketClient.sendMessage(BookAvailableMessage.builder()
                    .content(book.getTitle() + " by " + book.getAuthor() + " has been returned!").build());

            return book;
        } catch (EntityNotFoundException e) {
            log.warn("Book not found to return: {}", id);
            throw new BookNotFoundException("Book with id " + id + " not found");
        }
    }
}
