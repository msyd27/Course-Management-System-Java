// Name: Muteeb Syed
// Student Number: 500972883

public class CreditCourse extends Course
{
	private String semester;
	public double grade;
	public boolean active;


	// constructor method that sets the instance variable and calls 
	// super class constructor to initialize inherited variables
	public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade)
	{
		super(name, code, descr, fmt);
		this.semester = semester;
		this.grade = grade;
	}
	
	/** 
	 * @return boolean
	 */
	// method returns boolean variable active
	public boolean getActive()
	{
		return active;	
	}
	
	// static method sets active variable to true
	public void setActive()
	{
		active = true;
	}
	
	// static method sets active variable to false
	public void setInactive()
	{
		active = false;
	}

	/** 
	 * @param grade2
	 */
	// static method created sets the grade given in parameter to the grade variable
	public void setGrade(double grade2) 
	{
		grade = grade2;
	}

	/** 
	 * @return double
	 */
	// method created that returns grade
	public double getGrade()
	{
		return grade;
	}

	/** 
	 * @return String
	 */
	// method the prints out info for this course ad semester and grade using inherited method in super class
	public String displayGrade()
	{
		return super.getInfo() + " " + this.semester + " Grade: " + convertNumericGrade(grade);
	}	
}