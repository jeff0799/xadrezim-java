package app;

import java.util.Locale;
import java.util.Scanner;

import xadrez.Partida;

public class Teste {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc= new Scanner(System.in);
		
		try {
			Partida partida=new Partida();
			
			UI.printBoard(partida.getPieces());
		}
		catch(Throwable e){
			System.out.println("-----------------ERROR-----------------------");
		}

		sc.close();
	}

}
