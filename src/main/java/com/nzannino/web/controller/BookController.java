package com.nzannino.web.controller;

import com.nzannino.web.data.BookService;
import com.nzannino.web.data.db.tables.pojos.Book;
import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: zannino
 * Date: 24/03/2015
 * Time: 14:21
 */
@Api(basePath = "/book", value = "book", description = "book", produces = "application/json")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Book> getBooks(){
        return bookService.list();
    }

}
