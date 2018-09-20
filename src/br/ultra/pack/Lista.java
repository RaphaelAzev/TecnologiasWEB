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

@WebServlet("/lista")
public class Lista extends HttpServlet {
	
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		DAO dao;
		dao = new DAO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd | HH:mm");
		
		List<Notas> notas;
			notas = dao.getLista();
		
		
		PrintWriter out = response.getWriter();
		out.println("<html><body><table border='1'>");
		out.println("<tr><td>ID</td><td>Conteudo</td><td>ID-Cor</td>" +
		 "<td>Data de Criação</td>");
		
		List<Cor> cores;
		cores = dao.getListaCor();
		String bgcor = null;
		
		for (Notas nota : notas) {
			for (Cor cor : cores) {
				if (cor.getId() == nota.getIdcor()) {
					bgcor = cor.getCorHexa();
					break;
				}
				if (!cores.contains(cor.getId())) {
					bgcor = "#FFFFFF";
				}
			}
			out.println("<tr bgcolor ='" + bgcor + "'><td>" + nota.getId() + "</td>");
			out.println("<td>" + nota.getConteudo() + "</td>");
			out.println("<td>" + nota.getIdcor() + "</td>");
			out.println("<td>" + sdf.format(nota.getDatacriacao()) + "</td></tr>");
		}
		out.println("</table></body></html>");

		dao.close();
		
		
		
	}
}
