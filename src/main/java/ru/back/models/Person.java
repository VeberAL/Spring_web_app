package ru.back.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    //@NotEmpty ограничивает возможность оставить поле пустым и выводит сообщение при нарушении
    @NotEmpty(message = "Поле <Имя> не может быть пустым")

    //@Size устанавливает диапазон возможного кол-ва символов для поля
    @Size(min = 2, max = 30, message = "Поле <Имя> может содержать от 2 до 30 символов")
    private String name;

    //@Min устанавливает минимально возможное значение поля
    @Min(value = 0, message = "Поле <Возраст> не может быть меньше нуля")
    private int age;

    @NotEmpty(message = "Поле <Email> не может быть пустым")
    @Email(message = "Email не валидный")
    private String email;

    //
    public Person() {
    }
    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
