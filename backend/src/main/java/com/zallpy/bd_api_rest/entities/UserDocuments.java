package com.zallpy.bd_api_rest.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.zallpy.bd_api_rest.dto.UserFullDTO;
import com.zallpy.bd_api_rest.entities.enums.OrgaoEmissor;
import com.zallpy.bd_api_rest.entities.enums.States;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_user_documents")
@NoArgsConstructor 
@AllArgsConstructor
@EqualsAndHashCode
@Getter 
@Setter
public class UserDocuments implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "s_rg")
	private String rg;
	
	@Column(name = "orgao_emissor")
	private OrgaoEmissor orgaoEmissor;
	
	@Column(name = "orgao_emissor_estado")
	private States estado;
	
	@Column(name = "s_cpf")
	private String cpf;
	
	@Column(name = "s_sus")
	private String sus;
	
	@OneToOne
	@MapsId
	private User docUser;
	
	public UserDocuments(UserFullDTO dto) {
		this.rg = dto.getRg();
		this.orgaoEmissor = dto.getOrgaoEmissor();
		this.estado = dto.getEstado();
		this.cpf = dto.getCpf();
		this.sus = dto.getSus();
	}
}
