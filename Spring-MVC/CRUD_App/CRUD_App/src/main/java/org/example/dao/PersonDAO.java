package org.example.dao;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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


////////////////////////////////////
// Тестируем производительность пакетной вставки
////////////////////////////////////

    public void testMultipleUpdate() {
        List<Person> people = create1000People();
        long before = System.currentTimeMillis();
        for (Person person : people) {
            String SQL_REQUEST = "INSERT INTO Person VALUES(?, ?, ?, ?)";
            jdbcTemplate.update(SQL_REQUEST, person.getId(), person.getName(), person.getAge(), person.getEmail());
        }
        long after = System.currentTimeMillis();
        System.out.println("TIME WITHOUT[" + (after - before) + "]");
    }


    private List<Person> create1000People(){
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "name" + i, i, "a"+i+"@mail.ru"));
        }
        return people;
    }

    public void testBatchUpdate() {
        List<Person> people = create1000People();
        long before = System.currentTimeMillis();
        String SQL_REQUEST = "INSERT INTO Person VALUES(?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(SQL_REQUEST, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, people.get(i).getId());
                preparedStatement.setString(2, people.get(i).getName());
                preparedStatement.setInt(3, people.get(i).getAge());
                preparedStatement.setString(4, people.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });

        long after = System.currentTimeMillis();
        System.out.println("TIME WITH BATCH[" + (after - before) + "]");
    }
}
