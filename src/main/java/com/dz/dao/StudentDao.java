package com.dz.dao;

import com.dz.model.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * this is dao interface which is implement by student imp dao
 */
public interface StudentDao {


    void addStudent(Student student) throws SQLException;

    List<Student> display();

    void deleteData(Student student);

    void updateData(Student student);

    Student getStudentById(int id);
}
