package com.ewanthau.librarysystem.repository;

import com.ewanthau.librarysystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByAvailability(boolean availability);
    List<Book> findBookByTitle(String title);
    List<Book> findBookByAuthor(String author);
    List<Book> findBookByTitleAndAuthor(String title, String author);
}
