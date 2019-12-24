package br.com.desafio.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.usuario.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{

	public Perfil findByIdPerfil(Long idPerfil);

}
