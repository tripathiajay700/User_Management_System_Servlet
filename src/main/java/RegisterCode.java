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
@WebServlet("/RegisterCode")
public class RegisterCode extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("uname");
		String password=request.getParameter("psw");
		String email=request.getParameter("mail");
		String gender=request.getParameter("gender");
		String state=request.getParameter("state");
		long mobile=Long.parseLong(request.getParameter("mno"));
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sathyadb","sathyadb");
			PreparedStatement ps=con.prepareStatement("insert into u_register values(?,?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,password);
			ps.setString(3,email);
			ps.setString(4,gender);
			ps.setString(5,state);
			ps.setLong(6,mobile);
			int i=ps.executeUpdate();
			out.println(i+" New Record Registered Successfully....");
			con.close();
		}
		catch(Exception ex) {
		System.out.println(ex);
		}
}

	private String getParameter(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
