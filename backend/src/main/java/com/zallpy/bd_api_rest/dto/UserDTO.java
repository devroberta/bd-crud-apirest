package com.zallpy.bd_api_rest.dto;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.hateoas.RepresentationModel;

import com.zallpy.bd_api_rest.entities.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Getter @Setter
public class UserDTO extends RepresentationModel<User> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private Integer idade;
	private Boolean ativo;
	
	public UserDTO(User entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.sobrenome = entity.getSobrenome();
		this.email = entity.getEmail();
		this.idade = entity.getIdade();
		this.ativo = entity.getAtivo();
	}
}
