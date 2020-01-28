package xadrez;

import tabuleiro.Board;
import tabuleiro.Piece;
import tabuleiro.Posicao;

public abstract class ChessPiece extends Piece{
	private Cor cor;
	private int moveCounter=0;

	public ChessPiece(Board board, Cor cor) {
		super(board);
		this.cor = cor;
	}
	
	public int getMoveCounter() {
		return moveCounter;
	}
	
	public Cor getCor() {
		return cor;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.FromPosicao(posicao);
	}
	
	protected boolean temInimigo(Posicao pos) {
		ChessPiece piece=(ChessPiece) getBoard().piece(pos);
		
		return piece!=null && piece.cor!=cor;
	}
	protected boolean temInimigo(int l, int c) {
		ChessPiece piece=(ChessPiece) getBoard().piece(l,c);
		
		return piece!=null && piece.cor!=cor;
	}
	
	protected void increaseCounter() {
		moveCounter++;
	}
	protected void decreaseCounter() {
		moveCounter--;
	}
}
