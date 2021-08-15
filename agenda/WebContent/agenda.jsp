<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.JavaBeans"%>
    <%@ page import="java.util.ArrayList"%>
    <%
    	@SuppressWarnings("unchecked")
    	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
    %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/phone.png"/> 
<link rel="stylesheet" type="text/css" href="css/agenda.css"/>
</head>
<body>
	<section id="corpo-agenda">
		<h1 id="Titulo">Agenda de contatos</h1>
		<a href="index.html" class="direcionamento">Voltar<br></a>
		<a href="novo.html" class="direcionamento">Novo contato</a>
		<table class="tabela">
			<thead>
				<tr>
					<th>ID</th>
					<th>NOME</th>
					<th>FONE</th>
					<th>E-MAIL</th>
					<th colspan="2">Opções</th>
				</tr>	
			</thead>
			<tbody>
				<%for (int i=0; i < lista.size(); i++) { %>
					<tr>
						<td><%=lista.get(i).getIdCon()%></td>
						<td><%=lista.get(i).getNome()%></td>
						<td><%=lista.get(i).getFone()%></td>
						<td><%=lista.get(i).getEmail()%></td>
						<td><a href="select?idcon=<%=lista.get(i).getIdCon()%>">Editar</a>
							<a href="" id="botaoExclusao" <%=lista.get(i).getIdCon()%>>Excluir</a>
						</td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</section>
</body>
</html>