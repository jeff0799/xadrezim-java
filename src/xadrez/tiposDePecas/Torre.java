package xadrez.tiposDePecas;

import tabuleiro.Board;
import xadrez.ChessPiece;
import xadrez.Cor;

public class Torre extends ChessPiece{

	public Torre(Board board, Cor cor) {
		super(board, cor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat=new boolean[getBoard().getLinhas()][getBoard().getColunas()];
		int lin, col;
		//cima
		for(lin=posicao.getLinha()-1, col=posicao.getColuna();
				getBoard().posicaoExiste(lin,col) && !getBoard().temPeca(lin,col)  ;lin--) {
			
			mat[lin][col]=true;
		}
		if(getBoard().posicaoExiste(lin,col) && temInimigo(lin,col)) {
			mat[lin][col]=true;
		}
		// baixo
		for (lin = posicao.getLinha()+1, col = posicao.getColuna(); getBoard().posicaoExiste(lin, col)
				&& !getBoard().temPeca(lin, col); lin++) {

			mat[lin][col] = true;
		}
		if (getBoard().posicaoExiste(lin, col) && temInimigo(lin, col)) {
			mat[lin][col] = true;
		}
		//direita
		for (lin = posicao.getLinha(), col = posicao.getColuna()+1; getBoard().posicaoExiste(lin, col)
				&& !getBoard().temPeca(lin, col); col++) {

			mat[lin][col] = true;
		}
		if (getBoard().posicaoExiste(lin, col) && temInimigo(lin, col)) {
			mat[lin][col] = true;
		}
		//esquerda
		for (lin = posicao.getLinha(), col = posicao.getColuna() - 1; getBoard().posicaoExiste(lin, col)
				&& !getBoard().temPeca(lin, col); col--) {

			mat[lin][col] = true;
		}
		if (getBoard().posicaoExiste(lin, col) && temInimigo(lin, col)) {
			mat[lin][col] = true;
		}
		
		
		return mat;
	}

}
