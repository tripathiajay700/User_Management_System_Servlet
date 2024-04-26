

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

@WebServlet("/DeleteCode")
public class DeleteCode extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("uname");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sathyadb","sathyadb");
			
			PreparedStatement ps=con.prepareStatement("delete from u_register where name=?");
			
			ps.setString(1, name);
			
			int i=ps.executeUpdate();
			out.print(i+" Record Deleted Successfully....");
			con.close();
			
			
		}
		catch(Exception ex)
		{
		System.out.println(ex);
		}
		
	}

}
