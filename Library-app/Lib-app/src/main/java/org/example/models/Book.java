package org.example.models;

import javax.validation.constraints.Min;

public class Book {
    private int id_book;
    private int id_person;
    private String book_name;
    private String book_author;
    @Min(value = 1600, message = "WRONG INPUT")
    private int year_of_production;

    public Book(int id_book, int id_person, String name, String author, int productionYear) {
        this.id_book = id_book;
        this.id_person = id_person;
        this.book_name = name;
        this.book_author = author;
        this.year_of_production = productionYear;
    }

    public Book() {
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public int getYear_of_production() {
        return year_of_production;
    }

    public void setYear_of_production(int year_of_production) {
        this.year_of_production = year_of_production;
    }
}
