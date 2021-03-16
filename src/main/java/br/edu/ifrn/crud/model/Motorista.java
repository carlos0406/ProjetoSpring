package br.edu.ifrn.crud.model;

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
 * @author Carlo
 *
 */
/**
 * @author Carlo
 *
 */
@Entity
public class Motorista {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(nullable=false)
private String nome;

@ManyToOne
private Veiculo veiculo;
@Column(nullable=false)
private String cnh;
@Column(nullable=false)
private String categoria;

@OneToOne(fetch=FetchType.LAZY, cascade= CascadeType.REMOVE)
private File foto;

@OneToOne(fetch=FetchType.LAZY, cascade= CascadeType.REMOVE)
private File documento;

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
