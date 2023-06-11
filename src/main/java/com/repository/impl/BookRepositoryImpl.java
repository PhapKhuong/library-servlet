package com.repository.impl;

import com.bean.Book;
import com.bean.Student;
import com.database.DBConnection;
import com.database.MyQuery;
import com.repository.itf.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    @Override
    public List<Book> display() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Book> bookList = new ArrayList<>();

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.SELECT_ALL_BOOK);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int bookId = resultSet.getInt("bookId");
                    String bookName = resultSet.getString("bookName");
                    String author = resultSet.getString("author");
                    String description = resultSet.getString("description");
                    int quantity = resultSet.getInt("quantity");

                    bookList.add(new Book(bookId, bookName, author, description, quantity));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnection.close();
            }
        }
        return bookList;
    }

    @Override
    public void update(Book book) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.UPDATE_BOOK);
                statement.setString(1, book.getBookName());
                statement.setString(2, book.getAuthor());
                statement.setString(3, book.getDescription());
                statement.setInt(4, book.getQuantity());
                statement.setInt(5, book.getBookId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnection.close();
            }
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.DELETE_BOOK);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnection.close();
            }
        }
    }

    @Override
    public void create(Book book) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;

        if(connection!=null) {
            try {
                statement = connection.prepareStatement(MyQuery.CREATE_BOOK);
                statement.setInt(1,book.getBookId());
                statement.setString(2, book.getBookName());
                statement.setString(3, book.getAuthor());
                statement.setString(4, book.getDescription());
                statement.setInt(5, book.getQuantity());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnection.close();
            }
        }
    }

    @Override
    public List<Book> search(String str1, String str2) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Book> bookList = new ArrayList<>();

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.SEARCH_BOOK);
                statement.setString(1,str1);
                statement.setString(2,str2);

                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int bookId = resultSet.getInt("bookId");
                    String bookName = resultSet.getString("bookName");
                    String author = resultSet.getString("author");
                    String description = resultSet.getString("description");
                    int quantity = resultSet.getInt("quantity");

                    bookList.add(new Book(bookId, bookName, author, description, quantity));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnection.close();
            }
        }
        return bookList;
    }

    @Override
    public List<Book> findBorrowedBook() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Book> bookList = new ArrayList<>();

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.SELECT_BORROWED_BOOK);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int bookId = resultSet.getInt("bookId");
                    String bookName = resultSet.getString("bookName");
                    String author = resultSet.getString("author");
                    String description = resultSet.getString("description");
                    int quantity = resultSet.getInt("count(book.bookId)");

                    bookList.add(new Book(bookId, bookName, author, description, quantity));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnection.close();
            }
        }
        return bookList;
    }
}
