package br.com.desafio.usuario.service;

import java.util.List;
import java.util.Optional;

import br.com.desafio.usuario.controller.form.PerfilForm;
import br.com.desafio.usuario.model.Perfil;

public interface PerfilService {

	public List<Perfil> findAll() throws Exception;

	public void save(Perfil perfil) throws Exception;

	public Optional<Perfil> findById(Long id) throws Exception;

	public void deleteById(Long id) throws Exception;

	public Perfil converterCargoFormParaEntity(PerfilForm form) throws Exception;

}
