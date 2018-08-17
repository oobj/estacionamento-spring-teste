package br.com.oobj.estacionamento.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.oobj.estacionamento.dao.EstacionamentoDao;
import br.com.oobj.estacionamento.modelo.TipoVeiculo;
import br.com.oobj.estacionamento.modelo.Veiculo;

@Repository
public class EstacionamentoJDBCDAO implements EstacionamentoDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public EstacionamentoJDBCDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void salvar(Veiculo veiculo) {
		// TODO Auto-generated method stub

	}

	public Veiculo buscarUltimoEstacionamentoPorPlaca(String placa) {
		String sql = "SELECT placa, tipo_veiculo, data_hora_entrada, data_hora_saida "
				+ "FROM veiculo WHERE placa = ? ORDER BY data_hora_entrada DESC LIMIT 1";
		
		return jdbcTemplate.queryForObject(sql, 
				new Object[] { placa }, 
				new VeiculoRowMapper());
	}
	
	
	
	
	
	private class VeiculoRowMapper implements RowMapper<Veiculo> {

		public Veiculo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Veiculo veiculo = new Veiculo();
			veiculo.setPlaca(rs.getString("placa"));
			veiculo.setDataHoraEntrada(rs.getTimestamp("data_hora_entrada"));
			veiculo.setDataHoraSaida(rs.getTimestamp("data_hora_saida"));
			veiculo.setTipoVeiculo(TipoVeiculo.valueOf(rs.getString("tipo_veiculo")));
			return veiculo;
		}
		
	}

}
