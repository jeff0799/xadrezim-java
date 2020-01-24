package xadrez;

import tabuleiro.Board;
import tabuleiro.Piece;

public class ChessPiece extends Piece{
	Cor cor;
	//private int moveCounter;

	public ChessPiece(Board board, Cor cor) {
		super(board);
		this.cor = cor;
	}
	
	
}
