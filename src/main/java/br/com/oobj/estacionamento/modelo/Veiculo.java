package br.com.oobj.estacionamento.modelo;

import java.util.Date;

public class Veiculo {

	private String placa;
	private Date dataHoraEntrada;
	private Date dataHoraSaida;
	private TipoVeiculo tipoVeiculo;
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Date getDataHoraEntrada() {
		return dataHoraEntrada;
	}
	public void setDataHoraEntrada(Date dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}
	public Date getDataHoraSaida() {
		return dataHoraSaida;
	}
	public void setDataHoraSaida(Date dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}
	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	
}
