package com.repository.itf;

import com.bean.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> display ();

    void create (Student student);

    List<Student> searchByName(String str);

    void update (Student student);
}
