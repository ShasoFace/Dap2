import java.util.Arrays;
import java.util.Random;

public class Sortierung
{
	static int vergleich = 0;
	
	public static void main(String[] args)
	{
		if(args.length > 0 && args.length < 4)
		{
			int arrayLength = -1;
			//ersten Parameter als int in arrayLength speichern sonst Fehlermeldung
			try
			{
				arrayLength = Integer.parseInt(args[0]);
			}
			catch(Exception ex)
			{
				System.err.println("FEHLER: Der erste Parameter muss eine natuerliche Zahl sein.");
				System.err.println("Aufruf mit: java Sortierung n [insert|merge [auf|ab|rand]]");
				System.err.println("Beispiel: java Sortierung 10000 merge rand");
				return;
			}
			
			//Array kann keine negative länge haben
			if(arrayLength < 0)
			{
				System.err.println("FEHLER: Der erste Parameter darf keine negative Zahl sein");
				System.err.println("Aufruf mit: java Sortierung n [insert|merge [auf|ab|rand]]");
				System.err.println("Beispiel: java Sortierung 10000 merge rand");
				return;
			}
			
			int[] array = new int[arrayLength];
			
			//Array befüllen
			String fillSelection = "rand";
			//prüfen ob ein dritter Parameter übergeben wurde
			if(args.length > 2)
			{
				fillSelection = args[2];
			}
			
			//Array mit fillArray-Methode befüllen der bei falschem Parameter Fehlermeldung ausgeben
			if(!fillArray(array , fillSelection))
			{
				System.err.println("FEHLER: Unbekanntes Vorsortierverfahren: " + fillSelection);
				System.err.println("Aufruf mit: java Sortierung n [insert|merge [auf|ab|rand]]");
				System.err.println("Beispiel: java Sortierung 10000 merge rand");
				return;
			}
			
			//bei eine Länge bis einschließlich 100 soll das unsortierte Array ausgegeben werden
			if(arrayLength <= 100)
			{
				System.out.println(Arrays.toString(array));
			}
			
			vergleich = 0;
			
			String sortSelection = "merge";
			
			if(args.length > 1)
			{
				sortSelection = args[1];
			}
			
			if(sortSelection.equals("merge"))
			{
				mergeSort(array);
			}
			else
			{
				if(sortSelection.equals("insert"))
				{
					insertionSort(array);
				}
				else
				{
					System.err.println("FEHLER: Unbekanntes Sortierverfahren: " + sortSelection);
					System.err.println("Aufruf mit: java Sortierung n [insert|merge [auf|ab|rand]]");
					System.err.println("Beispiel: java Sortierung 10000 merge rand");
					return;
				}
			}
			

			//bei eine Länge bis einschließlich 100 soll das sortierte Array ausgegeben werden
			if(arrayLength <= 100)
			{
				System.out.println(Arrays.toString(array));
			}
			
			//Prüfen ob das Array korrekt sortiert wurde und entspechende Ausgabe machen
			if(isSorted(array))
			{
				System.out.println("Feld ist sortiert!");
			}
			else
			{
				System.out.println("FEHLER: Feld ist NICHT sortiert!");
			}
			
			//Anzahl der Vergleiche ausgeben
			System.out.println("Das Sortieren des Arrays hat " + vergleich + " Vergleiche benoetigt.");
			
		}
		else
		{
			System.err.println("FEHLER: Es müssen zwischen 1 und 2 Parameter angegeben werden.");
			System.err.println("Aufruf mit: java Sortierung n [insert|merge [auf|ab|rand]]");
			System.err.println("Beispiel: java Sortierung 10000 merge rand");
			return;
		}
	}
	
	//die Methode füllt das übergebene Array, je nach typ 
	//Wurde das Feld befüllt, so wird true zurückgegeben. Ansonsten wird false zurückgegeben.
	public static boolean fillArray(int[] arr , String type)
	{
		if(type.equals("ab"))
		{
			//Feld absteigend befüllen
			for(int i = 0; i < arr.length; i++)
			{
				arr[i] = arr.length-i;
			}
			return true;
		}
		else
		{
			if(type.equals("auf"))
			{
				//Feld aufsteigend befüllen
				for(int i = 0; i < arr.length; i++)
				{
					arr[i] = i+1;
				}
				return true;
			}
			else
			{
				//Feld mit zufälligen Zahlen befüllen
				if(type.equals("rand"))
				{
					Random rng = new Random(951);
					
					for(int i = 0; i < arr.length; i++)
					{
						arr[i] = rng.nextInt();
					}
					return true;
				}
				else
				{
					//Die übergebene Befüllungsart
					return false;
				}
			}
		}
	}
	
	public static void insertionSort(int[] array)
	{
		assert array != null:"Es wurde null an insertionSort übergeben";
		
		for(int j = 1; j < array.length; j++)
		{
			int key = array[j];
			int i = j-1; 
			while(i >= 0 && array[i] > key)
			{
				array[i+1] = array[i];
				i--;
				vergleich++;
			}
			vergleich++;
			array[i+1] = key;
		}
	}
	
	public static boolean isSorted(int[] array)
	{
		assert array != null:"Es wurde null an isSorted übergeben";
		
		for(int i = 0; i < array.length - 1; i++)
		{
			if(array[i] > array[i+1])
			{
				return false;
			}
		}
		return true;
	}
	
	public static void mergeSort(int[] array) {
	int[] tmpArray = new int[array.length];
	mergeSort(array, tmpArray, 0, array.length-1);
	assert isSorted(array);
	}
	
	public static void mergeSort(int[] array, int[] tmpArray, int left, int right)
	{
		if(left < right)
		{
			int middle = (left + right)/2;
			mergeSort(array, tmpArray, left, middle);
			mergeSort(array, tmpArray, middle+1, right);
			merge(array, tmpArray, left, middle, right);
		}
	}
	
	public static void merge(int array[],int tmpArray[], int left, int middle, int right) 
    {
		//Indizes
		int leftIndex = left;
		int rightIndex = middle+1;
		int tmpIndex = left;
		
		//solange man im jeweiligen Teilarray ist die kleinste Zahl von den beiden ins Hilfsarray kopieren und Index erhöhen
		while (leftIndex <= middle   && rightIndex <= right  )
		{
			if(array[leftIndex] <= array[rightIndex])
			{
				tmpArray[tmpIndex] = array[leftIndex];
				leftIndex++;
				tmpIndex++;
			}
			else
			{
				tmpArray[tmpIndex] = array[rightIndex];
				rightIndex++;
				tmpIndex++;
			}
			vergleich++;
		}
		
		
		//Wenn noch zahlen "übrig" sind, diese ans Ende packen
		while(leftIndex <= middle)
		{
			tmpArray[tmpIndex] = array[leftIndex];
			leftIndex++;
			tmpIndex++;
		}
		
		while(rightIndex <= right)
		{
			tmpArray[tmpIndex] = array[rightIndex];
			rightIndex++;
			tmpIndex++;
		}
		
		//"sortierte" Zahlen wieder ins Haupt array zurück schreiben
		for(int i = left; i <= right; i++)
		{
			array[i] = tmpArray[i];
		}
		
	}

}