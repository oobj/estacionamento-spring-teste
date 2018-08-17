package br.com.oobj.estacionamento.modelo;

import br.com.oobj.estacionamento.servico.calculadoras.CalculadoraEstacionamento;
import br.com.oobj.estacionamento.servico.calculadoras.impl.CalculadoraCarro;
import br.com.oobj.estacionamento.servico.calculadoras.impl.CalculadoraMoto;

public enum TipoVeiculo {
	
	CARRO(new CalculadoraCarro()),
	MOTO(new CalculadoraMoto());
	
	private CalculadoraEstacionamento calculadora;
	
	private TipoVeiculo(CalculadoraEstacionamento calculadoraEstacionamento) {
		this.calculadora = calculadoraEstacionamento;
	}
	
	public CalculadoraEstacionamento getCalculadora() {
		return calculadora;
	}
	
}


//public class TipoVeiculo {
//	public static final TipoVeiculo CARRO = new TipoVeiculo(new CalculadoraCarro());
//	public static final TipoVeiculo MOTO = new TipoVeiculo(new CalculadoraMoto());
//	public static final TipoVeiculo CAMINHAO = new TipoVeiculo(new CalculadoraCaminhao());
//	
//	private CalculadoraEstacionamento calculadora;
//	
//	private TipoVeiculo(CalculadoraEstacionamento calculadora) {
//		this.calculadora = calculadora;
//	}
//	
//	public CalculadoraEstacionamento getCalculadora() {
//		return this.calculadora;
//	}
//}
//
//
//private class Main {
//	public static void main(String[] args) {
//		TipoVeiculo.CARRO.getCalculadora();
//	}
//}