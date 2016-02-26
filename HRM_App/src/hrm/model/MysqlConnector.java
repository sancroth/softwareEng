package hrm.model;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;

import com.mysql.jdbc.Statement;  
public class MysqlConnector {  
  private static Connection conn;  
  private static String url = "jdbc:mysql://localhost:3306/humanresources?autoReconnect=true&useSSL=false";  
  private static String user = "root";//Username of database  
  private static String pass = "Sancroth38353";//Password of database 
  
  //Standard DB connect method.
  public static Connection connect() throws SQLException{  
    try{  
      Class.forName("com.mysql.jdbc.Driver").newInstance();  
    }catch(ClassNotFoundException cnfe){  
      System.err.println("Error: "+cnfe.getMessage());  
    }catch(InstantiationException ie){  
      System.err.println("Error: "+ie.getMessage());  
    }catch(IllegalAccessException iae){  
      System.err.println("Error: "+iae.getMessage());  
    }  
    conn = DriverManager.getConnection(url,user,pass);  
    return conn;  
  }  
  
  //Method to ensure that we wont try to open the same connection more than once.
  //If the connection allready exists it returns the Connection.Otherwise starts a new one.
  public static Connection getConnection() throws SQLException, ClassNotFoundException{  
    if(conn !=null && !conn.isClosed())  
      return conn;  
    connect();  
    return conn;  
  }
  
}   