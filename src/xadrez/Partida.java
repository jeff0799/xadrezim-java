package xadrez;

import tabuleiro.Board;
import tabuleiro.Posicao;
import xadrez.tiposDePecas.Rei;
import xadrez.tiposDePecas.Torre;

public class Partida {
	private Board board;
	
	public Partida() {
		board=new Board(8,8);
		initalSetup();
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat=new ChessPiece[8][8];
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				mat[i][j]=(ChessPiece)board.piece(i, j);
			}
		}
		
		
		return mat;
	}
	
	private void initalSetup() {
		//board.placePiece(null, new Posicao(0, 5));
		board.placePiece(new Rei(board, Cor.BLACK), new Posicao(0, 4));
		board.placePiece(new Rei(board, Cor.WHITE), new Posicao(7, 4));
		
		board.placePiece(new Torre(board, Cor.BLACK), new Posicao(0, 0));
		board.placePiece(new Torre(board, Cor.BLACK), new Posicao(0, 7));
		board.placePiece(new Torre(board, Cor.WHITE), new Posicao(7, 0));
		board.placePiece(new Torre(board, Cor.WHITE), new Posicao(7, 7));
		
	}
}
