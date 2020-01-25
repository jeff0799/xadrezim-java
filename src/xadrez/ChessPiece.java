package xadrez;

import tabuleiro.Board;
import tabuleiro.Piece;

public abstract class ChessPiece extends Piece{
	private Cor cor;
	//private int moveCounter;

	public ChessPiece(Board board, Cor cor) {
		super(board);
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}
}
