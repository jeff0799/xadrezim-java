package xadrez;

import tabuleiro.Board;

public class Partida {
	private Board board;
	
	public Partida() {
		board=new Board(8,8);
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
}
