

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ViewCode")
public class ViewCode extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("uname");
		out.print("<table border='1'>");
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sathyadb","sathyadb");
					
			PreparedStatement ps=con.prepareStatement("select * from u_register where name=?");
			ps.setString(1, name);
			
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			
			int n=rsmd.getColumnCount();
			
			for(int i=1;i<=n;i++)
				out.println("<td><font color=blue size=3>"+"<br>"+rsmd.getColumnName(i));
				out.println("<tr>");
				
				while(rs.next()) {
					for(int i=1;i<=n;i++)
						out.println("<td><br>"+rs.getString(i));
						out.println("<tr>");
				}
				
				out.println("</table>");
				con.close();
			
		}catch (Exception e) {
			System.out.println(e);
			
		}
		
	}

}
