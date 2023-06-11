package com.controller;

import com.bean.Book;
import com.bean.Card;
import com.bean.Student;
import com.exception.ExistException;
import com.exception.ValidateException;
import com.service.impl.BookServiceImpl;
import com.service.impl.CardServiceImpl;
import com.service.impl.StudentServiceImpl;
import com.service.itf.BookService;
import com.service.itf.CardService;
import com.service.itf.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "libraryServlet", value = "/library")
public class LibraryServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();
    private StudentService studentService = new StudentServiceImpl();
    private CardService cardService = new CardServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "book";
        }

        switch (action) {
            case "book":
                showListBook(req, resp);
                break;
            case "student":
                showListStudent(req, resp);
                break;
            case "card":
                showListCard(req, resp);
                break;
            case "borrowedBook":
                showListBorrowedBook(req, resp);
                break;
            default:
                showListBook(req, resp);
                break;
        }
    }

    private void showListBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.display();
        int select = 1;
        req.setAttribute("select", select);
        req.setAttribute("books", books);
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, resp);
    }

    private void showListStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.display();
        int select = 2;
        req.setAttribute("select", select);
        req.setAttribute("students", students);
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, resp);
    }

    private void showListCard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Card> cards = cardService.display();
        int select = 3;
        req.setAttribute("select", select);
        req.setAttribute("cards", cards);
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, resp);
    }

    private void showListBorrowedBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.findBorrowedBook();
        int select = 4;
        req.setAttribute("select", select);
        req.setAttribute("books", books);
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "editBook":
                doEditBook(req, resp);
                break;
            case "delBook":
                doDelBook(req, resp);
                break;
            case "delCard":
                doDelCard(req, resp);
                break;
            case "initBorrow":
                doInitBorrow(req, resp);
                break;
            case "borrow":
                doBorrow(req, resp);
                break;
            case "updateCard":
                doUpdateCard(req, resp);
                break;
            case "createBook":
                doCreateBook(req, resp);
                break;
            case "createStu":
                doCreateStu(req, resp);
                break;
            case "searchBook":
                doSearchBook(req, resp);
                break;
            case "searchStuByName":
                doSearchStuByName(req, resp);
                break;
            case "searchCard":
                doSearchCard(req, resp);
                break;
            case "editStu":
                doEditStu(req, resp);
                break;
            default:
                break;
        }
    }

    private void doEditBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        String newBookName = req.getParameter("newBookName");
        String newAuthor = req.getParameter("newAuthor");
        String newDescription = req.getParameter("newDescription");
        int newQuantity = Integer.parseInt(req.getParameter("newQuantity"));

        Book book = new Book(bookId, newBookName, newAuthor, newDescription, newQuantity);
        bookService.update(book);
        resp.sendRedirect("/library");
    }

    private void doDelBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int delBookId = Integer.parseInt(req.getParameter("delBookId"));

        bookService.delete(delBookId);
        resp.sendRedirect("/library");
    }

    private void doDelCard(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String delCardId = req.getParameter("delCardId");

        cardService.delete(delCardId);
        resp.sendRedirect("/library");
    }

    private void doInitBorrow(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int initQuantity = Integer.parseInt(req.getParameter("initQuantity"));
        if (initQuantity == 0) {
            String errorMsg = "Không còn sách này trong thư viện";
            req.setAttribute("error", errorMsg);
        } else {
            List<Card> cards = cardService.display();
            int maxCardId = 0;
            if (cards.size() == 0) {
                maxCardId = 1;
            } else {
                for (Card card : cards) {
                    int id = Integer.parseInt(card.getCardId().substring(3));
                    if (id + 1 > maxCardId) {
                        maxCardId = id + 1;
                    }
                }
            }

            String initCardId;
            if (maxCardId < 10) {
                initCardId = "MS-000" + maxCardId;
            } else if (maxCardId < 100) {
                initCardId = "MS-00" + maxCardId;
            } else if (maxCardId < 1000) {
                initCardId = "MS-0" + maxCardId;
            } else {
                initCardId = "MS-" + maxCardId;
            }

            int initBookId = Integer.parseInt(req.getParameter("initBookId"));
            String initBookName = req.getParameter("initBookName");
            String initAuthor = req.getParameter("initAuthor");
            String initDescription = req.getParameter("initDescription");
            Book book = new Book(initBookId, initBookName, initAuthor, initDescription, initQuantity);

            List<Student> students = studentService.display();

            LocalDate initLoanDate = LocalDate.now();

            req.setAttribute("initCardId", initCardId);
            req.setAttribute("book", book);
            req.setAttribute("students", students);
            req.setAttribute("initLoanDate", initLoanDate);
        }
        req.getRequestDispatcher("WEB-INF/view/borrow.jsp").forward(req, resp);
    }

    private void doBorrow(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            String cardId = req.getParameter("cardId");

            if (!cardId.matches("^MS-[\\d]{4}$")) {
                throw new ValidateException();
            }

            List<Card> cards = cardService.display();

            for (Card c : cards) {
                if (c.getCardId().equals(cardId)) {
                    throw new ExistException();
                }
            }

            int bookId = Integer.parseInt(req.getParameter("borrowBookId"));
            String bookName = req.getParameter("borrowBookName");
            String author = req.getParameter("borrowAuthor");
            String description = req.getParameter("borrowDescription");
            int quantity = Integer.parseInt(req.getParameter("borrowQuantity")) - 1;
            Book book = new Book(bookId, bookName, author, description, quantity);
            bookService.update(book);

            Student student = null;

            List<Student> students = studentService.display();
            int stuId = Integer.parseInt(req.getParameter("borrowStuId"));
            for (Student stu : students) {
                if (stu.getStuId() == stuId) {
                    student = stu;
                    break;
                }
            }
            LocalDate loanDate = LocalDate.parse(req.getParameter("loanDate"));
            LocalDate returnDate = LocalDate.parse(req.getParameter("returnDate"));

            Card card = new Card(cardId, book, student, true, loanDate, returnDate);
            cardService.create(card);
            resp.sendRedirect("/library");
        } catch (NumberFormatException e) {
            String errorMsg = "Bạn chưa chọn tên sinh viên";
            req.setAttribute("error", errorMsg);
            req.getRequestDispatcher("WEB-INF/view/borrow.jsp").forward(req, resp);
        } catch (ValidateException e) {
            String errorMsg = "Mã mượn sách sai định dạng";
            req.setAttribute("error", errorMsg);
            req.getRequestDispatcher("WEB-INF/view/borrow.jsp").forward(req, resp);
        } catch (ExistException e) {
            String errorMsg = "Mã mượn sách này đã tồn tại";
            req.setAttribute("error", errorMsg);
            req.getRequestDispatcher("WEB-INF/view/borrow.jsp").forward(req, resp);
        }
    }

    private void doUpdateCard(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Card> cards = cardService.display();
        List<Book> books = bookService.display();

        String cardId = req.getParameter("updateCardId");
        int bookId = 0;
        for (Card card : cards) {
            if (card.getCardId().equals(cardId)) {
                bookId = card.getBook().getBookId();
                break;
            }
        }
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                bookService.update(new Book(bookId, book.getBookName(), book.getAuthor(),
                        book.getDescription(), book.getQuantity() + 1));
                break;
            }
        }
        cardService.update(cardId);
        resp.sendRedirect("/library?action=card");
    }

    private void doCreateBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Book> books = bookService.display();
        int bookId = 0;
        int max = 0;
        if (books.size() == 0) {
            bookId = 1;
        } else {
            for (Book book : books) {
                if (book.getBookId() > max) {
                    max = book.getBookId();
                }
            }
            bookId = max + 1;
        }
        String bookName = req.getParameter("createBookName");
        String author = req.getParameter("createAuthor");
        String description = req.getParameter("createDescription");
        int quantity = Integer.parseInt(req.getParameter("createQuantity"));

        Book book = new Book(bookId, bookName, author, description, quantity);
        bookService.create(book);
        resp.sendRedirect("/library?action=book");
    }

    private void doCreateStu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Student> students = studentService.display();
        int stuId = 0;
        int max = 0;
        if (students.size() == 0) {
            stuId = 1;
        } else {
            for (Student stu : students) {
                if (stu.getStuId() > max) {
                    max = stu.getStuId();
                }
            }
            stuId = max + 1;
        }
        String stuName = req.getParameter("createStuName");
        String grade = req.getParameter("createGrade");

        Student student = new Student(stuId, stuName, grade);
        studentService.create(student);
        resp.sendRedirect("/library?action=student");
    }

    private void doSearchBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str1 = req.getParameter("searchBookByName");
        String bookName = "%" + str1 + "%";
        String str2 = req.getParameter("searchBookByAuthor");
        String author = "%" + str2 + "%";

        List<Book> books = bookService.search(bookName, author);

        if (books.size() == 0) {
            int select = 1;
            String errorMsg = "List of book is empty!";
            req.setAttribute("select", select);
            req.setAttribute("error", errorMsg);
        } else {
            int select = 1;
            req.setAttribute("select", select);
            req.setAttribute("books", books);
        }
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, resp);
    }

    private void doSearchStuByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("searchStuName");
        String StuName = "%" + str + "%";
        List<Student> students = studentService.searchByName(StuName);

        if (students.size() == 0) {
            int select = 2;
            String errorMsg = "List of student is empty!";
            req.setAttribute("select", select);
            req.setAttribute("error", errorMsg);
        } else {
            int select = 2;
            req.setAttribute("select", select);
            req.setAttribute("students", students);
        }
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, resp);
    }

    private void doSearchCard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str1 = req.getParameter("searchCardByBookName");
        String bookName = "%" + str1 + "%";
        String str2 = req.getParameter("searchCardByStuName");
        String stuName = "%" + str2 + "%";
        List<Card> cards = cardService.search(bookName, stuName);

        if (cards.size() == 0) {
            int select = 3;
            String errorMsg = "List of card is empty!";
            req.setAttribute("select", select);
            req.setAttribute("error", errorMsg);
        } else {
            int select = 3;
            req.setAttribute("select", select);
            req.setAttribute("cards", cards);
        }
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, resp);
    }

    private void doEditStu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int stuId = Integer.parseInt(req.getParameter("stuId"));
        String newStuName = req.getParameter("newStuName");
        String newGrade = req.getParameter("newGrade");

        Student student = new Student(stuId, newStuName, newGrade);
        studentService.update(student);
        resp.sendRedirect("/library?action=student");
    }
}
