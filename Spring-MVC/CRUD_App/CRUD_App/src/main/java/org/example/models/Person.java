package org.example.models;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "NAME SHOULD NOT BE EMPTY")
    @Size(min = 2, max = 12, message = "WRONG NAME")
    private String name;
    @Min(value = 0, message = "AGE CAN NOT BE NEGATIVE")
    @Max(value = 120, message = "AGE SHOULD BE LESS 120")
    private int age;
    @NotEmpty(message = "EMAIL SHOULD NOT BE EMPTY")
    @Email(message = "WRONG EMAIL")
    private String email;

    public Person() {}

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
