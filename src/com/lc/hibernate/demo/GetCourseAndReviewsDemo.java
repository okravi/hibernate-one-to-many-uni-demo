package com.lc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lc.hibernate.demo.entity.Course;
import com.lc.hibernate.demo.entity.Instructor;
import com.lc.hibernate.demo.entity.InstructorDetail;
import com.lc.hibernate.demo.entity.Review;

public class GetCourseAndReviewsDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {		
			
			
			//start a transaction
			session.beginTransaction();
			
			// get the course
			int theId = 10;
			Course theCourse =session.get(Course.class, theId);
			
			//print the course
			System.out.println(theCourse);
			
			//print reviews
			System.out.println(theCourse.getReviews());
			
			//commit transaction
			session.getTransaction().commit();;
			System.out.println("Done");
			
		} finally {
			session.close();
			factory.close();
		}
	}

}
