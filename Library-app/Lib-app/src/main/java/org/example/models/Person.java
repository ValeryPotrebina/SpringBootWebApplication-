package org.example.models;

import javax.validation.constraints.*;

public class Person {
    private int id;
//    @NotEmpty(message = "NAME SHOULD NOT BE EMPTY")
//    @Size(min = 2, max = 40, message = "WRONG INPUT")
    @Pattern(regexp = "[A-Z]\\w* [A-Z]\\w* [A-Z]*\\w*", message = "REQUIRED TO WRITE Name Surname Patric-name (if exists)")
    private String name;
//    @NotEmpty(message = "AGE SHOULD NOT BE EMPTY")
    @Min(value = 0, message = "AGE CAN NOT BE NEGATIVE")
    @Max(value = 120, message = "AGE SHOULD BE LESS 120")
    private int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person(){

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
}
