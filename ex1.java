package jdbc_asg1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Arrays;
public class ex1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Connection connection=null;
       try {
    	   Class.forName("org.postgresql.Driver");
    	   connection  =DriverManager.getConnection("jdbc:postgresql://localhost:5432/univdb","postgres","mouk*tika");
    	 
    	   if(connection!=null) {
    		   //System.out.println("Connection successfull");
    		   
    	   }else {
    		   //System.out.println("connection failed");
    	   }
       
       Scanner sc= new Scanner(System.in); 
       System.out.print("Enter table name: ");  
       String table= sc.nextLine();
       System.out.print("Enter row count: ");  
       int k = sc.nextInt();
       Statement st = connection.createStatement();
       ResultSet rs = st.executeQuery("select * from "+table+" limit " +k+ " ");
       ResultSetMetaData rsmd = rs.getMetaData();
      int columnsCount = rsmd.getColumnCount();
      for(int i = 1; i <= columnsCount; i++) {
          System.out.print(rsmd.getColumnLabel(i)+ "  ");
      }
       while (rs.next()) {
    	
     
       System.out.println();
    	 for(int j = 1 ; j <= columnsCount; j++){
    
    	       System.out.print(rs.getString(j) + "   "); 
     }

    	  
    	     }
       
       }catch(Exception e) {
    	   System.out.print(e);}
       }}

