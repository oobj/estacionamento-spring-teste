package br.com.oobj.estacionamento.dao;

import br.com.oobj.estacionamento.modelo.Veiculo;

public interface EstacionamentoDao {

	void salvar(Veiculo veiculo);
	
	Veiculo buscarUltimoEstacionamentoPorPlaca(String placa);
}
