package hrm.view;

import hrm.model.MailGenerator;
import hrm.model.Manager;
import hrm.model.MysqlConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import hrm.MainApp;

public class CreateDepartmentController {
	
	private ObservableList<String> departmentChoice = LoginController.getDepartmentChoice();
	
	private Manager newManager;
	@FXML
	TextField txtDepartName = new TextField();
	@FXML
	public TextField txtUniqueId = new TextField();
	@FXML
	 public TextField txtFirstName = new TextField();
	@FXML
	TextField txtLastName = new TextField();
	@FXML
	TextField txtWage = new TextField();
	@FXML
	TextField txtWageCategory = new TextField();
	@FXML
	TextField txtEmail = new TextField();
	@FXML
	TextField txtPhoneNum = new TextField();
	@FXML
	TextField txtAddress = new TextField();
	@FXML
	Button btnGenerateId = new Button();
	@FXML 
	Button btnCancel;
	@FXML
	private void sceneClose(ActionEvent event) {
		    Stage stage = (Stage) btnCancel.getScene().getWindow();
		    stage.close();
		}
	@FXML
	public void initialize(){
		txtUniqueId.setDisable(true);
		btnGenerateId.setDisable(true);
	}
	
	@FXML
	public boolean allowGenerator(){
		if(txtDepartName.getText().length() < 2){
			btnGenerateId.setDisable(true);
			//System.out.println("einai true");
			return true;
		}else{
			//System.out.println("einai false");
			btnGenerateId.setDisable(false);
			return false;
		}
	}
	
	@FXML
	private void genUniqueId(){
		System.out.println(Manager.getUniqueId());
		String uniqCode = txtDepartName.getText()+"-"+Manager.getUniqueId();
		txtUniqueId.setText(uniqCode);
		}
	
	@FXML
	private void creatNewDepartment() throws ClassNotFoundException, SQLException{
		if(txtFirstName.getText().equals("")==true || txtLastName.getText().equals("") || txtWage.getText().equals("")
				|| txtWageCategory.getText().equals("") || txtEmail.getText().equals("")
					|| txtPhoneNum.getText().equals("") || txtAddress.getText().equals(""))
		{
			MainApp.messageBox("Error 69","No Fields Should Be Left Empty!");
		}else{
			newManager = new Manager(txtFirstName.getText(),txtLastName.getText(),Double.parseDouble(txtWage.getText()),
					txtWageCategory.getText(),txtEmail.getText(),txtDepartName.getText(),txtPhoneNum.getText(),txtAddress.getText());

		 storeNewDepart();	
		}
	}
	
	public int storeNewDepart()throws ClassNotFoundException, SQLException{
    	String query="select DISTINCT departmentID from manager_acc";
    	Statement myStatm = (Statement) MysqlConnector.getConnection().createStatement();
	 	ResultSet myRs = myStatm.executeQuery(query);
	 	int i=0;
	 	boolean store=true;
	 	while(myRs.next()){
	 		//System.out.println(departmentChoice.get(i));
	 		if(departmentChoice.get(i) == myRs.getString("departmentID")){
	 		MainApp.messageBox("Error 31","Department Allready Exists!");
	 		store=false;
	 		}
	 		}i++;			
	 		
	 	if(store==true){
	 		String newPass=generatePass();
	 		String newUser=this.newManager.getLastName()+this.newManager.getDepartment();
	 		MailGenerator mail = new MailGenerator(newManager,newUser,newPass);
	 		mail.autoMail();
	 		String query2="INSERT into manager_acc values('"+newManager.getDepartment()+"','"+this.newManager.getFirstName()+"Mng"
	 														+"','"+generatePass()+"','"+txtDepartName.getText()+"')";
	    	myStatm.executeUpdate(query2);
	    	
	    	String query3="INSERT into employees (firstName,lastName,wage,wageCategory,mail,phone,address,depID) "
	    			+ "values('"+this.newManager.getFirstName()+"','"+this.newManager.getLastName()+"','"
	    			+this.newManager.getWage()+"','"+this.newManager.getWageCategory()+"','"+this.newManager.getMail()+"','"
	    			+this.newManager.getPhone()+"','"+this.newManager.getAddress()+"','"+this.newManager.getDepartment()+"')";
	    	myStatm.executeUpdate(query3);
	    	MainApp.messageBox("Success", "New Department has been created.The Manager has been assigned."
	    									+ "His account info have been succesfully send on his/her e-mail!");
	    	clearAll();
	
	 	}
		return i;  
	 	
}
	public void clearAll(){
		txtFirstName.setText("");
		txtLastName.setText("");
		txtWage.setText("");
		txtWageCategory.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		txtUniqueId.setText("");
		txtDepartName.setText("");
		
	}


	
	public String generatePass(){
			  SecureRandom random = new SecureRandom();
		    return new BigInteger(40, random).toString(32);  
	}
	
}