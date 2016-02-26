package hrm.model;
import java.security.SecureRandom;
import java.math.BigInteger;


public class Manager extends Employee {

	private String uniqueId = new String();
	
	//Empty constructor
//  public Manager(){
	  
 // }

  	//Default Constructor
	public Manager(
			String firstName,
    		String lastName ,
    		double wage,
    		String wageCategory,
    		String mail,
    		String department,
    		String phone,
    		String address){
		super(firstName,lastName,wage,wageCategory,mail,department,phone,address);
	    //Construct a Unique ID for each manager of each department.
		//this.uniqueId = department+"-"+setUniqueId();
	}
	
  private static String createUniqueId() {
	  SecureRandom random = new SecureRandom();
	//  System.out.println(new BigInteger(40, random));
    return new BigInteger(30, random).toString(32);
  }
  
  public void setUniqueId(String id){
	  this.uniqueId=id;
  }
  
  public static String getUniqueId(){
	  return createUniqueId();
  }
}