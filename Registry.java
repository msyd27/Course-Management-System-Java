import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// Name: Muteeb Syed
// Student Number: 500972883

public class Registry 
{
   private TreeMap<String, Student>	   students = new TreeMap<String, Student>();
   private TreeMap<String, ActiveCourse> courses  = new TreeMap<String, ActiveCourse>();
   
   public Registry() throws IOException
   {
	   // reads file students.txt
	   String f = "students.txt";
	   File inputFile = new File(f);
	   // if the file does not exist, throw exception
	   if (!inputFile.exists())
	   {
		   throw new FileNotFoundException(f +" File Not Found");
	   }
	   Scanner in = new Scanner(inputFile);
	   // while loop that goes through each line to check the format
	   while (in.hasNextLine())
	   {
		   String line = in.nextLine();
			Scanner lineScanner = new Scanner(line);
			// nested while loop to check each line to make sure name and id are present
			while (lineScanner.hasNext())
			{
				String name = lineScanner.next();
				if (lineScanner.hasNext())
				{
					String id = lineScanner.next();
					// if name is only alphabetical and id is numbers, then adds them to student registry
					if (name.matches("[a-zA-Z]+"))
					{
						if (id.matches("[0-9]+"))
						{
							Student s = new Student(name,id);
							students.put(s.getId(), s);
						}
						// throws ane exception bad file format
						else 
						{
							throw new BadFileException("Bad File Format " + f);
						}
					}
					else 
					{
						throw new BadFileException("Bad File Format " + f);
					}
				}
				else
				{
					throw new BadFileException("Bad File Format " + f);					
				}
			}	
		} 
		in.close();

		

	   ArrayList<Student> cps209List = new ArrayList<Student>();
	   String courseName = "Computer Science II";
	   String courseCode = "CPS209";
	   String descr = "Learn how to write complex programs!";
	   String format = "3Lec 2Lab";
	   // for loop to add a few students to course
	   int i = 0;
	   for (String id : students.keySet())
		{
			i += 1;
			Student s = students.get(id);
			if (i < 4){
				cps209List.add(s);
				s.addCourse(courseName, courseCode, descr, format, "W2020", 0);
			}		   
		}
	   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",cps209List));


	   
	  
	   // CPS511
	   ArrayList<Student> cps511List = new ArrayList<Student>();
	   courseName = "Computer Graphics";
	   courseCode = "CPS511";
	   descr = "Learn how to write cool graphics programs";
	   format = "3Lec";
	   // for loop to ad a few students to course
	   int j = 0;
	   for (String id : students.keySet())
		{
			j += 1;
			Student s = students.get(id);
			if (j % 2 == 0){
				cps511List.add(s);
				s.addCourse(courseName, courseCode, descr, format, "W2020", 0);
			}		   
		}
	   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"F2020",cps511List));
	   
	   // CPS643
	   ArrayList<Student> cps643List = new ArrayList<Student>();
	   courseName = "Virtual Reality";
	   courseCode = "CPS643";
	   descr = "Learn how to write extremely cool virtual reality programs";
	   format = "3Lec 2Lab";
	   // for loop to ad a few students to course
	   int z = 0;
	   for (String id : students.keySet())
		{
			z += 1;
			Student s = students.get(id);
			if (z % 2 == 1){
				cps643List.add(s);
				s.addCourse(courseName, courseCode, descr, format, "W2020", 0);
			}		   
		}
	   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",cps643List));
	   
	   // CPS706
	   ArrayList<Student> cps706List = new ArrayList<Student>();
	   courseName = "Computer Networks";
	   courseCode = "CPS706";
	   descr = "Learn about Computer Networking";
	   format = "3Lec 1Lab";
	   // for loop to ad a few students to course
	   int x = 0;
	   for (String id : students.keySet())
		{
			x += 1;
			Student s = students.get(id);
			if (x > 2){
				cps706List.add(s);
				s.addCourse(courseName, courseCode, descr, format, "W2020", 0);
			}		   
		}
	   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",cps706List));

	   // CPS616
	   ArrayList<Student> cps616List = new ArrayList<Student>();
	   courseName = "Algorithms";
	   courseCode = "CPS616";
	   descr = "Learn about Algorithms";
	   format = "3Lec 1Lab";
	   // for loop to ad a few students to course
	   int y = 0;
	   for (String id : students.keySet())
		{
			y += 1;
			Student s = students.get(id);
			if (y < 2){
				cps616List.add(s);
				s.addCourse(courseName, courseCode, descr, format, "W2020", 0);
			}		   
		}
	   courses.put(courseCode,new ActiveCourse(courseName,courseCode,descr,format,"W2020",cps616List));
	   
}
   


  
  /** 
   * @return TreeMap<String, ActiveCourse>
   */
  // returns the courses used in StudentRegistrySimulator
  public TreeMap<String, ActiveCourse> getCourses()
  {
	  return courses;
  }
   
   /** 
	* @param name
	* @param id
	* @return boolean
	*/
   // Method that adds new student to the registry 
   public boolean addNewStudent(String name, String id)
   {
	   // Create a new student object
	   Student s = new Student(name, id);
	   // checks to ensure student is not in registry
	   for (String ids : students.keySet())
	   {
		   // if there, returns false
			if (students.get(ids).getName().equalsIgnoreCase(name))
			{
				System.out.println("Student already in Registry");
				return false;
			}
			if (ids.equals(id))
			{
				System.out.println(" ERROR: Duplicate Student ID.");
				return false;
			}	
			
	   }
	   // if not, adds them and return true
	   students.put(s.getId(),s);
	   return true;
   }
   
   /** 
	* @param studentId
	* @return boolean
	*/
   // Method that removes student from registry 
   public boolean removeStudent(String studentId)
   {
	   // Find student in students treemap
	   for (String id : students.keySet())
	   {
		   if (id.equalsIgnoreCase(studentId))
		   {
			   students.remove(id);
			   return true;
			}
		} 
	   // If found, remove this student and return true
	   return false;
   }
   
   // Method that prints all registered students
   public void printAllStudents()
   {
	   for (String id : students.keySet())
	   {
		   System.out.println("ID: " + id + " Name: " + students.get(id).getName() );   
	   }
	   
   }
   
   /** 
	* @param studentId
	* @param courseCode
	*/
   // Method that given a studentId and a course code, adds student to the active course
   public void addCourse(String studentId, String courseCode)
   {
	   int count = 0;
	   // for loop goes through courses to check if course exists
	   for (String code : courses.keySet())
	   {
		   count += 1;
			if (code.equalsIgnoreCase(courseCode))
			{
				break;
			}
			if (count == courses.size())
			{
				System.out.println("Course does not exist");
				return;
			}
	   }
	   int i = 0;
	   String ids = "";
	   // for loop goes through students
	   for (String id : students.keySet())
	   {
		   i += 1;
		   if (id.equalsIgnoreCase(studentId))
		   {
			   ids = id;
			   // checks if student has already taken course
			   if (students.get(id).courseTaken(courseCode))
			   {
					System.out.println("Student has already taken course");
					break;
			   }
			   break;
			   
		   }
		   // also checks if student exist
		   if (i == students.size())
		   {
				System.out.println("Student not in registry");
				return;
		   }
	   }

	   Student s = students.get(ids);
	   // for loop that checks activecourse to see if student is already in course
	   for (String code: courses.keySet())
	   {
		   ActiveCourse c = courses.get(code);
		   if (code.equalsIgnoreCase(courseCode))
		   {
			   if (c.isEnrolled(studentId))
			   {
				   System.out.println("Student already enrolled");
				   return;
			   }
		   }
	   }
	   // final for loop to add student to course and add courses to the student's courses
	   for (String code : courses.keySet())
	   {
		   ActiveCourse a = courses.get(code);
		   if (code.equalsIgnoreCase(courseCode))
		   {
			   courses.get(code).addStudent(s);
			   s.addCourse(a.getName(), courseCode, a.getDescription(), a.getFormat(), a.getSemester(), 0);
		   }
	   }

	   
   }  

   /** 
	* @param studentId
	* @param courseCode
	*/
   // Method that takes a studentId and a course code and drops student from the active course
   public void dropCourse(String studentId, String courseCode)
   {
	   // Find the active course using a for loop
	   for (String code : courses.keySet())
	   {
		   // if statement checks if the coursecode is in activecourse.
		   if (code.equalsIgnoreCase(courseCode))
		   {
			   // nested for loop that goes through students treemap
			   for (String id : students.keySet())
			   {
				   // if student is in course, removes student from active course and remove credit course from student list
				   if (id.equalsIgnoreCase(studentId))
				   {
					   courses.get(code).removeStudent(students.get(id));
					   students.get(id).removeActiveCourse(courseCode);
					   break;
				   }
			   }
		   }
	   }
	}

   // Method that prints all active courses
   public void printActiveCourses()
   {
	   for (String code : courses.keySet())
	   {
		   //ActiveCourse ac = courses.get(code);
		   System.out.println(courses.get(code).getDescription());
		   System.out.println();
	   }
   }
   
   /** 
	* @param courseCode
	*/
   // Method that prints the list of students in an active course
   public void printClassList(String courseCode)
   {
	   // for loop goes through courses treemap and checks if courseCode exists
		for (String code : courses.keySet())
		{
			if(code.equalsIgnoreCase(courseCode))
			{
				courses.get(code).printClassList();
			}
		}
   }
    
   /** 
	* @param courseCode
	*/
   // Method that takes a course code, find course and sort class list by student name
   public void sortCourseByName(String courseCode)
   {
	   // for loop goes through courses treemap and checks if courseCode exists
	   for (String code : courses.keySet())
	   {
		   if (code.equalsIgnoreCase(courseCode))
		   {
			   courses.get(code).sortByName();
			}
		}
   }
   
   /** 
	* @param courseCode
	*/
   // Given a course code, find course and sort class list by student ID
   public void sortCourseById(String courseCode)
   {
	   // for loop goes through courses treemap and checks if courseCode exists
	   for (String code : courses.keySet())
	   {
		   if (code.equalsIgnoreCase(courseCode))
		   {
			   courses.get(code).sortById();
		   }
		}
   }
   
   /** 
	* @param courseCode
	*/
   // Given a course code, finds course and print student names and grades
   public void printGrades(String courseCode)
   {
	   // for loop goes through courses treemap and checks if courseCode exists
	   for (String code : courses.keySet())
	   {
		   if (code.equalsIgnoreCase(courseCode))
		   {
			   courses.get(code).printGrades();
			}
		} 
   }
   
   /** 
	* @param studentId
	*/
   // Given a studentId, print all active courses of student
   public void printStudentCourses(String studentId)
   {
	for (String id : students.keySet())
	{
		if (id.equals(studentId))
		{
			students.get(id).printActiveCourses();
		}
	} 
   }
   
   /** 
	* @param studentId
	*/
   // Given a studentId, print all completed courses and grades of student
   public void printStudentTranscript(String studentId)
   {
	   for (String id : students.keySet())
	   {
		   if (id.equals(studentId))
		   {
			   students.get(id).printTranscript();
			}
		}  
   }
   
   /** 
	* @param courseCode
	* @param studentId
	* @param grade
	*/
   // Given a course code, student id and numeric grade sets the final grade of the student
   public void setFinalGrade(String courseCode, String studentId, double grade)
   {
	   // for loop that finds the active course
	   
	   for (String code : courses.keySet())
	   {
		   if (code.equalsIgnoreCase(courseCode))
		   {
			   // nested for loop goes through students treemap to find student
			   for (String id : students.keySet())
			   {
				   if (id.equalsIgnoreCase(studentId))
				   {
					   // triple for loop that finds student credit course list in student object
					   for (CreditCourse z: students.get(id).courses)
					   {
						   if (z.getCode().equalsIgnoreCase(courseCode))
						   {
							   // sets the grade in credit course and set credit course inactive
							   z.setGrade(grade);
							   z.setInactive();
							}
						}
					}
				}
			}
		}  
   }
  
}
