package br.ultra.pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/removecor")
public class RemoveCor extends HttpServlet { 
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("Remover cor de ID: <input type='number' name='id'><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");
		}
	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		DAO dao;
		dao = new DAO();
		dao.removeCOR(Integer.valueOf(request.getParameter("id")));
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("Cor removida!!");
		out.println("<form action='/Projeto1Definitivo/welcome.jsp'>");
		out.println("<input type='submit' value='OK'>");
		out.println("</form>");
		out.println("</body></html>");
		dao.close();
	}
	
}
