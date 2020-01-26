package xadrez.tiposDePecas;

import tabuleiro.Board;
import xadrez.ChessPiece;
import xadrez.Cor;
//import xadrez.Partida;

public class Rei extends ChessPiece{
	//Partida partida;

	public Rei(Board board, Cor cor) {
		super(board, cor);
		//this.partida = partida;
	}
	
	@Override
	public String toString() {
		return "K"; //King
	}
	
	private boolean podeMover(int lin,int col) {
		ChessPiece p=(ChessPiece) getBoard().piece(lin, col);
		//return getBoard().posicaoExiste(lin,col) && p!=null || p.getCor()!=getCor();
		return p==null || p.getCor()!=getCor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat=new boolean[getBoard().getLinhas()][getBoard().getColunas()];
		int lin,col;
		
		//cima
		lin=posicao.getLinha() - 1;
		col=posicao.getColuna();
		if(getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col]=true;
		}
		//baixo
		lin = posicao.getLinha() + 1;
		col = posicao.getColuna();
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		//direita
		lin = posicao.getLinha();
		col = posicao.getColuna() + 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		//esquerda
		lin = posicao.getLinha();
		col = posicao.getColuna() - 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		// cima-esq
		lin = posicao.getLinha() - 1;
		col = posicao.getColuna() - 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		// cima-dir
		lin = posicao.getLinha() - 1;
		col = posicao.getColuna() +1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		// baixo-esq
		lin = posicao.getLinha() +1;
		col = posicao.getColuna() - 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		// baixo-dir
		lin = posicao.getLinha() + 1;
		col = posicao.getColuna() + 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}

		return mat;
	}
}
