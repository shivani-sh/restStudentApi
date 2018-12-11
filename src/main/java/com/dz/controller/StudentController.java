package com.dz.controller;

import com.dz.model.Student;
import com.dz.service.StudentService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@EnableTransactionManagement(proxyTargetClass = true)
public class StudentController {

    private final Logger log = LogManager.getLogger(StudentController.class.getName());

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    /**
     * return the student List to the json format
     * @return studentList
     */

    @RequestMapping(value = "/")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentList = studentService.display();
        try {
            for (Student student : studentList) {
                log.info(student.getId() + "" + student.getName() + "" + student.getAge() + "controller");
            }
            return new ResponseEntity<>(studentList, HttpStatus.OK);

        } catch (NullPointerException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     *  Add  a new Student
     */
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public ResponseEntity<Void> addStudent(@RequestBody Student student, UriComponentsBuilder uriComponentsBuilder) {
        log.info(student.getId() + "  " + student.getName() + " " + student.getAge() + " adding data");
        studentService.addStudent(student);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/student/{id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * @param student to set the record in new student
     * @return update student Record
     */
    @RequestMapping(value = "/updateStudent", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {

        log.info( + student.getAge() + " " + student.getName() + "  " + student.getId());

        try {
            studentService.updatedata(student);
            return new ResponseEntity(student, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity("no Data found ", HttpStatus.NO_CONTENT);
        }
    }

    /**
     * @param student delete record
     * @return
     */
    @RequestMapping(value = "/deletestudent", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudent(@RequestBody Student student) {
        try {
            studentService.deletedata(student);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * get student by id
     * @param id id
     * @return
     */
    @RequestMapping(value = "/getstudentbyid/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {
        try {
            Student student = studentService.getStudentById(id);
            return new ResponseEntity(student, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(" Data  not Mathched in Database", HttpStatus.NOT_FOUND);
        }
    }
}