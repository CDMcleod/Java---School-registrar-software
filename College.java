package mcleod.programfinal.model;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class College {

	private static College theCollege;//instance of college
	ArrayList<Student> students = new ArrayList<Student>();
	ArrayList<Instructor> instructors = new ArrayList<Instructor>();
	ArrayList<String> sections = new ArrayList<String>();
	
	private College(){
		
	}
	
	public static College getCollege(){
		
		if (theCollege == null)
		{
			theCollege = new College();//remove this for other application on singleton
		}
		
		return theCollege;
	}
	
	public ArrayList<Student> getStudentList() {
		
		return students;
	}
	
	public ArrayList<Instructor> getInstructorList() {
		
		return instructors;
	}
	
	public Student addStudent(String firstName, String lastName){
		
		Student s = new Student(firstName, lastName);
		students.add(s);
		return s;
	}
	
	public Instructor addInstructor(String firstName, String lastName){
		
		Instructor i = new Instructor(firstName, lastName);
		instructors.add(i);
		return i;
	}
	
	public Student getStudentById(int studentId) {

		for (int i = 0; i < students.size(); i++){
			if(students.get(i).getStudentId() == (studentId))
			{
				Student found = students.get(i);
				return found;
				
			}
			else if (!students.get(i).equals(studentId))
			{
				
			}
		}
		return null;
	
	}
	
	public Student getStudentByName(String firstName, String lastName) {

		for (int i = 0; i < students.size(); i++){
			if(students.get(i).getFirstName().equalsIgnoreCase(firstName))
			{
				if(students.get(i).getLastName().equalsIgnoreCase(lastName))
				{
				Student found = students.get(i);
				return found;
				}
				
			}
			else if (!students.get(i).getFirstName().equalsIgnoreCase(firstName))
			{
				
			}
		}
		return null;
	
	}
	
	public Instructor getInstructorByName(String firstName, String lastName) {

		for (int i = 0; i < instructors.size(); i++){
			if(instructors.get(i).getFirstName().equalsIgnoreCase(firstName))
			{
				if(instructors.get(i).getLastName().equalsIgnoreCase(lastName))
				{
				Instructor found = instructors.get(i);
				return found;
				}
				
			}
			else if (!instructors.get(i).getFirstName().equalsIgnoreCase(firstName))
			{
				
			}
		}
		return null;
	
	}
	
	public void loadStudents(){
		try {
			Scanner infile = new Scanner (new FileInputStream("NeptuneStudents.txt"));
			
			while(infile.hasNext()) {
				String line = infile.nextLine();
				String[] fields = (line.split(","));
				String firstName = fields[0];
				String lastName = fields[1];
				Student s = new Student(firstName, lastName);
				students.add(s);
				for (int i=2; i < fields.length; i++) {
					 String Major = (fields[i]);
					 s.setMajor(Major);
					 //students.add(new Student(firstName, lastName, Major));
				}
			}
			infile.close();
		}
		catch (IOException ex)
		{
			
			ex.printStackTrace();
		}//END try-catch
	}
	
	public void loadInstructors(){
		try {
			Scanner infile = new Scanner (new FileInputStream("NeptuneInstructors-B.txt"));
			
			while(infile.hasNext()) {
				String line = infile.nextLine();
				String[] fields = (line.split(","));
				String firstName = fields[0];
				String lastName = fields[1];
				Instructor b = new Instructor(firstName, lastName);
				instructors.add(b);
				for (int i=2; i < fields.length; i++) {
					 String courseId = (fields[i]);
					 b.addPreferredCourse(courseId);

				}
			}
			infile.close();
		}
		catch (IOException ex)
		{
			
			ex.printStackTrace();
		}//END try-catch
	}//end loadInstructors
	
	public void addSection(String sectionName){
		
		sections.add(sectionName);
	}
	
	public List<String> getSectionNames() {
		
		return Collections.unmodifiableList(sections);
	}
}
