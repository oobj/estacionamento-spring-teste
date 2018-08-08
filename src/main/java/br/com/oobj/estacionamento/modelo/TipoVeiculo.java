package br.com.oobj.estacionamento.modelo;

import br.com.oobj.estacionamento.servico.calculadoras.CalculadoraEstacionamento;
import br.com.oobj.estacionamento.servico.calculadoras.impl.CalculadoraCarro;
import br.com.oobj.estacionamento.servico.calculadoras.impl.CalculadoraMoto;;

public enum TipoVeiculo {
	
	CARRO(new CalculadoraCarro()),
	MOTO(new CalculadoraMoto());
	
	private final CalculadoraEstacionamento calculadora;
	
	private TipoVeiculo(CalculadoraEstacionamento calculadora) {
		this.calculadora = calculadora;
	}

	public CalculadoraEstacionamento getCalculadora() {
		return calculadora;
	}
}
