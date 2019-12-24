package br.com.desafio.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.usuario.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{

	public Cargo findByIdCargo(Long idCargo);

}
