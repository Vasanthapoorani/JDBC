package jdbcdemo1;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class DemoClass {

	public static void main(String a[]) throws Exception
	{
		String url="jdbc:mysql://localhost:3306/aliens";//DB name
		String uname="root";
		String pass="Saravanan@23";
		ArrayList<Person> personlist = new ArrayList<Person>();//usage of collections(ArrayList)
		 int count=0;
		//String query="select * from student;";
		Class.forName("com.mysql.cj.jdbc.Driver");//Loads the driver
		Connection con= DriverManager.getConnection(url,uname,pass);//will give u the obj for connection, since con is a interface can't create ob directly
		
		//**********************INSERT**********************
		String query="insert into student values(?,?);";
		PreparedStatement st=con.prepareStatement(query);
		int userid=12;
		String username="Manikanth";
		st.setInt(1,userid);
		st.setString(2,username);
		int counts=st.executeUpdate();//tells no.of rows updated,don't use query here if we use prepared statement
		if (counts > 0) {
		    System.out.println("A new user was inserted successfully!");
		}
		st.close();

		//**********************Update**********************
		String sql = "UPDATE student SET username=? WHERE userid=?";
		 
		PreparedStatement statement = con.prepareStatement(sql);
		
		statement.setString(1,"Vasanthapoorani"); 
		statement.setInt(2, 5); 
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
		    System.out.println("An existing user was updated successfully!");
		}
		statement.close();
		
		//**********************Delete**********************
		String sqll = "DELETE FROM student WHERE userid=?";
		 
		PreparedStatement statement1 = con.prepareStatement(sqll);
		
		statement1.setInt(1, 16);
		 
		int rowsDeleted = statement1.executeUpdate();
		if (rowsDeleted > 0) {
		    System.out.println("A user was deleted successfully!");
		}
		statement1.close();

		    //******************Insert Elements at run time****************
				PreparedStatement stmt=con.prepareStatement("insert into student values(?,?);");  
				@SuppressWarnings("resource")
				Scanner s=new Scanner(System.in);
				System.out.println("Please insert the elements now..");
				int id=s.nextInt();
				String name=s.next();
				stmt.setInt(1, id);
				stmt.setString(2, name);
				int rows=stmt.executeUpdate();
				System.out.println("NO.of rows updated in run time"+rows);
				
				
				//**********************Display all**********************
				String quer="select * from student;";
				Statement st1=con.createStatement();
				ResultSet rs=st1.executeQuery(quer);//has the power of storing in tabular format
				while(rs.next())
				{
					Person person = new Person();
					person.setName(rs.getString("username"));
		            person.setId(rs.getInt("userid"));
		            personlist.add(person);
				//String userdata=rs.getInt(1) +":"+ rs.getString(2);
				//System.out.println(userdata);
		            System.out.println(personlist.get(count).getId()+":"+personlist.get(count).getName());
				count++;
				}
				System.out.println("No. of elements in the list is "+personlist.size());	
				st1.close();
				
		con.close();
		
		}
}
