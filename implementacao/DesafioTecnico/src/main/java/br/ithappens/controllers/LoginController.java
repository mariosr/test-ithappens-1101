package br.ithappens.controllers;

import java.security.Principal;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.ithappens.models.Usuario;
import br.ithappens.services.AuthenticationService;

@Named
@Controller
public class LoginController {

	@Inject
	private AuthenticationService as;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {

		ModelAndView model = new ModelAndView("home/home");

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Usuário e/ou senha inválidos!");
		}

		if (logout != null) {
			model.addObject("msg", "Logado com sucesso!");
		}
		model.setViewName("login");

		return model;
	}

	@RequestMapping(value = "/getcomandantebyidopm", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody Usuario getComandanteByIdOPM(@RequestParam(value = "idOpm", required = true) Integer idOpm,
			@RequestParam(value = "currentRole", required = true) Integer currentRole) {

		return as.getComandanteByIdOPM(idOpm, currentRole);
	}

	@RequestMapping(value = "/authentication", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody Usuario realizarLogin(@RequestParam(value = "login", required = true) String login,
			@RequestParam(value = "senha", required = true) String senha) throws Exception {

		Usuario usuario = as.login(login, senha);
		if (usuario != null) {
			return usuario;
		} else {
			throw new Exception("Login Incorreto...");
		}

	}

	@RequestMapping(value = "/checkDataToAlert", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public void checkDataToAlert(@RequestParam(value = "idUser", required = true) Integer idUser,
			@RequestParam(value = "idBpm", required = true) Integer idBpm,
			@RequestParam(value = "idCia", required = true) Integer idCia) {

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpSession session) {
		session.invalidate();
		return "redirect:/login";

	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String acessoNegado(ModelMap model, Principal user) {
		if (user != null) {
			model.addAttribute("message",
					"Olá usuário " + user.getName() + ", você não tem permissão para acessar essa página!");
		} else {
			model.addAttribute("message", "Você não tem permissão para acessar essa página!");
		}
		return "403";
	}
}