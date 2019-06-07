package br.ithappens.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.ithappens.models.Filial;
import br.ithappens.models.Produto;
import br.ithappens.services.FilialService;
import br.ithappens.services.ProdutoService;

@Named
@RequestMapping("produto")
public class ProdutoController {

	@Inject
	private ProdutoService ps;
	
	@Inject
	private FilialService fs;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Model mod) {

		ModelAndView model = new ModelAndView("produto/produto");
		model.addObject("produtos", ps.findAll());

		// necessario para o formulario saveProduto
		mod.addAttribute("Produto", new Produto());

		return model;
	}

	@RequestMapping(value = "/categoriaproduto/", method = RequestMethod.GET)
	public ModelAndView categoriaProduto(Model mod) {

		ModelAndView model = new ModelAndView("produto/categoriaProduto");

		return model;
	}

	@RequestMapping(value = "/getProdutos", method = RequestMethod.GET)
	public @ResponseBody List<Produto> getProdutos() {

		List<Produto> produtos = ps.findAll();
		return produtos;
	}

	
	@RequestMapping(value = "/saveProduto", method = RequestMethod.POST)
	public String saveProduto(
			@RequestParam(value = "nome", required = true) String nome,
			@RequestParam(value = "filial", required = true) Integer filial,
			@RequestParam(value = "valor", required = false) Double valor,
			@RequestParam(value = "sequencial", required = true) String sequencial,
			@RequestParam(value = "codigobarra", required = true) String codigobarra,
			@RequestParam(value = "descricao", required = true) String descricao,
			@RequestParam(value = "quantidadeEstoque", required = true) Integer quantidadeEstoque) {

		Produto p = new Produto();
		p.setCodigoBarra(codigobarra);
		p.setDescricao(descricao);
		p.setNome(nome);
		p.setQuantidadeEstoque(quantidadeEstoque);
		p.setSequencial(sequencial);
		p.setValor(valor);		
		ps.save(p);
		
		Filial f = fs.findById(filial);
		f.getProdutos().add(p);
		fs.save(f);
		
		return "redirect:/produto/";
	}

}
