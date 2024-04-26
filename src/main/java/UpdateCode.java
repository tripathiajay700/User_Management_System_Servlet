

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateCode")
public class UpdateCode extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("uname");
		String password=request.getParameter("psw");
		String email=request.getParameter("mail");
		long mobile=Long.parseLong(request.getParameter("mno"));
		
		try {
				
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sathyadb","sathyadb");
			
			PreparedStatement ps=con.prepareStatement("update u_register set password=?,email=?,mobile=? where name=?");
			
			ps.setString(1,password);
			ps.setString(2,email);
			ps.setLong(3,mobile);
			ps.setString(4,name);
			
			int i=ps.executeUpdate();
			out.print(i+" Record Updated Successfully....");
			con.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		
	}

}
