package br.ithappens.services;

import java.util.List;

import br.ithappens.models.Filial;

public interface FilialService {

	public abstract void save(Filial f);

	public abstract List<Filial> findAll();

	public Filial findById(Integer id);
	
	public void remove(Filial f);

}