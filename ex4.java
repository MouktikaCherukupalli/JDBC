package jdbc_asg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class ex4{
	public static void main(String[] args) {
		 Connection connection=null;
		 float value=0;
		 float sum=0;
	       try {
	    	   Class.forName("org.postgresql.Driver");
	    	   connection  =DriverManager.getConnection("jdbc:postgresql://localhost:5432/univdb","postgres","mouk*tika");
	    	 
	    	   if(connection!=null) {
	    		   //System.out.println("Connection successfull");
	    		   
	    	   }else {
	    		   //System.out.println("connection failed");
	    	   }
		   Statement s=connection.createStatement();
    	   Scanner sc= new Scanner(System.in); //System.in is a standard input stream  
    	   System.out.print("Enter rollno: ");  
    	   int str= sc.nextInt();     
    	   System.out.println(""+str);//reads string   
    	   ResultSet result;
    	   String q;
    	q ="select tot_cred,grade,credits from student join takes on student.id = takes.id join course on takes.course_id = course.course_id where student.id='"+str+"'"+"";
  
    	
    	
    	   result = s.executeQuery(q);
           ResultSetMetaData rsmd = result.getMetaData();
           int columnsNumber = rsmd.getColumnCount();
       
      
                  
    
                  while (result.next()) {
                	  String a = result.getString(1) ;
                	
                		
                  float one =Integer.parseInt(a);  
                  String c = result.getString(2);
               
                float three =0;
                  if(c.equals("A+"))
                	  three = 10;
                 
                  else if(c.equals("A "))
                	  three = 9;
                  else if(c.equals("A-"))
                	  three = 8;
                 
                  if(c.equals("B+"))
                	  three = 7;
                  if(c.equals("B "))
                	  three = 6;
                  if(c.equals("B-"))
                	  three = 5;
                  if(c.equals("C+"))
                	  three = 4;
                  if(c.equals("C "))
                	  three = 3;
                  if(c.equals("C-"))
                	  three = 2;
                  //System.out.print(three);
                  String b = result.getString(3) ;
                  float two =Integer.parseInt(b);    
                  sum = sum+two;
                   
                   value = value+ three *(two);
           
              }	
	       }
                  catch(Exception e){e.printStackTrace();
          		}
	       if(value==0) {
	    	   System.out.println("Error! Rollno doesn't exist");
	    	  
	       }
	       else {
	    	   float final_gpa;
	    	   
	    	   final_gpa= value/sum;
		System.out.println(final_gpa);}
		
	}
	
                  
       }
       
