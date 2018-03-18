package sorter;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface SorterInterface {

	/**
	 * Metoda odczytuje nazwe klasy obiektu, ktorego referencja
	 * zostala od metody dostarczona.
	 * @param o referencja do obiektu, ktorego nazwa klasy jest poszukiwana
	 * @return nazwa obiektu odczytana na podstawie przekazanej referencji
	 */
	public static String getName(Object o) {
		return o.getClass().getSimpleName();
	}

	/**
	 * Klasa prezentujaca mozliwosc pobrania nazwy klasy gdy dysponujemy referencja
	 * do obiektu interesujacej nas klasy. Kod mozna uruchomic (w systemie Linux) za
	 * pomoca polecenia: <br><tt>java8 SorterInterface\$Show</tt>
	 */
	public class Show {
		public static void main(String[] args) {
			System.out.println(SorterInterface.getName(new Show()));
			System.out.println(SorterInterface.getName(new Object()));
			System.out.println(SorterInterface.getName("Ala ma kota"));
			System.out.println(SorterInterface.getName(126138));
		}
	}

	/**
	 * Metoda pozwala na przekazanie obiektowi strumienia, z ktorego obiekt bedzie
	 * pobierac dane. Strumien zawiera w sobie obiekty klasy Box3D i 
	 * kllas pochodnych.
	 * 
	 * @param stream
	 *            strumien z danymi
	 */
	void setInputStream(Stream<? extends Box3D> stream);

	/**
	 * Metoda dokonuje przegladu obiektow umieszczonych w strumieniu przekazanym za
	 * pomoca setInputStream oraz znajdujacych sie na przekazanej jako argument
	 * wywolania liscie. Matoda zwraca mape zawierajaca jako klucz nazwe klasy, z
	 * ktorej obiekt wygenerowano, a dla danego klucza, liste obiektow znalezionych
	 * w strumieniu i na przekazanej liscie
	 * 
	 * @param additionalInput
	 *            lista zawierajaca obiekty typu Box3D i klas pochodnych
	 * @return mapa, w ktorej nazwa klasy wskazuje na liste wszystkich obiektow tej
	 *         klasy.
	 */
	Map<String, List<Box3D>> find(List<? extends Box3D> additionalInput);

	/**
	 * Metoda dokonujaca posortowania obiektow udostepnionych poprzez strumien i
	 * additionalInput. Obiekty nalezy posortowac rosnaco wzgledem wartosci
	 * zwracanej przez metode getValue.
	 * 
	 * @param additionalInput
	 *            lista zawierajaca obiekty typu Box3D i klas pochodnych
	 * @param output
	 *            wynik sortowania
	 */
	void sizeSort(List<? extends Box3D> additionalInput, List<? super Box3D> output);

	/**
	 * Metoda przeszukuje przekazany za pomoca metody setInputStream strumien i
	 * wyszukuje obiekty, ktorych <b>typ jest inny</b> niz ten, ktory zostal
	 * przekazany jako obiekt wzorca (object).
	 * 
	 * @param object
	 *            obiekt, ktorego typ ma zostac uzyty do przeszukania strumienia
	 * @param output
	 *            wynikowa lista zawierajaca wszystkie obiekt ze strumienia, ktorych
	 *            typ nie jest taki sam jak typ object.
	 */
	void findObjectsDifferentThan(Box3D object, List<? super Box3D> output);

	/**
	 * Metoda przeszukuje strumien w poszukiwaniu obiektow, ktorych rozmiar mozna
	 * poznac. Te umieszcza na wynikowej liscie, o ile ich rozmiar bedzie wiekszy niz
	 * rozmiar przekazanego obiektu object2compare.
	 * 
	 * @param object2compare
	 *            obiekt do porownania
	 * @return lista z obiektami, ktore sa wieksze od obiektu przekazanego.
	 */
	List<? extends Box3D> findObjectsLargerThan(Box3DWithSize object2compare);
}
