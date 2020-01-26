package xadrez;

import tabuleiro.Board;
import tabuleiro.Piece;
import tabuleiro.Posicao;

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
	
	protected boolean temInimigo(Posicao pos) {
		ChessPiece piece=(ChessPiece) getBoard().piece(pos);
		
		return piece!=null && piece.cor!=cor;
	}
	protected boolean temInimigo(int l, int c) {
		ChessPiece piece=(ChessPiece) getBoard().piece(l,c);
		
		return piece!=null && piece.cor!=cor;
	}
}
