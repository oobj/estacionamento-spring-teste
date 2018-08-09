package br.com.oobj.estacionamento.servico.impl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.oobj.estacionamento.dao.EstacionamentoDAO;
import br.com.oobj.estacionamento.modelo.TipoVeiculo;
import br.com.oobj.estacionamento.modelo.Veiculo;
import br.com.oobj.estacionamento.servico.EstacionamentoServico;

@RunWith(SpringRunner.class)
public class EstacionamentoServicoImplTest {
	
	private static final String PLACA = "AAA1234";

	@MockBean
	private EstacionamentoDAO daoMock;

	private EstacionamentoServico sut;
	private Veiculo veiculo;

	@Before
	public void setup() throws Exception {
		sut = new EstacionamentoServicoImpl(daoMock);
		veiculo = new Veiculo();
		veiculo.setPlaca(PLACA);
		
		Mockito.when(daoMock.buscarUltimoEstacionamentoPorPlaca(PLACA))
			.thenReturn(veiculo);
	}
	
	@Test
	public void deve_salvar_veiculo_no_processo_de_entrada_do_estacionamento() throws Exception {
		sut.registrarEntrada(veiculo);
		
		Mockito.verify(daoMock).salvar(veiculo);
	}
	
	@Test
	public void deve_buscar_veiculo_antes_de_salvar_o_registro_de_saida() throws Exception {
		// Preparação
		String placa = PLACA;
		veiculo.setPlaca(placa);
		
		// execução
		sut.registrarSaida(veiculo);
		
		// verificação
		Mockito.verify(daoMock).buscarUltimoEstacionamentoPorPlaca(placa);
	}
	
	@Test
	public void deve_buscar_ultima_interacao_do_veiculo_no_estacionamento_para_realizar_a_cobranca() throws Exception {
		// Preparação
		Veiculo veiculoDoBanco = new Veiculo();
		veiculoDoBanco.setPlaca(PLACA);
		veiculoDoBanco.setTipoVeiculo(TipoVeiculo.CARRO);
		
		Calendar dataHoraEntrada = Calendar.getInstance();
		dataHoraEntrada.set(2018, Calendar.AUGUST, 6, 20, 0, 0);
		veiculoDoBanco.setDataHoraEntrada(dataHoraEntrada.getTime());

		Calendar dataHoraSaida = Calendar.getInstance();
		dataHoraSaida.set(2018, Calendar.AUGUST, 6, 20, 25, 0);
		veiculoDoBanco.setDataHoraSaida(dataHoraSaida.getTime());
		
		veiculo.setPlaca(PLACA);
		
		Mockito.when(daoMock.buscarUltimoEstacionamentoPorPlaca(PLACA))
				.thenReturn(veiculoDoBanco);
		
		// Execuçao
		Double valor = sut.calcularValor(veiculo);
		
		//verificaçao
		assertEquals(2.0, valor, 0.0001);
	}
	
	@Test
	public void deve_salvar_registro_de_saida_do_veiculo() throws Exception {
		sut.registrarSaida(veiculo);
		
		Mockito.verify(daoMock).salvar(veiculo);
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
		veiculo.setTipoVeiculo(TipoVeiculo.CARRO);

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
	public void deve_cobrar_1_real_para_moto_com_menos_de_30_minutos_estacionado() throws Exception {
		veiculo.setTipoVeiculo(TipoVeiculo.MOTO);

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
		assertEquals(1.0, valorEstacionamento, 0.0001);
	}

	@Test
	public void deve_cobrar_4_reais_para_carro_que_ficou_30_minutos_estacionado() throws Exception {
		veiculo.setTipoVeiculo(TipoVeiculo.CARRO);

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
	public void deve_cobrar_2_reais_para_moto_que_ficou_30_minutos_estacionada() throws Exception {
		veiculo.setTipoVeiculo(TipoVeiculo.MOTO);

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
		assertEquals(2.0, valorEstacionamento, 0.0001);
	}

	@Test
	public void deve_tolerar_carro_ate_15_minutos_estacionado() throws Exception {
		veiculo.setTipoVeiculo(TipoVeiculo.CARRO);

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
	public void deve_tolerar_moto_com_menos_de_15_minutos_estacionada() throws Exception {
		veiculo.setTipoVeiculo(TipoVeiculo.MOTO);

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
		veiculo.setTipoVeiculo(TipoVeiculo.CARRO);

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
	
	@Test
	public void deve_cobrar_14_reais_de_moto_estacionada_a_mais_de_5_horas_e_menos_de_6_horas() throws Exception {
		veiculo.setTipoVeiculo(TipoVeiculo.MOTO);

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
		assertEquals(14.0, valorEstacionamento, 0.0001);
	}
}
