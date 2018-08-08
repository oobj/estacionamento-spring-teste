package br.com.oobj.estacionamento.servico.calculadoras.impl;

import br.com.oobj.estacionamento.modelo.Veiculo;
import br.com.oobj.estacionamento.servico.calculadoras.CalculadoraEstacionamento;

public class CalculadoraMoto implements CalculadoraEstacionamento {
	
	private static final double VALOR_HORA_MOTO = 3.0;
	private static final double VALOR_POR_4_HORAS_MOTO = 8.0;
	
	private static final double GRATUIDADE_TOLERANCIA = 0.0;
	private static final int INTERVALO_4_HORAS = 4;
	private static final int MODULO_ADITIVO_COBRANCA = 1;
	private static final int INTERVALO_DE_COBRANCA = 30;
	private static final int MINUTOS_EM_UMA_HORA = 60;
	private static final int SEGUNDOS_EM_UM_MINUTO = 60;
	private static final int MILISSEGUNDOS_EM_UM_SEGUNDO = 1000;
	private static final int TEMPO_TOLERANCIA = 15;

	public Double calcular(Veiculo veiculo) {
		Long miliSaida = veiculo.getDataHoraSaida().getTime();
		Long miliEntrada = veiculo.getDataHoraEntrada().getTime();
		
		Long difMili = miliSaida - miliEntrada;
		Long minutos = (difMili / MILISSEGUNDOS_EM_UM_SEGUNDO) / SEGUNDOS_EM_UM_MINUTO;
		
		if(minutos < TEMPO_TOLERANCIA) {
			return GRATUIDADE_TOLERANCIA;
		}
		
		Long horas = minutos / MINUTOS_EM_UMA_HORA;
		
		if (horas < INTERVALO_4_HORAS) {
			return Double.valueOf(( minutos / INTERVALO_DE_COBRANCA)
					+ MODULO_ADITIVO_COBRANCA );
		} else {
			return Double.valueOf(VALOR_POR_4_HORAS_MOTO + (
					(horas - INTERVALO_4_HORAS + MODULO_ADITIVO_COBRANCA )
					* VALOR_HORA_MOTO));
		}
	}

}
