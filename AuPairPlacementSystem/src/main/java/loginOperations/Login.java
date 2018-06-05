package loginOperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class Login {

	public static void main(String[] args) {
		String userName ="";
		String userPassword="";
		Scanner input = new Scanner (System.in);
		System.out.println( "Welcome to Au Pair Placement system" );
		int the_count = 0 ;
		try
		
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT?useSSL=false",
					"root", "qwerty@12345");
			
				System.out.println("Enter Username: ");
				userName = input.next();
				System.out.println("Enter Password: ");
				userPassword = input.next();
				
				String loginValidation = "{Call loginValidation(?,?,?)}";
				CallableStatement stmt = conn.prepareCall(loginValidation);
				
				stmt.setString(1, userName);
				stmt.setString(2, userPassword);
				stmt.registerOutParameter(3, Types.INTEGER);
				stmt.execute();
				the_count = stmt.getInt(3);
				System.out.println("thecount" + the_count);
				
				if(the_count != 0)
				{
				System.out.println("Login successful");
								
			}
			else {
				System.out.println("Failed to login...");
			}
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		
	}

}
