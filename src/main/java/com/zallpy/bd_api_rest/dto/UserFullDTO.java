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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class UserFullDTO extends RepresentationModel<User> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String sobrenome;
	@NotEmpty
	@Email
	private String email;
	@NotNull
	private Integer idade;
	@NotNull
	private Boolean ativo;

	@NotEmpty
	private String rg;
	@NotNull
	private OrgaoEmissor orgaoEmissor;
	@NotNull
	private States estado;
	@NotEmpty
	private String cpf;
	@NotEmpty
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

		this.rg = entity.getDocuments().getRg();
		this.orgaoEmissor = entity.getDocuments().getOrgaoEmissor();
		this.estado = entity.getDocuments().getEstado();
		this.cpf = entity.getDocuments().getCpf();
		this.sus = entity.getDocuments().getSus();
	}
}