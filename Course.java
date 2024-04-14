// Name: Muteeb Syed
// Student Number: 500972883

// Super class Course
public class Course 
{
	private String code;
	private String name;
	private String description;
	private String format;
	   
	// initializes variables with default constructor method
	public Course()
	{
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
	}
	
	// constructor method that sets the instance variables for course
	public Course(String name, String code, String descr, String fmt)
	{
	  this.code        = code;
	  this.name        = name;
	  this.description = descr;
	  this.format      = fmt;
	}

	/** 
	 * @return String
	 */
	// returns the code of the course
	public String getCode()
	{
	   return code;
	}
	
	/** 
	 * @return String
	 */
	// returns the name of the course
	public String getName()
	{
	  return name;
	}
	   
	/** 
	 * @return String
	 */
	// returns the format of the course
	public String getFormat()
	{
	  return format;
	}

	/** 
	 * @return String
	 */
	// returns the description which includes code, name, format   
	public String getDescription()
	{
	  return code +" - " + name + "\n" + description + "\n" + format;
	}

	/** 
	 * @return String
	 */
	// returns the code and name of the course
	public String getInfo()
	{
		return code + " " + name;
	}
	 
	/** 
	 * @param grade
	 * @return String
	*/
	// static method to convert numeric grade to letter grade 
	public static String convertNumericGrade(double grade)
	{
		if (grade >= 90)
			return "A+";
		else if (grade >= 85)
        	return "A" ;
		else if (grade >= 80)
        	return "A-" ;
    	else if (grade >= 77)
    		return "B+";
    	else if (grade >= 73)
        	return "B";
    	else if (grade >= 70)
        	return "B-";
    	else if (grade >= 67)
        	return "C+";
    	else if (grade >= 63)
        	return "C";
     	else if (grade >= 60)
        	return "C-";
     	else if (grade >= 57)
        	return "D+";
     	else if (grade >= 53)
        	return "D";
     	else if (grade >= 50)
        	return "D-";
		return "F";
	 }	 
}
