package br.com.oobj.estacionamento.servico.impl;

import java.util.Date;

import br.com.oobj.estacionamento.modelo.Veiculo;
import br.com.oobj.estacionamento.servico.EstacionamentoServico;

public class EstacionamentoServicoImpl implements EstacionamentoServico {

	public void registrarEntrada(Veiculo veiculo) {
		veiculo.setDataHoraEntrada(new Date());
	}

	public void registrarSaida(Veiculo veiculo) {
		veiculo.setDataHoraSaida(new Date());
	}

	public Double calcularValor(Veiculo veiculo) {
		return veiculo.getTipoVeiculo().getCalculadora().calcular(veiculo);
	}

}
