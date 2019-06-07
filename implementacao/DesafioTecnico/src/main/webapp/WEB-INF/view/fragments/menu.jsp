<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- MENU -->
	<div id='cssmenu'>
		<ul>
			<li  class='active'><a href="<c:url value="/" ></c:url>">INICIO</a></li>
			<li><a href="<c:url value="/pedido/"></c:url>">PEDIDOS</a></li>
			<li><a href="<c:url value="/produto/"></c:url>">PRODUTOS</a></li>
			<li><a href="<c:url value="/usuario/"></c:url>">USUÁRIOS</a></li>
			<li><a href="<c:url value="/cliente/"></c:url>">CLIENTES</a></li>
			<li><a href="<c:url value="/filial/"></c:url>">FILIAIS</a></li>
			<li><a href="<c:url value="/logout/"></c:url>">SAIR</a></li>
		</ul>
	</div>