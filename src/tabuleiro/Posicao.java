package tabuleiro;

public class Posicao {
	private int linha, coluna;

	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	@Override
	public String toString() {
		return String.format("%d, %d", linha, coluna);
	}
	
	public void setPosition(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	
}