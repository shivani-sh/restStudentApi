package com.dz.dao;

import com.dz.model.Student;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository

public class StudentDaoImp implements StudentDao {


    private SessionFactory sessionFactory;
    private final Logger logger = LogManager.getLogger(StudentDaoImp.class);

    @Autowired
    public StudentDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /**
     * IN this we add data in list
     *
     * @param student object
     */
    @Override
    public void addStudent(Student student) {
        logger.info(student.getId() + "','" + student.getName() + "','" + student.getAge());
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.saveOrUpdate(student);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            logger.error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
    }

    /**
     * In this we display our list
     *
     * @return list of student details
     */

    @Override
    public List<Student> display() {
        try {

            Query query = sessionFactory.getCurrentSession().createQuery("From Student");
            List<Student> studentList = query.list();
            return studentList;
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * In this we delete data
     *
     * @param student object
     */
    @Override
    public void deleteData(Student student) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.delete(student);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * in this we update data
     *
     * @param student object
     */

    @Override
    public void updateData(Student student) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        logger.info(student.getId() + "','" + student.getName() + "','" + student.getAge());
        try {
            transaction.begin();
            session.saveOrUpdate(student);
            transaction.commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            transaction.rollback();
        } finally {
            session.close();
        }

    }


    /**
     * get student by id
     *
     * @param id of student
     * @return student object
     */
    public Student getStudentById(int id) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Student.class);
        try {
            criteria.add(Restrictions.eq("id", id));
            Student student = (Student) criteria.uniqueResult();
            logger.info(student.getId() + "','" + student.getName() + "','" + student.getAge());
            return student;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


}
