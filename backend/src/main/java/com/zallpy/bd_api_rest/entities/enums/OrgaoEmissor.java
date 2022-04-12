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
public enum OrgaoEmissor implements Serializable {

	CV(1, "Cartório Civil", "CV"),
	PC(2, "Polícia Civil", "PC"),
	SJS(3, "Secretaria da Justiça e Segurança", "SJS"),
	SJTC(4, "Secretaria da Justiça do Trabalho e Cidadania", "SJTC"),
	SRF(5, "Receita Federal", "SRF"), 
	SSP(6, "Secretaria de Segurança Pública", "SSP");
	
	@Id
	@Column(name = "int_codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	@Column(name = "s_nome")
	private String nome;	
	@Column(name = "s_sigla")
	private String sigla;	
}
