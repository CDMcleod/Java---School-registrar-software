package mcleod.programfinal.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Major {

	private final String majorName;
	private final boolean isDegree;
	public ArrayList<Course> requiredCourses;
	public ArrayList<String> invalidCourses;
	
	public Major(String name, boolean degree){
		majorName = name;
		isDegree = degree;
		requiredCourses = new ArrayList<Course>();
		invalidCourses = new ArrayList<String>();
	}

	public String getMajorName() {
		return majorName;
	}

	public boolean getIsDegree() {
		return isDegree;
	}
	
	public void addRequiredCourse(String courseId) {
		Catalog cat = Catalog.getCatalog();
		Course newCourse = cat.getCourseById(courseId);
		if(newCourse == null)
		{
			//System.out.println("Could not find course: '" + courseId + "'");
			invalidCourses.add(courseId);
		}
		else if(newCourse != null)
		{
			checkForCourse(newCourse);
			if(checkForCourse(newCourse) == false);
			{
				requiredCourses.add(newCourse);
			}
			
			if (checkForCourse(newCourse) == true)
			{
				System.out.println("Course already exists as requirment.");
			}
			
		}	
	}
	
	public boolean checkForCourse(Course newCourse){
		
		boolean exists = false;
		for (int i = 0; i < requiredCourses.size(); i++){
			if(requiredCourses.get(i).getCourseId().equals(newCourse))
			{
				exists = true;
				//return exists;
				
			}
			else
			{
				//return exists;
			}	
		}
		return exists;
	}
	public List<Course> getRequiredCourses() {
		
		return Collections.unmodifiableList(requiredCourses);
		
	}
}
