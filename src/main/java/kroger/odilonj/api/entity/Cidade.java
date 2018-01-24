package kroger.odilonj.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cidades")
public class Cidade {
	
	@Id
	@Column(name="ibge_id")
	private Integer ibgeId;
	
	private String name;
	
	private String uf;
	
	private boolean capital;
	
	@Column(name="no_accents")
	private String noAccents;
	
	@Column(name="alternative_names")
	private String alternativeNames;
	
	private String mesoregion;
	
	private String microregion;
	
	private double lon;
	
	private double lat;
	
	public Cidade() {
	}

	public Cidade(Integer ibgeId, String name, String uf, boolean capital, String noAccents, String alternativeNames,
			String mesoregion, String microregion, double lon, double lat) {
		this.ibgeId = ibgeId;
		this.name = name;
		this.uf = uf;
		this.capital = capital;
		this.noAccents = noAccents;
		this.alternativeNames = alternativeNames;
		this.mesoregion = mesoregion;
		this.microregion = microregion;
		this.lon = lon;
		this.lat = lat;
	}

	public Integer getIbgeId() {
		return ibgeId;
	}

	public void setIbgeId(Integer ibgeId) {
		this.ibgeId = ibgeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public boolean isCapital() {
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public String getNoAccents() {
		return noAccents;
	}

	public void setNoAccents(String noAccents) {
		this.noAccents = noAccents;
	}

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String getMesoregion() {
		return mesoregion;
	}

	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}
	
}
