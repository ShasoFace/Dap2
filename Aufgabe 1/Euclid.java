public class Euclid
{
	//Euclid berechnet den größten gemeinsamen Teiler zweier Zahlen, diese werden aus dem Parameter ausgelesen
	public static void main(String[] args)
	{
		if(args.length == 2)
		{
			int a = 0;
			int b = 0;
			
			
			try
			{
				//Parameter als int in a speichern
				a = Integer.parseInt(args[0]);
				//überprüft ob die Eingabe eine Positive Zahl über 0 ist
				if(a <= 0)
				{
					throw new IllegalArgumentException();
				}
			}
			catch(Exception ex)
			{
				//Fehlermeldungen
				System.err.println("Falscher Parameter - Nur Zahlen sind erlaubt!");
				System.err.println("Aufruf mit: java Euclid a b");
				System.err.println("Dabei muessen a und b natuerliche Zahlen groesser 0 sein.");
				System.err.println("Beispiel: java Euclid 24 896");
				return;
			}
			
			try
			{
				b = Integer.parseInt(args[1]);
				if(b <= 0)
				{
					throw new IllegalArgumentException();
				}
			}
			catch(Exception ex)
			{
				//angemessene Fehlermeldung ausgeben
				System.err.println("Falscher Parameter - Nur Zahlen sind erlaubt!");
				System.err.println("Aufruf mit: java Euclid a b");
				System.err.println("Dabei muessen a und b natuerliche Zahlen groesser 0 sein.");
				System.err.println("Beispiel: java Euclid 24 896");
				return;
			}
			
			//Aufruf von ggT
			System.out.println( ggT(a,b) );
			
		}
		else
		{
			//Fehlermeldungen
			System.err.println("Falsche Parameteranzahl!");
			System.err.println("Aufruf mit: java Euclid a b");
			System.err.println("Dabei muessen a und b natuerliche Zahlen groesser 0 sein.");
			System.err.println("Beispiel: java Euclid 24 896");
			return;
		}
	}
	
	public static int ggT(int a , int b)
	{
		if(b == 0)
		{
			return a;
		}
		else
		{
			return ggT(b , a%b);
		}
	}
}