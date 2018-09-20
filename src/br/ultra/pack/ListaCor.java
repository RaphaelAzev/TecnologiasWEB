package br.ultra.pack;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listacor")
public class ListaCor extends HttpServlet {
	
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		DAO dao;
		dao = new DAO();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd | HH:mm");
		
		List<Cor> cores;
			cores = dao.getListaCor();
		
		
		PrintWriter out = response.getWriter();
		out.println("<html><body><table border='1'>");
		out.println("<tr><td>ID</td><td>Cor em Hexadecimal</td><td>Cor</td>");
		
		for (Cor cor : cores) {
			 out.println("<tr><td>" + cor.getId() + "</td>");
			 out.println("<td>" + cor.getCorHexa() + "</td>");
			 out.println("<td><input type='color' name='cor' value = '" + cor.getCorHexa() + "'>");
			}
		
		
		out.println("</table>");
		out.println("<form action='/Projeto1Definitivo/welcome.jsp'>");
		out.println("<input type='submit' value='Home'>");
		out.println("</form>");
		out.println("</body></html>");

		dao.close();
		
		
		
	}
}
