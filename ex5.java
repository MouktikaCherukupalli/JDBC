package jdbc_asg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class ex5 {
	public static void main(String[] args) {
		Connection connection = null;

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/univdb", "postgres",
					"mouk*tika");

			if (connection != null) {
				//System.out.println("Connection successfull");

			} else {
				//System.out.println("connection failed");
			}
			Statement st = connection.createStatement();
			ResultSet rs1 = st.executeQuery("Select id from student");
			// System.out.println("1");
			Statement stmt1 = connection.createStatement();
			stmt1.executeUpdate("Create table id_cgpa(id varchar(6), cgpa real);");
			// System.out.println("2");
			while (rs1.next()) {
				Statement s = connection.createStatement();
				ResultSet result;
				float value = 0;
				float sum = 0;
				String q;
				String str = rs1.getString(1);
				q = "select tot_cred,grade,credits from student join takes on student.id = takes.id join course on takes.course_id = course.course_id where student.id='"
						+ str + "'" + ";";

				result = s.executeQuery(q);
				// ResultSetMetaData rsmd = result.getMetaData();
				// int columnsNumber = rsmd.getColumnCount();

				while (result.next()) {

					String a = result.getString(1);

					float one = Integer.parseInt(a);
					String c = result.getString(2);

					float three = 0;
					if (c.equals("A+"))
						three = 10;

					else if (c.equals("A "))
						three = 9;
					else if (c.equals("A-"))
						three = 8;

					if (c.equals("B+"))
						three = 7;
					if (c.equals("B "))
						three = 6;
					if (c.equals("B-"))
						three = 5;
					if (c.equals("C+"))
						three = 4;
					if (c.equals("C "))
						three = 3;
					if (c.equals("C-"))
						three = 2;
					// System.out.print(three);
					String b = result.getString(3);
					float two = Integer.parseInt(b);

					value = value + three * (two);
					sum = sum + two;
				}
				if (value == 0) {
					System.out.println("Error! Rollno doesn't exist");

				} else {
					float final_gpa;
					final_gpa = value / sum;
					String query = "INSERT INTO id_cgpa Values('"+str+"',"+final_gpa+");";
					Statement stmt = connection.createStatement();
					stmt.executeUpdate(query);
					// System.out.println(str + " " + final_gpa);
				}
			}
//1
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter number of rows:");
			int k = Integer.parseInt(sc.nextLine());
			// System.out.println("1");
			Statement s2 = connection.createStatement();
			// System.out.println("2");
			String rank1;
			rank1 = "select id, rank() over (order by cgpa desc) as s_rank from id_cgpa limit "+k+"";
			ResultSet rs2 = s2.executeQuery(rank1);

			while (rs2.next()) {
				System.out.println(rs2.getString(1));
			}
//2
			System.out.println("Enter dep_name:");
			String dep = sc.nextLine();
			System.out.println("enter number of rows: ");
			int k2 = Integer.parseInt(sc.nextLine());
			Statement s3 = connection.createStatement();
			String rank2;
			rank2 = "select id, dept_name, rank () over (partition by dept_name order by cgpa desc) as dept_rank from (SELECT id_cgpa.id as id,dept_name,cgpa FROM id_cgpa inner join student on id_cgpa.id=student.id) as a  where dept_name ='"
					+dep+"'limit "+k2+"";
			ResultSet rs3 = s3.executeQuery(rank2);

			while (rs3.next()) {
				System.out.println(rs3.getString(1));
			}
//3
			System.out.println("Enter course:");
			String course = sc.nextLine();
			System.out.println("enter number of rows: ");
			int k3 = Integer.parseInt(sc.nextLine());
			Statement s4 = connection.createStatement();
			String rank3;
			rank3 = "select id, course_id, dense_rank () over (partition by course_id order by cgpa desc) as course_rank from (SELECT id_cgpa.id as id,course_id,cgpa FROM id_cgpa inner join takes on id_cgpa.id=takes.id) as b where course_id ='"
					+course+"'limit "+k3+"";
			ResultSet rs4 = s4.executeQuery(rank3);
			while (rs4.next()) {
				System.out.println(rs4.getString(1));
			}

			// id_cgpa table --- id,cgpa

			connection.createStatement().executeUpdate("DROP TABLE id_cgpa");
		} catch (Exception e) {
		}
	}
}