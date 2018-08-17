package br.com.oobj.estacionamento.dao.impl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.oobj.estacionamento.dao.EstacionamentoDao;
import br.com.oobj.estacionamento.modelo.TipoVeiculo;
import br.com.oobj.estacionamento.modelo.Veiculo;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
@Sql(value = "classpath:load-database.sql", 
		executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:clean-database.sql", 
		executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
public class EstacionamentoJDBCDAOTest {

	@Autowired
	private EstacionamentoDao sut;
	
	@Test
	public void deve_buscar_ultimo_estacionamento_do_veiculo_pela_placa() throws Exception {
		Veiculo veiculo = sut.buscarUltimoEstacionamentoPorPlaca("AAA1234");
		
		assertNotNull(veiculo);
		assertEquals("AAA1234", veiculo.getPlaca());
		assertEquals(TipoVeiculo.CARRO, veiculo.getTipoVeiculo());
		
		Calendar dataHoraEntrada = Calendar.getInstance();
		dataHoraEntrada.setTime(veiculo.getDataHoraEntrada());
		assertEquals(2018, dataHoraEntrada.get(Calendar.YEAR));
		assertEquals(Calendar.AUGUST, dataHoraEntrada.get(Calendar.MONTH));
		assertEquals(9, dataHoraEntrada.get(Calendar.DAY_OF_MONTH));
		assertEquals(13, dataHoraEntrada.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, dataHoraEntrada.get(Calendar.MINUTE));
		assertEquals(0, dataHoraEntrada.get(Calendar.SECOND));
		
		Calendar dataHoraSaida = Calendar.getInstance();
		dataHoraSaida.setTime(veiculo.getDataHoraSaida());
		assertEquals(2018, dataHoraSaida.get(Calendar.YEAR));
		assertEquals(Calendar.AUGUST, dataHoraSaida.get(Calendar.MONTH));
		assertEquals(9, dataHoraSaida.get(Calendar.DAY_OF_MONTH));
		assertEquals(13, dataHoraSaida.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, dataHoraSaida.get(Calendar.MINUTE));
		assertEquals(0, dataHoraSaida.get(Calendar.SECOND));
		
	}
	
}
