package es.kevinrm.ejercicioswt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Embeddable 
@Table(name = "TIPO_JJOO")
public class TipoJJOO  implements Serializable{
	
	@Id
	@Column(name = "ID_TIPO_JJOO")
	private long idTipoJJOO;
	
	@Column(name = "DESCRIPCION_TIPO")
	private String descripcionTipo;
	
	@Override
	public String toString() {
		return descripcionTipo;
	}
	
	public long getIdTipoJJOO() {
		return idTipoJJOO;
	}
	public void setIdTipoJJOO(long idTipoJJOO) {
		this.idTipoJJOO = idTipoJJOO;
	}
	public String getDescripcionTipo() {
		return descripcionTipo;
	}
	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}
	
}
