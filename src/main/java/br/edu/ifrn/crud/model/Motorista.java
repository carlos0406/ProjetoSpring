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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Essa é a classe Motorista.
 * 
 * A anotação @Id e usada para indicar que essa e a coluna id
 * 
 * @GeneratedValue indica que sera gerado automaticamente *
 * 
 *                 A anotação @Column é para especificar que o atributo será uma
 *                 coluna no Banco de Dados. a anotação @OneToOne indica um
 *                 relacionamento com outro objeto, de cardinalidade um para um
 *                 a anotação @ManyToOne indica um relacionamento com outro
 *                 objeto de cardinalidade muitos para um
 */

@Entity
public class Motorista {
	/**
	 * sessao dos atributos.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String nome;

	@ManyToOne
	private Veiculo veiculo;
	@Column(nullable = false)
	private String cnh;
	@Column(nullable = false)
	private String categoria;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private File foto;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public File getFoto() {
		return foto;
	}

	public void setFoto(File foto) {
		this.foto = foto;
	}

	public File getDocumento() {
		return documento;
	}

	public void setDocumento(File documento) {
		this.documento = documento;
	}

	/**
	 * sessao dos metodos equals e hashcode para definir o que torna um motorista
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
		Motorista other = (Motorista) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
