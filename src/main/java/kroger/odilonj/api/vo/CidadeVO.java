package kroger.odilonj.api.vo;

public class CidadeVO {

	private final String name;
	
	private final double lon;
	
	private final double lat;

	public CidadeVO(String name, double lon, double lat) {
		this.name = name;
		this.lon = lon;
		this.lat = lat;
	}

	public String getName() {
		return name;
	}

	public double getLon() {
		return lon;
	}

	public double getLat() {
		return lat;
	}
	
}
