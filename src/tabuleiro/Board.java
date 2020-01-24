package tabuleiro;

public class Board {
	private int linhas, colunas;
	private Piece[][] pecas;
	
	public Board(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas=new Piece[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Piece piece(int l,int c) {
		return pecas[l][c];
	}
	
	public Piece piece(Posicao p) {
		return pecas[p.getLinha()][p.getColuna()];
	}
}
