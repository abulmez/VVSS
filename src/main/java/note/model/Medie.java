package note.model;

public class Medie {
	private Elev elev;
	private double medie;

	public Medie() {

	}

	public Medie(Elev elev, double medie) {
		this.elev = elev;
		this.medie = medie;
	}

	/**
	 * @return the elev
	 */
	public Elev getElev() {
		return elev;
	}
	/**
	 * @param elev the elev to set
	 */
	public void setElev(Elev elev) {
		this.elev = elev;
	}
	/**
	 * @return the medie
	 */
	public double getMedie() {
		return medie;
	}
	/**
	 * @param medie the medie to set
	 */
	public void setMedie(double medie) {
		this.medie = medie;
	}
	
	public String toString() {
		return this.elev.getNume() + " -> " + this.medie;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Medie){
			Medie other = (Medie) obj;
			return other.elev.equals(elev) && other.medie == medie;
		}
		return false;
	}
}
