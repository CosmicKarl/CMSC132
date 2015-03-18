package university;

import java.util.ArrayList;
/**
 * 
 * @author Jacob knapo
 * This class is the structure for one course. One course keeps tracts of each student that is in that class
 *
 */
public class UniClass {
	// This class represents a single University class
	private String department; // Department name
	private int cousrenumber; // Cousre number
	private int seatnumber; // Number of seats for a class
	private ArrayList<Student> students; // List of student in course

	public UniClass(String dep, int num, int seatnum) {
		department = dep;
		cousrenumber = num;
		seatnumber = seatnum;
		students = new ArrayList<Student>();
	}

	/**
	 * Getter for department name
	 * 
	 * @return department name
	 */
	public String getDepName() {
		return new String(department);
	}

	/**
	 * Getter for cousrse number
	 * 
	 * @return course number
	 */
	public int getCourseNum() {
		return cousrenumber;
	}

	/**
	 * Getter for seatnumber
	 * 
	 * @return seatnumber
	 */
	public int getSeatNumber() {
		return seatnumber;
	}

	public int sizeOfClass() {
		return students.size();
	}

	/**
	 * Adds one student to a class
	 * 
	 * @param stu
	 */
	public void addStudent(Student stu) {
		students.add(stu);
	}

	/**
	 * removes a student from class
	 * 
	 * @param stu
	 */
	public void removeStudent(Student stu) {
		students.remove(students.indexOf(stu));
	}

	/**
	 * 
	 * @param stu
	 * @return true if has student false otherwise
	 */
	public boolean hasStudent(Student stu) {
		return students.indexOf(stu) > -1;
	}

	/**
	 * updates all the students in a class if a class is canceled
	 */
	public void removeAll(){
		for(Student i: students){
			i.drop();
		}	
		this.students.removeAll(students);
	}

	/**
	 * Basic equals method for UniClass by comparing the course deoartment and
	 * course number not seat num;
	 */
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj == this) {
			return true;
		} else {
			if (!(obj instanceof UniClass)) {
				return false;
			} else {
				UniClass temp = (UniClass) obj;
				return this.department.equals(temp.getDepName())
						&& this.cousrenumber == temp.getCourseNum();
			}
		}
	}
}
