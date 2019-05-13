package mcleod.programfinal.model;
import java.util.ArrayList;

public class Instructor extends Person{

	public ArrayList<Course> preferredCourses;
	
	public Instructor(String fName, String lName) {
		super(fName, lName);
		
		preferredCourses = new ArrayList<Course>();
	}
	
	public void addPreferredCourse(String courseId) {
		Catalog cat = Catalog.getCatalog();
		Course newCourse = cat.getCourseById(courseId);
		if(newCourse == null)
		{
			System.out.println("Could not find course: '" + courseId + "'");
		}
		else if(newCourse != null)
		{
			isPreferredCourse(newCourse);
			if(isPreferredCourse(newCourse) == false);
			{
				preferredCourses.add(newCourse);
			}
			
			if (isPreferredCourse(newCourse) == true)
			{

			}
			
		}	
	}
	
	public boolean isPreferredCourse(Course newCourse){
		
		boolean exists = false;
		for (int i = 0; i < preferredCourses.size(); i++){
			if(preferredCourses.get(i).getCourseId().equals(newCourse))
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
}
