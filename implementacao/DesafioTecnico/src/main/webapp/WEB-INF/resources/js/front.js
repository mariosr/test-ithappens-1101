$(document).ready(function(){
	$("#produtos").autocomplete({
		source : function (request,response) {
			var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
			$.ajax({
				url : "http://localhost:8080/desafiotecnico/produto/getProdutos/",
				dataType : "json",
				success : function(data) {
					 response($.map(data, function(v,i){
		                    var text = v.nome;
		                    if (text && ( !request.term || matcher.test(text) ) ) {
		                        return {
		                                label: v.nome,
		                                value: v.id,
		                                preco: v.valor
		                               };
		                    }
		                }));
				}, error: function( error )
			      {
			         console.log( 'A chamada ao getProdutos foi mal sucedida pelo Ajax' );
			      }
			});
		},
		select : function(event, ui) {
			var qtdProduto = $('#qtd').val(); ;
		
			
			$('#produtosSolicitados').append("<tr><td>"+ui.item.value+"</td><td>"+ui.item.label+"</td><td>"+ui.item.preco+"</td> " +"<td> "+qtdProduto+"</td> </tr>");
			$('#savePedido').prepend("<input type='hidden' id = 'produtoSolicitado' name='produtoSolicitado' value='"+ui.item.value+"'>");
			$('#savePedido').prepend("<input type='hidden' id = 'qtdProdutos' name='qtdProdutos' value='"+qtdProduto+"'>");
			
			
			//limpando o campo após o elemento selecionado
			$(this).val('');
			return false;
	    }
	})
	
	
		$("#usuario").autocomplete({
		source : function (request,response) {
			var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
			$.ajax({
				url : "http://localhost:8080/desafiotecnico/usuario/getPessoas/",
				dataType : "json",
				success : function(data) {
					 response($.map(data, function(v,i){
		                    var text = v.nome;
		                    
		                    if (text && ( !request.term || matcher.test(text) ) ) {
		                        return {
		                                label: v.nome,
		                                value: v.id,
		                               };
		                    }
		                }));
				}, error: function( error )
			      {
			         console.log( 'A chamada ao getPessoas foi mal sucedida pelo Ajax' );
			      }
			});
		},
		select : function(event, ui) {

			
			$('#nomeUsuario').prepend( " "+ui.item.label);
			//limpando o campo após o elemento selecionado
			$(this).val(ui.item.value);
			return false;
	    }
	})
	
	
		$("#cliente").autocomplete({
		source : function (request,response) {
			var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
			$.ajax({
				url : "http://localhost:8080/desafiotecnico/cliente/getClientes/",
				dataType : "json",
				success : function(data) {
					 response($.map(data, function(v,i){
		                    var text = v.nome;
		                    
		                    if (text && ( !request.term || matcher.test(text) ) ) {
		                        return {
		                                label: v.nome,
		                                value: v.id,
		                               };
		                    }
		                }));
				}, error: function( error )
			      {
			         console.log( 'A chamada ao getClientes foi mal sucedida pelo Ajax' );
			      }
			});
		},
		select : function(event, ui) {

			
			$('#nomeCliente').prepend( " "+ui.item.label);
			//limpando o campo após o elemento selecionado
			$(this).val(ui.item.value);
			return false;
	    }
	})
	
	
	
	$("#filial").autocomplete({
		source : function (request,response) {
			var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
			$.ajax({
				url : "http://localhost:8080/desafiotecnico/filial/getFiliais/",
				dataType : "json",
				success : function(data) {
					 response($.map(data, function(v,i){
		                    var text = v.nome;
		                    
		                    if (text && ( !request.term || matcher.test(text) ) ) {
		                        return {
		                                label: v.nome,
		                                value: v.id,
		                               };
		                    }
		                }));
				}, error: function( error )
			      {
			         console.log( 'A chamada ao getFiliais foi mal sucedida pelo Ajax' );
			      }
			});
		},
		select : function(event, ui) {

			
			$('#nomeFilial').prepend( " "+ui.item.label);
			//limpando o campo após o elemento selecionado
			$(this).val(ui.item.value);
			return false;
	    }
	})
	
	
	var tableFiliais = $('#tableFiliais').DataTable( {
		"language": {
    		"sProcessing":   "Processando...",
            "lengthMenu": "Mostrando _MENU_ filiais por página",
            "zeroRecords": "Nenhum produto encontrado",
            "info": "Página _PAGE_ de _PAGES_",
            "infoEmpty": "Nada cadastrado",
            "infoFiltered": "(Filtrado de _MAX_ produtos no total)",
            "search":         "Filial:",
            "oPaginate": {
        	    "sFirst":    "Primeiro",
        	    "sPrevious": "Anterior",
        	    "sNext":     "Seguinte",
        	    "sLast":     "Último"
        	}
		},
		"aria" : {
			"sortAscending" : ": Ordenar colunas de forma ascendente",
			"sortDescending" : ": Ordenar colunas de forma descendente"
		},
		  "columnDefs": [
		                 { "searchable": false, "targets": 1}
		               ]
	} );
	
	var tableProdutos = $('#tableProdutos').DataTable( {
		"language": {
    		"sProcessing":   "Processando...",
            "lengthMenu": "Mostrando _MENU_ produtos por página",
            "zeroRecords": "Nenhum produto encontrado",
            "info": "Página _PAGE_ de _PAGES_",
            "infoEmpty": "Nada cadastrado",
            "infoFiltered": "(Filtrado de _MAX_ produtos no total)",
            "search":         "Produto:",
            "oPaginate": {
        	    "sFirst":    "Primeiro",
        	    "sPrevious": "Anterior",
        	    "sNext":     "Seguinte",
        	    "sLast":     "Último"
        	}
		},
		"aria" : {
			"sortAscending" : ": Ordenar colunas de forma ascendente",
			"sortDescending" : ": Ordenar colunas de forma descendente"
		},
		  "columnDefs": [
		                 { "searchable": false, "targets": 1}
		               ]
	} );
	var tableCategoriaProdutos = $('#tableCategoriaProdutos').DataTable( {
		"language": {
    		"sProcessing":   "Processando...",
            "lengthMenu": "Mostrando _MENU_ categorias por página",
            "zeroRecords": "Nenhuma categoria encontrada",
            "info": "Página _PAGE_ de _PAGES_",
            "infoEmpty": "Nada cadastrado",
            "infoFiltered": "(Filtrado de _MAX_ categorias no total)",
            "search":         "Nome da categoria:",
            "oPaginate": {
        	    "sFirst":    "Primeiro",
        	    "sPrevious": "Anterior",
        	    "sNext":     "Seguinte",
        	    "sLast":     "Último"
        	}
		},
		"aria" : {
			"sortAscending" : ": Ordenar colunas de forma ascendente",
			"sortDescending" : ": Ordenar colunas de forma descendente"
		}
	} );
	var tablePessoas = $('#tablePessoas').DataTable( {
		"language": {
    		"sProcessing":   "Processando...",
            "lengthMenu": "Mostrando _MENU_ pessoas por página",
            "zeroRecords": "Nenhuma pessoa encontrada",
            "info": "Página _PAGE_ de _PAGES_",
            "infoEmpty": "Nínguem encontrado",
            "infoFiltered": "(Filtrado de _MAX_ pessoas no total)",
            "search":         "Buscar:",
            "oPaginate": {
        	    "sFirst":    "Primeiro",
        	    "sPrevious": "Anterior",
        	    "sNext":     "Seguinte",
        	    "sLast":     "Último"
        	}
		},
		"aria" : {
			"sortAscending" : ": Ordenar colunas de forma ascendente",
			"sortDescending" : ": Ordenar colunas de forma descendente"
		}
	} );
	var tablePedidos = $('#tablePedidos').DataTable( {
		destroy: true,
		"language": {
    		"sProcessing":   "Processando...",
            "lengthMenu": "Mostrando _MENU_ pedidos por página",
            "zeroRecords": "Nenhuma pedido encontrado",
            "info": "Página _PAGE_ de _PAGES_",
            "infoEmpty": "Nada cadastrado",
            "infoFiltered": "(Filtrado de _MAX_ pedidos no total)",
            "search":         "Pedido:",
            "oPaginate": {
        	    "sFirst":    "Primeiro",
        	    "sPrevious": "Anterior",
        	    "sNext":     "Seguinte",
        	    "sLast":     "Último"
        	}
		},
		"aria" : {
			"sortAscending" : ": Ordenar colunas de forma ascendente",
			"sortDescending" : ": Ordenar colunas de forma descendente"
		},
		  "columnDefs": [
		                 { "searchable": false, "targets": 1},
		                 { "searchable": false, "targets": 2},
		                 { "searchable": false, "targets": 3}
		               ]
	} );

	//nao me pergunte WHY, mas o codigo de traducao só funciona se vc chamar o DataTable() duas vezes...
	tablePedidos.DataTable( {
		destroy: true
    } );
});
function somarValoresProdutosSelecionados(){
	
	var inputElements = document.getElementsByClassName('productos');
	var somaDosValores = 0.00;
	var contador = 0;
	
	for(var i=0; inputElements[i]; ++i){
	      if(inputElements[i].checked == true && inputElements[i].disabled == false){
	    	contador++;
	        var valorProduto = parseFloat(inputElements[i].value);
	        somaDosValores += valorProduto;
	        document.getElementById('somaProdutosSelecionados').innerHTML = somaDosValores.toFixed(2);
	      }
	}	
	if(contador == 0){
		document.getElementById('somaProdutosSelecionados').innerHTML = '';
	}
}
function pagarProdutosSelecionados(){
	
	var inputElements = document.getElementsByClassName('productos');
	var contador = 0;
	var chkProdutosLength = $('input.productos:checked').length;
	
	if( chkProdutosLength > 0 == true){
		if(saldoDevedorPago() == false){
			for(var i=0; inputElements[i]; ++i){
				if(inputElements[i].checked == true && inputElements[i].disabled == false){
					contador++;
					inputElements[i].checked=true;
					inputElements[i].disabled=true;
	        
					var valorProduto = inputElements[i].value;
					descontaValorSaldoDevedor(valorProduto);
	      		}
			}	
			if(contador > 0){
				document.getElementById('somaProdutosSelecionados').innerHTML = '';
			}
		}else{
			alert('O saldo devedor dessa conta é igual a 0.')
		}	
	}else{
		alert('Selecione algum produto.');
	}
}
function desmarcarProdutos(){
	
	var inputElements = document.getElementsByClassName('productos');
	
	for(var i=0; inputElements[i]; ++i){
		var produto = inputElements[i];
				if(produto.checked == true){
					produto.checked=false;		      				
				}
	      	}
	
	document.getElementById('somaProdutosSelecionados').innerHTML = '';
}
function selecionarTodosProdutos(){
	
	var inputElements = document.getElementsByClassName('productos');
	var somaDosValores = 0.00;
	
	for(var i=0; inputElements[i]; ++i){
		var produto = inputElements[i];
				if(produto.disabled == false){
					produto.checked=true;		      		
		      	 	var valorProduto = parseFloat(produto.value);
			        somaDosValores += valorProduto;					
				}
	}
	
	document.getElementById('somaProdutosSelecionados').innerHTML = somaDosValores.toFixed(2);
}
function descontaValorSaldoDevedor(valor){
	
	var valorDevedorHtml = document.getElementById('saldoDevedor').innerHTML;
	//convertendo para escala decimal para evitar numeros astronomicos
	
	var valorSolicitado = parseFloat(valor).toFixed(2);
	var valorDevedor = parseFloat(valorDevedorHtml).toFixed(2);
	
	if((valorDevedor - valorSolicitado) >= 0){
		var valorAtual = valorDevedor - valorSolicitado;
		document.getElementById('saldoDevedor').innerHTML = valorAtual.toFixed(2);	
	}else{
		console.log('O valorSolicitado é maior que o saldoDevedor');
	}
	
}
function saldoDevedorPago(){
	var valorDevedor = document.getElementById('saldoDevedor').innerHTML;
	
	if(valorDevedor > 0.0){
		return false;
	}else if(valorDevedor == 0.0){
		return true;
	}
}

function confirmarPagamentoPedido(idPedido){
		$.ajax({
	        url:  "http://localhost:8080/desafiotecnico/pedido/confirmStatus/"+idPedido,
	        dataType: 'JSON',
	        cache: false,
	        success: function (data) {
	        	console.log(data.message);
	            if (data.message == 'true') { 
	                alert("PEDIDO " +idPedido +" CONFIRMADO.");
	                location.reload();
	            } else if(data.message == 'false'){
	            	alert("PEDIDO " +idPedido +" NÃO CONFIRMADO.");
	            }
	        }
	    });
}
function cancelarPagamentoPedido(idPedido){

		$.ajax({
	        url:  "http://localhost:8080/desafiotecnico/pedido/refuseStatus/"+idPedido,
	        dataType: 'JSON',
	        cache: false,
	        success: function (data) {
	        	console.log(data.message);
	            if (data.message == 'true') { 
	                alert("PEDIDO " +idPedido +" CANCELADO.");
	                location.reload();
	            } else if(data.message == 'false'){
	            	alert("PEDIDO " +idPedido +" NÃO CANCELADO.");
	            }
	        }
	    });
}