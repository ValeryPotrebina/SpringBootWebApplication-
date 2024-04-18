package org.example.dao;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        String SQL_REQUEST = "SELECT * FROM Person";
        // new BeanPropertyRowMapper<>(Person.class) == new PersonMapper
        return jdbcTemplate.query(SQL_REQUEST, new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id) {
        String SQL_REQUEST = "SELECT * FROM Person WHERE id=?";
        return jdbcTemplate.query(SQL_REQUEST, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public void save(Person person)  {
        String SQL_REQUEST = "INSERT INTO Person VALUES(1, ?, ?, ?)";
        System.out.println("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] " + "saved newPerson");
        jdbcTemplate.update(SQL_REQUEST, person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person person) {
        String SQL_REQUEST = "UPDATE Person SET name=?, age=?, email=? WHERE id=?";
        jdbcTemplate.update(SQL_REQUEST, person.getName(), person.getAge(), person.getEmail(), id);
    }

    public void delete(int id) {
        String SQL_REQUEST = "DELETE FROM Person WHERE id=?";
        jdbcTemplate.update(SQL_REQUEST, id);
    }
}
