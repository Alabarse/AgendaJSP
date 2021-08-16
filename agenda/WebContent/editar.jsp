<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda contato</title>
<link rel="icon" href="imagens/phone.png" />
<link rel="stylesheet" href="css/editar.css" />
</head>
<body>
	<script src="scripts/validador.js"></script>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><h1>Editar contato</h1></td>
			</tr>
			<tr>
				<td><input type="text" name="id" id="caixaAlt"
					readonly="readonly"
					value="<%out.println(request.getAttribute("id"));%>" /></td>
			</tr>
			<tr>
				<td><input type="text" name="nome"
					value="<%out.println(request.getAttribute("nome"));%>" /></td>
			</tr>
			<tr>
				<td><input type="text" name="fone"
					value="<%out.println(request.getAttribute("fone"));%>" /></td>
			</tr>
			<tr>
				<td><input type="text" name="email"
					value="<%out.println(request.getAttribute("email"));%>" /></td>
			</tr>
			<tr>
				<td><input type="button" value="Salvar" class="Botao1"
					onclick="validar()" /></td>
			</tr>
		</table>
	</form>
</body>
</html>