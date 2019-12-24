package br.com.desafio.usuario.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.usuario.controller.form.CargoForm;
import br.com.desafio.usuario.model.Cargo;
import br.com.desafio.usuario.repository.CargoRepository;
import br.com.desafio.usuario.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService{

	@Autowired
	private CargoRepository repository;
	
	@Override
	public List<Cargo> findAll() throws Exception{
		return repository.findAll();
	}

	@Override
	public Cargo converterCargoFormParaEntity(CargoForm form) throws Exception{
		// TODO Auto-generated method stub
		return new Cargo(form.getNomeCargo());
	}

	@Override
	public void save(Cargo cargo) throws Exception{
		repository.save(cargo);
		
	}

	@Override
	public Optional<Cargo> findById(Long id) throws Exception{
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public void deleteById(Long id) throws Exception{
		repository.deleteById(id);
		
	}

}
