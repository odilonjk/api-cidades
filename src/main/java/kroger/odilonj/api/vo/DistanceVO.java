package kroger.odilonj.api.vo;

public class DistanceVO {

	private final String cidadeOrigem;
	
	private final String cidadeDestino;
	
	private final double distancia;

	public DistanceVO(String cidadeOrigem, String cidadeDestino, double distancia) {
		this.cidadeOrigem = cidadeOrigem;
		this.cidadeDestino = cidadeDestino;
		this.distancia = distancia;
	}

	public String getCidadeOrigem() {
		return cidadeOrigem;
	}

	public String getCidadeDestino() {
		return cidadeDestino;
	}

	public double getDistancia() {
		return distancia;
	}
	
}
