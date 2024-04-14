import java.util.ArrayList;

// Name: Muteeb Syed
// Student Number: 500972883

// class Student implement the Comparable interface
public class Student implements Comparable<Student>
{
  private String name;
  private String id;
  public  ArrayList<CreditCourse> courses;
  
  // constructor method that sets the instance variables for Student
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses   = new ArrayList<CreditCourse>();
  }
  

  // method created that gets the student names from TreeMap students and puts it in ArrayList
  //public ArrayList<CreditCourse> getOnlyCourses()
  {
     //ArrayList<CreditCourse> c = new ArrayList<>();
     //Set<String> code = courses.keySet();
     //for (String course : code)
     {
        //c.add(courses.get(code));
     }
     //return c;
  }
  
  

  /** 
   * @return String
   */
  // returns the id of the student
  public String getId()
  {
	  return id;
  }
  
  /** 
   * @return String
   */
  // returns the name of the student
  public String getName()
  {
	  return name;
  }

  /** 
   * @return int
   */
  // method returs the number of courses in courses arraylist
  public int getCoursesSize()
    {
      int count = 0;
      for (int i = 0; i < courses.size(); i++)
      {
        count += 1;
      }
      return count;
    }

  /** 
   * @param c
   * @return String
   */
  // method takes an int parameter as an index and returns the courseCode of the course at that index
  public String getCourseCode(int c)
  {
    return courses.get(c).getCode();
  }
  
  /** 
   * @param courseName
   * @param courseCode
   * @param descr
   * @param format
   * @param sem
   * @param grade
   */
  // add a credit course to list of courses for this student
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
    // create a CreditCourse object
	  CreditCourse cc = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
	  // set course active
	  cc.setActive();
	  // add to courses array list
	  courses.add(cc);
  }
  
  // Prints a student transcript
  public void printTranscript()
  {
    // for loop goes through courses arraylist and prints all completed courses with other information
    for (int i = 0; i < courses.size(); i++)
    {
      System.out.println(courses.get(i).displayGrade());
    }
  }
  
  // Prints all active courses this student is enrolled in
    public void printActiveCourses()
  {
    // for loop that checks if course is active and prints all active courses
    for (int i = 0; i < courses.size(); i++)
    {
      if (courses.get(i).getActive())
      {
        System.out.println(courses.get(i).getDescription());
      }
    }
  }
  
  /** 
   * @param courseCode
   */
  // method drops a course by using its courseCode
  public void removeActiveCourse(String courseCode)
  {
    // Find the credit course in courses arraylist above and remove it
    for (int i = 0; i < courses.size(); i++)
    {
      if (courses.get(i).getCode().equalsIgnoreCase(courseCode))
			{
        // if course is active, removes the course
        if (courses.get(i).getActive())
        {
          courses.remove(i);
        }	
			}
		}
  }

  /** 
   * @param courseCode
   * @return double
   */
  // Returns the numeric grade in this course for this student
  public double getGrade(String courseCode)
  {
    // for loop searches for the course that matches the gien courseCode
    for (int i = 0; i < courses.size(); i++)
    {
      CreditCourse cc = courses.get(i);
      if (courseCode.equals(cc.getCode()))
      {
        // return the grade stored in the credit course object
        return cc.getGrade();
      }  
    }
    // If student not found in course, return 0 
    return 0;	   
   }

  /** 
   * @param courseCode
   * @return boolean
   */
  // method created to return a boolean to check if the course is already taken
  public boolean courseTaken (String courseCode)
  {
    // for loop to find the courses
    for (int i = 0; i < courses.size(); i++)
    {
      CreditCourse cc = courses.get(i);
      // if course is found in credit courses, return true
      if (cc.getCode().equalsIgnoreCase(courseCode))
      {
        return true;
      }
    }
    return false;
  }
  
  /** 
   * @return String
   */
  // toString() method returns id and name
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  
  /** 
   * @param other
   * @return boolean
   */
  // override equals method inherited from superclass Object
  public boolean equals(Object other)
  {
    Student s = (Student) other;
    // if student names are equal *and* student ids are equal then return true
    if (this.getName().equals(s.getName()) && this.getId().equals(s.getId()))
    {
      return true;
    }
    // otherwise return false
    return false;
  }
  
  /** 
   * @param other
   * @return int
   */
  // compareTo() method used to 
  public int compareTo(Student other) 
  {
    return this.getName().compareTo(other.getName());
  }
}
