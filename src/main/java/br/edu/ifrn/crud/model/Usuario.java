package br.edu.ifrn.crud.model;

/**
 * @author Carlos Henrique e Larissa Beatriz
 * Data: 17/03/2021
 * @version 1.0
 */

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * Essa é a classe Usuario.
 * 
 * A anotação @Id e usada para indicar que essa e a coluna id 
 * @GeneratedValue indica que sera gerado automaticamente	 * 
 * 
 * A anotação @Column é para especificar que o atributo será uma coluna no Banco
 * de Dados.
 */

/**
 * sessao dos atributos.
 */

@Entity
public class Usuario {
	/**
	 * sessao dos valores responsavel por diferenciar o perfil do usuario.
	 */
	public static final String ADMIN = "ADMIN";
	public static final String USUARIO_COMUM = "COMUM";

	/**
	 * sessao dos atributos.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String perfil = USUARIO_COMUM;

	/**
	 * sessao dos metodos getters e setters.
	 */

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	/**
	 * sessao dos metodos equals e hashcode para definir o que torna um Usuario
	 * igual ao outro.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
