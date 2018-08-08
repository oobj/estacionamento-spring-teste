package br.com.oobj.estacionamento.dao;

import br.com.oobj.estacionamento.modelo.Veiculo;

public interface EstacionamentoDAO {
	
	void salvar(Veiculo veiculo);
	
	Veiculo buscarUltimoEstacionamentoPorPlaca(String placa);

}
