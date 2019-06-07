package br.ithappens.repository;

import java.util.List;

import br.ithappens.models.Papel;

public interface PapelRepository {

	public abstract void save(Papel c);

	public abstract List<Papel> findAll();
	
	public Papel findByName(String name);
	
	public void remove(Papel categoria);
	
}