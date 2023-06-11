package com.bean;

public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String description;
    private int quantity;

    public Book() {
    }

    public Book (String bookName, String author, String description, int quantity){
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
    }

    public Book(int bookId, String bookName, String author, String description, int quantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
