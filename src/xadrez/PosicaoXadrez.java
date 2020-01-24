package xadrez;

import tabuleiro.Posicao;

public class PosicaoXadrez {
	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		if(coluna<'a' || coluna>'h' || linha<1 || linha>8) {
			throw new ChessException("todo dia isso? essa posicao não existe");
		}
		
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	protected Posicao toPosicao() {
		int row=8-linha;
		int colunm=coluna-'a';
		
		return new Posicao(row,colunm);
	}
	
	protected static PosicaoXadrez FromPosicao(Posicao p) {
		int row=8-p.getLinha();
		char col=(char) (p.getColuna()+'a');
		
		return new PosicaoXadrez(col, row);
	}

	@Override
	public String toString() {
		return coluna+linha+"";
	}
	
	
}
