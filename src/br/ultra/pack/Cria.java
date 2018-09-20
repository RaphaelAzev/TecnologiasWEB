package br.ultra.pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cria")
public class Cria extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("Conteudo: <input type='text' name='conteudo'><br>");
		out.println("ID-Cor (a nota será pintada de acordo com a cor relacionada a esse id"
				+ " na tabela cores): <input type='number' name='idcor'><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
		DAO dao;
		dao = new DAO();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);

		Notas nota = new Notas();
		nota.setConteudo(request.getParameter("conteudo"));
		nota.setDatacriacao(timestamp);
		nota.setIdcor(Integer.parseInt(request.getParameter("idcor")));
		
		dao.adiciona(nota);
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		out.println("Adicionado uma nova nota!<br>");
		out.println("<form action='/Projeto1Definitivo/welcome.jsp'>");
		out.println("<input type='submit' value='OK'>");
		out.println("</form>");
		out.println("</body></html>");
		dao.close();

		}
			
		
		
	}