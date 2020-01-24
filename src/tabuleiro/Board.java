package tabuleiro;

public class Board {
	private int linhas, colunas;
	private Piece[][] pecas;
	
	public Board(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas=new Piece[linhas][colunas];
	}
	
	
}
