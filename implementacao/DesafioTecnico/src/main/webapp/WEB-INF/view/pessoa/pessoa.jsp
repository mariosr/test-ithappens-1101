<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>USUÁRIOS</title>

<jsp:include page="../fragments/headTag.jsp" />
</head>
<body>

	<jsp:include page="../fragments/menu.jsp" />

	<h1>CADASRTE UM USUÁRIO:</h1>

	<form id="savePessoa" name="savePessoa"
		action="/desafiotecnico/usuario/save" method="POST">

		Login: <input id="login" name="login" path="login" placeholder="Login" />

		Senha: <input id="password" name="password" type="password"
			placeholder="Senha" /> Nome: <input id="nome" name="nome"
			path="nome" placeholder="Nome" /> CPF: <input id="cpf" name="cpf"
			path="cpf" placeholder="Cpf" /> Papel: <select id="papeis"
			name="papeis">

			<option value="ROLE_ADMIN">ADMIN</option>
			<option value="ROLE_TOTEN_AUXILIAR">CAIXA</option>

		</select> Idade: <input id="idade" type="number" name="idade"
			placeholder="Idade" /> Sexo: <select id="sexo" name="sexo">

			<option value="MASCULINO">MASCULINO</option>

			<option value="FEMININO">FEMININO</option>

		</select> <input type="submit" value="ENVIAR" /> <input type="reset"
			value="CANCELAR" />

	</form>





	<h1>Listagem de pessoas</h1>



	<c:if test="${not empty pessoas}">

		<datatables:table id="tablePessoas" data="${pessoas}" cdn="false"
			row="pessoa">

			<datatables:column title="IDENTIFICADOR">

				<c:out value="${pessoa.id}"></c:out>

			</datatables:column>

			<datatables:column title="NOME">

				<c:out value="${pessoa.nome}"></c:out>

			</datatables:column>

			<datatables:column title="LOGIN">

				<c:out value="${pessoa.login}"></c:out>

			</datatables:column>

			<datatables:column title="CPF">

				<c:out value="${pessoa.cpf}"></c:out>

			</datatables:column>

			<datatables:column title="IDADE">

				<c:out value="${pessoa.idade}"></c:out>

			</datatables:column>

			<datatables:column title="SEXO">

				<c:out value="${pessoa.sexo}"></c:out>

			</datatables:column>

			<datatables:column title="PAPEL">

				<c:out value="${pessoa.papeis[0].nome}"></c:out>

			</datatables:column>

		</datatables:table>

	</c:if>

</body>

</html>

