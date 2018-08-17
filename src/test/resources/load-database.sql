CREATE TABLE veiculo (
	id SERIAL PRIMARY KEY,
	placa VARCHAR(8),
	data_hora_entrada TIMESTAMP,
	data_hora_saida TIMESTAMP,
	tipo_veiculo VARCHAR(15) 
);

INSERT INTO veiculo (placa, data_hora_entrada, data_hora_saida, tipo_veiculo) VALUES ('AAA1234', TIMESTAMP '2018-08-09 10:00:00', TIMESTAMP '2018-08-09 10:30:00', 'CARRO');
INSERT INTO veiculo (placa, data_hora_entrada, data_hora_saida, tipo_veiculo) VALUES ('BAD1234', TIMESTAMP '2018-08-09 11:00:00', TIMESTAMP '2018-08-09 11:30:00', 'MOTO');
INSERT INTO veiculo (placa, data_hora_entrada, data_hora_saida, tipo_veiculo) VALUES ('BBB5431', TIMESTAMP '2018-08-09 12:00:00', TIMESTAMP '2018-08-09 12:30:00', 'MOTO');
INSERT INTO veiculo (placa, data_hora_entrada, data_hora_saida, tipo_veiculo) VALUES ('AAA1234', TIMESTAMP '2018-08-09 13:00:00', TIMESTAMP '2018-08-09 13:30:00', 'CARRO');