package hrm.view;

import java.io.IOException;

import hrm.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HrmOptionsController {
	
	//Load Department Creation UI
	@FXML
	private void btnAddDepartment(){
		addDialog("view/CreateDepartment.fxml");
	}
	
	//Load Employee Management UI
	@FXML
	private void btnEmployeeManagement(){
		addDialog("view/EmployeeManagement.fxml");
	}
	@FXML 
	Button btnClose;
	@FXML
	private void sceneClose() {
		    Stage stage = (Stage) btnClose.getScene().getWindow();
		    stage.close();
		}
	
	//Local Function for Loading a new Dialog Dialog without closing the initial window.
	//String Parameter needs to be the path to the desirable .FXML
	private void addDialog(String s){
		try{
	 		FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(MainApp.class.getResource(s));
		    BorderPane addNewDepartment = loader.load();
			Stage addDialogStage = new Stage();
			addDialogStage.setTitle("Create New Department");
			addDialogStage.initModality(Modality.WINDOW_MODAL);
			addDialogStage.initOwner(MainApp.primaryStage);
			Scene scene = new Scene(addNewDepartment);
			addDialogStage.setScene(scene);
			addDialogStage.setResizable(false);
			addDialogStage.showAndWait();
			}catch (IOException e) {
			    e.printStackTrace();}
		}

	}



