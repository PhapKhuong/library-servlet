package com.service.itf;

import com.bean.Student;

import java.util.List;

public interface StudentService {
    List<Student> display ();

    void create (Student student);

    List<Student> searchByName(String str);

    void update (Student student);
}
