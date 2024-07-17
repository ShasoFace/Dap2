public class Eratosthenes
{
	public static void main(String args[])
	{
	//Der Code überprüft wie viele Zahlen bis zur übergebenen Zahl Primzahlen sind. Falls nur die Zahl übergeben wird, dann gibt das Programm aus wie viele Primzahlen es von 1 bis zu dieser Zahl gibt.
	//Falls man als Parameter eine Zahl und -o übergibt, dann listet das Programm noch zusätzlich die Zahlen aus.	
		
	  if(args.length == 1 || args.length == 2)
	  {
			// Der erste Parameter wird als int a initialisiert
			int a = 0;
			try
			{
				a = Integer.parseInt(args[0]);
				//a muss höher als 0 sein
				if(a < 0)
				{
					throw new Exception();
				}
			}
			catch(Exception ex)
			{
				//Fehlermeldungen
				System.err.println("Falscher Parameter! Nur Integer groeser 0 sind erlaubt");
				System.err.println("Aufruf mit : java Eratosthenes n [-o]");
				System.err.println("Es wird die Anzahl der Primzahlen aus dem Bereich [2,n] berechnet.");
				System.err.println("Mit -o werden diese Zahlen auch ausgegeben.");
				System.err.println("Bsp: java Eratosthenes 100 -o");
				return;
			}
		
			boolean isPrime[] = new boolean[a+2];
			//Zuerst setzen wir alle Werte des Arrays auf True
			for(int i = 2; i < isPrime.length; i++)
			{
				isPrime[i] = true;
			}
			
			//Variable x wird erstellt, womit wir dann überprüfen ob y%x==0 ist, um dann im Boolean Array den Index auf false zu setzen
			for(int x = 2; x < isPrime.length; x++)				
			{
				if(isPrime[x] == true)
				{
					for(int y = 2; y < isPrime.length; y++)
					{
						if( y > x && y%x== 0)
						{
							isPrime[y] = false;
						}
					}
				}
			}
			
			//Anzahl der Primzahlen wird überprüft und dann in int counter geschrieben
			int counter = 0;
			for(int b = 2; b <= a; b++)
			{
					if(isPrime[b])
					{
						counter++;
					}
			}
				
			System.out.println(counter);
			
			// Es wird geprüft ob als zweiter Parameter -o angegeben wurde, falls ja werden die Primzahlen ausgegeben
			
			if(args.length == 2 && args[1].equals("-o"))
			{
				for(int c = 2; c < a; c++)
				{
					if(isPrime[c])
					{
						System.out.print(c + " ");
					}
				}	
			}
	  }
	  else
	  {
		  System.err.println("Falsche Parameterzahl!");
		  System.err.println("Aufruf mit : java Eratosthenes n [-o]");
		  System.err.println("Es wird die Anzahl der Primzahlen aus dem Bereich [2,n] berechnet.");
		  System.err.println("Mit -o werden diese Zahlen auch ausgegeben.");
		  System.err.println("Bsp: java Eratosthenes 100 -o");
		  return;
	  }
	}	
}