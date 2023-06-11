package com.repository.impl;

import com.util.MyUtil;
import com.bean.Book;
import com.bean.Card;
import com.bean.Student;
import com.database.DBConnection;
import com.database.MyQuery;
import com.repository.itf.CardRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CardRepositoryImpl implements CardRepository {

    @Override
    public List<Card> display() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Card> cardList = new ArrayList<>();

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.SELECT_ALL_CARD);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String cardId = resultSet.getString("cardId");

                    int bookId = resultSet.getInt("bookId");
                    String bookName = resultSet.getString("bookName");
                    String author = resultSet.getString("author");
                    String description = resultSet.getString("description");
                    int quantity = resultSet.getInt("quantity");

                    int stuId = resultSet.getInt("stuId");
                    String stuName = resultSet.getString("stuName");
                    String grade = resultSet.getString("grade");

                    int s = resultSet.getInt("status");
                    boolean status;
                    if (s == 1) {
                        status = true;
                    } else {
                        status = false;
                    }
                    Date d1 = resultSet.getDate("loanDate");
                    LocalDate loanDate = MyUtil.convertToLocalDateViaSqlDate(d1);
                    Date d2 = resultSet.getDate("returnDate");
                    LocalDate returnDate = MyUtil.convertToLocalDateViaSqlDate(d2);

                    Book book = new Book(bookId, bookName, author, description, quantity);
                    Student student = new Student(stuId, stuName, grade);

                    cardList.add(new Card(cardId, book, student, status, loanDate, returnDate));
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
        return cardList;
    }

    @Override
    public void create(Card card) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.INSERT_NEW_CARD);
                statement.setString(1, card.getCardId());
                statement.setInt(2, card.getBook().getBookId());
                statement.setInt(3, card.getStudent().getStuId());
                int status = 0;
                if (card.isStatus()) {
                    status = 1;
                }
                statement.setInt(4, status);

                Date loanDate = java.sql.Date.valueOf(card.getLoanDate());
                Date returnDate = java.sql.Date.valueOf(card.getReturnDate());
                statement.setDate(5, loanDate);
                statement.setDate(6, returnDate);

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
    public void update(String cardId) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.UPDATE_CARD);
                statement.setString(1, cardId);
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
    public List<Card> search(String str1, String str2) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Card> cardList = new ArrayList<>();

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.SEARCH_CARD);
                statement.setString(1, str1);
                statement.setString(2, str2);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String cardId = resultSet.getString("cardId");

                    int bookId = resultSet.getInt("bookId");
                    String bookName = resultSet.getString("bookName");
                    String author = resultSet.getString("author");
                    String description = resultSet.getString("description");
                    int quantity = resultSet.getInt("quantity");

                    int stuId = resultSet.getInt("stuId");
                    String stuName = resultSet.getString("stuName");
                    String grade = resultSet.getString("grade");

                    int s = resultSet.getInt("status");
                    boolean status;
                    if (s == 1) {
                        status = true;
                    } else {
                        status = false;
                    }
                    Date d1 = resultSet.getDate("loanDate");
                    LocalDate loanDate = MyUtil.convertToLocalDateViaSqlDate(d1);
                    Date d2 = resultSet.getDate("returnDate");
                    LocalDate returnDate = MyUtil.convertToLocalDateViaSqlDate(d2);

                    Book book = new Book(bookId, bookName, author, description, quantity);
                    Student student = new Student(stuId, stuName, grade);

                    cardList.add(new Card(cardId, book, student, status, loanDate, returnDate));
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
        return cardList;
    }

    @Override
    public void delete(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.DELETE_CARD);
                statement.setString(1, id);
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
}
