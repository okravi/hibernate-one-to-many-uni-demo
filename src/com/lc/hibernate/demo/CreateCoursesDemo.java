package com.lc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lc.hibernate.demo.entity.Course;
import com.lc.hibernate.demo.entity.Instructor;
import com.lc.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {		
			
			
			//start a transaction
			session.beginTransaction();
			
			//get an instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			//create some courses
			Course tempCourse = new Course("Ultimate Guitar course");
			Course tempCourse1 = new Course("Pinball Masterclass");
			
			//add courses to instructor
			tempInstructor.add(tempCourse);
			tempInstructor.add(tempCourse1);
			
			//save courses
			session.save(tempCourse);
			session.save(tempCourse1);

			//commit transaction
			session.getTransaction().commit();;
			System.out.println("Done");
		} finally {
			session.close();
			factory.close();
		}
	}

}
