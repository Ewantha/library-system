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
        List<Book> books = bookRepository.findBookByAvailability(true);
        log.info("Successfully retrieved {} books", books.size());

        return books;
    }
}
