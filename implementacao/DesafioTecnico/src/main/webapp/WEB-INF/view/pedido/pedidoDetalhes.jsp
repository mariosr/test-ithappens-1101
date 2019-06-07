<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PEDIDO - DETALHES</title>
<jsp:include page="../fragments/headTag.jsp" />

</head>
<body>
	<jsp:include page="../fragments/menu.jsp" />
	<h1>DETALHES DO PEDIDO DE ID = ${pedido.id}</h1>
	
		<form id="saveFormaPagamento" name="saveFormaPagamento"
		action="/desafiotecnico/pedido/saveFormaPagamento" method="POST">

		<input type="hidden" id = "idPedido" name="idPedido" value="${pedido.id}" />
		
		Forma Pagamento: 
			<select id="formaPagamento" name="formaPagamento">
			   <option value="Dinheiro">Dinheiro</option>
			   <option value="Cartao">Cartão</option>
			</select> 

		<input type="submit" value="ENVIAR" /> 
		<input type="reset" value="CANCELAR" />
	</form>
	
	
		<c:if test="${not empty pedido}">
		<h1 id="resultadoSomaProdutosSelecionados">Soma Produtos Selecionados <p id ="somaProdutosSelecionados"></p></h1>
		
		<h3>Produtos Consumidos</h3>
		
		<button onclick="pagarProdutosSelecionados()">PAGAR</button>
		<button onclick="selecionarTodosProdutos()">SELECIONAR TODOS</button>
		<button onclick="desmarcarProdutos()">DESMARCAR</button>
		
		</c:if>
			
		<table id="produtosAPagar">
		<tr>
		<td>SELECIONAR</td>
		<td>NOME</td>
		<td>DESCRIÇÃO</td>
		<td>PREÇO</td>
		</tr>
		<c:forEach var="itens" items="${pedido.itensPedidos}">
					<tr class="test">
						<td><input type="checkbox" class="productos" name="product" onchange="somarValoresProdutosSelecionados()" value="${itens.produto.valor}"></td>
						<td><c:out value="${itens.produto.nome}"></c:out></td>
						<td><c:out value="${itens.produto.descricao}"></c:out></td>
						<td><c:out value="${itens.produto.valor}"></c:out></td>
					</tr>	
		</c:forEach>
		</table>
		
		<h3>Observações</h3>	
		<p>${pedido.observacao}</p>
		
		
		<h3>Status: ${pedido.status}</h3>
		
		<h3>MUDAR STATUS</h3>
			<a href="#" onclick="confirmarPagamentoPedido(${pedido.id});return false;">Confirmar</a>
			<a href="#" onclick="cancelarPagamentoPedido(${pedido.id});return false;">Cancelar</a>
</body>
</html>