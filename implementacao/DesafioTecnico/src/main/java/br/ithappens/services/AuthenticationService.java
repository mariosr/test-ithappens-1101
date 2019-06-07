package br.ithappens.services;

import br.ithappens.models.Usuario;

public interface AuthenticationService {

	public Usuario login(String login, String senha);

	public Usuario getComandanteByIdOPM(Integer idUser, Integer currentRole);

}