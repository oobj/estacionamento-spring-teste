package br.com.oobj.estacionamento.servico.impl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.com.oobj.estacionamento.modelo.Veiculo;
import br.com.oobj.estacionamento.servico.EstacionamentoServico;

public class EstacionamentoServicoImplTest {

	private EstacionamentoServico sut;
	private Veiculo veiculo;

	@Before
	public void setup() throws Exception {
		sut = new EstacionamentoServicoImpl();
		veiculo = new Veiculo();
	}

	@Test
	public void deve_registrar_entrada_do_veiculo() throws Exception {
		// Execução
		sut.registrarEntrada(veiculo);

		// Verificação
		assertNotNull(veiculo.getDataHoraEntrada());
	}

	@Test
	public void deve_registrar_saida_do_veiculo() throws Exception {
		// Execução
		sut.registrarSaida(veiculo);

		// Verificação
		assertNotNull("Data hora saída do veículo não deveria ser nula", veiculo.getDataHoraSaida());
	}

	@Test
	public void deve_cobrar_2_reais_para_carro_com_menos_de_30_minutos_estacionado() throws Exception {

		// Preparação
		Calendar dataHoraEntrada = Calendar.getInstance();
		dataHoraEntrada.set(2018, Calendar.AUGUST, 6, 20, 0, 0);
		veiculo.setDataHoraEntrada(dataHoraEntrada.getTime());

		Calendar dataHoraSaida = Calendar.getInstance();
		dataHoraSaida.set(2018, Calendar.AUGUST, 6, 20, 25, 0);
		veiculo.setDataHoraSaida(dataHoraSaida.getTime());

		// Execução
		Double valorEstacionamento = sut.calcularValor(veiculo);

		// Verificação
		assertEquals(2.0, valorEstacionamento, 0.0001);
	}

	@Test
	public void deve_cobrar_4_reais_para_carro_que_ficou_30_minutos_estacionado() throws Exception {
		// Preparação
		Calendar dataHoraEntrada = Calendar.getInstance();
		dataHoraEntrada.set(2018, Calendar.AUGUST, 6, 20, 0, 0);
		veiculo.setDataHoraEntrada(dataHoraEntrada.getTime());

		Calendar dataHoraSaida = Calendar.getInstance();
		dataHoraSaida.set(2018, Calendar.AUGUST, 6, 20, 30, 0);
		veiculo.setDataHoraSaida(dataHoraSaida.getTime());

		// Execução
		Double valorEstacionamento = sut.calcularValor(veiculo);

		// Verificação
		assertEquals(4.0, valorEstacionamento, 0.0001);
	}

	@Test
	public void deve_tolerar_carro_ate_15_minutos_estacionado() throws Exception {
		// Preparação
		Calendar dataHoraEntrada = Calendar.getInstance();
		dataHoraEntrada.set(2018, Calendar.AUGUST, 6, 20, 0, 0);
		veiculo.setDataHoraEntrada(dataHoraEntrada.getTime());

		Calendar dataHoraSaida = Calendar.getInstance();
		dataHoraSaida.set(2018, Calendar.AUGUST, 6, 20, 14, 0);
		veiculo.setDataHoraSaida(dataHoraSaida.getTime());

		// Execução
		Double valorEstacionamento = sut.calcularValor(veiculo);

		// Verificação
		assertEquals(0.0, valorEstacionamento, 0.0001);
	}

	@Test
	public void deve_cobrar_26_reais_de_carro_estacionado_a_mais_de_5_horas_e_menos_6_horas() throws Exception {
		// Preparação
		Calendar dataHoraEntrada = Calendar.getInstance();
		dataHoraEntrada.set(2018, Calendar.AUGUST, 6, 10, 0, 0);
		veiculo.setDataHoraEntrada(dataHoraEntrada.getTime());

		Calendar dataHoraSaida = Calendar.getInstance();
		dataHoraSaida.set(2018, Calendar.AUGUST, 6, 15, 45, 0);
		veiculo.setDataHoraSaida(dataHoraSaida.getTime());

		// Execução
		Double valorEstacionamento = sut.calcularValor(veiculo);

		// Verificação
		assertEquals(26.0, valorEstacionamento, 0.0001);
	}
}
