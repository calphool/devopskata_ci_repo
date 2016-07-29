package com.rounceville;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rounceville.EnglishNumberToWords;

public class SimpleWebAppServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                int i = 14562;
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		out.println("Hello World!");
                out.println("<hr/>");
                out.println("Now is the time for all good men to come to the aid of their country.");
                out.println("<hr/>");
                out.println(i + " = " + EnglishNumberToWords.convert(i));
                out.println("<hr/>");
                out.println("<form action='.' method='post'>\n");
                out.println("Type a number:\n");
                out.println("<input type=text name='thenumber'>\n");
                out.println("<input type=submit name=submit value=submit>\n");
                out.println("</form>");
		out.close();
	}

        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String thenumber = request.getParameter("thenumber");
		PrintWriter out = resp.getWriter();
		
                resp.setContentType("text/html");

                out.println("You sent the value: " + thenumber + "<br/>");
                out.println("Which means: " + EnglishNumberToWords.convert(Integer.parse(thenumber)));

                out.println("<br/><br/><a href='.'>Back</a>");
        }
}
