package ru.renatrenat.firstcrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.renatrenat.firstcrud.models.Book;
import ru.renatrenat.firstcrud.models.Person;

import java.util.List;
import java.util.Optional;

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
        jdbcTemplate.update("INSERT INTO \"Book\"(author, name, year) VALUES(?,?,?)",book.getAuthor(), book.getName(), book.getYear());
    }

    public void update(int id, Book updBook){
        jdbcTemplate.update("UPDATE \"Book\" SET author=?, name=?, year=? WHERE id=? ", updBook.getAuthor(), updBook.getName(), updBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM \"Book\" WHERE id=?", id);
    }

    // Join'им таблицы Book и Person и получаем человека, которому принадлежит книга с указанным id
    public Optional<Person> getBookOwner(int id) {
        // Выбираем все колонки таблицы Person из объединенной таблицы
        return jdbcTemplate.query("SELECT \"Person\".* FROM \"Book\" JOIN \"Person\" ON \"Book\".owner_id = \"Person\".id " +
                        "WHERE \"Book\".id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    // Освбождает книгу (этот метод вызывается, когда человек возвращает книгу в библиотеку)
    public void release(int id) {
        jdbcTemplate.update("UPDATE \"Book\" SET owner_id=NULL WHERE id=?", id);
    }

    // Назначает книгу человеку (этот метод вызывается, когда человек забирает книгу из библиотеки)
    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE \"Book\" SET owner_id=? WHERE id=?", selectedPerson.getId(), id);
    }

}
