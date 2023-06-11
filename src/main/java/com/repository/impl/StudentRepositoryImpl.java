package com.repository.impl;

import com.bean.Student;
import com.database.DBConnection;
import com.database.MyQuery;
import com.repository.itf.StudentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    @Override
    public List<Student> display() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> studentList = new ArrayList<>();

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.SELECT_ALL_STUDENT);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int stuId = resultSet.getInt("stuId");
                    String stuName = resultSet.getString("stuName");
                    String grade = resultSet.getString("grade");

                    studentList.add(new Student(stuId, stuName, grade));
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
        return studentList;
    }

    @Override
    public void create(Student student) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.CREATE_STUDENT);
                statement.setInt(1, student.getStuId());
                statement.setString(2, student.getStuName());
                statement.setString(3, student.getGrade());
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
    public List<Student> searchByName(String str) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> studentList = new ArrayList<>();

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.SEARCH_STUDENT_BY_NAME);
                statement.setString(1, str);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int stuId = resultSet.getInt("stuId");
                    String stuName = resultSet.getString("stuName");
                    String grade = resultSet.getString("grade");

                    studentList.add(new Student(stuId, stuName, grade));
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
        return studentList;
    }

    @Override
    public void update(Student student) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.UPDATE_STUDENT);
                statement.setString(1, student.getStuName());
                statement.setString(2, student.getGrade());
                statement.setInt(3, student.getStuId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.executeUpdate();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnection.close();
            }
        }
    }
}
