import java.util.ArrayList;
import java.util.TreeMap;

// Name: Muteeb Syed
// Student Number: 500972883

public class Scheduler 
{
	
	TreeMap<String,ActiveCourse> schedule;
	ArrayList<String> days = new ArrayList<String>();

	// constructor method adds the valid days into days arraylist and passes courses treemap from registry
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  schedule = courses;
	  days.add("mon"); days.add("tue");days.add("wed");days.add("thur");days.add("fri");
	}
	
	// Bonus B: a method very similar to setDayAndTime, got it to partially work.
	public void setLec(String courseCode, int duration)
	{
		int count = 0;
		String cd = "";
		// for loop goes through courses to check if course exists
		for (String code : schedule.keySet())
		{
			count += 1;
			if (code.equalsIgnoreCase(courseCode))
			{
				cd = code;
				break;
			}
			// if it doesn't exist, throws exception
				if (count == schedule.size())
				{
					throw new UnknownCourseException("Unknown Course: " + courseCode);
				}
			
		}
		ActiveCourse a = schedule.get(cd);
		if (a.getTotalDuration() > 3)
		{
			throw new InvalidDurationException("Total Duration for course reached (3hrs)");
		}
		// for loop goes through each hour
		for (int i = 800; i < 1700; i+=100)
		{
			// nested for loop goes through day
			for(String day : days){
			if (getCourseCode(i, day).equalsIgnoreCase("."))
			{
				// set schedule for 1hr lec
				if (duration == 1)
				{
					a.setSchedule1hrLec(day, i, duration);
					i += 100;
				}
				if (duration == 2)
				{
					if (getCourseCode(i+100, day).equalsIgnoreCase("."))
					{
						a.setSchedule2hrLec(day, i, duration);
						i += 100;
						break;
					}
					if (i == 1699 && day.equalsIgnoreCase("fri"))
					{
						throw new LectureTimeNotFound("Cannot find a lecture time for " + courseCode + " with duration of " + duration +"hr");
					}
				}
				if (duration == 3)
				{
					if (getCourseCode(i+100, day).equalsIgnoreCase("."))
					{
						if(getCourseCode(i+200, day).equalsIgnoreCase("."))
						{
							a.setSchedule3hrLec(day, i, duration);
							i += 100;
							break;
						}
						if (i == 1699 && day.equalsIgnoreCase("fri"))
						{
						throw new LectureTimeNotFound("Cannot find a lecture time for " + courseCode + " with duration of " + duration +"hr");
						}
					}
				}
			}
		}
		break;
		}	
	}
	
	/** 
	 * @param courseCode
	 * @param day
	 * @param startTime
	 * @param duration
	 */
	// method sets the schedule of a given course
	public void setDayAndTime(String courseCode, String day, int startTime, int duration)
	{
		int count = 0;
		// for loop goes through courses to check if course exists
		for (String code : schedule.keySet())
		{
			count += 1;
			if (code.equalsIgnoreCase(courseCode))
			{
				break;
			}
			// if it doesn't exist, throws exception
			if (!code.equalsIgnoreCase(courseCode))
			{
				if (count == schedule.size())
				{
					throw new UnknownCourseException("Unknown Course: " + courseCode);
				}
			}
		}

		int count1 = 0;
		// for loop goes through days to check if valid day given
		for (String d : days)
		{
			count1 += 1;
			if (d.equalsIgnoreCase(day))
			{
				break;
			}
			// if valid day not given, throws and exception
			if (!d.equalsIgnoreCase(day))
			{
				if (count1 == days.size())
				{
					throw new InvalidDayException("Invalid Lecture Day: " + day) ;
				}
			}
		}
		// if startTime is not valid, throws exception
		if (startTime < 800)
		{
			throw new InvalidTimeException("Invalid Start Time: " + startTime);
		}
		// if startTime is not valid, throws exception
		if (startTime + (duration*100) > 1700)
		{
			throw new InvalidTimeException("Duration exceeds end time");
		}
		// if duration is greater than 3 or less than 1, throws exception
		if (duration > 3 || duration < 1)
		{
			throw new InvalidDurationException("Invalid Lecture Duration: " + duration);
		}
		// for loop goes through course to check for lecture collision, there are three conditions
		for (String code : schedule.keySet())
		{
			ActiveCourse s = schedule.get(code);
			// checks for days for 3hr lectures
			if (day.equalsIgnoreCase(s.getLectureDay3hr()))
			{
				// if the time is already taken, throws exception
				if (startTime == s.getLectureStart3hr())
				{
					throw new LectureTimeCollision("Lecture time overlapping other lectures");
				}
				// the total lecture time overlaps, another lectures time by checking the duration of lectures, throw exception
				if ((startTime + (duration*100)) < (s.getLectureStart3hr() + (s.getLectureDuration3hr()*100)))
				{
					if (!((startTime + duration*100) <= s.getLectureStart3hr()))
					{
						throw new LectureTimeCollision("Lecture time overlapping other lectures");
					}
				}
				int z = (s.getLectureStart3hr() + s.getLectureDuration3hr()*100);
				// third condition makes sure the lecture time given is not started between another lecture
				for (int i = 0; i < z; i++)
				{
					if (i < s.getLectureStart3hr())
					{
						continue;
					}
					// throws an exception if starttime begins between another lecture
					if (startTime == i)
					{
						throw new LectureTimeCollision("Lecture time overlapping other lectures");
					}
					
				}
			}
			// the same conditions are applied for the 1 hr lectures
			if (day.equalsIgnoreCase(s.getLectureDay1hr()))
			{
				if (startTime == s.getLectureStart1hr())
				{
					throw new LectureTimeCollision("Lecture time overlapping other lectures");
				}
				if ((startTime + (duration*100)) < (s.getLectureStart1hr() + (s.getLectureDuration1hr()*100)))
				{
					if (!((startTime + duration*100) <= s.getLectureStart1hr()))
					{
						throw new LectureTimeCollision("Lecture time overlapping other lectures");
					}
				}
				int z = (s.getLectureStart1hr() + s.getLectureDuration1hr()*100);
				for (int i = 0; i < z; i++)
				{
					if (i < s.getLectureStart1hr())
					{
						continue;
					}
					if (startTime == i)
					{
						throw new LectureTimeCollision("Lecture time overlapping other lectures");
					}
					
				}
			}
			// same conditions are applied for the 2 hr lectures
			if (day.equalsIgnoreCase(s.getLectureDay2hr()))
			{
				if (startTime == s.getLectureStart2hr())
				{
					throw new LectureTimeCollision("Lecture time overlapping other lectures");
				}
				if ((startTime + (duration*100)) < (s.getLectureStart2hr() + (s.getLectureDuration2hr()*100)))
				{
					if (!((startTime + duration*100) <= s.getLectureStart2hr()))
					{
						throw new LectureTimeCollision("Lecture time overlapping other lectures");
					}
				}
				int z = (s.getLectureStart2hr() + s.getLectureDuration2hr()*100);
				for (int i = 0; i < z; i++)
				{
					if (i < s.getLectureStart2hr())
					{
						continue;
					}
					if (startTime == i)
					{
						throw new LectureTimeCollision("Lecture time overlapping other lectures");
					}
					
				}
			}
		}
		// for loop goes through courses to check if can set it after going through all exception handling
		for (String code : schedule.keySet())
		{
			if (code.equalsIgnoreCase(courseCode))
			{
				// if the total duration is over 3 hrs, throw and exception
				if (schedule.get(code).getTotalDuration() > 3)
				{
					throw new InvalidDurationException("Total Duration for course reached (3hrs)");
				}
				// set schedule for 1hr lec
				if (duration == 1)
				{
					schedule.get(code).setSchedule1hrLec(day, startTime, duration);
				}
				// set schedule for 2hr lec

				if (duration == 2)
				{
					schedule.get(code).setSchedule2hrLec(day, startTime, duration);
				}
				// set schedule for 3hr lec
				if (duration == 3)
				{
					schedule.get(code).setSchedule3hrLec(day, startTime, duration);
				}
				
			}
		}
	}
	
	
	/** 
	 * @param courseCode
	 */
	public void clearSchedule(String courseCode)
	{
		// for loop checks if course exist
		for (String code : schedule.keySet())
		{
			// if true, clear schedule for the course
			if (code.equalsIgnoreCase(courseCode))
			{
				schedule.get(code).clearSchedule();
			}
		}
	}
		
	public void printSchedule()
	{
		System.out.print("\t");
		// initial for loop sets the top row to print days
		for (String day : days)
		{
			System.out.print(Character.toUpperCase(day.charAt(0))+day.substring(1) + "\t");
		}
		System.out.println();
		// 2d array list that goes through each hour for each day
		String [][] sch = new String[9][6];
		// for loop goes through each row(hour)
		for (int i = 0; i < sch.length; i++)
		{
			// nested for loop goes through col (day)
			for (int j = 0; j < sch[i].length; j++)
			{
				// for first column, only print out the hour to set the left column
				if (j == 0)
				{
					if (i<2)
					sch[i][j] = ((i+8)*100 + "  |");
					else{
						sch[i][j] = ((i+8)*100 + " |");	
					}
					System.out.print(sch[i][j] + "\t");
				}
				// for the first column, which is mon, get the coursecode for each hour
				else if (j == 1)
				{
					sch[i][j] = getCourseCode((i+8)*100, "mon");
					System.out.print(sch[i][j] + "\t");
				}
				// for the first column, which is tue, get the coursecode for each hour
				else if (j == 2)
				{
					sch[i][j] = getCourseCode((i+8)*100, "tue");
					System.out.print(sch[i][j] + "\t");
				}
				// for the first column, which is wed, get the coursecode for each hour
				else if (j == 3)
				{
					sch[i][j] = getCourseCode((i+8)*100, "wed");
					System.out.print(sch[i][j] + "\t");
				}
				// for the first column, which is thur, get the coursecode for each hour
				else if (j == 4)
				{
					sch[i][j] = getCourseCode((i+8)*100, "thur");
					System.out.print(sch[i][j] + "\t");
				}
				// for the first column, which is fri, get the coursecode for each hour
				else if (j == 5)
				{
					sch[i][j] = getCourseCode((i+8)*100, "fri");
					System.out.print(sch[i][j] + "\t");
				}
			}
			System.out.println();
		}
		

	}

	
	/** 
	 * @param time
	 * @param d
	 * @return String
	 */
	// method created that gets the coursecode when given parameters time and d(day)
	public String getCourseCode(int time, String d)
	{
		// for loop goes through courses
		for (String code: schedule.keySet())
		{
			ActiveCourse s = schedule.get(code);
			// if statement checks if parameter d equals lecture days
			if (s.getLectureDay3hr().equalsIgnoreCase(d))
			{
				// if time equals any of the courses time, return code
				if (s.getLectureStart3hr() == time)
				{
					return code;
				}
				// for 3hr lectures, return code for hours after the starttime also
				if (s.getLectureStart3hr()+((s.getLectureDuration3hr()-1)*100) == time)
				{
					return code;
				}
				// for 3hr lectures, return code for hours after the starttime also
				if ((s.getLectureStart3hr()+((s.getLectureDuration3hr()-2)*100) == time))
				{
					return code;
				}
			}	
			// if parameter d is found in courses day 1hr
			if (s.getLectureDay1hr().equalsIgnoreCase(d))
			{
				// if time equals 1hr, return code
				if (s.getLectureStart1hr() == time)
				{
					return code;
				}
			}
			// if parameter d is found in courses day 2hr
			if (s.getLectureDay2hr().equalsIgnoreCase(d))
			{
				// if courses lecturestart equals time, return code
				if (s.getLectureStart2hr() == time)
				{
					return code;
				}
				// for 2hr lec, return code for row after starttime
				if (s.getLectureStart2hr()+((s.getLectureDuration2hr()-1)*100) == time)
				{
					return code;
				}
			}
		}
		// if code is not present at time, the print . to show empty slot in schedule
		return ".";
	}
	
}


