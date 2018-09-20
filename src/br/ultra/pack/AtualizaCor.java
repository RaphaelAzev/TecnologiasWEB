package br.ultra.pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/atualizacor")
public class AtualizaCor extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	out.println("<html><body>");
	out.println("<form method='post'>");
	out.println("ID: <input type='number' name='id'><br>");
	out.println("Escolha uma nova cor: <input type='color' name='cor'><br>");
	//out.println("Data de Criação: <input type='text' name='datacriacao'><br>");
	//out.println("Altura: <input type='number' name='altura' step='0.01'><br>");
	out.println("<input type='submit' value='Submit'>");
	out.println("</form>");
	out.println("<body><html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		Cor cor = new Cor();
		cor.setId(Integer.valueOf(request.getParameter("id")));
		cor.setCorHexa(request.getParameter("cor"));
		dao.alteraCOR(cor);
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("atualizado nota de Id: " + cor.getId().toString() + "!");
		out.println("<form action='/Projeto1Definitivo/welcome.jsp'>");
		out.println("<input type='submit' value='OK'>");
		out.println("</form>");
		out.println("</body></html>");
		dao.close();

	}
}
