package es.kevinrm.ejercicioswt.dto;

import java.math.BigInteger;


public class JuegosOlimpicosDTO {
	
	private int idPais;

	private String nombrePais;

	private int idCiudad;

	private String nombreCiudad;

	private BigInteger valor;

	private String tipoJJOO;

	private BigInteger vecesSede;

	@Override
	public String toString() {
		return new String(String.format("JuegosOlimpicosDTO "
				+ "[idPais = %s|nombrePais = %s|idCiudad = %s |nombreCiudad = %s|valor = %s|tipoJJOO = %s|vecesSede = %s]",
				idPais, nombrePais, idCiudad, nombreCiudad, valor, tipoJJOO, vecesSede));
	}

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

	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public BigInteger getValor() {
		return valor;
	}

	public void setValor(BigInteger valor) {
		this.valor = valor;
	}

	public String getTipoJJOO() {
		return tipoJJOO;
	}

	public void setTipoJJOO(String tipoJJOO) {
		this.tipoJJOO = tipoJJOO;
	}

	public BigInteger getVecesSede() {
		return vecesSede;
	}

	public void setVecesSede(BigInteger vecesSede) {
		this.vecesSede = vecesSede;
	}

	public JuegosOlimpicosDTO mapperResult(Object[] result) {
		this.idPais = (Integer) result[0];
		this.nombrePais = (String) result[1];
		this.idCiudad = (Integer) result[2];
		this.nombreCiudad = (String) result[3];
		this.valor = (BigInteger) result[4];
		this.tipoJJOO = (String) result[5];
		this.vecesSede = (BigInteger) result[6];
		return this;
	}

}
