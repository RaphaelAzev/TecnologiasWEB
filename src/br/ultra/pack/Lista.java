package br.ultra.pack;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lista")
public class Lista extends HttpServlet {
	
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		DAO dao;
		dao = new DAO();
		
		List<Notas> notas;
			notas = dao.getLista();
		
		
		PrintWriter out = response.getWriter();
		out.println("<html><body><table border='1'>");
		out.println("<tr><td>ID</td><td>Conteudo</td>" +
		 "<td>Data de Criação</td>");
		
		for (Notas nota : notas) {
			 out.println("<tr><td>" + nota.getId() + "</td>");
			 out.println("<td>" + nota.getConteudo() + "</td>");
			 out.println("<td>" + nota.getDatacriacao() + "</td>");
		//	 out.println("<td>" + pessoa.getAltura() + "</td></tr>");
			}
		out.println("</table></body></html>");

		dao.close();
		
		
		
	}
}
