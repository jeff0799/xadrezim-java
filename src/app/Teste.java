package app;

import java.util.InputMismatchException;
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
		//System.out.println("digite 'L' e outro numero qualquer para ver a legenda");
		UI.printLegenda();
		sc.nextLine();
		
		while(!partida.getXequemate()) {
			try {
				UI.clearScreen();
				UI.printMatch(partida);
				//System.out.println("onBoard: "+Arrays.toString(partida.getOnBoard().toArray()));
				System.out.print("source:");
				PosicaoXadrez source= UI.lerPosicaoXadrez(sc);
				//System.out.println(source.toPosicao());sc.nextLine();sc.nextLine();
				
				boolean[][] possiveis=partida.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(partida.getPieces(),possiveis);
				
				System.out.print("target:");
				PosicaoXadrez target= UI.lerPosicaoXadrez(sc);
				//System.out.println(target.toPosicao());
				
				partida.fazerMovim(source, target);
				
				if(partida.getPromovido()!=null) {
					System.out.print("quer promover o pe�ozim para que peca? (Q/C/B/T) ");
					sc.nextLine();
					String tipo=sc.nextLine();
					partida.replacePromoted(tipo);
				}
			}
			catch(BoardException e) {
				//System.out.println(e.getMessage());
				e.printStackTrace();
				sc.nextLine();
				sc.nextLine();
			}
			catch(InputMismatchException e){
				
				e.printStackTrace();
				//sc.nextLine();sc.nextLine();
				break;
			}
			catch(Throwable e){
				System.out.println("-----------------ERROR-----------------------");
				e.printStackTrace();
				sc.nextLine();
				sc.nextLine();
			}
		}//*/
		
		/*for(int i=30;i<50;i++) {
			System.out.printf("%d -- %c\n",i,i);
		}*/
		/*char c='a';
		System.out.printf("%d",(int)c);  //ascii
		*/
		/*Posicao p1=new Posicao(1, 1);
		Posicao p2=new Posicao(1, 1);
		System.out.println(p1==p2);//false*/
		
		System.out.println("XEQUEMATE\n"
				+ "winner:"+partida.getCurrentPlayer());

		sc.close();
		System.out.println("fechou");
	}
}