package tabuleiro;

public class Piece {
	protected Posicao posicao;
	private Board board;

	public Piece(Board board) {
		this.board = board;
		posicao=null;
	}



	protected Board getBoard() {
		return board;
	}
	
	
}
