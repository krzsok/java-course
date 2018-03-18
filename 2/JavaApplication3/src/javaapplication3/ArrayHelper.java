//    package javaapplication3;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;



class ArrayHelper {
	/**
	 * Metoda wykonuje losowanie <b> bez powtorzen</b> ze zbioru collection i 
	 * zwraca tablice z wylosowanymi elementami.
	 * 
	 * @param collection tablica z elementami, z ktorych dokonywane jest losowanie
	 * @param rnd referencja do odbiektu klasy Random, nalezy jej uzyc do
	 *            wygenerowania wyniku
	 * @param resultSize rozmiar wynikowej tablicy (nie wiekszy niz
	 * ilosc elementow w tablicy collection)
	 * @return tablica z elementami wylosowanymi z collection 
	 */
	public int[] combination( int [] collection, Random rnd, int resultSize ) {
		int[] result = new int[ resultSize ];
		List<Integer> uniqueIndexes = new ArrayList<>();
                
                
                while(uniqueIndexes.size() != resultSize)
                {
                    int rand = rnd.get(collection.length - 1);    
                    if(!uniqueIndexes.contains(rand))
                    {
                       uniqueIndexes.add(rand);
                    }                                      
                }
                
                for(int i=0;i<uniqueIndexes.size();i++)
                {
                    result[i] = collection[uniqueIndexes.get(i)];
                }
                
		return result;
	}
	
	/**
	 * Metoda zwraca tablice zawierajaca te same elementy, ktore przekazane
	 * zostaly za pomoca collections. Elementy sa ulozone w nastepujacej
	 * kolejnosci (zalozenie - przekazano 3 tablice, w kazdej po 3 elementy): 
	 * <pre>
	 * indeks w tablicy wynikowej | indeks tablicy | indeks elementu tablicy
	 *      0                            0                0
	 *      1                            1                0
	 *      2                            2                0
	 *      3                            0                1
	 *      4                            1                1
	 *      5                            2                1
	 *      6                            0                2
	 *      7                            1                2
	 *      8                            2                2
	 * </pre>
	 * @param collections przkazane do metody tablice. Wszystkie tablice maja
	 * ten sam rozmiar. Zadna z nich nie moze byc null.
	 * @return jednowymiarowa tablica zawierajaca odpowiednio
	 * ulozone elementy pobrane z przekazanych tablic.
	 */
	public int[] interweaving( int[] ... collections ) {
		int[] result = null;
		int resultSize = 0;
                int elementsInCollection = collections[0].length;
                List<Integer> temp = new ArrayList<>();
		// brak kodu
                for(int i=0;i<collections.length;i++)
                {
                        resultSize += collections[i].length;
                }
                
                result = new int[resultSize];

                    
                for(int i=0;i<elementsInCollection;i++)
                {
                    //int tempel = collections[i].length;
                    for(int j=0;j<collections.length;j++)
                    {
                        temp.add(collections[j][i]);  
                    }
                }
                    
                for(int i=0;i<temp.size();i++)
                {
                    result[i] = temp.get(i);
                }
                
                
		
		return result;
	}

	/**
	 * Metoda zwraca tablice zawierajaca te same elementy, ktore przekazane
	 * zostaly za pomoca collections. Elementy sa ulozone w nastepujacej
	 * kolejnosci (zalozenie - przekazano 3 tablice, w tablicy 0 i 2 są 3 elementy,
	 * w tablicy o indeksie 1 jest jeden element): 
	 * <pre>
	 * indeks w tablicy wynikowej | indeks tablicy | indeks elementu tablicy
	 *      0                            0                0
	 *      1                            1                0
	 *      2                            2                0
	 *      3                            0                1
	 *      4                            2                1
	 *      5                            0                2
	 *      6                            2                2
	 * </pre>
	 * @param collections przkazane do metody tablice. Nie ma gwarancji,ze 
	 * wszystkie tablice maja ten sam rozmiar. Zadna z nich nie moze byc null.
	 * @return jednowymiarowa tablica zawierajaca odpowiednio
	 * ulozone elementy pobrane z przekazanych tablic.
	 */
	public int[] interweaving2( int[] ... collections ) {
		int[] result = null;
		
		int resultSize = 0;
                
                List<Integer> temp = new ArrayList<>();
                Queue<Integer>[] q = new LinkedList[collections.length];
                
                int counter = 0;
                
               
                
                for(int i=0;i<collections.length;i++)
                {
                    q[i] = new LinkedList();
                    for(int j=0;j<collections[i].length;j++)
                    {
                        
                        q[i].add(collections[i][j]);
                        resultSize++;
                        
                    }
                }
                
                while(temp.size() != resultSize)
                {
                    if(q[counter % collections.length].peek() != null)
                    {
                        temp.add(q[counter % collections.length].poll());
                        
                    }
                    
                    counter++;
                }
                
                result = new int[resultSize];
                    
                for(int i=0;i<temp.size();i++)
                {
                    result[i] = temp.get(i);
                }
                
		
     		return result;
	}
	
	public static void main(String[] args) {
		int[] t1 = new int[] { 1, 2, 3 };
		int[] t2 = new int[] { 10, 20, 30 };
                int[] t3 = new int[] { 100, 200, 300 };
                int[] t4 = new int[] { 444 };
                int[] t5 = new int[] { 555, 666 };
                
		
		ArrayHelper ah = new ArrayHelper();
		// przykladowe wywolanie metody interweaving
                Random r = new Random();
                int[] result = ah.combination(t1, r, 2);
                
                for(int i=0;i<result.length;i++)
                {
                    System.out.print(result[i] + " ");
                }
                
		result = ah.interweaving( t1, t2, t3 );
                
                
                System.out.print("inter: \n");
                
                for(int i=0;i<result.length;i++)
                {
                    
                    System.out.print(result[i] + " ");
                }
                
                System.out.print("inter2: \n");
                
                result = ah.interweaving2(t1, t4, t5);
                
                for(int i=0;i<result.length;i++)
                {
                    
                    System.out.print(result[i] + " ");
                }
                
                
	}
}
