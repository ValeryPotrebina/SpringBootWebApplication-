package org.example.dao;


import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks(){
        String SQL_REQUEST = "SELECT * FROM BOOK";
        List<Book> books = jdbcTemplate.query(SQL_REQUEST, new BeanPropertyRowMapper<>(Book.class));
        System.out.println(books);
        return books;
    }

    public Book getBook(int id_book){
        String SQL_REQUEST = "SELECT * FROM BOOK WHERE id_book=?";
        return jdbcTemplate.query(SQL_REQUEST, new Object[]{id_book}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        String SQL_REQUEST = "INSERT INTO BOOK(id_person, book_name, book_author, year_of_production) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(SQL_REQUEST, book.getId_person(), book.getBook_name(), book.getBook_author(), book.getYear_of_production());
    }

    public void update(int id_book, Book book) {
        String SQL_REQUEST = "UPDATE BOOK SET id_person=?, book_name=?, book_author=?, year_of_production=? WHERE id_book=?";
        jdbcTemplate.update(SQL_REQUEST, book.getId_person(), book.getBook_name(), book.getBook_author(), book.getYear_of_production(), id_book);
    }

    public void delete(int id_book) {
        String SQL_REQUEST = "DELETE FROM BOOK WHERE id_book=?";
        jdbcTemplate.update(SQL_REQUEST, id_book);
    }
}
