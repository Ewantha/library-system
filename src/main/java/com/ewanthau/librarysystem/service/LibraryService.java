package com.ewanthau.librarysystem.service;

import com.ewanthau.librarysystem.dto.AddBookRequest;
import com.ewanthau.librarysystem.entity.Book;

import java.util.List;

public interface LibraryService {

    Book addBook(AddBookRequest addBookRequest);
    List<Book> getAvailableBooks();
    List<Book> getBooksByTitleAndAuthor(String title, String author);
    Book loanBook(Long id);
    Book returnBook(Long id);

}
