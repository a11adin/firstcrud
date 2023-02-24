package ru.renatrenat.firstcrud.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int id;

    //@NotEmpty(message = "Автор не может быть пустым")
    //@Max(value = 100, message = "Имя автора должно быть меньше 100 знаков")
    private String author;

    //@NotEmpty(message = "Название не может быть пустым")
    //@Max(value = 100, message = "Название книги должно быть меньше 100 знаков")
    private String name;

    //@NotEmpty(message = "Год не может быть пустым")
    //@Size(min = 4, max = 4, message = "Год должен быть из 4 цифр")
    private int year;

    private int owner_id;

    public Book() {
    }

    public Book(int id, String author, String name, int year, int owner_id) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.year = year;
        this.owner_id = owner_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }
}
