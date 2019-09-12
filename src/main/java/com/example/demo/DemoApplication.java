package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Collection;

import static org.springframework.boot.SpringApplication.*;

@Configuration
@ComponentScan

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		run(DemoApplication.class, args);
	}

}

@Component
class StudentCommandLineRunner implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {

		for(Student s : this.studentRepository.findAll())
			System.out.println(s.toString());

	}
	@Autowired
	StudentRepository studentRepository;

}

interface StudentRepository extends JpaRepository<Student,Long> {
	Collection<Student> findStudentName(String studentName);

}

@RestController
class StudentRestController{

	@RequestMapping("/bookings")
	Collection<Student> students(){
		return this.studentRepository.findAll();
	}

	@Autowired
	StudentRepository studentRepository;

}

@Entity
class Student{

	@Id @GeneratedValue
	private Long id;
	private String studentname;
	private  int note;


	public Student(){

	}
	public Student(String studentname, int note){
		this.studentname=studentname;
		this.note=note;

	}

	public Long getId(){
		return id;
	}

	public String getStudentName(){
		return studentname;
	}

	public int getNote(){
		return note;
	}

	public String toString(){
		return "Student[id="+id+" Student name= "+studentname+" Student note "+note+"]";
	}

}

