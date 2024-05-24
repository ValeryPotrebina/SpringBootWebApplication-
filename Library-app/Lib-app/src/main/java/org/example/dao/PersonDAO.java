package org.example.dao;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        String SQL_REQUEST = "SELECT * FROM PERSON";
        return jdbcTemplate.query(SQL_REQUEST, new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(int id) {
        String SQL_REQUEST = "SELECT * FROM PERSON WHERE id=?";
        return jdbcTemplate.query(SQL_REQUEST, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        String SQL_REQUEST = "INSERT INTO PERSON(name, age) VALUES(?, ?)";
        jdbcTemplate.update(SQL_REQUEST, person.getName(), person.getAge());
    }

    public void update(int id, Person person) {
        String SQL_REQUEST = "UPDATE PERSON SET name=?, age=? WHERE id=?";
        jdbcTemplate.update(SQL_REQUEST, person.getName(), person.getAge(), id);
    }

    public void delete(int id) {
        String SQL_REQUEST = "DELETE FROM PERSON WHERE id=?";
        jdbcTemplate.update(SQL_REQUEST, id);
    }

}
