<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRODUTOS</title>
<jsp:include page="../fragments/headTag.jsp" />
</head>
<body>

	<jsp:include page="../fragments/menu.jsp" />

	<h1>CADASTRO DE PRODUTOS - ENTRADA</h1>

	<form id="saveProduto" name="saveProduto"
		action="/desafiotecnico/produto/saveProduto" method="POST">

		Nome: <input type="text" id="nome" name="nome"
			placeholder="Digite"></input> 
		Filial: <input type="text"
			id="filial" name="filial" placeholder="filial" value=""></input>
		
		Valor: <input type="text"
			id="valor" name="valor" placeholder="valor" value="0"></input>

		Sequencial: <input type="text"
			id="sequencial" name="sequencial" placeholder="sequencial" value="0"></input>

		Código de barras: <input type="text"
			id="codigobarra" name="codigobarra" placeholder="codigobarra" value="0"></input>

		
		Quantidade: <input type="number"
			id="quantidadeEstoque" name="quantidadeEstoque" placeholder="Quantos?" value="0"></input>
		
		Descrição:
		<textarea id="descricao" name="descricao" rows="5" cols="30"
			placeholder="Digite"></textarea>

		<input type="submit" value="ENVIAR" /> 
		<input type="reset" value="CANCELAR" />
	</form>





	<h1>Listagem dos produtos</h1>

	<c:if test="${not empty produtos}">
	<datatables:table id="tableProdutos"  data="${produtos}" cdn="false" row="produto">
		<datatables:column title="NOME">
			<c:out value="${produto.nome}"></c:out>
		</datatables:column>
		<datatables:column title="VALOR">
			<c:out value="${produto.valor}"></c:out>
		</datatables:column>
		<datatables:column title="SEQUENCIAL">
			<c:out value="${produto.sequencial}"></c:out>
		</datatables:column>
		<datatables:column title="CÓDIGO DE BARRAS">
			<c:out value="${produto.codigoBarra}"></c:out>
		</datatables:column>
		<datatables:column title="ESTOQUE">
			<c:out value="${produto.quantidadeEstoque}"></c:out>
		</datatables:column>
		<datatables:column title="DESCRIÇÃO">
			<c:out value="${produto.descricao}"></c:out>
		</datatables:column>
	</datatables:table>
	</c:if>
		
</body>
</html>