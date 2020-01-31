package app;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ChessPiece;
import xadrez.Cor;
import xadrez.Partida;
import xadrez.PosicaoXadrez;

public class UI {
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	/*public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";//*/
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	
	/*
	 abre o terminal do git na pasta bin
	 java app/Programa
	 java app/Teste
	 */
	
	private static void printBoard(ChessPiece[][] p) {
		for(int i = 0;i<p.length;i++) {
			System.out.printf("%d ",8-i);
			
			for(int j=0;j<p[0].length;j++) {
				printPiece(p[i][j],false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printBoard(ChessPiece[][] p,boolean[][] possibleMoves) {
		for(int i = 0;i<p.length;i++) {
			System.out.printf("%d ",8-i);
			
			for(int j=0;j<p[0].length;j++) {
				printPiece(p[i][j],possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	@SuppressWarnings("unused")
	private static void printPiece(ChessPiece p,boolean fundo) {
		if(fundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if(p==null)System.out.print('-'+ANSI_RESET);
		else if (p.getCor() == Cor.WHITE) {
			System.out.print(ANSI_WHITE + p + ANSI_RESET);
			
		}
		else {
			System.out.print(ANSI_YELLOW + p + ANSI_RESET);
		}

		System.out.print(' ');
	}
	
	@SuppressWarnings("unused")
	//para terminais em preto e branco
	private static void printPiece2(ChessPiece p,boolean b) {
		if(p==null)System.out.print('-');
		else if (p.getCor() == Cor.WHITE) {
			System.out.print(p);
			
		}
		else {
			System.out.print(p);
		}

		System.out.print(' ');
	}
	
	private static void printCaptured(Partida partida) {
		System.out.println("pecas capturadas:");
		System.out.print(ANSI_YELLOW);
		System.out.println("black: "+Arrays.toString(partida.getCapturedBlack().toArray()));
		System.out.print(ANSI_RESET+ANSI_WHITE);
		System.out.println("white: "+Arrays.toString(partida.getCapturedWhite().toArray()));
		System.out.print(ANSI_RESET);
	}
	
	public static void printMatch(Partida partida) {
		printBoard(partida.getPieces());
		System.out.printf("turno:%d\njogador atual:%s\n",partida.getTurno(),partida.getCurrentPlayer());
		printCaptured(partida);
		
		if(partida.getXeque()) {
			System.out.println("VOCE ESTÁ EM CHEQUE");
		}
	}
	
	public static void printLegenda() {
		System.out.println("P-peão\nB-bispo\nT-torre\nC-cavalo\nK-rei\nQ-rainha");
	}
	
	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
		
		String p=sc.next();
		char col=p.charAt(0);
		int lin=p.charAt(1);
		lin-='0';
		if(col=='L') {
			printLegenda();
			throw new InputMismatchException("");
		}
		if(col<'a' || col>'h' || lin<1 || lin>8) {
			throw new InputMismatchException("posicao não existe");
		}
		
		return new PosicaoXadrez(col, lin);
	}
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		}
}
