package app;

import java.util.Locale;
import java.util.Scanner;

import tabuleiro.BoardException;
import xadrez.Partida;
import xadrez.PosicaoXadrez;

public class Teste {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc= new Scanner(System.in);	
		Partida partida=new Partida();
		
		for(int i=0;i<3;i++) {
			try {
				UI.clearScreen();
				UI.printBoard(partida.getPieces());
				System.out.print("source:");
				PosicaoXadrez source= UI.lerPosicaoXadrez(sc);
				//System.out.println(source.toPosicao());
				
				boolean[][] possiveis=partida.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(partida.getPieces(),possiveis);
				
				System.out.print("target:");
				PosicaoXadrez target= UI.lerPosicaoXadrez(sc);
				//System.out.println(target.toPosicao());
				
				partida.fazerMovim(source, target);
			}
			catch(BoardException e) {
				//System.out.println(e.getMessage());
				e.printStackTrace();
				sc.nextLine();
				sc.nextLine();
			}
			catch(Throwable e){
				System.out.println("-----------------ERROR-----------------------");
				e.printStackTrace();
				sc.nextLine();
				sc.nextLine();
			}
		}
		/*for(int i=30;i<50;i++) {
			System.out.printf("%d -- %c\n",i,i);
		}*/
		/*char c='a';
		System.out.printf("%d",(int)c);  //ascii
		*/

		sc.close();
	}
}