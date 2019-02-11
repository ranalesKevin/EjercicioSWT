package es.kevinrm.ejercicioswt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAIS")
public class Pais implements Serializable{
	
	@Id
	@Column(name = "ID_PAIS")
	private int idPais;
	
	@Column(name = "NOMBRE_PAIS")
	private String nombrePais;
	
	@Column(name = "CODIGO_PAIS")
	private String codigoPais;
	
	@Column(name = "VALOR_PAIS")
	private int valorPais;
	
	
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public int getValorPais() {
		return valorPais;
	}
	public void setValorPais(int valorPais) {
		this.valorPais = valorPais;
	}
	
	
}
