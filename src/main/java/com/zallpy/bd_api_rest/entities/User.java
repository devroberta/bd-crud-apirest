package com.zallpy.bd_api_rest.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.zallpy.bd_api_rest.dto.UserFullDTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@Getter 
@Setter
public class User extends RepresentationModel<User> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "s_nome")
	private String nome;
	
	@Column(name = "s_sobrenome")
	private String sobrenome;
	
	@Column(name = "s_email")
	private String email;
	
	@Column(name = "int_idade")
	private Integer idade;
	
	@Column(name = "boolean_ativo")
	private Boolean ativo;
	
	@OneToOne(mappedBy = "docUser", cascade = CascadeType.ALL)
	private UserDocuments documents;

	public User(Long id, String nome, String sobrenome, String email, Integer idade, Boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.idade = idade;
		this.ativo = ativo;
	}
	
	public User(UserFullDTO dto) {
		this.nome = dto.getNome();
		this.sobrenome = dto.getSobrenome();
		this.email = dto.getEmail();
		this.idade = dto.getIdade();
		this.ativo = dto.getAtivo();
	}

	public User updateData(UserFullDTO dto) {
		this.nome = dto.getNome();
		this.sobrenome = dto.getSobrenome();
		this.email = dto.getEmail();
		this.idade = dto.getIdade();
		this.ativo = dto.getAtivo();
		return this;
	}
}
