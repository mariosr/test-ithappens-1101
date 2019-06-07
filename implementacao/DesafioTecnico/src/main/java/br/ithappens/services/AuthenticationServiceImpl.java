package br.ithappens.services;

import javax.inject.Inject;
import javax.inject.Named;

import br.ithappens.models.Usuario;
import br.ithappens.repository.AuthenticationRepository;

@Named
public class AuthenticationServiceImpl implements AuthenticationService {

	@Inject
	private AuthenticationRepository authenticationRepository;

	public AuthenticationServiceImpl() {
	}

	@Override
	public Usuario login(String login, String senha) {
		return authenticationRepository.login(login, senha);
	}
	
	@Override
	public Usuario getComandanteByIdOPM(Integer idOpm, Integer currentRole) {
		return authenticationRepository.getComandanteByIdOPM(idOpm, currentRole);
	}

}
