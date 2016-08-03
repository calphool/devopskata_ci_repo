package com.rounceville;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rounceville.EnglishNumberToWords;

public class SimpleWebAppServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String endpointFile ; 
	private static String userFile;
	private static String passwordFile;
	
	private static String endpoint;
	private static String user;
	private static String password;
	
	public void init() throws ServletException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			throw new ServletException(e1);
		}
		
		endpointFile = getServletContext().getInitParameter("endpointfile");
		if(endpointFile == null)
			throw new ServletException("servlet context init parameter: endpointfile not found.");
		endpointFile = getServletContext().getRealPath("/") + endpointFile;
		
		userFile = getServletContext().getInitParameter("userfile");
		if(userFile == null)
			throw new ServletException("servlet context init parameter: userFile not found.");
		userFile = getServletContext().getRealPath("/") + userFile;

		passwordFile =  getServletContext().getInitParameter("passwordfile");
		if(passwordFile == null)
			throw new ServletException("servlet context init parameter: passwordFile not found.");
		passwordFile = getServletContext().getRealPath("/") + passwordFile;
		
		try {
			endpoint = new String(Files.readAllBytes(Paths.get(endpointFile))).trim();
			user = new String(Files.readAllBytes(Paths.get(userFile))).trim();
			password = new String(Files.readAllBytes(Paths.get(passwordFile))).trim();
			setupDB(endpoint, user,	password);
		} 
		catch (SQLException e) {
			throw new ServletException(e);
		} 
		catch (IOException e) {
			throw new ServletException(e);
		}
	}
	
	private void setupDB(String dbEndpoint, String user, String passwd) throws SQLException {
		Connection  connection = DriverManager.getConnection("jdbc:mariadb://" + dbEndpoint, user, passwd);
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("CREATE database if not exists poems;");
		stmt.close();
		connection.close();
		
		connection = DriverManager.getConnection("jdbc:mariadb://" + dbEndpoint + "/poems", user, passwd);
		stmt = connection.createStatement();
		stmt.executeUpdate("CREATE table if not exists poem (id binary(16) not null, poemtext varchar(1000), primary key (id));");
		stmt.close();

		stmt = connection.createStatement();
		//stmt.executeUpdate("delete from poem;");
		stmt.executeUpdate("insert into poem (id, poemtext) values(   UNHEX(REPLACE(UUID(),'-','')), 'Now is the time for all good men to come to the aid of their country.');");
		stmt.close();
		
		connection.close();
	}

	private ArrayList<String> getPoems() throws ServletException {
		ArrayList<String> alReturnResult = new ArrayList<String>();
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:mariadb://" + endpoint + "/poems", user, password);
			Statement stmt = connection.createStatement();
			String sql = "select poemtext from poem";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				alReturnResult.add(rs.getString("poemtext"));
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}	
		
		return alReturnResult;
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int i = 14562;
        
        PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		out.println("Hello World!");
                out.println("<hr/>");
                out.println("Poems:</br>");
                
                try {
                	ArrayList<String> alPoemList = getPoems();
                	for(String sPoem : alPoemList) {
                		out.println(sPoem + "</br/>\n");
                	}
                }
                catch(Exception e) {
                	out.println("Unable to get poems from database.");
                	out.println("Exception: " + e.getMessage());
                }
                
                out.println("<hr/>");
                out.println(i + " = " + EnglishNumberToWords.convert(i));
                out.println("<hr/>");
                out.println("<form action='./simplewebapp' method='post'>\n");
                out.println("Type a number:\n");
                out.println("<input type=text name='thenumber'>\n");
                out.println("<input type=submit name=submit value=submit>\n");
                out.println("</form>");
		out.close();
	}

        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String thenumber = req.getParameter("thenumber");
		 
                PrintWriter out = resp.getWriter();
		
                resp.setContentType("text/html");

                out.println("You sent the value: " + thenumber + "<br/>");
                out.println("Which means: " + EnglishNumberToWords.convert(Integer.parseInt(thenumber)));

                out.println("<br/><br/><a href='./simplewebapp'>Back</a>");
        }
}
