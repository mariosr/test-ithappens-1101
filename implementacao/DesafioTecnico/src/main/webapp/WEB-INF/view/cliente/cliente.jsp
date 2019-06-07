<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CLIENTES</title>
<jsp:include page="../fragments/headTag.jsp" />
</head>
<body>
	<jsp:include page="../fragments/menu.jsp" />
	<h1>CADASTRE UM CLIENTE</h1>

	<form id="savePessoa" name="savePessoa" action="/desafiotecnico/cliente/save"
		method="POST">

		Nome: <input id="nome" name="nome" path="nome" placeholder="Nome" />
		Email: <input id="email" name="email" path="email" placeholder="Email" />
		Celular: <input id="celular" name="celular" placeholder="Celular" />
		CPF: <input id="cpf" name="cpf" path="cpf" placeholder="Cpf" /> <input
			type="submit" value="ENVIAR" /> <input type="reset" value="CANCELAR" />
	</form>

	<h1>Listagem de clientes</h1>

	<c:if test="${not empty clientes}">
		<datatables:table id="tablePessoas" data="${clientes}" cdn="false"
			row="cliente">
			<datatables:column title="IDENTIFICADOR">
				<c:out value="${cliente.id}"></c:out>
			</datatables:column>
			<datatables:column title="NOME">
				<c:out value="${cliente.nome}"></c:out>
			</datatables:column>
			<datatables:column title="CPF">
				<c:out value="${cliente.cpf}"></c:out>
			</datatables:column>
			<datatables:column title="EMAIL">
				<c:out value="${cliente.email}"></c:out>
			</datatables:column>

			<datatables:column title="CELULAR">
				<c:out value="${cliente.celular}"></c:out>
			</datatables:column>

		</datatables:table>
	</c:if>
</body>
</html>