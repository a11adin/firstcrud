package ru.renatrenat.firstcrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.renatrenat.firstcrud.models.Book;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM \"Book\"", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM \"Book\" WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO \"Book\"(author, name, year, owner_id) VALUES(?,?,?,0)",book.getAuthor(), book.getName(), book.getYear());
    }

    public void update(int id, Book updBook){
        jdbcTemplate.update("UPDATE \"Book\" SET author=?, name=?, year=? WHERE id=? ", updBook.getAuthor(), updBook.getName(), updBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM \"Book\" WHERE id=?", id);
    }

}
