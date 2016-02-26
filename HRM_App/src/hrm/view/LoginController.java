package hrm.view;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import hrm.MainApp;
import hrm.model.MysqlConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class LoginController {
	private MainApp mainApp;
	private static final int maxLength = 15;
	private String lastUserText;
	private String lastPassText;
	private static String departmentUsed;
	private static ObservableList<String> departmentChoice = FXCollections.observableArrayList();
	
	@FXML
	TableView tableEmployee;
	@FXML
	public ChoiceBox<String> cboxDepartment;
	@FXML
	private AnchorPane loginAnchor;
	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private TextField txtLabel;
	@FXML
	private Label lblMessage;
	
	//Initializing Object Parameters the momment they are being called.
	@FXML
	private void initialize() throws ClassNotFoundException, SQLException{
		txtLabel.setDisable(true);
		fillChoiceBox();
		
	}
	
	
	//Method called by EACH key input of textfield and passwordfield.
	@FXML
	private void userChecker(KeyEvent e){
		restrictLength(e,"text");

	}
	
	@FXML
	private void passChecker(KeyEvent e){
		restrictLength(e,"pass");
		
	}
	
	//Method that counts and Restricts Password or User be over 15 characters.
	//Depending on who was the object that called the last method follows a diffirent path.
	private void restrictLength(KeyEvent e ,String type) {
		String getInput;
		if(type=="text"){
			getInput = txtUser.getText();		
			if(getInput.length() < (maxLength)) {
				lastUserText = getInput;
				lblMessage.setText(" ");
			}else{
				txtUser.setText(lastUserText);
				txtUser.positionCaret(maxLength);
				lblMessage.setText("Not over 15 characters are allowed.");
			}
		}else{
			getInput = txtPassword.getText();
			if(getInput.length() < maxLength) {
				lastPassText = getInput;
				lblMessage.setText(" ");
			}else{
				txtPassword.setText(lastPassText);
				txtPassword.positionCaret(maxLength);
				lblMessage.setText("Not over 15 characters are allowed.");
			}
		}
		

	}

	//Method for controlling the login button.Upon Action checks the 3 desired fields for empty or not
	//If they are empty a new Error window pop-ups.
	//Otherwise we move on to our options Depending on who logged in.
	@FXML
	public void btnLoginAction() throws ClassNotFoundException, SQLException{
    	try {
    		String userName = txtUser.getText();//.replaceAll("\\W","");
    		String pass = txtPassword.getText();//replaceAll("\\W","");
    		String depart = cboxDepartment.getValue();
    		departmentUsed = depart;
    		if(depart != null) { depart = depart.trim(); }	
    		
			//	System.out.println("User:"+userName+"Pass:"+pass+"depart:"+depart);
    		if(userName.equals("")==true || pass.equals("")==true || depart==null){
    			MainApp.messageBox("Error 404","Empty Field","No Field Should Be Left Empty!");
    		}
    		else{
    			boolean login = getDbManagers(userName,pass,depart);
    			System.out.println(login+"MALISTA");
    			if(login==true){
    			// Load HRM Options
    			FXMLLoader loader = new FXMLLoader();
    			if(depart.equals("HRM")){
    				//System.out.println(depart);
    				loader.setLocation(MainApp.class.getResource("view/HrmOptions.fxml"));
    			}else{
    				//System.out.println(depart);
    				loader.setLocation(MainApp.class.getResource("view/OtherManagerOptions.fxml"));
    			}
    			
    			AnchorPane personOverview = (AnchorPane) loader.load();
    			MainApp.rootLayout.setCenter(personOverview);
    			// Set HRM Options into the center of root layout.
    			}
    		
    			else{
    				MainApp.messageBox("Error 102","Wrong Input",
    						"UserName And/Or Password Missmatch with Department Manager!");
    			
    			}
    		}
  } catch (IOException e) {
    e.printStackTrace();}
		
	} 
	
		//Empty Constructor for use and communication with MainApp
        public LoginController(){
  
        }
        
        //Loading all the Managers existing in our database to check for login User/Pass.
        public boolean getDbManagers(String userName,String pass,String depart) 
        										throws SQLException, ClassNotFoundException{
        	boolean login = false;
    	 	String query="select * from manager_acc where departmentID='"+depart+"'";
    	 	Statement myStatm = (Statement) MysqlConnector.getConnection().createStatement();
    	 	ResultSet myRs = myStatm.executeQuery(query);
    	 	while(myRs.next()){
    	 		//System.out.println(myRs.getString("user")+" "+myRs.getString("pass"));
    	 		if(myRs.getString("user").equals(userName) && myRs.getString("pass").equals(pass)){
    	 			login=true;
    	 		}
    	 		else{
    	 			login=false;
    	 		}
    	 	} 	
		return login; 	
        }
        
        //Method to initialize Choice Box options based on how many departments exist.
        public void fillChoiceBox() throws ClassNotFoundException, SQLException{
        	String query="select DISTINCT departmentID from manager_acc";
        	Statement myStatm = (Statement) MysqlConnector.getConnection().createStatement();
    	 	ResultSet myRs = myStatm.executeQuery(query);
    	 	while(myRs.next()){
    	 		//System.out.println(myRs.getString("departmentID"));
    	 		departmentChoice.add(myRs.getString("departmentID"));
    	 	}
    	 	cboxDepartment.setItems(departmentChoice);
        }
        
    	public static ObservableList<String> getDepartmentChoice() {
    		return departmentChoice;
    	}
        
        public static String getDepartmentUsed(){
        	return departmentUsed;
        }
        
        public void setMainApp(MainApp mainApp) {
        	this.mainApp = mainApp;
        }
}