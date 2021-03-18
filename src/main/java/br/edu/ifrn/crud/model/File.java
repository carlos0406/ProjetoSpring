package br.edu.ifrn.crud.model;

/**
 * @author Carlos Henrique e Larissa Beatriz
 * Data: 17/03/2021
 * @version 1.0
 */

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Essa é a entidade Arquivo.
 * 
 * A anotação @Id e usada para indicar que essa e a coluna id
 * 
 * @GeneratedValue indica que sera gerado automaticamente A anotação @Column é
 *                 para especificar que o atributo será uma coluna no Banco de
 *                 Dados. a anotação
 * 
 *                 A anotação @Lob permite receber variaveis que ocupam mais
 *                 espaco.
 * 
 *                 A anotação @Column é para especificar que o atributo será uma
 *                 coluna no Banco de Dados.
 */

@Entity
public class File {
	/**
	 * sessao dos atributos.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Column(nullable = false)
	private String nomeArquivo;

	private String tipoArquivo;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] dados;

	/**
	 * sessao dos construtores.
	 */
	public File(Long id, String nomeArquivo, String tipoArquivo, byte[] dados) {
		super();
		this.id = id;
		this.nomeArquivo = nomeArquivo;
		this.tipoArquivo = tipoArquivo;
		this.dados = dados;
	}

	public File() {

	}

	/**
	 * sessao dos metodos getters e setters.
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	/**
	 * sessao dos metodos equals e hashcode para definir o que torna um
	 * File(arquivo) igual ao outro.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		File other = (File) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
