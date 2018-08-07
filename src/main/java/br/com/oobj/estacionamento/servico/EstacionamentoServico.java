package br.com.oobj.estacionamento.servico;

import br.com.oobj.estacionamento.modelo.Veiculo;

public interface EstacionamentoServico {

	void registrarEntrada(Veiculo veiculo);
	
	void registrarSaida(Veiculo veiculo);
	
	Double calcularValor(Veiculo veiculo);
}
