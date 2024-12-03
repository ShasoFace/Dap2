import java.util.Arrays;
import java.util.Random;

public class InsertionSort
{
	static int comparisons = 0;
	
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
				comparisons++;
			}
			comparisons++;
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
	
	public static void main(String[] args)
	{
		if(args.length == 1 || args.length == 2)
		{
			int arrayLength = -1;
			//ersten Parameter in einen Integer umformen, falls möglich
			try
			{
				arrayLength = Integer.parseInt(args[0]);
			}
			catch(Exception ex)
			{
				System.err.println("FEHLER: Der erste Parameter muss eine natuerliche Zahl sein.");
				System.err.println("Aufruf mit: java InsertionSort n [auf|ab|rand]");
				System.err.println("Beispiel: java InsertionSort 10000 rand");
				return;
			}
			
			//Korrektheit des Parameters überprüfen - Ein Array kann keine negative länge haben
			if(arrayLength < 0)
			{
				System.err.println("FEHLER: Der erste Parameter darf keine negative Zahl sein");
				System.err.println("Aufruf mit: java InsertionSort n [auf|ab|rand]");
				System.err.println("Beispiel: java InsertionSort 10000 rand");
				return;
			}
			
			int[] array = new int[arrayLength];
			
			//Array befüllen
			String fillSelection = "rand";
			//prüfen ob ein zweiter Parameter übergeben wurde
			if(args.length > 1)
			{
				fillSelection = args[1];
			}
			
			//Array mit fillArray-Methode befüllen der bei falschem Parameter Fehlermeldung ausgeben
			if(!fillArray(array , fillSelection))
			{
				System.err.println("FEHLER: Unbekanntes Vorsortierverfahren: " + args[1]);
				System.err.println("Aufruf mit: java InsertionSort n [auf|ab|rand]");
				System.err.println("Beispiel: java InsertionSort 10000 rand");
				return;
			}
			
			//bei eine Länge bis einschließlich 100 soll das unsortierte Array ausgegeben werden
			if(arrayLength <= 100)
			{
				System.out.println(Arrays.toString(array));
			}
			
			comparisons = 0;
			
			long tStart, tEnd, msecs;
			
			// Beginn der Messung
			tStart = System.currentTimeMillis();

			insertionSort(array);
			
			// Ende der Messung
			tEnd = System.currentTimeMillis();
			
			// Die vergangene Zeit ist die Differenz von tStart und tEnd
			msecs = tEnd - tStart;

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
			System.out.println("Das Sortieren des Arrays hat " + comparisons + " Vergleiche benoetigt.");
			
			//Laufzeit ausgabe
			System.out.println("insertionSort hat " + msecs + "ms zum sortieren benötigt");

			
		}
		else
		{
			System.err.println("FEHLER: Es müssen zwischen 1 und 2 Parameter angegeben werden.");
			System.err.println("Aufruf mit: java InsertionSort n [auf|ab|rand]");
			System.err.println("Beispiel: java InsertionSort 10000 rand");
			return;
		}
		
	}
}