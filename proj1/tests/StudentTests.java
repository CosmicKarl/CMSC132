package tests;

import org.junit.*;

import university.Student;
import university.UniClass;
import university.University;
import static org.junit.Assert.*;

public class StudentTests {

  // write your student tests in this class
	
	//Tests if class has a student and doesnt have a student
	@Test public void testHasStudents(){
		UniClass u1 = new UniClass("C", 1, 2);
		u1.addStudent(new Student("mark",0));
		assertTrue(u1.hasStudent(new Student("mark",0)));
		assertFalse(u1.hasStudent(new Student("jen", 0)));
			
	}
	//test equal method of uniclass
	//Show that only uniclass name matters and course number not seating
	//also that its case sensitive
	@Test public void testUniClassEquals(){
		UniClass u1 = new UniClass("C", 1, 2);
		UniClass u2 = new UniClass("c", 1, 2);
		UniClass u3 = new UniClass("C", 1, 2);
		UniClass u4 = new UniClass("C", 2, 2);
		UniClass u5 = new UniClass("C", 1, 6);
		
		assertTrue(u1.equals(u3)); 	//same class
		assertTrue(u1.equals(u5));	//diffrent seat numbers
		assertFalse(u1.equals(u2));	// diffrent class because of case
		assertFalse(u1.equals(u4));	//difrent course number
	}
	
	//Should remove all the students from a course then 
	//update each student that has been dropped
	@Test public void testUniClRemoveAll(){
		UniClass u1 = new UniClass("C", 1, 2);
		Student mark = new Student("mark", 0);
		Student coolaidrea = new Student("coolaidrea", 0);
		Student obamaniqua = new Student("obamaniqua", 0);
		
		u1.addStudent(mark);
		u1.addStudent(coolaidrea);
		u1.addStudent(obamaniqua);
		
		u1.removeAll();
		
		assertTrue(mark.getNumClasses()==-1);
		assertTrue(coolaidrea.getNumClasses()==-1);
		assertTrue(obamaniqua.getNumClasses()==-1);
		
		assertFalse(u1.hasStudent(mark));
		assertFalse(u1.hasStudent(obamaniqua));
		
		assertTrue(u1.sizeOfClass()==0);		
	}
	
	
	//Checking when registration is canceled all the students in that course
	//are correctly updated
	@Test public void testCancelReg(){
		University u1 = new University();
		
		u1.addCourse("C", 1, 5);
		u1.addCourse("H", 1, 2);
		u1.addCourse("H", 2, 2);
		u1.addCourse("H", 3, 2);
		u1.addCourse("H", 4, 2);
		u1.addCourse("H", 5, 2);
		
		u1.add("C", 1, "coolaidrea");
		u1.add("H", 1, "coolaidrea");
		u1.add("H", 2, "coolaidrea");
		u1.add("H", 3, "coolaidrea");
		u1.add("H", 4, "coolaidrea");
		
		u1.add("C",1, "mark");
		u1.cancelCourse("C", 1);
		
		assertTrue(u1.add("H", 5, "coolaidrea"));	
		
		
	}
	
	

}
