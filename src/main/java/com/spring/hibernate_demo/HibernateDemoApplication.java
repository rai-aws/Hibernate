package com.spring.hibernate_demo;

import com.spring.hibernate_demo.dao.StudentDaoImplement;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Student;

@SpringBootApplication
public class HibernateDemoApplication {

	private final StudentDaoImplement studentDaoImplement;

    HibernateDemoApplication(StudentDaoImplement studentDaoImplement) {
        this.studentDaoImplement = studentDaoImplement;
    }

    public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}
	
	@Bean 
	CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			// createStudent(studentDao);
			// readStudent(studentDao);
			// queryStudents(studentDao);
			// queryStudentsLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudents(studentDao);
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

}
