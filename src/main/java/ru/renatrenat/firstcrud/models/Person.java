package ru.renatrenat.firstcrud.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    //@NotEmpty(message = "ФИО не может быть пустым")
    //@Max(value = 100, message = "ФИО должно быть меньше 100 знаков")
    private String name;

    //@NotEmpty(message = "Дата рождения не может быть пустой")
    //@Size(min = 10, max = 10, message = "Дата должна быть в формате дд.мм.гггг")
    private String birthdate;

    public Person() {

    }

    public Person(int id, String name, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

}
