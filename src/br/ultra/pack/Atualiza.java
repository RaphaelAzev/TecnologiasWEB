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

@WebServlet("/atualiza")
public class Atualiza extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	out.println("<html><body>");
	out.println("<form method='post'>");
	out.println("ID: <input type='number' name='id'><br>");
	out.println("Conteudo: <input type='text' name='conteudo'><br>");
	out.println("ID-Cor (a nota será pintada de acordo com a cor relacionada a esse id"
			+ " na tabela cores: <input type='number' name='idcor'><br>");
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
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //System.out.println(timestamp);
		Notas nota = new Notas();
		nota.setId(Integer.valueOf(request.getParameter("id")));
		nota.setConteudo(request.getParameter("conteudo"));
		nota.setDatacriacao(timestamp);
		nota.setIdcor(Integer.parseInt(request.getParameter("idcor")));
		//String nascimento = request.getParameter("nascimento");
		//Date data = new SimpleDateFormat("yyyy-MM-dd").parse(nascimento);
		//Calendar dataNascimento = Calendar.getInstance();
		//dataNascimento.setTime(data);
		//pessoa.setNascimento(dataNascimento);
		dao.altera(nota);
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("atualizado nota de Id: " + nota.getId().toString() + "!");
		out.println("<form action='/Projeto1Definitivo/welcome.jsp'>");
		out.println("<input type='submit' value='OK'>");
		out.println("</form>");
		out.println("</body></html>");
		dao.close();

	}
}
