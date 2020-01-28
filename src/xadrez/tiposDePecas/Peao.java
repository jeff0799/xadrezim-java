package xadrez.tiposDePecas;

import tabuleiro.Board;
import xadrez.ChessPiece;
import xadrez.Cor;

public class Peao extends ChessPiece{

	public Peao(Board board, Cor cor) {
		super(board, cor);
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat=new boolean[getBoard().getLinhas()][getBoard().getColunas()];
		int lin=posicao.getLinha(),col=posicao.getColuna(),frente;
		frente=(getCor()==Cor.WHITE)? -1:1;
		
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
		
		return mat;
	}

}
