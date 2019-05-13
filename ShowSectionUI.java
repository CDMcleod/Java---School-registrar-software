package mcleod.programfinal.view;

import java.util.ArrayList;
import java.util.Formatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mcleod.programfinal.model.Section;
import mcleod.programfinal.model.Student;
import mcleod.programfinal.model.TermSchedule;

public class ShowSectionUI implements EventHandler<ActionEvent>{

	TermSchedule t = TermSchedule.getSchedule();
	Stage theStage;
	Button exitBtn, upCapBtn;
	private ListView<String> sectionInfoLV = new ListView<String>();
	public ArrayList<String> enrolledListString = new ArrayList<String>();
	
	public ShowSectionUI(Section sec){
	
		//Student stud = student;
		theStage = new Stage();
		GridPane pane = new GridPane();
		Scene secInfo = new Scene(pane);
		theStage.setScene(secInfo);
		theStage.setTitle("-Section Information-");
		ArrayList<Student> enrolledList = new ArrayList<Student>(sec.getClassRoll());
		for(Student stu : enrolledList) {
			enrolledListString.add(stu.getFirstName().toString() + " " + stu.getLastName().toString());
		}
		ObservableList<String> newEnrolledList = FXCollections.observableArrayList(enrolledListString);
		sectionInfoLV.setItems(newEnrolledList);
		
		Label secName = new Label("Section Name: " + sec.getName());
		Label seatsLeft = new Label("Seats Available: " + sec.getAvailableSeats());
		Label direct = new Label("Students Currently Enrolled:");
		upCapBtn = new Button("Raise Capacity");
		exitBtn = new Button("Close");
		
		pane.add(secName, 0 , 0);
		pane.add(seatsLeft, 0 , 1);
		pane.add(direct, 2 , 2);
		pane.add(sectionInfoLV, 2 , 3);
		pane.add(upCapBtn, 3 , 5);
		pane.add(exitBtn, 4 , 5);
		
		upCapBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
			
				sec.setOverride();
				//String newClassSize = Integer.toString(sec.getMaxCapacity());
				Formatter sf = new Formatter();
				String message = sf.format("The Maximum class size for section %s has been increased to %s", sec.getName(), sec.getMaxCapacity()).toString();
				Alert alert = new Alert(AlertType.INFORMATION, message);
				alert.show();
				
			}
		});
		exitBtn.setOnAction(this);
		
		pane.setHgap(10);
		pane.setVgap(5);
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		
		theStage.close();
	}

	public void show(Section foundSec) {

		theStage.show();
	}

}
