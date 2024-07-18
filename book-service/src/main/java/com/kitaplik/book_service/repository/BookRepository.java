package com.kitaplik.book_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitaplik.book_service.model.Book;


import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {

    Optional<Book> getBookByIsbn(String isbn);
}