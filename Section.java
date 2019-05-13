package mcleod.programfinal.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class Section extends Observable{

	private int maxStudents;
	private Course course;
	private String sectionNumber;
	ArrayList<Student> classRoll;
	private String sectionName; // sectionName = getCourse().toString + sectionNumber;
	Catalog myCatalog = new Catalog();
	
	public Section(String crsId, String secNum, int maxCapacity){
		
		myCatalog.loadCourses();
		course = myCatalog.getCourseById(crsId);
		sectionNumber = secNum;
		maxStudents = maxCapacity;
		classRoll = new ArrayList<Student>();
	}
	
	public Course getCourse(){
		
		return course;
	}
	
	public String getSectionNumber(){
		
		return sectionNumber;
	}
	
	public int getMaxCapacity(){
		
		return maxStudents;
	}
	
	public String getName(){
		
		sectionName = course.getCourseId() + "-" + sectionNumber;
		return sectionName;
	}
	
	public boolean registerStudent(Student student){
		
		if(classRoll.size() + 1 <= maxStudents)
		{
			if(!classRoll.contains(student))
			{
				classRoll.add(student);
				//System.out.println("Student succesfully added!");
				return true;
			}
			else
				//System.err.println("Error: student already registered for class.");
				return false;
		}
		else 
			//System.err.println("Error: class capacity already a maxiumum.");
			return false;//end outer if-else
		
	}//end registerStudent
	
	public ArrayList<Student> getClassRoll(){
		
		return classRoll;
	}
	
	public int getAvailableSeats(){
		
		int seatsLeft = maxStudents - classRoll.size();
		return seatsLeft;
	}
	
	public void setOverride(){
		
		maxStudents = maxStudents + 1;
	}
}
