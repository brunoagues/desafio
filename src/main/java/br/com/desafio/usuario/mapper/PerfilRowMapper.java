package br.com.desafio.usuario.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.desafio.usuario.model.Perfil;
import br.com.desafio.usuario.model.Usuario;

public class PerfilRowMapper implements RowMapper<Perfil> {

	@Override
	public Perfil mapRow(ResultSet rs, int rowNum) throws SQLException {
		Perfil perfil = new Perfil();
		
		perfil.setIdPerfil(rs.getLong("ID_PERFIL"));
		perfil.setNome(rs.getString("NOME_PERFIL"));
		
		return perfil;
	}


}
