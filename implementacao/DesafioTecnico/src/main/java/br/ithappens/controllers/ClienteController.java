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

import br.ithappens.models.Cliente;
import br.ithappens.services.ClienteService;

@Named
@RequestMapping("cliente")
public class ClienteController {

	@Inject
	private ClienteService cs;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Model mod) {

		ModelAndView model = new ModelAndView("cliente/cliente");
		model.addObject("clientes", cs.findAll());
		// necessario para o formulario saveCliente
		mod.addAttribute("Cliente", new Cliente());
		return model;
	}

	@RequestMapping(value = "/getClientes", method = RequestMethod.GET)
	public @ResponseBody List<Cliente> getClientes() {
		List<Cliente> clientes = cs.findAll();
		return clientes;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String saveCliente(@RequestParam(value = "nome", required = true) String name,
			@RequestParam(value = "cpf", required = false) String cpf,
			@RequestParam(value = "celular", required = false) String celular,
			@RequestParam(value = "email", required = false) String email) {

		Cliente cliente = cs.findByCpf(cpf);
		if (cliente == null)
			cliente = new Cliente();

		cliente.setNome(name);
		cliente.setCpf(cpf.length() > 0 ? cpf : null);
		cliente.setCelular(celular.length() > 0 ? celular : null);
		cliente.setEmail(email.length() > 0 ? email : null);

		cs.save(cliente);

		return "redirect:/cliente/";
	}

}
