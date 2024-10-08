package com.cw.bookapi.repo;

import com.cw.bookapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Long> {
    // save() -- save() method is also equivalent to performing an update
// findOne()
// findById()
// findByEmail()
// findAll()
// count()
// delete()
// deleteById()
}
