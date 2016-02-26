package hrm.view;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import hrm.MainApp;
import hrm.model.Employee;
import hrm.model.MysqlConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage; 
import javafx.scene.control.TableView;   

public class EmployeeManagementController {
	ObservableList<Employee> data = FXCollections.observableArrayList(); 
	private Employee newEmployee;
	@FXML
	private TableColumn<Employee, String> colEmployee;
	@FXML
	private TableColumn<Employee, String> colLastName;
	@FXML
	TableView<String> tableEmployee;
	@FXML
	ChoiceBox<String> choicePro;
	@FXML
	ChoiceBox<String> choiceOrg;
	@FXML
	ChoiceBox<String> choiceCr;
	@FXML
	ChoiceBox<String> choiceTeam;
	@FXML
	ChoiceBox<String> choiceCom;
	@FXML
	BorderPane kappa;
	private ObservableList<String> combo = FXCollections.observableArrayList("1","2","3","4","5");
	@FXML
	TextField txtDepartName;
	@FXML
	 public TextField txtFirstName;
	@FXML
	TextField txtLastName;
	@FXML
	TextField txtWage;
	@FXML
	TextField txtWageCategory;
	@FXML
	TextField txtEmail;
	@FXML
	TextField txtPhoneNum;
	@FXML
	TextField txtAddress;
	@FXML
	TableView<Employee> employeeTable;
	@FXML
	private void initialize() throws ClassNotFoundException, SQLException, IOException{
		choicePro.setItems(combo);
		choiceOrg.setItems(combo);
		choiceCr.setItems(combo);
		choiceCom.setItems(combo);
		choiceTeam.setItems(combo);
		txtDepartName.setText(LoginController.getDepartmentUsed());
		txtDepartName.setDisable(true);
		loadEmployees();
		employeeTable.setItems(data);
		colEmployee.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		colLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	}
	@FXML 
	Button btnCancel;
	
	//Button Cancel
	@FXML
	private void sceneClose(ActionEvent event) {
		    Stage stage = (Stage) kappa.getScene().getWindow();
		    stage.close();
	}
	//Upon Pressing submit Checks for empty Fields.If none calls storeNewEmployee.
	@FXML
	private void createNewEmployee() throws ClassNotFoundException, SQLException{
		System.out.println("A");
		if(txtFirstName.getText().equals("")==true || txtLastName.getText().equals("") || txtWage.getText().equals("")
				|| txtWageCategory.getText().equals("") || txtEmail.getText().equals("")
					|| txtPhoneNum.getText().equals("") || txtAddress.getText().equals(""))
		{
			MainApp.messageBox("Error 69","No Fields Should Be Left Empty!");
		}else{
		newEmployee = new Employee(txtFirstName.getText(),txtLastName.getText(),Double.parseDouble(txtWage.getText()),
					txtWageCategory.getText(),txtEmail.getText(),txtDepartName.getText(),txtPhoneNum.getText(),txtAddress.getText());
		storeNewEmployee();	
		}
		 
	}
	
	private void storeNewEmployee() throws ClassNotFoundException, SQLException{
	Statement myStatm = (Statement) MysqlConnector.getConnection().createStatement();
	String query="INSERT into employees (firstName,lastName,wage,wageCategory,mail,phone,address,depID) "
			+ "values('"+this.newEmployee.getFirstName()+"','"+this.newEmployee.getLastName()+"','"
			+this.newEmployee.getWage()+"','"+this.newEmployee.getWageCategory()+"','"+this.newEmployee.getMail()+"','"
			+this.newEmployee.getPhone()+"','"+this.newEmployee.getAddress()+"','"+this.newEmployee.getDepartment()+"')";
	myStatm.executeUpdate(query);
	MainApp.messageBox("Success", "New Employee Registered!");
	clearAll();
	}
	
	//Clear all textfields after submiting a new employee.
	public void clearAll(){
		txtFirstName.setText("");
		txtLastName.setText("");
		txtWage.setText("");
		txtWageCategory.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		txtPhoneNum.setText("");
		txtDepartName.setText("");	
	}
	 
	private void loadEmployees() throws ClassNotFoundException, SQLException, IOException{  

		   
	     String query = "SELECT * from employees";
	  	  Statement myStatm = (Statement) MysqlConnector.getConnection().createStatement();
	      //ResultSet 
	  	  ResultSet myRs = myStatm.executeQuery(query);
	  	  
	  	  while(myRs.next()) { 
	  		 data.add(new Employee(myRs.getString("firstName"),myRs.getString("lastName"),myRs.getDouble("wage"),
	  				 		myRs.getString("wageCategory"),myRs.getString("mail"),myRs.getString("depID"),
	  				 			myRs.getString("phone"),myRs.getString("address")));
	       } 
	  	  
	  		  
	  	 }
	}