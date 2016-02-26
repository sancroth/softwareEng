package hrm.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
/**
 * Model class for a Person.
 */
public class Employee {
	/*
	 * StringProperty and DoubleProperty are used instead of standard Netbeans Primitive
	 * types.It is done so we can use binding techniques of objects for later updates to 
	 * create graphs,statistics etc.
	 */
    private  StringProperty firstName;
    private  StringProperty lastName;
    private  DoubleProperty wage;
    private  StringProperty wageCategory ;
    private  StringProperty mail;
    private  StringProperty department;
    private  StringProperty phone;
    private  StringProperty address;

    /**
     * Test constructor.
     */
    public Employee() {
    }
	 /*
	 **/

    public Employee(
    		String firstName,
    		String lastName ,
    		double wage,
    		String wageCategory,
    		String mail,
    		String department,
    		String phone,
    		String address) {
    	//default constructor
    			this.firstName = new SimpleStringProperty(firstName);
    			this.lastName = new SimpleStringProperty(lastName);
    			this.wage = new SimpleDoubleProperty(wage);
    			this.wageCategory = new SimpleStringProperty(wageCategory);
    			this.mail = new SimpleStringProperty(mail);
    			this.department = new SimpleStringProperty(department);
    			this.phone = new SimpleStringProperty(phone);
    			this.address = new SimpleStringProperty(address);
    }

    
    //Getters for values and whole object and Setters for value//
    
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getWageCategory() {
        return wageCategory.get();
    }

    public void setWageCategory(String wageCategory) {
        this.wageCategory.set(wageCategory);
    }

    public StringProperty wageCategoryProperty() {
        return wageCategory;
    }


    public void setWage(double wage) {
        this.wage.set(wage);
    }
    
    public Double getWage() {
    	return wage.get();
    }

    public DoubleProperty wageProperty() {
        return wage;
    }

   public StringProperty mailProperty() {
	   return mail;
   }
   
   public void setMail(String mail) {
	   this.mail.set(mail);
   }
   
   public String getMail(){
	   return mail.get();
   }
   
   public StringProperty departmentProperty() {
	   return department;
   }
   
   public void setDepartment(String department) {
	   this.department.set(department);
   }
   
   public String getDepartment(){
	   return department.get();
   }
   
   
   public StringProperty phoneProperty() {
	   return phone;
   }
   
   public void setPhone(String phone) {
	   this.phone.set(phone);
   }
   
   public String getPhone(){
	   return phone.get();
   }
   
   public StringProperty addressProperty() {
	   return address;
   }
   
   public void setAdress(String address) {
	   this.address.set(address);
   }
   
   public String getAddress(){
	   return address.get();
   }
}