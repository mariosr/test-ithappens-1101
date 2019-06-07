<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FILIAIS</title>
<jsp:include page="../fragments/headTag.jsp" />
</head>
<body>

	<jsp:include page="../fragments/menu.jsp" />

	<h1>LISTAGEM DE FILIAIS</h1>

	<c:if test="${not empty filiais}">
	<datatables:table id="tableFiliais"  data="${filiais}" cdn="false" row="filial" export="pdf">
	
		<datatables:column title="ID">
			<c:out value="${filial.id}"></c:out>
		</datatables:column>
		
		<datatables:column title="NOME">
			<c:out value="${filial.nome}"></c:out>
		</datatables:column>

		<datatables:column title="PRODUTOS">
		<c:forEach var="produto" items="${filial.produtos}">
						<c:out value="${produto.nome}"></c:out>	
		</c:forEach>
		</datatables:column>

		<datatables:export type="pdf" cssClass="btn btn-small" />
	</datatables:table>
	</c:if>
		
</body>
</html>