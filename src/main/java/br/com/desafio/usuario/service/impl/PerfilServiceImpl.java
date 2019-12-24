package br.com.desafio.usuario.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.usuario.controller.form.PerfilForm;
import br.com.desafio.usuario.model.Perfil;
import br.com.desafio.usuario.repository.PerfilRepositoryJDBC;
import br.com.desafio.usuario.repository.PerfilRepository;
import br.com.desafio.usuario.service.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService{

	@Autowired
	private PerfilRepository repository;
	
	@Autowired
	private PerfilRepositoryJDBC repositoryJdbc;
	
	@Override
	public List<Perfil> findAll() throws Exception {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void save(Perfil perfil) throws Exception {
		repositoryJdbc.save(perfil);
		
	}

	@Override
	public Optional<Perfil> findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Perfil converterCargoFormParaEntity(PerfilForm form) {
		// TODO Auto-generated method stub
		return new Perfil(form);
	}

}
