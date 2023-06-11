package com.service.impl;

import com.bean.Student;
import com.repository.impl.StudentRepositoryImpl;
import com.repository.itf.StudentRepository;
import com.service.itf.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentRepository repository = new StudentRepositoryImpl();

    @Override
    public List<Student> display() {
        return repository.display();
    }

    @Override
    public void create(Student student) {
        repository.create(student);
    }

    @Override
    public List<Student> searchByName(String str) {
        return repository.searchByName(str);
    }

    @Override
    public void update(Student student) {
        repository.update(student);
    }
}
