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
import br.ithappens.services.FilialService;

@Named
@RequestMapping("filial")
public class FilialController {

	@Inject
	private FilialService fs;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Model mod) {

		ModelAndView model = new ModelAndView("filial/filial");
		model.addObject("filiais", fs.findAll());
	
		return model;
	}

	@RequestMapping(value = "/getFiliais", method = RequestMethod.GET)
	public @ResponseBody List<Filial> getFiliais() {
		List<Filial> filiais = fs.findAll();
		return filiais;
	}

	@RequestMapping(value = "/saveFilial", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String saveFilial(@RequestParam(value = "nome", required = true) String name) {

		Filial f = new Filial();
		f.setNome(name);
		fs.save(f);

		return "redirect:/filial/";
	}

}
