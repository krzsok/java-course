package histogramgenerator;
/**
 * Interfejs histogramu.
 *
 */
public interface HistogramInterface {
	/**
	 * Metoda ustawia maksymalny rozmiar histogramu. Dozwolone jest uzycie koszy
	 * (bin) o indeksach od 1 do maxBins wlacznie. Uzycie metody bin powoduje
	 * usuniecie wszystkich zliczen.
	 * 
	 * @param maxBins
	 *            maksymalna, prawidlowa wartosc parametru bin.
	 */
	public void setSize(int maxBins);

	/**
	 * Metoda zwieksza liczbe zliczen w koszu o numerze bin. Jednoczesne uzycia
	 * metody increment moga spowodowac blad obliczeniowy i jest zabronione.
	 * Dozwolone jest wspolbiezne uzycie metody increment dla roznych numerow bin.
	 * 
	 * @param bin
	 *            numer kosza, ktorego liczba zliczen jest ikrementowana
	 * @throws ArrayIndexOutOfBoundsException
	 *             bledny numer bin
	 */
	public void increment(int bin) throws ArrayIndexOutOfBoundsException;

	/**
	 * Metoda zwraca liczbe zliczen w koszu o numerze bin. Jednoczesne uzycie get i
	 * metody increment dla tej samej wartosci bin jest niedozwolone.
	 * 
	 * @param bin
	 *            numer kosz, ktorego liczba zliczen zostanie zwrocona
	 * @return liczba zliczen w koszu o numerze bin
	 * @throws ArrayIndexOutOfBoundsException
	 *             bledny numer bin
	 */
	public int get(int bin) throws ArrayIndexOutOfBoundsException;
}

class hist implements HistogramInterface {

    private int[] tab;
    
    
    @Override
    public void setSize(int maxBins) {
        this.tab = new int[maxBins + 1];
    }

    @Override
    public void increment(int bin) throws ArrayIndexOutOfBoundsException {
        tab[bin]++;
    }

    @Override
    public int get(int bin) throws ArrayIndexOutOfBoundsException {
        return tab[bin];
    }
    
}