package xadrez.tiposDePecas;

import tabuleiro.Board;
import xadrez.ChessPiece;
import xadrez.Cor;
import xadrez.Partida;

public class Peao extends ChessPiece{
	private Partida partida;

	public Peao(Board board, Cor cor, Partida partida) {
		super(board, cor);
		this.partida=partida;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat=new boolean[getBoard().getLinhas()][getBoard().getColunas()];
		int lin=posicao.getLinha(),col=posicao.getColuna();
		int frente=(getCor()==Cor.WHITE)? -1:1;
		
		if(getBoard().posicaoExiste(lin+frente,col) && !getBoard().temPeca(lin+frente, col)) {
			mat[lin+frente][col]=true;
			if(getBoard().posicaoExiste(lin+2*frente,col) && getMoveCounter()==0 
					&& !getBoard().temPeca(lin+frente*2, col)) {
				mat[lin+frente*2][col]=true;
			}
		}
		//capturas
		if(getBoard().posicaoExiste(lin+frente,col-1) && temInimigo(lin+frente, col-1)) {
			mat[lin+frente][col-1]=true;
		}
		if(getBoard().posicaoExiste(lin+frente,col+1) && temInimigo(lin+frente, col+1)) {
			mat[lin+frente][col+1]=true;
		}
		//en passant
		int esq=col-1,dir=col+1;
		if(getBoard().posicaoExiste(lin,dir) && temInimigo(lin, dir)
				&& partida.getEnPasVulnerable()==getBoard().piece(lin, dir) && !getBoard().temPeca(lin+frente, dir)) {
			mat[lin+frente][dir]=true;
		}
		if(getBoard().posicaoExiste(lin,esq) && temInimigo(lin, esq)
				&& partida.getEnPasVulnerable()==getBoard().piece(lin, esq) && !getBoard().temPeca(lin+frente, esq)) {
			mat[lin+frente][esq]=true;
		}
		
		return mat;
	}

}
