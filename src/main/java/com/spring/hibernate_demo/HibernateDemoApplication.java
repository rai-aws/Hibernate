package com.spring.hibernate_demo;

import com.spring.hibernate_demo.dao.AppDao;
import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.dao.StudentDaoImplement;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Review;
import com.spring.hibernate_demo.entity.Student;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main class for the Spring Boot Hibernate application.
 * Demonstrates CRUD operations and entity mappings.
 */
@SpringBootApplication
public class HibernateDemoApplication {

    private final StudentDaoImplement studentDaoImplement;

    /**
     * Constructs the application with required DAO implementations.
     *
     * @param studentDaoImplement Student DAO implementation
     * @param appDaoImplement Application DAO implementation
     */
    public HibernateDemoApplication(StudentDaoImplement studentDaoImplement) {
        this.studentDaoImplement = studentDaoImplement;
    }

    /**
     * Main application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(HibernateDemoApplication.class, args);
    }

    /**
     * Runner bean to execute database queries sequentially on boot.
     */
    @Bean
    public CommandLineRunner commandLineRunner(StudentDao student, 
                                                AppDao app) {
        return runner -> {
            // createStudent(student);
            // readStudent(student);
            // queryStudents(student);
            // queryStudentsLastName(student);
            // updateStudent(student);
            // deleteStudent(student);
            // deleteAllStudents(student);
            // createInstructor(app);
            // findInstructor(app);
            // deleteInstructor(app);
            // deleteInstructorDetail(app);
            // createInstructorWithCourses(app);
            // findInstructorWithCourses(app);
            // findCoursesForInstructor(app);
            // findInstructorByJoinFetch(app);
            // updateInstructor(app);
            // updateCourse(app);
            // deleteInstructorOnly(app);
            // deleteCourseOnly(app);
            // createCourseAndReview(app);
            // findCourseAndReviews(app);
            // deleteCourseAndReviews(app);
            // createCourseAndStudents(app);
            // findCourseAndStudents(app);
            // findStudentAndCourses(app);
            // addMoreCoursesForStudents(app);
            // deleteCoureById(app);
            // deleteStudent(app);
        };
    }

    /** Creates and persists a new Student record. */
    public void createStudent(StudentDao studentDao) {
        System.out.println("Creating new student object...");
        Student student = new Student("Kuya", "Joseph", "KJoseph@gmail.com");

        studentDao.save(student);
        System.out.println("Saved student. Generated id: " + student.getId());
    }

    /** Reads a Student record by ID. */
    public void readStudent(StudentDao studentDao) {
        System.out.println("Finding student with id 2 ...");
        Student student = studentDao.findById(2);
        System.out.println("Found the student: " + student);
    }

    /** Queries and logs all Student records. */
    private void queryStudents(StudentDao studentDao) {
        System.out.println("Finding all students...");
        List<Student> students = studentDao.findAll();
        for (Student s : students) {
            System.out.println(s);
        }
    }

