<%@ page language="java" contentType="text/html; charset==UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CRUDENOTES</title>
	</head>
	<body>
		<%@ page import="java.util.*,br.ultra.pack.*" %>
		<%@ page import="java.text.SimpleDateFormat" %>
		<table border = '1'>
		<% String text = "Olá, seja bem-vindo ao crudeNotes!"; %>
 		<h1><% out.println(text); %></h1>
 		<br>
 		<% String text2 = "Éstas são as notas esxistentes no momento:"; %>
 		<h2><% out.println(text2); %></h2>
		<tr>
			<td>ID</td>
			<td>Conteudo</td>
			<td>ID-Cor</td>
			<td>Data de Criação</td>
		</tr>
			<% DAO dao = new DAO();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd | HH:mm");
			
			List<Notas> notas;
				notas = dao.getLista();
			
			
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
				} %>
				<tr bgcolor ="<%= bgcor %>">
					<td><%=nota.getId()%></td>
					<td><%=nota.getConteudo()%></td>
					<td><%=nota.getIdcor()%></td>
					<td><%=sdf.format(nota.getDatacriacao())%></td>
				</tr>
				<% } %>
		
		
		
		
		</table>
		<h3>Escolha uma das opções a seguir:</h3>
		<form action='/Projeto1Definitivo/cria'>
			<input type='submit' value='Criar Nota'>
		</form>
		<form action='/Projeto1Definitivo/atualiza'>
			<input type='submit' value='Editar Nota'>
		</form>
			<form action='/Projeto1Definitivo/remove'>
		<input type='submit' value='Exluir Nota'>
		</form>
		<form action='/Projeto1Definitivo/criacor'>
			<input type='submit' value='Criar Cor'>
		</form>
		<form action='/Projeto1Definitivo/atualizacor'>
			<input type='submit' value='Editar Cor'>
		</form>
		<form action='/Projeto1Definitivo/removecor'>
			<input type='submit' value='Exluir Cor'>
		</form>
		<form action='/Projeto1Definitivo/listacor'>
			<input type='submit' value='Exibir Cores existentes'>
		</form>
	</body>
</html>