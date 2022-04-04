package com.zallpy.bd_api_rest.entities;

import com.zallpy.bd_api_rest.dto.UserDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class User extends RepresentationModel<User> implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "sobrenome")
  private String sobrenome;

  @Column(name = "email")
  private String email;

  @Column(name = "idade")
  private Integer idade;

  @Column(name = "ativo")
  private Boolean ativo;

  public User(UserDTO dto) {
    this.nome = dto.getNome();
    this.sobrenome = dto.getSobrenome();
    this.email = dto.getEmail();
    this.idade = dto.getIdade();
    this.ativo = dto.getAtivo();
  }
}
