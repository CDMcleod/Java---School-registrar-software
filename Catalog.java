package mcleod.programfinal.model;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;


public class Catalog {

	private static Catalog theCatalog;
	ArrayList<Course> courses = new ArrayList<Course>();
	ArrayList<Major> majors = new ArrayList<>();
	//public static int courseCount = 0;
	
	public Catalog() {
		theCatalog = this;
		
	}
	
	public static Catalog getCatalog() {
		
		return theCatalog;
	}
	
	public ArrayList<Course> getCourseList() {
		
		return courses;
	}
	
	public ArrayList<Major> getMajorList() {
		
		return majors;
	}
	
	public Course getCourseById(String courseId) {

		for (int i = 0; i < courses.size(); i++){
			if(courses.get(i).getCourseId().equals(courseId))
			{
				Course found = courses.get(i);
				return found;
				
			}
//			else if(!courses.get(i).equals(courseId))
//			{
//				
//			}
			else if (!courses.get(i).equals(courseId))
			{
				
			}
		}
		return null;
	
	}
	
	public Major getMajorByName(String majorName){
		
		for (int i = 0; i < majors.size(); i++){
			if(majors.get(i).getMajorName().equals(majorName))
			{
				Major found = majors.get(i);
				return found;
				
			}
			else if (!majors.get(i).equals(majorName))
			{
				
			}
		}
		return null;
	}
	
	public void loadCourses() {
		College c = College.getCollege();
		try {
			Scanner infile = new Scanner (new FileInputStream("NeptuneCourses.txt"));
			
			while(infile.hasNext()) {
				String line = infile.nextLine();
				String[] fields = (line.split(","));
				String courseId = fields[0];
				String courseName = fields[1];
				
				for (int i=2; i < fields.length; i++) {
					int hours1 = Integer.parseInt(fields[i]);
					courses.add(new Course(courseId,  courseName, hours1));
				}
				
//				String sectionId = courseId + "-001";
//				//System.out.println("sectionID: " + sectionId);
//				c.addSection(sectionId);
			}
			infile.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}//END try-catch
	}
	
	public void loadMajors() {
		College c = College.getCollege();
		try {
			Scanner infile = new Scanner (new FileInputStream("NeptuneMajors.txt"));
			
			while(infile.hasNext()) {
				String line = infile.nextLine();
				String[] fields = line.split(",");
				String courseName = fields[0];
				Boolean isDegree = Boolean.parseBoolean(fields[1]);
				Major m = new Major(courseName, isDegree);
				majors.add(m);
				//majors.add(new Major(courseName,  isDegree));
				for (int i=2; i < fields.length; i++) {
					String courseId = (fields[i]);
					//returnItem();
					m.addRequiredCourse(courseId);
					
					
				}
			}
			infile.close();
		}
		catch (IOException ex)
		{
			System.err.println("oh noes!!!");
			ex.printStackTrace();
		}//END try-catch
	}
	
	public int returnItem() {
		return courses.indexOf("ENG-101");
		
	}
	
	public void displayCourses() {
		
		for (Course crs : courses)
        {
        	 System.out.println(crs.getCourseId());
        	 System.out.println(crs.getCourseName());
        	 System.out.println(crs.getHours());
        }
	}
	
	public void displayMajors() {
		
		for (Major mjr : majors)
        {
        	 System.out.println(mjr.getMajorName());
        	 System.out.println(mjr.getIsDegree());

        }
	}
	
}//End Catalog class
