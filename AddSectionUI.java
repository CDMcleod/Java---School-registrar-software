package mcleod.programfinal.view;

import java.util.ArrayList;
import java.util.Formatter;

import javafx.collections.FXCollections;
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
import mcleod.programfinal.model.College;
import mcleod.programfinal.model.Student;

public class AddSectionUI implements EventHandler<ActionEvent>{

	College c = College.getCollege();
	Stage theStage;
	Button okBtn;
	Button cancelBtn;
	private ListView<String> sectionLV = new ListView<String>();
	private String selectedSec;
	private StudInfoUI sInfoUI;
	
	
	public AddSectionUI(Student student){
		
		theStage = new Stage();
		GridPane pane = new GridPane();
		Scene secSelect = new Scene(pane);
		theStage.setScene(secSelect);
		theStage.setTitle("Add Course Selection");
		
		Label direct = new Label("Please select a section, then ");
		Label direct2 = new Label("click 'Ok' to add:");
		okBtn = new Button("Ok");
		cancelBtn = new Button("Cancel");
		
		pane.add(direct, 0, 0);
		pane.add(direct2, 0, 1);
		pane.add(okBtn, 1, 3);
		pane.add(cancelBtn, 2, 3);
		pane.add(sectionLV, 0, 2);
		
		sectionLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		cancelBtn.setOnAction(this);
		okBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				selectedSec = sectionLV.getSelectionModel().getSelectedItem();
				
				if(student.addStudentSection(selectedSec) == true)
				{
					Formatter sf = new Formatter();
					String message = sf.format("The Student %s %s has been added to the selected class.", student.getFirstName(), student.getLastName()).toString();
					Alert alert = new Alert(AlertType.INFORMATION, message);
					alert.show();
				}
				else if(student.addStudentSection(selectedSec) != true)
				{
					Alert alert = new Alert(AlertType.ERROR, "!ERROR! : Student was unable to be added to the selected class.");
					alert.show();
				}
					
				//sInfoUI.
				theStage.hide();
				
				
			}
		});
		
		
		pane.setHgap(10);
		pane.setVgap(5);
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		
		//sInfoUI.show(Student);
		theStage.close();
		
	}
	
	public void show(ArrayList<String> newList) {
		if (!theStage.isShowing()) {
			sectionLV.setItems(FXCollections.observableList(newList));
			theStage.showAndWait();
		} 
		else {
			theStage.toFront();
		}

	}
	
	public String getSelectedSec() {
		
		return selectedSec;
	}
}
