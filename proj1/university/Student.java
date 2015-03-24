package university;
/**
 * 
 * @author Jacob Knapo 
 * 		   CMSC 132 Herman 
 * 	       2/13/2015 
 *         Proj 1
 * This is represents one student. Each student keeps tract of its name and how many classes they are taking
 *
 */
public class Student {
	private int numclasses; // number of classes a student is taking (Can only
							// reg upto five classses)
	private String name; // Student name

	public Student(String name, int numclasses) {
		this.name = name;
		this.numclasses = numclasses;
	}

	/**
	 * Getter for student name
	 * 
	 * @return studentName
	 */
	public String getName() {
		return new String(name);
	}

	/**
	 * Getter for number of classes a student is taking
	 * 
	 * @return numclasses
	 */
	public int getNumClasses() {
		return numclasses;
	}

	/**
	 * Update how many classes this student is taking by adding one
	 */
	public void add() {
		this.numclasses++;
	}

	/**
	 * Update how many classes this student is taking by removing one
	 */
	public void drop() {
		this.numclasses--;
	}

	/**
	 * Equals method that checks if name is equal
	 */
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj == this) {
			return true;
		} else {
			if (!(obj instanceof Student)) {
				return false;
			} else {
				Student temp = (Student) obj;
				return this.name.equals(temp.getName());
			}
		}
	}
}
