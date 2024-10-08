package com.cw.bookapi.service;

import com.cw.bookapi.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookServiceInterface {
    public abstract Book save(Book book);
    public abstract List<Book> findAll();
    public abstract Optional<Book> findById(Long id);
    public abstract Book update(Book book);
    public abstract void deleteById(Long id);
    public abstract long count();

}

