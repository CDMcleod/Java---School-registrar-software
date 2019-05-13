package mcleod.programfinal.view;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mcleod.programfinal.model.Catalog;
import mcleod.programfinal.model.College;
import mcleod.programfinal.model.Course;
import mcleod.programfinal.model.Major;
import mcleod.programfinal.model.Student;

public class SearchUI implements EventHandler<ActionEvent> {

	Stage theStage;
	Button searchBtn;
	TextField idOne, fName, lName;
	RadioButton id, name;
	ToggleGroup group;
	final TextArea instructions;
	final String direct1, direct2;
	private StudInfoUI sInfoUI;
	private AddStudentUI addSUI;
	
	public SearchUI(Stage primaryStage) {
		
		theStage = new Stage();
		GridPane pane = new GridPane();
		Scene studSearch = new Scene(pane);
		theStage.setScene(studSearch);
		theStage.setTitle("-Student Search-");
		
		id = new RadioButton("Search ID");
		name = new RadioButton("Search Name");
		group = new ToggleGroup();
		Label studId = new Label("Student ID:");
		Label studFName = new Label("First Name:");
		Label studLName = new Label("Last Name:");
		idOne = new TextField();
		fName = new TextField();
		lName = new TextField();
		searchBtn = new Button("Search");
		
		id.setToggleGroup(group);
		name.setToggleGroup(group);
		id.setSelected(true);
		
		direct1 = "Please select whether to search with ID number or first and last name. Then"
				+ " fill in the empty fields under your search option and click the 'Search' Button to proceed.\n";
		direct2 = "Now that the Student has been Added, simply click the 'Search' Button to proceed.";
		
		instructions = new TextArea();
		instructions.clear();
		instructions.setPrefRowCount(6);
		instructions.setPrefColumnCount(80);
		instructions.setWrapText(true);
		instructions.setPrefWidth(220);
		instructions.setEditable(false);
		instructions.setText(direct1);
		
		pane.add(instructions, 0, 1);
		pane.add(id, 0,3);
		pane.add(name, 2,3);
		pane.add(studId, 0,5);
		pane.add(idOne, 1,5);
		pane.add(studFName, 2,5);
		pane.add(studLName, 2,6);
		pane.add(fName, 3,5);
		pane.add(lName, 3,6);
		pane.add(searchBtn, 3, 8);
		
		searchBtn.setOnAction(this);
		
		pane.setHgap(10);
		pane.setVgap(5);
		
	}
	
	
	
	
	public void handle(ActionEvent event) {
	
		Button button = (Button)(event.getSource()); 
		if (button == searchBtn) {
			if(id.isSelected()){//searching by ID
				
				String newId = idOne.getText();
				if (newId.isEmpty()){
					
					Alert alert = new Alert(AlertType.ERROR, "Please fill in the blank fields under 'Search ID'.");
					alert.show();
				}
				else if(!newId.isEmpty()){
					try {
						int studentId = Integer.parseInt(newId);
						Student S = College.getCollege().getStudentById(studentId);
						if (College.getCollege().getStudentById(studentId) != null){
								sInfoUI = new StudInfoUI(S);
							//List<Course> coursesList = S.getMajor().getRequiredCourses();
							List<String> registeredList = S.getRegisteredSections();
							ArrayList<String> cList = new ArrayList<String>();
							for(String crs : registeredList) {
								cList.add(crs);
							}
							sInfoUI.show(S);
						}
						else{
							Alert alert3x3 = new Alert(AlertType.ERROR, "Unable to process request: Student information not found for student ID.");
							alert3x3.show();
						}

						}
					catch (Exception e){
						Alert alert3x3 = new Alert(AlertType.ERROR, "Unable to process request: Student information not found.");
						alert3x3.show();
				
						}
					
				}//end else-if
			}//end search by id
			else if(name.isSelected()){//searching by name
				String firstName = fName.getText();
				String lastName = lName.getText();
				if (firstName.isEmpty() || lastName.isEmpty()){
					
					Alert alert = new Alert(AlertType.ERROR, "Please fill in the blank fields under 'Search Name'.");
					alert.show();
				}
				else if(!firstName.isEmpty() && !lastName.isEmpty()){
					try {
						Student S = College.getCollege().getStudentByName(firstName, lastName);
						if (College.getCollege().getStudentByName(firstName, lastName) != null){
								sInfoUI = new StudInfoUI(S);
							//List<Course> coursesList = S.getMajor().getRequiredCourses();
							List<String> registeredList = S.getRegisteredSections();
							ArrayList<String> cList = new ArrayList<String>();
							for(String crs : registeredList) {
								cList.add(crs);
							}
							sInfoUI.show(S);
							instructions.clear();
							instructions.setText(direct1);
						}
						else if(College.getCollege().getStudentByName(firstName, lastName) == null){
							Alert alert = new Alert(AlertType.CONFIRMATION, "Student " + firstName + ", " + lastName + " not found. Would you like"
									+ " to add student to registry?", ButtonType.YES, ButtonType.NO);
							alert.showAndWait();
							if (alert.getResult() == ButtonType.YES){
								Student newStud = new Student(firstName, lastName);
								addSUI = new AddStudentUI(newStud);
								addSUI.show();
								
								instructions.clear();
								instructions.setText(direct2);
							}
							
						}

						}
					catch (Exception e){
						Alert alert3x3 = new Alert(AlertType.ERROR, "Unable to process request: Student information not found.");
						alert3x3.show();
				
						}
				}//end else-if
			}//end search by name
			
		}
		
	}
	
	public void show()
	{
		if (!theStage.isShowing()) {
			idOne.setText("");
			theStage.show();
		} 
		else {
			theStage.toFront();
		}

	}

}
