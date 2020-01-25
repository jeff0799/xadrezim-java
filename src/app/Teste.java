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
		
		try {
			Partida partida=new Partida();
			for(int i=0;i<3;i++) {
				UI.printBoard(partida.getPieces());
				System.out.print("source:");
				PosicaoXadrez source= UI.lerPosicaoXadrez(sc);
				//System.out.println(source.toPosicao());
				
				System.out.print("target:");
				PosicaoXadrez target= UI.lerPosicaoXadrez(sc);
				//System.out.println(target.toPosicao());
				
				partida.fazerMovim(source, target);
			}
			/*for(int i=30;i<50;i++) {
				System.out.printf("%d -- %c\n",i,i);
			}*/
			/*char c='a';
			System.out.printf("%d",(int)c);  //ascii
			*/
		}
		catch(BoardException e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
		catch(Throwable e){
			System.out.println("-----------------ERROR-----------------------");
			e.printStackTrace();
		}

		sc.close();
	}
}