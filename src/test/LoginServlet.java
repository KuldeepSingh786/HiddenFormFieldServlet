package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet
{
	public Connection con;
	@Override
	public void init()
	{
		con=DBConnection.getCon();
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		String uName=req.getParameter("uname");
		String pWord=req.getParameter("pword");
		
		try
		{
			PreparedStatement ps=con.prepareStatement("select * from Userreg14 where uname=? and pword=?");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				pw.println("<form action='welcome' method='post'>");
				pw.println("<input type='hidden' name='fName' value="+rs.getString(3)+">");
				pw.println("<input type='submit' value='DISPLAY'>");
				pw.println("</form>");
			}
		}catch(Exception e)
		{
			pw.println("Invalid UserName and Password!!");
			
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, res);
		}
		
	}
}
