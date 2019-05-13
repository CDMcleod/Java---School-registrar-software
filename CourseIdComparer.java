package mcleod.programfinal.model;
import java.util.Comparator;

public class CourseIdComparer implements Comparator<Course> {
	@Override
	public int compare(Course o1, Course o2)
	{
		return o1.getCourseId().compareTo(o2.getCourseId());
	}

}
