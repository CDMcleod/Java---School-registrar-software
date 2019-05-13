package mcleod.programfinal.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import mcleod.programfinal.model.Section;

public class Student extends Person{

	//Section sec = new Section();
	private static int nextId = 1;
	private final int studentId;
	private Major major;
	public ArrayList<String> registeredSections;
	
	public Student(String fName, String lName) {
		super(fName, lName);
		registeredSections = new ArrayList<String>();
		studentId = nextId;
		nextId++;
	}

	public int getStudentId() {
		return studentId;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(String majorName) {
		
		Catalog cat = Catalog.getCatalog();
		cat.getMajorList();
		for (int i = 0; i < cat.majors.size(); i++){
			if(cat.majors.get(i).getMajorName().equalsIgnoreCase(majorName))
			{
				this.major = cat.majors.get(i);
				break;
				//return found;
				
			}
			else if (!cat.majors.get(i).getMajorName().equalsIgnoreCase(majorName))
			{
				//System.out.println("!ERROR! : major not found!");
			}
		}
		//return null;
	}
	
	public boolean addStudentSection(String secName){
		
		TermSchedule t = TermSchedule.getSchedule();
		Section sec = t.getSectionByName(secName);
		
		if(sec.registerStudent(this) == true)
		{
			secName = secName.toUpperCase();
			registeredSections.add(secName);
			return true;
		}
		else
			return false;
		
	}
	
	public List<String> getRegisteredSections() {
		
		return Collections.unmodifiableList(registeredSections);
		
	}
	
	
}
