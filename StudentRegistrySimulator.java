import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Name: Muteeb Syed
// Student Number: 500972883

public class StudentRegistrySimulator 
{
  public static void main(String[] args) throws IOException
  {
	  // try catch used to check for all exceptions
	  // first try is to check for file exceptions
	  try
	  {
		  Registry registry = new Registry();
		  Scheduler scheduler = new Scheduler(registry.getCourses());
		  Scanner scanner = new Scanner(System.in);
		  System.out.print(">");
		  // while loop created to make sure the program continues after exceptions are catched.
		  while(true)
		  {
			  // the five created exceptions in scheduler class are handled in this try catch block
			  try
			  {
				  while (scanner.hasNextLine()) 
				  {
					  String inputLine = scanner.nextLine();
					  if (inputLine == null || inputLine.equals(""))
					  continue;
					  Scanner commandLine = new Scanner(inputLine);
					  String command = commandLine.next();
					  
					  if (command == null || command.equals(""))
					  continue;
					  
					  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST")) 
					  {
						  registry.printAllStudents();
					  }
					  else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT")){
						  return;
					  }					  
					  else if (command.equalsIgnoreCase("REG")) {
						// adds student to registry
						// gets name and student id from user
						String name = commandLine.next();
						String id = commandLine.next();
						// if statement ensure name is all alphabetic characters
						// ensure id string is all numeric characters
						if (isStringOnlyAlphabet(name) && isNumeric(id)) {
						if (id.length() == 5) {
							registry.addNewStudent(name, id);
						} else {
						System.out.println("ERROR: Student ID must be 5 digits.");
						}
						} else if (!isStringOnlyAlphabet(name)) {
						System.out.println("Invalid Characters in Name: " + name);
						} else if (!isNumeric(id)) {
						System.out.println("Invalid Characters in ID: " + id);
						}

						} else if (command.equalsIgnoreCase("DEL")) {
						// removes student from registry
						// gets student id from user
						String id = commandLine.next();
						// if statement ensure numeric
						if (isNumeric(id)) {
							// ensure of id is 5 digits
							if (id.length() == 5) {
								registry.removeStudent(id);
							} else {
								System.out.println("ERROR: Student ID must be 5 digits.");
							}

						} else if (!isNumeric(id)) {
							System.out.println("Invalid Characters in ID: " + id);
						}
						

						}else if (command.equalsIgnoreCase("ADDC")) {
							// adds a student to an active course
							// gets student id and course code from user
							String id = commandLine.next();
							String courseCode = commandLine.next();
							// if statement ensure if id is numeric and is 5 digits
							if (isNumeric(id)) {
								if (id.length() == 5) {
									registry.addCourse(id, courseCode);
								} else {
									System.out.println(" ERROR: Student ID must be 5 digits.");
								}
							} else if (!isNumeric(id)) {
								System.out.println(" Invalid Characters in  ID:" + id);
							}

						} else if (command.equalsIgnoreCase("DROPC")) {
							// drops student from a course
							// gets student id and course code string from user
							String id = commandLine.next();
							String courseCode = commandLine.next();
							// if statement ensure if id is numeric and is 5 digits
							if (isNumeric(id)) {
								if (id.length() == 5) {
									registry.dropCourse(id, courseCode);
								} else {
									System.out.println(" ERROR: Student ID must be 5 digits.");
								}

							} else if (!isNumeric(id)) {
								System.out.println(" Invalid Characters in  ID:" + id);
							}
						}

						else if (command.equalsIgnoreCase("PAC")) {
							// print all active courses using method in Registry class
							registry.printActiveCourses();
						}

						else if (command.equalsIgnoreCase("PCL")) {
							// gets course code string from user
							String courseCode = commandLine.next();
							registry.printClassList(courseCode);
							// print class list for this course

						} else if (command.equalsIgnoreCase("PGR")) {
							// gets course code from user
							String courseCode = commandLine.next();
							// print name, id and grade of all students in active course
							registry.printGrades(courseCode);

						} else if (command.equalsIgnoreCase("PSC")) {
							// print all credit courses of student
							// gets student id string from user
							String id = commandLine.next();
							// if statement ensure if id is numeric and is 5 digits
							if (isNumeric(id)) {
								if (id.length() == 5) {
									registry.printStudentCourses(id);
								} else {
									System.out.println(" ERROR: Student ID must be 5 digits.");
								}

							} else if (!isNumeric(id)) {
								System.out.println(" Invalid Characters in  ID:" + id);
							}

						}

						else if (command.equalsIgnoreCase("PST")) {
							// prints student transcript
							// gets student id string from user
							String id = commandLine.next();
							// if statement ensure if id is numeric and is 5 digits
							if (isNumeric(id)) {
								if (id.length() == 5) {
									registry.printStudentTranscript(id);
								} else {
									System.out.println(" ERROR: Student ID must be 5 digits.");
								}

							} else if (!isNumeric(id)) {
								System.out.println(" Invalid Characters in  ID:" + id);
							}

						} else if (command.equalsIgnoreCase("SFG")) {
							// gets course code, student id, numeric grades from user
							String courseCode = commandLine.next();
							String id = commandLine.next();
							String grade = commandLine.next();
							// take finalgrade as double using .parseDouble(grade)
							double finalGrade = Double.parseDouble(grade);
							// if statement ensure if id is numeric and is 5 digits
							if (isNumeric(id)) {
								if (id.length() == 5) {
									if (isNumeric(grade)) {
										// sets final grade of student
										registry.setFinalGrade(courseCode, id, finalGrade);
									}
									if (!isNumeric(grade)) {
										System.out.println(" ERROR: Invalid Characters in Grade: " + grade);
									}
								} else {
									System.out.println(" ERROR: Student ID must be 5 digits.");
								}
							} else if (!isNumeric(id)) {
								System.out.println(" Invalid Characters in  ID:" + id);
							}
							registry.setFinalGrade(courseCode, id, finalGrade);
						}

						else if (command.equalsIgnoreCase("SCN")) {
							// gets course code from user
							String courseCode = commandLine.next();
							// sort list of students in course by name
							registry.sortCourseByName(courseCode);
						}

						else if (command.equalsIgnoreCase("SCI")) {
							// gets course code from user
							String courseCode = commandLine.next();
							// sort list of students in course by student id
							registry.sortCourseById(courseCode);
						}

						else if (command.equalsIgnoreCase("SCH")) {
							// gets course code, day, starttime and duration from user
							String courseCode = commandLine.next();
							if (commandLine.hasNextInt())
							{
								int duration = commandLine.nextInt();
								scheduler.setLec(courseCode, duration);
							}
							else if (commandLine.hasNext())
							{
								String day = commandLine.next();
								int start = commandLine.nextInt();
								int duration = commandLine.nextInt();
							// set the schedule for the course
							scheduler.setDayAndTime(courseCode, day, start, duration);
							}
						}

						else if (command.equalsIgnoreCase("CSCH")) {
							// gets course code from user
							String courseCode = commandLine.next();
							// clear schedule
							scheduler.clearSchedule(courseCode);

						}
						else if (command.equalsIgnoreCase("PSCH")) {
							// gets course code from user
							// clear schedule
							scheduler.printSchedule();
						}
						System.out.print("\n>");
					}
				}
				// catch exceptions for scheduler class
				catch (RuntimeException e)
				{
					System.out.println(e.getMessage());
					System.out.print("\n>");
				}
			}
		 }
		 // catch exceptions from registry class for file
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
}
  // method that returns boolean if a string contains only alphabetic using regex method
  private static boolean isStringOnlyAlphabet(String str) 
  { 
	  return str.matches("[a-zA-Z]+");
  } 
  
  // write method to check if string str contains only numeric characters using regex
  public static boolean isNumeric(String str)
  {
	  for (int i = 0; i < str.length(); i++){
		  if(str.matches("[0-9]+"))
		  {
			return true;
		  } 
	  }
	  return false;
  }
}