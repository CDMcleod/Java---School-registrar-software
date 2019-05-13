package mcleod.programfinal.model;
import java.util.Comparator;

public class Course {

	private final String courseId;
	private final String courseName;
	private final int hours;
	
	public Course(String id, String name, int time) {
		courseId = id;
		courseName = name;
		hours = time;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public int getHours() {
		return hours;
	}
	
//	public int compareTo(Object o1, Object o2)
	public int compareTo(Course other)
	{
		return courseId.compareTo(other.courseId);
	}
	
}
