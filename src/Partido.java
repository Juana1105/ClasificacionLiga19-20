
public class Partido {
	private String team1;
	private String team2;
	private int goles1;
	private int goles2;
	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	public int getGoles1() {
		return goles1;
	}
	public void setGoles1(int goles1) {
		this.goles1 = goles1;
	}
	public int getGoles2() {
		return goles2;
	}
	public void setGoles2(int goles2) {
		this.goles2 = goles2;
	}
	public Partido(String team1, String team2, int goles1, int goles2) {
		this.team1 = team1;
		this.team2 = team2;
		this.goles1 = goles1;
		this.goles2 = goles2;
	}
	public Partido() {
	}
	
}
