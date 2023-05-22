
public class Equipo implements Comparable<Equipo>{
	private String nombre;
	private int partidosGanados;
	private int partidosEmpatados;
	private int partidosPerdidos;
	private int golesFavor;
	private int golesContra;
	
	public int calcularPuntos() {
		int puntos;
		puntos=(this.partidosGanados*3)+(this.partidosEmpatados);
		return puntos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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



	public Equipo(String nombre, int partidosGanados, int partidosEmpatados, int partidosPerdidos, int golesFavor,
			int golesContra) {
		this.nombre = nombre;
		this.partidosGanados = partidosGanados;
		this.partidosEmpatados = partidosEmpatados;
		this.partidosPerdidos = partidosPerdidos;
		this.golesFavor = golesFavor;
		this.golesContra = golesContra;
	}

	public Equipo(String nombre) {
		this.nombre=nombre;
		this.partidosGanados = 0;
		this.partidosEmpatados = 0;
		this.partidosPerdidos = 0;
		this.golesFavor = 0;
		this.golesContra = 0;
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", partidosGanados=" + partidosGanados + ", partidosEmpatados="
				+ partidosEmpatados + ", partidosPerdidos=" + partidosPerdidos + ", golesFavor=" + golesFavor
				+ ", golesContra=" + golesContra + "]";
	}

	@Override
	public int compareTo(Equipo o) {
		int a=0;
		if(calcularPuntos()<o.calcularPuntos()) {
			a=1;
		}
		else if(calcularPuntos()>o.calcularPuntos()) {
			a=-1;
		}
		else {
			if((this.golesFavor-this.golesContra)<(o.golesFavor-o.golesContra)) {
				a=1;
			}
			else {
				a=-1;
			}
		}
		return a;
	}
	
	
}
