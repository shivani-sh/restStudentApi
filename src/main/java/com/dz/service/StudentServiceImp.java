package com.dz.service;

import com.dz.dao.StudentDaoImp;
import com.dz.model.Student;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    private final Logger logger = LogManager.getLogger(StudentServiceImp.class);
    private StudentDaoImp studentDao;

    @Autowired
    public StudentServiceImp(StudentDaoImp studentDao) {
        this.studentDao = studentDao;
    }


    /**
     * add data
     *
     * @param student object
     */
    @Override
    public void addStudent(Student student) {
        try {
            studentDao.addStudent(student);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * here we get a student list
     *
     * @return list of student details
     */
    public List<Student> display() {

        return studentDao.display();
    }

    /**
     * receive the student id
     *
     * @param student object
     */

    public void deletedata(Student student) {
        studentDao.deleteData(student);
    }

    /**
     * update data where id receive  in data
     *
     * @param student object
     */
    public void updatedata(Student student) {
        studentDao.updateData(student);
    }

    /**
     * here we get student by id
     *
     * @param id of student
     * @return student object
     */
    public Student getStudentById(int id) {
        return studentDao.getStudentById(id);
    }

}
