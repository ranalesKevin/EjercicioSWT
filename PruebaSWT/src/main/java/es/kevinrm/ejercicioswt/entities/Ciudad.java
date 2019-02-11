package es.kevinrm.ejercicioswt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CIUDAD")
public class Ciudad implements Serializable{
	
	@Id
	@Column(name = "ID_CIUDAD")
	private long idCiudad;
	
	@Column(name = "NOMBRE_CIUDAD")
	private String nombreCiudad;
	
	@ManyToOne
	@JoinColumn(name="ID_PAIS")
	private Pais pais;
	
	
	@Column(name = "VALOR_CIUDAD")
	private Integer valorCiudad;
	
	
	
	public long getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(long idCiudad) {
		this.idCiudad = idCiudad;
	}
	public String getNombreCiudad() {
		return nombreCiudad;
	}
	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}
	public Integer getValorCiudad() {
		return valorCiudad;
	}
	public void setValorCiudad(Integer valorCiudad) {
		this.valorCiudad = valorCiudad;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
}
