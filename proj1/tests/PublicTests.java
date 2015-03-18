package tests;

import university.University;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Tests calling numCourses() on a newly-instantiated University with no
  // courses, and after one course has been added using addCourse().
  @Test public void testPublic1() {
    University university= new University();

    assertEquals(0, university.numCourses());

    university.addCourse("CMSC", 132, 10);
    assertEquals(1, university.numCourses());
  }

  // Tests that addCourse() can add different courses to different
  // departments in a University, by calling numCourses() after several
  // courses, in different departments, have been added to a University.
  @Test public void testPublic2() {
    University university= new University();

    university.addCourse("CMSC", 131, 10);
    university.addCourse("MATH", 132, 10);
    university.addCourse("BIOE", 250, 10);
    university.addCourse("ENGL", 101, 10);
    university.addCourse("PSYC", 100, 8);
    university.addCourse("COMM", 184, 7);
    university.addCourse("ENEE", 115, 80);
    university.addCourse("KNES", 217, 30);
    System.out.print(university.numCourses());
    assertEquals(8, university.numCourses());
  }

  // Tests that addCourse() properly returns a reference to its current
  // object, by making chained calls to it.
  @Test public void testPublic3() {
    University university= new University();

    university.addCourse("CMSC", 131, 10).addCourse("MATH", 132, 20);
    university.addCourse("BIOE", 250, 15).addCourse("ENGL", 101, 30);
    university.addCourse("PSYC", 100, 5);

    assertEquals(5, university.numCourses());
  }

  // Tests that trying to call addCourse() to add an already-existing course
  // does not have an effect (does not change the number of courses that are
  // in the University).
  @Test public void testPublic4() {
    University university= new University();

    university.addCourse("CMSC", 132, 10);
    university.addCourse("CMSC", 132, 11);
    assertEquals(1, university.numCourses());
  }

  // Tests that cancelCourse() removes a course from a University (using
  // numCourses()), and returns true.
  @Test public void testPublic5() {
    University university= new University();

    university.addCourse("CMSC", 132, 10);
    university.addCourse("MATH", 140, 20);
    assertEquals(2, university.numCourses());

    assertTrue(university.cancelCourse("MATH", 140));
    assertEquals(1, university.numCourses());
  }

  // Tests calling add() to add a student to a course, checks that it
  // returns true, and verifies that the student is in the course using
  // numStudentsInCourse().
  @Test public void testPublic6() {
    University university= new University();

    university.addCourse("CMSC", 132, 10);
    university.addCourse("MATH", 141, 10);
    university.addCourse("PSYC", 100, 10);

    assertTrue(university.add("CMSC", 132, "Sally"));
    assertEquals(1, university.numStudentsInCourse("CMSC", 132));
  }

  // Tests that add() returns true when a student is successfully added to a
  // course, but false when trying to add a student more than once to the
  // same course.
  @Test public void testPublic7() {
    University university= new University();

    university.addCourse("CMSC", 132, 10);
    university.addCourse("MATH", 141, 10);
    university.addCourse("PSYC", 100, 10);

    assertTrue(university.add("PSYC", 100, "Omar"));
    assertFalse(university.add("PSYC", 100, "Omar"));
    assertEquals(1, university.numStudentsInCourse("PSYC", 100));
  }

  // Tests calling add() to try to add a student to a nonexistent course (it
  // should return false in this case).
  @Test public void testPublic8() {

    University university= new University();

    university.addCourse("CMSC", 132, 10);
    university.addCourse("MATH", 141, 10);
    university.addCourse("PSYC", 100, 10);

    assertFalse(university.add("SOCY", 200, "Ravi"));
  }

  // Tests calling add() to try to add a student to too many courses; it
  // should return false, and not add the student to more than five courses.
  @Test public void testPublic9() {
    University university= new University();

    university.addCourse("CMSC", 132, 10);
    university.addCourse("MATH", 141, 10);
    university.addCourse("PSYC", 100, 10);
    university.addCourse("HIST", 101, 10);
    university.addCourse("ECON", 105, 10);
    university.addCourse("SOCY", 102, 10);

    university.add("CMSC", 132, "Hua");
    university.add("MATH", 141, "Hua");
    university.add("PSYC", 100, "Hua");
    university.add("HIST", 101, "Hua");
    university.add("ECON", 105, "Hua");

    assertFalse(university.add("SOCY", 102, "Hua"));

    // Hua did not get added to SOCY 102
    assertEquals(0, university.numStudentsInCourse("SOCY", 102));
  }

  // Tests calling add() to try to add more students to a course than its
  // capacity; it should return false, and not add students beyond the
  // capacity.
  @Test public void testPublic10() {
    University university= new University();

    university.addCourse("KNES", 321, 3);

    university.add("KNES", 321, "Laura");
    university.add("KNES", 321, "Paul");
    university.add("KNES", 321, "Chana");   // now at capacity
    university.add("KNES", 321, "Miguel");  // would be over capacity

    assertEquals(3, university.numStudentsInCourse("KNES", 321));
  }

  // Tests that addCourse() will create courses with negative and zero seat
  // counts, and that no students can be added to the courses later.
  @Test public void testPublic11() {
    University university= new University();

    university.addCourse("BIOE", 222, 0);
    university.addCourse("PHIL", 333, -4);

    assertFalse(university.add("BIOE", 222, "Hailey"));
    assertFalse(university.add("PHIL", 333, "Keisha"));

    assertEquals(0, university.numStudentsInCourse("BIOE", 222));
    assertEquals(0, university.numStudentsInCourse("PHIL", 333));
  }

  // Tests calling numStudentsInCourse() on different courses.
  @Test public void testPublic12() {
    University university= new University();

    university.addCourse("GEOG", 174, 2);
    university.addCourse("JAPN", 276, 4);
    university.addCourse("GEMS", 435, 5);
    university.addCourse("ARTT", 354, 3);

    university.add("JAPN", 276, "Zachary");

    university.add("GEMS", 435, "Brianna");
    university.add("GEMS", 435, "Amanda");

    university.add("ARTT", 354, "Trevor");
    university.add("ARTT", 354, "Kelly");
    university.add("ARTT", 354, "Miguel");

    assertEquals(0, university.numStudentsInCourse("GEOG", 174));
    assertEquals(1, university.numStudentsInCourse("JAPN", 276));
    assertEquals(2, university.numStudentsInCourse("GEMS", 435));
    assertEquals(3, university.numStudentsInCourse("ARTT", 354));
  }

  // Tests isRegisteredForCourse() in a few cases where it should return
  // true.
  @Test public void testPublic13() {
    University university= new University();

    university.addCourse("GEOG", 174, 2);
    university.addCourse("JAPN", 276, 4);
    university.addCourse("GEMS", 435, 5);

    university.add("GEOG", 174, "Lucas");

    university.add("GEOG", 174, "Whitney");
    university.add("JAPN", 276, "Whitney");
    university.add("GEMS", 435, "Whitney");

    university.add("GEMS", 435, "Devon");
    university.add("GEMS", 435, "Chloe");
    university.add("GEMS", 435, "Skyler");

    assertTrue(university.isRegisteredForCourse("GEOG", 174, "Lucas"));
    assertTrue(university.isRegisteredForCourse("GEOG", 174, "Whitney"));
    assertTrue(university.isRegisteredForCourse("JAPN", 276, "Whitney"));
    assertTrue(university.isRegisteredForCourse("GEMS", 435, "Whitney"));
    assertTrue(university.isRegisteredForCourse("GEMS", 435, "Devon"));
    assertTrue(university.isRegisteredForCourse("GEMS", 435, "Skyler"));
    assertTrue(university.isRegisteredForCourse("GEMS", 435, "Chloe"));
  }

  // Tests numCoursesRegisteredFor() in a few cases.
  @Test public void testPublic14() {
    University university= new University();

    university.addCourse("GEOG", 174, 2);
    university.addCourse("JAPN", 276, 4);
    university.addCourse("GEMS", 435, 5);

    university.add("GEOG", 174, "Whitney");
    university.add("JAPN", 276, "Whitney");
    university.add("GEMS", 435, "Whitney");
    university.add("GEMS", 435, "Devon");
    university.add("JAPN", 276, "Devon");
    university.add("GEMS", 435, "Chloe");

    assertEquals(1, university.numCoursesRegisteredFor("Chloe"));
    assertEquals(2, university.numCoursesRegisteredFor("Devon"));
    assertEquals(3, university.numCoursesRegisteredFor("Whitney"));
  }

  // Tests calling drop() on a student in a course, verifies that it returns
  // true, and ensures that the number of students in the course decreases
  // by 1.
  @Test public void testPublic15() {
    University university= new University();

    university.addCourse("CMSC", 132, 10);
    university.addCourse("MATH", 141, 10);
    university.addCourse("PSYC", 100, 10);
    university.addCourse("HIST", 101, 10);
    university.addCourse("ECON", 105, 10);

    university.add("CMSC", 132, "Maya");
    university.add("MATH", 141, "Maya");
    university.add("PSYC", 100, "Maya");
    university.add("HIST", 101, "Maya");
    university.add("ECON", 105, "Maya");
    university.add("CMSC", 132, "Liam");
    university.add("MATH", 141, "Liam");
    university.add("PSYC", 100, "Liam");

    university.drop("MATH", 141, "Maya");

    assertEquals(1, university.numStudentsInCourse("MATH", 141));
  }

  // Tests calling drop() on a student who is registered for five courses,
  // and checks that they are able to be added to a new course after that.
  @Test public void testPublic16() {
    University university= new University();

    university.addCourse("CMSC", 132, 10);
    university.addCourse("MATH", 141, 10);
    university.addCourse("PSYC", 100, 10);
    university.addCourse("HIST", 101, 10);
    university.addCourse("ECON", 105, 10);
    university.addCourse("SOCY", 102, 10);

    university.add("CMSC", 132, "Teresa");
    university.add("MATH", 141, "Teresa");
    university.add("PSYC", 100, "Teresa");
    university.add("HIST", 101, "Teresa");
    university.add("ECON", 105, "Teresa");

    university.drop("HIST", 101, "Teresa");

    assertTrue(university.add("SOCY", 102, "Teresa"));
    assertEquals(1, university.numStudentsInCourse("SOCY", 102));
  }

  // Tests that drop() removes a student from their only course, and that
  // they are subsequently not registered for any courses, using
  // numCoursesRegisteredFor().
  @Test public void testPublic17() {
    University university= new University();

    university.addCourse("MATH", 141, 10);
    university.addCourse("PSYC", 100, 10);

    university.add("MATH", 141, "Andy");
    university.add("PSYC", 100, "Nolan");

    university.drop("MATH", 141, "Andy");

    assertEquals(0, university.numCoursesRegisteredFor("Andy"));
  }

  // Tests cancelRegistration() by verifying that the number of students in
  // all of the student's courses is reduced by 1 after it's called.
  @Test public void testPublic18() {
    University university= new University();

    university.addCourse("CMSC", 132, 10);
    university.addCourse("MATH", 141, 10);
    university.addCourse("PSYC", 100, 10);
    university.addCourse("HIST", 101, 10);
    university.addCourse("ECON", 105, 10);
    university.addCourse("SOCY", 102, 10);

    university.add("CMSC", 132, "Nina");
    university.add("MATH", 141, "Nina");
    university.add("PSYC", 100, "Nina");
    university.add("HIST", 101, "Nina");
    university.add("ECON", 105, "Nina");

    assertTrue(university.cancelRegistration("Nina"));

    assertEquals(0, university.numStudentsInCourse("CMSC", 132));
    assertEquals(0, university.numStudentsInCourse("MATH", 141));
    assertEquals(0, university.numStudentsInCourse("PSYC", 100));
    assertEquals(0, university.numStudentsInCourse("HIST", 101));
    assertEquals(0, university.numStudentsInCourse("ECON", 105));
    assertEquals(0, university.numStudentsInCourse("SOCY", 102));
  }

  // Tests calling cancelRegistration() twice on a student-- the first call
  // should remove them, while the second call should not have any effect.
  @Test public void testPublic19() {
    University university= new University();

    university.addCourse("CMSC", 132, 10);
    university.addCourse("MATH", 141, 10);
    university.addCourse("PSYC", 100, 10);
    university.addCourse("HIST", 101, 10);
    university.addCourse("ECON", 105, 10);

    university.add("CMSC", 132, "Nina");
    university.add("MATH", 141, "Nina");
    university.add("PSYC", 100, "Nina");
    university.add("HIST", 101, "Nina");
    university.add("ECON", 105, "Nina");
    university.add("MATH", 141, "Renee");
    university.add("PSYC", 100, "Renee");
    university.add("HIST", 101, "Renee");

    university.cancelRegistration("Nina");
    university.cancelRegistration("Nina");

    assertEquals(0, university.numStudentsInCourse("CMSC", 132));
    assertEquals(1, university.numStudentsInCourse("MATH", 141));
    assertEquals(1, university.numStudentsInCourse("PSYC", 100));
    assertEquals(1, university.numStudentsInCourse("HIST", 101));
    assertEquals(0, university.numStudentsInCourse("ECON", 105));
  }

  // Tests that some methods are properly enforcing case sensitivity of
  // student names.
  @Test public void testPublic20() {
    University university= new University();

    university.addCourse("HACS", 207, 20);
    university.addCourse("CMSC", 132, 10);
    university.addCourse("MATH", 141, 10);
    university.addCourse("PSYC", 100, 10);
    university.addCourse("HIST", 101, 10);
    university.addCourse("ECON", 105, 10);

    university.add("HACS", 207, "Rosa");

    university.add("CMSC", 132, "Conor");
    university.add("MATH", 141, "Conor");
    university.add("PSYC", 100, "Conor");
    university.add("HIST", 101, "Conor");
    university.add("ECON", 105, "Conor");

    // rosa is not the same student as Rosa
    assertFalse(university.isRegisteredForCourse("HACS", 207, "rosa"));

    // CONOR is not the same student as Conor
    assertTrue(university.add("HACS", 207, "CONOR"));
  }

}
