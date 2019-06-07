package br.ithappens.repository;

import br.ithappens.models.Usuario;

public interface AuthenticationRepository {

	public Usuario login(String login, String senha);
	
	public Usuario getComandanteByIdOPM(Integer idOpm, Integer currentRole);

	public boolean isComandanteGeral(Integer idUser);

	public boolean isGrandeComando(Integer idUser);


}