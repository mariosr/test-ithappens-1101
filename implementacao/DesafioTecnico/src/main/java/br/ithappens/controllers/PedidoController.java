package br.ithappens.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.ithappens.auxiliary.ResponseStatus;
import br.ithappens.auxiliary.ResponseText;
import br.ithappens.auxiliary.StatusPedido;
import br.ithappens.models.Cliente;
import br.ithappens.models.Filial;
import br.ithappens.models.FormaPagamento;
import br.ithappens.models.ItensPedido;
import br.ithappens.models.PedidoEstoque;
import br.ithappens.models.Produto;
import br.ithappens.models.Usuario;
import br.ithappens.services.ClienteService;
import br.ithappens.services.FilialService;
import br.ithappens.services.PedidoService;
import br.ithappens.services.UsuarioService;

@Named
@RequestMapping("pedido")
public class PedidoController {

	@Inject
	private PedidoService ps;
	
	@Inject
	private UsuarioService us;
	
	@Inject
	private ClienteService cs;
	
	@Inject
	private FilialService fs;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homePedido(Model mod) {

		ModelAndView model = new ModelAndView("pedido/pedido");
		// necessario para o formulario savePedido
		mod.addAttribute("Pedido", new PedidoEstoque());
		model.addObject("pedidos", ps.findAll());

		return model;
	}

	@RequestMapping(value = "/saveFormaPagamento", method = RequestMethod.POST)
	public String saveFormaPagamento(
			@RequestParam(value = "formaPagamento", required = true) String formaPagamento,
			@RequestParam(value = "idPedido", required = true) Long idPedido) {

		PedidoEstoque p = ps.findById(idPedido);
		
		FormaPagamento fp = new FormaPagamento();
		fp.setNome(formaPagamento);
		
		p.setFormaPagamento(fp);
		
		ps.save(p);
		
		return "redirect:/pedido/";
	}

	
	@RequestMapping(value = "/savePedido", method = RequestMethod.POST)
	public String processRegistration(
			@RequestParam(value = "produtoSolicitado", required = true) List<Long> produtosSolicidados,
			@RequestParam(value = "qtdProdutos", required = true) List<Long> qtdProdutos,
			@RequestParam(value = "observacao", required = false) String observacao,
			@RequestParam(value = "filial", required = true) Integer filial,
			@RequestParam(value = "usuario", required = true) Integer usuario,
			@RequestParam(value = "cliente", required = true) Integer cliente) {

		Usuario u = us.findById(usuario);
		Cliente c = cs.findById(cliente);
		Filial f = fs.findById(filial);
		
		ps.solicitarPedido(produtosSolicidados, observacao, f, c, u, qtdProdutos);

		return "redirect:/pedido/";
	}

	@RequestMapping(value = "/getProdutosByIdPedido/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Produto> getProdutos(@PathVariable("id") Long id) {

		PedidoEstoque pedido = ps.findById(id);
		List<Produto> produtos = new ArrayList<Produto>();

		if (pedido != null) {

			List<ItensPedido> itensPedidos = pedido.getItensPedidos();
			
			for (ItensPedido ip : itensPedidos) {
				produtos.add(ip.getProduto());
			}
		} 

		return produtos;
	}

	@RequestMapping(value = "/getPedidoById/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody PedidoEstoque getPedidoById(@PathVariable("id") Long id) {

		PedidoEstoque pedido = ps.findById(id);
		return pedido;

	}

	@RequestMapping(value = "/getPedidos", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody List<PedidoEstoque> getPedidos() {

		List<PedidoEstoque> pedidos = ps.findAll();
		return pedidos;

	}
	
	@ResponseBody
	@RequestMapping(value = "/confirmStatus/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseText confirmarStatusPedido(@PathVariable("id") Long id) {
		
		ResponseText result = new ResponseText();
		PedidoEstoque pedido = ps.findById(id);
		
		if(pedido != null && pedido.getStatus() != StatusPedido.PROCESSADO){
			pedido.setStatus(StatusPedido.PROCESSADO);
			ps.save(pedido);
			result.setMessage(ResponseStatus.SUCCESS.getStatus());
		
		}else{
			  result.setMessage(ResponseStatus.FAILED.getStatus());
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/refuseStatus/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseText refuseStatusPedido(@PathVariable("id") Long id) {
		
		ResponseText result = new ResponseText();
		
		PedidoEstoque pedido = ps.findById(id);
		if(pedido != null && pedido.getStatus() == StatusPedido.ATIVO){
			pedido.setStatus(StatusPedido.CANCELADO);
			ps.save(pedido);
			result.setMessage(ResponseStatus.SUCCESS.getStatus());
		}else{
			result.setMessage(ResponseStatus.FAILED.getStatus());
		}
		return result;
	}

	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ModelAndView detailsPedido(@PathVariable("id") Long id) {

		PedidoEstoque pedido = ps.findById(id);

		ModelAndView model = new ModelAndView("pedido/pedidoDetalhes");

		model.addObject("pedido", pedido);
	
		return model;

	}

	public String formatDate(Date date) {

		String newString = "";

		if (date != null) {
			newString = new SimpleDateFormat("dd-MM-yyyy 'às' HH:mm")
					.format(date);
		}

		return newString;
	}

}
