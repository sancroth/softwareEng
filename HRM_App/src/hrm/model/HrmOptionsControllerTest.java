package hrm.model;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.sql.SQLException;

import hrm.MainApp;
import hrm.view.CreateDepartmentController;
import hrm.view.LoginController;


import org.junit.Test;

import com.mysql.jdbc.Statement;
import com.sun.glass.ui.Application;
import com.sun.glass.ui.CommonDialogs.ExtensionFilter;
import com.sun.glass.ui.CommonDialogs.FileChooserResult;
import com.sun.glass.ui.Cursor;
import com.sun.glass.ui.Pixels;
import com.sun.glass.ui.Robot;
import com.sun.glass.ui.Screen;
import com.sun.glass.ui.Size;
import com.sun.glass.ui.Timer;
import com.sun.glass.ui.View;
import com.sun.glass.ui.Window;


public class HrmOptionsControllerTest{



	@Test
	public void testLoginController()  throws ClassNotFoundException, SQLException, IOException {
		LoginController login = new LoginController();
	 assertEquals("kappa",true,login.getDbManagers("Sancroth","softEng","HRM"));
	 
	 	Manager testM = new Manager("Nikos","Senounta",900,"Low","sancroth@gmail.com","kappa","opl","Solonos");
	 	assertEquals("manager","Nikos",testM.getFirstName());
	 	assertNotEquals("Crypto","HRM-123321",Manager.getUniqueId());
	 	testM.setLastName("Preze");
	 	assertEquals("Manager Name Swap","Preze",testM.getLastName());
	 	
	 	MailGenerator mail = new MailGenerator(testM, "jntou","jntou2");
	 	assertEquals("mail",testM,mail.getManager());
	 	
	}
	
	

}

	
