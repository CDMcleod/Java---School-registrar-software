package mcleod.programfinal.view;

import java.util.Formatter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mcleod.programfinal.model.Catalog;
import mcleod.programfinal.model.College;
import mcleod.programfinal.model.Student;
import mcleod.programfinal.model.Major;

public class AddStudentUI implements EventHandler<ActionEvent> {

	Catalog myCatalog = new Catalog();
	Stage theStage;
	TextField name1;
	TextField name2;
	TextField nameM;
	Button saveBtn;
	Button cancelBtn;
	
	public AddStudentUI(Student student){
		
		theStage = new Stage();
		GridPane pane = new GridPane();
		Scene addStud = new Scene(pane);
		theStage.setScene(addStud);
		theStage.setTitle("-Add Student-");
		
		Label direct = new Label("Please fill in all fields below.");
		Label fName = new Label("First Name:");
		Label lName = new Label("Last Name:");
		Label major = new Label("Major:");
		name1 = new TextField(student.getFirstName().toString());
		name2 = new TextField(student.getLastName().toString());
		nameM = new TextField();
		saveBtn = new Button("Save");
		cancelBtn = new Button("Cancel");
		
		pane.add(direct, 0, 0);
		pane.add(fName,0,2);
		pane.add(name1,1,2);
		pane.add(lName,0,3);
		pane.add(name2,1,3);
		pane.add(major,0,4);
		pane.add(nameM,1,4);
		pane.add(saveBtn, 4, 7);
		pane.add(cancelBtn, 5, 7);
		
		saveBtn.setOnAction(this);
		cancelBtn.setOnAction(this);
		
		pane.setHgap(10);
		pane.setVgap(5);
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		Button button = (Button)(event.getSource()); 
		if (button == saveBtn) {
			String firstName = name1.getText();
			String lastName = name2.getText();
			String majorName = nameM.getText();
			if (firstName.isEmpty() || lastName.isEmpty() || majorName.isEmpty()){
				
				Alert alert = new Alert(AlertType.ERROR, "Please complete all three fields.");
				alert.show();
			}//
			else if(!firstName.isEmpty() && !lastName.isEmpty() && !majorName.isEmpty()){
				try {
					myCatalog.loadMajors();
					Student newS = College.getCollege().addStudent(firstName, lastName);
					newS.setMajor(majorName);
					if (newS.getMajor() != null){
					Formatter sf = new Formatter();
					String message = sf.format("The Student %s %s has been added.", newS.getFirstName(), newS.getLastName()).toString();
					Alert alert = new Alert(AlertType.INFORMATION, message);
					alert.show();
					}
					else if(newS.getMajor() == null) {
						Alert alert = new Alert(AlertType.ERROR, "!ERROR! : The desired major was not found. Student " + newS.getFirstName() + ", " + newS.getLastName() + 
								" has been added without a major.");
						alert.show();
					}//end else-if
					theStage.hide();
					
					}
				catch (Exception e){
					Alert alert = new Alert(AlertType.ERROR, "!ERROR! : The desired major not found.");
					alert.show();
			
					}//end try catch
			}//end inner if-else
		}//end outer if
		else if(button == cancelBtn){
			theStage.hide();
			
		}
	}
	
	public void show()
	{
		if (!theStage.isShowing()) {
			nameM.setText("");
			theStage.showAndWait();
		} 
		else {
			theStage.toFront();
		}

	}

	
}
