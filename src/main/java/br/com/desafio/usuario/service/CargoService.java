package br.com.desafio.usuario.service;

import java.util.List;
import java.util.Optional;

import br.com.desafio.usuario.controller.form.CargoForm;
import br.com.desafio.usuario.model.Cargo;

public interface CargoService {

	public List<Cargo> findAll() throws Exception;

	public Cargo converterCargoFormParaEntity(CargoForm form) throws Exception;

	public void save(Cargo cargo) throws Exception;

	public Optional<Cargo> findById(Long id) throws Exception;

	public void deleteById(Long id) throws Exception;
	
}
