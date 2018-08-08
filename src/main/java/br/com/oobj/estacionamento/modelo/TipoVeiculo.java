package br.com.oobj.estacionamento.modelo;

import br.com.oobj.estacionamento.servico.CalculadoraEstacionamento;
import br.com.oobj.estacionamento.servico.impl.CalculadoraCarro;
import br.com.oobj.estacionamento.servico.impl.CalculadoraMoto;;

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
