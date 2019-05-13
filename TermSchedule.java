package mcleod.programfinal.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TermSchedule {

	private static TermSchedule theSchedule;
	ArrayList<Section> sections = new ArrayList<Section>();
	
	private TermSchedule(){
		
	}
	
	public static TermSchedule getSchedule(){
		
		if (theSchedule == null)
		{
			theSchedule = new TermSchedule();//remove this for other application on singleton
		}
		
		return theSchedule;
	}
	
	public void loadSections(){
		
		try {
			Scanner infile = new Scanner (new FileInputStream("NeptuneSections-final.txt"));
			
			while(infile.hasNext()) 
			{
				String line = infile.nextLine();
				String[] fields = (line.split(","));
				String courseId = fields[0];
				String sectionNum = fields[1];
				int maxCap = Integer.parseInt(fields[2]);
				
				sections.add(new Section(courseId, sectionNum, maxCap));
				//courses.add(new Course(courseId,  courseName, hours1));
				
//				String sectionId = courseId + "-001";
//				System.out.println("sectionID: " + sectionId);
//				c.addSection(sectionId);
			}
			infile.close();
		}
		catch (IOException ex)
		{
			
			ex.printStackTrace();
		}//END try-catch
	}
	
	public List<String> getSectionNames(){
		
		ArrayList<String> secNames = new ArrayList<String>();
		
		for (int i = 0; i < sections.size(); i++)
		{
			String newSec = sections.get(i).getName();
			secNames.add(newSec);
		}
		return Collections.unmodifiableList(secNames);
	}
	
	public Section getSectionByName(String secName){
		
		for (int i = 0; i < sections.size(); i++)
		{
			String newSecString = sections.get(i).getName();
			
			if(newSecString.equalsIgnoreCase(secName))
			{
				Section found = sections.get(i);
				return found;
				
			}
			else if (!newSecString.equalsIgnoreCase(secName))
			{
				
			}
		}//end for loop
		return null;
	}//end getSectionByName
	
	public List<Section> getSectionsByCourseId(String crsId){
		
		ArrayList<Section> courseSections = new ArrayList<Section>();
		for (int i = 0; i < sections.size(); i++)
		{
			Section newSec = sections.get(i);
			Course secCourse = newSec.getCourse();
			
			if((secCourse.toString()).equalsIgnoreCase(crsId))
			{
				courseSections.add(newSec);
			}
			else if(!(secCourse.toString()).equalsIgnoreCase(crsId))
			{
				
			}
		}//end for loop
		return Collections.unmodifiableList(courseSections);
		
	}//end getSectionsByCourseId
	
	public List<String> getSectionNamesByCourseId(String crsId){
		
		ArrayList<String> crsSectionsStrings = new ArrayList<String>();
		for (int i = 0; i < sections.size(); i++)
		{
			Section newSec = sections.get(i);
			Course secCourse = newSec.getCourse();
			
			if((secCourse.toString()).equalsIgnoreCase(crsId))
			{
				crsSectionsStrings.add((newSec.toString()));
			}
			else if(!(secCourse.toString()).equalsIgnoreCase(crsId))
			{
				
			}
		}//end for loop
		return Collections.unmodifiableList(crsSectionsStrings);
	}
}
