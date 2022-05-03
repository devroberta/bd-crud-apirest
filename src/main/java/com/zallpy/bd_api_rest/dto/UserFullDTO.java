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
	@NotBlank(message = "Campo obrigatório preenchimento")
	private String nome;
	@NotBlank(message = "Campo obrigatório preenchimento")
	private String sobrenome;
	@NotBlank(message = "Campo obrigatório preenchimento")
	@Email(message = "Email incorreto")
	private String email;
	@NotNull(message = "Campo obrigatório preenchimento")
	private Integer idade;
	@NotNull(message = "Campo requerido")
	private Boolean ativo;

	@NotNull(message = "Campo obrigatório preenchimento")
	private String rg;
	@NotNull(message = "Campo requerido")
	private OrgaoEmissor orgaoEmissor;
	@NotNull(message = "Campo requerido")
	private States estado;
	@NotNull(message = "Campo obrigatório preenchimento")
	private String cpf;
	@NotNull(message = "Campo obrigatório preenchimento")
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