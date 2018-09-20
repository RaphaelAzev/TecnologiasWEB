<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*,br.ultra.pack.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import ="javax.servlet.http.HttpServletRequest"%>
<html>
<head>
<meta charset="UTF-8">
<title>Criador de Notas</title>
</head>
<body>
	<form method='post' action = 'welcome.jsp'>
		Conteudo: <input type='text' name='conteudo'><br>
		ID-Cor (a nota será pintada de acordo com a cor relacionada a esse id na tabela cores): <input type='number' name='idcor'><br>
		Data de Criação: <input type='text' name='datacriacao'><br>
		Altura: <input type='number' name='altura' step='0.01'><br>
		<input type='submit' value='Submit'>");
	</form>
	
	<% DAO dao;
	dao = new DAO();
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	Notas nota = new Notas();
	nota.setConteudo(request.getParameter("conteudo"));
	nota.setDatacriacao(timestamp);
	nota.setIdcor(Integer.parseInt(request.getParameter("idcor")));
	
	dao.adiciona(nota);%>

</body>
</html>