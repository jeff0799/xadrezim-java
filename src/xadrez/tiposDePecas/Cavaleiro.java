package xadrez.tiposDePecas;

import tabuleiro.Board;
import xadrez.ChessPiece;
import xadrez.Cor;

public class Cavaleiro extends ChessPiece{

	public Cavaleiro(Board board, Cor cor) {
		super(board, cor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "C";
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
		lin=posicao.getLinha() - 2;
		col=posicao.getColuna() + 1;
		if(getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col]=true;
		}
		
		lin = posicao.getLinha() - 2;
		col = posicao.getColuna() - 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		//baixo
		lin = posicao.getLinha() + 2;
		col = posicao.getColuna() + 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		
		lin = posicao.getLinha() + 2;
		col = posicao.getColuna() - 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		// dir
		lin = posicao.getLinha() - 1;
		col = posicao.getColuna() + 2;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		
		lin = posicao.getLinha() + 1;
		col = posicao.getColuna() + 2;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		// esq
		lin = posicao.getLinha() - 1;
		col = posicao.getColuna() - 2;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		
		lin = posicao.getLinha() + 1;
		col = posicao.getColuna() - 2;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		
		return mat;
	}

}
