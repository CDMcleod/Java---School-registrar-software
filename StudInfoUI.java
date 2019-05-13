package mcleod.programfinal.view;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mcleod.programfinal.model.Catalog;
import mcleod.programfinal.model.College;
import mcleod.programfinal.model.Course;
import mcleod.programfinal.model.Section;
import mcleod.programfinal.model.Student;
import mcleod.programfinal.model.TermSchedule;

public class StudInfoUI implements EventHandler<ActionEvent>{

	//College c = College.getCollege();
	TermSchedule t = TermSchedule.getSchedule();
	Stage theStage;
	Button addBtn;
	Button closeBtn;
	Button showSecBtn;
	Label mName;
	final TextArea instructions;
	final String direct;
	private ListView<String> coursesLV;
	private AddSectionUI addSecUI;
	private ShowSectionUI showSecUI;
	private String selectedSec;
	
	public StudInfoUI(Student student) {
		
		Student stud = student;
		theStage = new Stage();
		GridPane pane = new GridPane();
		Scene studInfo = new Scene(pane);
		theStage.setScene(studInfo);
		theStage.setTitle("-Student Information-");
		coursesLV = new ListView<String>(FXCollections.observableList(stud.getRegisteredSections()));
		
		Label fName = new Label("First Name: " + student.getFirstName());
		Label lName = new Label("Last Name: " + student.getLastName());
		if (student.getMajor() != null){
			mName = new Label("Student's Major: " + student.getMajor().getMajorName());
		}
		else /*if (student.getMajor() == null)*/{
			mName = new Label("Student's Major: N/A ");
		}
		addBtn = new Button("Add Section");
		closeBtn = new Button("Exit");
		showSecBtn = new Button("Show Section");
		
		direct = "Click 'Add Section' to add a new course. Select a course in the list and click"
				+ " 'Show Section' to view the selected courses' information.";
		
		instructions = new TextArea();
		instructions.clear();
		instructions.setPrefRowCount(5);
		instructions.setPrefColumnCount(88);
		instructions.setWrapText(true);
		instructions.setPrefWidth(185);
		instructions.setEditable(false);
		instructions.setText(direct);
		
		pane.add(instructions, 0, 0);
		
		pane.add(fName, 2 , 1);
		pane.add(lName, 2 , 2);
		pane.add(mName, 2 , 3);
		pane.add(coursesLV, 2, 4);
		pane.add(addBtn, 4, 7);
		pane.add(showSecBtn, 5, 7);
		pane.add(closeBtn, 6, 7);
		
		coursesLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (addSecUI == null)
					addSecUI = new AddSectionUI(stud);
				List<String> sectionsList = t.getSectionNames();
				ArrayList<String> sList = new ArrayList<String>();
				for(String sec : sectionsList) {
					sList.add(sec);
				}
				addSecUI.show(sList);
				String secPicked = addSecUI.getSelectedSec();
				if (secPicked != null)
				{
					coursesLV.setItems(FXCollections.observableList(stud.getRegisteredSections()));
					theStage.show();
				}
			}
		});
		
		showSecBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
			
				selectedSec = coursesLV.getSelectionModel().getSelectedItem();
				if(selectedSec == null)
				{
					Alert alert = new Alert(AlertType.ERROR, "!ERROR! : Please select a section from the student's list of courses.");
					alert.show();
				}
				else
				{
				Section foundSec = t.getSectionByName(selectedSec);
				showSecUI = new ShowSectionUI(foundSec);
				showSecUI.show(foundSec);
				}
				
			}
		});
		
		closeBtn.setOnAction(this);
		
		pane.setHgap(10);
		pane.setVgap(5);
	}
	
	
	@Override
	public void handle(ActionEvent event) {

			theStage.close();
	}
	
	public void show(Student S)
	{
		if (!theStage.isShowing()) {
			coursesLV.setItems(FXCollections.observableList(S.getRegisteredSections()));
			theStage.showAndWait();
			coursesLV.setItems(FXCollections.observableList(S.getRegisteredSections()));
		} 
		else if(theStage.isShowing()){
			coursesLV.setItems(FXCollections.observableList(S.getRegisteredSections()));
			theStage.toFront();
		}

	}

}
