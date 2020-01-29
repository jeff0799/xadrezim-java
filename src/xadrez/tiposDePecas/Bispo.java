package xadrez.tiposDePecas;

import tabuleiro.Board;
import xadrez.ChessPiece;
import xadrez.Cor;

public class Bispo extends ChessPiece{

	public Bispo(Board board, Cor cor) {
		super(board, cor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat=new boolean[getBoard().getLinhas()][getBoard().getColunas()];
		int lin, col;
		//cima-dir
		for(lin=posicao.getLinha()-1, col=posicao.getColuna()+1;
				getBoard().posicaoExiste(lin,col) && !getBoard().temPeca(lin,col)  ;lin--,col++) {
			
			mat[lin][col]=true;
		}
		if(getBoard().posicaoExiste(lin,col) && temInimigo(lin,col)) {
			mat[lin][col]=true;
		}
		// cima-esq
		for (lin = posicao.getLinha()-1, col = posicao.getColuna()-1; getBoard().posicaoExiste(lin, col)
				&& !getBoard().temPeca(lin, col); lin--,col--) {

			mat[lin][col] = true;
		}
		if (getBoard().posicaoExiste(lin, col) && temInimigo(lin, col)) {
			mat[lin][col] = true;
		}
		//baixo-dir
		for (lin = posicao.getLinha()+1, col = posicao.getColuna()+1; getBoard().posicaoExiste(lin, col)
				&& !getBoard().temPeca(lin, col);lin++, col++) {

			mat[lin][col] = true;
		}
		if (getBoard().posicaoExiste(lin, col) && temInimigo(lin, col)) {
			mat[lin][col] = true;
		}
		//baixo-esq
		for (lin = posicao.getLinha()+1, col = posicao.getColuna() - 1; getBoard().posicaoExiste(lin, col)
				&& !getBoard().temPeca(lin, col);lin++, col--) {

			mat[lin][col] = true;
		}
		if (getBoard().posicaoExiste(lin, col) && temInimigo(lin, col)) {
			mat[lin][col] = true;
		}
		
		return mat;
	}

}
