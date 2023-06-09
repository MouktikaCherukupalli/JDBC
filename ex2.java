package jdbc_asg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Arrays;
public class ex2 {

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
    	   Statement s=connection.createStatement();
    	   ResultSet result;
    	  Scanner sc= new Scanner(System.in); //System.in is a standard input stream  
    	   System.out.print("Enter courseid: ");  
    	   int str= sc.nextInt();     
    	   //System.out.println(""+str);//reads string   
    	  
     
     
           String q;
         q=  "with recursive rec_prereq(course_id, prereq_id) as ( select course_id, prereq_id from prereq where course_id = '"+str+"' union select rec_prereq.course_id, prereq.prereq_id from rec_prereq, prereq  where rec_prereq.prereq_id = prereq.course_id) select course_id,title from course where course_id in (select prereq_id from rec_prereq)"; 
         result = s.executeQuery(q);
         ResultSetMetaData rsmd = result.getMetaData();
         int columnsNumber = rsmd.getColumnCount();
         while (result.next()) {
             for (int i = 1; i <= columnsNumber; i++) {
                 if (i > 1) System.out.print(",  ");
                 String columnValue = result.getString(i);
                 System.out.print(columnValue);
             }
             System.out.println("");
         }

  } catch(Exception e){e.printStackTrace();
	}

}}

