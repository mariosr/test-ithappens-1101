package br.ithappens.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.ithappens.auxiliary.StatusPedido;
import br.ithappens.models.ItensPedido;
import br.ithappens.models.PedidoEstoque;
import br.ithappens.models.Produto;

public class EntradaSaidaProdutos {

	   @Test
	    public void testarMetodosGettersESetters() {
	        PedidoEstoque pe = new PedidoEstoque(); 
	        
	        pe.setId(new Long(1));
	        pe.setObservacao("observacao teste, deve ser diferente de null");
	        pe.setStatus(StatusPedido.ATIVO);
	        

	        assertEquals(new Long(1), pe.getId());
	        assertNotNull(pe.getObservacao());
	        assertEquals(StatusPedido.ATIVO, pe.getStatus());
	    }
	   
	   @Test
	    public void testarSomaValorTotalItensPedido() { 
	        Produto p = new Produto();
		    p.setId(new Long(1));
		    p.setQuantidadeEstoque(30);
		    p.setValor(10.00);
	        
	        List<ItensPedido> ipList = new ArrayList<ItensPedido>();
	        ItensPedido ip = new ItensPedido();
	        ip.setId(new Long(1));
	        ip.setProduto(p);
	        ip.setQuantidade(20);
	        //método responsável por configurar os valores * quantidade.
	        ip.configureValorTotal();
	        
	        ipList.add(ip);

	        assertEquals(new Double(200.00), ip.getValorTotal());
	    }
	   
	   @Test
	    public void testarMudancaStatusPedido() {
	        PedidoEstoque pe = new PedidoEstoque(); 
	        
	        pe.setId(new Long(1));
	        pe.setStatus(StatusPedido.PROCESSADO);

	        assertEquals(StatusPedido.PROCESSADO, pe.getStatus());
	    }
	   
}
