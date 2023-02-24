package ru.renatrenat.firstcrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.renatrenat.firstcrud.models.Book;
import ru.renatrenat.firstcrud.models.Person;

import java.util.List;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM \"Person\"", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM \"Person\" WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO \"Person\"(name, birthdate) VALUES(?, ?)", person.getName(), person.getBirthdate());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE \"Person\" SET name=?, birthdate=? WHERE id=?", updatedPerson.getName(), updatedPerson.getBirthdate(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM \"Person\" WHERE id=?", id);
    }


    // Здесь Join не нужен. И так уже получили человека с помощью отдельного метода
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM \"Book\" WHERE owner_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
