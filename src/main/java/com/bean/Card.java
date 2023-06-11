package com.bean;

import java.time.LocalDate;

public class Card {
    private String cardId;
    private Book book;
    private Student student;
    private boolean status;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Card() {
    }

    public Card(String cardId, Book book, Student student, boolean status, LocalDate loanDate, LocalDate returnDate) {
        this.cardId = cardId;
        this.book = book;
        this.student = student;
        this.status = status;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
