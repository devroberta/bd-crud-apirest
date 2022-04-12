package com.zallpy.bd_api_rest.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.entities.UserDocuments;
import com.zallpy.bd_api_rest.entities.enums.OrgaoEmissor;
import com.zallpy.bd_api_rest.entities.enums.States;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class UserFullDTO extends RepresentationModel<User> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private Integer idade;
	private Boolean ativo;
	
	private String rg;
	private OrgaoEmissor orgaoEmissor;
	private States estado;
	private String cpf;
	private String sus;
	
	public UserFullDTO(User entity, UserDocuments userDocs) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.sobrenome = entity.getSobrenome();
		this.email = entity.getEmail();
		this.idade = entity.getIdade();
		this.ativo = entity.getAtivo();
		
		this.rg = userDocs.getRg();
		this.orgaoEmissor = userDocs.getOrgaoEmissor();
		this.estado = userDocs.getEstado();
		this.cpf = userDocs.getCpf();
		this.sus = userDocs.getSus();
	}
	
	public UserFullDTO(User entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.sobrenome = entity.getSobrenome();
		this.email = entity.getEmail();
		this.idade = entity.getIdade();
		this.ativo = entity.getAtivo();
	}
}