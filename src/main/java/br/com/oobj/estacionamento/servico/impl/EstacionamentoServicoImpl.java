package br.com.oobj.estacionamento.servico.impl;

import java.util.Date;

import br.com.oobj.estacionamento.dao.EstacionamentoDAO;
import br.com.oobj.estacionamento.modelo.Veiculo;
import br.com.oobj.estacionamento.servico.EstacionamentoServico;

public class EstacionamentoServicoImpl implements EstacionamentoServico {

	private EstacionamentoDAO dao;

	public EstacionamentoServicoImpl(EstacionamentoDAO dao) {
		this.dao = dao;
	}

	public void registrarEntrada(Veiculo veiculo) {
		veiculo.setDataHoraEntrada(new Date());
		
		// chamar o dao para salvar no banco de dados.
		this.dao.salvar(veiculo);
	}

	public void registrarSaida(Veiculo veiculo) {
		// buscar a ultima entrada do veiculo pela placa
		veiculo = this.dao.buscarUltimoEstacionamentoPorPlaca(veiculo.getPlaca());
		
		veiculo.setDataHoraSaida(new Date());
		
		// salvar esse veiculo
		this.dao.salvar(veiculo);
	}

	public Double calcularValor(Veiculo veiculo) {
		veiculo = dao.buscarUltimoEstacionamentoPorPlaca(veiculo.getPlaca());
		
		return veiculo.getTipoVeiculo().getCalculadora().calcular(veiculo);
	}

}
