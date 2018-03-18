//package fillbase;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class FillBase {
	/**
	 * Metoda wypelnia zadanym kolorem (color) obrazek (image). Obrazek reprezentowany jest
	 * przez dwowymiarowa tablice. Wypelnienie zaczyna sie 
	 * w pozycji image[ firstIndex ][ secondIndex ] i obejmuje caly obszar obrazka, ktory
	 * jest osiagalny przemieszczajac sie z pozycji startowej do sasiednich pikseli
	 * wg. ponizszych zasad:
	 * <ul>
	 * <li>Kazdy piksel wewnatrz obrazka sasiaduje z 8-mioma sasiednimi pikselami. Liczba sasiadow
	 * dla pikseli brzegowych jest odpowienio mniejsza.</li>
	 * <li>Z aktualnie analizowanej pozycji wolno przemieszczac sie wylacznie do tych sasiednich lokalizacji, 
	 * ktore oznaczone sa w tablicy sasiadow jako <code>true</code>. <code>false</code> oznacza brak
	 * mozliwosci ruchu w kierunku danego sasiada.</li>
	 * 
	 * <li>Tablica sasiadow (neighbours) jest zawsze rozmiaru 3x3. Informacja o tym czy
	 * z danej pozycji mozna przesunac sie o w danym kierunku znajduje sie na stosownej pozycji
	 * tej tablicy. I tak jesli analizujemy mozliwosc ruchu z polozenia [x][y], to obowiazuje nastepujace
	 * powiazenie polozen w tablicy sasiadow i mozliwosci przesuniec.
	 * <table style="border:1px solid blue" border="1" summary="Powiazanie tablicy sąsiadów z możliwością ruchu">
	 * <tr><td></td><td>neighbour[0][]</td><td>neighbour[1][]</td><td>neighbour[2][]</td></tr>
	 * <tr><td>neighbour[][0]</td><td>[ x - 1 ][ y + 1 ]</td><td>[ x ][ y + 1 ]</td><td>[ x + 1 ][ y + 1 ]</td></tr> 
	 * <tr><td>neighbour[][1]</td><td>[ x - 1 ][ y ]</td><td>[ x ][ y  ]</td><td>[ x + 1 ][ y ]</td></tr> 
	 * <tr><td>neighbour[][2]</td><td>[ x - 1 ][ y - 1 ]</td><td>[ x ][ y - 1 ]</td><td>[ x + 1 ][ y - 1 ]</td></tr> 
	 * </table>
	 * </li>
	 * 
	 * <li>Nie wolno nadpisywac kolorem (color) i tym samym przekraczac tych elementow obrazka, ktore
	 * maja kolor wymieniony w tablicy <code>colors</code>. Uwaga: color moze znajdowac sie w tablicy colors.</li>   
	 * </ul>
	 * 
	 * @param image obrazek do wypelnienia kolorem
	 * @param neighbours informacja, ktore piksele sa traktowane jako sasiednie
	 * @param colors tablica chronionych kolorow
	 * @param color kolor, ktorym wypelniamy obrazek
	 * @param firstIndex pierwszy indeks pozycji startowej
	 * @param secondIndex drugi indeks pozycji startwej
	 */
         private boolean [][] neighbours;
         private int color;
         private int maxX;
         private int maxY; 
         private Set forbiddenColors;
         
         public void print(int[][] image)
         {
           for(int i=image[0].length - 1;i>=0;i--)
           {
               System.out.println("");
               for(int j=0;j<image.length;j++)
               {
                   System.out.print(image[j][i] + " ");
               }
           }
             
         }
         
         public void fillNeighbours(int[][]image, int x, int y)
         {

                
            if(neighbours[0][0] == true && x-1 >= 0 && y+1 < maxY)
            {
                if(!forbiddenColors.contains(image[x-1][y+1]) && image[x-1][y+1] != color)
                {
                    image[x-1][y+1] = color;
                    fillNeighbours(image, x-1, y+1);
                }
            }
            if(neighbours[0][1] == true && y+1 < maxY)
            {
                if(!forbiddenColors.contains(image[x][y+1]) && image[x][y+1] != color)
                {
                    image[x][y+1] = color;
                    fillNeighbours(image, x, y+1);
                }
            }
            if(neighbours[0][2] == true && x+1 < maxX && y+1 < maxY)
            {
                if(!forbiddenColors.contains(image[x+1][y+1]) && image[x+1][y+1] != color)
                {
                    image[x+1][y+1] = color;
                    fillNeighbours(image, x+1, y+1);
                }
            }
            if(neighbours[1][0] == true && x-1 >= 0)
            {
                if(!forbiddenColors.contains(image[x-1][y]) && image[x-1][y] != color)
                {
                    image[x-1][y] = color;
                    fillNeighbours(image, x-1, y);
                }
            }
            if(neighbours[1][2] == true && x+1 < maxX )
            {
                if(!forbiddenColors.contains(image[x+1][y]) && image[x+1][y] != color)
                {
                    image[x+1][y] = color;
                    fillNeighbours(image, x+1, y);
                }
            }
            if(neighbours[2][0] == true && x-1 >= 0 && y-1 >= 0)
            {
                if(!forbiddenColors.contains(image[x-1][y-1]) && image[x-1][y-1] != color )
                {
                    image[x-1][y-1] = color;
                    fillNeighbours(image, x-1, y-1);
                }
            }
            if(neighbours[2][1] == true && y-1 >= 0)
            {
                if(!forbiddenColors.contains(image[x][y-1]) && image[x][y-1] != color)
                {
                    image[x][y-1] = color;
                    fillNeighbours(image, x, y-1);
                }
            }
            if(neighbours[2][2] == true && x+1 < maxX && y-1 >= 0)
            {
                if(!forbiddenColors.contains(image[x+1][y-1]) && image[x+1][y-1] != color)
                {
                    image[x+1][y-1] = color;
                    fillNeighbours(image, x+1, y-1);
                }
            }
             
         }
         
         
         
	 public void fill( int[][] image, boolean [][] neighbours, int[] colors, int color, int firstIndex, int secondIndex ) {

            
            // tak, to jest miejsce, w którym należy umieścić kod
           
             this.neighbours = neighbours;
             this.color = color;
             this.maxX = image.length;
             this.maxY = image[0].length;
             this.forbiddenColors = Arrays.stream(colors).boxed().collect(Collectors.toSet());
          
                
             if(!forbiddenColors.contains(image[firstIndex][secondIndex]))
             {
                 image[firstIndex][secondIndex] = color;
                 fillNeighbours(image, firstIndex, secondIndex);
             }
     
           
             //print(image);  
         }
        
         public static void main(String[] args)
         {
            FillBase f = new FillBase();
            
            int[][] img = new int[10][10];
            
            
            
            int[][] img2 = new int[9][9];
            
            img2[4][6] = 3;
            img2[3][6] = 3;
            img2[2][6] = 3;
            img2[1][6] = 3;
            img2[0][6] = 3;
            img2[5][6] = 3;
            
            img2[5][7] = 0;
            img2[5][5] = 2;
            
            boolean[][] neighbours = new boolean[][]
            {
                    {false, true, true},
                    {false, false, false},
                    {false, false, false}
            };
            
            f.fill(img, neighbours, new int[]{2,3}, 1, 6, 6);
            System.out.println("");
            f.fill(img2, neighbours, new int[]{3}, 2, 0, 2);
            
            
        }
}
