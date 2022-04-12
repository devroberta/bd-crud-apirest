package com.zallpy.bd_api_rest.entities.enums;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter 
@ToString
public enum States implements Serializable {	
	
	AMAZONAS(1, "Amazonas", "AM"),
	ALAGOAS(2, "Alagoas", "AL"),
	ACRE(3, "Acre", "AC"),
	AMAPA(4, "Amapá", "AP"),
	BAHIA(5, "Bahia", "BA"),
	PARA(6, "Pará", "PA"),
	MATO_GROSSO(7, "Mato Grosso", "MT"),
	MINAS_GERAIS(8, "Minas Gerais", "MG"),
	MATO_GROSSO_DO_SUL(9, "Mato Grosso do Sul", "MS"),
	GOIAS(10, "Goiás", "GO"),
	MARANHAO(11, "Maranhão", "MA"),
	RIO_GRANDE_DO_SUL(12, "Rio Grande do Sul", "RS"),
	TOCANTINS(13, "Tocantins", "TO"),
	PIAUI(14, "Piauí", "PI"),
	SAO_PAULO(15, "São Paulo", "SP"),
	RONDONIA(16, "Rondônia", "RO"),
	RORAIMA(17, "Roraima", "RR"),
	PARANA(18, "Paraná", "PR"),
	CEARA(19, "Ceará", "CE"),
	PERNAMBUCO(20, "Pernambuco", "PE"),
	SANTA_CATARINA(21, "Santa Catarina", "SC"),
	PARAIBA(22, "Paraíba", "PB"),
	RIO_GRANDE_DO_NORTE(23, "Rio Grande do Norte", "RN"),
	ESPIRITO_SANTO(24, "Espírito Santo", "ES"),
	RIO_DE_JANEIRO(25, "Rio de Janeiro", "RJ"),
	SERGIPE(26, "Sergipe", "SE"),
	DISTRITO_FEDERAL(27, "Distrito Federal", "DF");
	
	@Id
	@Column(name = "int_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	@Column(name = "s_nome")
	private String nome;	
	@Column(name = "s_uf")
	private String sigla;	
}
