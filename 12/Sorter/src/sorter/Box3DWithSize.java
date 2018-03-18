package sorter;
/**
 * Klasa bazowa dla klas, ktore pozwalaja na poznanie swojej wielkosci.
 * 
 */
public class Box3DWithSize extends Box3D {
	private int size;

	public Box3DWithSize(int value, int size) {
		super(value);
		this.size = size;
	}

	/**
	 * Metoda zwracajaca rozmiar obiektu.
	 * 
	 * @return rozmiar obiektu
	 */
	public int getSize() {
		return size;
	}
}
