package br.com.desafio.usuario.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.desafio.usuario.controller.dto.CargoDTO;
import br.com.desafio.usuario.controller.form.CargoForm;
import br.com.desafio.usuario.model.Cargo;
import br.com.desafio.usuario.service.CargoService;

@RestController
@RequestMapping(path = "/cargos")
public class CargoController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired 
	private CargoService service;
	
	@GetMapping
	public List<CargoDTO> cargos() {
		
		
		List<Cargo> listCargos = new ArrayList<Cargo>();
		try {
			listCargos = service.findAll();
		} catch (Exception e) {
			logger.error("CargoController - buscar: Erro ao buscar cargos" + e.getMessage());
		}
		
		return CargoDTO.converter(listCargos);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CargoDTO> cadastrarCargo(@RequestBody @Valid CargoForm form,  UriComponentsBuilder uribBuilder){
		
		Cargo cargo = new Cargo();
		try {
			cargo = service.converterCargoFormParaEntity(form);
		} catch (Exception e) {
			logger.error("CargoController - buscar: Erro ao converter cargos" + e.getMessage());
		}
		
		try {
			service.save(cargo);
		} catch (Exception e) {
			logger.error("CargoController - salvar: Erro ao salvar cargos" + e.getMessage());
		}
		
		URI uri = uribBuilder.path("/{idUsuario}").buildAndExpand(cargo.getIdCargo()).toUri();
		return ResponseEntity.created(uri).body(new CargoDTO(cargo));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		
		try {
			Optional<Cargo> optional = service.findById(id);
			if(optional.isPresent()) {
				service.deleteById(id);
				return ResponseEntity.ok().build();
			}
		} catch (Exception e) {
			logger.error("CargoController - remover: Erro ao remover cargos" + e.getMessage());
		}
		
		return ResponseEntity.notFound().build();

	}
}
