package org.example.controllers;


import org.example.dao.BookDAO;
import org.example.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String showBooks(Model model){
//        System.out.println(bookDAO.getBooks());
        model.addAttribute("books", bookDAO.getBooks());
        return "books/index";
    }

    @GetMapping("/{id_book}")
    public String showBook(@PathVariable("id_book") int id_book, Model model){
        model.addAttribute("book", bookDAO.getBook(id_book));
        return "books/show";
    }

    @GetMapping("/new")
    public String addBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") Book book){
        bookDAO.save(book);
        return "redirect:/books";
    }


    @GetMapping("{id_book}/edit")
    public String update(Model model, @PathVariable("id_book") int id_book){
        model.addAttribute(bookDAO.getBook(id_book));
        return "books/edit";
    }

    @PatchMapping("{id_book}")
    public String update(@ModelAttribute("book") Book book, @PathVariable("id_book") int id_book){
        bookDAO.update(id_book, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id_book}")
    public String delete(@PathVariable("id_book") int id_book) {
        bookDAO.delete(id_book);
        return "redirect:/books";
    }
}
