package histogramgenerator;
public interface HistogramGeneratorInterface {
	/**
	 * Metoda pozwalajaca przekazac obiekt, ktory ma byc uzyty do wygenerowania
	 * histogramu.
	 * 
	 * @param histogram
	 *            referencja do obiektu umozliwiajego wyliczenie histogramu.
	 */
	public void setHistogram(HistogramInterface histogram);

	/**
	 * Metoda pozwalajaca przekazac interfejs kosti do gry, ktorej histogram ma
	 * zostac wyznaczony.
	 * 
	 * @param dice
	 *            referencja do obiektu reprezentujacego kostke do gry.
	 */
	public void setDice(DiceInterface dice);

	/**
	 * Metoda ustawia liczbe watkow jaka ma byc uzywana w trakcie wyznaczania
	 * histogramu.
	 * 
	 * @param threads
	 *            liczba watkow, ktore nalezy uzyc w obliczeniach histogramu
	 */
	public void setNumberOfThreads(int threads);

	/**
	 * Uruchomienie obliczen. Obliczenia maja zakonczyc sie z chwila zrealizowania
	 * numberOfDraws losowan. Wynik wszystkich losowan jest uzywany do wyznaczenia
	 * histogramu.
	 * 
	 * @param numberOfDraws
	 *            liczba losowan (rzutow kostka), ktore maja byc uzyte do wyliczenia
	 *            histogramu.
	 */
	public void start(int numberOfDraws);
}
