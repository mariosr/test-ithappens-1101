package br.ithappens.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.ithappens.auxiliary.PessoaSexo;
import br.ithappens.models.Papel;
import br.ithappens.models.Usuario;
import br.ithappens.services.PapelService;
import br.ithappens.services.UsuarioService;

@Named
@RequestMapping("usuario")
public class UsuarioController {

	@Inject
	private UsuarioService ps;
	@Inject
	private PapelService pls;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Model mod) {

		ModelAndView model = new ModelAndView("pessoa/pessoa");
		model.addObject("pessoas", ps.findAll());
		model.addObject("sexos", PessoaSexo.values());

		// necessario para o formulario savePessoa
		mod.addAttribute("Pessoa", new Usuario());

		return model;
	}

	@RequestMapping(value = "/getPessoas", method = RequestMethod.GET)
	public @ResponseBody List<Usuario> getPessoas() {

		List<Usuario> pessoas = ps.findAll();
		return pessoas;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String processRegistration(@RequestParam(value = "papeis", required = true) String role,
			@RequestParam(value = "login", required = true) String login,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "nome", required = true) String name,
			@RequestParam(value = "cpf", required = true) String cpf,
			@RequestParam(value = "idade", required = true) Integer age,
			@RequestParam(value = "sexo", required = true) String gender) {

		Usuario pessoa = new Usuario();
		pessoa.setNome(name);

		List<Papel> papeis = new ArrayList<Papel>();
		Papel papel = pls.findByName(role);
		papeis.add(papel);

		pessoa.setPapeis(papeis);
		pessoa.setLogin(login);
		pessoa.setIdade(age);

		try {
			pessoa.setPassword(generateHash256(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		pessoa.setCpf(cpf);
		pessoa.setSexo(PessoaSexo.valueOf(gender));

		ps.save(pessoa);

		return "redirect:/pessoa/";
	}

	public String generateHash256(String password) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		return hexString.toString();
	}
}
