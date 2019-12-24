package br.com.desafio.usuario.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.desafio.usuario.model.Perfil;
import br.com.desafio.usuario.repository.PerfilRepositoryJDBC;

@Repository
public class PerfilRepositoryJDBCImpl implements PerfilRepositoryJDBC{

	@Autowired
	 private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public void save(Perfil perfil) throws Exception {
		String sql = "INSERT INTO PERFIL (NOME_PERFIL) VALUES (:nomePerfil)";
		
				Map<String, Object> namedParameters = new HashMap<String, Object>();
		
				namedParameters.put("nomePerfil", perfil.getNome());
				SqlParameterSource params = new MapSqlParameterSource(namedParameters);
				
				namedParameterJdbcTemplate.update(sql, params);
	}

}
