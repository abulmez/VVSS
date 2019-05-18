package note.model;

public class Corigent {
	private String numeElev;
	private int nrMaterii;
	/**
	 * @return the numeElev
	 */
	
	public Corigent(String numeElev, int nrMaterii) {
		this.numeElev = numeElev;
		this.nrMaterii = nrMaterii;
	}
	
	
	public String getNumeElev() {
		return numeElev;
	}
	/**
	 * @param numeElev the numeElev to set
	 */
	public void setNumeElev(String numeElev) {
		this.numeElev = numeElev;
	}
	/**
	 * @return the nrMaterii
	 */
	public int getNrMaterii() {
		return nrMaterii;
	}
	/**
	 * @param nrMaterii the nrMaterii to set
	 */
	public void setNrMaterii(int nrMaterii) {
		this.nrMaterii = nrMaterii;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Corigent){
			Corigent other = (Corigent) obj;
			return other.numeElev.equals(numeElev) && other.nrMaterii == nrMaterii;
		}
		return false;
	}

	public String toString() {
		return numeElev + " -> " + nrMaterii;
	}
}
