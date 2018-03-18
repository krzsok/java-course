package sorter;
/**
 * Klasa bazowa dla klas, ktore pozwalaja poznac wartosc.
 */
public class Box3D {
	protected int value;

	public Box3D(int value) {
		super();
		this.value = value;
	}

	/**
	 * Metoda zwracajaca pewna wartosc.
	 * 
	 * @return wartosc
	 */
	public int getValue() {
		return value;
	}

}
