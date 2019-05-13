package mcleod.programfinal.view;

import javafx.application.Application;
import javafx.stage.Stage;
import mcleod.programfinal.model.Catalog;
import mcleod.programfinal.model.College;
import mcleod.programfinal.model.Major;
import mcleod.programfinal.model.TermSchedule;
import mcleod.programfinal.view.SearchUI;

public class MainClass extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		SearchUI menu = new SearchUI(primaryStage);
		menu.show();
	}
	
	public static void main(String[] args) {

		Catalog myCatalog = new Catalog();
		College c = College.getCollege();
		TermSchedule t = TermSchedule.getSchedule();
		
		myCatalog.loadCourses();
		myCatalog.loadMajors();
		c.loadStudents();
		t.loadSections();
		
		for (Major mjr : myCatalog.getMajorList())
        {
			for (String course : mjr.invalidCourses)
			{
				System.err.println("!ERROR!: Could not find course: '" + course + "'");
			}
        }
		
		Application.launch(args);
	}

}
