package tabuleiro;

public class Piece {
	protected Posicao posicao;
	private Board board;
	
	public Piece(Posicao posicao) {
		this.posicao = posicao;
		board=null;
	}

	protected Board getBoard() {
		return board;
	}
	
	
}
