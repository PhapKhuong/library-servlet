package com.service.impl;

import com.bean.Book;
import com.bean.Student;
import com.repository.impl.BookRepositoryImpl;
import com.repository.itf.BookRepository;
import com.service.itf.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository repository = new BookRepositoryImpl();
    @Override
    public List<Book> display() {
        return repository.display();
    }

    @Override
    public void update(Book book) {
        repository.update(book);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void create(Book book) {
        repository.create(book);
    }

    @Override
    public List<Book> search(String str1, String str2) {
        return repository.search(str1, str2);
    }

    @Override
    public List<Book> findBorrowedBook() {
        return repository.findBorrowedBook();
    }
}
