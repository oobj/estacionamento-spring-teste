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
		Long miliSaida = veiculo.getDataHoraSaida().getTime();
		Long miliEntrada = veiculo.getDataHoraEntrada().getTime();
		
		Long difMili = miliSaida - miliEntrada;
		Long minutos = (difMili / 1000) / 60;
		
		if(minutos < 15) {
			return 0.0;
		}
		
		
		Long horas = minutos / 60;
		
		if (horas < 4) {
			return Double.valueOf(( minutos / 30) + 1 ) * 2;
		} else {
			return Double.valueOf(16.0 + ((horas - 4 + 1 ) * 5));
		}

	}

}
