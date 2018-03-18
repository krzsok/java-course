package programmabledice;


public class Random {
	//private java.util.Random random = new java.util.Random();
	
	/**
	 * Metoda zwraca losowa liczbe z zakresu od 1 do max wlacznie.
	 * @param max ograniczenie gorne zwracenego wyniku
	 * @return liczba losowa z zakresu od 1 do max wlacznie
	 */
	//int get( int max ) {
	//	return random.nextInt( max ) + 1;
	//}
        
        private static int[] numbers;
	private static int counter;

	public static void setNumbers( int ... numbers ) {
		Random.numbers = numbers;
		counter = 0;
	}
	
	int get(int max) {
		return numbers[(counter++) % numbers.length];
	}

	public int getCounter() {
		return counter;
	}
        
        
	
	public static void main(String[] args) {
		Random rnd = new Random();
		
	Dice d = new Dice();
        d.setNumberOfFaces(6);
        
        d.setNumberOfFaces(6);
		int[] start1 = new int[] { 4, 2, 3 };
		int delay1 = 2;
		int repetitions1 = 1;
		int[] result1 = new int[] { 3, 2, 6 };

		int[] start2 = new int[] { 2, 6 };
		int delay2 = 2;
		int repetitions2 = 3;
		int[] result2 = new int[] { 5, 5, 5 };

		d.addProgram(start1, delay1, result1, repetitions1);
		d.addProgram(start2, delay2, result2, repetitions2);

		int[] input4Random = new int[] { 1, 1, 1, 4, 2, 3, 3, 3, 4, 4, 3, 3, 3, 1, 4, 4, 4, 4 };
		Random.setNumbers(input4Random);
        
        
        System.out.println("d gety :");
        for(int i=0;i<20;i++)
        {
            System.out.print(d.get() + " ");
        }
        
       
            
            
                for(int i=0;i<10;i++)
                {
                    System.out.println(d.get());
                }
        
        
        
        
        }
}
