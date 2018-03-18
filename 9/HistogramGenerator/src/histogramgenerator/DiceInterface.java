package histogramgenerator;
public interface DiceInterface {
	/**
	 * Metoda zwraca liczbe scianek kostki.
	 * 
	 * @return liczba scianek kostki
	 */
	public int getNumberOfFaces();

	/**
	 * Metoda zwraca wynik rzutu kostka. Metoda moze byc jednoczenie wywolywana
	 * przez wiele watkow. Wynik zawiera sie w przedziale od 1 do getNumberOfFaces.
	 * 
	 * @return wynik rzutu kostka.
	 */
	public int get();
}

class dice implements DiceInterface {

    private int faces=2;
    private int counter=0;
    
    @Override
    public int getNumberOfFaces() {
        return faces;
    }

    @Override
    public int get() {
        //return counter++ % 4 + 1;
        return 1;
    }
    
}