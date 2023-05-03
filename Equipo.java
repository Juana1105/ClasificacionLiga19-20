package tareaAvanzadaDOM;


public class Equipo {
	
	private int partidosGanados;
	private int partidosEmpatados;
	private int partidosPerdidos;
	private int golesFavor;
	private int golesContra;
	
	
	
	public Equipo() { //constructor vacio para q inicialice los datos a 0
		this.partidosGanados = 0;
		this.partidosEmpatados = 0;
		this.partidosPerdidos = 0;
		this.golesFavor = 0;
		this.golesContra = 0;
	}



	public Equipo(int partidosGanados, int partidosEmpatados, int partidosPerdidos, int golesFavor, int golesContra) {
		this.partidosGanados = partidosGanados;
		this.partidosEmpatados = partidosEmpatados;
		this.partidosPerdidos = partidosPerdidos;
		this.golesFavor = golesFavor;
		this.golesContra = golesContra;
	}



	public int calcularPuntos() {
		return this.partidosGanados*3+this.partidosEmpatados;
	}



	@Override
	public String toString() {
		return "Equipo [partidosGanados=" + partidosGanados + ", partidosEmpatados=" + partidosEmpatados
				+ ", partidosPerdidos=" + partidosPerdidos + ", golesFavor=" + golesFavor + ", golesContra="
				+ golesContra + "]";
	}



	public int getPartidosGanados() {
		return partidosGanados;
	}



	public void setPartidosGanados(int partidosGanados) {
		this.partidosGanados = partidosGanados;
	}



	public int getPartidosEmpatados() {
		return partidosEmpatados;
	}



	public void setPartidosEmpatados(int partidosEmpatados) {
		this.partidosEmpatados = partidosEmpatados;
	}



	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}



	public void setPartidosPerdidos(int partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}



	public int getGolesFavor() {
		return golesFavor;
	}



	public void setGolesFavor(int golesFavor) {
		this.golesFavor = golesFavor;
	}



	public int getGolesContra() {
		return golesContra;
	}



	public void setGolesContra(int golesContra) {
		this.golesContra = golesContra;
	}

	
	
	
	
}