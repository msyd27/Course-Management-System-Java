import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


// Name: Muteeb Syed
// Student Number: 500972883

// Active University Course
 
public class ActiveCourse extends Course
{
	private ArrayList<Student> students; 
   private String             semester;
   private int lectureStart3hr;
   private int lectureStart2hr;
   private int lectureStart1hr;
   private int lectureDuration3hr;
   private int lectureDuration2hr;
   private int lectureDuration1hr;
   private String lectureDay3hr = "";
   private String lectureDay2hr = "";
   private String lectureDay1hr = "";

   // constructor method that sets the instance variable and calls 
   // super class constructor to initialize inherited variables
   public ActiveCourse(String name, String code, String descr, String fmt, String semester,ArrayList<Student> students) 
   {
      super(name, code, descr, fmt);
      this.students = new ArrayList<Student>(students);
      this.semester = semester;
      
   }
   
   /** 
    * @return String
    */
   // method that returns the semester
   public String getSemester()
   {
	   return semester;
   }

   /** 
    * @return TreeMap<String, Student>
    */
   // Returns the TreeMap students.
   public ArrayList<Student> getStudents()
   {
      return students;
   }

   /** 
    * @return int
    */
   // Returns an int that gives the number of students
   public int getStudentsSize()
   {
      int count = 0;
      for (int i = 0; i < students.size(); i++)
      {
        count += 1;
      }
      return count;
    }
   
   // Prints the students in this course (name and student id)
   public void printClassList()
   {	 
      for (int i = 0; i < students.size(); i++)
		{
			System.out.println("Student ID: " + students.get(i).getId() + " Name: " + students.get(i).getName());
      } 
   }
   
   // Prints the grade of each student in this course along with name and student id
   public void printGrades()
   {
      for (int i = 0; i < students.size(); i++)
      {
         System.out.println(students.get(i).getId() + " " + students.get(i).getName() + " " + students.get(i).getGrade(super.getCode())); 
      }
   }
   
   /** 
    * @return String
    */
   // Returns a String containing the course information as well as the semester and the number of students 
   public String getDescription()
   {
	   return super.getDescription() + " " + semester + " Enrolment: " + getStudentsSize();
   }
   
   /** 
    * @return String
    */
   // Returns a string with only the description of the course
   public String getOnlyDescription()
   {
      return super.getDescription();
   }
   
   /** 
    * @param st
    */
   // static method takes student object and adds it to students arraylist
   public void addStudent(Student st)
   {
      students.add(st);
   }
   
   /** 
    * @param st
    */
   public void removeStudent(Student st)
   {
      students.remove(st);
   }
   
   /** 
    * @param id
    * @return boolean
    */
   // method created that goes through students arraylist to check if student is enrolled in active course
   public boolean isEnrolled(String id)
   {
      for (int i = 0; i < students.size(); i++)
      {
         Student s = students.get(i);
         if (s.getId().equalsIgnoreCase(id))
         {
            return true;
         }
      }
      return false;
   }
   
   // Sort the students in the course by name using private Comparator NameComparator
   public void sortByName()
   {
     Collections.sort(students, new NameComparator());
   }
   
   // class implement the Comparator interface
   // This class is used to compare two Student objects based on student name
   private class NameComparator implements Comparator<Student>
   {
      public int compare(Student x, Student y)
      {
        return x.getName().compareTo(y.getName());
      }
   }
   
   // Sort the students in the course by id using private Comparator IdComparator
   public void sortById()
   {
 	  Collections.sort(students, new IdComparator());
   }
   
   // class implement the Comparator interface
   // This class is used to compare two Student objects based on student id
   private class IdComparator implements Comparator<Student>
   {
      public int compare(Student x, Student y)
      {
        return x.getId().compareTo(y.getId());
      }
   }

   
   /** 
    * @param day
    * @param start
    * @param duration
    */
    // public method sets schedule for 3hr lec
   public void setSchedule3hrLec(String day, int start, int duration)
   {
      lectureDay3hr = day;
      lectureStart3hr = start;
      lectureDuration3hr = duration;
   }

   
   /** 
    * @param day
    * @param start
    * @param duration
    */
    // public method sets schedule for 1hr lec
   public void setSchedule1hrLec(String day, int start, int duration)
   {
      lectureDay1hr = day;
      lectureStart1hr = start;
      lectureDuration1hr = duration;
   }

   
   /** 
    * @param day
    * @param start
    * @param duration
    */
   // public method sets schedule for 2hr lec
   public void setSchedule2hrLec(String day, int start, int duration)
   {
      lectureDay2hr = day;
      lectureStart2hr = start;
      lectureDuration2hr = duration;
   }

   /** 
    * @return int
    */
    // returns lecturestart for 3hr
   public int getLectureStart3hr()
   {
      return lectureStart3hr;
   }
   
   /** 
    * @return int
    */
    // returns lecturestart for 1hr
   public int getLectureStart1hr()
   {
      return lectureStart1hr;
   }
   
   /** 
    * @return int
    */
    // returns lecturestart for 2hr
   public int getLectureStart2hr()
   {
      return lectureStart2hr;
   }
   

   
   /** 
    * @return String
    */
    // returns lectureday for 3hr
   public String getLectureDay3hr()
   {
	   return lectureDay3hr;
   }
   
   /** 
    * @return String
    */
    // returns lectureday for 1hr
   public String getLectureDay1hr()
   {
	   return lectureDay1hr;
   }
   
   /** 
    * @return String
    */
    // returns lectureday for 2hr
   public String getLectureDay2hr()
   {
	   return lectureDay2hr;
   }

   
   /** 
    * @return int
    */
    // returns lectureduration for 3hr
   public int getLectureDuration3hr()
   {
      return lectureDuration3hr;
   }
   
   /** 
    * @return int
    */
    // returns lecture duration for 1hr
   public int getLectureDuration1hr()
   {
      return lectureDuration1hr;
   }
   
   /** 
    * @return int
    */
    // returns lecture duration for 2hr
   public int getLectureDuration2hr()
   {
      return lectureDuration2hr;
   }

   
   /** 
    * @return int
    */
    // returns totalduration for lectures in this course
   public int getTotalDuration() 
   {
      return lectureDuration3hr + lectureDuration1hr + lectureDuration2hr;
   }

   // method clears all the instance variable that are used in scheduler class
   public void clearSchedule()
   {
      lectureDay3hr = "";
      lectureDay1hr = "";
      lectureDay2hr = "";
      lectureDuration3hr = 0;
      lectureDuration1hr = 0;
      lectureDuration2hr = 0;
      lectureStart3hr = 0;
      lectureStart1hr = 0;
      lectureStart2hr = 0;
   }


}

