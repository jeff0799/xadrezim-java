package app;

import java.util.Locale;
import java.util.Scanner;

import tabuleiro.BoardException;
import xadrez.Partida;

public class Teste {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc= new Scanner(System.in);
		
		try {
			Partida partida=new Partida();
			
			UI.printBoard(partida.getPieces());//*/
			/*for(int i=30;i<50;i++) {
				System.out.printf("%d -- %c\n",i,i);
			}*/
			/*char c='a';
			System.out.printf("%d",(int)c);  //ascii
			*/
		}
		catch(BoardException e) {
			System.out.println(e.getMessage());
		}
		catch(Throwable e){
			System.out.println("-----------------ERROR-----------------------");
			e.printStackTrace();
		}

		sc.close();
	}
}