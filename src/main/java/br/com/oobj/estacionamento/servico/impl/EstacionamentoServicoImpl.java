package br.com.oobj.estacionamento.servico.impl;

import java.util.Date;

import br.com.oobj.estacionamento.modelo.Veiculo;
import br.com.oobj.estacionamento.servico.EstacionamentoServico;

public class EstacionamentoServicoImpl implements EstacionamentoServico {

	public void registrarEntrada(Veiculo veiculo) {
		veiculo.setDataHoraEntrada(new Date());
		
		// salvar registro do veículo no banco (novo registro)
	}

	public void registrarSaida(Veiculo veiculo) {
		// buscar última entrada do veículo no estacionamento pela placa
		veiculo.setDataHoraSaida(new Date());
		
		// atualizar registro do veiculo no banco
	}

	public Double calcularValor(Veiculo veiculo) {
		// buscar ultimo registro do veiculo no banco
		
		return veiculo.getTipoVeiculo().getCalculadora().calcular(veiculo);
	}

}
