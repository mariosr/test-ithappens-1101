<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PEDIDOS</title>
<jsp:include page="../fragments/headTag.jsp" />

</head>
<body>
	<jsp:include page="../fragments/menu.jsp" />
	<h1>VENDA DE PRODUTOS - SAÍDA</h1>

	<form id="savePedido" name="savePedido"
		action="/desafiotecnico/pedido/savePedido" method="POST">

		Filial: <input type="text" id="filial" name="filial"
			placeholder="Digite"></input> 
		Usuário: <input type="text"
			id="usuario" name="usuario" placeholder="ID do usuário" value=""></input>
		
		Cliente: <input type="text"
			id="cliente" name="cliente" placeholder="ID do cliente" value=""></input>

		Observacao:
		<textarea id="observacao" name="observacao" rows="5" cols="30"
			placeholder="Digite"></textarea>
		
		Quantidade: <input type="number"
			id="qtd" name="qtd" placeholder="Quantos?" value="0"></input>
		
		Produtos: <input type="text"
			id="produtos" name="produtos" placeholder="Digite o nome" value=""></input>

		<input type="submit" value="ENVIAR" /> 
		<input type="reset" value="CANCELAR" />
	</form>

	<h3>Produtos solicitados</h3>
	<table id="produtosSolicitados" border="1">
		<tr>
			<td>ID</td>
			<td>NOME</td>
			<td>PREÇO</td>
			<td>QUANTIDADE</td>
		</tr>
	</table>
	
	<h3>CLIENTE: <span id = "nomeCliente"></span> </h3>
	<h3>USUÁRIO:<span id = "nomeUsuario"></span> </h3>
	<h3>FILIAL: <span id = "nomeFilial"></span> </h3>

	<h1>Lista de todos os PEDIDOS</h1>
	<c:if test="${not empty pedidos}">
	<datatables:table id="tablePedidos"  data="${pedidos}" cdn="false" row="pedido" export="pdf">
		
		<datatables:column title="IDENTIFICADOR">
			<c:out value="${pedido.id}"></c:out> 
		</datatables:column>
		
		<datatables:column title="FILIAL">
			<c:out value="${pedido.filial.nome}"></c:out> 
		</datatables:column>
	
		<datatables:column title="CLIENTE">
			<c:out value="${pedido.cliente.nome}"></c:out>
		</datatables:column>
		
		<datatables:column title="PAGAMENTO">
			<a href="<c:url value = "/pedido/details/${pedido.id}"></c:url>">Detalhes</a>
		</datatables:column>
		
		<datatables:export type="pdf" cssClass="btn btn-small" />
	</datatables:table>
	</c:if>
	
</body>
</html>