    /** Queries students filtered by last name. */
    public void queryStudentsLastName(StudentDao studentDao) {
        System.out.println("Finding students with last name Doe...");
        List<Student> students = studentDao.findByLastName("Doe");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    /** Updates an existing Student entity. */
    public void updateStudent(StudentDao studentDao) {
        int id = 2;
        System.out.println("Student ID: " + id);
        Student student = studentDao.findById(id);
        System.out.println("Updating student...");
        student.setFirstName("Scooby");
        studentDao.update(student);
        System.out.println("Updated student: " + student);
    }

    /** Deletes a Student record by ID via StudentDao. */
    public void deleteStudent(StudentDao studentDao) {
        int id = 2;
        System.out.println("Deleting student with id: " + id);
        studentDao.delete(id);
        System.out.println("Deleted student with id: " + id);
    }

    /** Deletes all Student records. */
    public void deleteAllStudents(StudentDao studentDao) {
        System.out.println("Deleting all students...");
        int rowsDeleted = studentDao.deleteAll();
        System.out.println("Number of rows: " + rowsDeleted);
    }

    /** Creates an Instructor with InstructorDetail (OneToOne). */
    public void createInstructor(AppDao app) {
        Instructor ins = new Instructor("rai", "san", "raisan@gmail.com");
        InstructorDetail insDetail = new InstructorDetail
                                            ("PE", 
                                            "watching movies");

        ins.setInstructorDeets(insDetail);

        System.out.println("Saving instructor: " + ins);
        app.save(ins);
    }

    /** Finds an Instructor by ID. */
    public void findInstructor(AppDao app) {
        int id = 4;

        System.out.println("Finding instructor with id: " + id);
        Instructor ins = app.findInstructorById(id);

        System.out.println("Found instructor: " + ins);
        System.out.println("Associated instructor details: " + ins.getInstructorDeets());
    }

    /** Deletes an Instructor and cascades to details. */
    public void deleteInstructor(AppDao app) {
        int id = 3;

        System.out.println("Delete id : " + id);
        app.deleteInstructorById(id);
        System.out.println("Deleted successfully");
    }

    /** Deletes an InstructorDetail record by ID. */
    public void deleteInstructorDetail(AppDao app) {
        int id = 6;
        System.out.println("Delete id: " + id);

        app.deleteInstructorDetail(id);
        System.out.println("Deleted Successfully!");
    }

    /** Creates an Instructor with associated Courses (OneToMany). */
    public void createInstructorWithCourses(AppDao app) {
        Instructor ins = new Instructor("nhu", "dee", "nhudee@gmail.com");
        InstructorDetail deets = new InstructorDetail("math", "sleeping");

        ins.setInstructorDeets(deets);

        Course course1 = new Course("Physics");
        Course course2 = new Course("Biology");

        ins.add(course1);
        ins.add(course2);

        System.out.println("Courses saved: " + ins.getCourses());
        app.save(ins);
    }

    /** Fetches an Instructor along with their courses. */
    public void findInstructorWithCourses(AppDao app) {
        int id = 5;

        System.out.println("Finding instructor with id: " + id);
        Instructor ins = app.findInstructorById(id);

        System.out.println("Found instructor: " + ins);
        System.out.println("Courses: " + ins.getCourses());
    }

    /** Fetches courses for a given Instructor ID. */
    public void findCoursesForInstructor(AppDao app) {
        int id = 8;

        System.out.println("Find Instructor id: " + id);
        Instructor ins = app.findInstructorById(id);

        System.out.println("Instructor: " + ins);

        System.out.println("Courses from id: " + id);
        List<Course> courses = app.findCoursesByInstructorId(id);

        ins.setCourses(courses);
        System.out.println(ins.getCourses());
    }

    /** Fetches Instructor and Courses using optimized JOIN FETCH query. */
    public void findInstructorByJoinFetch(AppDao app) {
        int id = 8;
        System.out.println("Find instructor id: " + id);

        Instructor ins = app.findInstructorByJoinFetch(id);

        System.out.println("Instructor: " + ins);
        System.out.println("Courses: " + ins.getCourses());
    }

    /** Updates an Instructor's details. */
    public void updateInstructor(AppDao app) {
        int id = 8;

        System.out.println("Find Instructor id: " + id);
        Instructor ins = app.findInstructorById(id);

        ins.setLastName("Warmer");

        System.out.println("Updated!");
        app.update(ins);
    }

    /** Updates a Course title. */
    public void updateCourse(AppDao app) {
        int id = 1;

        System.out.println("Finding course: " + id);

        Course cor = app.findCourseById(id);

        cor.setTitle("ICT");
        System.out.println("Updated!");
        app.update(cor);
    }

    /** Deletes an Instructor without deleting associated courses. */
    public void deleteInstructorOnly(AppDao app) {
        int id = 8;

        System.out.println("Delete Instructor: " + id);

        app.deleteInstructorOnlyById(id);
        System.out.println("Deleted Successfully!");
    }

    /** Deletes a Course by ID. */
    public void deleteCourseOnly(AppDao app) {
        int id = 6;

        System.out.println("Delete Course: " + id);

        app.deleteCourseById(id);
        System.out.println("Deleted Successfully!");
    }

    /** Creates a Course with associated Reviews (OneToMany). */
    private void createCourseAndReview(AppDao app) {
        Course course = new Course("React Native");

        course.addReview(new Review("Learn JS"));
        course.addReview(new Review("Learn DOM"));
        course.addReview(new Review("Learn CSS"));

        app.save(course);
        System.out.println("Course saved!");
    }

    /** Fetches a Course and its Reviews. */
    private void findCourseAndReviews(AppDao app) {
        int id = 6;
        System.out.println("Get course and review from id: " + id);

        Course course = app.findCourseAndReviewsByCourseId(id);

        System.out.println(course);
        System.out.println(course.getReview());
    }

    /** Deletes a Course along with its Reviews. */
    private void deleteCourseAndReviews(AppDao app) {
        int id = 5;

        System.out.println("Delete course: " + id);
        app.deleteCourseById(id);
        System.out.println("Deleted Successfully!");
    }

    /** Creates a Course with enrolled Students (ManyToMany). */
    private void createCourseAndStudents(AppDao app) {
        Course course = new Course("Embedded Systems");

        Student student1 = new Student("quynh", "nhu", "quynhdee@gmail.com");
        Student student2 = new Student("lee", "san", "leesan@gmail.com");

        course.addStudent(student1);
        course.addStudent(student2);
        app.save(course);

        System.out.println("Successfully Enrolled!");
    }

    /** Fetches a Course and its enrolled Students. */
    private void findCourseAndStudents(AppDao app) {
        int id = 8;

        Course course = app.findCourseAndStudentByCourseId(id);

        System.out.println("Course: " + course);
        System.out.println("Students: " + course.getStudents());
    }

    /** Fetches a Student and their enrolled Courses. */
    private void findStudentAndCourses(AppDao app) {
        int id = 22;

        Student student = app.findStudentAndCourseByStudentId(id);

        System.out.println("Student: " + student);
        System.out.println("Courses: " + student.getCourses());
    }

    /** Enrolls a Student into additional Courses. */
    private void addMoreCoursesForStudents(AppDao app) {
        int id = 22;
        Student student = app.findStudentAndCourseByStudentId(id);

        Course course1 = new Course("GE 1");
        Course course2 = new Course("GE 2");
        Course course3 = new Course("GE 3");

        student.addCourse(course1);
        student.addCourse(course2);
        student.addCourse(course3);

        System.out.println("Successfully enrolled to new courses!");

        app.update(student);
    }

    /** Deletes a Course by ID. */
    private void deleteCoureById(AppDao app) {
        int id = 9;
        System.out.println("Delete course: " + id);

        app.deleteCourseById(id);
        System.out.println("Deleted Successfully!");
    }

    /** Deletes a Student record by ID via AppDao. */
    private void deleteStudent(AppDao app) {
        int id = 22;

        System.out.println("Delete student: " + id);
        app.deleteStudentById(id);
        System.out.println("Deleted Successfully!");
    }
}