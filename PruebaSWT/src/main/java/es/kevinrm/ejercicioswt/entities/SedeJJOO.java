package es.kevinrm.ejercicioswt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SEDE_JJOO")
public class SedeJJOO implements Serializable{
	
	@Id
	@Column(name = "AÑO")
	private long ano;
	
	@OneToOne
	@JoinColumn(name="SEDE")
	private Ciudad ciudad;
	
	@ManyToOne
	@JoinColumn(name="ID_TIPO_JJOO")
	private TipoJJOO tipoJJOO;
	
	@Override
	public String toString() {
		return new String (String.format("SEDE_JJOO[Año = %s|Ciudad = %s|Tipo JJOO = %s]",
				ano, ciudad.getNombreCiudad() , tipoJJOO.getDescripcionTipo()));
	}
	
	public long getAno() {
		return ano;
	}
	public void setAno(long ano) {
		this.ano = ano;
	}
	
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	public TipoJJOO getTipoJJOO() {
		return tipoJJOO;
	}
	public void setTipoJJOO(TipoJJOO tipoJJOO) {
		this.tipoJJOO = tipoJJOO;
	}
	
}
