package com.dz.service;

import com.dz.model.Student;

import java.util.List;

public interface StudentService {


    void addStudent(Student student);

    List<Student> display();

    void deletedata(Student student);

    void updatedata(Student student);

    Student getStudentById(int id);


}
