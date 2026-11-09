package com.spring.hibernate_demo;

import com.spring.hibernate_demo.dao.StudentDaoImplement;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_demo.dao.AppDao;
import com.spring.hibernate_demo.dao.AppDaoImplement;
import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;

@SpringBootApplication
public class HibernateDemoApplication {

	private final StudentDaoImplement studentDaoImplement;

    HibernateDemoApplication(StudentDaoImplement studentDaoImplement, AppDaoImplement appDaoImplement) {
        this.studentDaoImplement = studentDaoImplement;
    }

    public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}
	
	@Bean 
	CommandLineRunner commandLineRunner(StudentDao studentDao, AppDao app) {
		return runner -> {
			// createStudent(studentDao);
			// readStudent(studentDao);
			// queryStudents(studentDao);
			// queryStudentsLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudents(studentDao);
			// createInstructor(app);
			// findInstructor(app);
			// deleteInstructor(app);
			// deleteInstructorDetail(app);
			// createInstructorWithCourses(app);
			// findInstructorWithCourses(app);
			// findCoursesForInstructor(app);
			// findInstructorByJoinFetch(app);
			// updateInstructor(app);
		};
	}

	public void createStudent(StudentDao studentDao) {
		System.out.println("Creating new student object...");
		Student student = new Student("Kuya",
					 					"Joseph", 
					 						"KuyaJoseph@gmail.com");
		//save the student object
		studentDao.save(student);
		//display id of the saved student
		System.out.println("Saved student. Generated id: " + student.getId());
	}

	public void readStudent(StudentDao studentDao) {
		System.out.println("Finding student with id 2 ...");
		Student student = studentDao.findById(2);
		System.out.println("Found the student: " + student);
	}

	private void queryStudents(StudentDao studentDao) {
		System.out.println("Finding all students...");
		List<Student> students = studentDao.findAll();
		for(Student s : students) {
			System.out.println(s);
		}
	}

	public void queryStudentsLastName(StudentDao studentDao) {
		System.out.println("Finding students with last name Doe...");
		List<Student> students = studentDao.findByLastName("Doe");
		for(Student s : students) {
			System.out.println(s);
		}
	}

	public void updateStudent(StudentDao studentDao) {
		int id =2;
		System.out.println("Student ID: " + id);
		Student student = studentDao.findById(id);
		System.out.println("Updating student...");
		student.setFirstName("Scooby");
		studentDao.update(student);
		System.out.println("Updated student: " + student);
	}

	public void deleteStudent(StudentDao studentDao) {
		int id = 2;
		System.out.println("Deleting student with id: " + id);
		studentDao.delete(id);
		System.out.println("Deleted student with id: " + id);
	}

	public void deleteAllStudents(StudentDao studentDao) {
		System.out.println("Deleting all students...");
		int rowsDeleted = studentDao.deleteAll();
		System.out.println("Number of rows: " + rowsDeleted);
	}

	public void createInstructor(AppDao app){
		Instructor ins = new Instructor("rai", "san", "raisan@gmail.com");

		InstructorDetail insDetail = new InstructorDetail("PE", "watching movies");

		ins.setInstructorDeets(insDetail);

		System.out.println("Saving instructor: " + ins);
		app.save(ins);
	}

	public void findInstructor(AppDao app) {
		int id = 4;

		System.out.println("Finding instructor with id: " + id);
		Instructor ins = app.findInstructorById(id);
		
		System.out.println("Found instructor: " + ins);
		System.out.println("Associated instructor details: " + ins.getInstructorDeets());
	}

	public void deleteInstructor(AppDao app) {
		int id = 3;

		System.out.println("Delete id	: " + id);
		app.deleteInstructorById(id);
		System.out.println("Deleted succesfully");
	}

	public void deleteInstructorDetail(AppDao app){
		int id = 6;
		System.out.println("Delete id:"+ id);

		app.deleteInstructorDetail(id);
		System.out.println("Deleted Successfully!");
	}

	public void createInstructorWithCourses(AppDao app){
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

	public void findInstructorWithCourses(AppDao app){
		int id = 5;

		System.out.println("Finding instructor with id: " + id);
		Instructor ins = app.findInstructorById(id);
		
		System.out.println("Found instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());

	}

	public void findCoursesForInstructor(AppDao app){
		int id = 8;

		System.out.println("Find Instructor id: " + id);
		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);

		System.out.println("Courses from id: " + id);
		List<Course> courses = app.findCoursesByInstructorId(id);

		ins.setCourses(courses);

		System.out.println(ins.getCourses());
	}

	public void findInstructorByJoinFetch(AppDao app){
		int id = 8;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorByJoinFetch(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());

	}

	public void updateInstructor(AppDao app){
		int id = 8;

		System.out.println("Find Instructor id: " + id);
		Instructor ins = app.findInstructorById(id);
		//update Lastname
		ins.setLastName("Warmer");
		
		System.out.println("Updated!");
		app.update(ins);
	}


}
