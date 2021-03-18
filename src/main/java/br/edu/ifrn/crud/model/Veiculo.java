package br.edu.ifrn.crud.model;

/**
 * @author Carlos Henrique e Larissa Beatriz
 * Data: 17/03/2021
 * @version 1.0
 */

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Veiculo {
	/**
	 * Essa é a classe veiculo.
	 * 
	 * A anotação @Id e usada para indicar que essa e a coluna id
	 * 
	 * @GeneratedValue indica que sera gerado automaticamente *
	 * 
	 *                 A anotação @Column é para especificar que o atributo será uma
	 *                 coluna no Banco de Dados.
	 * 
	 *                 a anotação @OneToOne indica um relacionamento com outro
	 *                 objeto, de cardinalidade um para um
	 */

	/**
	 * sessao dos atributos.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	@Column(nullable = false)
	private String placa;
	@Column(nullable = false)
	private String modelo;
	@Column(nullable = false)
	private int ano;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
	private File documento;

	/**
	 * sessao dos metodos getters e setters.
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public File getDocumento() {
		return documento;
	}

	public void setDocumento(File documento) {
		this.documento = documento;
	}

	/**
	 * sessao dos metodos equals e hashcode para definir o que torna um Veiculo
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
		Veiculo other = (Veiculo) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
