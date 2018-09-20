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

@WebServlet("/criacor")
public class CriaCor extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("Escolha uma cor para definir: ");
		out.println("<input type='color' name='cor'><br>");
		//out.println("Data de Criação: <input type='text' name='datacriacao'><br>");
		//out.println("Altura: <input type='number' name='altura' step='0.01'><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
		DAO dao;
		dao = new DAO();
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //System.out.println(timestamp);

		Cor cor = new Cor();
		cor.setCorHexa(request.getParameter("cor"));
		//nota.setDatacriacao(timestamp);
		//String nascimento = request.getParameter("nascimento");
		//Date data = new SimpleDateFormat("yyyy-MM-dd").parse(nascimento);
		//Calendar dataNascimento = Calendar.getInstance();
		//dataNascimento.setTime(data);
		//pessoa.setNascimento(dataNascimento);
		
		dao.adicionaCOR(cor);
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("Adicionado uma nova cor");
		out.println("<form action='/Projeto1Definitivo/listacor'>");
		out.println("<input type='submit' value='OK'>");
		out.println("</form>");
		out.println("</body></html>");
		dao.close();

		}
			
		
		
	}