package tabuleiro;

public abstract class Piece {
	protected Posicao posicao;
	private Board board;

	public Piece(Board board) {
		this.board = board;
		posicao=null;
	}

	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Posicao p) {
		return possibleMoves()[p.getLinha()][p.getColuna()];
	}
	
	public boolean temPossibleMove() {
		boolean[][] mat=possibleMoves();
		
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat[0].length;j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
