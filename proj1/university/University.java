package university;

import java.util.ArrayList;

public class University {

	private ArrayList<UniClass> classes; // classes the uni has to offer;
	private ArrayList<Student> students; // students in uni

	public University() {
		classes = new ArrayList<UniClass>();
		students = new ArrayList<Student>();
	}

	/**
	 * 
	 * @param department
	 * @param number
	 * @param numSeats
	 * @returns a course 
	 * If course passed does not exist yet then add is to the classes arraylist.
	 * else do nuthing
	 */
	public University addCourse(String department, int number, int numSeats) {
		// cheacks if has class
		if (this.classes.indexOf(new UniClass(department, number, numSeats)) == -1) {
			classes.add(new UniClass(department, number, numSeats));
			return this;
		} else {
			return this;
		}
	}

	/**
	 * This function cancels a course by first check if the class passed exists.
	 * Then updates all the students in the class as if they dropped a class.
	 * Then removes all the students belonging to that class
	 * 
	 * @param department
	 * @param number
	 * @return boolean
	 */
	public boolean cancelCourse(String department, int number) {
		UniClass chk = new UniClass(department, number, 0); // uniclass to check
															// if it already
															// exists

		// checks if has class
		if (this.classes.indexOf(chk) > -1) {
			classes.get(classes.indexOf(chk)).removeAll();
			classes.remove(classes.indexOf(chk));
			return true;
		} else {
			return false;
		}
	}

	public int numCourses() {
		return classes.size();
	}
	
	/**
	 * 
	 * @param department
	 * @param number
	 * @param name
	 * @return true if succecfull
	 * Adds a student to a class
	 */
	public boolean add(String department, int number, String name) {
		Student tempstu = new Student(name, 0); // This is a student
												// representing passed name
		UniClass chk = new UniClass(department, number, 0);

		// First check if that class exist then check that student is not in
		// that class already
		// last check that this class has room for one more student
		if (classes.indexOf(chk) > -1
				&& !(classes.get(classes.indexOf(chk)).hasStudent(tempstu))
				&& classes.get(classes.indexOf(chk)).getSeatNumber() != classes
						.get(classes.indexOf(chk)).sizeOfClass()
				&& classes.get(classes.indexOf(chk)).getSeatNumber() > 0) {
			// If student is not part of university then add them
			if (students.indexOf(tempstu) == -1) {
				students.add(tempstu);
			}

			// Check if student has room to add another course
			if (students.get(students.indexOf(tempstu)).getNumClasses() != 5) {
				classes.get(classes.indexOf(chk)).addStudent(tempstu);
				students.get(students.indexOf(tempstu)).add();
				return true;
			}
		}
		return false;
	}

	public int numStudentsInCourse(String department, int number) {
		UniClass chk = new UniClass(department, number, 0);
		if (classes.indexOf(chk) > -1) {
			return classes.get(classes.indexOf(chk)).sizeOfClass();
		} else {
			return -1;
		}
	}

	public boolean isRegisteredForCourse(String department, int number,
			String name) {
		Student tempstu = new Student(name, 0); // This is a student
		// representing passed name
		UniClass chk = new UniClass(department, number, 0);
		return classes.get(classes.indexOf(chk)).hasStudent(tempstu);

	}

	/**
	 * 
	 * @param name
	 * @return 0 if student is not registered in system or how many classes a
	 *         student is taking
	 */
	public int numCoursesRegisteredFor(String name) {
		Student tempstu = new Student(name, 0);
		// If student is registered return how many classes that student is
		// taking
		if (students.indexOf(tempstu) > -1)
			return students.get(students.indexOf(tempstu)).getNumClasses();
		return 0;
	}

	/**
	 * 
	 * @param department
	 * @param number
	 * @param name
	 * @return true if student was able to drop false otherwise
	 */
	public boolean drop(String department, int number, String name) {
		Student tempstu = new Student(name, 0); // This is a student
		// representing passed name
		UniClass chk = new UniClass(department, number, 0);

		// If this course exist and has this student then...
		if (classes.indexOf(chk) > -1
				&& (classes.get(classes.indexOf(chk)).hasStudent(tempstu))) {
			
			//remove student from class
			classes.get(classes.indexOf(chk)).removeStudent(tempstu);
			//update that student has dropped a class
			students.get(students.indexOf(tempstu)).drop();
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param name
	 * @return true if student exist false otherwise
	 */
	public boolean cancelRegistration(String name) {
		Student tempstu = new Student(name, 0);
		//if student doesnt exist return fasle
		if (students.indexOf(tempstu) == -1)
			return false;
		
		//for each class drop this student and updte that they dropped that class
		for (UniClass cl : classes) {
			if (cl.hasStudent(tempstu)) {
				classes.get(classes.indexOf(cl)).removeStudent(tempstu);
				students.get(students.indexOf(tempstu)).drop();
			}
		}
		return true;

	}

}
