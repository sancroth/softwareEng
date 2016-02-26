package hrm;

import java.io.IOException;

import hrm.model.Employee;
import hrm.view.LoginController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static Stage primaryStage;
    public static BorderPane rootLayout;
    private ObservableList<Employee> personData = FXCollections.observableArrayList();
    @Override
    final public void start(final Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.setTitle("Recource Management App");
       
        initRootLayout();
        showLogin();
        
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from FXML file.
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            final Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the login overview inside the root layout.
     */
    public void showLogin() {
        try {
            // Load login overview.
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
            final AnchorPane loginView = (AnchorPane) loader.load();
            // Set person login into the center of root layout.
            rootLayout.setCenter(loginView);
            final LoginController login =(LoginController) loader.getController();
            login.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
     }
    

     public static final boolean messageBox(final String title,final String header,final String content){
			final Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(title);
			alert.setHeaderText(header);
			alert.setContentText(content);
			alert.showAndWait();
			return true;
     }
     
     public static final void messageBox(final String title,final String content){
			final Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(content);
			alert.showAndWait();
     }
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public ObservableList<Employee> getPersonData() {
        return personData;
    }
    final public static void main(final String[] args) {
    	 launch(args);
    }
}